<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.app.add")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
.roles label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 6px;
}
table th {
  width: 120px;
  line-height: 25px;
  padding: 5px 10px 5px 0px;
  font-weight: normal;
  white-space: nowrap;
}
.itemList-upload{
	left:650px;
	top:45px;
	width:111px;
	padding:8px;
	border-radius:2px;
	border:1px solid #D6D6D6;
}
.logoAppImgView{
	width:93px;
	height:93px;
}

.appScreenshotView{
	width:96px;
	height:144px;
}
.imgShowBox{
	position:relative;
}
.left{
	float:left;
	width:35%;
}
.right{
	margin-left:37%;
}
</style>
<script type="text/javascript">

var selectImageNum = 0;//全局变量

$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    appName: {
		        required: true,
		        maxlength:20
		    },
			appDescription: {
				required: true,
				maxlength:1000
			},
			installUrl: {
				required: true,
				maxlength:500
			},
			logoAppImg: {
				required: true
			},
			openUrl: {
				required: true,
				maxlength:500
			},
			appSize: {
				required: true,
				maxlength:10,
				pattern:/^\d+(\.\d+){0,1}[KMGkmg]$/
				
			},
			versionName: {
				required: true,
				maxlength:50
			},
			versionCode: {
				required: true,
				maxlength:10,
				pattern:/^\d+(\.\d+){0,3}$/
			},
			appCode:{
				required: true,
				maxlength:100,
				remote: {
					url: "check_app_code.ct",
					cache: false
				},
				pattern:/^[A-Za-z.]*$/
			},
			appCategoryId:{
				required: true
			},
			appScreenshotsNum:{
				required:true
			},
			appAttachment:"required"
		},
		messages: {
			appCode: {
				pattern: "${message("console.app.appCodeIllegalPattern")}",
				remote: "${message("console.app.appCodeExist")}"
			}
		}
	});
	
	<!--start上传应用图标插件 -->
 	var $logoAppImgUpload = $("#logoAppImgUpload");
	var $logoAppImgView = $("#logoAppImgView");
	var $delLogoAppImg = $("#delLogoAppImg");
	var $logoAppImg = $("#logoAppImg");
    $logoAppImgUpload.uploadify({  
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
            		$logoAppImgView.attr("src",contentImgFileUrl);
		            $logoAppImg.val(contentImgFileUrl);
		            $delLogoAppImg.css('display','inline'); 
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
	<!--end上传应用图标插件 -->
	
	<!-- start删除应用图标插件-->
	// 删除封面图片
	$delLogoAppImg.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $logoAppImgView.attr("src","${base}/resources/console/images/appIconUpload.png");
	            $logoAppImg.val("");
	            $delLogoAppImg.css('display','none'); 
			}
		});
	});
	<!-- end删除应用图标插件-->
	
	/** 5个应用截图上传*/
	[#list 1..5 as index]
		// 上传
		$("#appScreenshotUpload${index}").uploadify({  
	        'successTimeout' : 50000,
	        'height'        : 27,   
	        'width'         : 80,    
	        'buttonText'    : '浏览',  
	        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
	        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
	        'auto'          : true,
	        'multi'          : true, //是否支持多文件上传  
		    'simUploadLimit' : 1, //一次同步上传的文件数目     
		    'fileSizeLimit'  : 50, //设置单个文件大小限制     
		    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
		    'fileObjName'    :  'file',
		    'fileTypeDesc'  :  '*.jpg;*.png',//图片选择描述  
	        'fileTypeExts'  :  '*.jpg;*.png',//允许的格式      
	        'formData'      : {
	        		'token' : getCookie("token"),
	        		'imageWidth':'320',
	        		'imageHeight':'480'
	         },
	        //上传成功  
	        'onUploadSuccess' : function(file, data, response) {  
	        	var index = "${index}";
	            var dataJson = JSON.parse(data);
	            var contentImgFileUrl = dataJson['url'];
	            var messageContent = dataJson['message']['content'];
	            var messageType = dataJson['message']['type'];
	            var sizeErrorMessageType = dataJson['sizeErrorMessage']['type'];//图片文件上传尺寸返回信息类型
	            var sizeErrorMessageContent = dataJson['sizeErrorMessage']['content'];//图片文件上传尺寸返回信息内容
	            if(messageType == 'success'){
	            	if(sizeErrorMessageType == 'success'){
	            		$("#screenshotUrl"+index).val(contentImgFileUrl);
			            $("#screenshot"+index).val(file.name);
			            $("#appScreenshotView"+index).attr("src",contentImgFileUrl);
			            $("#delAppScreenshot"+index).css('display','inline');
			            selectImageNum++; 
			            $("#appScreenshotsNum").val(selectImageNum);
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
		    }
	    });
	    
	    // 删除上传
		$("#delAppScreenshot${index}").on("click", function() {
			var $this = $(this);
			$.dialog({
				type: "warn",
				content: "${message("admin.dialog.deleteConfirm")}",
				onOk: function() {
				    $("#appScreenshotView${index}").attr("src","${base}/resources/console/images/appScreenshotUpload.png");
		            $("#screenshotUrl${index}").val("");
		            $("#screenshot${index}").val("");
		            $("#delAppScreenshot${index}").css('display','none'); 
		            selectImageNum--;
		            $("#appScreenshotsNum").val(selectImageNum);
		            if(selectImageNum == 0){
		            	$("#appScreenshotsNum").val("");
		            }
				}
			});
		});
	[/#list]
	
	<!-- start 上传应用 -->
	$("#appUpload").uploadify({
		'successTimeout' : 50000,
        'height'        : 34,   
        'width'         : 120, 
      	'buttonText'    : '选择上传',  
      	'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
      	'uploader'      : '${base}/console/file/upload.ct?fileType=file',  
      	'auto'          : true,
      	'multi'         : false, //是否支持多文件上传 
      	'simUploadLimit': 1, //一次同步上传的文件数目
      	'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
      	'fileObjName'    :  'file', 
      	'fileTypeDesc'  :  '*.zip',//选择描述  
        'fileTypeExts'  :  '*.zip',//允许的格式  
        'formData'      : {
        		'token' : getCookie("token"),
		},
		//上传成功  
        'onUploadSuccess' : function(file, data, response) { 
        	 var dataJson = JSON.parse(data);
        	 var appURL = dataJson['url'];
        	 var appFileName = file.name;
        	 var messageType = dataJson['message']['type'];
        	 var messageContent = dataJson['message']['content'];
        	 if(messageType == 'success'){
        	 	$("#appUpload").attr("display","none");
        	 	$("#appAttachment").val(appURL);
        	 	$("#appFileName").html(appFileName);
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
	<!-- end   上传应用 -->
	
	<!-- listen to the OperatingSystem radios change -->
	$("input:radio[name='operatingSystem']").change(function(){
		var operatingSystem = $("input[name='operatingSystem']:checked").val(); 
		if(operatingSystem == "hybird" || operatingSystem == "hbuilder"){
			$("#appAttachmentTR").show();
			$("#appAttachment").attr("name","appAttachment");	
		}
		else{
			$("#appAttachmentTR").hide();
			$("#appAttachment").removeAttr("name");
		}
		
		
	});
	
	
});

<!--删除上传的app文件-->
function deleteAppFile(){
	$("#appAttachment").val("");
 	$("#appFileName").html("");
}
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
                    <h2> ${message("console.app.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.app.add")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增应用-->
	             <form id="inputForm" action="save.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content" style="margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table class="table table-striped">
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("应用名称")}:
												</th>
												<td colspan="2">
													<div class="left">
														<input type="text" name="appName" class="form-control"/>
													</div>
													<div class="right">
														<label>应用名称不超过20个字符</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用简介")}:
												</th>
												<td style="width:60%;">
													<textarea name="appDescription" class="form-control" style="height: 80px;"></textarea>
												</td>
												<td>
													<label>应用简介字符不超过1000个字符</label>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用截图")}:
												</th>
												<td colspan="2">
													<div>
														<label>应用截图320*480.jpg\png格式，单张图片不超过50K</label>
													</div>
													<input type="hidden" id="appScreenshotsNum" name="appScreenshotsNum" value=""/>
													<div class="itemList-upload" style="float:left;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appScreenshotUpload.png" alt="" id="appScreenshotView1" class="appScreenshotView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delAppScreenshot1">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="appScreenshotUpload1" />
															<input type="hidden" id="screenshotUrl1" name="appScreenshots[0].screenshotUrl" value=""/>
															<input type="hidden" id="screenshot1" name="appScreenshots[0].screenshot" value=""/>
														</div>
													</div>
													<div class="itemList-upload" style="float:left;margin-left:15px;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appScreenshotUpload.png" alt="" id="appScreenshotView2" class="appScreenshotView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delAppScreenshot2">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="appScreenshotUpload2" />
															<input type="hidden" id="screenshotUrl2" name="appScreenshots[1].screenshotUrl" value=""/>
															<input type="hidden" id="screenshot2" name="appScreenshots[1].screenshot" value=""/>
														</div>
													</div>
													<div class="itemList-upload" style="float:left;margin-left:15px;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appScreenshotUpload.png" alt="" id="appScreenshotView3" class="appScreenshotView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delAppScreenshot3">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="appScreenshotUpload3" />
															<input type="hidden" id="screenshotUrl3" name="appScreenshots[2].screenshotUrl" value=""/>
															<input type="hidden" id="screenshot3" name="appScreenshots[2].screenshot" value=""/>
														</div>
													</div>
													<div class="itemList-upload" style="float:left;margin-left:15px;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appScreenshotUpload.png" alt="" id="appScreenshotView4" class="appScreenshotView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delAppScreenshot4">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="appScreenshotUpload4" />
															<input type="hidden" id="screenshotUrl4" name="appScreenshots[3].screenshotUrl" value=""/>
															<input type="hidden" id="screenshot4" name="appScreenshots[3].screenshot" value=""/>
														</div>
													</div>
													<div class="itemList-upload" style="float:left;margin-left:15px;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appScreenshotUpload.png" alt="" id="appScreenshotView5" class="appScreenshotView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delAppScreenshot5">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="appScreenshotUpload5" />
															<input type="hidden" id="screenshotUrl5" name="appScreenshots[4].screenshotUrl" value=""/>
															<input type="hidden" id="screenshot5" name="appScreenshots[4].screenshot" value=""/>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("操作系统")}:
												</th>
												<td colspan="2">
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline"  name="operatingSystem" value="android" checked="checked">
							                                ${message("Andriod")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline"  name="operatingSystem" value="ios">
							                                ${message("iOS")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline"  name="operatingSystem" value="hybird">
							                                ${message("Hybird(WEB混合)")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline"  name="operatingSystem" value="hbuilder">
							                                ${message("HBuilder")}
						                                </label>
						                            </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("运行方式")}:
												</th>
												<td colspan="2">
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="runType" value="build_in" checked="checked">
							                                ${message("内嵌运行")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="runType" value="thirdparty">
							                                ${message("第三方")}
						                                </label>
						                            </div>
												</td>
											</tr>
											<!-- start If Operating System is Hybird or HBuilder,show appAttachment tr -->
											<tr id="appAttachmentTR" style="display:none;">
												<th>
													<span class="requiredField">*</span>${message("应用上传")}:
												</th>
												<td colspan="2">
													<div class="uploadContainer" >
														<div style="float:left;" style="width:150px;">
															<input type="file" id="appUpload" />
															<label id="appFileName">原应用</label>
															<input type="hidden" id="appAttachment" value="">
														</div>
														<div style="float:left;margin-left:10px;">
															<input type="button" class="btn btn-danger" value="删除" onclick="deleteAppFile()">
														</div>
														<div style="float:left;margin-left:10px;margin-top:10px;">
															<label>支持上传zip格式的压缩包</label>
														</div>
													</div>
												</td>
											</tr>
											<!-- end   If Operating System is Hybird or HBuilder,show appAttachment tr -->
											<!--<tr>
												<th>
													<span class="requiredField">*</span>${message("上架方式")}:
												</th>
												<td>
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="runType" value="build_in" checked="checked">
							                                ${message("用户首页")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="runType" value="thirdparty">
							                                ${message("应用列表")}
						                                </label>
						                            </div>
												</td>
											</tr>-->
											<tr>
												<th>
													<span class="requiredField">*</span>${message("安装URL")}:
												</th>
												<td colspan="2">
													<div class="uploadContainer left" style="margin-left: 7px;">
														<input type="text" name="installUrl"  class="form-control"/>
													</div>
													<div class="right">
														<label>第三方应用的下载地址</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用图标")}:
												</th>
												<td colspan="2">
													<div class="itemList-upload" style="float:left;">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appIconUpload.png" alt="" id="logoAppImgView" class="logoAppImgView" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delLogoAppImg">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="logoAppImgUpload" />
															<input type="hidden" id="logoAppImg" name="logoAppImg" value="">
														</div>
													</div>
													<div style="margin-left:25%;">
														<label>支持大小为98*98px PNG图片，大小为不超过20K</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("打开URL")}:
												</th>
												<td style="text-align: left;" colspan="2">
													<div class="left" style="width:40%;">
														<input type="text" name="openUrl"  class="form-control"/>
													</div>
													<div style="margin-left:42%;">
														<label>打开应用的URL地址</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用大小")}:
												</th>
												<td colspan="2">
													<div class="left">
														<input type="text" name="appSize" class="form-control"/>
													</div>
													<div class="right">
														<label>如：56K，9.2M</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("版本名称")}:
												</th>
												<td colspan="2">
													<div style="width:35%;">
														<input type="text" name="versionName" class="form-control"/>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("版本号")}:
												</th>
												<td colspan="2">
													<div class="left">
														<input type="text" name="versionCode" class="form-control"/>
													</div>
													<div class="right">
														<label>如：1.0.1</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用标识")}:
												</th>
												<td colspan="2">
													<div class="left">
														<input type="text" name="appCode" class="form-control"/>
													</div>
													<div class="right">
														<label>英文名字，如：news,bus等</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													${message("关键词")}:
												</th>
												<td colspan="2">
													<div class="left">
														<input type="text" name="keyWord" class="form-control"/>
													</div>
													<div class="right">
														<label>多个关键词请以中文逗号隔开</label>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("应用分类")}:
												</th>
												<td colspan="2">
													<select name="appCategoryId" class="form-control" style="width:35%;">
														<option value="">${message("console.common.select")}</option>
														[#list appCategories as appCategory]
															<option value="${appCategory.id}" >
																${appCategory.name}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input type="submit" class="btn  btn-primary" value="${message("console.common.submit")}" />
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 新增应用-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>