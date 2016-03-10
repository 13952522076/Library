<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("shop.personalCenter.returnGoodsApply")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台 官方网站[/#if]</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        <link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
        <script type="text/javascript">
            $().ready(function() {

                var $returnGoodsBtn = $(".returnGoods .actionsControls .button");
                var $itxOverlayHeader = $("#itxOverlay-header");
                var $returnGoodsModalForm = $(".returnGoodsModalForm");
                var $returnGoodsClose = $(".returnGoodsClose");

                [@flash_message /] 
                $returnGoodsBtn.live("click",
                function() {
                    $returnGoodsModalForm.show('normal');
                    $itxOverlayHeader.show('normal');
                });

                $returnGoodsClose.live("click",
                function() {
                    $returnGoodsModalForm.hide('normal');
                    $itxOverlayHeader.hide('normal');
                })

            });
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
	                    <div class="line">
	                        <div class="unit size1of1">
	                            <h2 class="h2">
	                                ${message("shop.returnGoods.chooseReturnGoodsTitle1")}
	                            </h2>
	                        </div>
	                    </div>
	                    <form action="#"
	                    method="post" id="formShoppingBag">
	                        <div class="line mod">
	                            <div class="unit size1of1">
	                                <table cellpadding="0" cellspacing="0" class="table shopBag">
	                                    <thead id="tableHead">
	                                        <tr>
	                                            <th>
	                                                ${message("shop.returnGoods.ordrNum")}
	                                            </th>
	                                            <th>
	                                                ${message("shop.returnGoods.goods")}
	                                            </th>
	                                            <th>
	                                                ${message("shop.returnGoods.consignee")}
	                                            </th>
	                                            <th class="aleft">
	                                                ${message("shop.returnGoods.createTime")}
	                                            </th>
	                                            <th class="aright">
	                                                ${message("shop.returnGoods.orderMoney")}
	                                            </th>
	                                            <th class="aright">
	                                               ${message("shop.returnGoods.orderStatus")}
	                                            </th>
	                                            <th class="lastCol">
	                                                ${message("shop.returnGoods.orderOpertion")}
	                                            </th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    [#list retrunOrders as order]
	                                        <tr class="orderItem" >
	                                            <td>
	                                               ${order.sn}
	                                            </td>
	                                            <td class="desc">
	                                                [#list order.orderItems as orderItem]
														<img src="[#if orderItem.thumbnail??]${orderItem.thumbnail}[#else]${setting.defaultThumbnailProductImage}[/#if]" class="thumbnail" alt="${orderItem.fullName}" style="height:60px"/>
														[#if orderItem_index == 2]
															[#break /]
														[/#if]
													[/#list]
	                                            </td>
	                                            <td>
	                                                ${order.consignee}
	                                            </td>
	                                            <td class="size">
	                                                 <span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
	                                            </td>
	                                            <td class="price">
	                                                <span>
	                                                  ${currency(order.amount, true)}
	                                                </span>
	                                            </td>
	                                            <td>
	                                                <nobr>
													[#if order.expired]
														${message("shop.member.order.hasExpired")}
													[#elseif order.orderStatus == "completed" || order.orderStatus == "cancelled"]
														${message("Order.OrderStatus." + order.orderStatus)}
													[#elseif order.paymentStatus == "unpaid" || order.paymentStatus == "partialPayment"]
														${message("shop.member.order.waitingPayment")}
														[#if order.shippingStatus != "unshipped"]
															${message("Order.ShippingStatus." + order.shippingStatus)}
														[/#if]
													[#else]
														${message("Order.PaymentStatus." + order.paymentStatus)}
														[#if order.paymentStatus == "paid" && order.shippingStatus == "unshipped"]
															${message("shop.member.order.waitingShipping")}
														[#else]
														    [#if order.orderStatus == "receipt" && order.paymentStatus == "paid"]
															  ${message("Order.OrderStatus." + order.orderStatus)}
															[#else]
															  ${message("Order.ShippingStatus." + order.shippingStatus)}
															[/#if]
														[/#if]
													[/#if]
												    </nobr>
												</td>
	                                            <td >
	                                                  <div class="actionsControls"  style="margin: 0 auto;">
						                                    <a type="button" class="button butBlack butReturn" href="${base}/member/returnGoods/goodsInfo.ct?orderId=${order.id}">
						                                        <span>
						                                            ${message("shop.returnGoods")}
						                                        </span>
						                                    </a>
						                               </div>
	                                            </td>
	                                        </tr>
	                                       [/#list]
	                                       [#if !retrunOrders?has_content]
										       <p>${message("shop.member.noResult")}</p>
										   [/#if]
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                        <div class="line mod gaView">
	                            <div class="unit size1of1">
	                                <div class="actionsControls fRight">
	                                    <a id="continueShoppingBt" type="button" class="button butWhite" href="${base}/member/personalCenter/personalCenter.ct">
	                                        <span>
	                                            ${message("shop.returnGoods.returnPersonalCenter")}
	                                        </span>
	                                    </a>
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