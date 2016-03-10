<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("shop.returnGoods.returnGoodsSubmit")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台 官方网站[/#if]</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        <link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
        <script type="text/javascript">
            $().ready(function() {
                var $returnsForm = $("#returnsForm");
                var $returnGoodsInfoSubmit = $("#returnGoodsInfoSubmit");
                var $returnsItemsQuantity1 = $(".returnsItemsQuantity1");
                var $returnGoodsInfoSubmit = $("#returnGoodsInfoSubmit");
                
				$.validator.addClassRules({
					returnsItemsQuantity1: {
						required: true,
						digits: true
					}
				});
				
				$returnsForm.validate({
					rules: {
						refundReason:"required"
					}
				});
				
				$returnGoodsInfoSubmit.click(function(){  
		            if(validateReturns()){  
		                $returnsForm.submit();  
		            }  
		        });
		        
		        $returnsItemsQuantity1.change(function(){
		            var shippingReqQtyName = $(this)[0].name;
		            var shippingReqQty = document.getElementById(shippingReqQtyName);
		            shippingReqQty.value = $(this)[0].value;
				});
            });
            
			function validateReturns(){
				var $returnsItemsQuantity1 = $(".returnsItemsQuantity1");
				var len = $returnsItemsQuantity1.length;
				var count = 0;
				for(i=0; i<len; i++){
				    var qty = $returnsItemsQuantity1[i].value;
				    if(qty == 0){
				        count += 1;
				    }
				}
				if(len == count){
				   $.message("warn","${message("shop.returnGoods.returnsQtyLessThan")}");
				   return false;
				}else{
				   return true;
				}
			}
        </script>
    </head>
    <body>
        [#include "/shop/include/header.ftl" /]
         <div class="rightWrapper">
	        <div class="container" style="width:65%;">
	            <section id="main" class="content-main body">
	                <div class="head">
	                </div>
	                <div class="body">
	                    <form action="${base}/member/returnGoods/returns.ct" method="post" onsubmit="validateReturns()" id="returnsForm" name="returnsForm">
	                        <input type="hidden" value="${orderId}" name="orderId"/>
	                        <div class="line mod">
	                            <div class="unit size1of1">
	                                 <h2 class="h2">
	                                    ${message("shop.returnGoods.chooseReturnGoodsTitle")}
	                                 </h2>
	                                <table cellpadding="0" cellspacing="0" class="table shopBag">
	                                    <thead id="tableHead">
	                                        <tr>
	                                            <th>
	                                                ${message("Shipping.order")}
	                                            </th>
	                                            <th>
	                                                ${message("Shipping.deliveryCorp")}
	                                            </th>
	                                            <th>
	                                                ${message("Shipping.trackingNo")}
	                                            </th>
	                                            <th>
	                                                ${message("Shipping.shopStore")}
	                                            </th>
	                                            <th>
	                                                <nobr>
	                                                   ${message("Order.returnQuantity")}
	                                                </nobr>
	                                            </th>
	                                            <th>
	                                              <nobr>
	                                                   ${message("shop.returnGoods.returnGoodsNum")}
	                                               </nobr>
	                                            </th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                     [#list shippings as shipping]
	                                        <tr>
	                                            <input type="hidden" name="returnss[${shipping_index}].shopStore.id" value="${(shipping.shopStore.id)!0}"/>
	                                            <input type="hidden" name="shippings[${shipping_index}].id]" value="${shipping.id}"/>
	                                            <td>
	                                              ${shipping.order.sn}
	                                            </td>
	                                            <td>
	                                              ${shipping.deliveryCorp}
	                                            </td>
	                                            <td class="color">
	                                               ${shipping.trackingNo}
	                                            </td>
	                                            <td>
	                                               ${shipping.shopStore.address}
	                                            </td>
	                                            <td>
	                                               &nbsp;
	                                            </td>
	                                            <td>
	                                               &nbsp;
	                                            </td>
	                                        </tr>
		                                        [#list shipping.shippingItems as shippingItem]
			                                        <tr>
			                                            <input type="hidden" name="returnss[${shipping_index}].returnsItems[${shippingItem_index}].shippingItemId" value="${shippingItem.id}"/>
			                                            <input type="hidden" name="returnss[${shipping_index}].returnsItems[${shippingItem_index}].sn" value="${shippingItem.sn}"/>
			                                            <input type="hidden" name="returnss[${shipping_index}].returnsItems[${shippingItem_index}].name" value="${shippingItem.name}"/>
			                                            <input type="hidden" name="shippings[${shipping_index}].shippingItems[${shippingItem_index}].id" value="${shippingItem.id}"/>
			                                            <td style="visibility: hidden;">
			                                              &nbsp;
			                                            </td>
			                                            <td>
			                                              ${shippingItem.sn}
			                                            </td>
			                                            <td>
			                                               ${shippingItem.name}
			                                            </td>
			                                            <td class="color">
			                                               ${shippingItem.quantity}(${message("shop.order.quantity")})
			                                            </td>
			                                            <td>
			                                               ${shippingItem.returnQuantity}
			                                            </td>
			                                            <td>
			                                                <input type="text" name="returnss[${shipping_index}].returnsItems[${shippingItem_index}].quantity" class="inputText inputW34 text  returnsItemsQuantity1" value="0" maxlength="9" style="width: 30px;"[#if shippingItem.quantity - shippingItem.returnQuantity <= 0 ] disabled="disabled"[#else] max="${shippingItem.quantity - (shippingItem.returnQuantity)!0}"[/#if]  autocomplete="off"/>
			                                                
			                                                <input type="hidden" name="shippings[${shipping_index}].shippingItems[${shippingItem_index}].returnQuantity" value="0" id="returnss[${shipping_index}].returnsItems[${shippingItem_index}].quantity"/>
			                                            </td>
			                                        </tr>
		                                        [/#list]
	                                       [/#list]
	                                    </tbody>
	                                </table>
	                                <div style="margin-top: 30px;"> </div>
	                               <table  cellpadding="0" cellspacing="0" width="80%" class="table shopBag" style="[#if !canReturn]display: none;[/#if]" >
	                                <tbody>
										<tr>
											<td>
												${message("Order.sn")}:
											</td>
											<td width="300" class="aleft">
												${order.sn}
											</td>
											<td>
												${message("console.common.createDate")}:
											</td>
											<td class="aleft">
												${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
										<tr>
											<td>
												${message("Returns.refundReason")}:
											</td>
											<td class="aleft">
												<select class="defaultSelect" name="refundReason">
													<option value="">${message("console.common.choose")}</option>
													[#list refuseReasons as refuseReason]
														<option value="${refuseReason.refuseReason}">${refuseReason.refuseReason}</option>
													[/#list]
												</select>
											</td>
											<td>
												${message("Returns.memo")}:
											</td>
											<td class="aleft" colspan="3">
												<input type="text" name="memo" class="inputText" maxlength="500" />
											</td>
										</tr>
									 </tbody>
								</table>
	                            </div>
	                        </div>
	                        <div class="line mod gaView">
	                            <div class="unit size1of1">
	                                <div class="actionsControls fLeft">
	                                    <a id="continueShoppingBt" type="button" class="button butWhite"  href="${base}/member/personalCenter/personalCenter.ct">
	                                        <span>
	                                           ${message("shop.returnGoods.returnPersonalCenter")}
	                                        </span>
	                                    </a>
	                                </div>
	                                <div class="actionsControls fRight" style="[#if !canReturn]display: none;[/#if]" >
	                                    <button type="button" class="button butBlack" id="returnGoodsInfoSubmit">
	                                        <span>
	                                            ${message("shop.returnGoods.returnGoodsSubmit")}
	                                        </span>
	                                    </button>
	                                </div>
	                            </div>
	                        </div>
	                    </form>
	                    <div class="line">
	                    </div>
	                </div>
	            </section>
	        </div>
	    </div>
        [#include "/shop/include/footer.ftl" /]
    </body>

</html>