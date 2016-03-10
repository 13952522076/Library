<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.order.logisticsinformation.title")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/logistics.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">
$(document).ready(function($){	
	$("#logisticsInfoTab").tabso({
		cntSelect:"#logisticsInfoCon",
		tabEvent:"click",
		tabStyle:"normal"
	});
});
</script>
</head>
<body>
[#include "/shop/include/header.ftl" /]
	<div class="rightWrapper">
	    <div class="container">
        <div class="logistics_information">
            <div class="logistics_information_title"><span>${message("shop.order.logisticsinformation.title")}</span></div>
        </div>
	    <div class="logistics_iframe_title">
            <ul>
			    <li class="personalControl">
				    <label class="vipLabelUp" for="tracking">${message("shop.order.logisticsinformation.tracking")}</label>
					<span id="tracking">
					       ${message("shop.order.logisticsinformation.kuaidi100.start")}
					       <a target="_blank" href="http://www.kuaidi100.com">${message("shop.order.logisticsinformation.kuaidi100")}</a>
					       ${message("shop.order.logisticsinformation.kuaidi100.end")}
                    </span>
				</li>
			</ul>	
		</div>
		 <div class="logistics_iframe_title">
            <ul>
			    <li class="personalControl">
				    <label class="vipLabelUp" for="orderSn">${message("shop.order.sn")}：</label>
					<span id="orderSn">
					       ${order.sn}
                    </span>
				</li>
				<li class="personalControl">
				    <label class="vipLabelUp" for="orderCreateDate">${message("shop.common.createDate")}：</label>
					<span id="orderCreateDate">
					    ${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
                    </span>
				</li>
			</ul>			
		</div>
        <ul class="tabbtn" id="logisticsInfoTab">
            [#list shippingLogisticsUrlBlockList as shippingLogisticsUrlBlock]
			    <li class="current">
			        <a href="javascript:;">
			            ${message("shop.order.logisticsinformation.parcel")}${shippingLogisticsUrlBlock_index + 1}
			        </a>
			    </li>
            [/#list]
		</ul><!--tabbtn end-->
		<div class="tabcon" id="logisticsInfoCon">
			[#list shippingLogisticsUrlBlockList as shippingLogisticsUrlBlock]
				<div class="sublist">
					<ul>
					    <li class="personalControl">
						    <label class="vipLabelUp" for="receivingAddress">${message("shop.order.logisticsinformation.receivingAddress")}：</label>
							<span id="receivingAddress">${shippingLogisticsUrlBlock.shipping.area}-${shippingLogisticsUrlBlock.shipping.address}-${shippingLogisticsUrlBlock.shipping.zipCode}-${shippingLogisticsUrlBlock.shipping.consignee}-${shippingLogisticsUrlBlock.shipping.phone}</span>
						</li>
						<li class="personalControl">
						    <label class="vipLabelUp" for="shippingAddress">${message("shop.order.logisticsinformation.shippingAddress")}：</label>
							<span id="shippingAddress">${shippingLogisticsUrlBlock.shipping.shopStore.province}${shippingLogisticsUrlBlock.shipping.shopStore.city}-${shippingLogisticsUrlBlock.shipping.shopStore.address}-${shippingLogisticsUrlBlock.shipping.shopStore.zipcode}-${shippingLogisticsUrlBlock.shipping.shopStore.consignor}-${shippingLogisticsUrlBlock.shipping.shopStore.telephone}</span>
						</li>
						<li class="personalControl">
							<label class="vipLabelUp" for="deliveryCorp">${message("shop.order.logisticsinformation.logisticscompanyname")}：</label>
							<span id="deliveryCorp">${shippingLogisticsUrlBlock.shipping.deliveryCorp}</span>
						</li>
						<li class="personalControl">
							<label class="vipLabelUp" for="trackingNo">${message("shop.order.logisticsinformation.waybill.number")}：</label>
							<span id="trackingNo">${shippingLogisticsUrlBlock.shipping.trackingNo}</span>
						</li>
						<li class="personalControl">
							<label class="vipLabelUp" for="createDate">${message("shop.order.logisticsinformation.deliverytime")}：</label>
							<span id="createDate">${shippingLogisticsUrlBlock.shipping.createDate?string("yyyy-MM-dd HH:mm:ss")}</span>
						</li>
						<li class="personalControl">
							<table cellpadding="0" cellspacing="0" class="table shopBag">
                                <thead id="tableHead">
                                    <tr>
                                        <th>
                                            ${message("ShippingItem.sn")}
                                        </th>
                                        <th>
                                            ${message("ShippingItem.name")}
                                        </th>
                                        <th>
                                            ${message("ShippingItem.quantity")}
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                [#list shippingLogisticsUrlBlock.shipping.shippingItems as shippingItem]
                                    <tr>
                                        <td>
                                          ${shippingItem.sn}
                                        </td>
                                        <td>
                                           ${shippingItem.name}
                                        </td>
                                        <td class="color">
                                           ${shippingItem.quantity}
                                        </td>
                                    </tr>
                                [/#list]
                                </tbody>
	                        </table>
						</li>
						<li class="personalControl">
						    <div class="logistics_iframe_container">
			                    <iframe id="kuaidi100_${shippingLogisticsUrlBlock.id}" name="kuaidi100_${shippingLogisticsUrlBlock.id}" src="${shippingLogisticsUrlBlock.logisticsUrl}" width="100%" height="260" marginwidth="0" border="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no">
		                        </iframe>
			                </div>
						</li>
					</ul>
				</div><!--tabcon end-->
			[/#list]
		</div><!--tabcon end-->
	    </div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>