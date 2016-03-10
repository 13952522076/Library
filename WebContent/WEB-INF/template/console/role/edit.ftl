<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.role.edit")} - 小书僮™智慧幼教管理平台</title>
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
	/**内容管理*/
	var $contentGroupSelectAll = $("#contentGroupSelectAll");
	var $marketingGroupSelectAll = $("#marketingGroupSelectAll");
	var $systemGroupSelectAll = $("#systemGroupSelectAll");
    var $meritTemplateSelectAll = $("#meritTemplateSelectAll");
    var $campusGroupSelectAll = $("#campusGroupSelectAll");
	var $curriculumScheduleGroupSelectAll = $("#curriculumScheduleGroupSelectAll");
	var $attendanceSelectAll = $("#attendanceSelectAll");
	var $recipeSelectAll = $("#recipeSelectAll");
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
	
	/**课程表管理*/
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
	$attendanceSelectAll.click(function(){
	     var $thisCheckbox =  $(".attendanceGroup").find(":checkbox");
	     if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		 } else {
			$thisCheckbox.prop("checked", true);
		 }
		 return false;
	});
	
	/**食谱管理*/
	$recipeSelectAll.click(function(){
	     var $thisCheckbox =  $(".recipesGroup").find(":checkbox");
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
                    <h2>${message("console.role.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.role.edit")}</strong>
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
	                 <input type="hidden" name="id" value="${role.id}" />
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
													       <input type="checkbox" id="consoleMember" name="authorities" value="console:member"[#if role.authorities?seq_contains("console:member")] checked="checked"[/#if] />
													       <lable for="consoleMember">
													           ${message("console.role.member")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="memberIconAddIcon"  value="console:member_icon_addIcon"[#if role.authorities?seq_contains("console:member_icon_addIcon")] checked="checked"[/#if] />
															<lable for="memberIconAddIcon">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberAEdit" value="console:member_a_edit"[#if role.authorities?seq_contains("console:member_a_edit")] checked="checked"[/#if] />
														    <label for="memberAEdit">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberButtonDeleteButton" value="console:member_button_deleteButton"[#if role.authorities?seq_contains("console:member_button_deleteButton")] checked="checked"[/#if] />
															<lable for="memberButtonDeleteButton">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="memberButtonExportButton" value="console:member_button_exportButton"[#if role.authorities?seq_contains("console:member_button_exportButton")] checked="checked"[/#if] />
															<label for="memberButtonExportButton">
															    ${message("console.common.export")}
															</label>
														</li>
												    </ul>
												</td>
											</tr>
											
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
													       <input type="checkbox" id="campusMng" name="authorities" value="console:dictSchool"  [#if role.authorities?seq_contains("console:dictSchool")] checked="checked"[/#if]/>
													       <label for="campusMng">
													           ${message("console.role.campusMng")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictSchool"  value="console:addDictSchool"   [#if role.authorities?seq_contains("console:addDictSchool")] checked="checked"[/#if]/>
															<label for="addDictSchool">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictSchool" value="console:editDictSchool"  [#if role.authorities?seq_contains("console:editDictSchool")] checked="checked"[/#if]/>
														    <label for="editDictSchool">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictSchool" value="console:deleteDictSchool" [#if role.authorities?seq_contains("console:deleteDictSchool")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="dictGrade" name="authorities" value="console:dictGrade" [#if role.authorities?seq_contains("console:dictGrade")] checked="checked"[/#if]/>
													       <label for="dictGrade">
													           ${message("年级管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictGrade"  value="console:addDictGrade" [#if role.authorities?seq_contains("console:addDictGrade")] checked="checked"[/#if]/>
															<label for="addDictGrade">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictGrade" value="console:editDictGrade" [#if role.authorities?seq_contains("console:editDictGrade")] checked="checked"[/#if]/>
														    <label for="editDictGrade">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictGrade" value="console:deleteDictGrade" [#if role.authorities?seq_contains("console:deleteDictGrade")] checked="checked"[/#if] />
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
													       <input type="checkbox" id="dictClass" name="authorities" value="console:dictClass" [#if role.authorities?seq_contains("console:dictClass")] checked="checked"[/#if]/>
													       <label for="dictClass">
													           ${message("console.role.dictClass")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictClass"  value="console:addDictClass" [#if role.authorities?seq_contains("console:addDictClass")] checked="checked"[/#if]/>
															<label for="addDictClass">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictClass" value="console:editDictClass" [#if role.authorities?seq_contains("console:editDictClass")] checked="checked"[/#if]/>
														    <label for="editDictClass">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictClass" value="console:deleteDictClass" [#if role.authorities?seq_contains("console:deleteDictClass")] checked="checked"[/#if] />
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
													       <input type="checkbox" id="teacher" name="authorities" value="console:teacher"  [#if role.authorities?seq_contains("console:teacher")] checked="checked"[/#if] />
													       <label for="teacher">
													           ${message("老师管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addTeacher"  value="console:addTeacher"  [#if role.authorities?seq_contains("console:addTeacher")] checked="checked"[/#if] />
															<label for="addTeacher">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editTeacher" value="console:editTeacher"  [#if role.authorities?seq_contains("console:editTeacher")] checked="checked"[/#if] />
														    <label for="editTeacher">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteTeacher" value="console:deleteTeacher"  [#if role.authorities?seq_contains("console:deleteTeacher")] checked="checked"[/#if]/>
															<label for="deleteTeacher">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--家长管理-->
											<tr class="campusGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="patriarch" name="authorities" value="console:patriarch"  [#if role.authorities?seq_contains("console:patriarch")] checked="checked"[/#if] />
													       <label for="patriarch">
													           ${message("家长管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addPatriarch"  value="console:addPatriarch"  [#if role.authorities?seq_contains("console:addPatriarch")] checked="checked"[/#if] />
															<label for="addPatriarch">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editPatriarch" value="console:editPatriarch"  [#if role.authorities?seq_contains("console:editPatriarch")] checked="checked"[/#if] />
														    <label for="editPatriarch">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deletePatriarch" value="console:deletePatriarch"  [#if role.authorities?seq_contains("console:deletePatriarch")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="dictStudent" name="authorities" value="console:dictStudent"  [#if role.authorities?seq_contains("console:dictStudent")] checked="checked"[/#if] />
													       <label for="dictStudent">
													           ${message("console.role.dictStudent")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDictStudent"  value="console:addDictStudent"  [#if role.authorities?seq_contains("console:addDictStudent")] checked="checked"[/#if] />
															<label for="addDictStudent">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDictStudent" value="console:editDictStudent"  [#if role.authorities?seq_contains("console:editDictStudent")] checked="checked"[/#if] />
														    <label for="editDictStudent">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDictStudent" value="console:deleteDictStudent"  [#if role.authorities?seq_contains("console:deleteDictStudent")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="curriculumSchedule" name="authorities" value="console:curriculumSchedule"  [#if role.authorities?seq_contains("console:curriculumSchedule")] checked="checked"[/#if]/>
													       <label for="curriculumSchedule">
													           ${message("console.role.curriculumSchedule")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCurriculumSchedule" value="console:addCurriculumSchedule" [#if role.authorities?seq_contains("console:addCurriculumSchedule")] checked="checked"[/#if]/>
															<label for="addCurriculumSchedule">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCurriculumSchedule" value="console:editCurriculumSchedule" [#if role.authorities?seq_contains("console:editCurriculumSchedule")] checked="checked"[/#if]/>
														    <label for="editCurriculumSchedule">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCurriculumSchedule" value="console:deleteCurriculumSchedule" [#if role.authorities?seq_contains("console:deleteCurriculumSchedule")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="courseName" name="authorities" value="console:courseName"  [#if role.authorities?seq_contains("console:courseName")] checked="checked"[/#if]/>
													       <label for="courseName">
													           ${message("console.role.courseName")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCourseName" value="console:addCourseName" [#if role.authorities?seq_contains("console:addCourseName")] checked="checked"[/#if]/>
															<label for="addCourseName">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCourseName" value="console:editCourseName" [#if role.authorities?seq_contains("console:editCourseName")] checked="checked"[/#if]/>
														    <label for="editCourseName">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCourseName" value="console:deleteCourseName" [#if role.authorities?seq_contains("console:deleteCourseName")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="qualityCourse" name="authorities" value="console:qualityCourse" [#if role.authorities?seq_contains("console:qualityCourse")] checked="checked"[/#if]/>
													       <label for="qualityCourse">
													           ${message("console.role.qualityCourse")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addQualityCourse" value="console:addQualityCourse" [#if role.authorities?seq_contains("console:addQualityCourse")] checked="checked"[/#if]/>
															<label for="addQualityCourse">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editQualityCourse" value="console:editQualityCourse"  [#if role.authorities?seq_contains("console:editQualityCourse")] checked="checked"[/#if]/>
														    <label for="editQualityCourse">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteQualityCourse" value="console:deleteQualityCourse" [#if role.authorities?seq_contains("console:deleteQualityCourse")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="weeklyPlanSection" name="authorities" value="console:weeklyPlanSection"  [#if role.authorities?seq_contains("console:weeklyPlanSection")] checked="checked"[/#if]/>
													       <label for="weeklyPlanSection">
													           ${message("周计划")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWeeklyPlanSection" value="console:addWeeklyPlanSection" [#if role.authorities?seq_contains("console:addWeeklyPlanSection")] checked="checked"[/#if]/>
															<label for="addWeeklyPlanSection">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWeeklyPlanSection" value="console:editWeeklyPlanSection" [#if role.authorities?seq_contains("console:editWeeklyPlanSection")] checked="checked"[/#if]/>
														    <label for="editWeeklyPlanSection">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWeeklyPlanSection" value="console:deleteWeeklyPlanSection" [#if role.authorities?seq_contains("console:deleteWeeklyPlanSection")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="schoolYearMng" name="authorities" value="console:schoolYearMng" [#if role.authorities?seq_contains("console:schoolYearMng")] checked="checked"[/#if]/>
													       <label for="schoolYearMng">
													           ${message("console.role.schoolYearMng")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSchoolYearMng"  value="console:addSchoolYearMng" [#if role.authorities?seq_contains("console:addSchoolYearMng")] checked="checked"[/#if]/>
															<label for="addSchoolYearMng">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSchoolYearMng" value="console:editSchoolYearMng" [#if role.authorities?seq_contains("console:editSchoolYearMng")] checked="checked"[/#if] />
														    <label for="editSchoolYearMng">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSchoolYearMng" value="console:deleteSchoolYearMng" [#if role.authorities?seq_contains("console:deleteSchoolYearMng")] checked="checked"[/#if] />
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
													 <input type="button" class="btn btn-primary btn-xs" id="attendanceSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="attendance" name="authorities" value="console:attendance"  [#if role.authorities?seq_contains("console:attendance")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="askLeave" name="authorities" value="console:askLeave"  [#if role.authorities?seq_contains("console:askLeave")] checked="checked"[/#if]/>
													       <label for="askLeave">
													           ${message("console.role.askLeave")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAskLeave" value="console:addAskLeave" [#if role.authorities?seq_contains("console:addAskLeave")] checked="checked"[/#if]/>
															<label for="addAskLeave">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAskLeave" value="console:editAskLeave" [#if role.authorities?seq_contains("console:editAskLeave")] checked="checked"[/#if] />
														    <label for="editAskLeave">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAskLeave" value="console:deleteAskLeave" [#if role.authorities?seq_contains("console:deleteAskLeave")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="teacherAskLeave" name="authorities" value="console:teacherAskLeave"  [#if role.authorities?seq_contains("console:teacherAskLeave")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="attendanceEquipment" name="authorities" value="console:attendanceEquipment" [#if role.authorities?seq_contains("console:attendanceEquipment")] checked="checked"[/#if]/>
													       <label for="attendanceEquipment">
													           ${message("console.role.attendanceEquipment")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAttendanceEquipment"  value="console:addAttendanceEquipment" [#if role.authorities?seq_contains("console:addAttendanceEquipment")] checked="checked"[/#if]/>
															<label for="addAttendanceEquipment">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAttendanceEquipment" value="console:editAttendanceEquipment" [#if role.authorities?seq_contains("console:editAttendanceEquipment")] checked="checked"[/#if]/>
														    <label for="editAttendanceEquipment">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAttendanceEquipment" value="console:deleteAttendanceEquipment" [#if role.authorities?seq_contains("console:deleteAttendanceEquipment")] checked="checked"[/#if]/>
															<label for="deleteAttendanceEquipment">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!--班次管理-->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="workSetting" name="authorities" value="console:workSetting" [#if role.authorities?seq_contains("console:workSetting")] checked="checked"[/#if]/>
													       <label for="workSetting">
													           ${message("班次管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWorkSetting"  value="console:addWorkSetting" [#if role.authorities?seq_contains("console:addWorkSetting")] checked="checked"[/#if]/>
															<label for="addWorkSetting">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWorkSetting" value="console:editWorkSetting" [#if role.authorities?seq_contains("console:editWorkSetting")] checked="checked"[/#if]/>
														    <label for="editWorkSetting">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWorkSetting" value="console:deleteWorkSetting" [#if role.authorities?seq_contains("console:deleteWorkSetting")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="workScheduling" name="authorities" value="console:workScheduling" [#if role.authorities?seq_contains("console:workScheduling")] checked="checked"[/#if]/>
													       <label for="workScheduling">
													           ${message("排班管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addWorkScheduling"  value="console:addWorkScheduling" [#if role.authorities?seq_contains("console:addWorkScheduling")] checked="checked"[/#if]/>
															<label for="addWorkScheduling">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editWorkScheduling" value="console:editWorkScheduling" [#if role.authorities?seq_contains("console:editWorkScheduling")] checked="checked"[/#if]/>
														    <label for="editWorkScheduling">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteWorkScheduling" value="console:deleteWorkScheduling" [#if role.authorities?seq_contains("console:deleteWorkScheduling")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="teacherAttendance" name="authorities" value="console:teacherAttendance" [#if role.authorities?seq_contains("console:teacherAttendance")] checked="checked"[/#if]/>
													       <label for="teacherAttendance">
													           ${message("教师考勤")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    
												</td>
											</tr>
											<!-- 考勤统计 -->
											<tr class="attendanceGroup">
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="attendanceStatistics" name="authorities" value="console:attendanceStatistics" [#if role.authorities?seq_contains("console:attendanceStatistics")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="schoolHours" name="authorities" value="console:schoolHours" [#if role.authorities?seq_contains("console:schoolHours")] checked="checked"[/#if]/>
													       <label for="schoolHours">
													           ${message("console.role.schoolHours")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSchoolHours"  value="console:addSchoolHours" [#if role.authorities?seq_contains("console:addSchoolHours")] checked="checked"[/#if]/>
															<label for="addSchoolHours">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSchoolHours" value="console:editSchoolHours" [#if role.authorities?seq_contains("console:editSchoolHours")] checked="checked"[/#if]/>
														    <label for="editSchoolHours">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSchoolHours" value="console:deleteSchoolHours" [#if role.authorities?seq_contains("console:deleteSchoolHours")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="meritTemplate" name="authorities" value="console:meritTemplate"  [#if role.authorities?seq_contains("console:meritTemplate")] checked="checked"[/#if]/>
													       <label for="meritTemplate">
													           ${message("console.role.meritTemplate")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addMeritTemplate" value="console:addMeritTemplate" [#if role.authorities?seq_contains("console:addMeritTemplate")] checked="checked"[/#if]/>
															<label for="addMeritTemplate">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editMeritTemplate" value="console:editMeritTemplate" [#if role.authorities?seq_contains("console:editMeritTemplate")] checked="checked"[/#if]/>
														    <label for="editMeritTemplate">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteMeritTemplate" value="console:deleteMeritTemplate" [#if role.authorities?seq_contains("console:deleteMeritTemplate")] checked="checked"[/#if] />
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
													       <input type="checkbox" id="overallMerit" name="authorities" value="console:overallMerit"  [#if role.authorities?seq_contains("console:overallMerit")] checked="checked"[/#if] />
													       <label for="overallMerit">
													           ${message("console.role.overallMerit")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addOverallMerit"  value="console:addOverallMerit"  [#if role.authorities?seq_contains("console:addOverallMerit")] checked="checked"[/#if] />
															<label for="addOverallMerit">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editOverallMerit" value="console:editOverallMerit"  [#if role.authorities?seq_contains("console:editOverallMerit")] checked="checked"[/#if] />
														    <label for="editOverallMerit">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteOverallMerit" value="console:deleteOverallMerit"  [#if role.authorities?seq_contains("console:deleteOverallMerit")] checked="checked"[/#if] />
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
													 <input type="button" class="btn btn-primary btn-xs" id="recipeSelectAll" value="${message("console.common.selectAllOrNot")}">
												</th>
												<td>
												    <ul>
													    <li>
													       <input type="checkbox" id="recipes" name="authorities" value="console:recipes" [#if role.authorities?seq_contains("console:recipes")] checked="checked"[/#if]/>
													       <label for="recipes">
													           ${message("console.role.recipes")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
													<ul class="authorities">
													  	<li>
															<input type="checkbox" name="authorities" id="addRecipe" value="console:addRecipe" [#if role.authorities?seq_contains("console:addRecipe")] checked="checked"[/#if]/>
															<label for="addRecipe">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipe" value="console:editRecipe" [#if role.authorities?seq_contains("console:editRecipe")] checked="checked"[/#if]/>
														    <label for="editRecipe">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipe" value="console:deleteRecipe" [#if role.authorities?seq_contains("console:deleteRecipe")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="recipeSectionManager" name="authorities" value="console:recipeSectionManager" [#if role.authorities?seq_contains("console:recipeSectionManager")] checked="checked"[/#if]/>
													       <label for="recipeSectionManager">
													           ${message("console.role.recipeSectionManager")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addRecipeSectionManager" value="console:addRecipeSectionManager" [#if role.authorities?seq_contains("console:addRecipeSectionManager")] checked="checked"[/#if]/>
															<label for="addRecipeSectionManager">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipeSectionManager" value="console:editRecipeSectionManager" [#if role.authorities?seq_contains("console:editRecipeSectionManager")] checked="checked"[/#if]/>
														    <label for="editRecipeSectionManager">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipeSectionManager" value="console:deleteRecipeSectionManager" [#if role.authorities?seq_contains("console:deleteRecipeSectionManager")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="recipeWeekManager" name="authorities" value="console:recipeWeekManager" [#if role.authorities?seq_contains("console:recipeWeekManager")] checked="checked"[/#if]/>
													       <label for="recipeWeekManager">
													           ${message("console.role.recipeWeekManager")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addRecipeWeekManager"  value="console:addRecipeWeekManager" [#if role.authorities?seq_contains("console:addRecipeWeekManager")] checked="checked"[/#if]/>
															<label for="addRecipeWeekManager">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editRecipeWeekManager" value="console:editRecipeWeekManager" [#if role.authorities?seq_contains("console:editRecipeWeekManager")] checked="checked"[/#if]/>
														    <label for="editRecipeWeekManager">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteRecipeWeekManager" value="console:deleteRecipeWeekManager" [#if role.authorities?seq_contains("console:deleteRecipeWeekManager")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="growthDiary" name="authorities" value="console:growthDiary"  [#if role.authorities?seq_contains("console:growthDiary")] checked="checked"[/#if]/>
													       <label for="growthDiary">
													           ${message("成长记")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="deleteGrowthDiary" value="console:deleteGrowthDiary" [#if role.authorities?seq_contains("console:deleteGrowthDiary")] checked="checked"[/#if]/>
															<label for="deleteGrowthDiary">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="detailGrowthDiary" value="console:detailGrowthDiary" [#if role.authorities?seq_contains("console:detailGrowthDiary")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="diaryTag" name="authorities" value="console:diaryTag"  [#if role.authorities?seq_contains("console:diaryTag")] checked="checked"[/#if] />
													       <label for="diaryTag">
													           ${message("成长记标签")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addDiaryTag"  value="console:addDiaryTag"  [#if role.authorities?seq_contains("console:addDiaryTag")] checked="checked"[/#if] />
															<label for="addDiaryTag">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editDiaryTag" value="console:editDiaryTag"  [#if role.authorities?seq_contains("console:editDiaryTag")] checked="checked"[/#if] />
														    <label for="editDiaryTag">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteDiaryTag" value="console:deleteDiaryTag"  [#if role.authorities?seq_contains("console:deleteDiaryTag")] checked="checked"[/#if] />
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
													       <input type="checkbox" id="diaryPicture" name="authorities" value="console:diaryPicture"  [#if role.authorities?seq_contains("console:diaryPicture")] checked="checked"[/#if] />
													       <label for="diaryPicture">
													           ${message("成长记图片")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
														<li>
															<input type="checkbox" name="authorities" id="deleteDiaryPicture" value="console:deleteDiaryPicture"  [#if role.authorities?seq_contains("console:deleteDiaryPicture")] checked="checked"[/#if] />
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
													       <input type="checkbox" name="authorities" id="news" value="console:news" [#if role.authorities?seq_contains("console:news")] checked="checked"[/#if]/>
													       <label for="news">
													          ${message("console.role.news")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addNews" value="console:addNews" [#if role.authorities?seq_contains("console:addNews")] checked="checked"[/#if]/>
															<label for="addNews">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities"  id="editNews" value="console:editNews" [#if role.authorities?seq_contains("console:editNews")] checked="checked"[/#if]/>
															<label for="editNews">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteNews" value="console:deleteNews" [#if role.authorities?seq_contains("console:deleteNews")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="newsCategoryMng" value="console:newsCategoryMng" [#if role.authorities?seq_contains("console:newsCategoryMng")] checked="checked"[/#if]/>
													        <label for="newsCategoryMng">
													           ${message("新闻类别管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addNewsCategory" value="console:addNewsCategory" [#if role.authorities?seq_contains("console:addNewsCategory")] checked="checked"[/#if]/>
															<label for="addNewsCategory">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editNewsCategory" value="console:editNewsCategory" [#if role.authorities?seq_contains("console:editNewsCategory")] checked="checked"[/#if]/>
															<label for="editNewsCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteNewsCategory" value="console:deleteNewsCategory" [#if role.authorities?seq_contains("console:deleteNewsCategory")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="parenting" value="console:parenting" [#if role.authorities?seq_contains("console:parenting")] checked="checked"[/#if]/>
													        <label for="parenting">
													           ${message("育儿中心")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addParenting" value="console:addParenting" [#if role.authorities?seq_contains("console:addParenting")] checked="checked"[/#if]/>
															<label for="addParenting">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editParenting" value="console:editParenting" [#if role.authorities?seq_contains("console:editParenting")] checked="checked"[/#if]/>
															<label for="editParenting">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteParenting" value="console:deleteParenting" [#if role.authorities?seq_contains("console:deleteParenting")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="parentingCategoryMng" value="console:parentingCategoryMng" [#if role.authorities?seq_contains("console:parentingCategoryMng")] checked="checked"[/#if]/>
													        <label for="parentingCategoryMng">
													           ${message("育儿类别管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addParentingCategory" value="console:addParentingCategory" [#if role.authorities?seq_contains("console:addParentingCategory")] checked="checked"[/#if]/>
															<label for="addParentingCategory">
															    ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editParentingCategory" value="console:editParentingCategory" [#if role.authorities?seq_contains("console:editParentingCategory")] checked="checked"[/#if]/>
															<label for="editParentingCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteParentingCategory" value="console:deleteParentingCategory" [#if role.authorities?seq_contains("console:deleteParentingCategory")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="poster" value="console:poster" [#if role.authorities?seq_contains("console:poster")] checked="checked"[/#if]/>
													        <label for="poster">
													           ${message("海报管理")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addPoster" value="console:addPoster" [#if role.authorities?seq_contains("console:addPoster")] checked="checked"[/#if]/>
															<label for="addPoster">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editPoster" value="console:editPoster" [#if role.authorities?seq_contains("console:editPoster")] checked="checked"[/#if]/>
															<label for="editPoster">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deletePoster" value="console:deletePoster" [#if role.authorities?seq_contains("console:deletePoster")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="announcement" value="console:announcement" [#if role.authorities?seq_contains("console:announcement")] checked="checked"[/#if]/>
													        <label for="announcement">
													           ${message("console.role.announcement")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAnnouncement" value="console:addAnnouncement" [#if role.authorities?seq_contains("console:addAnnouncement")] checked="checked"[/#if]/>
															<label for="addAnnouncement">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAnnouncement" value="console:editAnnouncement" [#if role.authorities?seq_contains("console:editAnnouncement")] checked="checked"[/#if]/>
															<label for="editAnnouncement">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAnnouncement" value="console:deleteAnnouncement" [#if role.authorities?seq_contains("console:deleteAnnouncement")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="profile" value="console:profile" [#if role.authorities?seq_contains("console:profile")] checked="checked"[/#if]/>
													        <label for="profile">
													           ${message("console.role.profile")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addProfile" value="console:addProfile" [#if role.authorities?seq_contains("console:addProfile")] checked="checked"[/#if]/>
															<label for="addProfile">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editProfile" value="console:editProfile" [#if role.authorities?seq_contains("console:editProfile")] checked="checked"[/#if]/>
															<label for="editProfile">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteProfile" value="console:deleteProfile" [#if role.authorities?seq_contains("console:deleteProfile")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="campusviewImg" value="console:campusviewImg" [#if role.authorities?seq_contains("console:campusviewImg")] checked="checked"[/#if]/>
													        <label for="campusviewImg">
													           ${message("console.role.campusviewImg")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addCampusviewImg" value="console:addCampusviewImg" [#if role.authorities?seq_contains("console:addCampusviewImg")] checked="checked"[/#if]/>
															<label for="addCampusviewImg">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editCampusviewImg" value="console:editCampusviewImg" [#if role.authorities?seq_contains("console:editCampusviewImg")] checked="checked"[/#if]/>
															<label for="editCampusviewImg">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteCampusviewImg" value="console:deleteCampusviewImg" [#if role.authorities?seq_contains("console:deleteCampusviewImg")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="classAlbumImage" value="console:classAlbumImage" [#if role.authorities?seq_contains("console:classAlbumImage")] checked="checked"[/#if]/>
													        <label for="classAlbumImage">
													           ${message("班级相册")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
												   <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addClassAlbumImage" value="console:addClassAlbumImage" [#if role.authorities?seq_contains("console:addClassAlbumImage")] checked="checked"[/#if]/>
															<label for="addClassAlbumImage">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editClassAlbumImage" value="console:editClassAlbumImage" [#if role.authorities?seq_contains("console:editClassAlbumImage")] checked="checked"[/#if]/>
															<label for="editClassAlbumImage">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteClassAlbumImage" value="console:deleteClassAlbumImage" [#if role.authorities?seq_contains("console:deleteClassAlbumImage")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="systemSuggest" name="authorities" value="console:systemSuggest"  [#if role.authorities?seq_contains("console:systemSuggest")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="app" name="authorities" value="console:app" [#if role.authorities?seq_contains("console:app")] checked="checked"[/#if]/>
													       <label for="app">
													           ${message("console.main.app")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addApp" value="console:addApp" [#if role.authorities?seq_contains("console:addApp")] checked="checked"[/#if]/>
															<label for="addApp">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editApp" value="console:editApp" [#if role.authorities?seq_contains("console:editApp")] checked="checked"[/#if]/>
														    <label for="editApp">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appShelve" value="console:appShelve" [#if role.authorities?seq_contains("console:appShelve")] checked="checked"[/#if]/>
														    <label for="appShelve">
															    ${message("上下架")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appCredential" value="console:appCredential" [#if role.authorities?seq_contains("console:appCredential")] checked="checked"[/#if]/>
														    <label for="appCredential">
															    ${message("API授权")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appUpgrade" value="console:appUpgrade" [#if role.authorities?seq_contains("console:appUpgrade")] checked="checked"[/#if]/>
														    <label for="appUpgrade">
															    ${message("升级")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appVersionList" value="console:appVersionList" [#if role.authorities?seq_contains("console:appVersionList")] checked="checked"[/#if]/>
														    <label for="appVersionList">
															    ${message("版本回退")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="appAuthorization" value="console:appAuthorization" [#if role.authorities?seq_contains("console:appAuthorization")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="appRole" name="authorities" value="console:appRole" [#if role.authorities?seq_contains("console:appRole")] checked="checked"[/#if]/>
													       <label for="appRole">
													           ${message("console.main.appRole")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppRole"  value="console:addAppRole" [#if role.authorities?seq_contains("console:addAppRole")] checked="checked"[/#if]/>
															<label for="addAppRole">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppRole" value="console:editAppRole" [#if role.authorities?seq_contains("console:editAppRole")] checked="checked"[/#if]/>
														    <label for="editAppRole">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppRole" value="console:deleteAppRole" [#if role.authorities?seq_contains("console:deleteAppRole")] checked="checked"[/#if] />
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
													       <input type="checkbox" id="appCategory" name="authorities" value="console:appCategory" [#if role.authorities?seq_contains("console:appCategory")] checked="checked"[/#if]/>
													       <label for="appCategory">
													           ${message("console.main.appCategory")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppCategory"  value="console:addAppCategory" [#if role.authorities?seq_contains("console:addAppCategory")] checked="checked"[/#if]/>
															<label for="addAppCategory">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppCategory" value="console:editAppCategory" [#if role.authorities?seq_contains("console:editAppCategory")] checked="checked"[/#if]/>
														    <label for="editAppCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppCategory" value="console:deleteAppCategory" [#if role.authorities?seq_contains("console:deleteAppCategory")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="appClientVersion" name="authorities" value="console:appClientVersion" [#if role.authorities?seq_contains("console:appClientVersion")] checked="checked"[/#if]/>
													       <label for="appClientVersion">
													           ${message("console.main.appClientVersion")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppClientVersion"  value="console:addAppClientVersion" [#if role.authorities?seq_contains("console:addAppClientVersion")] checked="checked"[/#if]/>
															<label for="addAppClientVersion">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppClientVersion" value="console:editAppClientVersion" [#if role.authorities?seq_contains("console:editAppClientVersion")] checked="checked"[/#if]/>
														    <label for="editAppClientVersion">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppClientVersion" value="console:deleteAppClientVersion" [#if role.authorities?seq_contains("console:deleteAppClientVersion")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="appPoster" name="authorities" value="console:appPoster" [#if role.authorities?seq_contains("console:appPoster")] checked="checked"[/#if]/>
													       <label for="appPoster">
													           ${message("console.main.appPoster")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAppPoster"  value="console:addAppPoster" [#if role.authorities?seq_contains("console:addAppPoster")] checked="checked"[/#if]/>
															<label for="addAppPoster">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAppPoster" value="console:editAppPoster" [#if role.authorities?seq_contains("console:editAppPoster")] checked="checked"[/#if]/>
														    <label for="editAppPoster">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAppPoster" value="console:deleteAppPoster" [#if role.authorities?seq_contains("console:deleteAppPoster")] checked="checked"[/#if]/>
															<label for="deleteAppPoster">
													           ${message("console.common.delete")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
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
													       <input type="checkbox" id="ad" name="authorities" value="console:ad"  [#if role.authorities?seq_contains("console:ad")] checked="checked"[/#if]/>
													       <label for="ad">
													           ${message("广告管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												    	<li>
															<input type="checkbox" name="authorities" id="addAd"  value="console:addAd" [#if role.authorities?seq_contains("console:addAd")] checked="checked"[/#if]/>
															<label for="addAd">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAd" value="console:editAd" [#if role.authorities?seq_contains("console:editAd")] checked="checked"[/#if]/>
														    <label for="editAd">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAd" value="console:deleteAd" [#if role.authorities?seq_contains("console:deleteAd")] checked="checked"[/#if]/>
															<label for="deleteAd">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="adShelve" value="console:onLineChange" [#if role.authorities?seq_contains("console:onLineChange")] checked="checked"[/#if]/>
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
													       <input type="checkbox" id="adCategory" name="authorities" value="console:adCategory" [#if role.authorities?seq_contains("console:adCategory")] checked="checked"[/#if]/>
													       <label for="adCategory">
													           ${message("广告分类管理")}
													       </label>
													    </li>
												    </ul>
												</td>
												<td>
												    <ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addAdCategory"  value="console:addAdCategory" [#if role.authorities?seq_contains("console:addAdCategory")] checked="checked"[/#if]/>
															<label for="addAdCategory">
													           ${message("console.common.add")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editAdCategory" value="console:editAdCategory" [#if role.authorities?seq_contains("console:editAdCategory")] checked="checked"[/#if]/>
														    <label for="editAdCategory">
															    ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteAdCategory" value="console:deleteAdCategory" [#if role.authorities?seq_contains("console:deleteAdCategory")] checked="checked"[/#if] />
															<label for="deleteAdCategory">
													           ${message("console.common.delete")}
													       </label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="unableAdCategory" value="console:unableAdCategory" [#if role.authorities?seq_contains("console:unableAdCategory")] checked="checked"[/#if] />
															<label for="unableAdCategory">
													           ${message("禁用")}
													       </label>
														</li>
												    </ul>
												</td>
											</tr>
											<!-- end  广告管理 -->
											<!-- 系统设置 -->
											<tr class="authorities">
												<th>
													${message("系统设置")}
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
													        <input type="checkbox" name="authorities" id="setting" value="console:setting"[#if role.authorities?seq_contains("console:setting")] checked="checked"[/#if] />
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
													        <input type="checkbox" name="authorities" id="systemDict" value="console:systemDict" [#if role.authorities?seq_contains("console:systemDict")] checked="checked"[/#if]/>
													        <label for="systemDict">
													           ${message("数据字典")}
													        </label>
													    </li>
												    </ul>
												</td>
												<td>
											   		<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" id="addSystemDict" value="console:addSystemDict" [#if role.authorities?seq_contains("console:addSystemDict")] checked="checked"[/#if]/>
															<label for="addSystemDict">
															   ${message("console.common.add")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="editSystemDict" value="console:editSystemDict" [#if role.authorities?seq_contains("console:editSystemDict")] checked="checked"[/#if]/>
															<label for="editSystemDict">
															   ${message("console.common.edit")}
															</label>
														</li>
														<li>
															<input type="checkbox" name="authorities" id="deleteSystemDict" value="console:deleteSystemDict" [#if role.authorities?seq_contains("console:deleteSystemDict")] checked="checked"[/#if]/>
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
													        <input type="checkbox" name="authorities" id="area" value="console:area"[#if role.authorities?seq_contains("console:area")] checked="checked"[/#if] />
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
													        <input type="checkbox" name="authorities" id="paymentPlugin" value="console:paymentPlugin"[#if role.authorities?seq_contains("console:paymentPlugin")] checked="checked"[/#if] />
													        <label for="paymentPlugin">
													            ${message("console.role.paymentPlugin")}
													        </label>
													    </li>
													</ul>
											 	</td>
											 	<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_install"[#if role.authorities?seq_contains("console:paymentPlugin_a_install")] checked="checked"[/#if] />${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_setting"[#if role.authorities?seq_contains("console:paymentPlugin_a_setting")] checked="checked"[/#if] />${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:paymentPlugin_a_uninstall"[#if role.authorities?seq_contains("console:paymentPlugin_a_uninstall")] checked="checked"[/#if] />${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 存储插件 -->
											<tr class="systemGroup">
											    <td>
												    <ul>
														<li>
													         <input type="checkbox" name="authorities" id="storagePlugin" value="console:storagePlugin"[#if role.authorities?seq_contains("console:storagePlugin")] checked="checked"[/#if] />
														     <label for="storagePlugin">
														        ${message("console.role.storagePlugin")}
														    </label>
												     </li>
													</ul>
											    </td>
											    <td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_install"[#if role.authorities?seq_contains("console:storagePlugin_a_install")] checked="checked"[/#if] />${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_setting"[#if role.authorities?seq_contains("console:storagePlugin_a_setting")] checked="checked"[/#if] />${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:storagePlugin_a_uninstall"[#if role.authorities?seq_contains("console:storagePlugin_a_uninstall")] checked="checked"[/#if] />${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 消息插件 -->
											<tr class="systemGroup">
											    <td>
												    <ul>
														<li>
													         <input type="checkbox" name="authorities" id="messagePushPlugin" value="console:messagePushPlugin" [#if role.authorities?seq_contains("console:messagePushPlugin")] checked="checked"[/#if] />
														     <label for="messagePushPlugin">
														        ${message("console.role.messagePushPlugin")}
														    </label>
												     </li>
													</ul>
											    </td>
											    <td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:installMessagePushPlugin" [#if role.authorities?seq_contains("console:installMessagePushPlugin")] checked="checked"[/#if]/>${message("console.common.install")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:settingMessagePushPlugin" [#if role.authorities?seq_contains("console:settingMessagePushPlugin")] checked="checked"[/#if]/>${message("console.common.setting")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:uninstallMessagePushPlugin" [#if role.authorities?seq_contains("console:uninstallMessagePushPlugin")] checked="checked"[/#if]/>${message("console.common.uninstall")}
														</li>
												    </ul>
												</td>
											</tr>
											<!-- 管理员管理 -->
											<tr class="systemGroup">
											    <td>
											    	<ul>
													  <li>
													    <input type="checkbox" name="authorities" id="admin" value="console:admin"[#if role.authorities?seq_contains("console:admin")] checked="checked"[/#if] />
													    <label for="admin">
													        ${message("console.role.admin")}
													    </label>
												    </li>
													</ul>
												</td>		
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:admin_icon_addIcon"[#if role.authorities?seq_contains("console:admin_icon_addIcon")] checked="checked"[/#if] />${message("console.common.add")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:admin_button_delteButton"[#if role.authorities?seq_contains("console:admin_button_delteButton")] checked="checked"[/#if] />${message("console.common.delete")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:admin_a_edit"[#if role.authorities?seq_contains("console:admin_a_edit")] checked="checked"[/#if] />${message("console.common.edit")}
														</li>
												    </ul>
												</td>		
											</tr>
											<!-- 角色管理 -->
											<tr class="systemGroup">
											     <td>
											     <ul>
													  <li>
												   <input type="checkbox" name="authorities" id="role" value="console:role"[#if role.authorities?seq_contains("console:role")] checked="checked"[/#if] />
												   <label for="role">
												        ${message("console.role.role")}
												   </label>
												   </li>
													</ul>
												</td>
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:role_icon_addIcon"[#if role.authorities?seq_contains("console:role_icon_addIcon")] checked="checked"[/#if] />${message("console.common.add")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:role_button_deleteButton"[#if role.authorities?seq_contains("console:role_button_deleteButton")] checked="checked"[/#if] />${message("console.common.delete")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:role_a_edit"[#if role.authorities?seq_contains("console:role_a_edit")] checked="checked"[/#if] />${message("console.common.edit")}
														</li>
												    </ul>
												</td>		
											</tr>
											<!-- 日志管理 -->
											<tr class="systemGroup">
											    <td>
											       <ul>
													<li>
													     <input type="checkbox" name="authorities" id="log" value="console:log"[#if role.authorities?seq_contains("console:log")] checked="checked"[/#if] />
														 <label for="log">
														     ${message("console.role.log")}
														 </label>
													 </li>
													</ul>
												</td>
												<td>
													<ul class="authorities">
												        <li>
															<input type="checkbox" name="authorities" value="console:log_button_clearButton"[#if role.authorities?seq_contains("console:log_button_clearButton")] checked="checked"[/#if] />${message("console.common.clear")}
														</li>
														<li>
															<input type="checkbox" name="authorities" value="console:log_button_deleteButton"[#if role.authorities?seq_contains("console:log_button_deleteButton")] checked="checked"[/#if] />${message("console.common.delete")}
														</li>
												    </ul>
												</td>			
											</tr>
											
											[#if role.isSystem]
												<tr>
													<th>
														&nbsp;
													</th>
													<td colspan="2">
														<span class="tips">${message("console.role.editSystemNotAllowed")}</span>
													</td>
												</tr>
											[/#if]
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td colspan="2">
													<input type="submit" class="btn  btn-primary" value="${message("console.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
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