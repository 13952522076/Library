<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]

<link href="${base}/resources/console/css/templatemo-style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins.min.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/main.min.js"></script>

<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
	//下拉加载
	$(window).bind("scroll",function(){
		if($(document).scrollTop() + $(window).height() > $(document).height() - 100 && c != 0)// 接近底部100px
		{
			alert("test");
		}
	});
	
	
	
});
</script>

 <!-- Preloader -->
<script type="text/javascript">
    //<![CDATA[
    $(window).load(function() { // makes sure the whole site is loaded
        $('#loader').fadeOut(); // will first fade out the loading animation
        $('#loader-wrapper').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
        $('body').delay(350).css({'overflow-y':'visible'});
    })
    //]]>
</script>



</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
 	<div class="wrapper wrapper-content">
 		<!--主体内容 start-->
		<div id="loader-wrapper">
            <div id="loader"></div>
        </div>

        <div class="content-bg"></div>
        <div class="bg-overlay"></div>

        
        
        <!-- MAIN POSTS -->
        <div class="main-posts">
            <div class="container">
                <div class="row">
                    <div id="masonryDiv" class="blog-masonry masonry-true">
                    	[#list page.content as book]
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <img src="http://rescdn.qqmail.com/dyimg/20140630/7D3176BB2FD7.jpg" alt="">
                                <div class="title-over">
                                    <h4><a href="#">${book.name}</a></h4>
                                </div>
                                <div class="post-hover text-center">
                                    <div class="inside">
                                        <i class="fa fa-plus"></i>
                                        <span class="date">${book.author}</span>
                                        <p>${book.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- /.post-masonry -->
                        [/#list]
                        
                    </div>
                </div>
            </div>
        </div>
	 	
	 	<!--主体内容 end  -->
    </div>
</div>
</body>
</html>