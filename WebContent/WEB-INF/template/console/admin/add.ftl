<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.admin.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
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
//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorClass: " m-b-none",
    validClass: " m-b-none"
});
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			dictSchoolId:"required",
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check_username.ct",
					cache: false
				}
			},
			name:{
				maxlength:20
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			roleIds: "required"
		},
		messages: {
			username: {
				pattern: "${message("console.validate.illegal")}",
				remote: "${message("console.validate.exist")}"
			},
			password: {
				pattern: "${message("console.validate.illegal")}"
			}
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
                    <h2> ${message("console.admin.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.admin.add")}</strong>
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
	             <form id="inputForm" action="save.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <input type="hidden" name="id" value="${admin.id}" />
	                                     <table id="listTable" class="table table-striped">
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.dictSchool")}:
												</th>
												<td>
													<select name="dictSchoolId" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list dictSchools as dictSchool]
															<option value="${dictSchool.id}">
																${dictSchool.name}
															</option>
														[/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.username")}:
												</th>
												<td>
													<input type="text" name="username" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.name")}:
												</th>
												<td>
													<input type="text" name="name" class="form-control" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.password")}:
												</th>
												<td>
													<input type="password" id="password" name="password" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.admin.rePassword")}:
												</th>
												<td>
													<input type="password" name="rePassword" class="form-control" maxlength="20" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Admin.email")}:
												</th>
												<td>
													<input type="text" name="email" class="form-control" maxlength="200" />
												</td>
											</tr>
											<tr class="roles">
												<th>
													<span class="requiredField">*</span>${message("Admin.roles")}:
												</th>
												<td>
													<span class="fieldSet">
														[#list roles as role]
															<label>
																<input type="checkbox" name="roleIds" value="${role.id}" />${role.name}
															</label>
														[/#list]
													</span>
												</td>
											</tr>
											<tr>
												<th>
													${message("Admin.isSchoolManager")}:
												</th>
												<td>
													<label>
														<input type="checkbox" name="isSchoolManager" value="true" checked="checked" />${message("Admin.isSchoolManager")}
														<input type="hidden" name="_isSchoolManager" value="false" />
													</label>
												</td>
											</tr>
											<tr>
												<th>
													${message("console.common.setting")}:
												</th>
												<td>
													<label>
														<input type="checkbox" name="isEnabled" value="true" checked="checked" />${message("Admin.isEnabled")}
														<input type="hidden" name="_isEnabled" value="false" />
													</label>
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