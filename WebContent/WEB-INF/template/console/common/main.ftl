<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书智能管理推荐系统</title>
    <meta name="keywords" content="图书智能管理推荐系统">
    <meta name="description" content="图书智能管理推荐系统">
    [#include "/console/include/resources.ftl" /]
    <script type="text/javascript" src="${base}/resources/console/fullpage/jquery.fullPage.min.js"></script>
    <link href="${base}/resources/console/fullpage/jquery.fullPage.css" rel="stylesheet" type="text/css" />
    <style>
    h1{
		font-size: 5em;
		font-family: arial,helvetica;
		margin:0;
		padding:0;
	}
	h2{
		font-size: 2em;
		font-family: arial,helvetica;
	}
	.section{
		text-align:center;
	}
	.section{
		background-size: cover;
	}
	.slide{
		background-size: cover;
	}
	#section0{
		background-image: url(${base}/resources/console/images/book/book1.png);
		padding: 10% 0 0 0;
	}
	#section2{
		background-image: url(${base}/resources/console/images/book/book2.png);
		padding: 15% 0 0 0;
	}
	#slide1{
		background-image: url(${base}/resources/console/images/book/book3.png);
		padding: 6% 0 0 0;
	}
	#slide2{
		background-image: url(${base}/resources/console/images/book/book4.png);
		padding: 6% 0 0 0;
	}
    </style>
    
    
    
    
    <script type="text/javascript">
		$(document).ready(function() {
			$('#fullpage').fullpage({
				verticalCentered: false
			});
		});
	</script>
    
</head>
<body class="gray-bg">

<!-- start  导航 -->
[#include "/console/include/nav.ftl" /]
<!-- end 导航-->


<!--内容start-->
<div id="fullpage">
	<div class="section " id="section0">
		<div class="row">
			<h1>图书智能管理推荐系统</h1>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-5">
				<h2>Better mind,better life</h2>
			</div>
		</div>
		<div class="row" style="padding-top:10%;text-align:left;">	
			<div class="col-md-9 col-md-offset-3">		
				<h2>AUTHOR:马旭</h2>	
				<h2>EMAIL:maxu@sencloud.com.cn</h2>	
				<h2>MOBILE:13952522076</h2>	
			</div>		
		</div>
		
		
	</div>
	<div class="section" id="section1">
	    <div class="slide" id="slide1">
	    	<h1>精准的推荐算法</h1>
	    	<div class="col-md-8 col-md-offset-4" style="padding-top:10%;text-align:left;">		
				<h2>IMDB Top 250————热门书籍</h2>
				<h2>关联规则————历史推荐</h2>
				<h2>协同过滤————智能推荐</h2>
				<h2>最近邻算法————相似同学</h2>
				<h2>。。。</h2>
			</div>	
	    </div>
	    <div class="slide" id="slide2">
	    	<h1>精准的推荐算法</h1>
	    	<div class="col-md-7 col-md-offset-4" style="padding-top:10%;text-align:left;">		
				<span style="font-size: 1.5em;">个性化推荐和非个性化推荐，让读书变得简单，兴趣斐然，实时更新的后台服务让系统更加了解每个用户。想您所想，用您所用。</span>
			</div>
    	</div>
	</div>
	<div class="section" id="section2">
		<h1>READING<br />Better mind,better life</h1>
	</div>
	
</div>
<!--内容end  -->
  	

</body>
</html>