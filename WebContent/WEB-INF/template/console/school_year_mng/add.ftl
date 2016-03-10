<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.schoolYearMng.add")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/css/plugins/iCheck/custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
	
	[@flash_message /]
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			startYear: {
				required: true,
				digits:true,
				minlength:4
			},
			endYear: {
				required: true,
				digits:true,
				minlength:4,
				min:function(){
					return $("#startYear").val();	
				}
			},
			term: {
				required: true
			},
			isShow: {
				
			},
			dictSchoolId: {
				required: true
			}
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
                    <h2> ${message("console.schoolYearMng.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.schoolYearMng.add")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增学年-->
	             <form id="inputForm" action="save.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content" style="width:50%;margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
	                                     [#if !admin.isSchoolManager]
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("SchoolYearMng.dictSchool")}:
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
											[#else]
											      <input type="hidden" name="dictSchoolId" class="form-control" maxlength="4"  value="${admin.dictSchool.id}"/>
											[/#if]
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("SchoolYearMng.startYear")}:
												</th>
												<td>
													<input id="startYear" type="text" name="startYear" class="form-control" maxlength="4"  placeholder="yyyy(2014)"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("SchoolYearMng.endYear")}:
												</th>
												<td>
													<input type="text" name="endYear" class="form-control" maxlength="4" placeholder="yyyy(2015)" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("SchoolYearMng.term")}:
												</th>
												<td>
													<select name="term" class="form-control m-b">
														<option value="1">${message("console.schoolYearMng.oneTerm")}</option>
														<option value="2">${message("console.schoolYearMng.twoTerm")}</option>
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("SchoolYearMng.isShow")}:
												</th>
												<td>
													<label>
														<input type="checkbox" name="isShow" value="true" class="i-checks" checked="checked" />${message("SchoolYearMng.isShow")}
														<input type="hidden" name="_isShow" value="false" />
													</label>
												</td>
											</tr>
											<tr>
												<th>
													${message("是否为当前学年")}:
												</th>
												<td>
													<label>
														<input type="checkbox" name="isCurrent" value="true" class="i-checks">
														<input type="hidden" name="_isCurrent" value="false"/>  
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
	             <!-- end 新增学年-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
<script src="${base}/resources/console/js/plugins/iCheck/icheck.min.js"></script>
<script>
	$(document).ready(function () {
		$('.i-checks').iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});
	});
</script>
</body>
</html>