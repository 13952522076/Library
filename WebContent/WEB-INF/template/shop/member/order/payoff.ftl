<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.order.payment")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台[/#if]</title>
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/dealorder4pay.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $paymentForm = $("#paymentForm");
	var $paymentSubmit = $("#paymentSubmit");
	var $paymentPluginChange = $("#paymentPluginChange");
	var $paymentPlugin = $("#paymentPlugin");
	var $orderDetailTheadTd = $(".orderDetailThead td");
    $orderDetailTheadTd.last().css("border-right","1px #020202 solid");
	
	[#if order.paymentMethod.method == "online" && (order.paymentStatus == "unpaid" || order.paymentStatus == "partialPayment")]
		// 订单锁定
		setInterval(function() {
			$.ajax({
				url: "lock.ct",
				type: "POST",
				data: {sn: "${order.sn}"},
				dataType: "json",
				cache: false,
				success: function(data) {
					if (!data) {
						//location.href = "view.ct?sn=${order.sn}";
					}
				}
			});
		}, 10000);

		// 检查支付
		setInterval(function() {
			$.ajax({
				url: "check_payment.ct",
				type: "POST",
				data: {sn: "${order.sn}"},
				dataType: "json",
				cache: false,
				success: function(data) {
					if (data) {
						//location.href = "view.ct?sn=${order.sn}";
					}
				}
			});
		}, 10000);
	[/#if]
	
	// 支付
	$paymentSubmit.click(function(){
		$paymentForm.attr('action','${base}/payment/submit.ct');
	    $paymentForm.submit();
	});
	
	$paymentPluginChange.click(function(){
	    $paymentPlugin.fadeIn('normal');
	    var $bankCode = $('#bankCode');
	    var $bankName = $('#bankName');
	    if($bankCode.val() && $bankName.val()){
	        var $currentBankId = 'bankPay' + $bankCode.val() + 'Image';
	        var $currentBankImage = $('#'+$currentBankId);
	        var $bankNameObject = $currentBankImage.next();
	        $bankNameObject.css({visibility:"visible"});
	        $currentBankImage.addClass("paymentImageSection");
	    }else{
	        var $currentBankImage = $('#alipayDirectPluginImage');
	        var $bankNameObject = $currentBankImage.next();
	        $bankNameObject.css({visibility:"visible"});
	        $currentBankImage.addClass("paymentImageSection");
	    }
	});

});

