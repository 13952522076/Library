<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictSchool.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/editor/kindeditor.js"></script>
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
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	
	[@flash_message /]
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/console/common/area.ct"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			code: {
				required: true,
				digits:true,
				maxlength:10
			},
			name: {
				required: true,
				maxlength:20
			},
			address: {
				required: true,
				maxlength:30
			},
			kindergartenName: {
				required: true,
				maxlength:20
			},
			kindergartenPhone: {
				required: true,
				pattern: /1\d{10}/
				
			},
			areaId_select: "required"
		},
		messages: {
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
                    <h2> ${message("console.dictSchool.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.dictSchool.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增管理员-->
	             <form id="inputForm" action="update.ct" method="post">
	                  <input type="hidden" id="id" name="id" value="${(dictSchool.id)!}" />
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
													${message("DictSchool.area")}:
												</th>
												<td style="float: left;">
													<span class="fieldSet">
														<input type="hidden" id="areaId" name="areaId" value="${(dictSchool.area.id)!}" treePath="${(dictSchool.area.treePath)!}" />
													</span>
												</td>
											</tr>
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.code")}:
												</th>
												<td>
													<input type="text" name="code" class="form-control" maxlength="30" value="${dictSchool.code}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.name")}:
												</th>
												<td>
													<input type="text" name="name" class="form-control"  value="${dictSchool.name}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.address")}:
												</th>
												<td>
													<input type="text" id="address" name="address" class="form-control" value="${dictSchool.address}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.kindergartenName")}:
												</th>
												<td>
													<input type="text" name="kindergartenName" class="form-control" maxlength="50" value="${dictSchool.kindergartenName}"  />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.kindergartenPhone")}:
												</th>
												<td>
													<input type="text" name="kindergartenPhone" class="form-control" maxlength="20"  value="${dictSchool.kindergartenPhone}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictSchool.description")}:
												</th>
												<td>
													<textarea name="description" id="editor" class="form-control" >${dictSchool.description}</textarea>
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
	             <!-- end 新增管理员-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>