[#if notifyMessage??]
${notifyMessage}
[#else]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.payment.notify")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
<style type="text/css">
div.payment .title {
	height: 80px;
	line-height: 80px;
	text-align: center;
	font-size: 16px;
	font-family: "Microsoft YaHei";
	border: solid 1px #000000;
	background-color:#ffffff;
}

div.payment .bottom {
	height: 60px;
	line-height: 60px;
	margin-bottom: 10px;
	text-align: center;
	color: #888888;
	background-color: #f9f9f9;
	border-left:solid 1px #ffffff;
	border-right:solid 1px #ffffff;
	border-bottom:solid 1px #ffffff;
}

div.payment .bottom a {
	color: #000000;
	font-size: 14px;
	margin: 0px 10px;
}
</style>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container payment">
		<div class="span24">
			<div class="title">
				[#if payment.status == "wait"]
					${message("shop.payment.waitTitle")}
				[#elseif payment.status == "success"]
					[#if payment.type == "payment"]
						${message("shop.payment.orderTitle")}
					[#elseif payment.type == "recharge"]
						${message("shop.payment.depositTitle")}
					[/#if]
				[#elseif payment.status == "failure"]
					${message("shop.payment.failureTitle")}
				[/#if]
			</div>
			<div class="bottom">
				[#if payment.type == "payment"]
					<a href="${base}/member/personalCenter/personalCenter.ct?orderId=${payment.order.id}">${message("shop.payment.viewOrder")}</a>
				[#elseif payment.type == "recharge"]
					<a href="${base}/member/deposit/list.ct">${message("shop.payment.deposit")}</a>
				[/#if]
				| <a href="${base}/">${message("shop.payment.index")}</a>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>
[/#if]