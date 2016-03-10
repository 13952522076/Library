/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.alipayDirect;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sammyun.Setting;
import com.sammyun.entity.Payment;
import com.sammyun.entity.PluginConfig;
import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.util.RSAUtils;
import com.sammyun.util.SettingUtils;

/**
 * Plugin - 支付宝(即时交易)
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Component("alipayDirectPlugin")
public class AlipayDirectPlugin extends PaymentPlugin
{

    @Override
    public String getName()
    {
        return "支付宝(即时交易)";
    }

    @Override
    public String getVersion()
    {
        return "1.0";
    }

    @Override
    public String getAuthor()
    {
        return "Sencloud";
    }

    @Override
    public String getSiteUrl()
    {
        return "http://www.sammyun.com.cn";
    }

    @Override
    public String getInstallUrl()
    {
        return "alipay_direct/install.ct";
    }

    @Override
    public String getUninstallUrl()
    {
        return "alipay_direct/uninstall.ct";
    }

    @Override
    public String getSettingUrl()
    {
        return "alipay_direct/setting.ct";
    }

    @Override
    public String getRequestUrl()
    {
        return "https://mapi.alipay.com/gateway.do";
    }

    @Override
    public RequestMethod getRequestMethod()
    {
        return RequestMethod.get;
    }

    @Override
    public String getRequestCharset()
    {
        return "UTF-8";
    }

    @Override
    public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request)
    {
        Setting setting = SettingUtils.get();
        PluginConfig pluginConfig = getPluginConfig();
        Payment payment = getPayment(sn);
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("service", "create_direct_pay_by_user");
        parameterMap.put("partner", pluginConfig.getAttribute("partner"));
        parameterMap.put("_input_charset", "utf-8");
        parameterMap.put("sign_type", "MD5");
        parameterMap.put("return_url", getNotifyUrl(sn, NotifyMethod.sync));
        parameterMap.put("notify_url", getNotifyUrl(sn, NotifyMethod.async));
        parameterMap.put("out_trade_no", sn);
        parameterMap.put("subject",
                StringUtils.abbreviate(description.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 60));
        parameterMap.put("body",
                StringUtils.abbreviate(description.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 600));
        parameterMap.put("payment_type", "1");
        parameterMap.put("seller_id", pluginConfig.getAttribute("partner"));
        parameterMap.put("total_fee", payment.getAmount().setScale(2).toString());
        parameterMap.put("show_url", setting.getSiteUrl());
        parameterMap.put("paymethod", "directPay");
        parameterMap.put("exter_invoke_ip", request.getLocalAddr());
        parameterMap.put("extra_common_param", "preschoolEdu");
        parameterMap.put("sign", generateSign(parameterMap));
        return parameterMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean verifyNotify(String sn, NotifyMethod notifyMethod, HttpServletRequest request)
    {
        PluginConfig pluginConfig = getPluginConfig();
        Payment payment = getPayment(sn);
        if (generateSign(request.getParameterMap()).equals(request.getParameter("sign"))
                && pluginConfig.getAttribute("partner").equals(request.getParameter("seller_id"))
                && sn.equals(request.getParameter("out_trade_no"))
                && ("TRADE_SUCCESS".equals(request.getParameter("trade_status")) || "TRADE_FINISHED".equals(request.getParameter("trade_status")))
                && payment.getAmount().compareTo(new BigDecimal(request.getParameter("total_fee"))) == 0)
        {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("service", "notify_verify");
            parameterMap.put("partner", pluginConfig.getAttribute("partner"));
            parameterMap.put("notify_id", request.getParameter("notify_id"));
            if ("true".equals(post("https://mapi.alipay.com/gateway.do", parameterMap)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyMobileNotify(String sn, NotifyMethod notifyMethod, HttpServletRequest request)
    {
        PluginConfig pluginConfig = getPluginConfig();
        Payment payment = getPayment(sn);
        String content = generateMobileSign(request);
        if (RSAUtils.verify(content, request.getParameter("sign"), PluginConfig.ALI_PUBLIC_KEY, "utf-8")
                && pluginConfig.getAttribute("partner").equals(request.getParameter("seller_id"))
                && sn.equals(request.getParameter("out_trade_no"))
                && ("TRADE_SUCCESS".equals(request.getParameter("trade_status")) || "TRADE_FINISHED".equals(request.getParameter("trade_status")))
                && payment.getAmount().compareTo(new BigDecimal(request.getParameter("total_fee"))) == 0)
        {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("service", "notify_verify");
            parameterMap.put("partner", pluginConfig.getAttribute("partner"));
            parameterMap.put("notify_id", request.getParameter("notify_id"));
            if ("true".equals(post("https://mapi.alipay.com/gateway.do", parameterMap)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getNotifyMessage(String sn, NotifyMethod notifyMethod, HttpServletRequest request)
    {
        if (notifyMethod == NotifyMethod.async)
        {
            return "success";
        }
        return null;
    }

    @Override
    public Integer getTimeout()
    {
        return 21600;
    }

    /**
     * 生成签名
     * 
     * @param parameterMap 参数
     * @return 签名
     */
    private String generateSign(Map<String, ?> parameterMap)
    {
        PluginConfig pluginConfig = getPluginConfig();
        return DigestUtils.md5Hex(joinKeyValue(new TreeMap<String, Object>(parameterMap), null,
                pluginConfig.getAttribute("key"), "&", true, "sign_type", "sign"));
    }

    /**
     * 生成手机端待签名字符串
     * 
     * @param parameterMap 参数
     * @return 签名
     */
    private String generateMobileSign(HttpServletRequest request)
    {
        // 获取支付宝POST过来反馈信息
        Map<String, Object> params = new HashMap<String, Object>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            params.put(name, valueStr);
        }
        // 过滤空值、sign与sign_type参数
        Map<String, Object> sParaNew = PaymentPlugin.paraFilter(params);
        // 获取待签名字符串
        String preSignStr = PaymentPlugin.createLinkString(sParaNew);
        // 获取待签名字符串
        return preSignStr;
    }
}
