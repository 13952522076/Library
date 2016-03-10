<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.appCategory.edit")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">

.itemList-upload{
	left:650px;
	top:45px;
	width:111px;
	padding:8px;
	border-radius:2px;
	border:1px solid #D6D6D6;
}
.logoAppCategoryImgView{
	width:93px;
	height:93px;
}

</style>
<script type="text/javascript">
$().ready(function() {
	
	var $inputForm = $("#inputForm");

	[@flash_message /]
	
	// 表单验证
	// 其中name的验证(可以输入数字英文和中文还有-_和空格,末端不可以有空格)
	$inputForm.validate({
		rules: {
			name:{
				required: true,
				pattern:/^[0-9a-zA-Z\u4e00-\u9fa5-_]([0-9a-zA-Z\u4e00-\u9fa5-_ ]*[0-9a-zA-Z\u4e00-\u9fa5-_])?$/,
				remote: {
					url: "check_name.ct?previousName=${appCategory.name?url}",
					cache: false
				},
				maxlength:50
			},
			description: {
				required: true,
				maxlength:100
			},
			appCategoryLogoUrl:{
				required:true
			}
		},
		messages: {
			name: {
				pattern: "${message("console.appCategory.nameIllegalPattern")}",
				remote: "${message("console.appCategory.nameExist")}"
			}
		}
	});
	
	<!--start上传应用分类图标插件 -->
 	var $logoAppCategoryImgUpload = $("#logoAppCategoryImgUpload");
	var $logoAppCategoryImgView = $("#logoAppCategoryImgView");
	var $delLogoAppCategoryImg = $("#delLogoAppCategoryImg");
	var $logoAppCategoryImg = $("#logoAppCategoryImg");
    $logoAppCategoryImgUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '浏览',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'fileSizeLimit' : 20, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.png',//图片选择描述  
        'fileTypeExts'  :  '*.png',//允许的格式      
        'formData'      : {
        		'token' : getCookie("token"),
        		'imageWidth':'98',
        		'imageHeight':'98'
         },
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
            var dataJson = JSON.parse(data);
            var contentImgFileUrl = dataJson['url'];
            var messageContent = dataJson['message']['content'];
            var messageType = dataJson['message']['type'];
            var sizeErrorMessageType = dataJson['sizeErrorMessage']['type'];//图片文件上传尺寸返回信息类型
            var sizeErrorMessageContent = dataJson['sizeErrorMessage']['content'];//图片文件上传尺寸返回信息内容
            if(messageType == 'success'){
            	if(sizeErrorMessageType == 'success'){
            		$logoAppCategoryImgView.attr("src",contentImgFileUrl);
		            $logoAppCategoryImg.val(contentImgFileUrl);
		            $delLogoAppCategoryImg.css('display','inline'); 
            	}
            	else{
            		$.message("warn", sizeErrorMessageContent);
            	}
            }else{
                $.message("warn", messageContent);
            }
        },
	    onComplete: function (event, queueID, fileObj, response, data) {
	           
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    },
	    onSelect: function(file){
	        //alert("文件:" + file.name);
	    }
    });
	<!--end上传应用分类图标插件 -->
	
	<!-- start删除应用分类图标插件-->
	// 删除封面图片
	$delLogoAppCategoryImg.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $logoAppCategoryImgView.attr("src","${base}/resources/console/images/appIconUpload.png");
	            $logoAppCategoryImg.val("");
	            $delLogoAppCategoryImg.css('display','none'); 
			}
		});
	});
	<!-- end删除应用分类图标插件-->

});
</script>
</head>
<body>
   <div id="wrapper">
		<!-- start  导航 -->
	    [#include "/console/include/nav.ftl" /]
	    <!-- end 导航-->
	    
	    <div id="page-wrapper" class="gray-bg dashbard-1">
	    	<!-- start 头部 -->
			[#include "/console/include/header.ftl" /]
			<!-- end 头部-->
			
			<!-- start 头部面包屑区域 -->
	        <div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2> ${message("console.appCategory.edit")} </h2>
	                <ol class="breadcrumb">
						<li>
						    <!--首页-->
							<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> 
	                    </li>
	                    <li>
	                        <strong>${message("console.appCategory.edit")}</strong>
	                    </li>
					</ol>
	            </div>
	            <div class="col-lg-2">
	
	            </div>
	        </div>
	       	<!-- end 头部面包屑区域 -->	
			<!-- start 中间内容部分 -->
			<div class="wrapper wrapper-content animated fadeIn">
				<div class="row">
			        <div class="col-lg-12">
			            <div class="ibox float-e-margins">
			                <div class="ibox-content" style="width:50%;margin:0 auto;">
			                    <form id="inputForm" action="update.ct" method="post" class="form-horizontal m-t">
			                        <input type="hidden" name="id" value="${appCategory.id}" />
			                        <input type="hidden" name="status" value="${appCategory.status?string('true','false')}" />
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("AppCategory.name")}：</label>
			                            <div class="col-sm-8">
			                                <input id="name" name="name" class="form-control" value="${appCategory.name}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("AppCategory.description")}：</label>
			                            <div class="col-sm-8">
			                                <input id="description" name="description" class="form-control" value="${appCategory.description}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("AppCategory.appCategoryLogoUrl")}：</label>
			                            <div class="col-sm-8">
			                                <div class="itemList-upload" style="float:left;">
												<div class="imgShowBox">
													[#if appCategory.appCategoryLogoUrl??]
												        <img src="${appCategory.appCategoryLogoUrl}" alt="" id="logoAppCategoryImgView" class="logoAppCategoryImgView" >
												    [#else]
												        <img src="${base}/resources/console/images/appIconUpload.png" alt="" id="logoAppCategoryImgView" class="logoAppCategoryImgView" >
												    [/#if]
												</div>
												<div class="uploadContainer" style="margin-left: 7px;">
													<input type="file" id="logoAppCategoryImgUpload" />
													<input type="hidden" id="logoAppCategoryImg" name="appCategoryLogoUrl" value="appCategory.appCategoryLogoUrl">
												</div>
											</div>
											<div style="margin-left:45%;">
												<label>支持大小为98*98px PNG图片，大小为不超过20K</label>
											</div>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <div class="col-sm-8 col-sm-offset-3">
			                               <input type="submit" class="btn  btn-primary" value="${message("console.common.confirm")}" />
										   <input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
			                            </div>
			                        </div>
			                    </form>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<!-- end 中间内容部分 -->
		   	[#include "/console/include/footer.ftl" /]
		</div>
	</div>
</body>
</html>