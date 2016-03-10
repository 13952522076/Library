<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${message("console.main.title")} - 小书僮™智慧幼教管理平台</title>
    <meta name="keywords" content="小书僮™智慧幼教管理平台">
    <meta name="description" content="小书僮™智慧幼教管理平台">
    [#include "/console/include/resources.ftl" /]
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
	  width: 150px;
	  line-height: 25px;
	  padding: 5px 10px 5px 0px;
	  font-weight: normal;
	  white-space: nowrap;
	}
	.itemList-upload{
		left:650px;
		top:45px;
		width:340px;
		padding:8px;
		border-radius:2px;
		border:1px solid #D6D6D6;
	}
	.indexImage{
		width:320px;
		height:200px;
	}
	.imgShowBox{
		position:relative;
	}
	.tipDiv {
	  float: left;
	  margin-top: 10px;
	  color: #BCBCBC;
	}
	</style>
	<script type="text/javascript">
	$().ready(function() {
		
		var $inputForm = $("#inputForm");
		var $browserButton = $("input.browserButton");
		
		[@flash_message /]
		
		$browserButton.browser();
		
		
		$.validator.addMethod("compareLength", 
			function(value, element, param) {
				return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
			},
			"${message("console.setting.compareLength")}"
		);
		
		// 表单验证
		$inputForm.validate({
			rules: {
				siteName: {
					required:true,
					maxlength:50
				},
				cookiePath: "required"
			}
		});
		loadKindEditor('schoolBadgeEditor');
		loadKindEditor('sendWordEditor');
		loadKindEditor('schoolMottoEditor');
		loadKindEditor('schoolSongEditor');
		
		<!--start上传图片插件 -->
	 	var $indexImageUpload = $("#indexImageUpload");
		var $indexImagePreView = $("#indexImagePreView");
		var $delIndexImage = $("#delIndexImage");
		var $indexImage = $("#indexImage");
	    $indexImageUpload.uploadify({  
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
		    'fileTypeDesc'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//图片选择描述  
	        'fileTypeExts'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式      
	        'formData'      : {'token' : getCookie("token") },
	        //上传成功  
	        'onUploadSuccess' : function(file, data, response) {  
	            var dataJson = JSON.parse(data);
	            var contentImgFileUrl = dataJson['url'];
	            var messageContent = dataJson['message']['content'];
	            var messageType = dataJson['message']['type'];
	            if(messageType == 'success'){
		            $indexImagePreView.attr("src",contentImgFileUrl);
		            $indexImage.val(contentImgFileUrl);
		            $delIndexImage.css('display','inline'); 
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
		$delIndexImage.on("click", function() {
			var $this = $(this);
			$.dialog({
				type: "warn",
				content: "${message("admin.dialog.deleteConfirm")}",
				onOk: function() {
				    $indexImagePreView.attr("src","${base}/resources/console/images/160-90.jpg");
		            $indexImage.val("");
		            $delIndexImage.css('display','none'); 
				}
			});
		});
		<!-- end  删除图片插件-->
		
		
		
	});
	
	function showTab(id){
	    $("#" + id).css("visibility","visible");
	    $("#" + id).css("height","auto");
	}
	</script>
	<style>
	    .captchaTypes label{
	          margin-right: 10px;
	    }
	</style>
</head>
<body class="fixed-navigation">
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
                    <h2>${message("console.main.profile")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/main.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong> ${message("console.main.profile")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2"></div>
            </div>
            <!-- end 头部面包屑区域 -->
	       	
	        <!-- start 中间内容部分 -->
	        <div class="row border-bottom white-bg-bak dashboard-header mainContent">
	            <div class="panel blank-panel">
                            <div class="panel-heading">
                                <div class="panel-options">
                                    <ul class="nav nav-tabs">
                                    	<li class="active">
                                            <a data-toggle="tab"  href="tabs_panels.ct#indexImgTab">
                                                ${message("学校封面")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('introductionTab')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#introductionTab">
                                                ${message("console.profile.introduction")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('sendWordTab')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#sendWordTab">
                                                ${message("园长寄语")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('schoolBadgeTab')">
                                            <a data-toggle="tab" href="tabs_panels.html#schoolBadgeTab">
                                                ${message("console.profile.schoolBadge")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('schoolMottoTab')">
                                            <a data-toggle="tab" href="tabs_panels.html#schoolMottoTab">
                                                ${message("console.profile.schoolMotto")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('schoolSongTab')">
                                            <a data-toggle="tab" href="tabs_panels.html#schoolSongTab">
                                                ${message("console.profile.schoolSong")}
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <form id="inputForm" action="save.ct" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${profile.id}" />
                            <div class="panel-body">
                                <div class="tab-content">
                                	<div id="indexImgTab" class="tab-pane active">
                                        <table class="table table-striped">
											<tr>
												<th>
													校园封面:
												</th>
												<td>
													<div class="itemList-upload">
														<div class="imgShowBox">
														   [#if profile.indexImage??]
														       <img src="${profile.indexImage}" alt="上传封面预览图" id="indexImagePreView" class="indexImage" >
														   [#else]
														       <img src="${base}/resources/console/images/160-90.jpg" alt="上传新闻预览图" id="indexImagePreView" class="indexImage" >
														   [/#if]
															
															<a href="javascript:;" title="删除图片" class="delImg" id="delIndexImage">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 120px;">
															<input type="file" id="indexImageUpload" />
															<input type="hidden" id="indexImage" name="indexImage"  value="${profile.indexImage}">
														</div>
													</div>
													<div class="tipDiv" id="sizeTip">图片尺寸：640*400px，支持jpg和png格式，限制50k以内</div>
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="introductionTab" class="tab-pane active" style="  visibility: hidden;  height: 0px;">
                                        <table class="table table-striped">
											<tr>
												<th>
													${message("Profile.introduction")}:
												</th>
												<td>
													<textarea name="introduction" id="editor" class="form-control">${profile.introduction?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="sendWordTab" class="tab-pane active" style="  visibility: hidden;  height: 0px;">
                                        <table class="table table-striped">
											<tr>
												<th>
													${message("园长寄语")}:
												</th>
												<td>
													<textarea name="sendWord" id="sendWordEditor" class="form-control">${profile.sendWord?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="schoolBadgeTab" class="tab-pane active" style="  visibility: hidden;  height: 0px;">
                                        <table class="table table-striped">
											<tr>
												<th>
													${message("Profile.schoolBadge")}:
												</th>
												<td>
													<textarea name="schoolBadge" id="schoolBadgeEditor" class="form-control">${profile.schoolBadge?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="schoolMottoTab" class="tab-pane active"  style="  visibility: hidden;  height: 0px;">
                                         <table class="table table-striped">
											<tr>
												<th>
													${message("Profile.schoolMotto")}:
												</th>
												<td>
													<textarea name="schoolMotto" id="schoolMottoEditor" class="form-control">${profile.schoolMotto?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                     <div id="schoolSongTab" class="tab-pane active"  style="  visibility: hidden;  height: 0px;">
                                         <table class="table table-striped">
											<tr>
												<th>
													${message("Profile.schoolSong")}:
												</th>
												<td>
													<textarea name="schoolSong" id="schoolSongEditor" class="form-control">${profile.schoolSong?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <input type="submit" class="btn btn-primary" value="${message("console.common.submit")}" />
                                    </div>
                                </div>
                              </form>
                            </div>
                        </div>
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
      </div>
    </div>
</body>
</html>