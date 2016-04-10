<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]	
});
</script>
<style>
</style>
</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
 	<div class="wrapper wrapper-content">
 		<!--主体内容 start-->
	    <div class="row">
	    	<!--start 评论最多-->
            <div class="col-sm-12">
            	<div class="ibox-title">
                    <h5>评论最多</h5>
                </div>
        	 	<div class="ibox-content">
        	 		<div class="row">
        	 			[#list mostMarks as mostMark]
        	 				<div class="col-sm-2">
        	 					<a href="${base}/console/book/detail.ct?id=${mostMark.key.id}">
	                        		<img alt="image" class="img-responsive" src="http://img2.imgtn.bdimg.com/it/u=2730523809,2825683527&fm=11&gp=0.jpg">
	                        	</a>	
	                        	<center>
	                        		《${mostMark.key.name!}》<br>
	                        		<h6><small>共${mostMark.value}条评论</small></h6>
                        		</center>
	                        </div>
        	 			[/#list]
                    </div>
            	 </div>
            </div>
            <!--end 评论最多-->
            
            <!--start 评分最高-->
            <div class="col-sm-12">
            	<div class="ibox-title">
                    <h5>评分最高</h5>
                </div>
        	 	<div class="ibox-content">
        	 		<div class="row">
        	 			[#list topMarks as topMark]
        	 				<div class="col-sm-2">
        	 					<a href="${base}/console/book/detail.ct?id=${topMark.key.id}">
	                        		<img alt="image" class="img-responsive" src="http://img2.imgtn.bdimg.com/it/u=2730523809,2825683527&fm=11&gp=0.jpg">
	                        	</a>
	                        	<center>
	                        		《${topMark.key.name!}》<br>
	                        		<h6><small>系统评分：${topMark.value}</small></h6>
                        		</center>
	                        </div>
        	 			[/#list]
                    	
                    </div>
            	 </div>
            </div>
            <!--end 评分最高-->
            
        </div>
	 	<!--主体内容 end  -->
    </div>
</div>
</body>
</html>