</script>
</head>
<body>
	 [#include "/shop/include/header.ftl" /]
	<div class="orderContainer">
		<div class="orderPaymentComplete">
	      <div class="mytitle">
	        <span>${message("shop.orderDetail.orderComplete")}</span>
	      </div>
	      <div class="paymentTip"> 
	          <span>
	          ${message("shop.orderDetail.orderCompleteTips",timeoutStr)}
	          </span>
		  </div>
		  <div class="actionsControls fRight" style="margin-top: 30px;margin-bottom: 10px;">
		       <button type="button" id="paymentPluginChange" class="button butWhite">
		            <span>
		              ${message("shop.orderDetail.changePayMethod")}
		            </span>
		        </button>
		        <button type="button" id="paymentSubmit" class="button butBlack">
		            <span>
		               ${message("shop.finishorder.payment")}
		            </span>
		        </button>
		  </div>
		  <div class="methodPaymentFinish" id="paymentPlugin">
		        <h3 id="paymentSectionTitle" class="titleSection">${message("shop.order.paymentMethod")}</h3>
				<div class="paymentpanel">
						<div id="alipayFormControl" class="modpay">
							 <div class="paymentGroupWrapper">
								<p class="paymentGroup _paymentGroupType_2">支付宝</p>
								<ul class="listH listCards2 cnBanks">
								    [#if paymentPlugin?has_content]
										<li class="formControl ">
											<img src="${paymentPlugin.logo}" bank="" bankName="" alt="${paymentPlugin.paymentName}" id="${paymentPlugin.id}Image" title="${paymentPlugin.paymentName}" onclick="paymentModeChoice('${paymentPlugin.id}')"
											plugin="${paymentPlugin.id}"
											 style="cursor: pointer;"/>
											<span>${paymentPlugin.paymentName}</span>
										</li>
									 [/#if]
								</ul>
							</div>
							<div class="paymentGroupWrapper">
								<p class="paymentGroup _paymentGroupType_2">网银支付</p>
								<ul class="listH listCards2 cnBanks">
								    [#list payBankInfos as payBankInfo]
										<li class="formControl ">
											<img src="${payBankInfo.bankImage}" bank="${payBankInfo.bankCode}" bankName="${payBankInfo.bankName}" alt="${payBankInfo.bankName}" 
											plugin="${payBankInfo.paymentPluginId}" id="${payBankInfo.paymentMethod}${payBankInfo.bankCode}Image" title="${payBankInfo.bankName}" onclick="paymentModeChoice('${payBankInfo.paymentMethod}${payBankInfo.bankCode}')" style="cursor: pointer;"/>
											<span>${payBankInfo.bankName}</span>
										</li>
									[/#list]
								</ul>
							</div>
						
							<ul class="conditions" style="display:none" id="paymentSelectedTips">
								<li class="formControl">
								    ${message("shop.orderDetail.selectedPaymentTypeWrapper")}&nbsp;<strong id="selectedPaymentType"></strong>
								</li>
							</ul>
			        </div>
		        </div>
		  </div>
		  
		  <form id="paymentForm" method="post">
		      <div class="orderPriview">
			      <input type="hidden" name="type" value="payment" />
				  <input type="hidden" name="sn" value="${order.sn}" />
				  <input type="hidden" name="amount" value="${order.amountPayable}" />
				  <input type="hidden" name="paymentPluginId" id="paymentPluginId" value="${order.paymentPluginId}"/>
		          <input type="hidden" name="bank" id="bankCode"  value="${order.bankCode}"/>
		          <input type="hidden" name="bankName" id="bankName"  value="${order.bankName}"/>
			      <div class="previewPayment">
			          <ul class="head">
			             <li>
			                 <lable class="info" >${message("shop.orderDetail.orderPriviewNum")}</lable>
		                     <span class="info">${order.sn}</span>
		                  </li>
			          </ul>
			          <ul class="body">
			             <li>
			                  <label>${message("shop.orderDetail.orderPriviewDate")}</label> 
		                      <span>${order.createDate}</span>
		                 </li>
		                 <li>
			                  <label>${message("shop.orderDetail.doorshippingMethod")}</label> 
		                      <span>${message("shop.orderDetail.doorDelivery")}</span>
		                 </li>
		                 <li>
			                  <label>${message("shop.orderDetail.paymentMethodName")}</label> 
		                      <span>
		                          ${paymentName}
		                          [#if order.bankName]
		                             (${order.bankName})
		                          [/#if]
		                      </span>
		                 </li>
			          </ul>
			     </div>
			  <div class="previewPayment">
			      <ul class="head">
			             <li>
			                  <span class="info">${message("shop.orderDetail.receiptInfo")}</span>
		                  </li>
			      </ul>
			      <ul class="body">
			             <li>
			                  <label>${message("shop.orderDetail.receiptPeople")}</label> 
		                      <span>${order.consignee}</span>
		                 </li>
		                 <li>
			                  <label>${message("shop.orderDetail.receiptPhone")}</label> 
		                      <span>${order.phone}</span>
		                 </li>
		                 <li>
			                   <label>${message("shop.orderDetail.receiptAdress")}</label> 
		                       <span>${order.areaName}${order.address}</span>
		                 </li>
		                  <li>
			                    <label>${message("shop.orderDetail.receiptZipCode")}</label> 
		                        <span>${order.zipCode}</span>	
		                 </li>
			       </ul>
			  </div>
		      <div id="goodstable">
		          <!--start 订单购买详情 -->
		           <table style="margin-top: 20px;" cellpadding="0" cellspacing="0">
	                        <thead>
	                            <tr height="30px"  class="orderDetailThead">
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
		                            <tr style="height:120px;"  class="orderDetailTable">
		                                <td>
		                                    <input type="hidden" name="shippingItems[${orderItem_index}].sn" value="${orderItem.sn}" />
		                                    <a href="${base}${orderItem.product.path}?brandId=${orderItem.product.brand.id}&productCategoryId=${orderItem.product.productCategory.id}&productId=${orderItem.product.id}" target="_blank">
		                                        <img src="[#if orderItem.product.thumbnail??]${orderItem.product.thumbnail}[#else]${base}/resources/shop/images/noimage_thumb.png[/#if]" height="80" />
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
	                    <table class="statistic" cellpadding="0" cellspacing="0" >
					        <tr>
					            <td>
					               ${order.orderItemQuantity}${message("shop.orderDetail.shoppingBasket1")}， ${message("shop.orderDetail.shippedAmount")} 
					            </td>
					            <td>
					                <em id="sellingPrice">${currency(order.sellingPrice, true)}</em>
					            </td>
					        </tr>
					        <tr [#if order.freight == 0] style="display: none;" [/#if]>
					             <td>
					               ${message("shop.order.freight")}：
					            </td>
					            <td>
					               <em id="freight">${currency(order.freight, true)}</em>
					            </td>
					        </tr>
					        [#if setting.isInvoiceEnabled && setting.isTaxPriceEnabled]
						        <tr [#if order.tax == 0] style="display: none;" [/#if]>
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
					    </table>
	                    <!-- end 订单购买详情 -->
		      </div>
		     </div>
	    </form>
	    </div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>