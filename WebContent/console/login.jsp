<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.sammyun.Setting"%>
<%@page import="com.sammyun.util.SettingUtils"%>
<%@page import="com.sammyun.util.SpringUtils"%>
<%@page import="com.sammyun.Setting.CaptchaType"%>
<%@page import="com.sammyun.Setting.AccountLockType"%>
<%@page import="com.sammyun.service.RSAService"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String base = request.getContextPath();
	String captchaId = UUID.randomUUID().toString();
	ApplicationContext applicationContext = SpringUtils
			.getApplicationContext();
	Setting setting = SettingUtils.get();
	if (applicationContext != null) {
%>
<shiro:authenticated>
	<%
		response.sendRedirect(base + "/console/common/main.ct");
	%>
</shiro:authenticated>
<%
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="renderer" content="webkit">
<%
	if (applicationContext != null) {
		RSAService rsaService = SpringUtils.getBean("rsaServiceImpl",
				RSAService.class);
		RSAPublicKey publicKey = rsaService.generateKey(request);
		String modulus = Base64.encodeBase64String(publicKey
				.getModulus().toByteArray());
		String exponent = Base64.encodeBase64String(publicKey
				.getPublicExponent().toByteArray());

		String message = null;
		String loginFailure = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (loginFailure != null) {
			if (loginFailure
					.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
				message = "console.captcha.invalid";
			} else if (loginFailure
					.equals("org.apache.shiro.authc.UnknownAccountException")) {
				message = "console.login.unknownAccount";
			} else if (loginFailure
					.equals("org.apache.shiro.authc.DisabledAccountException")) {
				message = "console.login.disabledAccount";
			} else if (loginFailure
					.equals("org.apache.shiro.authc.LockedAccountException")) {
				message = "console.login.lockedAccount";
			} else if (loginFailure
					.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
				if (ArrayUtils.contains(setting.getAccountLockTypes(),
						AccountLockType.admin)) {
					message = "console.login.accountLockCount";
				} else {
					message = "console.login.incorrectCredentials";
				}
			} else if (loginFailure
					.equals("org.apache.shiro.authc.AuthenticationException")) {
				message = "console.login.authentication";
			}
		}
%>
<title>小书僮™智慧幼教管理平台</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="Sencloud Team" />
<meta name="copyright" content="Sencloud" />
<link href="<%=base%>/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=base%>/resources/console/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/resources/console/js/jquery.1.8.3.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/jsbn.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/prng4.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/rng.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/rsa.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/base64.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/common.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
	
	
</script>
<script type="text/javascript">
	$().ready( function() {
		var $loginForm = $("#loginForm");
		var $enPassword = $("#enPassword");
		var $username = $("#username");
		var $password = $("#password");
		var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		var $isRememberUsername = $("#isRememberUsername");
		// 记住用户名
		if(getCookie("adminUsername") != null) {
			$isRememberUsername.prop("checked", true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		} else {
			$isRememberUsername.prop("checked", false);
			$username.focus();
		}
		// 更换验证码
		$captchaImage.click( function() {
			$captchaImage.attr("src", "<%=base%>/console/common/captcha.ct?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
		});
		// 表单验证、记住用户名
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$.message("warn", "<%=SpringUtils
						.getMessage("console.login.usernameRequired")%>");
				return false;
			}
			if ($password.val() == "") {
				$.message("warn", "<%=SpringUtils
						.getMessage("console.login.passwordRequired")%>");
				return false;
			}
			if ($captcha.val() == "") {
				$.message("warn", "<%=SpringUtils
						.getMessage("console.login.captchaRequired")%>");
				return false;
			}
			
			if ($isRememberUsername.prop("checked")) {
				addCookie("adminUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
			} else {
				removeCookie("adminUsername");
			}
			
			var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
			var enPassword = hex2b64(rsaKey.encrypt($password.val()));
			$enPassword.val(enPassword);
		});
				<%if (message != null) {%>
				$.message("error", "<%=SpringUtils.getMessage(message, setting.getAccountLockCount())%>");
<%}%>
	});
</script>


<%
	} else {
%>
<title>提示信息 -图书智能管理推荐系统</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="Sencloud Team" />
<meta name="copyright" content="maxu" />
<link href="<%=base%>/resources/console/css/common.css" rel="stylesheet"
	type="text/css" />
<link href="<%=base%>/resources/console/css/login.css" rel="stylesheet"
	type="text/css" />
<%
	}
%>
</head>
<body>
	<%
		if (applicationContext != null) {
	%>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<img src="<%=base%>/resources/console/images/login_logo2.png"
					alt="小书僮™智慧幼教管理平台" style="  height: 80px;width: 300px;" />
				<!-- 二维码 start-->
				<div class="wechatdiv" style="display: none;">
					<div class="qrcodeImage">
						<img src="<%=base%>/resources/console/images/ios.jpg"
							style="width: 100px; height: 100px;">
					</div>
					<div class="scan_text">
						<span>下载</span>
					</div>
					<div class="scan_text">
						<span>小书僮iOS版本</span>
					</div>
				</div>
				<div class="wechatdivs" style="display: none;">
					<div class="qrcodeImages">
						<img src="<%=base%>/resources/console/images/android.jpg"
							style="width: 100px; height: 100px;">
					</div>
					<div class="scan_texts">
						<span>下载</span>
					</div>
					<div class="scan_texts">
						<span>小书僮Android版本</span>
					</div>
				</div>
				<!-- 二维码 end-->
			</div>
			<div class="menu">
				<ul>
					<li><a href="<%=base%>/console/">首页</a></li>
					<li><a href="<%=base%>/front/link/applyOpen.ct">申请开通小书僮</a></li>
					<!-- onclick="javascript:Show_Hidden(this)" -->
					<li><a href="javascript:void(0)" id="downloadApplication">下载应用</a></li>
					<li class="noborder"><a href="<%=base%>/front/link/help.ct" style="border-right: 0;">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main" id="div_1" style="display: block;">
		<!-- <div class="img1" style="display: block;"></div>
        <div class="img2" style="display: none;"></div> 
        -->
		<div class="img3" style="display: block;"></div>
		<div class="img4" style="display: none;"></div>
		<div class="img5" style="display: none;"></div>
		<div class="img6" style="display: none;"></div>
		<div class="login">
			<div class="row">
				<div class="rowLeft">
					<h1 class="login-header-big"></h1>
				</div>
				<div class="rowRight loginstyle">
					<div class="loginForm well">
						<form id="loginForm" action="login.jsp" method="post"
							class="loginForm">
							<input type="hidden" id="enPassword" name="enPassword" />
							<%
								if (ArrayUtils.contains(setting.getCaptchaTypes(),
									CaptchaType.consoleLogin)) {
							%>
							<input type="hidden" name="captchaId" value="<%=captchaId%>" />
							<%
								}
							%>
							<div class="login-header">
								<%=SpringUtils
				.getMessage("console.login.PreschoolEducationPlatform")%>
							</div>
							<fieldset>
								<section> <label class="input"> <input
									type="text" id="username" name="username" class="user_input"
									maxlength="20"
									placeholder="<%=SpringUtils.getMessage("console.login.username")%>" />
								</label> </section>
								<section> <label class="input"> <input
									type="password" id="password" class="user_input" maxlength="20"
									autocomplete="off"
									placeholder="<%=SpringUtils.getMessage("console.login.password")%>" />
								</label> </section>
								<%
									if (ArrayUtils.contains(setting.getCaptchaTypes(),
										CaptchaType.consoleLogin)) {
								%>
								<section> <label class="input"> <input
									type="text" id="captcha" name="captcha"
									class="captchaText captcha" maxlength="4" autocomplete="off"
									placeholder="<%=SpringUtils.getMessage("console.captcha.name")%>" />
									<img id="captchaImage" class="captchaImage"
									src="<%=base%>/console/common/captcha.ct?captchaId=<%=captchaId%>"
									title="<%=SpringUtils
					.getMessage("console.captcha.imageTitle")%>" />
								</label> </section>
								<%
									}
								%>
							</fieldset>
							<footer> <label> <input type="checkbox"
								id="isRememberUsername" value="true" /> <%=SpringUtils
				.getMessage("console.login.rememberUsername")%>
							</label> <input type="submit" class="loginButton"
								value="<%=SpringUtils.getMessage("console.login.login")%>" />
							<div class="link">
								强烈建议使用 <a target="_blank"
									href="http://rj.baidu.com/soft/detail/14744.html?ald"> <img
									width="18px" height="18px"
									src="<%=base%>/resources/console/images/Google.png" alt="">谷歌浏览器
								</a> <a target="_blank" href="http://www.firefox.com.cn/download/">
									<img width="18px" height="18px"
									src="<%=base%>/resources/console/images/firefox.png" alt="">火狐浏览器
								</a>
							</div>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="wrap">
			<a href="<%=base%>/front/link/useAgreement.ct">使用协议</a>
			<span> 福州盛云汇电子科技有限公司版权所有
				© 2015-2016&nbsp;&nbsp;闽ICP备15011832号</span>
		</div>
	</div>
	 <%
     } else {
     %>
	<fieldset>
		<legend>系统出现异常</legend>
	</fieldset>
	<%}%>
</body>
</html>