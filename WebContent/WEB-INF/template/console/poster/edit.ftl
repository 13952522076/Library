<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.poster.edit")} -图书智能管理推荐系统</title>
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
.newsPreview{
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
	var $posterContentTr = $("#posterContentTr");
	var $posterUrlTr = $("#posterUrlTr")
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    posterTitle: {
		        required: true,
				maxlength:100
		    },
			posterCover: {
				required: true
			},
			content: {
				required: $posterUrlTr.is(":hidden")
			},
			url: {
			    required: $posterContentTr.is(":hidden"),
				maxlength:500
			}
		},
		messages: {
		}
	});
	
	<!--start上传图片插件 -->
 	var $posterCoverUpload = $("#posterCoverUpload");
	var $posterCoverPreview = $("#posterCoverPreview");
	var $delPosterCover = $("#delPosterCover");
	var $posterCover = $("#posterCover");
    $posterCoverUpload.uploadify({  
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
	            $posterCoverPreview.attr("src",contentImgFileUrl);
	            $posterCover.val(contentImgFileUrl);
	            $delPosterCover.css('display','inline'); 
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
	$delPosterCover.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $posterCoverPreview.attr("src","${base}/resources/console/images/160-90.jpg");
	            $posterCover.val("");
	            $delPosterCover.css('display','none'); 
			}
		});
	});
	<!-- end  删除图片插件-->
	
	$("input[type=radio][name='posterType']").on('click',function(){
	    var $this = $(this);
	    if(this.value== 'CONTENT'){
	         $posterUrlTr.hide();
	         $posterContentTr.show();
	    }else if (this.value== 'LINK'){
	         $posterUrlTr.show();
	         $posterContentTr.hide();
	    }
	});
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
                    <h2> ${message("console.poster.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.poster.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增新闻-->
	             <form id="inputForm" action="update.ct" method="post">
	             	<input type="hidden" name="id" value="${poster.id}">
	             	<input type="hidden" name="status" value="${poster.status}">
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
													<span class="requiredField">*</span>${message("Poster.posterType")}:
												</th>
												<td>
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="posterType" value="CONTENT" [#if poster.posterType == "CONTENT"] checked="checked" [/#if] >
							                                ${message("Poster.posterType.CONTENT")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="posterType" value="LINK" [#if poster.posterType == "LINK"] checked="checked" [/#if]>
							                                ${message("Poster.posterType.LINK")}
						                                </label>
						                            </div>
												</td>
											</tr>
											 <tr>
												<th>
													${message("海报位置")}:
												</th>
												<td>
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="checkbox" class="checkbox checkbox-inline" name="posterPosition" value="HOMEPAGE" [#if poster.posterPosition == "HOMEPAGE"] checked="checked" [/#if] >
							                                ${message("首页")}
						                                </label>
						                            </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Poster.posterTitle")}:
												</th>
												<td>
													<input type="text" name="posterTitle" class="form-control"  value="${poster.posterTitle}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Poster.posterCover")}:
												</th>
												<td>
													<div class="itemList-upload">
														<div class="imgShowBox">
														   [#if poster.posterCover??]
														       <img src="${poster.posterCover}" alt="上传新闻预览图" id="posterCoverPreview" class="newsPreview" >
														   [#else]
														       <img src="${base}/resources/console/images/160-90.jpg" alt="上传新闻预览图" id="posterCoverPreview" class="newsPreview" >
														   [/#if]
															
															<a href="javascript:;" title="删除图片" class="delImg" id="delPosterCover">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 120px;">
															<input type="file" id="posterCoverUpload" />
															<input type="hidden" id="posterCover" name="posterCover"  value="${poster.posterCover}">
														</div>
													</div>
													<div class="tipDiv" id="sizeTip">图片尺寸：640*400px，支持jpg和png格式，限制50k以内</div>
												</td>
											</tr>
											<tr id="posterContentTr">
												<th>
													<span class="requiredField">*</span>${message("Poster.content")}:
												</th>
												<td style="text-align: left;">
													<textarea name="content" id="editor" class="form-control" >${poster.content?html}</textarea>
												</td>
											</tr>
											<tr id="posterUrlTr" style="display:none">
												<th>
													<span class="requiredField">*</span>${message("Poster.url")}:
												</th>
												<td>
													<input name="url" class="form-control" type="type" value="${poster.url}"/>
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
	             <!-- end 新增新闻-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>