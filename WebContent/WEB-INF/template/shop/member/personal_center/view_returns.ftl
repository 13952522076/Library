<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("shop.returnGoods.view")}[#if systemShowPowered] -图书智能管理推荐系统 官方网站[/#if]</title>
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
                var $returnAreaId = $("#returnAreaId");
                var $updateLogisticsForm = $("#updateLogisticsForm");
                var $updateLogisticsSubmit = $("#updateLogisticsSubmit");
                
	            // 地区选择
				$returnAreaId.lSelect({
					url: "${base}/common/area.ct"
				});
				
				$.validator.addClassRules({
					returnsItemsQuantity1: {
						required: true,
						digits: true
					}
				});
				
				$updateLogisticsForm.validate({
					rules: {
						freight: {
							min: 0,
							decimal: {
								integer: 12,
								fraction: ${setting.priceScale}
							}
						},
						shippingMethodId: "required",
						deliveryCorpId: "required",
						trackingNo: "required",
						phone: "required",
						refundReason:"required"
					}
				});
				
				
            });
        </script>
    </head>
    <body>
        [#include "/shop/include/header.ftl" /]
         <div class="rightWrapper">
	        <div class="container">
	            <section id="main" class="content-main body">
	                <div class="head">
	                </div>
	                <div class="body">
	                        <input type="hidden" name="id" id="id" value="${returns.id}">
	                        <input type="hidden" name="orderId" id="orderId" value="${returns.order.id}">
	                        <div class="line mod">
	                            <div class="unit size1of1">
	                                <div style="margin-top: 30px;"> </div>
	                                
	                                <table cellpadding="0" cellspacing="0" class="table shopBag">
	                                    <thead id="tableHead">
	                                        <tr>
	                                            <th>
													${message("ReturnsItem.sn")}
												</th>
												<th>
													${message("ReturnsItem.name")}
												</th>
												<th>
													${message("ReturnsItem.quantity")}
												</th>
												<th>
													${message("Returns.returnsAppliedAmount")}:
												</th>
												<th>
													${message("Returns.returnsAmount")}:
												</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    [#list returns.returnsItems as returnsItem]
	                                        <tr>
	                                            <td>
													${returnsItem.sn}
												</td>
												<td>
													<span title="${returnsItem.name}">${abbreviate(returnsItem.name, 50, "...")}</span>
												</td>
												<td>
													${returnsItem.quantity}
												</td>
												<td>
												    ${currency(returnsItem.returnsAppliedAmount, true)!"-"}
												</td>
												<td>
												   ${currency(returnsItem.returnsAmount, true)!"-"}
												</td>
	                                        </tr>
	                                        [/#list]
	                                    </tbody>
	                                </table>
	                               <table  cellpadding="0" cellspacing="0" width="80%" class="table shopBag">
	                                <tbody>
										<tr>
											<td>
												${message("Order.sn")}:
											</td>
											<td width="300" class="aleft">
												${returns.order.sn}
											</td>
											<td>
												${message("console.common.createDate")}:
											</td>
											<td class="aleft">
												${returns.order.createDate?string("yyyy-MM-dd HH:mm:ss")}
											</td>
										</tr>
										<tr >
											<td class="hidden">
												${message("Returns.shippingMethod")}:
											</td>
											<td class="aleft hidden" >
												${(returns.shippingMethod)!"-"}
											</td>
											<td>
												${message("Returns.deliveryCorp")}:
											</td>
											<td class="aleft">
												${(returns.deliveryCorp)!"-"}
											</td>
											<td>
												${message("Returns.trackingNo")}:
											</td>
											<td class="aleft">
											    ${(returns.trackingNo)!"-"}
											</td>
										</tr>
										<tr>
											<td>
												${message("Returns.freight")}:
											</td>
											<td class="aleft">
											     ${(returns.freight)!"-"}
											</td>
											<td>
												${message("Returns.refundReason")}:
											</td>
											<td class="aleft">
											    ${(returns.refundReason)!"-"}
											</td>
										</tr>
										<tr class="hidden">
										   <td>
												${message("Returns.zipCode")}:
											</td>
											<td class="aleft">
											      ${(returns.zipCode)!"-"}
											</td>
											<td>
												${message("Returns.shipper")}:
											</td>
											<td class="aleft">
												${(returns.shipper)!"-"}
											</td>
										</tr>
										<tr class="hidden">
											<td>
												${message("Returns.area")}:
											</td>
											<td colspan="3" class="aleft">
											    ${(returns.order.area.treePath)!"-"}
											</td>
										</tr>
										<tr class="hidden">
											<td>
												${message("Returns.address")}:
											</td>
											<td class="aleft">
											   <span>
												<input type="text" name="address" class="inputText" value="${returns.order.address}" maxlength="200" />
											   </span>	
											</td>
											<td>
												${message("Returns.phone")}:
											</td>
											<td class="aleft">
												<input type="text" name="phone" class="inputText" value="${returns.order.phone}" maxlength="200" />
											</td>
										</tr>
										<tr >
											<td>
												${message("Returns.memo")}:
											</td>
											<td class="aleft" colspan="3">
												${(returns.memo)!"-"}
											</td>
										</tr>
									 </tbody>
									 [#if returns.hasGoodReturn == 'true']
									 <tfoot>
									         <tr class="padT20">
	                                            <th colspan="4" style="text-align: center;padding: 10px;">
	                                               ${message("shop.returnGoods.returnsAddressTips")} 
	                                            </th>
		                                     </tr>
									  [#if returns.shopStore??]
	                                        <tr class="padT20">
	                                            <th colspan="2">
	                                               ${message("shop.returnGoods.reutrnAddress")}：
	                                            </th>
	                                            <td colspan="6">
	                                               ${returns.shopStore.address}
	                                            </td>
	                                        </tr>
	                                        <tr class="padT20">
	                                            <th colspan="2">
	                                               ${message("shop.returnGoods.zipCode")}：
	                                            </th>
	                                            <td colspan="6">
	                                               ${returns.shopStore.zipcode}
	                                            </td>
	                                        </tr>
	                                         <tr class="padT20">
	                                            <th colspan="2">
	                                               ${message("shop.returnGoods.name")}：
	                                            </th>
	                                            <td colspan="6">
	                                               ${returns.shopStore.consignor}
	                                            </td>
	                                        </tr>
	                                        <tr class="padT20">
	                                            <th colspan="2">
	                                             ${message("shop.returnGoods.phone")}：
	                                            </th>
	                                            <td colspan="6">
	                                               ${orderConsignor.telephone}
	                                            </td>
	                                        </tr>
	                                        [#else]
	                                            <tr class="padT20">
		                                            <th colspan="4" style="text-align: center;padding: 10px;">
		                                               ${message("shop.contact.customer.service")} 
		                                            </th>
	                                            </tr>
	                                       [/#if]
	                                    </tfoot>
	                                [/#if]
								</table>
	                            </div>
	                        </div>
	                        <div class="line mod gaView">
	                            <div class="unit size1of1">
	                                <div class="actionsControls fLeft">
	                                    <a id="continueShoppingBt" type="button" class="button butWhite"  href="returnGoodsHistory.ct?orderId=${returns.order.sn}">
	                                        <span>
	                                           ${message("shop.returnGoods.previous")}
	                                        </span>
	                                    </a>
	                                </div>
	                            </div>
	                        </div>
	                    <div class="line">
	                    </div>
	                </div>
	            </section>
	        </div>
	    </div>
        [#include "/shop/include/footer.ftl" /]
    </body>
</html>