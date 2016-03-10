<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("API授权")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
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

input[type=text]{
	width:50%
}
.radioTD input{
	margin-right:5px;
}
.radioTD label{
	margin-right:10px;
}
.resetbtn{
	margin-top:0px;
	margin-left: 20px;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			appKey:"required",
			appSecret:"required",
			status:"required"
			
		},
		messages: {
		}
	});
	
});

function changeStatus(){
	var text = $("#appCredentialStatus").val();
	if(text == "false"){
		$("#statusText").val("启用");
		$("#appCredentialStatus").val("true");
		
		$("#statusBtn").val("禁用");
		$("#statusBtn").attr("class", "btn btn-warning resetbtn");
		
		
	}else{
		$("#statusText").val("禁用");
		$("#appCredentialStatus").val("false");
		
		$("#statusBtn").val("启用");
		$("#statusBtn").attr("class", "btn btn-success resetbtn");
	}
}

//生成appKey
function changeAppKey(){
	$.ajax({
		type:"GET",
		url:"changeAppKey.ct",
		success:function(data){
			 var dataJson = JSON.parse(data);  
			$("#appKey").val(dataJson.appKey);
			$("#appSecret").val(dataJson.appSecret);
		},
		
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
                    <h2> ${message("API授权")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/app/list.ct">${message("应用管理")}</a>
                        </li>
                        <li>
                            <strong>${message("API授权")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start API授权-->
	             <form id="inputForm" action="updateCredential.ct" method="post">
	                  <input type="hidden" id="appId" name="appId" value="${(app.id)}"/>
	                  <input type="hidden" name="id" value="${appCredential.id}">
	                  <input type="hidden" name="createDate" value="${appCredential.createDate}">
	                  <input type="hidden" name="modifyDate" value="${appCredential.modifyDate}">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("应用名称")}:
												</th>
												<td>
													<input type="text" class="form-control" readOnly value="${app.appName}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("APP Key")}:
												</th>
												<td>
													<input type="text" name="appKey" readOnly id="appKey" class="form-control"  value="${appCredential.appKey}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("APP Secret")}:
												</th>
												<td>
													<div style="float:left;width:50%;">
														<input type="text" name="appSecret" readOnly id="appSecret" class="form-control"  value="${appCredential.appSecret}" style="width:100%;"/>
													</div>
													<input type="button" onclick="changeAppKey()" value="重置" class="btn btn-warning resetbtn" style="margin-top:0px;">
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("状态")}:
												</th>
												<td class="radioTD">
													<div style="float:left;width:50%;">
														
														<input type="text" id="statusText" class="form-control" readOnly value="[#if appCredential.status ==true]启用[#else]禁用[/#if]" style="width:100%;"/>
													</div>
													<div>
														[#if appCredential.status ==true]
														<input type="button" onclick="changeStatus()" id="statusBtn" value="禁用" class="btn btn-warning resetbtn" style="margin-top:0px;">
														<input type="hidden" name="status" id="appCredentialStatus" value="true">
														[#else]
														<input type="button" onclick="changeStatus()" id="statusBtn" value="启用" class="btn btn-success resetbtn" style="margin-top:0px;">
														<input type="hidden" name="status" id="appCredentialStatus" value="false">
														[/#if]
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
	             <!-- end API授权-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>