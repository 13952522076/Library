<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.member.order.view")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<style type="text/css">
	.info {
	height: 26px;
	line-height: 26px;
	padding-left: 6px;
	overflow: hidden;
	border-bottom: 1px solid #d7e8f1;
	background-color: #f5faff;
	font-size: larger;
}
</style>
</head>
<body>
	<center>
	<table width="700px">
		<tr>
			<td colspan="3" class="info">
				订单号：${sn}
			</td>
		</tr>
		<tr>
			<table width="660px" style="text-align:left;">
				[#list shippings as shipping]
					<tr>
						<th>
							${message("Shipping.deliveryCorp")}:
						</th>
						<th>
							${message("Shipping.trackingNo")}:
						</th>
						<th>
							${message("shop.member.order.deliveryDate")}:
						</th>
					</tr>
					<tr>
						<td>
							${(shipping.deliveryCorp)!"-"}
						</td>
						<td>
							${(shipping.trackingNo)!"-"}
						</td>
						<td>
							${shipping.createDate?string("yyyy-MM-dd HH:mm")}
						</td>
					</tr>
					<tr>
						<td class="info" colspan="3">
							<span style="color: blueviolet;font-size: larger;">物流跟踪：</span>以下跟踪信息由<span><a href="http://www.kuaidi100.com/">快递100</a></span>提供，如有疑问请到物流公司官网查询
						</td>
					</tr>
					<tr>
						<td width="100px">
							&nbsp;
						</td>
						<td colspan="2">
							<table>
								<tr>
									<td>
										${data}
									</td>
								</tr>
							</table>
						</td>
					</tr>
				[/#list]
			</table>
		</tr>
	</table>
	</center>
</body>
</html>