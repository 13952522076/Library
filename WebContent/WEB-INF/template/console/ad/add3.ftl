<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("新增广告")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
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
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		   adImage:"required"
		},
		messages: {
		}
	});
	
	
	[#if success ??]
		layer.msg("${success}",{
			time: 1000
		});
	[/#if]
	
	listenShowType();
	$("#showType").change(function(){
		listenShowType();
	});
	uploadAdImg();
	$("#adPosition").change(function(){
		uploadAdImg();
		deleteAdImg();
	});
	
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$("#deleteColumnCoverImage").on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
	            deleteAdImg();
			}
		});
	});
	<!-- end  删除图片插件-->

});

//上传图片方法
function uploadAdImg(){
	<!--start上传图片插件 -->
 	var $columnCoverImageUpload = $("#columnCoverImageUpload");
	var $showColumnCoverImage = $("#showColumnCoverImage");
	var $showColumnCoverImageHref = $("#showColumnCoverImageHref");
	var $deleteColumnCoverImage = $("#deleteColumnCoverImage");
	var $columnCoverImage = $("#columnCoverImage");
	var $showColumnCoverImageBr = $("#showColumnCoverImageBr");
	var width = "640";
	var height = "1136";
	var $adPosition = $("#adPosition");
	if($adPosition.val() == "start" || $adPosition.val() == null){
		width = '640';
		height = '1136';
	}
	else{
		width = '480';
		height = '320';
	}
	$("#sizeTip").html("请上传尺寸为"+width+"＊"+height+"的图片");
	
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
        'formData'      : {
        		'token' : getCookie("token"),
        		'imageWidth':width,
        		'imageHeight':height
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
		            $showColumnCoverImage.attr("src",contentImgFileUrl);
		            $showColumnCoverImageHref.attr("href",contentImgFileUrl);
		            $deleteColumnCoverImage.attr("val",contentImgFileUrl);
		            $columnCoverImage.val(contentImgFileUrl);
		            $("#columnCoverImageUpload").hide(100);
		            $showColumnCoverImage.show(100);
		            $showColumnCoverImageHref.show(100);
		            $deleteColumnCoverImage.show(100);
		            $showColumnCoverImageBr.show(100);
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
	<!--end上传图片插件 -->
}

//删除图片方法
function deleteAdImg(){
	$("#showColumnCoverImage").attr("src","");
    $("#showColumnCoverImageHref").attr("href","");
    $("#deleteColumnCoverImage").attr("val","");
    $("#columnCoverImage").val("");
    $("#columnCoverImageUpload").show(100);
    $("#showColumnCoverImage").hide(100);
    $("#showColumnCoverImageHref").hide(100);
    $("#deleteColumnCoverImage").hide(100);
    $("#showColumnCoverImageBr").hide(100);
}


function listenShowType(){
	var showType = $("#showType").val();
	if(showType == "pop"){
		$("#exampleImg").attr("src","${base}/resources/console/images/pop_example_img.png");
	}else{
		$("#exampleImg").attr("src","${base}/resources/console/images/inside_example_img.png");
	}
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
                    <h2> ${message("新增广告")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("广告形式")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 广告定向策略-->
	             <form id="inputForm" action="save3.ct" method="post">
	             	<input type="hidden" name="adId" value="${id}">
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
													<span class="requiredField">*</span>${message("广告形式")}:
												</th>
												<td>
												 	<div class="col-md-4">
													 	<select data-placeholder="广告位置" id="adPosition" name="adPosition" class="chosen-select form-control" tabindex="4">
															<option value="start">启动页</option>
															<option value="index">首页海报页</option>
															<option value="news">新闻页</option>
															<option value="gd">成长记</option>
														<select>
													</div>
											    </td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("广告图片")}:
												</th>
												<td>
													<div class="col-sm-4">
														<input type="file" id="columnCoverImageUpload" />
														<img style="display: none;" height="150px" id="showColumnCoverImage" />
														<br style="display: none;" id="showColumnCoverImageBr"/>
														<a href="#" style="display: none;" id="showColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
														<a href="javascript:;" style="display: none;" id="deleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
														<input type="hidden" id="columnCoverImage" name="adImage" class="form-control"/>
													</div>
													<div class="col-sm-4">
														<small id="sizeTip">请上传尺寸为640＊1136的图片</small>
													</div>
											    </td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("广告展现方式")}:
												</th>
												<td>
												 	<div class="col-md-4">
													 	<select id="showType" data-placeholder="广告展现方式" name="showType" class="chosen-select form-control" tabindex="4">
															<option value="pop">弹出</option>
															<option value="inside">当前界面打开</option>
														<select>
													</div>
												</td>
											</tr>
											<tr>
												<th>图例</th>
												<td>
													<div class="col-md-4">
														<img id="exampleImg" src="${base}/resources/console/images/pop_example_img.png" class="img-responsive" alt="Responsive image">
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
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
													<input type="submit" class="btn  btn-primary" value="${message("console.common.confirm")}" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 广告定向策略-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
<script>
    var config = {
    
        '.chosen-select': {
        	disable_search_threshold: 10
        },
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
</script>
</body>
</html>