<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.attendanceEquipment.edit")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
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
			dictSchoolId: {
				required: true
			},
			equipmentSequence: {
				required: true,
				maxlength:50,
				remote: {
					url: "edit_check_equipmentSequence.ct?previousSequence=${attendanceEquipment.equipmentSequence}",
					cache: false
				}
			},
			equipmentName: {
				required: true,
				maxlength:20
			},
			status:{
				maxlength:10
			},
			ipAddress:{
				pattern:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
			},
			port:{
				digits:true,
				range:[0,65535]
			},
			firmwareVersion:{
				maxlength:50
			},
			remark:{
				maxlength:100
			}
			
		},
		messages: {
			equipmentSequence:{
				remote:"${message("console.validate.exist")}"
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
                    <h2> ${message("console.attendanceEquipment.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.attendanceEquipment.edit")}</strong>
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
	                 <input type="hidden" name="id" class="form-control"  value="${attendanceEquipment.id}"/>
	                 <input type="hidden" id="communicationTime" name="communicationTime" class="form-control"  value="${attendanceEquipment.communicationTime}"/>
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
												<th>
													<span class="requiredField">*</span>${message("AttendanceEquipment.equipmentSequence")}:
												</th>
												<td>
													<input type="text" name="equipmentSequence" class="form-control" maxlength="50" value="${attendanceEquipment.equipmentSequence}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("AttendanceEquipment.equipmentName")}:
												</th>
												<td>
													<input type="text" name="equipmentName" class="form-control" value="${attendanceEquipment.equipmentName}" />
												</td>
											</tr>
											<tr>
												<th>
													${message("AttendanceEquipment.status")}:
												</th>
												<td>
													<input type="text" id="status" name="status" class="form-control"  value="${attendanceEquipment.status}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("AttendanceEquipment.ipAddress")}:
												</th>
												<td>
													<input type="text" name="ipAddress" class="form-control" maxlength="50"  value="${attendanceEquipment.ipAddress}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("AttendanceEquipment.port")}:
												</th>
												<td>
													<input type="text" name="port" class="form-control" maxlength="20" value="${attendanceEquipment.port}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("AttendanceEquipment.firmwareVersion")}:
												</th>
												<td>
													<input type="text" name="firmwareVersion" class="form-control" maxlength="20" value="${attendanceEquipment.firmwareVersion}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("AttendanceEquipment.remark")}:
												</th>
												<td>
													<input type="text" name="remark" class="form-control" maxlength="20" value="${attendanceEquipment.remark}"/>
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