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
		padding: 6% 0 0 0;
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
	    <div class="slide" id="slide1"><h1>Slide Backgrounds</h1></div>
	    <div class="slide" id="slide2"><h1>Totally customizable</h1></div>
	</div>
	<div class="section" id="section2"><h1>Lovely images <br />for a lovely page</h1></div>
	
</div>
<!--内容end  -->
  	

</body>
</html>