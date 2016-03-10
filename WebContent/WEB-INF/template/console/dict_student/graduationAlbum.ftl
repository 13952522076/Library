<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0">
<title>毕业纪念册</title>
<meta name="description" content="小书童毕业纪念册"/>
<link rel="image_src" type="image/jpeg" href="img/share_meilannote.jpg">
<link href="${base}/resources/console/hp/css/style.css" rel="stylesheet" />
<script src="${base}/resources/console/hp/js/flipsnap.js"></script>
<script type="text/javascript" src="${base}/resources/console/hp/js/jquery-2.1.1.min.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "${base}/resources/console/hp/js/hm.js";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body>
    <div class="front_load">
        <div class="loading loading_bg"></div>

        <div class="mx4_imgs loading loading_img">
            <div class="spinner">
                <div class="spinner-container container1">
                    <div class="circle1"></div>
                    <div class="circle2"></div>
                    <div class="circle3"></div>
                    <div class="circle4"></div>
                </div>
                <div class="spinner-container container2">
                    <div class="circle1"></div>
                    <div class="circle2"></div>
                    <div class="circle3"></div>
                    <div class="circle4"></div>
                </div>
                <div class="spinner-container container3">
                    <div class="circle1"></div>
                    <div class="circle2"></div>
                    <div class="circle3"></div>
                    <div class="circle4"></div>
                </div>
            </div>
        </div>
    </div>
    
        </div> 
        <div id="light" class="white_content"> 
            <img src="${base}/resources/console/hp/images/close.png" onClick="closeBrowseFunction()">   
            <div class="photoShowWrapper" id="photoShowWrapper">
                <div class="photoShow" id="photoShow">
                </div>
            </div>
            <script type="text/javascript">
            function openBrowseFunction(obj){
                var screenWidth = document.documentElement.clientWidth;
                document.getElementById("photoShow").style.width = $("." + obj).size()*screenWidth + 'px' ;
                var photoAttachHtml = "";
                $("." + obj).each(function(){
                    var imageUrl = $(this).val();
                     photoAttachHtml += '<div style="width:'+screenWidth+'px; max-height: 400px;height: auto;float: left; background-size: cover;background-repeat: no-repeat;">';
                     photoAttachHtml += '<img src="'+imageUrl+'" style="width:100%;height:auto;"/>';
                     photoAttachHtml += '</div>';
                     
                    document.getElementById('photoShow').innerHTML = photoAttachHtml;
                });
                
                document.getElementById('light').style.display='block';
                Flipsnap('.photoShow',{
                    distance:screenWidth    //每次移动的距离，移动次数为子元素个数减一；注意：此处不能有分号,可以有逗号
                })
            }

            function closeBrowseFunction(){
                document.getElementById('light').style.display='none';
                 var node = document.getElementById("photoShow");
                while(node.hasChildNodes()) //当div下还存在子节点时 循环继续
                {
                    node.removeChild(node.firstChild);
                }
            }
            </script> 
            <script type="text/javascript">  
                
            </script> 
        </div> 

    <div id="fullscreen" class="main">
        <!--**********************我的成长档案***********************-->
        <div id="p1" class="section" style="top:0%;background-image: url(${base}/resources/console/hp/images/album_1_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_1.png" /></div>
            <div class="baby_img">
                <img class="avater_img" src="${dictStudent.iconPhoto}"/>
            </div>
            <div class="baby_info">
                [#assign dictClass = dictStudent.dictClass /]
                [#assign dictSchool = dictClass.dictSchool /]
                [#assign profile = dictSchool.profile /]
                [#if dictSchool?has_content]
                    <p>${dictSchool.name}</p>
                [/#if]
                [#if dictClass?has_content]
                    <p>${dictClass.name}</p>
                [/#if]
                [#if dictStudent?has_content]
                    <p>${dictStudent.studentName}</p>
                [/#if]
            </div>
            <div class="section_child"></div>  
        </div>
        <!--**********************我的幼儿园***********************-->
        <div id="p2" class="section" style="top:100%;background-image: url(${base}/resources/console/hp/images/album_2_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_2.png" /></div>
            <div class="photoShowContainer" id="photoShowContainer" >
                [#if dictSchool.campusviewImgs?has_content]
                [#list dictSchool.campusviewImgs as campusviewImg]
                   <img class="sample" src="${campusviewImg.coverImage}">
                   [#break /]
                [/#list]
                [/#if]
                [#if dictSchool.campusviewImgs?has_content]
                [#list dictSchool.campusviewImgs as campusviewImg]
                   <input class = "campusviewImgs" type= "hidden" value="${campusviewImg.coverImage}">
                 [/#list]
                [/#if]
               <label class="browse_album" onclick="openBrowseFunction('campusviewImgs')">查看图集</label>
            </div>
            <div class="section_child"></div>  
        </div>
        
        <!--**********************幼儿园介绍***********************-->
        <div id="p3" class="section" style="top:200%;background-image: url(${base}/resources/console/hp/images/album_3_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_3.png" /></div>
            <div  id="content_p3" class="content" style="background-image: url(${base}/resources/console/hp/images/blue_frame.png);">
                <div style="position: absolute; width: 90%;height:90%;left:5%;top:5%;">
                [#if profile?has_content]
                   ${profile.introduction}
                [#else]
                   ${dictSchool.description}
                [/#if]
                </div>
            </div>
            <div class="section_child"></div>  
        </div> 
        <!--**********************园长寄语***********************-->
        <div id="p4" class="section" style="top:300%;background-image: url(${base}/resources/console/hp/images/album_4_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_4.png" /></div>
            <div class="content">
                ${profile.sendWord}
            </div>
            <div class="section_child"></div>  
        </div>
        <!--**********************毕业合影***********************-->
        <div id="p5" class="section" style="top:400%;background-image: url(${base}/resources/console/hp/images/album_5_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_5.png" /></div>
            <div class="photoShowContainer" id="photoShowContainer" >
                [#if dictClass.graduationPhotos?has_content]
                    <img class="sample" src="${dictClass.graduationPhotos[0].imageAttach}">
                [#list dictClass.graduationPhotos as graduationPhoto]
                   <input class = "graduationPhotos" type= "hidden" value="${graduationPhoto.imageAttach}">
                 [/#list]
                [/#if]
                 <label class="browse_album" onclick="openBrowseFunction('graduationPhotos')">查看图集</label> 
            </div>
            <div class="section_child"></div>    
        </div>
        <!--**********************我的老师***********************-->
        <div id="p6" class="section" style="top:500%;background-image: url(${base}/resources/console/hp/images/album_6_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_6.png" /></div>
            [#if dictClass.classTeacherMap?has_content]
               [#list dictClass.classTeacherMap as classTeacherMap]
                [#if classTeacherMap_index>2]
                [#break /]
                [/#if]
                [#if classTeacherMap.member?has_content]
                 [#assign member = classTeacherMap.member /]
                 [#if member.isAcceptLeaveInfo]
                     [#if member.iconPhoto?has_content]
                       <img id="img${classTeacherMap_index +1}" class="img${classTeacherMap_index +1}" src="${member.iconPhoto}" >
                     [#else]
                        <img id="img${classTeacherMap_index +1}" class="img${classTeacherMap_index +1}" src="${setting.defaultImage}" >
                     [/#if]
                 [/#if]
                [/#if]
              [/#list]
            [/#if]
            <div class="section_child"></div>  
        </div>
        <!--**********************班级活动***********************-->
        <div id="p7" class="section" style="top:600%;background-image: url(${base}/resources/console/hp/images/album_blue_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_7.png" /></div>
            <div class="photoShowContainer" id="photoShowContainer" >
            [#if dictClass.classAlbumImages?has_content]
                [#list dictClass.classAlbumImages as classAlbumImage]
	                [#if classAlbumImage.classAlbumImageAttachs?has_content]
	                  [#list classAlbumImage.classAlbumImageAttachs as classAlbumImageAttach]
                        <img class="sample" src="${classAlbumImageAttach.imageAttach}">
                        [#break /]
	                  [/#list]
	                [/#if]
                 [/#list]
                [/#if]
                [#if dictClass.classAlbumImages?has_content]
                [#list dictClass.classAlbumImages as classAlbumImage]
	                [#if classAlbumImage.classAlbumImageAttachs?has_content]
	                  [#list classAlbumImage.classAlbumImageAttachs as classAlbumImageAttach]
	                     <input class = "classAlbumImages" type= "hidden" value="${classAlbumImageAttach.imageAttach}">
	                  [/#list]
	                [/#if]
                 [/#list]
                [/#if]
                <label class="browse_album" onclick="openBrowseFunction('classAlbumImages')">查看图集</label>
            </div>
            <div class="girl" style="background-image: url(${base}/resources/console/hp/images/girl.png);"></div>
            <div class="section_child"></div>  
        </div>
        <!--**********************学生作品***********************-->
     <!--   <div id="p8" class="section" style="top:700%;background-image: url(${base}/resources/console/hp/images/album_blue_background.jpg)">
            <div class="fiction-img"><img src="${base}/resources/console/hp/images/fiction.gif" /></div>
            <div class="photoShowContainer" id="photoShowContainer" >
                [#if dictStudent.studentWorkss?has_content]
                 <img class="sample" src="${dictStudent.studentWorkss[0].imageAttach}">
                [#list dictStudent.studentWorkss as studentWorks]
	                <input class = "studentWorkss" type= "hidden" value="${studentWorks.imageAttach}">
                 [/#list]
                [/#if]
                <label class="browse_album" onclick="openBrowseFunction('studentWorkss')">查看图集</label>
            </div>
            <div class="showFiction" style="background-image: url(${base}/resources/console/hp/images/show_fiction.png)"></div>
            <div class="section_child"></div>  
        </div> --》
        <!--**********************同学录***********************-->
        <div id="p9" class="section" style="top:700%;background-image: url(${base}/resources/console/hp/images/album_blue_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_9.png" /></div>
           
            [#if dictClass.dictStudents?has_content]
               [#list dictClass.dictStudents as tempDictStudent]
                [#if (tempDictStudent_index>((dictClass.dictStudents?size)/2))]
                [#break /]
                [/#if]
                [#if tempDictStudent.studentName ="${dictStudent.studentName}" ]
                
                [#else]
                    [#if tempDictStudent.iconPhoto?has_content]
                      <input class = "tempDictStudents" type= "hidden" value="${tempDictStudent.iconPhoto}" title ="${tempDictStudent.studentName}" >
                    [#else]
                       <input class = "tempDictStudents" type= "hidden" value="${setting.defaultImage}" title ="${tempDictStudent.studentName}" >
                    [/#if]
                [/#if]
              [/#list]
            [/#if]
            <div id="friendContainer" class="friendContainer"></div>
            <script type="text/javascript">
            $().ready(function() {
                var avaterAttachHtml = "";
               $('.tempDictStudents').each(function(){
                    var imageUrl = $(this).val();
                    var title =  $(this).attr("title");
                    avaterAttachHtml += '<div class="friendInfoContainer" style="right:8px;position: relative;width:22%;height:15%; margin: 2px;display: inline-table;overflow: hidden;background-image:url'+'(' +imageUrl+')' +';background-size:100% auto;background-repeat:no-repeat;">';
                    avaterAttachHtml += '<label style="position: absolute; width: 100%;height: 30%;bottom: 0px;left: 0px; background-color: gray;color: white;text-align: center;font-size: 15px;font-weight: bolder;">'+title;
                    avaterAttachHtml += '</label>';
                    avaterAttachHtml += '</div>';
                });
                document.getElementById('friendContainer').innerHTML = avaterAttachHtml;
             });
            </script>
            <div class="section_child"></div> 
        </div>
        <!--**********************同学录***********************-->
        <div id="p9" class="section" style="top:800%;background-image: url(${base}/resources/console/hp/images/album_blue_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_9.png" /></div>
            [#if dictClass.dictStudents?has_content]
               [#list dictClass.dictStudents as tempDictStudent]
                [#if tempDictStudent_index<((dictClass.dictStudents?size)/2)]
                [#else]
                   [#if tempDictStudent.studentName ="${dictStudent.studentName}" ]
                [#else]
                    [#if tempDictStudent.iconPhoto?has_content]
                      <input class = "tempDictStudentss" type= "hidden" value="${tempDictStudent.iconPhoto}" title ="${tempDictStudent.studentName}">
                    [#else]
                       <input class = "tempDictStudentss" type= "hidden" value="${setting.defaultImage}" title ="${tempDictStudent.studentName}">
                    [/#if]
                [/#if] 
                [/#if]
              [/#list]
            [/#if]
            <div id="friendContainerCopy" class="friendContainer"></div>
            <script type="text/javascript">
            $().ready(function() {
                var avaterAttachHtml = "";
                 $('.tempDictStudentss').each(function(){
                    var imageUrl = $(this).val();
                    var title =  $(this).attr("title");
                    avaterAttachHtml += '<div class="friendInfoContainer" style="right:8px;position: relative;width:22%;height:15%; margin: 2px;display: inline-table;overflow: hidden;background-image:url'+'(' +imageUrl+')' +';background-size:100% auto;background-repeat:no-repeat;">';
                    avaterAttachHtml += '<label style="position: absolute; width: 100%;height: 30%;bottom: 0px;left: 0px; background-color: gray;color: white;text-align: center;font-size: 15px;font-weight: bolder;">'+title;
                    avaterAttachHtml += '</label>';
                    avaterAttachHtml += '</div>';
                 });
                document.getElementById('friendContainerCopy').innerHTML = avaterAttachHtml;
              });
            </script>
            <div class="section_child"></div> 
        </div>
        <!--**********************我的全家福***********************-->
        <div id="p10" class="section" style="top:900%;background-image: url(${base}/resources/console/hp/images/album_blue_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_10.png" /></div>
            <div class="photoShowContainer" id="photoShowContainer" >
                [#if dictStudent.familAlbums?has_content]
                     <img class="sample" src="${dictStudent.familAlbums[0].imageAttach}">
                [#list dictStudent.familAlbums as familAlbum]
	                <input class = "familAlbums" type= "hidden" value="${familAlbum.imageAttach}">
                 [/#list]
                [/#if]
                <label class="browse_album" onclick="openBrowseFunction('familAlbums')">查看图集</label>
            </div>
            <div class="red_plane" style="background-image: url(${base}/resources/console/hp/images/red_plane.png)"></div>
            <div class="yellow_plane" style="background-image: url(${base}/resources/console/hp/images/yellow_plane.png)"></div>
            <div class="blue_plane" style="background-image: url(${base}/resources/console/hp/images/blue_plane.png)"></div> 
            <div class="section_child"></div> 
        </div>
        <!--**********************综合评价***********************-->
        <div id="p11" class="section" style="top:1000%;background-image: url(${base}/resources/console/hp/images/album_11_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_11.png" /></div>
            <div class="allContainer" id="allContainer"></div>
            [#if dictStudent.overallMerits?has_content]
                [#list dictStudent.overallMerits as overallMerit]
	                <input class = "overallMerits" type= "hidden" value="${overallMerit.grade}" title = "${overallMerit.meritTemplate.meritName}">
                 [/#list]
                [/#if]
           <div class="section_child"></div> 
        </div>
        <!--**********************健康档案***********************-->
        <div id="p12" class="section" style="top:1100%;background-image: url(${base}/resources/console/hp/images/album_12_background.jpg)">
            <div class="banner"><img src="${base}/resources/console/hp/images/banner/banner_12.png" /></div>
            <div class="labelContainer">
                <label>我的身高(cm):<u>     ${dictStudent.healthFile.height}    </u>体重(kg)<u>    ${dictStudent.healthFile.weight}    </u></label>
                <label>视力(左):<u>    ${dictStudent.healthFile.leftVision}     </u>视力(右)<u>    ${dictStudent.healthFile.rightVision}    </u></label>
            </div>
            <div class="targetTitle" style="background-image: url(${base}/resources/console/hp/images/target_title.png)"></div>
            <div class="target" style="background-image: url(${base}/resources/console/hp/images/target.png)"></div>
            <div class="section_child"></div> 
        </div>
        <!--**********************毕业证书***********************-->
        <div id="p13" class="section" style="top:1200%;background-image: url(${base}/resources/console/hp/images/album_13_background.jpg)">
         <!--   <div class="graduate_container">
                <label class="headcontent">亲爱的<u>${dictStudent.studentName}</u>小朋友:</label>
                <p>时光流逝，愉快的幼儿园生活就要结束了，你从一个爱哭的娃娃变成了活泼可爱的孩子，你于[#if dictStudent.dictClass.graduationCertificate.graduationDate??]${dictStudent.dictClass.graduationCertificate.graduationDate?string('yyyy-MM-dd')} [#else][/#if]毕业于${dictSchool.name}。</p>
                <label class="end">特发此证，以示留念</label>
                <label class="end">院长:${dictSchool.kindergartenName}</label>
                <label class="end">2015年6月25日</label>
            </div> -->
            <div class="graduate_container" style="background-image: url(${base}/resources/console/hp/images/graduate_lice.jpg)">
                <div class="infoContainer">
                    <p><u>${dictStudent.studentName}</u>小朋友，性别<u>[#if dictStudent.gender=='male']男[/#if][#if dictStudent.gender=='female']女[/#if]</u>，[#if dictStudent.birthday??]${dictStudent.birthday?string('yyyy-MM-dd')} [#else][/#if]出生，于[#if dictStudent.dictClass.graduationCertificate.graduationDate??]${dictStudent.dictClass.graduationCertificate.graduationDate?string('yyyy-MM-dd')} [#else][/#if]毕业离园。</p>
                </div>
            </div>
            <div class="section_child"></div> 
        </div>
        <!--**********************结束语***********************-->
        <div id="p14" class="section" style="top:1300%;background-image: url(${base}/resources/console/hp/images/album_14_background.jpg)">
            <div style="position:absolute; background-image: url(${base}/resources/console/hp/images/logo.png);background-size:contain;width:18%;height:15%;right: 10%;background-repeat: no-repeat;top:30%"></div>
            <div class="wish">
                <label class="header">亲爱的小朋友:</label>
                <p class="content">祝愿你在小学校园里茁壮成长学习进步，成为一个有理想，懂礼貌的好学生，快乐地度过你金色美好的童年时代。</p>
            </div>
            <div class="end_bye">
                <label class="over_left">记录孩子成长</label>
                <label class="over_right">过程的点点滴滴</label>
                <label class="over_right">再见</label>
            </div>
            <div class="section_child"></div> 
        </div>
    </div>
    <div class="fullpage_option">
        <div class="next_screen on"><img src="${base}/resources/console/hp/img/arrow.png" /></div>
        <div class="mx4_music"><img class="music_active" src="${base}/resources/console/hp/img/music.png" /></div>
        <div style="display:none;"><audio autoplay src="${base}/resources/console/hp/mp3/071.mp3"></audio></div>
    </div>
    
</body>
<script type="text/javascript">
    function fullscreen(a){this.touchStartY=0;this.callback=a;this.win_H=document.body.clientHeight;this.now_H=0;this.isscrolling=true;this.now_screen=1}fullscreen.prototype.setscreen=function(a){this.now_screen=a;this.now_H=0};fullscreen.prototype.initialize=function(){this.addTouchHandler()};fullscreen.prototype.addTouchHandler=function(){var a=this;$(".section_child").on("touchstart",function(){a.touchStartHandler(arguments)});$(".section_child").on("touchmove",function(){a.touchMoveHandler(arguments)})};fullscreen.prototype.getEventsPage=function(b){var a=new Array();if(window.navigator.msPointerEnabled){a["y"]=b.pageY}else{if(b.originalEvent){a["y"]=b.originalEvent.touches[0].pageY}else{a["y"]=b.touches[0].pageY}}return a};fullscreen.prototype.touchStartHandler=function(b){var c=b[0];var a=this.getEventsPage(c);this.touchStartY=a["y"];a=null};fullscreen.prototype.touchMoveHandler=function(c){var f=c[0];f.preventDefault();var b=this.getEventsPage(f);touchEndY=b["y"];var g=document.body.clientHeight/100*1;var d=navigator.userAgent.match(/(iPhone|iPod|iPad)/);if(d){g=document.body.clientHeight/100*5}if(Math.abs(this.touchStartY-touchEndY)>g){if(this.touchStartY>touchEndY){var a=$("#fullscreen .section").length;if(this.now_screen==a){return false}this.changeScreen("down")}else{if(this.now_screen==1){return false}this.changeScreen("up")}this.touchStartY=touchEndY}};fullscreen.prototype.changeScreen=function(f){if(this.isscrolling){var d=this;var e=setTimeout(function(){d.isscrolling=true},1000);var a=$("#fullscreen .section").length;var b=$(".section").last().height();if(f=="down"){var c=this.now_screen+1==a?-b:-$(window).height();this.now_H+=c}else{if(f=="up"){var c=this.now_screen==a?b:$(window).height();this.now_H+=c}}this.now_screen+=f=="down"?1:-1;var g=navigator.userAgent.match(/(iPhone|iPod|iPad)/);if(g){$("#fullscreen").css({"-webkit-transition":"all 0.3s linear","-webkit-transform":"matrix(1, 0, 0, 1, 0, "+this.now_H+")"})}else{$("#fullscreen").css({"-webkit-transform":"translate3d(0px, "+this.now_H+"px, 0px)","-webkit-transform":"translate3d(0px, "+this.now_H+"px, 0px)"})}this.callback(this.now_screen)}this.isscrolling=false};
</script>
<script type="text/javascript">
    window.onload=function(){$(".front_load").hide();$("#p1 .baby_img").addClass("on");$("#p1 .baby_info").addClass("on")};
    ~function(){var c=0;var i=true;var f=new fullscreen(function(m){if(i){var n=document.getElementsByTagName("audio")[0];
    n.addEventListener("ended",function(){this.currentTime=0;$(".mx4_music img").removeClass("music_active");$(".mx4_music img").attr("src","${base}/resources/console/hp/img/music-stop.png")},false);
    document.getElementsByTagName("audio")[0].play();i=false;$(".mx4_music").on("touchstart",function(){var o=$(this).children("img");
    if(o.hasClass("music_active")){$("audio")[0].pause();
    o.attr("src","${base}/resources/console/hp/img/music-stop.png");
    o.removeClass("music_active")}else{o.addClass("music_active");$("audio")[0].play();
    o.attr("src","${base}/resources/console/hp/img/music.png")}})}$(".next_screen").addClass("on");
    switch(m){
        case 2:
        $("#p2 .sample").addClass("on");
        $("#p2 p").addClass("on");
        break;
        case 3:
        $("#p3 p").addClass("on");
        break;
        case 4:
        $("#p4 .mz_img").addClass("on");
        $("#p4 p").addClass("on");
        break;
        case 5:
        $("#p5 img").addClass("on");
        break;
        case 6:
        $("#p6 p").addClass("on");
        $("#p6 img").addClass("on");
        break;
        case 7:
        $("#p7 .mz_img").addClass("on");$("#p7 p").addClass("on");$("#p7 .girl").addClass("on");
        break;
        case 8:
        $("#p8 .showFiction").addClass("on");
        break;
        case 10:
        $("#p10 .red_plane").addClass("on");
        $("#p10 .yellow_plane").addClass("on");
        $("#p10 .blue_plane").addClass("on");
        break;
        case 11:
        
        break;
        case 12:
        
        break;
        case 13:
        $("#p13 .graduate_container .headcontent").addClass("on");$("#p13 p").addClass("on");$("#p13 .end").addClass("on");
        break;
        case 14:
        $("#p14 .end_bye").addClass("on");
        break
        }});f.initialize();}()
</script>
<!--综合评价动态添加-->
<script type="text/javascript">
$().ready(function() {
   var evaluateAttachHtml = "";
   var reg= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
	$('.overallMerits').each(function(){
       var originalData =  $(this).val();
       var title =  $(this).attr("title");
	   var isDecimals = reg.test(originalData);
	   parseInt(originalData);
	    evaluateAttachHtml += '<div class="evaluateContainer" id="evaluateContainer" style="position: relative;width: 90%;height: 7%;left: 5%;">';
	    if (isDecimals == false) {
	        for (var j = 0; j < parseInt(originalData); j++) {//表示星星个数
	            evaluateAttachHtml += '<img src="${base}/resources/console/hp/images/full_star.png" style="float:left; position: relative;width: 7%;height: auto;display: inline-table;"/>';
	        };
	    } else {
	        for (var j = 0; j < parseInt(originalData); j++) {//表示星星个数
	            evaluateAttachHtml += '<img src="${base}/resources/console/hp/images/full_star.png" style="float:left; position: relative;width: 7%;height: auto;display: inline-table;"/>';
	        };
	        evaluateAttachHtml += '<img src="${base}/resources/console/hp/images/half_star.png" style="float:left; position: relative;width: 7%;height: auto;display: inline-table;"/>';
	    };
	    
	    evaluateAttachHtml += '<p text="'+title+'" style="position: relative;width: 60%;height: 100%;font-size: 15px;float:right;text-align:left;">'+title;
	    evaluateAttachHtml += '</p>';
	    evaluateAttachHtml += '</div>';
	});
	document.getElementById("allContainer").innerHTML = evaluateAttachHtml;
});
</script>
<!-- WeixinApi -->
<script type="text/javascript">
(function(c){var d={version:4.2};c.WeixinApi=d;if(typeof define==="function"&&(define.amd||define.cmd)){if(define.amd){define(function(){return d})}else{if(define.cmd){define(function(f,e,g){g.exports=d})}}}var b=function(){var f={},j,g;for(var h=0,e=arguments.length;h<e;h++){j=arguments[h];if(typeof j==="object"){for(g in j){j[g]&&(f[g]=j[g])}}}return f};var a=function(i,h,g){g=g||{};var e=function(j){switch(true){case /\:cancel$/i.test(j.err_msg):g.cancel&&g.cancel(j);break;case /\:(confirm|ok)$/i.test(j.err_msg):g.confirm&&g.confirm(j);break;case /\:fail$/i.test(j.err_msg):default:g.fail&&g.fail(j);break}g.all&&g.all(j)};var f=function(k,j){if(i.menu=="menu:share:timeline"||(i.menu=="general:share"&&j.shareTo=="timeline")){k.title=k.timeline;k.desc=k.timeline}if(j&&(j.shareTo=="favorite"||j.scene=="favorite")){if(g.favorite===false){WeixinJSBridge.invoke("sendAppMessage",k,new Function())}else{WeixinJSBridge.invoke(i.action,k,e)}}else{if(i.menu==="general:share"){if(j.shareTo==="timeline"){WeixinJSBridge.invoke("shareTimeline",k,e)}else{if(j.shareTo==="friend"){WeixinJSBridge.invoke("sendAppMessage",k,e)}else{if(j.shareTo==="QQ"){WeixinJSBridge.invoke("shareQQ",k,e)}else{if(j.shareTo==="weibo"){WeixinJSBridge.invoke("shareWeibo",k,e)}}}}}else{WeixinJSBridge.invoke(i.action,k,e)}}};WeixinJSBridge.on(i.menu,function(k){g.dataLoaded=g.dataLoaded||new Function();if(g.async&&g.ready){d["_wx_loadedCb_"]=g.dataLoaded;if(d["_wx_loadedCb_"].toString().indexOf("_wx_loadedCb_")>0){d["_wx_loadedCb_"]=new Function()}g.dataLoaded=function(m){g.__cbkCalled=true;var l=b(h,m);l.img_url=l.imgUrl||l.img_url;delete l.imgUrl;d["_wx_loadedCb_"](l);f(l,k)};if(!(k&&(k.shareTo=="favorite"||k.scene=="favorite")&&g.favorite===false)){g.ready&&g.ready(k,h);if(!g.__cbkCalled){g.dataLoaded({});g.__cbkCalled=false}}}else{var j=b(h);if(!(k&&(k.shareTo=="favorite"||k.scene=="favorite")&&g.favorite===false)){g.ready&&g.ready(k,j)}f(j,k)}})};d.shareToTimeline=function(f,e){a({menu:"menu:share:timeline",action:"shareTimeline"},{"appid":f.appId?f.appId:"","img_url":f.imgUrl,"link":f.link,"desc":f.desc,"title":f.title,"timeline":f.timeline,"img_width":"640","img_height":"640"},e)};d.shareToFriend=function(f,e){a({menu:"menu:share:appmessage",action:"sendAppMessage"},{"appid":f.appId?f.appId:"","img_url":f.imgUrl,"link":f.link,"desc":f.desc,"title":f.title,"img_width":"640","img_height":"640"},e)};d.shareToWeibo=function(f,e){a({menu:"menu:share:weibo",action:"shareWeibo"},{"content":f.desc,"url":f.link},e)};d.generalShare=function(f,e){a({menu:"general:share"},{"appid":f.appId?f.appId:"","img_url":f.imgUrl,"link":f.link,"desc":f.desc,"title":f.title,"timeline":f.timeline,"img_width":"640","img_height":"640"},e)};d.disabledShare=function(e){e=e||function(){alert("当前页面禁止分享！")};["menu:share:timeline","menu:share:appmessage","menu:share:qq","menu:share:weibo","general:share"].forEach(function(f){WeixinJSBridge.on(f,function(){e();return false})})};d.addContact=function(e,f){f=f||{};WeixinJSBridge.invoke("addContact",{webtype:"1",username:e},function(h){var g=!h.err_msg||"add_contact:ok"==h.err_msg||"add_contact:added"==h.err_msg;if(g){f.success&&f.success(h)}else{f.fail&&f.fail(h)}})};d.imagePreview=function(e,f){if(!e||!f||f.length==0){return}WeixinJSBridge.invoke("imagePreview",{"current":e,"urls":f})};d.showOptionMenu=function(){WeixinJSBridge.call("showOptionMenu")};d.hideOptionMenu=function(){WeixinJSBridge.call("hideOptionMenu")};d.showToolbar=function(){WeixinJSBridge.call("showToolbar")};d.hideToolbar=function(){WeixinJSBridge.call("hideToolbar")};d.getNetworkType=function(e){if(e&&typeof e=="function"){WeixinJSBridge.invoke("getNetworkType",{},function(f){e(f.err_msg)})}};d.closeWindow=function(e){e=e||{};WeixinJSBridge.invoke("closeWindow",{},function(f){switch(f.err_msg){case"close_window:ok":e.success&&e.success(f);break;default:e.fail&&e.fail(f);break}})};d.ready=function(h){var f=function(){var i={};Object.keys(WeixinJSBridge).forEach(function(j){i[j]=WeixinJSBridge[j]});Object.keys(WeixinJSBridge).forEach(function(j){if(typeof WeixinJSBridge[j]==="function"){WeixinJSBridge[j]=function(){try{var k=arguments.length>0?arguments[0]:{},l=k.__params?k.__params.__runOn3rd_apis||[]:[];["menu:share:timeline","menu:share:appmessage","menu:share:weibo","menu:share:qq","general:share"].forEach(function(n){l.indexOf(n)===-1&&l.push(n)})}catch(m){}return i[j].apply(WeixinJSBridge,arguments)}}})};if(h&&typeof h=="function"){var e=this;var g=function(){f();h(e)};if(typeof c.WeixinJSBridge=="undefined"){if(document.addEventListener){document.addEventListener("WeixinJSBridgeReady",g,false)}else{if(document.attachEvent){document.attachEvent("WeixinJSBridgeReady",g);document.attachEvent("onWeixinJSBridgeReady",g)}}}else{g()}}};d.openInWeixin=function(){return/MicroMessenger/i.test(navigator.userAgent)};d.scanQRCode=function(e){e=e||{};WeixinJSBridge.invoke("scanQRCode",{needResult:e.needResult?1:0,desc:e.desc||"WeixinApi Desc",scanType:["qrCode","barCode"]},function(f){switch(f.err_msg){case"scanQRCode:ok":case"scan_qrcode:ok":e.success&&e.success(f);
break;default:e.fail&&e.fail(f);break}})};d.getInstallState=function(f,e){e=e||{};WeixinJSBridge.invoke("getInstallState",{"packageUrl":f.packageUrl||"","packageName":f.packageName||""},function(i){var h=i.err_msg,g=h.match(/state:yes_?(.*)$/);if(g){i.version=g[1]||"";e.success&&e.success(i)}else{e.fail&&e.fail(i)}e.all&&e.all(i)})};d.sendEmail=function(f,e){e=e||{};WeixinJSBridge.invoke("sendEmail",{"title":f.subject,"content":f.body},function(g){if(g.err_msg==="send_email:sent"){e.success&&e.success(g)}else{e.fail&&e.fail(g)}e.all&&e.all(g)})};d.enableDebugMode=function(e){c.onerror=function(i,g,f,h){if(typeof e==="function"){e({message:i,script:g,line:f,column:h})}else{var j=[];j.push("额，代码有错。。。");j.push("\n错误信息：",i);j.push("\n出错文件：",g);j.push("\n出错位置：",f+"行，"+h+"列");alert(j.join(""))}}}})(window);
</script>
<script type="text/javascript">
WeixinApi.ready(function(Api) {
    // 微信分享的数据
    var wxData = {
        "appId": "wx34ea076f3a9b252c", // 服务号可以填写appId
        "imgUrl" : 'http://weixin.res.meizu.com/meilannote/img/share_meilannote.jpg',
        "link" : 'http://weixin.res.meizu.com/meilannote/index.html',
        "timeline" : '魅族科技12月23日发布全新品牌魅蓝，魅蓝note16G版本999元。',
        "title" : '魅蓝note-青年良品',
        "desc" : "魅族科技12月23日发布全新品牌魅蓝，魅蓝note16G版本999元。"
    };

    // 分享的回调
    var wxCallbacks = {
        // 收藏操作是否触发回调，默认是开启的
        favorite : false
    };

    // 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
    Api.shareToFriend(wxData, wxCallbacks);

    // 点击分享到朋友圈，会执行下面这个代码
    Api.shareToTimeline(wxData, wxCallbacks);

    // 点击分享到腾讯微博，会执行下面这个代码
    Api.shareToWeibo(wxData, wxCallbacks);

    // iOS上，可以直接调用这个API进行分享，一句话搞定
    Api.generalShare(wxData,wxCallbacks);
});   

</script>
</html>