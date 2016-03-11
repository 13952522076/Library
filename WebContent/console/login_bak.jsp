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
<script>
	$(function() {
		if (navigator.userAgent.indexOf("MSIE 6") > -1) {
			//document.location.href = "/file/ie6/ie.html";
			document.end();
		}
		$(".img3").fadeIn(2000);
		
		setTimeout(function() {
			$(".img3").fadeOut(2000);
			$(".img4").fadeIn(2000);
			$(".loginForm").addClass("wellAppend1");
			$(".loginForm").removeClass("well");
		}, 6000);
		
		setTimeout(function() {
			$(".img4").fadeOut(2000);
			$(".img5").fadeIn(2000);
			$(".loginForm").addClass("wellAppend2");
			$(".loginForm").removeClass("wellAppend1");
		}, 12000);
		setTimeout(function() {
			$(".img5").fadeOut(2000);
			$(".img6").fadeIn(2000);
			$(".loginForm").addClass("wellAppend3");
			$(".loginForm").removeClass("wellAppend2");
		}, 18000);
		setTimeout(function() {
			$(".img6").fadeOut(2000);
			$(".img3").fadeIn(2000);
			$(".loginForm").addClass("well");
			$(".loginForm").removeClass("wellAppend3");
		}, 24000);//第一次执行一遍
		setInterval(function() {
			var testInt = setInterval(function() {
				$(".img3").fadeIn(2000);
				setTimeout(function() {
					$(".img3").fadeOut(2000);
					$(".img4").fadeIn(2000);
					$(".loginForm").addClass("wellAppend1");
					$(".loginForm").removeClass("well");
				}, 6000);
				setTimeout(function() {
					$(".img4").fadeOut(2000);
					$(".img5").fadeIn(2000);
					$(".loginForm").addClass("wellAppend2");
					$(".loginForm").removeClass("wellAppend1");
				}, 12000);
				setTimeout(function() {
					$(".img5").fadeOut(2000);
					$(".img6").fadeIn(2000);
					$(".loginForm").addClass("wellAppend3");
					$(".loginForm").removeClass("wellAppend2");
				}, 18000);
				setTimeout(function() {
					$(".img6").fadeOut(2000);
					$(".img3").fadeIn(2000);
					$(".loginForm").addClass("well");
					$(".loginForm").removeClass("wellAppend3");
				}, 24000);
				clearInterval(testInt);
				//$(".img6").fadeIn(8000);
			}, 2000);
		}, 24000);//自动切换执行
	});
</script>
<script type="text/javascript">
	//二维码效果
	$(function() {
		$("#downloadApplication").click(function() {
			$(".wechatdiv").toggle();
			$(".wechatdivs").toggle();
		});
	});
	//登录页面切换
	function choise() {
		document.getElementById("div_1").style.display = "none";
		document.getElementById("div_3").style.display = "none";
		document.getElementById("section_1").style.display = "none";
		$(".wechatdiv").css("display", "none");
		$(".wechatdivs").css("display", "none");
		document.getElementById("div_2").style.display = "block";
	}
	function help() {
		document.getElementById("div_1").style.display = "none";
		document.getElementById("div_2").style.display = "none";
		document.getElementById("div_3").style.display = "none";
		$(".wechatdiv").css("display", "none");
		$(".wechatdivs").css("display", "none");
		document.getElementById("section_1").style.display = "block";
	}
	function agreement() {
		document.getElementById("div_1").style.display = "none";
		document.getElementById("div_2").style.display = "none";
		document.getElementById("section_1").style.display = "none";
		$(".wechatdiv").css("display", "none");
		$(".wechatdivs").css("display", "none");
		document.getElementById("div_3").style.display = "block";
	}
	//使用帮助页面切换
	function changeShow(obj) {
		var all = document.getElementById("showAll")
		.getElementsByTagName("div");
		var li = document.getElementById("navul").getElementsByTagName("li");
		for (var i = 0; i < all.length; i++) {
			if (all[i] == obj) {
				li[i].setAttribute("class", "single active");
				all[i].style.display = 'block';
			} else {
				li[i].setAttribute("class", "single");
				all[i].style.display = 'none';
			}
		}
	}
	
