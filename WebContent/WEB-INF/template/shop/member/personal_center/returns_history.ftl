<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("shop.returnGoods.returnGoodsHistory")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台 官方网站[/#if]</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        <link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
    </head>
    <body>
        [#include "/shop/include/header.ftl" /]
         <div class="rightWrapper">
	        <div class="container">
	            <section id="main" class="content-main body">
	                <div class="head">
	                </div>
	                <div class="body">
	                        <div class="line mod">
	                            <div class="unit size1of1">
	                                 <h2 class="h2">
	                                    ${message("shop.returnGoods.returnGoodsHistory")}
	                                 </h2>
	                                <table cellpadding="0" cellspacing="0" class="table shopBag">
	                                    <thead id="tableHead">
	                                        <tr>
												<th>
													${message("Returns.sn")}
												</th>
												<th>
												    ${message("Returns.order")}
												</th>
												<th>
													<nobr>${message("Returns.shippingMethod")}</nobr>
												</th>
												<th>
													<nobr>${message("Returns.deliveryCorp")}</nobr>
												</th>
												<th>
													<nobr>${message("Returns.trackingNo")}</nobr>
												</th>
												<th>
													<nobr>${message("Returns.shipper")}</nobr>
												</th>
												<th>
													${message("console.common.createDate")}
												</th>
												<th>
													<nobr>${message("Returns.hasGoodReturn")}</nobr>
												</th>
												<th>
													<nobr>${message("Returns.agreeReturn")}</nobr>
												</th>
												<th>
													<nobr>${message("console.common.handle")}</nobr>
												</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    [#list returnsList as returns]
											<tr>
												<td>
													<a href="viewReturns.ct?id=${returns.id}" style="text-decoration: underline;">${returns.sn}</a> 
												</td>
												<td> 
												   ${returns.order.sn}
												</td>
												<td>
													${(returns.shippingMethod)!"-"}
												</td>
												<td>
													${(returns.deliveryCorp)!"-"}
												</td>
												<td>
													${(returns.trackingNo)!"-"}
												</td>
												<td>
													${(returns.shipper)!"-"}
												</td>
												<td>
													<span title="${returns.createDate?string("yyyy-MM-dd HH:mm:ss")}">${returns.createDate?string("yyyy-MM-dd HH:mm:ss")}</span>
												</td>
												<td>
													[#if returns.hasGoodReturn]
														${message("Returns.hasGoodReturn."+returns.hasGoodReturn)}
													[#else] 
													    -
													[/#if]
												</td>
											    <td>
											       <nobr>
												    [#if returns.agreeReturn == -1]
														${message("console.returns.unaudited")}
													[#elseif returns.agreeReturn == 0] 
													    ${message("console.returns.agree")}
													[#elseif returns.agreeReturn == 1] 
													    ${message("console.returns.reject")}
												    [#elseif returns.agreeReturn == 2]
													   ${message("console.returns.agreedRefund")}
													[/#if]
													</nobr>
											    </td>
												<td>
												    [#if returns.agreeReturn == 0 || returns.agreeReturn == 2]
												       [#if returns.hasGoodReturn == 'true']
															<a href="editLogistics.ct?id=${returns.id}">
															   <nobr>[${message("shop.returnGoods.editLogistics")}]</nobr>
															</a>
													   [/#if]
													[/#if]
												</td>
											</tr>
										[/#list]
	                                    </tbody>
	                                </table>
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