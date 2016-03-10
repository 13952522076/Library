[#if notifyMessage??]
${notifyMessage}
[#else]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.payment.notify")}[#if systemShowPowered] -图书智能管理推荐系统[/#if]</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no,width=320,target-densitydpi=142">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="icon" href="${base}/favicon.ico" type="image/x-icon" />
<link href="${base}/resources/mobile/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/mobile/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/mobile/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/mobile/js/mobile.js"></script>
</head>
<body class="body_margin">
	<div class="fullscreen">
		<div class="span24" style="text-align: center;margin-top: 50%;">
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
					<a href="${base}/mobile/member/index.ct">${message("shop.payment.viewOrder")}</a>
				[#elseif payment.type == "recharge"]
					<a href="${base}/member/deposit/list.ct">${message("shop.payment.deposit")}</a>
				[/#if]
				| <a href="${base}/mobile/mobileIndex.ct">${message("shop.payment.index")}</a>
			</div>
		</div>
		<!-- footer -->
  [#include "/mobile/include/footer.ftl" /]
 <!-- footer -->
	</div>
  
</body>
</html>
[/#if]