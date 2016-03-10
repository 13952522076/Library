<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.qualityCourse.edit")} -图书智能管理推荐系统</title>
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
		    }
		},
		messages: {
		}
	});
	
	<!-- start 上传图集 -->
	var $qualityCourseAttachsUpload = $("#qualityCourseAttachsUpload");
	var $qualityCourseAttachList = $("#qualityCourseAttachList");
	var fileIndex = 0;
    $qualityCourseAttachsUpload.uploadify({  
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
	            if(${qualityCourse.qualityCourseImageAttachs?size} > 0){
	                fileIndex = ${qualityCourse.qualityCourseImageAttachs?size} + file.index;
	            } else {
	                 fileIndex = file.index;
	            }
	            [@compress single_line = true]
					var imageAttachHtml = 
						'<a class="fancybox" href="'+ contentImgFileUrl +'" >';
					imageAttachHtml +='<img alt="image" src="'+ contentImgFileUrl +'" />';
					imageAttachHtml += '<input type="hidden" name="qualityCourseImageAttachs['+fileIndex+'].imageAttach" value='+'"'+contentImgFileUrl+'"'+' \/>';
					
					imageAttachHtml += '<a href="javascript:;" class="deleteQualityCourseAttach" onclick="deleteQualityCourseAttach(this)">${message("admin.common.delete")}</a>';
				    imageAttachHtml += '<\/a>';
				[/@compress]
	             $qualityCourseAttachList.append(imageAttachHtml); 
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
	<!-- end 上传图集 //imageAttachHtml += '<textarea rows="3" cols="20" name="qualityCourseImageAttachs['+file.index+'].description" \/>';-->
	
});

function deleteQualityCourseAttach(obj){
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
                    <h2> ${message("console.qualityCourse.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.qualityCourse.edit")}</strong>
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
	                <input type="hidden" name="id" value="${qualityCourse.id}"/>
	                <input type="hidden" name="status" value="${qualityCourse.status}"/>
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
													<span class="requiredField">*</span>${message("QualityCourse.description")}:
												</th>
												<td>
													<input type="text" name="description" class="form-control"  value="${qualityCourse.description}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("QualityCourse.qualityCourseImageAttachs")}:
												</th>
												<td>
													<div class="uploadContainer" style="margin-left: 7px;">
														<input type="file" id="qualityCourseAttachsUpload" />
													</div>
												</td>
											</tr>
											<tr>
											  <td colspan="2">
											     <div class="col-lg-12">
							                        <div class="ibox float-e-margins">
							                            <div class="ibox-content" id="qualityCourseAttachList">
								                            [#list qualityCourse.qualityCourseImageAttachs as qualityCourseImageAttach]
								                                <a class="fancybox" href="${qualityCourseImageAttach.imageAttach}" />
								                                    <img alt="image" src="${qualityCourseImageAttach.imageAttach}" />
								                                    <!--<textarea rows="3" cols="20" name="qualityCourseImageAttachs[${qualityCourseImageAttach_index}].description"></textarea>-->
								                                    <input type="hidden" name="qualityCourseImageAttachs[${qualityCourseImageAttach_index}].imageAttach"  class="text" value="${qualityCourseImageAttach.imageAttach}">
							   										<input type="hidden" name="qualityCourseImageAttachs[${qualityCourseImageAttach_index}].id" class="text" value="${qualityCourseImageAttach.id}">
							   										<a href="javascript:;" class="deleteQualityCourseAttach" onclick="deleteQualityCourseAttach(this)">${message("admin.common.delete")}</a>
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