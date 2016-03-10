<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.profile.edit")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	$.validator.addMethod("requiredTo", 
		function(value, element, param) {
			var parameterValue = $(param).val();
			if ($.trim(parameterValue) == "" || ($.trim(parameterValue) != "" && $.trim(value) != "")) {
				return true;
			} else {
				return false;
			}
		},
		"${message("console.profile.requiredTo")}"
	);
	
	// 表单验证
	$inputForm.validate({
		rules: {
			currentPassword: {
				requiredTo: "#password",
				remote: {
					url: "check_current_password.ct",
					cache: false
				}
			},
			password: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			password: {
				pattern: "${message("console.validate.illegal")}"
			}
		}
	});
	
	<!--start上传图片插件 -->
 	var $columnCoverImageUpload = $("#columnCoverImageUpload");
	var $showColumnCoverImage = $("#showColumnCoverImage");
	var $showColumnCoverImageHref = $("#showColumnCoverImageHref");
	var $deleteColumnCoverImage = $("#deleteColumnCoverImage");
	var $columnCoverImage = $("#columnCoverImage");
	var $showColumnCoverImageBr = $("#showColumnCoverImageBr");
    $columnCoverImageUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '浏览',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',       //图片选择描述  
        'fileTypeExts'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式      
        'formData'      : {'token' : getCookie("token") },
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
            var dataJson = JSON.parse(data);
            var contentImgFileUrl = dataJson['url'];
            var messageContent = dataJson['message']['content'];
            var messageType = dataJson['message']['type'];
            if(messageType == 'success'){
	            $showColumnCoverImage.attr("src",contentImgFileUrl);
	            $showColumnCoverImageHref.attr("href",contentImgFileUrl);
	            $deleteColumnCoverImage.attr("val",contentImgFileUrl);
	            $columnCoverImage.val(contentImgFileUrl);
	            $("#columnCoverImageUpload").hide(100);
	            $showColumnCoverImage.show(100);
	            $showColumnCoverImageHref.show(100);
	            $deleteColumnCoverImage.show(100);
	            $showColumnCoverImageBr.show(100);
            }else{
                $.message("warn", messageContent);
            }
        },
	    onComplete: function (event, queueID, fileObj, response, data) {
	           
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    }
    });
	<!--end上传图片插件 -->
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$deleteColumnCoverImage.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
	            $showColumnCoverImage.attr("src","");
	            $showColumnCoverImageHref.attr("href","");
	            $deleteColumnCoverImage.attr("val","");
	            $columnCoverImage.val("");
	            $("#columnCoverImageUpload").show(100);
	            $showColumnCoverImage.hide(100);
	            $showColumnCoverImageHref.hide(100);
	            $deleteColumnCoverImage.hide(100);
	            $showColumnCoverImageBr.hide(100);
			}
		});
	});
	<!-- end  删除图片插件-->
	
	<!-- start头像加载编辑 -->
	var src = $("#showColumnCoverImage").attr("src");
	if(src!=""){
		$("#columnCoverImageUpload").hide();
		$("#showColumnCoverImage").show();
		$("#showColumnCoverImageHref").show();
		$("#deleteColumnCoverImage").show();
		$("#showColumnCoverImageBr").show();
		$("#showColumnCoverImageHref").attr("href",src);
	}
	<!-- end头像加载编辑 -->
	
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
                    <h2>${message("console.profile.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.profile.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增管理员-->
	             <form id="inputForm" action="update.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table class="table table-striped">
											<tr>
												<th>
													${message("Admin.username")}: 
												</th>
												<td>
													${admin.username}
												</td>
											</tr>
											<tr>
												<th>
													${message("Admin.name")}: 
												</th>
												<td>
													${admin.name}
												</td>
											</tr>
											<tr>
												<th>
													${message("Admin.dictSchool")}: 
												</th>
												<td>
												    [#if admin.dictSchool??]
												        ${admin.dictSchool.name}
												    [#else]
												       -
												    [/#if]
												</td>
											</tr>
											<tr>
												<th>
													${message("console.profile.currentPassword")}: 
												</th>
												<td>
													<input type="password" name="currentPassword" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													${message("console.profile.password")}: 
												</th>
												<td>
													<input type="password" id="password" name="password" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													${message("console.profile.rePassword")}: 
												</th>
												<td>
													<input type="password" name="rePassword" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.email")}: 
												</th>
												<td>
													<input type="text" name="email" class="form-control" value="${admin.email}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.iconPhoto")}:
												</th>
												<td>
													<input type="file" id="columnCoverImageUpload" />
													<img src="${admin.iconPhoto}" style="display: none;" height="150px" id="showColumnCoverImage" />
													<br style="display: none;" id="showColumnCoverImageBr"/>
													<a href="#" style="display: none;" id="showColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
													<a href="javascript:;" style="display: none;" id="deleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
													<input type="hidden" id="columnCoverImage" name="iconPhoto" class="form-control" value="${admin.iconPhoto}"/>
												</td>
											</tr>
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<span class="tips">${message("console.profile.tips")}</span>
												</td>
											</tr>
											
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input type="submit" class="btn btn-primary" value="${message("console.common.submit")}" />
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='../common/index.ct'" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 新增管理员-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>