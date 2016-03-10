<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>小书僮™智慧幼教管理平台</title>
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
	
	//使用帮助页面切换
	function changeClass(id){
		$("#navul li").each(function(){
			$(this).removeClass("active");
		});
		$("#"+id+"").addClass("active");
	}	
</script>
</head>
<body>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<img src="${base}/resources/console/images/login_logo2.png"
					alt="小书僮™智慧幼教管理平台" style="  height: 80px;width: 300px;" />
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
	
	<!-- 使用帮助start -->
	<section class="main" id="section_1">
	<aside> <nav style="position: fixed; top: 30%;right:5%;">
	<ul class="list" id="navul">
		<li class="single active" id="li1"><a href="#agrement1" onclick="changeClass('li1')">接受条款</a></li>
		<li class="single" id="li2"><a href="#agrement2" onclick="changeClass('li2')">服务说明</a></li>
		<li class="single" id="li3"><a href="#agrement3" onclick="changeClass('li3')">遵守法律</a></li>
		<li class="single" id="li4"><a href="#agrement4" onclick="changeClass('li4')">用户帐号、密码及安全</a></li>
		<li class="single" id="li5"><a href="#agrement5" onclick="changeClass('li5')">小书僮隐私权政策</a></li>
		<li class="single" id="li6"><a href="#agrement6" onclick="changeClass('li6')">提供者之责任</a></li>
	</ul>
	</nav> </aside>
	<!-- 使用协议start--->
	<div class="whole">
		<div class="article" id="div_3" >
			<div class="grid-16-8 clearfix">
				<div class="article" id="showAll">
				<div id="agrement1"> 
					<span class="describe"><h3>1 接受条款</h3></span>
					<p style="text-indent:2em;">小书僮智慧幼教管理平台网（以下简称“小书僮”）根据以下服务条款为您提供服务。这些条款可由小书僮随时更新，且毋须另行通知。小书僮使用协议（以下简称“使用协议”）一旦发生变动，小书僮将在网页上公布修改内容。修改后的使用协议一旦在网页上公布即有效代替原来的使用协议。此外，当您使用小书僮特殊服务时，您和小书僮应遵守小书僮随时公布的与该服务相关的指引和规则。前述所有的指引和规则，均构成本使用协议的一部分。</p>
					<p style="text-indent:2em;">您在使用小书僮提供的各项服务之前，应仔细阅读本使用协议。如您不同意本使用协议及/或随时对其的修改，请您立即停止使用小书僮所提供的全部服务；您一旦使用小书僮服务，即视为您已了解并完全同意本使用协议各项内容，包括小书僮对使用协议随时所做的任何修改，并成为小书僮用户（以下简称“用户”）。</p>
				</div>	
				<div  id="agrement2">
					<span class="describe"><h3>2 服务说明</h3></span>
					<p style="text-indent:2em;">小书僮目前向用户提供如下服务：管理用户所属幼儿园，包括校园校区、班级教师、学生家长信息列表；发布并推送新闻、公告、通知；发布或更新校园餐点、年级课程；管理学生、教师考勤情况。除非本使用协议另有其它明示规定，增加或强化目前本服务的任何新功能，包括所推出的新产品，均受到本使用协议之规范。您了解并同意，本服务仅依其当前所呈现的状况提供，对于任何用户信息或个人化设定之时效、删除、传递错误、未予储存或其它任何问题，小书僮均不承担任何责任。小书僮保留不经事先通知为维修保养、升级或其它目的暂停本服务任何部分的权利。</p>
				</div>	
				<div id="agrement3">
					<span class="describe"><h3>3 遵守法律</h3></span>
					<p style="text-indent:2em;">您同意遵守中华人民共和国相关法律法规的所有规定，并对以任何方式使用您的密码和您的帐号使用本服务的任何行为及其结果承担全部责任。如您的行为违反国家法律和法规的任何规定，有可能构成犯罪的，将被追究刑事责任，并由您承担全部法律责任。</p>
					<p style="text-indent:2em;">同时如果小书僮有理由认为您的任何行为，包括但不限于您的任何言论和其它行为违反或可能违反国家法律和法规的任何规定，小书僮可在任何时候不经任何事先通知终止向您提供服务。</p>
				</div>	
				<div id="agrement4">
					<span class="describe"><h3>4 用户帐号、密码及安全</h3></span>
					<p style="text-indent:2em;">完成本服务的申请程序并成功获得账号之后，您可使用您的用户名和密码，登录到您在小书僮的帐号（下称“帐号”）。保护您的帐号安全，是您的责任。</p>
					<p style="text-indent:2em;">您应对所有使用您的密码及帐号的活动负完全的责任。您同意：</p>
					<p style="text-indent:2em;">1）您的小书僮帐号遭到未获授权的使用，或者发生其它任何安全问题时，您将立即通知小书僮；</p>
					<p style="text-indent:2em;">2）如果您未保管好自己的帐号和密码，因此而产生的任何损失或损害，小书僮无法也不承担任何责任；</p>
					<p style="text-indent:2em;">3）每个用户都要对其帐号中的所有行为和事件负全责。如果您未保管好自己的帐号和密码而对您、小书僮或第三方造成的损害，您将负全部责任。</p>
				</div>
				<div  id="agrement5">	
					<span class="describe"><h3>5 小书僮隐私权政策</h3></span>
					<p style="text-indent:2em;">您提供的登记资料及小书僮保留的有关您的若干其它资料将受到中国有关隐私的法律和本公司《隐私声明》之规范。</p>
				</div>	
				<div  id="agrement6">	
					<span class="describe"><h3>6 提供者之责任</h3></span>
					<p style="text-indent:2em;">根据有关法律法规，小书僮在此郑重提请您注意，任何经由本服务而发布、上传的文字、资讯、资料、音乐、照片、图形、视讯、信息或其它资料（以下简称“内容 ”），无论系公开还是私下传送，均由内容提供者承担责任。小书僮仅为用户提供内容存储空间，无法控制经由本服务传送之内容，因此不保证内容的正确性、完整性或品质。您已预知使用本服务时，可能会接触到令人不快、不适当或令人厌恶之内容。在任何情况下，小书僮均不为任何内容负责，但小书僮有权依法停止传输任何前述内容并采取相应行动，包括但不限于暂停用户使用本服务的全部或部分，保存有关记录，并向有关机关报告。</p>
				</div>
				<div  id="agrement7" style="margin-bottom:50px;">	
					<p style="text-indent:2em;">小书僮对本使用协议享有最终解释权。</p>
				</div>
						
				</div>
			</div>
		</div>
	</div>
	<!-- 使用协议end -->
	
	</section>
	<!-- 使用帮助end -->
	
	
	<div class="footer" style="position: relative;border:0;">
		<div class="wrap">
			<a href="${base}/front/link/useAgreement.ct">使用协议</a>
			<span> 福州盛云汇电子科技有限公司版权所有
				© 2015-2016&nbsp;&nbsp;闽ICP备15011832号</span>
		</div>
	</div>
</body>
</html>