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
	
	<!-- 使用帮助start -->
	<section class="main" id="section_1" style="padding-bottom:40px;">
	<aside> <nav style="position: fixed; top: 30%;left:5%;">
	<ul class="list" id="navul">
		<li class="single active" id="li1"><a href="#describe1" onclick="changeClass('li1')">公司介绍</a></li>
		<li class="single" id="li2"><a href="#describe2" onclick="changeClass('li2')">隐私政策</a></li>
		<li class="single" id="li3"><a href="#describe3" onclick="changeClass('li3')">责任承担声明</a></li>
	</ul>
	</nav> </aside>
	<div id="showAll" class="whole" style="margin-left:15%;  padding-right: 15%;">
		<div id="describe1">
			<div  class="describe"><h3>公司介绍</h3></div>
			<p style="text-indent:2em;">我们（福州盛云汇电子科技有限公司）专注于幼儿园智慧校园信息化建设，主要业务包括：幼儿园智慧校园软硬件的部署实施、培训等。</p>
	       	<p style="text-indent:2em;">我们主打产品——小书僮智慧幼教移动幼儿园平台软件采用J2EE架构，结合iOS、Android手机移动应用，为家长、学校建立了良好的沟通平台、信息获取平台，产品独创的二维码接送模块，解决了传统以来家长依靠IC卡刷卡接送孩子上学放学的便捷性问题，从此只需携带手机即可接送孩子，而且还可以实时获取到接送纪录信息。产品拥有强大灵活的后台管理功能、个性化模块添加功能、操作体验一流的移动端功能，为幼儿园学校赢得优异的口碑。</p>
	       	<p style="text-indent:2em;">我们秉承“为合作伙伴创造价值”的核心价值观，并以“诚实、宽容、创新、服务”为企业精神，通过自主创新和真诚合作为幼教行业创造价值。<p>
	       	<p style="text-indent:2em;">关于“为合作伙伴创造价值”</p>
	       	<p style="text-indent:2em;">我们认为客户、供应商、家长等一切和自身有合作关系的单位和个人都是自己的合作伙伴，并只有通过努力为合作伙伴创造价值，才能体现自身的价值并获得发展和成功。<br>
	       	<p style="text-indent:2em;">关于“诚实、宽容、创新、服务”</p>
	       	<p style="text-indent:2em;">我们认为诚信是一切合作的基础，宽容是解决问题的前提，创新是发展事业的利器，服务是创造价值的根本。</p>
		</div>
		<div id="describe2" >
			<div class="describe"><h3>隐私政策</h3></div>
			<p style="text-indent:2em;">小书僮智慧幼教管理平台网（以下简称“小书僮”）将严格保护用户个人隐私的安全。我们使用各种安全技术保护您在使用小书僮时所涉及的个人信息不被未经授权的访问、使用或泄漏，承诺不会将此类信息用作他途，不会未经用户允许，将此类信息出租或出售给任何第三方。但以下情况除外：</p>
			<p style="text-indent:2em;">1、事先获得用户的明确授权；</p>
			<p style="text-indent:2em;">2、用户同意公开其个人资料；</p>
			<p style="text-indent:2em;">3、根据法律的有关规定或者行政或司法机构的要求，向第三方或者行政、司法机构披露；</p>
			<p style="text-indent:2em;">4、用户违反了本产品其他使用规定；</p>
			<p style="text-indent:2em;">5、为维护社会公众的合法利益；</p>
			<p style="text-indent:2em;">6、其他非因小书僮原因导致的个人信息泄漏</p>
			
		</div>
		<div id="describe3">
			<div class="describe"><h3>责任承担声明</h3></div>
			<p style="text-indent:2em;">在适用法律允许的最大范围内，公司不提供任何其他类型的保证，不论是明示的或默示的，包括但不限于适销性、适用性、可靠性、准确性、完整性、无病毒以及无错误的任何默示保证和责任。</p>
			<p style="text-indent:2em;">在适用法律允许的最大范围内，公司并不担保提供的产品和服务一定能满足用户的要求，也不担保提供的产品和服务不会被中断，并且对产品和服务的及时性，安全性，出错发生，以及信息是否能准确、及时、顺利的传送均不作任何担保。</p>
			<p style="text-indent:2em;">在适用法律允许的最大范围内，公司不就因用户使用公司的产品和服务引起的，或在任何方面与公司的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求(包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何责任、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿)承担任何责任。</p>
			<p style="text-indent:2em;">凡以任何方式直接、间接使用本公司产品或接受服务者，视为自愿接受本声明的约束。</p>
		</div>
		
	</div>
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