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
<title>图书智能管理推荐系统</title>
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
	
	<div class="main" id="div_1" style="display: block;">
		<div class="img3" style="display: block;"></div>
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
							<footer>
							 
							<input type="submit" class="loginButton"
								value="<%=SpringUtils.getMessage("console.login.login")%>" />
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