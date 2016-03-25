<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]	
	
});



</script>
<style>
.enjoy{
	color:darkred;
}
</style>


</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
 	<div class="wrapper wrapper-content">
 		<!--主体内容 start-->
	    <div class="row animated fadeInRight">
            <div class="col-sm-3 col-sm-offset-1">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>图书信息</h5>
                    </div>
                    <div>
                        <div class="ibox-content no-padding border-left-right">
                        	<center>
	                            
	                         		[#if book.cover?? && book.cover!=""]
	                        		<img alt="image" class="img-responsive" style="width:90%;" src="${book.cover}">
	                        		[#else]
	                        		<img alt="image" class="img-responsive" style="width:90%;" src="${base}/resources/console/images/book_cover.png">
                        			<h3>《${book.name}》</h3>
	                        		[/#if]
                        	</center>
                        </div>
                        <div class="ibox-content profile-content">
                            <h5>作者：<strong>${book.author}</strong></h5>
                            <p><i class="fa fa-bookmark"></i> ${book.publishCompany}</p>
                            <h5>简介</h5>
                            <p>${book.description}</p>
                            <div class="user-button">
                                <div class="row">
                                	<center>
                                        <a type="button" class="btn btn-warning">
	                                        <i class="fa fa-heart-o"></i> 
	                                        喜欢收藏
                                        </a>
                                	</center>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-7 form-group container">
            	<div class="row">
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>请用心评价这本书给别人参考哦～</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form class="form-horizontal m-t" id="commentForm">
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">评分:</label>
	                                <div class="col-sm-10">
	                                    <i class="fa fa-heart-o"></i> 
	                                    <i class="fa fa-heart-o"></i> 
	                                    <i class="fa fa-heart-o"></i> 
	                                    <i class="fa fa-heart-o"></i> 
	                                    <i class="fa fa-heart-o"></i> 
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">评价:</label>
	                                <div class="col-sm-10">
	                                <textarea  class="form-control" rows="3" maxlength="50"></textarea>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <div class="col-sm-4 col-sm-offset-3">
	                                    <button class="btn btn-primary" type="submit">提交</button>
	                                </div>
	                            </div>
	                        </form>
	                    </div>
	                </div>
            	
            	
            	</div>
        	</div>
    	</div>
	 	<!--主体内容 end  -->
    </div>
</div>
</body>
</html>