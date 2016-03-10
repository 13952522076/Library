<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.overallMerit.add")} - 小书僮™智慧幼教管理平台</title>
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
		    dictClassId:{
		    	required:true,
		    },
		    dictStudentId:{
		    	required:true,
		    	remote:{                                    
                  type:"GET",
                  url:"checkStudent.ct",
                  data:{
                    dictStudentId:this.value
                  },
                  dataType: "json"
                } 
		   }
		},
		messages: {
			dictStudentId: {
				remote: "${message("console.validate.exist")}"
			}
		}
	});
});

//ajax动态获取拼接学生列表
function getStudents(){
	if(($("#dictClassId").val()==null)||$("#dictClassId").val()==""){
		$("#dictStudentId").html("");
		return false;
	}
	$.ajax({
		type: "GET",
		url: "getStudents.ct",
		data: {
		    dictClassId:$("#dictClassId").val()
		},
		dataType: "json",
		success:function(students){
			var content ="";
			if((students!=null)&&(students.length>0)){
			    content += '<option value="">${message("console.common.select")}</option>'
				for(var i=0;i<students.length;i++){
					var student = 	students[i];
					content+='<option value="'+student.id+'">';
					content+=student.studentName;
					content+='</option>';
				}
			}
			$("#dictStudentId").html(content);
		}
	});
}
function checkField(obj){
   if(!isNaN(obj.value)){
	}else{
	 	  $.message("warn", "请输入数字!");
	 	  return false; 
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
                    <h2> ${message("console.overallMerit.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.overallMerit.add")}</strong>
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
													<span class="requiredField">*</span>${message("console.overallMerit.class")}:
												</th>
												<td>
													<select id="dictClassId" name="dictClassId" class="form-control m-b" onchange="getStudents();">
														<option value="">${message("console.common.select")}</option>
														 [#list dictClasses as dictClass]
															<option value="${dictClass.id}" >
																${dictClass.name}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.overallMerit.student")}:
												</th>
												<td>
													<select id="dictStudentId" name="dictStudentId" class="form-control m-b"></select>
												</td>
											</tr>
											 [#list meritTemplates as meritTemplate]
												<tr>
													<th>
														${meritTemplate.meritName}:
													</th>
													<td>
														<input type="hidden" name="overallMerits[${meritTemplate_index}].meritTemplate.id" id="overallMerits[${meritTemplate_index}].meritTemplate.id" class="form-control" value= "${meritTemplate.id}"/>
														<input type="text" name="overallMerits[${meritTemplate_index}].grade" id="overallMerits[${meritTemplate_index}].grade" class="form-control" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9.]+/,'');}).call(this)" onblur="this.v();"/>
													</td>
													<th>
														分
													</th>
												</tr>
											 [/#list]
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
</body>
</html>