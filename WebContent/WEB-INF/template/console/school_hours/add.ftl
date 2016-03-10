<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.schoolHours.add")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/timepicker/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/timepicker/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/timepicker/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="${base}/resources/console/timepicker/jquery-ui-timepicker-addon.js"></script>
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
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.input-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.input-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorClass: " m-b-none",
    validClass: " m-b-none"
});
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	<!-- timepicker插件绑定 start-->
	$("#attendTime").timepicker();
	$("#finishTime").timepicker();
	
	var startAttendTime = $('#startAttendTime');
	var endAttendTime = $('#endAttendTime');
	$.timepicker.timeRange(
		startAttendTime,
		endAttendTime,
		{
			//minInterval: (1000*60), // 1min
			timeFormat: 'HH:mm',
			start: {}, // start picker options
			end: {} // end picker options
		}
	);
	
	var startFinishTime = $('#startFinishTime');
	var endFinishTime = $('#endFinishTime');
	$.timepicker.timeRange(
		startFinishTime,
		endFinishTime,
		{
			//minInterval: (1000*60), // 1min
			timeFormat: 'HH:mm',
			start: {}, // start picker options
			end: {} // end picker options
		}
	);
	<!-- timepicker插件绑定 end  -->
	
	// 表单验证
	$inputForm.validate({
		rules: {
			attendTime:"required",
			startAttendTime:"required",
			endAttendTime:"required",
			finishTime:"required",
			startFinishTime:"required",
			endFinishTime:"required"
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
                    <h2> ${message("console.schoolHours.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.schoolHours.add")}</strong>
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
	                        <div class="ibox float-e-margins" style="width:80%;margin:0 auto;">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.schoolHours.attendTime")}:
												</th>
												<td>
													<div class="input-group">
														<input type="text" id="attendTime" name="attendTime" />
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.schoolHours.startAttendTime")}:
												</th>
												<td>
													<div class="input-group">
														<input type="text" id="startAttendTime" name="startAttendTime" />
														—
														<input type="text" id="endAttendTime" name="endAttendTime" />
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.schoolHours.finishTime")}:
												</th>
												<td>
													<div class="input-group">
														<input type="text" id="finishTime" name="finishTime"/>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("console.schoolHours.startFinishTime")}:
												</th>
												<td>
													<div class="input-group">
														<input type="text" id="startFinishTime" name="startFinishTime"/>
														—
														<input type="text" id="endFinishTime" name="endFinishTime"/>
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
													<div class="input-group">
														<input type="submit" class="btn  btn-primary" value="${message("console.common.submit")}" />
														<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
													</div>
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