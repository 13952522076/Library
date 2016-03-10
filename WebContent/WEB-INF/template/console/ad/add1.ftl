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
		   	adName:{
		   		required:true,
		   		maxlength:20
		   	},
		   	adCategoryId:"required",
		   	content:{
		   		required:true,
		   		maxlength:100
		   	},
		   	type:"required",
		   	appName:{
		   		maxlength:20
		   	},
		   	appAuthor:{
		   		maxlength:20
		   	},
		   	appDescription:{
		   		maxlength:50
		   	},
		   	adSite:{
		   		maxlength:255,
		   		url:true
		   	}
		   	
		},
		messages: {
		}
	});
	
	listenType();
	$("input:radio[name='type']").change(function(){
		listenType();
	});

});

function listenType(){
	 var type = $("input[name='type']:checked").val(); 
	 if(type == "app"){
	 	$("#appTypeTR").show();
	 	$("#websiteTypeTR").hide();
	 }else{
	 	$("#websiteTypeTR").show();
	 	$("#appTypeTR").hide();
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
                            <strong>${message("创建广告")}</strong>
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
	             <form id="inputForm" action="save1.ct" method="post">
	             	<input type="hidden" name = "isDraft" value = "false">
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
													<span class="requiredField">*</span>${message("广告名称")}:
												</th>
												<td>
													<input name="adName" class="form-control" />
													<small>该名称方便您在我们系统中识别该广告，手机用户是看不到这个名称的</small>
											    </td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("广告类别")}:
												</th>
												<td>
													<select data-placeholder="广告类别" name="adCategoryId" class="chosen-select form-control" tabindex="4">
														<option value=""></option>
														[#list adCategories as adCategory]
															<option value="${adCategory.id}" hassubinfo="true">${adCategory.name}</option>
														[/#list]
													<select>
											    </td>
											</tr>
											<tr>
												<th>
													${message("广告描述")}:
												</th>
												<td>
													<textarea class="form-control" rows="3" name="content"></textarea>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("广告类型")}:
												</th>
												<td>
													<div class="col-sm-9">
							                            <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="type" value="app" checked>
							                                ${message("应用推广")}
						                                </label>
						                                <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" name="type" value="website">
							                                ${message("网站推广")}
						                                </label>
						                            </div>
												</td>
											</tr>
											<tr id="appTypeTR">
												<th>&nbsp;</th>
												<td>
													<table class="table table-bordered">
														<tr>
															<th>应用平台</th>
															<td>
															 	<label class="radio-inline">
						                                			<input type="radio" class="checkbox checkbox-inline" name="platform" value="android" checked>
						                                			${message("Android")}
				                                				</label>
					                               			 	<label class="radio-inline">
						                                			<input type="radio" class="checkbox checkbox-inline" name="platform" value="ios">
							                                		${message("iOS")}
						                                		</label>
															</td>
														</tr>
														<tr>
															<th>应用地址</th>
															<td>
															 	<input name="adPackageUrl" class="form-control" />
															</td>
														</tr>
														<tr>
															<th>应用名称</th>
															<td>
															 	<input name="appName" class="form-control" />
															</td>
														</tr>
														<tr>
															<th>应用作者</th>
															<td>
															 	<input name="appAuthor" class="form-control" />
															</td>
														</tr>
														<tr>
															<th>应用描述</th>
															<td>
															 	<input name="appDescription" class="form-control" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="websiteTypeTR">
												<th>&nbsp;</th>
												<td>
													<table class="table table-bordered">
														<tr>
															<th>广告链接</th>
															<td><input name="adSite" class="form-control" /></td>
														</tr>
													</table>
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
													<input type="submit" class="btn  btn-primary" value="${message("下一步")}" />
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