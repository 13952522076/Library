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
<%@page import="com.sammyun.Setting.AccountLockType"%>
<%@page import="com.sammyun.service.RSAService"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String base = request.getContextPath();
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
		System.out.println(loginFailure);
		if (loginFailure != null) {
		    message = "登录失败";
		}
%>
<title>图书智能管理推荐系统</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<script type="text/javascript" src="<%=base%>/resources/console/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/jsbn.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/prng4.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/rng.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/rsa.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/base64.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/common.js"></script>

<script src="<%=base%>/resources/console/js/bootstrap.min.js?v=1.7"></script>
<script src="<%=base%>/resources/console/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="<%=base%>/resources/console/js/plugins/layer/layer.js"></script>

<link href="<%=base%>/resources/console/css/bootstrap.min.css?v=1.7" rel="stylesheet">
<link href="<%=base%>/resources/console/css/login.min.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
	$().ready( function() {
		var $loginForm = $("#loginForm");
		var $enPassword = $("#enPassword");
		var $username = $("#username");
		var $password = $("#password");
		
		
		// 表单验证
		$loginForm.submit( function() {
			if ($username.val() == "") {
				$.message("warn", "用户名不能为空");
				return false;
			}
			if ($password.val() == "") {
				$.message("warn", "密码不能为空");
				return false;
			}
			
			
			var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
			var enPassword = hex2b64(rsaKey.encrypt($password.val()));
			$enPassword.val(enPassword);
		});
				<%if (message != null) {%>
				layer.msg("<%=SpringUtils.getMessage(message, setting.getAccountLockCount())%>");
<%}%>
	});
</script>


<%
	} else {
%>
<title>图书智能管理推荐系统</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="Sencloud Team" />
<meta name="copyright" content="maxu" />
<link href="<%=base%>/resources/console/css/common.css" rel="stylesheet"
	type="text/css" />
<link href="<%=base%>/resources/console/css/login.min.css" rel="stylesheet"
	type="text/css" />
<%
	}
%>
</head>
<body class="signin">
	
	<!--  			
		<form id="loginForm" action="login.jsp" method="post">
			<input type="hidden" id="enPassword" name="enPassword" />
			<input id="username" name="username" maxlength="20" placeholder="用户名" />
			<input type="password" id="password"  maxlength="20" autocomplete="off" placeholder="密码" />
			<input type="submit" value="登录" />
		</form>
	-->		
	
	
	<div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>图书智能管理</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>Better mind,better life</h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 手机电脑的适应</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优秀算法的推荐</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优质管理的服务</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 简单易懂的操作</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 简洁大气的页面</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form id="loginForm" action="login.jsp" method="post">
                    <h4 class="no-margins">Login the system：</h4>
                    
                    <input type="hidden" id="enPassword" name="enPassword" />
                    <input id="username" class="form-control uname" name="username" maxlength="20" placeholder="用户名" />
                    <input type="password" id="password"  maxlength="20" autocomplete="off" placeholder="密码" class="form-control pword m-b"/>
                    <input class="btn btn-success btn-block" type="submit" value="登录" />
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2016 All Rights Reserved. Melody
            </div>
        </div>
    </div>			
	
</body>
</html>