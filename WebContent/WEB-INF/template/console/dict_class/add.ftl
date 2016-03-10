<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictClass.add")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
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
	//$("#dictSchoolId option:last").attr("selected", true);
	
	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			//dictSchoolId:{
			//	required: true
			//},
			dictGradeId:{
		    	required:true,
		    },
			code: {
				required: true,
				digits:true,
				maxlength:10,
				remote: {
					url: "check_code.ct",
					data:{
			       		code:function(){
			          		return $("#code").val();
		          		}
		          		//,
			       		//dictSchoolId:function(){
			          	//	return $("#dictSchoolId").val();
			          	//}
 					},   
					cache: false
				}
			},
			name: {
				required: true,
				maxlength:20
			},
			cmaster: {
				required: true,
				maxlength:20
			},
			dictSchool: "required",
			description:{
				maxlength:50
			}
			
		},
		messages: {
			code: {
				remote: "${message("console.validate.exist")}"
			}
		}
	});
	
});
function getFormValidate(){
	$.ajax({
		type: "GET",
		url: "check_code.ct",
		data:{
       		code:function(){
          		return $("#code").val();
      		}
      		//,
       		//dictSchoolId:function(){
          	//	return $("#dictSchoolId").val();
          	//}
		},   
		dataType: "json",
		success:function(exsit){
			if(!exsit){
				if($("#code").val()!=""){
					//if($("#dictSchoolId").val()!=""){
					//	$.message("warn", "当前学校存在当前班级编号");
					//	return false;
					//}else{
					//	$("#inputForm").submit();
					//}
					$.message("warn", "当前学校存在当前班级编号");
					
				}
				else{
					$("#inputForm").submit();
				}
				
			}
			else{
				$("#inputForm").submit();
			}
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
                    <h2> ${message("console.dictClass.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.dictClass.add")}</strong>
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
	                            <div class="ibox-content" style="width:50% ;margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
	                                      <tr>
												<th>
													<span class="requiredField">*</span>${message("所属年级")}:
												</th>
												<td>
													<select id="dictGradeId" name="dictGradeId" class="form-control">
														<option value="">${message("console.common.select")}</option>
														 [#list dictGrades as dictGrade]
															<option value="${dictGrade.id}" >
																${dictGrade.gradeName}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("DictClass.code")}:
												</th>
												<td>
													<input id="code" type="text" name="code" class="form-control" maxlength="30" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictClass.name")}:
												</th>
												<td>
													<input type="text" name="name" class="form-control"  />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictClass.cmaster")}:
												</th>
												<td>
													<input type="text" id="cmaster" name="cmaster" class="form-control" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictClass.classStatus")}:
												</th>
												<td>
													<div style="float: left;">
													     <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="classStatus" name="classStatus" value="active" checked="checked">
							                                ${message("DictClass.classStatus.active")}
							                             </label>
							                              <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="classStatus" name="classStatus" value="graduated">
							                                ${message("DictClass.classStatus.graduated")}
							                             </label>
							                        </div>
												</td>
											</tr>
											<tr>
												<th>
													${message("DictClass.description")}:
												</th>
												<td>
													<input type="text" name="description" class="form-control" maxlength="50" />
												</td>
											</tr>
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<a class="btn  btn-primary"  onclick="getFormValidate()">${message("console.common.submit")}</a>
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