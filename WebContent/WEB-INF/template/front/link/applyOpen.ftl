<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//二维码效果
	$(function() {
		$("#downloadApplication").click(function() {
			$(".wechatdiv").toggle();
			$(".wechatdivs").toggle();
		});
	});
	
	
</script>
</head>
<body>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<img src="${base}/resources/console/images/login_logo2.png"
					alt="图书智能管理推荐系统" style="  height: 80px;width: 300px;" />
				<!-- 二维码 start-->
				<div class="wechatdiv" style="display: none;">
					<div class="qrcodeImage">
						<img src="${base}/resources/console/images/ios.jpg"
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
						<img src="${base}/resources/console/images/android.jpg"
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
					<li><a href="${base}/console/">首页</a></li>
					<li><a href="${base}/front/link/applyOpen.ct">申请开通小书僮</a></li>
					<!-- onclick="javascript:Show_Hidden(this)" -->
					<li><a href="javascript:void(0)" id="downloadApplication">下载应用</a></li>
					<li class="noborder"><a href="${base}/front/link/help.ct" style="border-right: 0;">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 申请开通小书僮 start-->
	<div class="main">
		<div class="contact_bodyColor">
			<div class="case_title">
				<p class="title_CN">申请开通小书僮</p>
			</div>
			<div class="contact_form">
				<form>
					<div style="margin-top:50px;">
						<input type="text" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名" required="">
					</div>
					<div>
						<input type="text" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱/手机号码" required="">
					</div>
					<div>
						<textarea placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请描述您的需求，我们会在2个工作日之内与您取得联系"></textarea>
					</div>
					<div style="width:97%;">
						<input type="submit" class="contact_submit">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--  申请开通小书僮nd  -->
	<div class="footer" style="position: relative;border:0;">
		<div class="wrap">
			<a href="${base}/front/link/useAgreement.ct">使用协议</a>
			<span> 福州盛云汇电子科技有限公司版权所有
				© 2015-2016&nbsp;&nbsp;闽ICP备15011832号</span>
		</div>
	</div>
</body>
</html>