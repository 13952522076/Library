<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.member.add")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${base}/resources/console/css/plugins/iCheck/custom.css" >
<link href="${base}/resources/console/timepicker/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/timepicker/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/timepicker/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="${base}/resources/console/timepicker/jquery-ui-timepicker-addon.js"></script>
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
	[@flash_message /]
	
	<!-- timepicker插件绑定 start-->
	
	var startTime = $('#startTime');
	var endTime = $('#endTime');
	$.timepicker.timeRange(
		startTime,
		endTime,
		{
			//minInterval: (1000*60), // 1min
			timeFormat: 'HH:mm',
			start: {}, // start picker options
			end: {} // end picker options
		}
	);
	<!-- timepicker插件绑定 end  -->
	
	// 表单验证
	$("#inputForm").validate({
		rules: {
			classId:{
				required: true
			},
			schoolYearMngId:{
				required: true
			},
			courseName:{
				required: true,
				maxlength: 50
			},
			week:{
				required: true
			},
			lessons:{
				required: true
			}
		}
	});
});

function validateAddCourse(){
	var schoolYearMngId = $("#schoolYearMngId").val();
	var classId = $("#classId").val();
	var week = $("#week").val();
	var lessons = $("#lessons").val();
	var previousSchoolYearMngId = ${course.schoolYearMng.id};
	var previousClassId = ${course.dictClass.id};
	var previousWeek = ${course.week};
	var previousLessons = ${course.lessons};
	
	if((schoolYearMngId==previousSchoolYearMngId)&&(classId==previousClassId)&&(week==previousWeek)&&(lessons==previousLessons)){
		$("#inputForm").submit();
	}
	else{
		$.ajax({
			type: "GET",
			url: "courseExsit.ct",
			data: {
			    schoolYearMngId:schoolYearMngId,
			    classId:classId,
			    week:week,
			    lessons:lessons
			},
			dataType: "json",
			success:function(data){
				if(data==true){
					$.message("warn", "当前课节已有课程，请确认!");
				}
				else{
					$("#inputForm").submit();
				}
			}
		});
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
					<h2> ${message("console.course.editor")} </h2>
	                <ol class="breadcrumb">
						<li>
						    <!--首页-->
							<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> 
	                    </li>
	                    <li>
	                        <strong>${message("console.course.editor")}</strong>
	                    </li>
					</ol>
	            </div>
	            <div class="col-lg-2">
	
	            </div>
	        </div>
	       	<!-- end 头部面包屑区域 -->	
			<!-- start 中间内容部分 -->
			<div class="wrapper wrapper-content animated fadeIn">
				<div class="row">
			        <div class="col-lg-12">
			            <div class="ibox float-e-margins">
			                <div class="ibox-content" style="width:50%;margin:0 auto;">
			                    <form id="inputForm" action="update.ct" method="post" class="form-horizontal m-t">
                                   <input type="hidden" name="id" value="${course.id}">
                                   <div class="form-group"">
                                        <label class="col-sm-3 control-label"><span class="requiredField">*</span>学年：</label>
	                                    <div class="col-sm-8 m-b-xs">
	                                        <select id="schoolYearMngId" name="schoolYearMngId" class="input-sm form-control input-s-sm inline ">
			                                    [#list schoolYearMngs as schoolYearMng]
                                                <option [#if course.schoolYearMng.id=="${schoolYearMng.id}"] selected="selected" [/#if] value="${schoolYearMng.id}">
                                                    ${schoolYearMng.startYear}-${schoolYearMng.endYear}第${schoolYearMng.term}学期
                                                </option>
                                                [/#list]
                                            </select>
	                                    </div>
                                    </div>
                                    <div class="form-group"">
                                        <label class="col-sm-3 control-label"><span class="requiredField">*</span>班级：</label>
	                                    <div class="col-sm-8 m-b-xs">
	                                        <select id="classId" name="classId" class="input-sm form-control input-s-sm inline">
                                                [#list classes as class]
                                                <option [#if course.dictClass.id=="${class.id}"] selected="selected" [/#if] value="${class.id}">
                                                    ${class.name}
                                                </option>
                                                [/#list]
                                            </select>
	                                    </div>
                                    </div>
                                    <div class="form-group"">
                                        <label class="col-sm-3 control-label"><span class="requiredField"></span>星期：</label>
	                                    <div class="col-sm-8 m-b-xs">
	                                        <select id="week" name="week" class="input-sm form-control input-s-sm inline ">
			                                    <option [#if course.week=="1"] selected="selected" [/#if] value="1">星期一</option>
			                                    <option [#if course.week=="2"] selected="selected" [/#if] value="2">星期二</option>
			                                    <option [#if course.week=="3"] selected="selected" [/#if] value="3">星期三</option>
			                                    <option [#if course.week=="4"] selected="selected" [/#if] value="4">星期四</option>
			                                    <option [#if course.week=="5"] selected="selected" [/#if] value="5">星期五</option>
			                                    <option [#if course.week=="6"] selected="selected" [/#if] value="6">星期六</option>
			                                    <option [#if course.week=="7"] selected="selected" [/#if] value="7">星期日</option>
		                                    </select>
	                                    </div>
                                    </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField"></span>授课老师：</label>
			                            <div class="col-sm-8">
			                                <input id="teacherName" name="teacherName" class="form-control" value="${course.teacherName}">
			                            </div>
			                        </div>
			                       <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField"></span>课程名：</label>
			                            <div class="col-sm-8">
			                                <input id="courseName" name="courseName" class="form-control" value="${course.courseName}">
			                            </div>
			                        </div>
			                        <div class="form-group"">
                                        <label class="col-sm-3 control-label"><span class="requiredField"></span>课节：</label>
	                                    <div class="col-sm-4 m-b-xs">
	                                         <select id="lessons" name="lessons" class="input-sm form-control input-s-sm inline">
                                                <option value="1" [#if course.lessons=="1"] selected="selected" [/#if]>（上午）第一节</option>
                                                <option value="2" [#if course.lessons=="2"] selected="selected" [/#if]>（上午）第二节</option>
                                                <option value="3" [#if course.lessons=="3"] selected="selected" [/#if]>（上午）第三节</option>
                                                <option value="4" [#if course.lessons=="4"] selected="selected" [/#if]>（上午）第四节</option>
                                                <option value="5" [#if course.lessons=="5"] selected="selected" [/#if]>（下午）第一节</option>
                                                <option value="6" [#if course.lessons=="6"] selected="selected" [/#if]>（下午）第二节</option>
                                                <option value="7" [#if course.lessons=="7"] selected="selected" [/#if]>（下午）第三节</option>
                                                <option value="8" [#if course.lessons=="8"] selected="selected" [/#if]>（下午）第四节</option>
                                            </select>
	                                    </div>
                                    </div>
                                    <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField"></span>教室：</label>
			                            <div class="col-sm-8">
			                                <input id="classRoom" name="classRoom" class="form-control" value="${course.classRoom}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField"></span>时间：</label>
										<input type="text" id="startTime" name="startTime" value="${course.startTime}"/>
										—
										<input type="text" id="endTime" name="endTime" value="${course.endTime}"/>
			                        </div>
			                        <div class="form-group">
			                            <div class="col-sm-8 col-sm-offset-3">
			                               <a class="btn  btn-primary" onclick="validateAddCourse()">${message("console.common.confirm")}</a>
			                              <!-- <input type="submit" class="btn  btn-primary" value="${message("console.common.confirm")}" /> -->
										   <input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
			                            </div>
			                        </div>
			                    </form>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<!-- end 中间内容部分 -->
		   	[#include "/console/include/footer.ftl" /]
		</div>
	</div>
</body>
</html>