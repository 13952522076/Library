<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.campusviewImg.edit")} -图书智能管理推荐系统</title>
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
	width:111px;
	padding:8px;
	border-radius:2px;
	border:1px solid #D6D6D6;
}
.newsPreview{
	width:93px;
	height:93px;
}
.imgShowBox{
	position:relative;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    description: {
		        required: true
		    },
			coverImage: {
				required: true,
				maxlength:255
			},
			summary: {
				required: true,
				maxlength:500
			},
			content: {
			    required: true
			}
		},
		messages: {
		}
	});
	
	<!--start上传预览图 -->
 	var $coverImageUpload = $("#coverImageUpload");
	var $coverImagePreview = $("#coverImagePreview");
	var $delCoverImage = $("#delCoverImage");
	var $coverImage = $("#coverImage");
    $coverImageUpload.uploadify({  
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
	            $coverImagePreview.attr("src",contentImgFileUrl);
	            $coverImage.val(contentImgFileUrl);
	            $delCoverImage.css('display','inline'); 
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
	<!--end上传预览图 -->
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$delCoverImage.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $coverImagePreview.attr("src","${base}/resources/console/images/announcementUpload.jpg");
	            $coverImage.val("");
	            $delCoverImage.css('display','none'); 
			}
		});
	});
	<!-- end  删除图片插件-->
	
	<!-- start 上传图集 -->
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
	            if(${classAlbumImage.classAlbumImageAttachs?size} > 0){
	                fileIndex = ${classAlbumImage.classAlbumImageAttachs?size} + file.index;
	            } else {
	                 fileIndex = file.index;
	            }
	            [@compress single_line = true]
					var imageAttachHtml = 
						'<a class="fancybox" href="'+ contentImgFileUrl +'" >';
					imageAttachHtml +='<img alt="image" src="'+ contentImgFileUrl +'" />';
					imageAttachHtml += '<input type="hidden" name="classAlbumImageAttachs['+fileIndex+'].imageAttach" value='+'"'+contentImgFileUrl+'"'+' \/>';
					imageAttachHtml += '<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>';
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
	<!-- end 上传图集 -->
	
});

function deleteCampusviewImageAttach(obj){
	var $this = $(obj);
	$.dialog({
		type: "warn",
		content: "${message("admin.dialog.deleteConfirm")}",
		onOk: function() {
			$this.prev().closest("a").remove();
			$this.closest("a").remove();
		}
	});
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
                    <h2> ${message("console.campusviewImg.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.campusviewImg.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start -->
	             <form id="inputForm" action="update.ct" method="post">
	                <input type="hidden" name="id" value="${classAlbumImage.id}"/>
	                <input type="hidden" name="status" value="${classAlbumImage.status}"/>
	                <input type="hidden" name="dictClassId" value="${classAlbumImage.dictClass.id}"/>
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
	                                     	<tr></tr>
	                                       	<tr>
	                                       		<th>
													<span class="requiredField">*</span>${message("相关班级")}:
												</th>
												<td>
													${classAlbumImage.dictClass.name}
												</td>
	                                       	</tr>
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("CampusviewImg.description")}:
												</th>
												<td>
													<input type="text" name="description" class="form-control"  value="${classAlbumImage.description}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("CampusviewImg.coverImage")}:
												</th>
												<td>
													<div class="itemList-upload">
														<div class="imgShowBox">
															[#if classAlbumImage.coverImage??]
														        <img src="${classAlbumImage.coverImage}" alt="上传预览图" id="coverImagePreview" class="newsPreview" />
														    [#else]
														        <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="coverImagePreview" class="newsPreview" />
														    [/#if]
															<a href="javascript:;" title="删除图片" [#if classAlbumImage.coverImage??] style="display:inline-block"[/#if]  class="delImg" id="delCoverImage">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="coverImageUpload" />
															<input type="hidden" id="coverImage" name="coverImage" value="${classAlbumImage.coverImage}">
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													${message("CampusviewImg.campusviewImageAttachs")}:
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
								                            [#list classAlbumImage.classAlbumImageAttachs as classAlbumImageAttach]
								                                <a class="fancybox" href="${classAlbumImageAttach.imageAttach}" />
								                                    <img alt="image" src="${classAlbumImageAttach.imageAttach}" />
								                                    <input type="hidden" name="classAlbumImageAttachs[${classAlbumImageAttach_index}].imageAttach"  class="text" value="${classAlbumImageAttach.imageAttach}">
							   										<input type="hidden" name="classAlbumImageAttachs[${classAlbumImageAttach_index}].id" class="text" value="${classAlbumImageAttach.id}">
							   										<a href="javascript:;" class="deleteCampusviewImageAttach" onclick="deleteCampusviewImageAttach(this)">${message("admin.common.delete")}</a>
								                                </a>
								                            [/#list]
							                            </div>
							                        </div>
							                    </div>
							                </div>
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
	             <!-- end -->
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
</body>
</html>