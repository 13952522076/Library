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
	var $inputForm = $("#inputForm");
	/**添加正整数验证规则 */
	jQuery.validator.addMethod("positiveinteger", function(value, element) {
		var aint=parseInt(value);	
    	return aint>0==value;   
  	}, "请填写一个正整数"); 
	
	
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
	
	/** 验证表单*/
	$inputForm.validate({
		rules: {
		    name: {
		        required: true
		    },
		    count:{
		    	number:true,
		    	digits:true,
		    	min:0,
		        max:100
		    },
		    price:{
		    	number:true
		    }
			
		},
		messages: {
		}
	});
	
	
	<!--图片上传插件-->
	$("#imageUpload").uploadify({
		'successTimeout' : 50000,
        'height'        : 27, //按钮高度  
        'width'         : 80, // 按钮长度  
        'buttonText'    : '浏览',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/upload/uploadImage.ct',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//图片选择描述  
        'fileTypeExts'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式      
        'formData'      : {'token' : getCookie("token") },
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
            var dataJson = JSON.parse(data);
            var imageURL = dataJson['url'];
            var message = dataJson['message'];
            if(message == 'success'){
	            $("#cover_hidden").val(imageURL);
	            document.getElementById("bookSample").style.backgroundImage = 'url('+imageURL+')';
            }else{
            	alert("上传失败");
            }
        },
	    onComplete: function (event, queueID, fileObj, response, data) {
	           
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    }
	});
	
	
	
	
	<!--图片上传插件-->
	
	<!--监听表单变化-->
	$("#name, #author,#publishCompany").change(function(){
		var name = $("#name").val();
		var author = $("#author").val();
		var publishCompany = $("#publishCompany").val();
		
		$("#publishCompanyShow").html(publishCompany);
		$("#nameShow").html(name);
		$("#authorShow").html(author);
		
	});  
	
	
	
	
});



</script>
<style>
.self_form_div{
    border: 1px solid lightgray;
    border-radius: 10px;
    padding-top:20px;
    padding-bottom:10px;
}
.self_book_cover{
	background-image: url(${base}/resources/console/images/book_cover.png);
	/*background-size: cover;*/
	background-size: contain;
	background-repeat: no-repeat;
	/*background-attachment: fixed;*/
	height:500px;
}
.laydate_box, .laydate_box * {
    box-sizing:content-box;
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
	    <div class="col-md-8 col-md-offset-2 form-group self_form_div container">
		    <form id="inputForm" class="form-horizontal" action="save.ct" method="post">
		        <div class="row">
		        	<div  class="col-md-7" style="border-right: 1px solid lightgray">
		        		<div class="form-group">
                            <label class="col-sm-3 control-label">书名<span class="text-danger">*</span></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="name" name="name" maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">作者</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="author" name="author" maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出版日期</label>
                            <div class="col-sm-9">
                                <input type="text" id="publishDate" class="form-control layer-date" name="publishDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">入库日期</label>
                            <div class="col-sm-9">
                                <input type="text" id="putingDate" class="form-control layer-date" name="putingDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格（元）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出版社</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="publishCompany" name="publishCompany"  maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">简介</label>
                            <div class="col-sm-9">
                            	<textarea name="description"  class="form-control" rows="3" maxlength="50" placeholder="50个字以内"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">藏书数</label>
                            <div class="col-sm-9">
                                <input type="text" name="count" class="form-control" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">封面</label>
                            <div class="col-sm-9">
                            	<input type="hidden" id="cover_hidden" name="cover" >
                                <input type="file" id="imageUpload" />
                            </div>
                        </div>
		        	</div>
		        	<div id="rightDIV" class="col-md-5">
		        		<div class="row">
		        			<!--<img src="${base}/resources/console/images/book_cover.jpg" alt="..." class="img-thumbnail">-->
		        			<div id="bookSample" class="col-md-10 col-md-offset-1 self_book_cover">
		        				<center>
		        					<h2 id="nameShow" style="margin-top:30%;">书名</h2>
		        					<h3 id="authorShow" style="margin-top:10%;">作者</h3>
		        					<div id="publishCompanyShow" style="margin-top:10%;">出版社</div>
		        				</center>
		        			</div>
		        		</div>
		        	</div>
		        </div>
		        <div class="row">
                	<center>
                    	<input type="submit" value="保存" class="btn btn-success" style="width:15%;">
                	</center>
	        	</div>
			</form>
		</div>
	 	<!--主体内容 end  -->
    </div>
</div>
<script type="text/javascript" src="${base}/resources/console/laydate/laydate.js"></script>
<script>
laydate.skin('molv');
laydate({
    elem: '#publishDate', 
    event: 'focus',
    max: laydate.now(), 
    format: 'YYYY-MM-DD' 
});
laydate({
    elem: '#putingDate', 
    event: 'focus',
    max: laydate.now(), 
    format: 'YYYY-MM-DD'
});
</script>

</body>
</html>