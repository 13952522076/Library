/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.PaymentMethod;
import com.sammyun.entity.PaymentMethod.Method;
import com.sammyun.service.PaymentMethodService;

/**
 * Controller - 支付方式
 * 


 */
@Controller("adminPaymentMethodController")
@RequestMapping("/console/payment_method")
public class PaymentMethodController extends BaseController
{

    @Resource(name = "paymentMethodServiceImpl")
    private PaymentMethodService paymentMethodService;

    

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("methods", Method.values());
        return "/console/payment_method/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(PaymentMethod paymentMethod, Long[] shippingMethodIds, RedirectAttributes redirectAttributes)
    {
       
        paymentMethodService.save(paymentMethod);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("methods", Method.values());
        model.addAttribute("paymentMethod", paymentMethodService.find(id));
        return "/console/payment_method/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(PaymentMethod paymentMethod, Long[] shippingMethodIds, RedirectAttributes redirectAttributes)
    {
        if (!isValid(paymentMethod))
        {
            return ERROR_VIEW;
        }
        paymentMethodService.update(paymentMethod, "orders");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", paymentMethodService.findPage(pageable));
        return "/console/payment_method/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids.length >= paymentMethodService.count())
        {
            return Message.error("console.common.deleteAllNotAllowed");
        }
        paymentMethodService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
