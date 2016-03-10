<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.login.title")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>

<script type="text/javascript">
$().ready(function() {
      removeCookie("username");
      removeCookie("name");									
      $(".topNavDiv").css({'display':'none'});
});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="rightWrapper">
	<div class="container login" id="container login">
		<div class="span12" style="display: none;">
			<!--[@ad_position id = 9 /] -->
		</div>  
		<div class="span12 last" style="padding-left: 5px">
        <div class="modalContent" style="border:none;z-index:100"> 
			<div class="modalTitle">
				<h2 id="pop-title">${message("shop.login.popTitle")}</h2>
			</div>
			<div class="modalInfo">
				<!--登录弹出层-->
						<div class="rPadded">
							<p  style="max-width: 100em;">${message("shop.login.timeOutDesc")}</p>
							<form id="userLogonForm" method="post" name="Logon" action="${base}/login/loginSubmit.ct">
								<div class="actions" style="margin:8px 0 2px">
									<button id="verificationBt" type="button"class="button butBlack actionButton" value="${message("shop.login.verificationBt")}">
										<span> ${message("shop.login.verificationBt")} </span>
									</button>
								</div>
								<label id="error_result" style="" for="result" generated="true" class="error"></label>
							</form>
		    </div>  
		</div>
		</div>
	</div>

	[#include "/shop/include/footer.ftl" /]
</body>
</html>