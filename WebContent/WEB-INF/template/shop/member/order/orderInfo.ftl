<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>处理订单[#if systemShowPowered] -图书智能管理推荐系统[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/order.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>

<script type="text/javascript">
$().ready(function() {
	var $newReceiverButton = $("#newReceiverButton");
	var $orderForm = $("#orderForm");
	var $receiverId = $("#receiverId");
	var $paymentMethodId = $(".paymentMethodInfo").find('img');
	var $shippingMethodId = $("#shippingMethod :radio");
	var $isInvoice = $("#isInvoice");
	var $invoiceTitleTr = $("#invoiceTitleTr");
	var $invoiceTypeSelect = $("#invoiceType");
	var $invoiceTitle = $("#invoiceTitle");
	var $code = $("#code");
	var $couponCode = $("#couponCode");
	var $couponName = $("#couponName");
	var $couponButton = $("#couponButton");
	var $useBalance = $("#useBalance");
	var $freight = $("#freight");
	var $promotionDiscount = $("#promotionDiscount");
	var $couponDiscount = $("#couponDiscount");
	var $tax = $("#tax");
	var $amountPayable = $("#amountPayable");
	var $submit = $("#submit");
	var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
	var $receiverFormModify = $("#receiverFormModify");
	var $modifyReceiverSubmit = $("#modifyReceiverSubmit");
	var shippingMethodIds = {};
	
	var $buyRulesCheckboxGroup = document.getElementById('buyRulesCheckboxGroup');
	var $invoiceTitleCheckboxGroup = document.getElementById('invoiceTitleCheckboxGroup');
	
	checkBoxGroupControl($buyRulesCheckboxGroup);
	checkBoxGroupControl($invoiceTitleCheckboxGroup);
	
	[@compress single_line = true]
		[#list paymentMethods as paymentMethod]
			shippingMethodIds["${paymentMethod.id}"] = [
				[#list paymentMethod.shippingMethods as shippingMethod]
					"${shippingMethod.id}"[#if shippingMethod_has_next],[/#if]
				[/#list]
			];
		[/#list]
	[/@compress]
	
	[#if !member.receivers?has_content]
	    addNewDeliveryAddress(true)
	[/#if]
	
	//选择收货信息
	var $receiverList = $("#receiverList").find('li');
	var $receiverConsignee = $("#receiverConsignee");
	var $receiverPhone = $("#receiverPhone");
	$receiverList.each(function(){
	    var $this = $(this);
	    var $receiverInfo = $this.find('label');
	    $receiverInfo.click(function(){
	        if(!!$(this).hasClass('checked')){
	            $receiverId.val($this.attr("receiverId"));
	            $receiverConsignee.val($this.attr("receiverConsignee"));
	            $receiverPhone.val($this.attr("receiverPhone"));
	            return;
	        }else{
	            $this.siblings("li").find('label').removeClass("checked");
	            $this.siblings("li").find('label').addClass("unchecked");
	            $(this).removeClass("unchecked");
	            $(this).addClass("checked");
	            $receiverId.val($this.attr("receiverId"));
	            $receiverConsignee.text($this.attr("receiverConsignee"));
	            $receiverPhone.text($this.attr("receiverPhone"));
	        }
	    });
	});
	
	$("#checkbox4InvoiceTitleTrLabel").click(function(){
	    var $invoiceTypeAndTitle =  $("#invoiceTypeAndTitle");
        $invoiceTypeAndTitle.toggle();
        calculate();
    });
	
	// 计算
	function calculate() {
		$.ajax({
			url: "calculate.ct",
			type: "POST",
			data: $orderForm.serialize(),
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.message.type == "success") {
					$freight.text(currency(data.freight, true));
					if (data.promotionDiscount > 0) {
						$promotionDiscount.text(currency(data.promotionDiscount, true));
						$promotionDiscount.parent().parent().show();
					} else {
						$promotionDiscount.parent().parent().hide();
					}
					if (data.couponDiscount > 0) {
						$couponDiscount.text(currency(data.couponDiscount, true));
						$couponDiscount.parent().parent().show();
					} else {
						$couponDiscount.parent().parent().hide();
					}
					if (data.tax > 0) {
						$tax.text(currency(data.tax, true));
						$tax.parent().parent().show();
					} else {
						$tax.parent().parent().hide();
					}
					$amountPayable.text(currency(data.amountPayable, true, false));
				} else {
					$.message(data.message);
					setTimeout(function() {
						location.reload(true);
					}, 3000);
				}
			}
		});
	}
	
	// 配送方式
	$shippingMethodId.click(function() {
		var $this = $(this);
		if ($this.prop("disabled")) {
			return false;
		}
		$this.closest("dd").addClass("selected").siblings().removeClass("selected");
		calculate();
	});
	
	// 优惠券
	$couponButton.click(function() {
		if ($code.val() == "") {
			if ($.trim($couponCode.val()) == "") {
				return false;
			}
			$.ajax({
				url: "coupon_info.ct",
				type: "POST",
				data: {code : $couponCode.val()},
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$couponButton.prop("disabled", true);
				},
				success: function(data) {
					if (data.message.type == "success") {
						$code.val($couponCode.val());
						//$couponCode.hide();
						//$couponName.text(data.couponName).show();
						$couponButton.text("${message("shop.order.codeCancel")}");
						//$couponButton.css("background","url(${base}/resources/shop/images/coupon_cancel.jpg)");
						calculate();
					} else {
						$.message(data.message);
					}
				},
				complete: function() {
					$couponButton.prop("disabled", false);
				}
			});
		} else {
			$code.val("");
			$couponCode.show();
			$couponName.hide();
			$couponButton.text("${message("shop.order.codeConfirm")}");
			
			//$couponButton.css("background","url(${base}/resources/shop/images/coupon_confirm.jpg)");
			calculate();
		}
	});
	
	// 订单提交
	$submit.click(function() {
		var $checkedPaymentMethodId = $paymentMethodId.filter(".paymentImageSection");
		var $paymentPluginId = $("#paymentPluginId");
		var $checkedShippingMethodId = $shippingMethodId.filter(":checked");
		
		var $checkbox4buyRules = $("#checkbox4buyRules");
		var $isInvoice = $("#isInvoice");
		var $invoiceTitle = $("#invoiceTitle");
		var $invoiceType = $("#invoiceType");
		
		if ($checkedPaymentMethodId.size() == 0 || !$paymentPluginId.val()) {
			$.message("warn", "${message("shop.order.paymentMethodRequired")}");
			return false;
		}
		if ($checkedShippingMethodId.size() == 0) {
			$.message("warn", "${message("shop.order.shippingMethodRequired")}");
			return false;
		}
		
		if(!$checkbox4buyRules[0].checked){
		    $.message("warn", "${message('shop.order.agreenbuyrules')}");
			return;
		}
		[#if setting.isInvoiceEnabled]
			if($isInvoice[0].checked){
			    if(!$invoiceTitle.val()){
			        $.message("warn", "${message('shop.order.invoiceTitleNotEmpty')}");
			        $invoiceTitle.focus();
				    return;
			    }
			    if(!$invoiceType.val()){
			        $.message("warn", "${message('shop.order.invoiceTypeNotEmpty')}");
			        $invoiceType.focus();
				    return;
			    }
			}
		[/#if]
		
		$.ajax({
			url: "createOrder.ct",
			type: "POST",
			data: $orderForm.serialize(),
			dataType: "json",
			cache: false,
			beforeSend: function() {
				$submit.prop("disabled", true);
			},
			success: function(message) {
				if (message.type == "success") {
					location.href = "payoff.ct?orderSn=" + message.content;
				} else {
					$.message(message);
					setTimeout(function() {
						location.reload(true);
					}, 3000);
				}
			},
			complete: function() {
				$submit.prop("disabled", false);
			}
		});
	});
	
	// 新增/修改地址表单验证
	$receiverFormModify.validate({
		rules: {
			consignee: "required",
			modify_orderAreaId: "required",
			address: "required",
			phone: {
			    required: true,
				isMobile: true
			},
			zipCode:{
			   required: true,
			   isZipCode: true
			} 
		},
		submitHandler: function(form) {
		    var $receiverModifyAreaSpan = $("#receiverModifyAreaSpan");
		    var $areaSelects = $receiverModifyAreaSpan.find("select");
		    var $isDefault_Modify =  $("#isDefault_Modify");
		    var requestUrl ='';
		    var $areaIsNotEmpty = true;
		    for(i=0; i< $areaSelects.length; i++){
		         if(!$areaSelects[i].value){
		           $.message("warn",message("shop.validate.areaError"));
		           $(this).focus();
		           return false;
		         }
		    }
		    if(optionType == "modify"){
		        requestUrl = "${base}/member/order/update_receiver.ct";
		    }else if(optionType == "add"){
		        requestUrl = "${base}/member/receiver/add_receiver.ct";
		    }
		    if($isDefaultYes_lable.hasClass("checked")){
		        $isDefault_Modify.val("true");
		    }
		    if($isDefaultNo_lable.hasClass("checked")){
		        $isDefault_Modify.val("false");
		    }
			$.ajax({
				url: requestUrl,
				type: "POST",
				data: $receiverFormModify.serialize(),
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$modifyReceiverSubmit.prop("disabled", true);
				},
				success: function(data) {
					if (data.message.type == "success") {
						$modifyOrderAddressPanel.fadeOut('normal');
						hiddenMask();
						location.reload(true);
					} else {
						$.message("warn",data.message.content);
					}
				},
				complete: function() {
					$modifyReceiverSubmit.prop("disabled", false);
				}
			});
		}
	});
    
	
	var $modifyReceiverCancle = $("#modifyReceiverCancle");
    $modifyReceiverCancle.click(function(){
         hiddenMask();
	     $modifyOrderAddressPanel.fadeOut('normal');
    });
    
    var $isDefaultYes_lable = $("#isDefaultYes_lable");
    var $isDefaultNo_lable = $("#isDefaultNo_lable");
    $isDefaultYes_lable.click(function(){
        if(!$isDefaultYes_lable.hasClass("checked") && $isDefaultYes_lable.hasClass("unchecked")){
            $isDefaultYes_lable.removeClass("unchecked");
            $isDefaultYes_lable.addClass("checked");
            $("#isDefault_Modify").val("true");
        }
        if($isDefaultNo_lable.hasClass("checked")){
            $isDefaultNo_lable.removeClass("checked");
            $isDefaultNo_lable.addClass("unchecked");
        }
    });
    
    $isDefaultNo_lable.click(function(){
        if(!$isDefaultNo_lable.hasClass("checked") && $isDefaultNo_lable.hasClass("unchecked")){
            $isDefaultNo_lable.removeClass("unchecked");
            $isDefaultNo_lable.addClass("checked");
            $("#isDefault_Modify").val("false");
        }
        if($isDefaultYes_lable.hasClass("checked")){
            $isDefaultYes_lable.removeClass("checked");
            $isDefaultYes_lable.addClass("unchecked");
        }
    });
});

function deleteReceiver(receiverId){
    var msg = "${message("shop.personal.receiver.delete.confirm")}";
    if (confirm(msg)==true){ 
        $.ajax({
            url: '${base}/member/receiver/delete_receiver.ct',
			type: "POST",
			data: {
			    id:receiverId
			},
			dataType: "json",
			cache: false,
			beforeSend: function() {
				
			},
			success: function(data) {
				if (data.message.type == "success") {
					location.reload(true);
				} else {
					$.message("warn",data.message.content);
				}
			},
			complete: function() {
				
			}
        });
    }else{ 
        return false; 
    } 
}

/**
 * 新增地址信息
 */
function addNewDeliveryAddress(hasReceiver){
        var $modify_orderAreaId = $("#modify_orderAreaId");
        var $popTitleReceiver = $("#pop_title_receiver");
        var $modifyReceiverSubmitSpan = $("#modifyReceiverSubmit span");
        var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
	    var $receiverFormModify = $("#receiverFormModify");
		$receiverFormModify[0].reset();
        $receiverFormModify.attr("action",preschoolEdu.base + "/member/receiver/add_receiver.ct");
        $popTitleReceiver.text("${message("shop.orderDetail.modifyReceiptAddress.add")}");
        $modifyReceiverSubmitSpan.text("${message("shop.orderDetail.modifyReceiverSubmit.add")}");
        if(hasReceiver){
           $("#modifyReceiverCancle").css({'display':'none'});
        }
	    $modify_orderAreaId.lSelect({
            url: preschoolEdu.base+"/common/area.ct"
		});
		optionType = "add";
		showMask();
		$modifyOrderAddressPanel.show();
	    verticalCenterWin($modifyOrderAddressPanel);
}

/**
 * 更新地址信息
 */
function modifyDeliveryAddress(receiverId){
    showMask();
    $("#pop_title_receiver").text("${message("shop.orderDetail.modifyReceiptAddress")}");
    $("#modifyReceiverSubmit span").text("${message("shop.orderDetail.modifyReceiverSubmit")}");
    var $modify_id = $("#modify_id");
    
    var $hiddenReceiver_consignee = $('#hiddenReceiver_consignee_' + receiverId);
	var $hiddenReceiver_area = $('#hiddenReceiver_area_' + receiverId);
	var $hiddenReceiver_areaTreePath = $('#hiddenReceiver_areaTreePath_' + receiverId);
	var $hiddenReceiver_isDefault = $('#hiddenReceiver_isDefault_' + receiverId);
	var $hiddenReceiver_address = $('#hiddenReceiver_address_' + receiverId);
	var $hiddenReceiver_zipCode = $('#hiddenReceiver_zipCode_' + receiverId);
	var $hiddenReceiver_phone = $('#hiddenReceiver_phone_' + receiverId);
    
    var $modify_consignee = $("#modify_consignee");
    var $modify_orderAreaId = $("#modify_orderAreaId");
    var $modify_address = $("#modify_address");
    var $modify_zipCode = $("#modify_zipCode");
    var $modify_phone = $("#modify_phone");
    var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
    var $isDefaultModify = $("#isDefault_Modify");
    var $isDefaul = $("#isDefaul");
    
    var $isDefaultYes_lable = $("#isDefaultYes_lable");
    var $isDefaultNo_lable = $("#isDefaultNo_lable");
    $modify_id.val(receiverId);
    $modify_consignee.val($hiddenReceiver_consignee.val());
    $modify_orderAreaId.val($hiddenReceiver_area.val());
    $modify_orderAreaId.attr('treePath',$hiddenReceiver_areaTreePath.val());
    $modify_address.val($hiddenReceiver_address.val());
    $modify_zipCode.val($hiddenReceiver_zipCode.val());
    $modify_phone.val($hiddenReceiver_phone.val());
    $modify_orderAreaId.lSelect({
	    url: preschoolEdu.base+"/common/area.ct"
	});
	if($isDefaul.val()){
	    if($isDefaultYes_lable.hasClass("unchecked")){
	        $isDefaultYes_lable.removeClass("unchecked");
	        $isDefaultYes_lable.addClass("checked");
	        $isDefaul.val("true");
	        $isDefaultNo_lable.removeClass("checked");
	    }
	}else{
	    if($isDefaultNo_lable.hasClass("unchecked")){
	        $isDefaultNo_lable.removeClass("unchecked");
	        $isDefaultNo_lable.addClass("checked");
	        $isDefaul.val("false");
	        $isDefaultYes_lable.removeClass("checked");
	    }
	}
	optionType = "modify";
	$modifyOrderAddressPanel.show();
	verticalCenterWin($modifyOrderAddressPanel);
}
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="orderContainer order">
		<div class="span24">
			<div class="step">
				<ul>
					<li>处理订单</li>
				</ul>
			</div>
			<div class="info">
			    <div id="sendMode" class="sendMode">
			        <div class="helpTitle">
			        ${message("shop.orderDetail.pointsRule")} ${setting.phone}
			        <!--
			        <b>温馨提示:</b>
			        <p>01/ 春节期间发货安排：</p>
						<p style="padding-left: 20px;">商城优先顺丰快递，2月17日-24日快递停运暂停发货，2月25日起逐步恢复正常；停运期间订单及2月10日-24日顺丰无法到达区域的订单，将在2月25日起陆续发出。 </p>

					 <p>02/ 春节期间客服安排： </p>
					  <p style="padding-left: 20px;">2月16-24日春节期间，客服休假，请自助下单；如需帮助请拨打公司电话 ${setting.phone}，值班人员将记录好您的问题，退货请保持购物清单、包装吊牌完整，节后（2月25日后）统一处理。</p>
					  -->
			       </div>
			        <div class="title">${message("shop.orderDetail.shippingMethod")}</div>
			        <div class="sendModeContent">
			            <p style="padding-top: 0px;">${message("shop.product.content.sendWay.Desc")}</p>
			            <table class="area-table" cellspacing="0" rowspacing="0" border="0">
			                <tr>
			                    <td style="border-right:1px solid #020202;border-bottom:1px solid #020202; width: 220px;">${message("shop.orderDetail.area1")}</td>
			                    <td style="border-bottom:1px solid #020202">&nbsp;&nbsp;${message("shop.orderDetail.area11")}</td>
			                </tr>
			                <tr>
			                    <td style="border-right:1px solid #020202;border-bottom:1px solid #020202; width: 220px;">${message("shop.orderDetail.area2")}</td>
			                    <td style="border-bottom:1px solid #020202">&nbsp;&nbsp;${message("shop.orderDetail.area21")}</td>
			                </tr>
			                <tr>
			                    <td style="border-right:1px solid #020202; width: 220px;">${message("shop.orderDetail.area3")}</td>
			                    <td>&nbsp;&nbsp;${message("shop.orderDetail.area31")}</td>
			                </tr>
			            </table>
			        </div>
			    </div>
			    <form id="orderForm" action="createOrder.ct" method="post">
			    <input type="hidden" id="receiverId" name="receiverId"[#if defaultReceiver??] value="${defaultReceiver.id}"[/#if] />
	            <input type="hidden" name="paymentPluginId" id="paymentPluginId"/>
	            <input type="hidden" name="bank" id="bankCode"/>
	            <input type="hidden" name="bankName" id="bankName"/>
	            <input type="hidden" name="cartToken" value="${cartToken}" />
	            
				<div class="receiver clearfix">
					<div class="title">${message("收货信息")}</div>
					<div class="receiverInfo">
						<table  cellspacing="0" rowspacing="0" border="0">
						    <tr>
						        <td>
						            <lable for="receiverConsignee">
						               收件人：
						            </lable>
						            <span id="receiverConsignee">
						                [#if defaultReceiver??] ${defaultReceiver.consignee}[/#if]
						            </span>
						        </td>
						    </tr>
						    <tr>
						        <td>
							        <lable for="receiverPhone">
						              电话：
						            </lable>
						            <span id="receiverPhone">
						                [#if defaultReceiver??] ${defaultReceiver.phone}[/#if]
						            </span>
					            </td>
						    </tr>
						    <tr>
						        <td>
						            收货地址：
						        </td>
						    </tr>
						</table>
					</div>
					<ul id="receiverList">
					    [#list member.receivers as receiver]
					        <input type="hidden" id="hiddenReceiver_consignee_${receiver.id}" value="${receiver.consignee}" />
					        <input type="hidden" id="hiddenReceiver_area_${receiver.id}" value="${receiver.area.id}" />
						    <input type="hidden" id="hiddenReceiver_areaTreePath_${receiver.id}" value="${(receiver.area.treePath)!}" />
						    <input type="hidden" id="hiddenReceiver_isDefault_${receiver.id}" value="${(receiver.isDefault)!}" />
						    <input type="hidden" id="hiddenReceiver_address_${receiver.id}" value="${(receiver.address)!}" />
						    <input type="hidden" id="hiddenReceiver_zipCode_${receiver.id}" value="${(receiver.zipCode)!}" />
						    <input type="hidden" id="hiddenReceiver_phone_${receiver.id}" value="${(receiver.phone)!}" />
						    <li receiverId="${receiver.id}" receiverConsignee="${receiver.consignee}" receiverPhone="${receiver.phone}" >
								<label [#if receiver.isDefault || receiver_index == 0]class="checked"[#else]class="unchecked"[/#if] name="selectdeliveryAddress" for="selectdeliveryAddress">
								    ${receiver.areaName}${receiver.address}
								</label>
								<span>
								    收件人：${receiver.consignee}
								</span>
								<span>
								    邮编：${receiver.zipCode}
								</span>
								<a href="javascript:;" class="acctButton" onclick="modifyDeliveryAddress('${receiver.id}')">
		                           ${message("shop.personalcenter.deliveryAddressModify")}
		                        </a>
		                        <a href="javascript:;" class="acctButton" onclick="deleteReceiver('${receiver.id}')">
		                           ${message("shop.personalcenter.deliveryAddressDelete")}
		                        </a>
						    </li>
						[/#list]
					</ul>
					<label class="unchecked" onclick="addNewDeliveryAddress(false)">
					    使用新地址
					</label>
				</div>
				
				<div id="paymentMethod" class="paymentMethod clearfix">
					<div class="title">${message("shop.order.paymentMethod")}</div>
					<div class="paymentMethodInfo" id="alipayFormControl">
				        <dl class="paymentMethodSelect">
				            <dt>支付宝</dt>
				            <dd>
				                 <ul class="paymentGroupWrapper">
									 [#if paymentPlugin?has_content]
										<li class="formControl ">
											<img src="${paymentPlugin.logo}" bank="" bankName="" 
											alt="${paymentPlugin.paymentName}" id="${paymentPlugin.id}Image" title="${paymentPlugin.paymentName}" 
											onclick="paymentModeChoice('${paymentPlugin.id}')"
											plugin="${paymentPlugin.id}"
											 style="cursor: pointer;"/>
											<span>${paymentPlugin.paymentName}</span>
										</li>
									 [/#if]
									</ul>
				            </dd>
				            <dt>网银支付</dt>
				            <dd>
				                 <ul class="paymentGroupWrapper cyberBank">
									  [#list payBankInfos as payBankInfo]
											<li class="formControl">
												<img src="${payBankInfo.bankImage}" bank="${payBankInfo.bankCode}" bankName="${payBankInfo.bankName}" alt="${payBankInfo.bankName}" 
												plugin="${payBankInfo.paymentPluginId}" id="${payBankInfo.paymentMethod}${payBankInfo.bankCode}Image" title="${payBankInfo.bankName}" onclick="paymentModeChoice('${payBankInfo.paymentMethod}${payBankInfo.bankCode}')" style="cursor: pointer;"/>
												<span>${payBankInfo.bankName}</span>
											</li>
									 [/#list]
								</ul>
				            </dd>
				        </dl>
				   </div>
				   
				   <dl id="shippingMethod" style="width: 100%;display:none">
					    <dt>${message("shop.order.shippingMethod")}</dt>
					    [#list shippingMethods as shippingMethod]
							<dd>
								<label for="shippingMethod_${shippingMethod.id}">
									<input class="shippingMethod" type="radio" id="shippingMethod_${shippingMethod.id}" [#if shippingMethod_index == 1]checked="checked"[/#if] name="shippingMethodId" value="${shippingMethod.id}" />
									<span>
										<strong>${shippingMethod.name}</strong>
									</span>
									<span>${abbreviate(shippingMethod.description, 80, "...")}</span>
								</label>
							</dd>
					    [/#list]
				    </dl>
				   
				   <div class="buyRules">
		                 <div class="radio-group buyRulesRadioGroup" id="buyRulesCheckboxGroup">
							<div class="radio-group-panel">
								<input type="checkbox" name="checkbox4buyRules" id="checkbox4buyRules" class="confirmCheckBox"/>
								<label class="unconfirm" name="checkbox4buyRules" for="checkbox4buyRules" style="font-size:12px">
		                           ${message("shop.orderDetail.accept")} 
		                        </label>
		                         <a href="${base}/info/buyingguide.ct?id=56&param=clause" target="_blank" style="text-decoration:underline;font-size: 12px;"> ${message("shop.orderDetail.accept1")}</a>
							</div>
						</div>
		           </div>
			       <ul class="chosenBank" style="display:none" id="paymentSelectedTips">
						<li>
						    ${message("shop.orderDetail.selectedPaymentTypeWrapper")}&nbsp;<strong id="selectedPaymentType"></strong>
						</li>
				   </ul>
			   </div>
			   
			   [#if setting.isInvoiceEnabled]
				   <div id="invoiceInfo" class="invoiceInfo">
				        <div class="title">${message("shop.order.invoiceInfo")}</div>
				        <div class="invoiceInfoContent">
				             <div id="invoiceTitleTr">
				                 <div class="radio-group" id="invoiceTitleCheckboxGroup">
									<div class="radio-group-panel">
										<input type="checkbox" id="isInvoice" name="isInvoice" class="confirmCheckBox"/>
										<label class="unconfirm" for="isInvoice" id="checkbox4InvoiceTitleTrLabel">
				                            ${message("shop.orderDetail.invoicing")}[#if setting.isTaxPriceEnabled](${message("shop.order.invoiceTax")}: ${setting.taxRate * 100}%)[/#if]
				                        </label>
									</div>
								</div>
								<ul id="invoiceTypeAndTitle" style="display: none;padding-top: 8px;">
								    <li>
								         <lable for="invoiceType">
								            ${message("shop.orderDetail.invoiceType")}：
								         </lable>
								         <select id="invoiceType" class="defaultSelect" name="invoiceTypeId" style="width: 204px;">
						                    [#list invoiceTypes as invoiceType]
							                    <option value="${invoiceType.id}">${invoiceType.invoiceTypeName}</option>
						                    [/#list]
						                </select>
								    </li>
								    <li>
								         <lable for="invoiceTitle">
								             ${message("shop.orderDetail.invoiceTitle")}：
								         </lable>
								         <input type="text" id="invoiceTitle" class="invoiceTitle" name="invoiceTitle" class="text"  maxlength="200"/>
								    </li>
								</ul>
				             </div>
				        </div>
				    </div>
			   [/#if]
				<div id="productList" class="productList">
				    <div class="title">${message("shop.orderDetail.purchasedGoods")}</div>
				    <table class="product" border="1"  cellpadding="0" cellspacing="0">
	                        <thead>
	                            <tr class="orderDetailThead">
		                            <td colspan="2">
		                                ${message("shop.orderDetail.orderItem")}
		                            </td>
		                            <td>
		                                ${message("shop.orderDetail.orderColor")}
		                            </td>
		                            <td>
		                                ${message("shop.orderDetail.orderSize")}
		                            </td>
		                            <td>
		                                ${message("shop.orderDetail.orderUnitPrice")}
		                            </td>
		                            <td>
		                                ${message("shop.orderDetail.orderNum")}
		                            </td>
		                           
		                            <td>
		                                ${message("shop.orderDetail.orderTotalNum")}
		                            </td>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            [#if order??] 
		                            [#list order.orderItems as orderItem]
		                            <tr class="orderDetailTable">
		                                <td>
		                                    <input type="hidden" name="shippingItems[${orderItem_index}].sn" value="${orderItem.sn}" />
		                                    <a href="${base}${orderItem.product.path}?brandId=${orderItem.product.brand.id}&productCategoryId=${orderItem.product.productCategory.id}&productId=${orderItem.product.id}" target="_blank">
		                                        <img src="[#if orderItem.product.thumbnail??]${orderItem.product.coverPluImage}@50w_100Q_1x.jpg[#else]${base}/resources/shop/images/noimage_thumb.png[/#if]" height="80" />
		                                    </a>
		                                </td>
		                                <td>
		                                    <ul>
		                                        <li>
		                                           <span id="goodsName">
			                                           [#if orderItem.isGift]
			                                             [${message("shop.cart.gift")}]
			                                          [/#if]
		                                             <a href="${base}${orderItem.product.path}?brandId=${orderItem.product.brand.id}&productCategoryId=${orderItem.product.productCategory.id}&productId=${orderItem.product.id}" target="_blank">
		                                              ${orderItem.product.name}
		                                             </a>
		                                           </span>
		                                        </li>
		                                        <li>
			                                        <nobr>
			                                           ${message("shop.orderDetail.orderItemNumber")} &nbsp; ${orderItem.product.sn}
			                                        </nobr>
		                                        </li>
		                                    </ul>
		                                </td>
		                                <td>
		                                    ${orderItem.product.colorVal}
		                                </td>
		                                <td>
		                                    ${orderItem.product.sizeVal}
		                                </td>
		                                <td>
		                                    ${currency(orderItem.price, true)}
		                                </td>
		                                <td>
		                                    ${orderItem.quantity}
		                                </td>
		                                <td>
		                                    ${currency(orderItem.subtotal, true)}
		                                </td>
		                            </tr>
		                            [/#list] 
	                            [/#if]
	                        </tbody>
	                    </table>
				</div>
					<div class="span12">
						<dl class="memo">
							<dt>${message("shop.orderDetail.mark")}：</dt>
							<dd>
								<input type="text" name="markinfo" maxlength="200" />
							</dd>
						</dl>
						<dl class="coupon">
							<dt>${message("shop.order.coupon")}：</dt>
							<dd>
								<input type="hidden" id="code" name="code" maxlength="200" />
								<input type="text" id="couponCode" maxlength="200" />
								<span id="couponName">&nbsp;</span>
								<!-- ${message("shop.order.codeConfirm")} -->
								<button type="button" id="couponButton">${message("shop.order.codeConfirm")}</button>
							</dd>
						</dl>
					</div>
					<div class="span12 last">
					    <table class="statistic"  cellpadding="0" cellspacing="0" >
					        <tr>
					            <td>
					               ${order.orderItemQuantity}${message("shop.orderDetail.shoppingBasket1")}， ${message("shop.orderDetail.shippedAmount")} 
					            </td>
					            <td>
					                <em id="sellingPrice">${currency(order.sellingPrice, true)}</em>
					            </td>
					        </tr>
					        <tr>
					             <td>
					               ${message("shop.order.freight")}：
					            </td>
					            <td>
					               <em id="freight">${currency(order.freight, true)}</em>
					            </td>
					        </tr>
					        [#if setting.isInvoiceEnabled && setting.isTaxPriceEnabled]
						        <tr style="display: none;">
						             <td>
						              ${message("shop.order.tax")}：
						            </td>
						            <td>
						                <em id="tax">${currency(order.tax, true)}</em>
						            </td>
						        </tr>
					        [/#if]
					        <tr>
					            <td>
					               ${message("shop.order.point")}：
					            </td>
					            <td>
					               <em id="point">${order.point}</em>
					            </td>
					        </tr>
					        <tr [#if order.promotionDiscount == 0] style="display: none;" [/#if]>
					             <td>
					              ${message("shop.order.promotionDiscount")}：
					            </td>
					            <td>
					                <em id="promotionDiscount">${currency(order.promotionDiscount, true)}</em>
					            </td>
					        </tr>
					        <tr [#if order.couponDiscount == 0] style="display: none;" [/#if]>
					            <td>
					                ${message("shop.order.couponDiscount")}：
					            </td>
					            <td>
					               <em id="couponDiscount">${currency(order.couponDiscount, true)}</em>
					            </td>
					        </tr>
					        <tr>
					            <td>
					                <strong>${message("shop.order.amountPayable")}：</strong>
					            </td>
					            <td>
					                 <strong id="amountPayable">${currency(order.amountPayable, true, false)}</strong>
					            </td>
					        </tr>
					        [#if member.balance > 0]
						        <tr>
						            <td colspan="2"> 
						                <input type="checkbox" id="useBalance" name="useBalance" value="true" />
						                <label for="useBalance">
										${message("shop.order.useBalance")} (${message("shop.order.balance")}: <em>${currency(member.balance, true)}</em>)
									    </label>
						            </td>
						        </tr>
					        [/#if]
					    </table>
					</div>
					<div class="span24">
						<div class="bottom">
							<a href="javascript:;" id="submit" class="submit">${message("shop.order.submit")}</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- start 修改收货地址 -->
	<div id="modifyOrderAddressPanel" class="modal" style="zoom: 1; opacity: 1;z-index: 1002; display: none">
		<div class="modalContent">
		    <div class="modalTitle">
		         <h2 id="pop_title_receiver"></h2>
	        </div>
	        <form id="receiverFormModify" action="${base}/member/order/update_receiver.ct" method="post" novalidate="novalidate">
	        <table id="newReceiver" style="line-height: 30px;">
	                <input type="hidden" id="modify_id" name="modify_id"/>
					<tbody><tr>
						<td width="100">
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptPerson")}
						</td>
						<td>
							<input type="text" id="modify_consignee" name="consignee" class="inputText" maxlength="200"/>
						</td>
					</tr>
					<tr>
						<td>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptArea")}
						</td>
						<td style="width: 183px;">
							<span class="fieldSet" id="receiverModifyAreaSpan">
								<input type="hidden" id="modify_orderAreaId" name="modify_orderAreaId" treePath=""/>
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptAdress")}
						</td>
						<td>
							<input type="text" id="modify_address" name="address" class="inputText" maxlength="500"/>
						</td>
					</tr>
					<tr>
						<td>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptZipCode")}
						</td>
						<td>
							<input type="text" id="modify_zipCode" name="zipCode" class="inputText" maxlength="6"/>
						</td>
					</tr>
					<tr>
						<td>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptphoneAndMobile")}
						</td>
						<td>
							<input type="text" id="modify_phone" name="phone" class="inputText" maxlength="11"/>
						</td>
					</tr>
					<tr>
						<td>
							<span class="requiredField"></span>${message("shop.personalcenter.deliveryAddressIsDefault")}
						</td>
						<td>
						    <input type="hidden" name="isDefault" id="isDefault_Modify" value="false"/>
							<div class="radio-group-panel isDefaultYes" style="display: inline;float: initial;">
								<input name="isDefaultInput" type="radio" class="signin-radio" id="isDefault_radio">
								<label id="isDefaultYes_lable" class="unchecked" name="isDefault_radio" for="isDefault_radio">${message("shop.personalcenter.deliveryAddressIsDefault.yes")}</label>
							</div>
							<div class="radio-group-panel isDefaultNo" style="display: inline;float: initial;">
								<input name="isDefaultInput" type="radio" class="signin-radio" id="isDefault_radio">
								<label id="isDefaultNo_lable" class="unchecked" name="isDefault_radio" for="isDefault_radio">${message("shop.personalcenter.deliveryAddressIsDefault.no")}</label>
						    </div>
						</td>
					</tr>
					<tr>
						<th>
						</th>
						<td style="text-align: right;">
							<div class="requiredLegend">
				                <em class="required">${message("shop.info.acceptClauseMust")}</em>
			                </div>
						</td>
					</tr>
				</tbody>
				</table>
				<div class="actionsControls" style="text-align:center;margin:20px 0 28px">
                    <button type="submit" id="modifyReceiverSubmit" class="button butBlack">
                        <span>
                            ${message("shop.orderDetail.modifyReceiverSubmit")}
                        </span>
                    </button>
                     <button type="button" id="modifyReceiverCancle" class="button butBlack">
                        <span>
                            ${message("shop.orderDetail.newReceiverCancle")}
                        </span>
                    </button>
                </div>
               </form>
		</div>
		<!-- end 修改收货地址 -->
	[#include "/shop/include/footer.ftl" /]
</body>
</html>