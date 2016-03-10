<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${message("console.main.title")} -图书智能管理推荐系统</title>
    <meta name="keywords" content="小书僮™智慧幼教管理平台">
    <meta name="description" content="小书僮™智慧幼教管理平台">
    [#include "/console/include/resources.ftl" /]
    <script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
    <script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/resources/console/editor/kindeditor.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
	<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
	<!-- Fancy box -->
	<link href="${base}/resources/console/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<script src="${base}/resources/console/js/plugins/fancybox/jquery.fancybox.js"></script>
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
				height: {
					number:true,
					min:1,
					max:300
				},
				weight:{
					number:true,
					min:1,
					max:300
				},
				leftVision:{
					maxlength:20
				},
				rightVision:{
					maxlength:20
				}
			}
		});
		loadKindEditor('schoolBadgeEditor');
		loadKindEditor('sendWordEditor');
		loadKindEditor('schoolMottoEditor');
		loadKindEditor('schoolSongEditor');
		
		<!-- start 上传学生作品 -->
		var $campusviewImageAttachsUpload = $("#campusviewImageAttachsUpload");
		var $campusviewImageAttachList = $("#campusviewImageAttachList");
		var fileIndex = 0;
	    $campusviewImageAttachsUpload.uploadify({  
	        'successTimeout' : 50000,
	        'height'        : 27,   
	        'width'         : 80,    
	        'buttonText'    : '上传图集',  
	        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
	        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
	        'auto'          : true,
	        'multi'          : true, //是否支持多文件上传
		    'simUploadLimit' : 20, //一次同步上传的文件数目
		    'sizeLimit'      : 19871202, //设置单个文件大小限制
		    'queueSizeLimit' : 20, //队列中同时存在的文件个数限制
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
		            if(${dictStudent.studentWorkss?size} > 0){
		                fileIndex = ${dictStudent.studentWorkss?size} + file.index;
		            } else {
		                 fileIndex = file.index;
		            }
		            [@compress single_line = true]
						var imageAttachHtml = 
							'<a class="fancybox" href="'+ contentImgFileUrl +'" >';
						imageAttachHtml +='<img alt="image" src="'+ contentImgFileUrl +'" />';
						imageAttachHtml += '<input type="hidden" name="studentWorkss['+fileIndex+'].imageAttach" value='+'"'+contentImgFileUrl+'"'+' \/>';
						imageAttachHtml += '<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>';
						imageAttachHtml += '<input  name="studentWorkss['+fileIndex+'].description" placeholder="描述" \/>';
					    imageAttachHtml += '<\/a>';
					[/@compress]
		             $campusviewImageAttachList.append(imageAttachHtml); 
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
		<!-- end 上传学生作品 -->
		
		<!-- start 上传全家福 -->
		var $familAlbumUpload = $("#familAlbumUpload");
		var $familAlbumList = $("#familAlbumList");
		var fileIndex = 0;
	    $familAlbumUpload.uploadify({  
	        'successTimeout' : 50000,
	        'height'        : 27,   
	        'width'         : 80,    
	        'buttonText'    : '上传图集',  
	        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
	        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
	        'auto'          : true,
	        'multi'          : true, //是否支持多文件上传
		    'simUploadLimit' : 20, //一次同步上传的文件数目
		    'sizeLimit'      : 19871202, //设置单个文件大小限制
		    'queueSizeLimit' : 20, //队列中同时存在的文件个数限制
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
		            if(${dictStudent.familAlbums?size} > 0){
		                fileIndex = ${dictStudent.familAlbums?size} + file.index;
		            } else {
		                 fileIndex = file.index;
		            }
		            [@compress single_line = true]
						var imageAttachHtml = 
							'<a class="fancybox" href="'+ contentImgFileUrl +'" >';
						imageAttachHtml +='<img alt="image" src="'+ contentImgFileUrl +'" />';
						imageAttachHtml += '<input type="hidden" name="familAlbums['+fileIndex+'].imageAttach" value='+'"'+contentImgFileUrl+'"'+' \/>';
						imageAttachHtml += '<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>';
						imageAttachHtml += '<input  name="familAlbums['+fileIndex+'].description" placeholder="描述" \/>';
					    imageAttachHtml += '<\/a>';
					[/@compress]
		             $familAlbumList.append(imageAttachHtml); 
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
		<!-- end 上传全家福 -->
		
		<!-- start 上传毕业合影 -->
		var $graduationPhotoUpload = $("#graduationPhotoUpload");
		var $graduationPhotoList = $("#graduationPhotoList");
		var fileIndex = 0;
	    $graduationPhotoUpload.uploadify({  
	        'successTimeout' : 50000,
	        'height'        : 27,   
	        'width'         : 80,    
	        'buttonText'    : '上传图集',  
	        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
	        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
	        'auto'          : true,
	        'multi'          : true, //是否支持多文件上传
		    'simUploadLimit' : 20, //一次同步上传的文件数目
		    'sizeLimit'      : 19871202, //设置单个文件大小限制
		    'queueSizeLimit' : 20, //队列中同时存在的文件个数限制
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
		            if(${dictStudent.dictClass.graduationPhotos?size} > 0){
		                fileIndex = ${dictStudent.dictClass.graduationPhotos?size} + file.index;
		            } else {
		                 fileIndex = file.index;
		            }
		            [@compress single_line = true]
						var imageAttachHtml = 
							'<a class="fancybox" href="'+ contentImgFileUrl +'" >';
						imageAttachHtml +='<img alt="image" src="'+ contentImgFileUrl +'" />';
						imageAttachHtml += '<input type="hidden" name="graduationPhotos['+fileIndex+'].imageAttach" value='+'"'+contentImgFileUrl+'"'+' \/>';
						imageAttachHtml += '<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>';
					    imageAttachHtml += '<\/a>';
					[/@compress]
		             $graduationPhotoList.append(imageAttachHtml); 
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
		<!-- end 上传毕业合影 -->
		
	});
	function deleteCampusviewImageAttach(obj){
		var $this = $(obj);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$this.next().remove();
				$this.prev().closest("a").remove();
				$this.closest("a").remove();
			}
		});
	}
	
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
                    <h2>${message("静态化毕业相册")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/dict_student/list.ct">${message("学生列表")}</a>
                        </li>
                        <li>
                            <strong>${dictStudent.studentName}${message("同学")}</strong>
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
                                            <a data-toggle="tab"  href="tabs_panels.ct#healthFileTab">
                                                ${message("健康档案")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('studentWorksTab')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#studentWorksTab">
                                                ${message("学生作品")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('familAlbumTab')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#familAlbumTab">
                                                ${message("全家福")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('graduationCertificateTab')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#graduationCertificateTab">
                                                ${message("毕业证书")}
                                            </a>
                                        </li>
                                        <li class="" onclick="showTab('graduationPhoto')">
                                            <a data-toggle="tab"  href="tabs_panels.ct#graduationPhoto">
                                                ${message("毕业合影")}
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <form id="inputForm" action="staticSave.ct" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="dictStudentId" value="${dictStudent.id}">
                            <div class="panel-body">
                                <div class="tab-content">
                                	<!--start健康档案-->
                            		<div id="healthFileTab" class="tab-pane active ibox-content" style="overflow: auto;">
                            			<input type="hidden" name="healthFileId" value="${healthFile.id}">
                                 		<div class="form-group" style="margin-bottom: 15px;overflow: auto;">
			                            	<label class="col-sm-3 control-label">身高(cm)：</label>
				                            <div class="col-sm-8">
				                                <input id="mobile" name="height" class="form-control" value="${healthFile.height}" autocomplete="off">
				                            </div>
				                        </div>
				                        <div class="form-group"  style="margin-bottom: 15px;overflow: auto;">
			                            	<label class="col-sm-3 control-label">体重(kg)：</label>
				                            <div class="col-sm-8">
				                                <input id="mobile" name="weight" class="form-control" value="${healthFile.weight}" autocomplete="off">
				                            </div>
				                        </div>
				                        <div class="form-group"  style="margin-bottom: 15px;overflow: auto;">
			                            	<label class="col-sm-3 control-label">左眼视力：</label>
				                            <div class="col-sm-8">
				                                <input id="mobile" name="leftVision" class="form-control" value="${healthFile.leftVision}" autocomplete="off">
				                            </div>
				                        </div>
				                        <div class="form-group"  style="margin-bottom: 15px;overflow: auto;">
			                            	<label class="col-sm-3 control-label">右眼视力：</label>
				                            <div class="col-sm-8">
				                                <input id="mobile" name="rightVision" class="form-control" value="${healthFile.rightVision}" autocomplete="off">
				                            </div>
				                        </div>
                                    </div>
                                   	<!--end  健康档案-->
                                   	<!--start学生作品-->
                                    <div id="studentWorksTab" class="tab-pane active ibox-content" style="  visibility: hidden;  height: 0px;overflow: auto;">
                                    	<tr></tr>
                                    	<tr>
												<th>
													${message("上传学生作品")}:
												</th>
												<td>
													<div class="uploadContainer" style="margin-left: 7px;">
														<input type="file" id="campusviewImageAttachsUpload" />
													</div>
												</td>
											</tr>
											<tr>
											  <td colspan="2">
											     <div class="col-lg-12">
							                        <div class="ibox float-e-margins">
							                            <div class="ibox-content" id="campusviewImageAttachList">
								                            [#list dictStudent.studentWorkss as studentWorks]
								                                <a class="fancybox" href="${studentWorks.imageAttach}" />
								                                    <img alt="image" src="${studentWorks.imageAttach}" />
								                                    <input type="hidden" name="studentWorkss[${studentWorks_index}].imageAttach"  class="text" value="${studentWorks.imageAttach}">
							   										<input type="hidden" name="studentWorkss[${studentWorks_index}].id" class="text" value="${studentWorks.id}">
							   										<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>
								                                	<input name="studentWorkss[${studentWorks_index}].description" value="${studentWorks.description}">
								                                </a>
								                            [/#list]
							                            </div>
							                        </div>
							                    </div>
											  </td>
											</tr>
                                    </div>
                                    <!--end  学生作品-->
                                    <!--start全家福-->
                                    <div id="familAlbumTab" class="tab-pane active ibox-content" style="  visibility: hidden;  height: 0px;overflow: auto;">
                                        <tr></tr>
                                    	<tr>
												<th>
													${message("上传全家福")}:
												</th>
												<td>
													<div class="uploadContainer" style="margin-left: 7px;">
														<input type="file" id="familAlbumUpload" />
													</div>
												</td>
											</tr>
											<tr>
											  <td colspan="2">
											     <div class="col-lg-12">
							                        <div class="ibox float-e-margins">
							                            <div class="ibox-content" id="familAlbumList">
								                            [#list dictStudent.familAlbums as familAlbum]
								                                <a class="fancybox" href="${familAlbum.imageAttach}" />
								                                    <img alt="image" src="${familAlbum.imageAttach}" />
								                                    <input type="hidden" name="familAlbums[${familAlbum_index}].imageAttach"  class="text" value="${familAlbum.imageAttach}">
							   										<input type="hidden" name="familAlbums[${familAlbum_index}].id" class="text" value="${familAlbum.id}">
							   										<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>
								                                	<input name="familAlbums[${familAlbum_index}].description" value="${familAlbum.description}">
								                                </a>
								                            [/#list]
							                            </div>
							                        </div>
							                    </div>
											  </td>
											</tr>
                                    </div>
                                    <!--end  全家福-->
                                    
                                    <!--start毕业证书-->
                            		<div id="graduationCertificateTab" class="tab-pane active ibox-content" style="visibility: hidden;  height: 0px;overflow: auto;">
                            			<input type="hidden" name="graduationCertificateId" value="${dictStudent.dictClass.graduationCertificate.id}">
                                 		<div class="form-group" style="margin-bottom: 15px;overflow: auto;">
			                            	<label class="col-sm-3 control-label">毕业时间：</label>
				                            <div class="col-sm-8">
				                                <input id="graduationDate" name="graduationDate" class="laydate-icon form-control layer-date" value="${dictStudent.dictClass.graduationCertificate.graduationDate}" autocomplete="off">
				                            </div>
				                        </div>
                                    </div>
                                   	<!--end  毕业证书-->
                                   	
                                     <!--start 毕业合影-->
                                    <div id="graduationPhoto" class="tab-pane active ibox-content" style="  visibility: hidden;  height: 0px;overflow: auto;">
                                        <tr></tr>
                                    	<tr>
												<th>
													${message("上传毕业合影")}:
												</th>
												<td>
													<div class="uploadContainer" style="margin-left: 7px;">
														<input type="file" id="graduationPhotoUpload" />
													</div>
												</td>
											</tr>
											<tr>
											  <td colspan="2">
											     <div class="col-lg-12">
							                        <div class="ibox float-e-margins">
							                            <div class="ibox-content" id="graduationPhotoList">
								                           [#list dictStudent.dictClass.graduationPhotos as graduationPhoto]
								                                <a class="fancybox" href="${graduationPhoto.imageAttach}" />
								                                    <img alt="image" src="${graduationPhoto.imageAttach}" />
								                                    <input type="hidden" name="graduationPhotos[${graduationPhoto_index}].imageAttach"  class="text" value="${graduationPhoto.imageAttach}">
							   										<input type="hidden" name="graduationPhotos[${graduationPhoto_index}].id" class="text" value="${graduationPhoto.id}">
							   										<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>
								                                </a>
								                            [/#list] 
							                            </div>
							                        </div>
							                    </div>
											  </td>
											</tr>
                                    </div>
                                    <!--end  毕业合影-->
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <input type="submit" class="btn btn-primary" value="${message("console.common.submit")}" />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
      </div>
    </div>
     <script>
        $(document).ready(function () {
            $('.fancybox').fancybox({
                openEffect: 'none',
                closeEffect: 'none'
            });
        });
    </script>
    <!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
        <script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js"></script>
        <script>
        //外部js调用
        laydate({
            elem: '#graduationDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        </script>
        <script>
		    var config = {
		        '.chosen-select': {},
		        '.chosen-select-deselect': {
		            allow_single_deselect: true
		        },
		        '.chosen-select-no-single': {
		            disable_search_threshold: 10
		        },
		        '.chosen-select-no-results': {
		            no_results_text: '无选择项'
		        },
		        '.chosen-select-width': {
		            width: "95%"
		        }
		    }
		    for (var selector in config) {
		        $(selector).chosen(config[selector]);
		    }
		    $(".chosen-select").trigger("chosen:updated");
		</script>
</body>
</html>