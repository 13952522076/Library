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
	$(window).scroll(function () {
	    var scrollTop = $(this).scrollTop();
	    var scrollHeight = $(document).height();
	    var windowHeight = $(this).height();
	    if (scrollTop + windowHeight == scrollHeight) {
			//ajax获取数据
			getBooks();
	    }
	});
	
	
	
});

//ajax获取book
function getBooks(){
	var pageNumber = $("#pageNumber").val();
	pageNumber++;
	$("#pageNumber").val(pageNumber);
	$.ajax({
		type: "GET",
		url: "ajaxList.ct",
		data: {
			pageNumber:pageNumber
		},
		dataType: "json",
		success:function(data){
			showBooks(data);
			//插入页面
		}
	});
}

//显示排版图书
function showBooks(data){
	if(data==null || data.length==0){
		alert("已到底");
		return;
	}
	
	var appendContent = '';
	for (i in data)
	{
		var iBook = data[i];
		appendContent+='<div class="post-masonry col-md-4 col-sm-6">';
		appendContent+='<div class="post-thumb">';
		appendContent+='<img src="http://rescdn.qqmail.com/dyimg/20140630/7D3176BB2FD7.jpg" alt="">';
		appendContent+='<div class="title-over">';
		appendContent+='<h4><a href="#">'+iBook.name+'</a></h4>';
		appendContent+='</div>';
		appendContent+='<div class="post-hover text-center">';
		appendContent+='<div class="inside">';
		appendContent+='<i class="fa fa-plus"></i>';
		appendContent+='<span class="date">'+iBook.author+'</span>';
		appendContent+='<p>'+iBook.description+'</p>';
		appendContent+='</div>';
		appendContent+='</div>';
		appendContent+='</div>';
		appendContent+='</div>';
	}
	//$("#masonryDiv").append(appendContent);
	$("#masonryDiv").append("haha");
}
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
 		<input type="hidden" id="pageNumber" value="1">
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