</script>
<%
	} else {
%>
<title>提示信息 -图书智能管理推荐系统</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="Sencloud Team" />
<meta name="copyright" content="Sencloud" />
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
					alt="图书智能管理推荐系统" style="  height: 80px;width: 300px;" />
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
								图书智能管理推荐系统
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
	
	<!-- IE版本过低 start -->
	<div id="bodyMask" style="display:none;"></div>
	<div id="bodyMask2" style="display:none;"></div>
	<div id="browser" class="layerDiv" jQuery19103044797775550879="18">
		<h2>噢，您是否知道您正在使用浏览器无法支持我们的页面？</h2>
		<h3>很抱歉，由于采用了HTML5，当前浏览器无法完美的呈现该页面。</h3>
		<div>
			<span>如果你还不知道什么是HTML5标准，请看<a href="http://zh.wikipedia.org/wiki/HTML5" target="_blank">维基百科</a></span>
			建议您使用以下浏览器最新版本
		</div>
		<ul>
			<li>
				<a target="_blank" href="http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html" class="icoChrom" title="谷歌Chrome浏览器"></a>
			</li>
			<li>
				<a target="_blank" href="http://www.opera.com/zh-cn" class="icoOpera" title="Opera浏览器"></a>
			</li>
			<li>
				<a target="_blank" href="http://www.firefox.com.cn/" class="icoFirefox" title="火狐浏览器"></a>
			</li>
			<li>
				<a target="_blank" href="http://www.apple.com/cn/safari/" class="icosafari" title="safari浏览器"></a>
			</li>
		</ul>
		<p><a class="ico icoGt" onclick="knowStillOpen()" >知道了但是还是请打开页面</a></p>
	</div>
	<!-- IE版本过低 end-->
	
	<!-- 申请开通小书僮 start-->
	<div class="main contact_bodyColor" id="div_2" style="display: none;">
		<div class="case_title">
			<p class="title_CN">申请开通小书童</p>
		</div>
		<div class="contact_form">
			<form>
				<div>
					<input type="text" placeholder="姓名" required="">
				</div>
				<div>
					<input type="text" placeholder="电子邮箱/手机号码" required="">
				</div>
				<div>
					<textarea placeholder="请描述您的需求，我们会在2个工作日之内与您取得联系"></textarea>
				</div>
				<div>
					<input type="submit" class="contact_submit">
				</div>
			</form>
		</div>
	</div>
	<!--  申请开通小书僮nd  -->
	<!-- 使用帮助start -->
	<section class="main" id="section_1" style="display: none;">
	<aside> <nav>
	<ul class="list" id="navul">
		<li class="single active"><a href="#"
			onclick="changeShow(describe1)">公司介绍</a></li>
		<li class="single"><a href="#" onclick="changeShow(describe2)">服务条款</a></li>
		<li class="single"><a href="#" onclick="changeShow(describe3)">幼教平台说明</a></li>
		<li class="single"><a href="#" onclick="changeShow(describe4)">幼教平台升级</a></li>
		<li class="single"><a href="#" onclick="changeShow(describe5)">幼教平台维护</a></li>
	</ul>
	</nav> </aside>
	<div class="service_content content" id="showAll">
		<div id="describe1">
			<h2 class="first">公司介绍</h2>
			<p>本公司向用户提供的所有产品和服务，包括但不限于商标、域名、软件程序、文字报导、图片、声音、录像、图表、标志、标识、广告、版面设计、目录与名称等，均属于我公司所有，受国家相关法律保护。未经本公司书面许可，任何单位及个人不得以任何方式或理由将上述产品、服务、信息、文字材料的任何部分用于商业、营利或广告目的；不得进行分发、修改、编辑、传播、表演、展示、程序反向工程、镜像、销售或与其它产品捆绑使用、销售。用户将本公司提供的内容与服务用于纯个人消费时，应遵守知识产权及其他相关法律的规定，不得侵犯本公司权利。
				违反上述声明者，本公司将依法追究其法律责任。</p>
			<h2>隐私保护声明</h2>
			<p>为更好的为用户提供安全、周到及个性化的服务，用户使用本公司服务或产品时，可能需要提供个人信息，这些信息由用户自愿提供。用户有权在任何时候拒绝提供这些信息，但可能无法完全体验本公司提供的服务。本公司注重保护用户的个人隐私，公司保证不对外公开或向第三方提供用户申请资料及用户在使用服务时的个人信息，但下列情况除外：事先获得用户的明确授权；根据有关的法律法规及相关政府主管部门的要求；为维护公司、用户及社会公众的利益等。不透露单个用户隐私资料的前提下，公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。本公司将尽力采用相应的技术手段防止用户的个人资料丢失、被盗用或遭窜改，但不承诺完全避免上述情况。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用的，公司不承担责任。</p>
			<h2>责任承担声明</h2>
			<p>在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
			<h2>隐私声明</h2>
			<p>广州市贝聊信息科技有限公司以此声明对本产品用户的隐私保护进行许诺。本声明适用贝聊的所有相关服务，并随产品提供的服务范围不断扩大，及时更新隐私声明且不再另行通知，更新后的隐私声明一旦公布即有效代替原来的隐私声明。</p>
			<h2>隐私政策</h2>
			<p>贝聊将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用贝聊时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p>
				1、事先获得用户的明确授权；<br>2、用户同意公开其个人资料；<br>3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；<br>4、用户违反了本产品其他使用规定；<br>5、为维护社会公众的合法利益；<br>6、为维护贝聊的合法权益；<br>7、其他非因贝聊原因导致的个人信息泄漏
			</p>
			<h2>使用说明</h2>
			<p>贝聊用户可以通过设定的密码来保护账户和资料安全。用户应当对其密码的保密负全部责任。请不要将此类信息泄漏给他人。</p>
			<h2>免责说明</h2>
			<p>如发生以下情况，贝聊不承担任何法律责任：</p>
			<p>
				1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏；<br>2、贝聊根据法律规定或政府相关政策要求提供您的个人信息；<br>3、任何由于黑客攻击、电脑病毒侵入或政府管制等非贝聊方面的原因导致的用户信息安全问题；<br>因不可抗力导致的任何后果。
			</p>
		</div>
		<div id="describe2">
			<h2 class="first">服务条款</h2>
			<p>本公司向用户提供的所有产品和服务，包括但不限于商标、域名、软件程序、文字报导、图片、声音、录像、图表、标志、标识、广告、版面设计、目录与名称等，均属于我公司所有，受国家相关法律保护。未经本公司书面许可，任何单位及个人不得以任何方式或理由将上述产品、服务、信息、文字材料的任何部分用于商业、营利或广告目的；不得进行分发、修改、编辑、传播、表演、展示、程序反向工程、镜像、销售或与其它产品捆绑使用、销售。用户将本公司提供的内容与服务用于纯个人消费时，应遵守知识产权及其他相关法律的规定，不得侵犯本公司权利。
				违反上述声明者，本公司将依法追究其法律责任。</p>
			<h2>隐私保护声明</h2>
			<p>为更好的为用户提供安全、周到及个性化的服务，用户使用本公司服务或产品时，可能需要提供个人信息，这些信息由用户自愿提供。用户有权在任何时候拒绝提供这些信息，但可能无法完全体验本公司提供的服务。本公司注重保护用户的个人隐私，公司保证不对外公开或向第三方提供用户申请资料及用户在使用服务时的个人信息，但下列情况除外：事先获得用户的明确授权；根据有关的法律法规及相关政府主管部门的要求；为维护公司、用户及社会公众的利益等。不透露单个用户隐私资料的前提下，公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。本公司将尽力采用相应的技术手段防止用户的个人资料丢失、被盗用或遭窜改，但不承诺完全避免上述情况。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用的，公司不承担责任。</p>
			<h2>责任承担声明</h2>
			<p>在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
			<h2>隐私声明</h2>
			<p>广州市贝聊信息科技有限公司以此声明对本产品用户的隐私保护进行许诺。本声明适用贝聊的所有相关服务，并随产品提供的服务范围不断扩大，及时更新隐私声明且不再另行通知，更新后的隐私声明一旦公布即有效代替原来的隐私声明。</p>
			<h2>隐私政策</h2>
			<p>贝聊将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用贝聊时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p>
				1、事先获得用户的明确授权；<br>2、用户同意公开其个人资料；<br>3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；<br>4、用户违反了本产品其他使用规定；<br>5、为维护社会公众的合法利益；<br>6、为维护贝聊的合法权益；<br>7、其他非因贝聊原因导致的个人信息泄漏
			</p>
			<h2>使用说明</h2>
			<p>贝聊用户可以通过设定的密码来保护账户和资料安全。用户应当对其密码的保密负全部责任。请不要将此类信息泄漏给他人。</p>
			<h2>免责说明</h2>
			<p>如发生以下情况，贝聊不承担任何法律责任：</p>
			<p>
				1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏；<br>2、贝聊根据法律规定或政府相关政策要求提供您的个人信息；<br>3、任何由于黑客攻击、电脑病毒侵入或政府管制等非贝聊方面的原因导致的用户信息安全问题；<br>因不可抗力导致的任何后果。
			</p>
		</div>
		<div id="describe3">
			<h2 class="first">幼教平台说明</h2>
			<p>本公司向用户提供的所有产品和服务，包括但不限于商标、域名、软件程序、文字报导、图片、声音、录像、图表、标志、标识、广告、版面设计、目录与名称等，均属于我公司所有，受国家相关法律保护。未经本公司书面许可，任何单位及个人不得以任何方式或理由将上述产品、服务、信息、文字材料的任何部分用于商业、营利或广告目的；不得进行分发、修改、编辑、传播、表演、展示、程序反向工程、镜像、销售或与其它产品捆绑使用、销售。用户将本公司提供的内容与服务用于纯个人消费时，应遵守知识产权及其他相关法律的规定，不得侵犯本公司权利。
				违反上述声明者，本公司将依法追究其法律责任。</p>
			<h2>隐私保护声明</h2>
			<p>为更好的为用户提供安全、周到及个性化的服务，用户使用本公司服务或产品时，可能需要提供个人信息，这些信息由用户自愿提供。用户有权在任何时候拒绝提供这些信息，但可能无法完全体验本公司提供的服务。本公司注重保护用户的个人隐私，公司保证不对外公开或向第三方提供用户申请资料及用户在使用服务时的个人信息，但下列情况除外：事先获得用户的明确授权；根据有关的法律法规及相关政府主管部门的要求；为维护公司、用户及社会公众的利益等。不透露单个用户隐私资料的前提下，公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。本公司将尽力采用相应的技术手段防止用户的个人资料丢失、被盗用或遭窜改，但不承诺完全避免上述情况。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用的，公司不承担责任。</p>
			<h2>责任承担声明</h2>
			<p>在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
			<h2>隐私声明</h2>
			<p>广州市贝聊信息科技有限公司以此声明对本产品用户的隐私保护进行许诺。本声明适用贝聊的所有相关服务，并随产品提供的服务范围不断扩大，及时更新隐私声明且不再另行通知，更新后的隐私声明一旦公布即有效代替原来的隐私声明。</p>
			<h2>隐私政策</h2>
			<p>贝聊将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用贝聊时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p>
				1、事先获得用户的明确授权；<br>2、用户同意公开其个人资料；<br>3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；<br>4、用户违反了本产品其他使用规定；<br>5、为维护社会公众的合法利益；<br>6、为维护贝聊的合法权益；<br>7、其他非因贝聊原因导致的个人信息泄漏
			</p>
			<h2>使用说明</h2>
			<p>贝聊用户可以通过设定的密码来保护账户和资料安全。用户应当对其密码的保密负全部责任。请不要将此类信息泄漏给他人。</p>
			<h2>免责说明</h2>
			<p>如发生以下情况，贝聊不承担任何法律责任：</p>
			<p>
				1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏；<br>2、贝聊根据法律规定或政府相关政策要求提供您的个人信息；<br>3、任何由于黑客攻击、电脑病毒侵入或政府管制等非贝聊方面的原因导致的用户信息安全问题；<br>因不可抗力导致的任何后果。
			</p>
		</div>
		<div id="describe4">
			<h2 class="first">幼教平台升级</h2>
			<p>本公司向用户提供的所有产品和服务，包括但不限于商标、域名、软件程序、文字报导、图片、声音、录像、图表、标志、标识、广告、版面设计、目录与名称等，均属于我公司所有，受国家相关法律保护。未经本公司书面许可，任何单位及个人不得以任何方式或理由将上述产品、服务、信息、文字材料的任何部分用于商业、营利或广告目的；不得进行分发、修改、编辑、传播、表演、展示、程序反向工程、镜像、销售或与其它产品捆绑使用、销售。用户将本公司提供的内容与服务用于纯个人消费时，应遵守知识产权及其他相关法律的规定，不得侵犯本公司权利。
				违反上述声明者，本公司将依法追究其法律责任。</p>
			<h2>隐私保护声明</h2>
			<p>为更好的为用户提供安全、周到及个性化的服务，用户使用本公司服务或产品时，可能需要提供个人信息，这些信息由用户自愿提供。用户有权在任何时候拒绝提供这些信息，但可能无法完全体验本公司提供的服务。本公司注重保护用户的个人隐私，公司保证不对外公开或向第三方提供用户申请资料及用户在使用服务时的个人信息，但下列情况除外：事先获得用户的明确授权；根据有关的法律法规及相关政府主管部门的要求；为维护公司、用户及社会公众的利益等。不透露单个用户隐私资料的前提下，公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。本公司将尽力采用相应的技术手段防止用户的个人资料丢失、被盗用或遭窜改，但不承诺完全避免上述情况。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用的，公司不承担责任。</p>
			<h2>责任承担声明</h2>
			<p>在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
			<h2>隐私声明</h2>
			<p>广州市贝聊信息科技有限公司以此声明对本产品用户的隐私保护进行许诺。本声明适用贝聊的所有相关服务，并随产品提供的服务范围不断扩大，及时更新隐私声明且不再另行通知，更新后的隐私声明一旦公布即有效代替原来的隐私声明。</p>
			<h2>隐私政策</h2>
			<p>贝聊将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用贝聊时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p>
				1、事先获得用户的明确授权；<br>2、用户同意公开其个人资料；<br>3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；<br>4、用户违反了本产品其他使用规定；<br>5、为维护社会公众的合法利益；<br>6、为维护贝聊的合法权益；<br>7、其他非因贝聊原因导致的个人信息泄漏
			</p>
			<h2>使用说明</h2>
			<p>贝聊用户可以通过设定的密码来保护账户和资料安全。用户应当对其密码的保密负全部责任。请不要将此类信息泄漏给他人。</p>
			<h2>免责说明</h2>
			<p>如发生以下情况，贝聊不承担任何法律责任：</p>
			<p>
				1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏；<br>2、贝聊根据法律规定或政府相关政策要求提供您的个人信息；<br>3、任何由于黑客攻击、电脑病毒侵入或政府管制等非贝聊方面的原因导致的用户信息安全问题；<br>因不可抗力导致的任何后果。
			</p>
		</div>
		<div id="describe5">
			<h2 class="first">幼教平台维护</h2>
			<p>本公司向用户提供的所有产品和服务，包括但不限于商标、域名、软件程序、文字报导、图片、声音、录像、图表、标志、标识、广告、版面设计、目录与名称等，均属于我公司所有，受国家相关法律保护。未经本公司书面许可，任何单位及个人不得以任何方式或理由将上述产品、服务、信息、文字材料的任何部分用于商业、营利或广告目的；不得进行分发、修改、编辑、传播、表演、展示、程序反向工程、镜像、销售或与其它产品捆绑使用、销售。用户将本公司提供的内容与服务用于纯个人消费时，应遵守知识产权及其他相关法律的规定，不得侵犯本公司权利。
				违反上述声明者，本公司将依法追究其法律责任。</p>
			<h2>隐私保护声明</h2>
			<p>为更好的为用户提供安全、周到及个性化的服务，用户使用本公司服务或产品时，可能需要提供个人信息，这些信息由用户自愿提供。用户有权在任何时候拒绝提供这些信息，但可能无法完全体验本公司提供的服务。本公司注重保护用户的个人隐私，公司保证不对外公开或向第三方提供用户申请资料及用户在使用服务时的个人信息，但下列情况除外：事先获得用户的明确授权；根据有关的法律法规及相关政府主管部门的要求；为维护公司、用户及社会公众的利益等。不透露单个用户隐私资料的前提下，公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。本公司将尽力采用相应的技术手段防止用户的个人资料丢失、被盗用或遭窜改，但不承诺完全避免上述情况。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用的，公司不承担责任。</p>
			<h2>责任承担声明</h2>
			<p>在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
			<h2>隐私声明</h2>
			<p>广州市贝聊信息科技有限公司以此声明对本产品用户的隐私保护进行许诺。本声明适用贝聊的所有相关服务，并随产品提供的服务范围不断扩大，及时更新隐私声明且不再另行通知，更新后的隐私声明一旦公布即有效代替原来的隐私声明。</p>
			<h2>隐私政策</h2>
			<p>贝聊将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用贝聊时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p>
				1、事先获得用户的明确授权；<br>2、用户同意公开其个人资料；<br>3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；<br>4、用户违反了本产品其他使用规定；<br>5、为维护社会公众的合法利益；<br>6、为维护贝聊的合法权益；<br>7、其他非因贝聊原因导致的个人信息泄漏
			</p>
			<h2>使用说明</h2>
			<p>贝聊用户可以通过设定的密码来保护账户和资料安全。用户应当对其密码的保密负全部责任。请不要将此类信息泄漏给他人。</p>
			<h2>免责说明</h2>
			<p>如发生以下情况，贝聊不承担任何法律责任：</p>
			<p>
				1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏；<br>2、贝聊根据法律规定或政府相关政策要求提供您的个人信息；<br>3、任何由于黑客攻击、电脑病毒侵入或政府管制等非贝聊方面的原因导致的用户信息安全问题；<br>因不可抗力导致的任何后果。
			</p>
		</div>
	</div>
	</section>
	<!-- 使用帮助end -->
	<!-- 使用协议start--->
	<div class="whole">
		<div class="article" id="div_3" style="display: none;">
			<h1>使用协议</h1>
			<div class="grid-16-8 clearfix">
				<div class="article">
					<span class="describe"><h3>1 接受条款</h3></span>
					    豆瓣网（以下简称“豆瓣”）根据以下服务条款为您提供服务。这些条款可由豆瓣随时更新，且毋须另行通知。豆瓣使用协议（以下简称“使用协议”）一旦发生变动，豆瓣将在网页上公布修改内容。修改后的使用协议一旦在网页上公布即有效代替原来的使用协议。此外，当您使用豆瓣特殊服务时，您和豆瓣应遵守豆瓣随时公布的与该服务相关的指引和规则。前述所有的指引和规则，均构成本使用协议的一部分。<br><br>
					    您在使用豆瓣提供的各项服务之前，应仔细阅读本使用协议。如您不同意本使用协议及/或随时对其的修改，请您立即停止使用豆瓣网所提供的全部服务；您一旦使用豆瓣服务，即视为您已了解并完全同意本使用协议各项内容，包括豆瓣对使用协议随时所做的任何修改，并成为豆瓣用户（以下简称“用户”）。<br>
					<span class="describe"><h3>2 服务说明</h3></span>
					    豆瓣目前向用户提供如下服务：发布并分享对图书、电影、音乐的评论；收藏图书、电影、音乐；在豆瓣中发布话题、日记、上传图片；在同城中发布或参加各类活动。除非本使用协议另有其它明示规定，增加或强化目前本服务的任何新功能，包括所推出的新产品，均受到本使用协议之规范。您了解并同意，本服务仅依其当前所呈现的状况提供，对于任何用户信息或个人化设定之时效、删除、传递错误、未予储存或其它任何问题，豆瓣均不承担任何责任。豆瓣保留不经事先通知为维修保养、升级或其它目的暂停本服务任何部分的权利。<br>
					<span class="describe"><h3>3 遵守法律</h3></span>
						您同意遵守中华人民共和国相关法律法规的所有规定，并对以任何方式使用您的密码和您的帐号使用本服务的任何行为及其结果承担全部责任。如您的行为违反国家法律和法规的任何规定，有可能构成犯罪的，将被追究刑事责任，并由您承担全部法律责任。<br><br>
						同时如果豆瓣有理由认为您的任何行为，包括但不限于您的任何言论和其它行为违反或可能违反国家法律和法规的任何规定，豆瓣可在任何时候不经任何事先通知终止向您提供服务。<br>
					<span class="describe"><h3>4 您的注册义务</h3></span>
						为了能使用本服务，您同意以下事项：依本服务注册提示请您填写正确的注册邮箱、密码和名号，并确保今后更新的登录邮箱、名号、头像等资料的有效性和合法性。若您提供任何违法、不道德或豆瓣认为不适合在豆瓣上展示的资料；或者豆瓣有理由怀疑你的资料属于程序或恶意操作，豆瓣有权暂停或终止您的帐号，并拒绝您于现在和未来使用本服务之全部或任何部分。<br><br>
						豆瓣无须对任何用户的任何登记资料承担任何责任，包括但不限于鉴别、核实任何登记资料的真实性、正确性、完整性、适用性及/或是否为最新资料的责任。<br>
					<span class="describe"><h3>5 用户帐号、密码及安全</h3></span>
						完成本服务的注册程序并成功注册之后，您可使用您的Email和密码，登录到您在豆瓣的帐号（下称“帐号”）。保护您的帐号安全，是您的责任。<br><br>
						您应对所有使用您的密码及帐号的活动负完全的责任。您同意：<br><br>
						1）您的豆瓣帐号遭到未获授权的使用，或者发生其它任何安全问题时，您将立即通知豆瓣；<br><br>
						2）如果您未保管好自己的帐号和密码，因此而产生的任何损失或损害，豆瓣无法也不承担任何责任；<br><br>
						3）每个用户都要对其帐号中的所有行为和事件负全责。如果您未保管好自己的帐号和密码而对您、豆瓣或第三方造成的损害，您将负全部责任。<br>
					<span class="describe"><h3>6 豆瓣隐私权政策</h3></span>
						您提供的登记资料及豆瓣保留的有关您的若干其它资料将受到中国有关隐私的法律和本公司《<a
					href="/about?policy=privacy">隐私声明</a>》之规范。<br>
					<span class="describe"><h3>7 提供者之责任</h3></span>
						根据有关法律法规，豆瓣在此郑重提请您注意，任何经由本服务而发布、上传的文字、资讯、资料、音乐、照片、图形、视讯、信息或其它资料（以下简称“内容
						”），无论系公开还是私下传送，均由内容提供者承担责任。豆瓣仅为用户提供内容存储空间，无法控制经由本服务传送之内容，因此不保证内容的正确性、完整性或品质。您已预知使用本服务时，可能会接触到令人不快、不适当或令人厌恶之内容。在任何情况下，豆瓣均不为任何内容负责，但豆瓣有权依法停止传输任何前述内容并采取相应行动，包括但不限于暂停用户使用本服务的全部或部分，保存有关记录，并向有关机关报告。<br>
						6）豆瓣对本使用协议享有最终解释权。<br><br><br><br>
				</div>
			</div>
		</div>
	</div>
	<!-- 使用协议end -->
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