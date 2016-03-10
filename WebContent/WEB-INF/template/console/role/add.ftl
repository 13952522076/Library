<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.role.add")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<style type="text/css">
.authorities label {
  cursor: pointer;
}

table.input1 td {
	line-height: 26px;
}

.authorities li {float:left;  margin-left: 20px;}

li{list-style-type:none;}

</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	/**用户管理*/
	var $memberGroupSelectAll = $("#memberGroupSelectAll");
	var $contentGroupSelectAll = $("#contentGroupSelectAll");
	
	var $campusGroupSelectAll = $("#campusGroupSelectAll");
	var $curriculumScheduleGroupSelectAll = $("#curriculumScheduleGroupSelectAll");
	var $attendanceGroupSelectAll = $("#attendanceGroupSelectAll");
	var $recipesGroupSelectAll = $("#recipesGroupSelectAll");
	
	var $marketingGroupSelectAll = $("#marketingGroupSelectAll");
	var $systemGroupSelectAll = $("#systemGroupSelectAll");
	var $meritTemplateSelectAll = $("#meritTemplateSelectAll");
	var $feedbackSelectAll = $("#feedbackSelectAll");
	var $growthDiarySelectAll = $("#growthDiarySelectAll");
    var $appSelectAll = $("#appSelectAll");
    var $adSelectAll = $("#adSelectAll");
	
	[@flash_message /]
	
	/**用户管理*/
	$memberGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".memberGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**内容管理*/
	$contentGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".contentGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**营销*/
	$marketingGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".marketingGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**系统*/
	$systemGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".systemGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**校园管理*/
	$campusGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".campusGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**课程管理*/
	$curriculumScheduleGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".curriculumScheduleGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**考勤管理*/
	$attendanceGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".attendanceGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	
	/**评价管理*/
	$meritTemplateSelectAll.click(function(){
	     var $thisCheckbox =  $(".meritTemplateGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**食谱管理*/
	$recipesGroupSelectAll.click(function(){
	     var $thisCheckbox =  $(".recipesGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**反馈管理*/
	$feedbackSelectAll.click(function(){
	     var $thisCheckbox =  $(".feedbackGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**成长记管理*/
	$growthDiarySelectAll.click(function(){
	     var $thisCheckbox =  $(".growthDiaryGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**应用管理*/
	$appSelectAll.click(function(){
	     var $thisCheckbox =  $(".appGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/** 广告管理 */
	$adSelectAll.click(function(){
	     var $thisCheckbox =  $(".adGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required:true,
				maxlength:15
			},
			description:{
				maxlength:50
			},
			authorities: "required"
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
                    <h2>${message("console.path.index")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.role.add")}</strong>
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
	                                     <table  class="table table-bordered">
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Role.name")}:
												</th>
												<td colspan="2">
													<input type="text" name="name"  class="form-control" value="${role.name}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Role.description")}:
												</th>
												<td colspan="2">
													<input type="text" name="description"  class="form-control" value="${role.description}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<td colspan="3">
													&nbsp;
												</td>
											</tr>
											<!-- start 用户管理 -->
											<tr class="authorities">
												<th>
													${message("console.role.memberGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="memberGroup">
												<th>
													 <input type="button" class="btn btn-primary btn-xs" id="memberGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="consoleMember" name="authorities" value="console:member" />
													       <label for="consoleMember">
													           ${message("console.role.member")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="memberIconAddIcon"  value="console:member_icon_addIcon"/>
															<label for="memberIconAddIcon">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberAEdit" value="console:member_a_edit" />
														    <label for="memberAEdit">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberButtonDeleteButton" value="console:member_button_deleteButton" />
															<lable for="memberButtonDeleteButton">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberButtonExportButton" value="console:member_button_exportButton"/>
															<label for="memberButtonExportButton">
															    ${message("console.common.export")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end 用户管理 -->
											
											
											<!-- start 校园管理-->
											
											<tr class="authorities">
												<th>
													${message("console.role.campusGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="campusGroup">
												<th rowspan="6">
													 <input type="button" class="btn btn-primary btn-xs" id="campusGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="campusMng" name="authorities" value="console:dictSchool" />
													       <label for="campusMng">
													           ${message("console.role.campusMng")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictSchool"  value="console:addDictSchool"/>
															<label for="addDictSchool">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictSchool" value="console:editDictSchool" />
														    <label for="editDictSchool">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictSchool" value="console:deleteDictSchool" />
															<label for="deleteDictSchool">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--start 年级管理 -->
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="dictGrade" name="authorities" value="console:dictGrade"/>
													       <label for="dictGrade">
													           ${message("年级管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictGrade"  value="console:addDictGrade"/>
															<label for="addDictGrade">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictGrade" value="console:editDictGrade"/>
														    <label for="editDictGrade">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictGrade" value="console:deleteDictGrade" />
															<label for="deleteDictGrade">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end   年级管理 -->
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="dictClass" name="authorities" value="console:dictClass" />
													       <label for="dictClass">
													           ${message("console.role.dictClass")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictClass"  value="console:addDictClass"/>
															<label for="addDictClass">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictClass" value="console:editDictClass" />
														    <label for="editDictClass">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictClass" value="console:deleteDictClass" />
															<label for="deleteDictClass">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<!--老师管理-->
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="dictStudent" name="teacher" value="console:teacher"/>
													       <label for="teacher">
													           ${message("老师管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addTeacher"  value="console:addTeacher"/>
															<label for="addTeacher">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editTeacher" value="console:editTeacher"/>
														    <label for="editTeacher">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteTeacher" value="console:deleteTeacher"/>
															<label for="deleteTeacher">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<!-- 家长管理 -->
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="patriarch" name="authorities" value="console:patriarch"/>
													       <label for="patriarch">
													           ${message("家长管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addPatriarch"  value="console:addPatriarch"/>
															<label for="addPatriarch">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editPatriarch" value="console:editPatriarch"/>
														    <label for="editPatriarch">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deletePatriarch" value="console:deletePatriarch"/>
															<label for="deletePatriarch">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											
											
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="dictStudent" name="authorities" value="console:dictStudent" />
													       <label for="dictStudent">
													           ${message("console.role.dictStudent")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictStudent"  value="console:addDictStudent"/>
															<label for="addDictStudent">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictStudent" value="console:editDictStudent" />
														    <label for="editDictStudent">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictStudent" value="console:deleteDictStudent" />
															<label for="deleteDictStudent">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<!-- end 校园管理 -->
											
											
											
											<!-- start 课程表管理-->
											<tr class="authorities">
												<th>
													${message("console.role.curriculumScheduleGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="curriculumScheduleGroup">
												<th rowspan="5">
													 <input type="button" class="btn btn-primary btn-xs" id="curriculumScheduleGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="curriculumSchedule" name="authorities" value="console:curriculumSchedule" />
													       <label for="curriculumSchedule">
													           ${message("console.role.curriculumSchedule")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCurriculumSchedule" value="console:addCurriculumSchedule"/>
															<label for="addCurriculumSchedule">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCurriculumSchedule" value="console:editCurriculumSchedule" />
														    <label for="editCurriculumSchedule">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCurriculumSchedule" value="console:deleteCurriculumSchedule" />
															<label for="deleteCurriculumSchedule">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="curriculumScheduleGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="courseName" name="authorities" value="console:courseName" />
													       <label for="courseName">
													           ${message("console.role.courseName")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCourseName" value="console:addCourseName"/>
															<label for="addCourseName">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCourseName" value="console:editCourseName" />
														    <label for="editCourseName">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCourseName" value="console:deleteCourseName" />
															<label for="deleteCourseName">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="curriculumScheduleGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="qualityCourse" name="authorities" value="console:qualityCourse" />
													       <label for="qualityCourse">
													           ${message("console.role.qualityCourse")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addQualityCourse" value="console:addQualityCourse"/>
															<label for="addQualityCourse">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editQualityCourse" value="console:editQualityCourse" />
														    <label for="editQualityCourse">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteQualityCourse" value="console:deleteQualityCourse" />
															<label for="deleteQualityCourse">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="curriculumScheduleGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="weeklyPlanSection" name="authorities" value="console:weeklyPlanSection"/>
													       <label for="weeklyPlanSection">
													           ${message("周计划")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWeeklyPlanSection" value="console:addWeeklyPlanSection"/>
															<label for="addWeeklyPlanSection">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWeeklyPlanSection" value="console:editWeeklyPlanSection"/>
														    <label for="editWeeklyPlanSection">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWeeklyPlanSection" value="console:deleteWeeklyPlanSection"/>
															<label for="deleteWeeklyPlanSection">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<tr class="curriculumScheduleGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="schoolYearMng" name="authorities" value="console:schoolYearMng" />
													       <label for="schoolYearMng">
													           ${message("console.role.schoolYearMng")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSchoolYearMng"  value="console:addSchoolYearMng"/>
															<label for="addSchoolYearMng">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSchoolYearMng" value="console:editSchoolYearMng" />
														    <label for="editSchoolYearMng">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSchoolYearMng" value="console:deleteSchoolYearMng" />
															<label for="deleteSchoolYearMng">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end 课程表管理 -->
											
											
											<!-- start 考勤管理 -->
											<tr class="authorities">
												<th>
													${message("console.role.attendanceGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="attendanceGroup">
												<th rowspan="9">
													 <input type="button" class="btn btn-primary btn-xs" id="attendanceGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="attendance" name="authorities" value="console:attendance" />
													       <label for="attendance">
													           ${message("console.role.attendance")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												  
												</td>
											</tr>
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="askLeave" name="authorities" value="console:askLeave" />
													       <label for="askLeave">
													           ${message("console.role.askLeave")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAskLeave" value="console:addAskLeave"/>
															<label for="addAskLeave">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAskLeave" value="console:editAskLeave" />
														    <label for="editAskLeave">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAskLeave" value="console:deleteAskLeave" />
															<label for="deleteAskLeave">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- start教师请假 -->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="teacherAskLeave" name="authorities" value="console:teacherAskLeave" />
													       <label for="teacherAskLeave">
													           ${message("教师请假")}
													       </label>
													    </li>
												    </ul>
												</td>
											</tr>
											<!-- end  教师请假 -->
											
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="attendanceEquipment" name="authorities" value="console:attendanceEquipment" />
													       <label for="attendanceEquipment">
													           ${message("console.role.attendanceEquipment")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAttendanceEquipment"  value="console:addAttendanceEquipment"/>
															<label for="addAttendanceEquipment">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAttendanceEquipment" value="console:editAttendanceEquipment" />
														    <label for="editAttendanceEquipment">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAttendanceEquipment" value="console:deleteAttendanceEquipment" />
															<label for="deleteAttendanceEquipment">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="workSetting" name="authorities" value="console:workSetting"/>
													       <label for="workSetting">
													           ${message("班次管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWorkSetting"  value="console:addWorkSetting"/>
															<label for="addWorkSetting">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWorkSetting" value="console:editWorkSetting"/>
														    <label for="editWorkSetting">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWorkSetting" value="console:deleteWorkSetting"/>
															<label for="deleteWorkSetting">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 排班管理 -->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="workScheduling" name="authorities" value="console:workScheduling"/>
													       <label for="workScheduling">
													           ${message("排班管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWorkScheduling"  value="console:addWorkScheduling"/>
															<label for="addWorkScheduling">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWorkScheduling" value="console:editWorkScheduling"/>
														    <label for="editWorkScheduling">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWorkScheduling" value="console:deleteWorkScheduling"/>
															<label for="deleteWorkScheduling">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--  教师考勤 -->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="teacherAttendance" name="authorities" value="console:teacherAttendance"/>
													       <label for="teacherAttendance">
													           ${message("教师考勤")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    
												</td>
											</tr>
											<!--考勤统计-->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="attendanceStatistics" name="authorities" value="console:attendanceStatistics"/>
													       <label for="attendanceStatistics">
													           ${message("考勤统计")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    
												</td>
											</tr>
											<!--上学放学时间管理 -->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="schoolHours" name="authorities" value="console:schoolHours"/>
													       <label for="schoolHours">
													           ${message("console.role.schoolHours")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSchoolHours"  value="console:addSchoolHours"/>
															<label for="addSchoolHours">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSchoolHours" value="console:editSchoolHours"/>
														    <label for="editSchoolHours">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSchoolHours" value="console:deleteSchoolHours"/>
															<label for="deleteSchoolHours">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<!-- end 考勤管理-->
											
											
											<!-- start 评价管理-->
											<tr class="authorities">
												<th>
													${message("console.main.appraisal")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="meritTemplateGroup">
												<th rowspan="2">
													 <input type="button" class="btn btn-primary btn-xs" id="meritTemplateSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="meritTemplate" name="authorities" value="console:meritTemplate" />
													       <label for="meritTemplate">
													           ${message("console.role.meritTemplate")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addMeritTemplate" value="console:addMeritTemplate"/>
															<label for="addMeritTemplate">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editMeritTemplate" value="console:editMeritTemplate" />
														    <label for="editMeritTemplate">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteMeritTemplate" value="console:deleteMeritTemplate" />
															<label for="deleteMeritTemplate">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="meritTemplateGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="overallMerit" name="authorities" value="console:overallMerit" />
													       <label for="overallMerit">
													           ${message("console.role.overallMerit")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addOverallMerit"  value="console:addOverallMerit"/>
															<label for="addOverallMerit">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editOverallMerit" value="console:editOverallMerit" />
														    <label for="editOverallMerit">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteOverallMerit" value="console:deleteOverallMerit" />
															<label for="deleteOverallMerit">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end 评价管理 -->
											
											<!-- start 食谱管理 -->
											
											<tr class="authorities">
												<th>
													${message("console.role.recipesGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="recipesGroup">
												<th rowspan="3">
													 <input type="button" class="btn btn-primary btn-xs" id="recipesGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="recipes" name="authorities" value="console:recipes" />
													       <label for="recipes">
													           ${message("console.role.recipes")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
													<ul class="authorities">
														<li>
														
															<input type="checkbox" name="authorities" id="addRecipe" value="console:addRecipe" />
															<label for="addRecipe">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipe" value="console:editRecipe" />
														    <label for="editRecipe">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipe" value="console:deleteRecipe" />
															<label for="deleteRecipe">
													           ${message("console.common.delete")}
													       </label>
														</li>
													</ul>
												</td>
											</tr>
											<tr class="recipesGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="recipeSectionManager" name="authorities" value="console:recipeSectionManager" />
													       <label for="recipeSectionManager">
													           ${message("console.role.recipeSectionManager")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addRecipeSectionManager" value="console:addRecipeSectionManager"/>
															<label for="addRecipeSectionManager">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipeSectionManager" value="console:editRecipeSectionManager" />
														    <label for="editRecipeSectionManager">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipeSectionManager" value="console:deleteRecipeSectionManager" />
															<label for="deleteRecipeSectionManager">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<tr class="recipesGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="recipeWeekManager" name="authorities" value="console:recipeWeekManager" />
													       <label for="recipeWeekManager">
													           ${message("console.role.recipeWeekManager")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addRecipeWeekManager"  value="console:addRecipeWeekManager"/>
															<label for="addRecipeWeekManager">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipeWeekManager" value="console:editRecipeWeekManager" />
														    <label for="editRecipeWeekManager">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipeWeekManager" value="console:deleteRecipeWeekManager" />
															<label for="deleteRecipeWeekManager">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											
											<!-- end 食谱管理 -->
											
											<!-- start成长记管理 -->
											<tr class="authorities">
												<th>
													${message("成长记管理")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="growthDiaryGroup">
												<th rowspan="3">
													 <input type="button" class="btn btn-primary btn-xs" id="growthDiarySelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="growthDiary" name="authorities" value="console:growthDiary"/>
													       <label for="growthDiary">
													           ${message("成长记")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="deleteGrowthDiary" value="console:deleteGrowthDiary" />
															<label for="deleteGrowthDiary">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="detailGrowthDiary" value="console:detailGrowthDiary"/>
														    <label for="detailGrowthDiary">
															    ${message("详情")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="growthDiaryGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="diaryTag" name="authorities" value="console:diaryTag"  />
													       <label for="diaryTag">
													           ${message("成长记标签")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDiaryTag"  value="console:addDiaryTag" />
															<label for="addDiaryTag">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDiaryTag" value="console:editDiaryTag"  />
														    <label for="editDiaryTag">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDiaryTag" value="console:deleteDiaryTag"  />
															<label for="deleteDiaryTag">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="growthDiaryGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="diaryPicture" name="authorities" value="console:diaryPicture"  />
													       <label for="diaryPicture">
													           ${message("成长记图片")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
														<li>
															<input type="checkbox" name="authorities" id="deleteDiaryPicture" value="console:deleteDiaryPicture"  />
															<label for="deleteDiaryPicture">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end  成长记管理 -->
											
											<!-- start内容管理 -->
											<tr>
												<th>
													${message("console.role.contentGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="contentGroup">
												<th rowspan="9">
													<input type="button" class="btn btn-primary btn-xs" id="contentGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" name="authorities" id="news" value="console:news"/>
													       <label for="news">
													          ${message("console.role.news")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addNews" value="console:addNews" />
															<label for="addNews">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities"  id="editNews" value="console:editNews"/>
															<label for="editNews">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteNews" value="console:deleteNews"/>
															<label for="deleteNews">
															    ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--start新闻类别管理-->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="newsCategoryMng" value="console:newsCategoryMng" />
													        <label for="newsCategoryMng">
													           ${message("新闻类别管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addNewsCategory" value="console:addNewsCategory"/>
															<label for="addNewsCategory">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editNewsCategory" value="console:editNewsCategory"/>
															<label for="editNewsCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteNewsCategory" value="console:deleteNewsCategory"/>
															<label for="deleteNewsCategory">
															    ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end  新闻类别管理-->
											<!--start育儿管理-->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="parenting" value="console:parenting"/>
													        <label for="parenting">
													           ${message("育儿中心")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addParenting" value="console:addParenting"/>
															<label for="addParenting">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editParenting" value="console:editParenting"/>
															<label for="editParenting">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteParenting" value="console:deleteParenting" />
															<label for="deleteParenting">
															    ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end  育儿管理-->
											<!--start育儿类别管理-->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="parentingCategoryMng" value="console:parentingCategoryMng" />
													        <label for="parentingCategoryMng">
													           ${message("育儿类别管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addParentingCategory" value="console:addParentingCategory" />
															<label for="addParentingCategory">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editParentingCategory" value="console:editParentingCategory"/>
															<label for="editParentingCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteParentingCategory" value="console:deleteParentingCategory" />
															<label for="deleteParentingCategory">
															    ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end  育儿类别管理-->
											<!--start海报管理-->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="poster" value="console:poster"/>
													        <label for="poster">
													           ${message("海报管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addPoster" value="console:addPoster"/>
															<label for="addPoster">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editPoster" value="console:editPoster"/>
															<label for="editPoster">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deletePoster" value="console:deletePoster"/>
															<label for="deletePoster">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end海报管理-->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="announcement" value="console:announcement"/>
													        <label for="announcement">
													           ${message("console.role.announcement")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAnnouncement" value="console:addAnnouncement"/>
															<label for="addAnnouncement">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAnnouncement" value="console:editAnnouncement"/>
															<label for="editAnnouncement">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAnnouncement" value="console:deleteAnnouncement"/>
															<label for="deleteAnnouncement">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="profile" value="console:profile"/>
													        <label for="profile">
													           ${message("console.role.profile")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addProfile" value="console:addProfile"/>
															<label for="addProfile">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editProfile" value="console:editProfile"/>
															<label for="editProfile">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteProfile" value="console:deleteProfile"/>
															<label for="deleteProfile">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="campusviewImg" value="console:campusviewImg"/>
													        <label for="campusviewImg">
													           ${message("console.role.campusviewImg")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCampusviewImg" value="console:addCampusviewImg"/>
															<label for="addCampusviewImg">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCampusviewImg" value="console:editCampusviewImg"/>
															<label for="editCampusviewImg">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCampusviewImg" value="console:deleteCampusviewImg"/>
															<label for="deleteCampusviewImg">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--start 班级相册管理 -->
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="classAlbumImage" value="console:classAlbumImage"/>
													        <label for="classAlbumImage">
													           ${message("班级相册")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addClassAlbumImage" value="console:addClassAlbumImage"/>
															<label for="addClassAlbumImage">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editClassAlbumImage" value="console:editClassAlbumImage" />
															<label for="editClassAlbumImage">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteClassAlbumImage" value="console:deleteClassAlbumImage"/>
															<label for="deleteClassAlbumImage">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--end   班级相册管理 -->
											
											<!--
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="template" value="console:template"/>
													        <label for="template">
															   ${message("console.role.template")}
															</label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="templateAEdit" value="console:template_a_edit"/>
														    <label for="templateAEdit">
															   ${message("console.common.edit")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													       <input type="checkbox" name="authorities" id="cache" value="console:cache"/>
													       <label for="cache">
															   ${message("console.role.cache")}
															</label>
													    </li>
												    </ul>
												</td>
												<td>
												    &nbsp;
												</td>
											</tr>
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													       <input type="checkbox" name="authorities" value="console:static" />${message("console.role.static")}
													    </li>
												    </ul>
												</td>
												<td>
												    &nbsp;
												</td>
											</tr>
											<tr class="contentGroup">
											    <td>
												    <ul>
													    <li>
													       <input type="checkbox" name="authorities" value="console:index"/>${message("console.role.index")}
													    </li>
												    </ul>
												</td>
												<td>
												    &nbsp;
												</td>
											</tr>-->
											<!-- end 内容管理 -->
											
											<!-- 
											<tr class="authorities">
												<th>
													${message("console.role.marketingGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="marketingGroup">
												<th rowspan="2">
												    <input type="button" class="btn btn-primary btn-xs"  id="marketingGroupSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
													<ul>
													    <li>
													        <input type="checkbox" name="authorities" id="seo" value="console:seo" />
															<label for="seo">
															   ${message("console.role.seo")}
															</label>
													    </li>
													</ul>
											 	</td>
											 	<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="seoAEdit" value="console:seo_a_edit" />
														    <label for="seoAEdit">
															    ${message("console.common.edit")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="marketingGroup">
												<th >
												  <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="sitemap" value="console:sitemap"/>
													        <label for="sitemap">
															   ${message("console.role.sitemap")}
															</label>
													    </li>
													</ul>
												</th>
											 	<td>
													&nbsp;
												</td>
											</tr>
											-->
											
											<!-- start 反馈管理-->
											<tr class="authorities">
												<th>
													${message("反馈管理")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="feedbackGroup">
												<th rowspan="1">
													 <input type="button" class="btn btn-primary btn-xs" id="feedbackSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="systemSuggest" name="authorities" value="console:systemSuggest" />
													       <label for="systemSuggest">
													           ${message("意见反馈")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												    </ul>
												</td>
											</tr>
											
											<!-- end 反馈管理 -->
											
											<!-- start 应用管理-->
											<tr class="authorities">
												<th>
													${message("console.main.app")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="appGroup">
												<th rowspan="5">
													 <input type="button" class="btn btn-primary btn-xs" id="appSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="app" name="authorities" value="console:app" />
													       <label for="app">
													           ${message("console.main.app")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addApp" value="console:addApp"/>
															<label for="addApp">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editApp" value="console:editApp" />
														    <label for="editApp">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appShelve" value="console:appShelve"/>
														    <label for="appShelve">
															    ${message("上下架")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appCredential" value="console:appCredential"/>
														    <label for="appCredential">
															    ${message("API授权")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appUpgrade" value="console:appUpgrade"/>
														    <label for="appUpgrade">
															    ${message("升级")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appVersionList" value="console:appVersionList"/>
														    <label for="appVersionList">
															    ${message("版本回退")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appAuthorization" value="console:appAuthorization"/>
														    <label for="appAuthorization">
															    ${message("应用授权")}
															</label>
														</li>
														
													<!--	<li>
															<input type="checkbox" name="authorities" id="deleteMeritTemplate" value="console:deleteMeritTemplate" />
															<label for="deleteMeritTemplate">
													           ${message("console.common.delete")}
													       </label>
														</li> -->
												    </ul>
												</td>
											</tr>
											<tr class="appGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="appRole" name="authorities" value="console:appRole" />
													       <label for="appRole">
													           ${message("console.main.appRole")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppRole"  value="console:addAppRole"/>
															<label for="addAppRole">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppRole" value="console:editAppRole" />
														    <label for="editAppRole">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppRole" value="console:deleteAppRole" />
															<label for="deleteAppRole">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="appGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="appCategory" name="authorities" value="console:appCategory" />
													       <label for="appCategory">
													           ${message("console.main.appCategory")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppCategory"  value="console:addAppCategory"/>
															<label for="addAppCategory">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppCategory" value="console:editAppCategory" />
														    <label for="editAppCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppCategory" value="console:deleteAppCategory" />
															<label for="deleteAppCategory">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="appGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="appClientVersion" name="authorities" value="console:appClientVersion" />
													       <label for="appClientVersion">
													           ${message("console.main.appClientVersion")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppClientVersion"  value="console:addAppClientVersion"/>
															<label for="addAppClientVersion">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppClientVersion" value="console:editAppClientVersion" />
														    <label for="editAppClientVersion">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppClientVersion" value="console:deleteAppClientVersion" />
															<label for="deleteAppClientVersion">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="appGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="appPoster" name="authorities" value="console:appPoster" />
													       <label for="appPoster">
													           ${message("console.main.appPoster")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppPoster"  value="console:addAppPoster"/>
															<label for="addAppPoster">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppPoster" value="console:editAppPoster" />
														    <label for="editAppPoster">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppPoster" value="console:deleteAppPoster" />
															<label for="deleteAppPoster">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end 应用管理 -->
											<!-- start广告管理 -->
											<tr class="authorities">
												<th>
													${message("广告管理")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="adGroup">
												<th rowspan="2">
													 <input type="button" class="btn btn-primary btn-xs" id="adSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="ad" name="authorities" value="console:ad"  />
													       <label for="ad">
													           ${message("广告管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												    	<li>
															<input type="checkbox" name="authorities" id="addAd"  value="console:addAd" />
															<label for="addAd">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAd" value="console:editAd" />
														    <label for="editAd">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAd" value="console:deleteAd"/>
															<label for="deleteAd">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="adShelve" value="console:onLineChange"/>
															<label for="onLineAd">
													           ${message("console.common.onLine")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<tr class="adGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="adCategory" name="authorities" value="console:adCategory" />
													       <label for="adCategory">
													           ${message("广告分类管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAdCategory"  value="console:addAdCategory" />
															<label for="addAdCategory">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAdCategory" value="console:editAdCategory" />
														    <label for="editAdCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAdCategory" value="console:deleteAdCategory" />
															<label for="deleteAdCategory">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="unableAdCategory" value="console:unableAdCategory" />
															<label for="unableAdCategory">
													           ${message("禁用")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end  广告管理 -->
											<!-- start 系统设置 -->
											<tr class="authorities">
												<th>
													${message("console.role.systemGroup")}
												</th>
												<td>
													${message("console.common.menuRole")}
												</td>
												<td>
													${message("console.common.buttonAttribute")}
												</td>
											</tr>
											<tr class="systemGroup">
											    <th rowspan="9">
												    <input type="button" class="btn btn-primary btn-xs selectAllTable" id="systemGroupSelectAll"  value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
													<ul>
													    <li>
													        <input type="checkbox" name="authorities" id="setting" value="console:setting"/>
															<label for="setting">
															    ${message("console.role.setting")}
															</label>
													    </li>
													</ul>
											 	</td>
											 	<td>
													&nbsp;
												</td>
											</tr>
											
											<!-- start数据字典 -->
											<tr class="systemGroup">
											    <td>
												    <ul>
													    <li>
													        <input type="checkbox" name="authorities" id="systemDict" value="console:systemDict" />
													        <label for="systemDict">
													           ${message("数据字典")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
											   		<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSystemDict" value="console:addSystemDict"/>
															<label for="addSystemDict">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSystemDict" value="console:editSystemDict"/>
															<label for="editSystemDict">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSystemDict" value="console:deleteSystemDict"/>
															<label for="deleteSystemDict">
															   ${message("console.common.delete")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end  数据字典 -->
											
											<!-- 地区管理 -->
											<tr class="systemGroup">
												<td>
													<ul>
													    <li>
													        <input type="checkbox" name="authorities" id="area" value="console:area" />
													        <label for="area">
													            ${message("console.role.area")}
													        </label>
													    </li>
													</ul>
											 	</td>
											 	<td>
													&nbsp;
												</td>
											</tr>
											
											<!-- 支付插件 -->
											<tr class="systemGroup">
											    <td>
													<ul>
													    <li>
													        <input type="checkbox" name="authorities" id="paymentPlugin" value="console:paymentPlugin"/>
													        <label for="paymentPlugin">
													            ${message("console.role.paymentPlugin")}
													        </label>
													    </li>
													</ul>
											 	</td>
											 	<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_install" />${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_setting"/>${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_uninstall"/>${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 存储插件 -->
											<tr class="systemGroup">
											    <td>
												    <ul>
														<li>
													         <input type="checkbox" name="authorities" id="storagePlugin" value="console:storagePlugin"/>
														     <label for="storagePlugin">
														        ${message("console.role.storagePlugin")}
														    </label>
												     </li>
													</ul>
											    </td>
											    <td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_install"/>${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_setting" />${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_uninstall"/>${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 消息插件 -->
											<tr class="systemGroup">
											    <td>
												    <ul>
														<li>
													         <input type="checkbox" name="authorities" id="messagePushPlugin" value="console:messagePushPlugin"/>
														     <label for="messagePushPlugin">
														        ${message("console.role.messagePushPlugin")}
														    </label>
												     </li>
													</ul>
											    </td>
											    <td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:installMessagePushPlugin"/>${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:settingMessagePushPlugin" />${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:uninstallMessagePushPlugin"/>${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 管理员管理 -->
											<tr class="systemGroup">
											    <td>
											    	<ul>
													  <li>
													    <input type="checkbox" name="authorities" id="admin" value="console:admin" />
													    <label for="admin">
													        ${message("console.role.admin")}
													    </label>
												    </li>
													</ul>
												</td>		
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:admin_icon_addIcon"/>${message("console.common.add")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:admin_button_delteButton"/>${message("console.common.delete")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:admin_a_edit"/>${message("console.common.edit")}
														</li>
												    </ul>
												</td>		
											</tr>
											<!-- 角色管理 -->
											<tr class="systemGroup">
											     <td>
											     <ul>
													  <li>
												   <input type="checkbox" name="authorities" id="role" value="console:role" />
												   <label for="role">
												        ${message("console.role.role")}
												   </label>
												   </li>
													</ul>
												</td>
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:role_icon_addIcon"/>${message("console.common.add")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:role_button_deleteButton"/>${message("console.common.delete")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:role_a_edit"/>${message("console.common.edit")}
														</li>
												    </ul>
												</td>		
											</tr>
											<!-- 日志管理 -->
											<tr class="systemGroup">
											    <td>
											       <ul>
													<li>
													     <input type="checkbox" name="authorities" id="log" value="console:log"/>
														 <label for="log">
														     ${message("console.role.log")}
														 </label>
													 </li>
													</ul>
												</td>
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:log_button_clearButton" />${message("console.common.clear")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:log_button_deleteButton"/>${message("console.common.delete")}
														</li>
												    </ul>
												</td>			
											</tr>
											<!-- end 系统设置 -->
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td colspan="2">
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

	<form id="inputForm" action="update.ct" method="post">
		
		
	</form>
</body>
</html>