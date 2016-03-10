<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.appPoster.add")} - 小书僮™智慧幼教管理平台</title>
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
	
	var selectImageNum = 0;//全局变量
	
	// 表单验证
	$inputForm.validate({
		rules: {
			posterName: {
				required: true,
				maxlength:20
			},
			operatingSystem: {
				required: true,
			},
			posterImg: {
			    required: true
			},
			gotoMethod:{
			    required: true
			},
			externalLink:{
				required: true,
				maxlength:20
			},
			appPosterNum:{
				required: true,
				maxlength:20
			}
		},
		messages: {
		}
	});
	
	//搜索App应用
    $("#searchApp").click(function(){
        var appName=$("#externalLink").val();
        $.ajax({
		type: "GET",
			url: "searchApp.ct",
			data: {
		   		appName:appName
			},
			dataType: "json",
			success:function(appList){
			    $("#appBody").html("");
				if(appList==null||appList.length==0){
					return;
				}
				else{
				    var content ="";
					for(num in appList){
					    var app=appList[num];
					    var appName=app.appName;
					    var appCategory=app.appCategory.name;
					    var operatingSystem = message("console.OperatingSystem."+app.operatingSystem);
						content+='<tr>'
									+'<td>'
										+'<input type="checkbox" name="appId" value="'+app.id+'" onClick="check(this)"/>'
									+'</td>'
									+'<td>'
										+appName
								    +'</td>'
									+'<td>'
										+appCategory
									+'</td>'
									+'<td>'
										+operatingSystem
									+'</td>'
								 +'</tr>';
					}
					$("#appBody").html(content);
				}
			}
		});
     });
     
	<!--start上传图片插件 -->
 	var $smallIconfileImageUpload = $("#smallIconfileImageUpload");
	var $smallIconfilePreview = $("#smallIconfilePreview");
	var $delSmallIconfileImg = $("#delSmallIconfileImg");
	var $smallIconfile = $("#smallIconfile");
    $smallIconfileImageUpload.uploadify({  
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
	    'fileTypeDesc'  :  '*.jpg;*.jpeg;*.png',//图片选择描述  
        'fileTypeExts'  :  '*.jpg;*.png',//允许的格式      
        'formData'      :  {
        		'token' : getCookie("token"),
        		'imageWidth':'640',
        		'imageHeight':'278'
         },
        'fileSizeLimit' :'50k',
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
            		 $smallIconfilePreview.attr("src",contentImgFileUrl);
	                 $smallIconfile.val(contentImgFileUrl);
	                 $delSmallIconfileImg.css('display','inline'); 
	                 selectImageNum++; 
	                 $("#appPosterNum").val(selectImageNum);
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
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$delSmallIconfileImg.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $smallIconfilePreview.attr("src","${base}/resources/console/images/newsUpload.png");
	            $smallIconfile.val("");
	            $delSmallIconfileImg.css('display','none'); 
	            selectImageNum--; 
	              $("#appPosterNum").val(selectImageNum);
		            if(selectImageNum == 0){
		            	$("#appPosterNum").val("");
		            }
			}
		});
	});
	<!-- end  删除图片插件-->
	
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
                    <h2> ${message("console.appPoster.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.appPoster.add")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增海报-->
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
													<span class="requiredField">*</span>${message("console.appPoster.title")}:
												</th>
												<td>
													<input type="text" name="posterName" class="form-control"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.appPoster")}:
												</th>
												<td>
												<input type="hidden" id="appPosterNum" name="appPosterNum" value=""/>
													<div class="itemList-upload">
														<div class="imgShowBox">
															<img src="${base}/resources/console/images/appPosterUpload.png" alt="上传海报" id="smallIconfilePreview" class="newsPreview" >
															<a href="javascript:;" title="删除图片" class="delImg" id="delSmallIconfileImg">×</a>
														</div>
														<div class="uploadContainer" style="margin-left: 7px;">
															<input type="file" id="smallIconfileImageUpload" />
															<input type="hidden" id="smallIconfile" name="posterImg" value="0">
														</div>
													</div>
													<div style="font-weight: inherit;padding-top: 7px;">${message("console.appPoster.imagText")}</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.app.terminal")}:
												</th>
												<td>
													<div style="padding: 5px 20px 5px 0px;">
			                                        	<input type="radio" value="android" name="operatingSystem" checked>
			                                        		android
			                                        	<input type="radio" value="ios" name="operatingSystem" style="margin-left:23px">
			                                        		ios
				                                    </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.appPoster.object")}:
												</th>
												<td>
													<select id="gotoMethod" name="gotoMethod" class="btn-white dropdown-toggle" style="height:30px;width:210px;margin-right:9px;">
                                            			<option value="link">${message("console.appPoster.externalLink")}</option>
                                            			<option value="app">${message("console.appPoster.appIntroduce")}</option>
                                            		</select>
												</td>
											</tr>
											
											 <!-- start 外链 -->
											 <tr id="showLink">
												<th>
													<span class="requiredField">*</span>
													[#if gotoMethod=="link"]
													 	<span id="txtLink">${message("console.appPoster.outLink")}<span>
													 [#else]
													 	<span id="txtLink">${message("console.appPoster.app")}<span>
													 [/#if]
												</th>
												
												<td>
													<input type="text" id="externalLink" name="externalLink" class="form-control" style="width:210px"/>
													<div id="linkEg" >例如：http://www.google.hk</div>
													<div id="searchBtn" style="margin-top:-45px;margin-left: 216px;display:none;">
														<input id="searchApp" type="button" class="btn  btn-primary" value="搜索" style="float: left;margin-top: 0px;"/>
													</div>
												</td>
											 </tr>
											 <!-- end 外链 -->
											 <!-- start 应用列表-->
	                                    		 <table  class="table table-striped" id="listTable"  style="display:none">
	                                    		 	<thead>
														<tr>
															<th class="check">
																<input type="checkbox" id="selectAll" />
															</th>
															<th>
																<a href="javascript:;" name="stateName">${message("console.appPoster.appName")}</a>
															</th>
															<th>
																<a href="javascript:;" name="titleCategory">${message("console.appPoster.catalog")}</a>
															</th>
															<th>
																<a href="javascript:;" name="posterImgSort">${message("console.appPoster.opSystem")}</a>
															</th>
												        </tr>
												     </thead>
												     <tbody id="appBody">
												     </tbody>
											     </table>
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input id="submit" type="submit" class="btn  btn-primary" value="${message("console.common.submit")}" />
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
<script type="text/javascript">
	$().ready(function() {
	
		$("#gotoMethod").change(function(){
	    	var option=$("#gotoMethod option:selected").attr("value");
			if(option!="link"){
				$("#txtLink").html("应用:");
				$("#searchBtn").css({'display':'block'});
				$("#submit").attr('disabled',true);
				$("#listTable").fadeIn();
				$("#linkEg").fadeOut();
			}else{
				$("#txtLink").html("外链:");
				$("#searchBtn").css({'display':'none'});
				$("#listTable").fadeOut();
				$("#submit").attr('disabled',false);
				$("#linkEg").fadeIn();
			}
	    });
 	});
 	
 	//应用列表 （只能选中一个行）
 	function check(obj) {
   		$('#appBody input').each(function () {
        	if (this != obj){
        		$(this).attr("checked", false);
        	}
        	else {
            	if ($(this).prop("checked")){
             		$(this).attr("checked", true);
             		$("#submit").attr('disabled',false);
            	}
           		else{
            		$(this).attr("checked", false);
            		$("#submit").attr('disabled',true);
            	}
        	}
     	});
   	}
</script>
</body>
</html>