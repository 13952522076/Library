/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.SMSLibException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Setting;
import com.sammyun.entity.Member;
import com.sammyun.entity.Payment;
import com.sammyun.entity.Payment.Method;
import com.sammyun.entity.Payment.Status;
import com.sammyun.entity.Payment.Type;
import com.sammyun.entity.Sn;
import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.plugin.PaymentPlugin.NotifyMethod;
import com.sammyun.service.MemberService;
import com.sammyun.service.PaymentService;
import com.sammyun.service.PluginService;
import com.sammyun.service.SnService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SmsCellUtil;
import com.sammyun.util.SpringUtils;

/**
 * Controller - 支付
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("shopPaymentController")
@RequestMapping("/payment")
public class PaymentController extends BaseController
{

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    @Resource(name = "paymentServiceImpl")
    private PaymentService paymentService;

    @Resource(name = "snServiceImpl")
    private SnService snService;

    /**
     * 提交
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(Type type, String paymentPluginId, String sn, BigDecimal amount, String bank, String bankName,
            HttpServletRequest request, HttpServletResponse response, ModelMap model,
            RedirectAttributes redirectAttributes)
    {
        Member member = memberService.getCurrent();
        if (member == null)
        {
            return ERROR_VIEW;
        }
        PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin(paymentPluginId);
        if (paymentPlugin == null || !paymentPlugin.getIsEnabled())
        {
            return ERROR_VIEW;
        }
        Payment payment = new Payment();
        String description = null;
        type = Type.payment;
        //if (type == Type.payment)
        //{
        //}
        //else 
        if (type == Type.recharge)
        {
            Setting setting = SettingUtils.get();
            //if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0 || amount.precision() > 15
            //        || amount.scale() > setting.getPriceScale())
            //{
             //   return ERROR_VIEW;
            //}
            payment.setSn(snService.generate(Sn.Type.payment));
            payment.setType(Type.recharge);
            payment.setMethod(Method.online);
            payment.setStatus(Status.wait);
            payment.setPaymentMethod(paymentPlugin.getPaymentName());
            payment.setFee(paymentPlugin.calculateFee(amount));
            payment.setAmount(paymentPlugin.calculateAmount(amount));
            payment.setPaymentPluginId(paymentPluginId);
            payment.setExpire(paymentPlugin.getTimeout() != null ? DateUtils.addMinutes(new Date(),
                    paymentPlugin.getTimeout()) : null);
            payment.setMember(member);
            paymentService.save(payment);
            description = message("shop.member.deposit.recharge");
        }
        else
        {
            return ERROR_VIEW;
        }
        model.addAttribute("requestUrl", paymentPlugin.getRequestUrl());
        model.addAttribute("requestMethod", paymentPlugin.getRequestMethod());
        model.addAttribute("requestCharset", paymentPlugin.getRequestCharset());
        model.addAttribute("parameterMap", paymentPlugin.getParameterMap(payment.getSn(), description, request));
        if (StringUtils.isNotEmpty(paymentPlugin.getRequestCharset()))
        {
            response.setContentType("text/html; charset=" + paymentPlugin.getRequestCharset());
        }
        return "shop/payment/submit";
    }

    /**
     * PC端通知
     */
    @RequestMapping("/notify/{notifyMethod}/{sn}")
    public String notify(@PathVariable NotifyMethod notifyMethod, @PathVariable String sn, HttpServletRequest request,
            ModelMap model)
    {
        Payment payment = paymentService.findBySn(sn);
        if (payment != null)
        {
            PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin(payment.getPaymentPluginId());
            if (paymentPlugin != null)
            {
                if (paymentPlugin.verifyNotify(sn, notifyMethod, request))
                {
                    paymentService.handle(payment);
                    // 支付成功，发送支付成功邮件。
                    Member member = memberService.getCurrent();
                    if (member == null)
                    {
                        member = payment.getMember();
                    }
                    if (member != null && member.getEmail() != null)
                    {
                        //
                        // mailService.sendPayedMail(member.getEmail(),
                        // payment);
                    }
                    if (member != null && member.getMobile() != null)
                    {
                        Setting setting = SettingUtils.get();
                        if (setting.getIsMsgNotified())
                        {
                            try
                            {
                                HashMap smsSendResult = SmsCellUtil.getInstance().sendDone(member.getMobile(),
                                        SpringUtils.getMessage("shop.send.payment.messageContent"),
                                        DateUtil.getCurrentDate());
                                if ("0".equals(smsSendResult.get("return")))
                                {
                                    logger.info("send paymentMsg Sms Push Success");
                                }
                                else
                                {
                                    logger.info("send paymentMsg Sms Push Failed");
                                }
                            }
                            catch (SMSLibException e)
                            {
                                e.printStackTrace();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                model.addAttribute("notifyMessage", paymentPlugin.getNotifyMessage(sn, notifyMethod, request));
            }
            model.addAttribute("payment", payment);
        }
        return "shop/payment/notify";
    }

    /**
     * 手机端通知
     */
    @RequestMapping("/mobileNotify/{notifyMethod}/{sn}")
    public String mobileNotify(@PathVariable NotifyMethod notifyMethod, @PathVariable String sn,
            HttpServletRequest request, ModelMap model)
    {
        Payment payment = paymentService.findBySn(sn);
        if (payment != null)
        {
            PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin(payment.getPaymentPluginId());
            if (paymentPlugin != null)
            {
                if (paymentPlugin.verifyMobileNotify(sn, notifyMethod, request))
                {
                    paymentService.handle(payment);
                    // 支付成功，发送支付成功邮件。
                    Member member = memberService.getCurrent();
                    if (member == null)
                    {
                        member = payment.getMember();
                    }
                    if (member != null && member.getEmail() != null)
                    {
                        // mailService.sendPayedMail(member.getEmail(),
                        // payment);
                    }
                    if (member != null && member.getMobile() != null)
                    {
                        sendMsgToMember(member, SpringUtils.getMessage("shop.send.payment.messageContent"));
                    }
                }
                model.addAttribute("notifyMessage", paymentPlugin.getNotifyMessage(sn, notifyMethod, request));
            }
            model.addAttribute("payment", payment);
        }
        return "shop/payment/mobileNotify";
    }

    /**
     * 发送短信给会员
     * 
     * @see [类、类#方法、类#成员]
     */
    private void sendMsgToMember(Member member, String message)
    {
        Setting setting = SettingUtils.get();
        if (setting.getIsMsgNotified())
        {
            try
            {
                HashMap smsSendResult = SmsCellUtil.getInstance().sendDone(member.getMobile(), message,
                        DateUtil.getCurrentDate());
                if ("0".equals(smsSendResult.get("return")))
                {
                    logger.info("send paymentMsg Sms Push Success");
                }
                else
                {
                    logger.info("send paymentMsg Sms Push Failed");
                }
            }
            catch (SMSLibException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
