<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]	
	
	/** Bootstrap 对jqueryvalidate样式支持 */
	$.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorClass: "m-b-none",
        validClass: "m-b-none"
    });
    
    
	$("#ratingForm").validate({
		rules: {
		    mark:{
		    	required:true
		    },
		    evaluation:{
		    	required:true
		    }
			
		},
		messages: {
			mark:{
		    	required: "别忘了给出评分哦"
		    },
		    evaluation:{
		    	required: "麻烦给出一个评价吧"
		    }
		}
	});
	
	
	
});
/** 打分亮星星 */
function ratingMark(num){
	var hearts = $("#heartsContainer a");
	hearts.attr("class", "fa fa-heart-o");
	hearts.slice(0,num).attr("class", "fa fa-heart");
	$("#mark").val(num);
	check();
}

function check(){
	var mark = $("#mark").val();
	if(mark==null || mark==""){
		$("#markError").html("别忘记评分哦");
		return false;
	}
	$("#markError").html("");
	return true;
}


/** 喜欢收藏按钮功能实现 */
function collect(){
	$.ajax({
		url: "collect.ct",
		type: "POST",
		data: {
		    bookId: ${book.id} 
			},
		dataType: "json",
		cache: false,
		async:false,
		success: function(message) {
			if(message == "save"){
				$("#collectLabel").html("已收藏");
				layer.msg('收藏成功');
			}
			if(message == "delete"){
				$("#collectLabel").html("喜欢收藏");
				layer.msg('取消收藏');
			}
		}
	});

}
</script>
<style>
.enjoy{
	color:darkred;
}
#heartsContainer{
	font-size: 20px;
}
#heartsContainer a{
    color: red;
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
                            <div class="row">
                            	<center>
                                    <a type="button" class="btn btn-warning" onclick="collect()">
                                        <i id="collectLabel" class="fa fa-heart-o">喜欢收藏</i> 
                                    </a>
                            	</center>
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
	                        <form id="ratingForm" class="form-horizontal m-t" onsubmit="return check()" action="rating.ct" method="post">
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">评分:</label>
	                                <div id="heartsContainer" class="col-sm-10">
	                                	<input type="hidden" name="bookId" value="${book.id}">
	                                    <a onclick="ratingMark(1)" class="fa fa-heart-o"></a> 
	                                    <a onclick="ratingMark(2)" class="fa fa-heart-o"></a> 
	                                    <a onclick="ratingMark(3)" class="fa fa-heart-o"></a> 
	                                    <a onclick="ratingMark(4)" class="fa fa-heart-o"></a> 
	                                    <a onclick="ratingMark(5)" class="fa fa-heart-o"></a> 
	                                    <input type="hidden" id="mark" name="mark">
                                     	<small id="markError" class="m-b-none" style="display:initial;font-size:15px;"></small>
	                                </div>
	                               
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label">评价:</label>
	                                <div class="col-sm-10">
	                                <textarea name="evaluation" class="form-control" rows="3" maxlength="50"></textarea>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <div class="col-sm-4 col-sm-offset-3">
	                                    <button class="btn btn-primary" type="submit" >提交</button>
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