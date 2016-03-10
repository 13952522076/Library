<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>添加学生请假 - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    dictStudentId: {
		        required: true
		    },
		    dictClassId:{
		        required: true
		    },
			askLeaveType: {
				required: true
			},
			leaveStartDate: {
				required: true
			},
			leaveEndDate: {
				required: true
			},
			auditingUserId: {
				required: true
			},
			description: {
			    required: true,
			    maxlength:200
			}
		},
		messages: {
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

//ajax获取当前班级下老师
function getTeachers(){
	if(($("#dictClassId").val()==null)||$("#dictClassId").val()==""){
		$("#auditingUserId").html("");
		return false;
	}
	$.ajax({
		type: "GET",
		url: "${base}/console/classTeacherMap/getTeachers.ct",
		data: {
		    dictClassId:$("#dictClassId").val()
		},
		dataType: "json",
		success:function(members){
			var content ="";
			if((members!=null)&&(members.length>0)){
				for(var i=0;i<members.length;i++){
					var member = members[i];
					content+='<option value="'+member.id+'">';
					if(member.realName==null){
						content+=member.username;
					}
					else{
						content+=member.realName;
					}
					content+='</option>';
				}
			}
			$("#auditingUserId").html(content);
		}
	});
};
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
                    <h2> ${message("添加学生请假")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.dictStudent.add")}</strong>
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
	                            <div class="ibox-content" style="width:50%;margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table class="table table-striped">
	                                     <tr>
												<th>
													<span class="requiredField">*</span>班级:
												</th>
												<td>
													<select id="dictClassId" name="dictClassId" class="form-control m-b" onchange="getStudents();getTeachers();">
														<option value="">${message("console.common.select")}</option>
														 [#list dictClasses as dictClass]
															<option value="${dictClass.id}" >
																(${dictClass.dictSchool.name})${dictClass.name}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
	                                        <tr>
												<th>
													<span class="requiredField">*</span>学生:
												</th>
												<td>
													<select id="dictStudentId" name="dictStudentId" class="form-control m-b"></select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>请假类型:
												</th>
												<td>
													<div style="float: left;">
													     <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="askLeaveType" name="askLeaveType" value="sick" checked="checked">
							                                病假
							                             </label>
							                              <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="askLeaveType" name="askLeaveType" value="compassionate">
							                                事假
							                             </label>
							                        </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>请假开始时间:
												</th>
												<td>
													<input type="text" name="leaveStartDate" id="leaveStartDate" class="laydate-icon form-control layer-date"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>请假结束时间:
												</th>
												<td>
													<input type="text" name="leaveEndDate" id="leaveEndDate" class="laydate-icon form-control layer-date"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>请假说明:
												</th>
												<td>
													<input type="text" name="description" class="form-control"  />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>审批人:
												</th>
												<td>
													<select id="auditingUserId" name="auditingUserId" class="form-control m-b">
													</select>
												</td>
											</tr>
											<tr>
												<th>是否同意:</th>
												<td>
													<input type="checkbox" name="isAgree" class="form-control" value="true" />
													<input type="hidden" name="_isAgree" value="false">
												</td>
											</tr>
										</table>
										<table class="input">
											<tr>
												<th>&nbsp;</th>
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
<!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
    <script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js"></script>
    <script>
    //外部js调用
    var start = {
	    elem: '#leaveStartDate',
	    format: 'YYYY-MM-DD',
	    min: '1900-01-01', //最小日期
	    //设定最小日期为当前日期
	    max: '2099-06-16',
	    //最大日期
	    istime: true,
	    istoday: false,
	    choose: function(datas) {
	        end.min = datas; //开始日选好后，重置结束日的最小日期
	        end.start = datas //将结束日的初始值设定为开始日
	    }
	};
	var end = {
	    elem: '#leaveEndDate',
	    format: 'YYYY-MM-DD',
	    min: '1900-01-01', //最小日期
	    max: '2099-06-16',
	    istime: true,
	    istoday: false,
	    choose: function(datas) {
	        start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	};
	laydate(start);
	laydate(end);
    </script>
</body>
</html>