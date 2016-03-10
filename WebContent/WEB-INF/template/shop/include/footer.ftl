<div class="footer">
	<div class="share">
		<img src="${base}/resources/shop/images/icons/icon1.png" class="weibo" onclick="window.open('http://weibo.com/mocoparis')">
	    <img src="${base}/resources/shop/images/icons/icon2.png"  class="wechat" onclick="showWechat()">
	    <img src="${base}/resources/shop/images/icons/icon3.png"  class="video" onclick="window.open('http://i.youku.com/u/UNjE5NTYxNDQw')">
	    <img src="${base}/resources/shop/images/icons/icon4.png"  class="apps" onclick="window.location='${base}/info/appdownload.ct?id=51'">
	    <img src="${base}/resources/shop/images/icons/icon5.png"  class="email" onclick="window.location='${base}/info/contactus.ct?id=54'">
       <!-- <img src="${base}/resources/shop/images/icons/icon2.png"  class="guideCalendar" onclick="showGuideCalendar()"> -->
	</div>
	<div class="copyright">
	<!--
		<a id="validation" target="_blank" href="javascript:;">
		    ${message("shop.include.productAntiCounterfeitingValidation")}
		</a>
		-->
	    <a target="_blank" href="${base}/info/buyingguide.ct?id=56&param=howBuy">
		    ${message("shop.include.termService")}
		</a>
		<a target="_blank" href="${base}/info/buyingguide.ct?id=56&param=privacypolicy">
		    ${message("shop.include.privacyPolicy")}
		</a>
	    ${message("shop.include.copyRight")}
		<a target="_blank" href="http://www.miitbeian.gov.cn">
		    ${setting.certtext}
		</a>
	</div>
</div>

<div class="wechatdiv">
    <div class="qrcodeImage">
        <img src="${base}/resources/shop/images/qrcode.png" style="width:100px;height:100px;">
    </div>
	<div class="scan_text"><span>${message("shop.include.weChat.text1")}</span></div>
	<div class="scan_text"><span>${message("shop.include.weChat.text2")}</span></div>
</div>

<div class="guideCalendardiv" style="left:240px; height:150px;">
    <div class="qrcodeImage">
        <img src="${setting.guideCalendarImage}" style="width:100px;height:100px;">
    </div>
</div>
[#include "/shop/include/statistics.ftl" /]