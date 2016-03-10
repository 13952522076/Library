<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.recipe.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/css/plugins/iCheck/custom.css" rel="stylesheet" type="text/css" />
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
.itemList-upload{
	left:650px;
	top:45px;
	width:111px;
	padding:8px;
	border-radius:2px;
	border:1px solid #D6D6D6;
}
.newsPreview{
	width:93px;
	height:93px;
}
.imgShowBox{
	position:relative;
}
.textAreaMain {
  width: 293px;
  height: 116px;
  padding: 20px;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    year:{
				required: true
			},
			month:{
				required: true
			},
			week:{
				required: true
			},
			weekStartDate:{
				required: true
			},
			weekEndDate:{
				required: true
			}
		},
		messages: {
		}
	});
	
	<!--start上传图片插件 -->
	<!-- 周一 -->
 	var $mondayBreakfastUpload = $("#mondayBreakfastUpload");
	var $mondayBreakfastPreview = $("#mondayBreakfastPreview");
	var $delMondayBreakfast = $("#delMondayBreakfast");
	var $mondayBreakfast = $("#mondayBreakfast");
    loadUploadify($mondayBreakfastUpload,$mondayBreakfastPreview,$mondayBreakfast);
    
    var $mondayLunchUpload = $("#mondayLunchUpload");
	var $mondayLunchPreview = $("#mondayLunchPreview");
	var $delMondayLunch = $("#delMondayLunch");
	var $mondayLunch = $("#mondayLunch");
    loadUploadify($mondayLunchUpload,$mondayLunchPreview,$mondayLunch);
    
    var $mondaySnackUpload = $("#mondaySnackUpload");
	var $mondaySnackPreview = $("#mondaySnackPreview");
	var $delMondaySnack = $("#delMondaySnack");
	var $mondaySnack = $("#mondaySnack");
    loadUploadify($mondaySnackUpload,$mondaySnackPreview,$mondaySnack);
    
    
    <!-- 周二 -->
    var $tuesdayBreakfastUpload = $("#tuesdayBreakfastUpload");
	var $tuesdayBreakfastPreview = $("#tuesdayBreakfastPreview");
	var $deltuesdayBreakfast = $("#deltuesdayBreakfast");
	var $tuesdayBreakfast = $("#tuesdayBreakfast");
    loadUploadify($tuesdayBreakfastUpload,$tuesdayBreakfastPreview,$tuesdayBreakfast);
    
    var $tuesdayLunchUpload = $("#tuesdayLunchUpload");
	var $tuesdayLunchPreview = $("#tuesdayLunchPreview");
	var $deltuesdayLunch = $("#deltuesdayLunch");
	var $tuesdayLunch = $("#tuesdayLunch");
    loadUploadify($tuesdayLunchUpload,$tuesdayLunchPreview,$tuesdayLunch);
    
    var $tuesdaySnackUpload = $("#tuesdaySnackUpload");
	var $tuesdaySnackPreview = $("#tuesdaySnackPreview");
	var $deltuesdaySnack = $("#deltuesdaySnack");
	var $tuesdaySnack = $("#tuesdaySnack");
    loadUploadify($tuesdaySnackUpload,$tuesdaySnackPreview,$tuesdaySnack);
    
     <!-- 周三 -->
     var $wednesdayBreakfastUpload = $("#wednesdayBreakfastUpload");
	var $wednesdayBreakfastPreview = $("#wednesdayBreakfastPreview");
	var $delwednesdayBreakfast = $("#delwednesdayBreakfast");
	var $wednesdayBreakfast = $("#wednesdayBreakfast");
    loadUploadify($wednesdayBreakfastUpload,$wednesdayBreakfastPreview,$wednesdayBreakfast);
    
    var $wednesdayLunchUpload = $("#wednesdayLunchUpload");
	var $wednesdayLunchPreview = $("#wednesdayLunchPreview");
	var $delwednesdayLunch = $("#delwednesdayLunch");
	var $wednesdayLunch = $("#wednesdayLunch");
    loadUploadify($wednesdayLunchUpload,$wednesdayLunchPreview,$wednesdayLunch);
    
    var $wednesdaySnackUpload = $("#wednesdaySnackUpload");
	var $wednesdaySnackPreview = $("#wednesdaySnackPreview");
	var $delwednesdaySnack = $("#delwednesdaySnack");
	var $wednesdaySnack = $("#wednesdaySnack");
    loadUploadify($wednesdaySnackUpload,$wednesdaySnackPreview,$wednesdaySnack);
    
    
     <!-- 周四 -->
    var $thursdayBreakfastUpload = $("#thursdayBreakfastUpload");
	var $thursdayBreakfastPreview = $("#thursdayBreakfastPreview");
	var $delthursdayBreakfast = $("#delthursdayBreakfast");
	var $thursdayBreakfast = $("#thursdayBreakfast");
    loadUploadify($thursdayBreakfastUpload,$thursdayBreakfastPreview,$thursdayBreakfast);
    
    var $thursdayLunchUpload = $("#thursdayLunchUpload");
	var $thursdayLunchPreview = $("#thursdayLunchPreview");
	var $delthursdayLunch = $("#delthursdayLunch");
	var $thursdayLunch = $("#thursdayLunch");
    loadUploadify($thursdayLunchUpload,$thursdayLunchPreview,$thursdayLunch);
    
    var $thursdaySnackUpload = $("#thursdaySnackUpload");
	var $thursdaySnackPreview = $("#thursdaySnackPreview");
	var $delthursdaySnack = $("#delthursdaySnack");
	var $thursdaySnack = $("#thursdaySnack");
    loadUploadify($thursdaySnackUpload,$thursdaySnackPreview,$thursdaySnack);
    
    <!-- 周五 -->
    var $fridayBreakfastUpload = $("#fridayBreakfastUpload");
	var $fridayBreakfastPreview = $("#fridayBreakfastPreview");
	var $delfridayBreakfast = $("#delfridayBreakfast");
	var $fridayBreakfast = $("#fridayBreakfast");
    loadUploadify($fridayBreakfastUpload,$fridayBreakfastPreview,$fridayBreakfast);
    
    var $fridayLunchUpload = $("#fridayLunchUpload");
	var $fridayLunchPreview = $("#fridayLunchPreview");
	var $delfridayLunch = $("#delfridayLunch");
	var $fridayLunch = $("#fridayLunch");
    loadUploadify($fridayLunchUpload,$fridayLunchPreview,$fridayLunch);
    
    var $fridaySnackUpload = $("#fridaySnackUpload");
	var $fridaySnackPreview = $("#fridaySnackPreview");
	var $delfridaySnack = $("#delfridaySnack");
	var $fridaySnack = $("#fridaySnack");
    loadUploadify($fridaySnackUpload,$fridaySnackPreview,$fridaySnack);
    
	<!--end上传图片插件 -->
	
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
                    <h2> ${message("console.recipe.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.recipe.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 食谱更新-->
	             <form id="inputForm" action="update.ct" method="post">
	                 <input type="hidden" name="id" value="${recipe.id}"/>
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
													<span class="requiredField">*</span>${message("Recipe.timeSlot")}:
												</th>
												<td>
													<select name="year">
														<option value="">${message("console.common.select")}</option>
														[#list 2010..2016 as year]
														    <option value="${year}" [#if recipe.year == year] selected="selected" [/#if]>
														        ${year}${message("console.common.year")}
														    </option>
														[/#list]
													</select>
													-
													<select name="month">
														<option value="">${message("console.common.select")}</option>
														[#list 1..12 as month]
														    <option value="${month}" [#if recipe.month == month] selected="selected" [/#if]>
														        ${month}${message("console.common.month")}
														    </option>
														[/#list]
													</select>
													-
													<select name="week">
														<option value="">${message("console.common.select")}</option>
														[#list 1..5 as week]
															<option value="${week}" [#if recipe.week == week] selected="selected" [/#if] >
																${message("Recipe.week.${week}")}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Recipe.timeQuantum")}:
												</th>
												<td>
													<input type="text" name="weekStartDate" id="weekStartDate" class="laydate-icon form-control layer-date" value="${recipe.weekStartDate}"/>
													-<input type="text" name="weekEndDate" id="weekEndDate" class="laydate-icon form-control layer-date" value="${recipe.weekEndDate}"/>
												</td>
											</tr>
											<tr>
											   <td colspan="2">
											       <div class="panel blank-panel">
							                            <div class="panel-heading">
							                                <div class="panel-options">
							                                    <ul class="nav nav-tabs">
							                                        <li class="active">
							                                            <a data-toggle="tab"  href="tabs_panels.ct#mondayTab">
							                                                ${message("Recipe.monday")}
							                                                <input type="hidden" name="recipeWeekDays[0].id" value="${recipe.recipeWeekDays[0].id}"/>
							                                                <input type="hidden" name="recipeWeekDays[0].weekDay" value="1"/>
							                                            </a>
							                                        </li>
							                                        <li class="" >
							                                            <a data-toggle="tab" href="tabs_panels.html#tuesdayTab">
							                                                ${message("Recipe.tuesday")}
							                                                <input type="hidden" name="recipeWeekDays[1].id" value="${recipe.recipeWeekDays[1].id}"/>
							                                                <input type="hidden" name="recipeWeekDays[1].weekDay" value="2"/>
							                                            </a>
							                                        </li>
							                                        <li class="" >
							                                            <a data-toggle="tab" href="tabs_panels.html#wednesdayTab">
							                                                ${message("Recipe.wednesday")}
							                                                <input type="hidden" name="recipeWeekDays[2].id" value="${recipe.recipeWeekDays[2].id}"/>
							                                                <input type="hidden" name="recipeWeekDays[2].weekDay" value="3"/>
							                                            </a>
							                                        </li>
							                                        <li class="">
							                                            <a data-toggle="tab" href="tabs_panels.html#thursdayTab">
							                                                ${message("Recipe.thursday")}
							                                                <input type="hidden" name="recipeWeekDays[3].id" value="${recipe.recipeWeekDays[3].id}"/>
							                                                <input type="hidden" name="recipeWeekDays[3].weekDay" value="4"/>
							                                            </a>
							                                        </li>
							                                        <li class="">
							                                            <a data-toggle="tab" href="tabs_panels.html#fridayTab">
							                                                ${message("Recipe.friday")}
							                                                <input type="hidden" name="recipeWeekDays[4].id" value="${recipe.recipeWeekDays[4].id}"/>
							                                                <input type="hidden" name="recipeWeekDays[4].weekDay" value="5"/>
							                                            </a>
							                                        </li>
							                                    </ul>
							                                </div>
							                            </div>
							                            <div class="panel-body">
							                                <div class="tab-content">
							                                    <div id="mondayTab" class="tab-pane active">
							                                        <table class="table table-striped">
																		<tr>
																			<th>
																				${message("RecipeSection.breakfast")}:
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[0].id" value="${recipe.recipeWeekDays[0].recipeSections[0].id}"/>
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[0].sectionName" value="${message("RecipeSection.breakfast")}"/>
																			</th>
																			<td>
																			    <div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[0].recipeSections[0].description">${recipe.recipeWeekDays[0].recipeSections[0].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						  [#if recipe.recipeWeekDays[0].recipeSections[0].recipeImages[0].dishImage??]
																						      <img src="${recipe.recipeWeekDays[0].recipeSections[0].recipeImages[0].dishImage}" id="mondayBreakfastPreview" class="newsPreview" >
																						  [#else]
																						      <img src="${base}/resources/console/images/announcementUpload.jpg" id="mondayBreakfastPreview" class="newsPreview" >
																						  [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delMondayBreakfast">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="mondayBreakfastUpload" />
																							<input type="hidden" name="recipeWeekDays[0].recipeSections[0].recipeImages[0].id" value="${recipe.recipeWeekDays[0].recipeSections[0].recipeImages[0].id}">
																							<input type="hidden" id="mondayBreakfast" name="recipeWeekDays[0].recipeSections[0].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[0].recipeSections[0].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.lunch")}:
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[1].id" value="${recipe.recipeWeekDays[0].recipeSections[1].id}" />
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[1].sectionName" value="${message("RecipeSection.lunch")}" />
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[0].recipeSections[1].description">${recipe.recipeWeekDays[0].recipeSections[1].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						  [#if recipe.recipeWeekDays[0].recipeSections[1].recipeImages[0].dishImage??]
																						      <img src="${recipe.recipeWeekDays[0].recipeSections[1].recipeImages[0].dishImage}" id="mondayLunchPreview" class="newsPreview" >
																						  [#else]
																						      <img src="${base}/resources/console/images/announcementUpload.jpg" id="mondayLunchPreview" class="newsPreview" >
																						  [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delMondayLunch">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="mondayLunchUpload" />
																						    <input type="hidden" name="recipeWeekDays[0].recipeSections[1].recipeImages[0].id" value="${recipe.recipeWeekDays[0].recipeSections[1].recipeImages[0].id}" />
																							<input type="hidden" id="mondayLunch"  name="recipeWeekDays[0].recipeSections[1].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[0].recipeSections[1].recipeImages[0].dishImage}" />
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.snack")}:
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[2].id" value="${recipe.recipeWeekDays[0].recipeSections[2].id}" />
																				<input type="hidden" name="recipeWeekDays[0].recipeSections[2].sectionName" value="${message("RecipeSection.snack")}" />
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[0].recipeSections[2].description">${recipe.recipeWeekDays[0].recipeSections[2].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																							  [#if recipe.recipeWeekDays[0].recipeSections[2].recipeImages[0].dishImage??]
																							      <img src="${recipe.recipeWeekDays[0].recipeSections[2].recipeImages[0].dishImage}" id="mondaySnackPreview" class="newsPreview" >
																							  [#else]
																							      <img src="${base}/resources/console/images/announcementUpload.jpg" id="mondaySnackPreview" class="newsPreview" >
																							  [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delMondaySnack">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="mondaySnackUpload" />
																							<input type="hidden"  name="recipeWeekDays[0].recipeSections[2].recipeImages[0].id" value="${recipe.recipeWeekDays[0].recipeSections[2].recipeImages[0].id}" />
																							<input type="hidden" id="mondaySnack" name="recipeWeekDays[0].recipeSections[2].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[0].recipeSections[2].recipeImages[0].dishImage}" />
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</table>
							                                    </div>
							                                    <div id="tuesdayTab" class="tab-pane">
							                                        <table class="table table-striped">
																		<tr>
																			<th>
																				${message("RecipeSection.breakfast")}:
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[0].id" value="${recipe.recipeWeekDays[1].recipeSections[0].id}"/>
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[0].sectionName" value="${message("RecipeSection.breakfast")}"/>
																			</th>
																			<td>
																			    <div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[1].recipeSections[0].description" >${recipe.recipeWeekDays[1].recipeSections[0].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						      [#if recipe.recipeWeekDays[1].recipeSections[0].recipeImages[0].dishImage?? ]
																							      <img src="${recipe.recipeWeekDays[1].recipeSections[0].recipeImages[0].dishImage}" id="tuesdayBreakfastPreview" class="newsPreview" >
																							  [#else]
																							      <img src="${base}/resources/console/images/announcementUpload.jpg" id="tuesdayBreakfastPreview" class="newsPreview" >
																							  [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delTuesdayBreakfast">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="tuesdayBreakfastUpload" />
																							<input type="hidden"  name="recipeWeekDays[1].recipeSections[0].recipeImages[0].id" value="${recipe.recipeWeekDays[1].recipeSections[0].recipeImages[0].id}">
																							<input type="hidden" id="tuesdayBreakfast" name="recipeWeekDays[1].recipeSections[0].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[1].recipeSections[0].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.lunch")}:
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[1].id" value="${recipe.recipeWeekDays[1].recipeSections[1].id}"/>
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[1].sectionName" value="${message("RecipeSection.lunch")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[1].recipeSections[1].description" >${recipe.recipeWeekDays[1].recipeSections[1].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																							[#if recipe.recipeWeekDays[1].recipeSections[1].recipeImages[0].dishImage?? ]
																							    <img src="${recipe.recipeWeekDays[1].recipeSections[1].recipeImages[0].dishImage}" id="tuesdayLunchPreview" class="newsPreview" >
																							[#else]
																							    <img src="${base}/resources/console/images/announcementUpload.jpg" id="tuesdayLunchPreview" class="newsPreview" >
																							[/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delTuesdayLunch">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="tuesdayLunchUpload" />
																							<input type="hidden"  name="recipeWeekDays[1].recipeSections[1].recipeImages[0].id" value="${recipe.recipeWeekDays[1].recipeSections[1].recipeImages[0].id}">
																							<input type="hidden" id="tuesdayLunch"  name="recipeWeekDays[1].recipeSections[1].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[1].recipeSections[1].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.snack")}:
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[2].id" value="${recipe.recipeWeekDays[1].recipeSections[2].id}"/>
																				<input type="hidden" name="recipeWeekDays[1].recipeSections[2].sectionName" value="${message("RecipeSection.snack")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[1].recipeSections[2].description">${recipe.recipeWeekDays[1].recipeSections[2].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																							[#if recipe.recipeWeekDays[1].recipeSections[2].recipeImages[0].dishImage??]
																							    <img src="${recipe.recipeWeekDays[1].recipeSections[2].recipeImages[0].dishImage}" id="tuesdaySnackPreview" class="newsPreview" >
																							[#else]
																							    <img src="${base}/resources/console/images/announcementUpload.jpg" id="tuesdaySnackPreview" class="newsPreview" >
																							[/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delTuesdaySnack">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="tuesdaySnackUpload" />
																							<input type="hidden"   name="recipeWeekDays[1].recipeSections[2].recipeImages[0].id" value="${recipe.recipeWeekDays[1].recipeSections[2].recipeImages[0].id}">
																							<input type="hidden" id="tuesdaySnack"  name="recipeWeekDays[1].recipeSections[2].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[1].recipeSections[2].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</table>
							                                    </div>
							                                    <div id="wednesdayTab" class="tab-pane">
							                                         <table class="table table-striped">
																		<tr>
																			<th>
																				${message("RecipeSection.breakfast")}:
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[0].id" value="${recipe.recipeWeekDays[2].recipeSections[0].id}"/>
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[0].sectionName" value="${message("RecipeSection.breakfast")}"/>
																			</th>
																			<td>
																			    <div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[2].recipeSections[0].description">${recipe.recipeWeekDays[2].recipeSections[0].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						[#if recipe.recipeWeekDays[2].recipeSections[0].recipeImages[0].dishImage??]
																							 <img src="${recipe.recipeWeekDays[2].recipeSections[0].recipeImages[0].dishImage}" id="wednesdayBreakfastPreview" class="newsPreview" >
																					    [#else]
																						    <img src="${base}/resources/console/images/announcementUpload.jpg" id="wednesdayBreakfastPreview" class="newsPreview" >
																						[/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delWednesdayBreakfast">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="wednesdayBreakfastUpload" />
																							<input type="hidden" name="recipeWeekDays[2].recipeSections[0].recipeImages[0].id" value="${recipe.recipeWeekDays[2].recipeSections[0].recipeImages[0].id}">
																							<input type="hidden" id="wednesdayBreakfast" name="recipeWeekDays[2].recipeSections[0].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[2].recipeSections[0].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.lunch")}:
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[1].id" value="${recipe.recipeWeekDays[2].recipeSections[1].id}"/>
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[1].sectionName" value="${message("RecipeSection.lunch")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[2].recipeSections[1].description">${recipe.recipeWeekDays[2].recipeSections[1].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						    [#if recipe.recipeWeekDays[2].recipeSections[1].recipeImages[0].dishImage??]
																						        <img src="${recipe.recipeWeekDays[2].recipeSections[1].recipeImages[0].dishImage}" id="wednesdayLunchPreview" class="newsPreview" >
																						    [#else]
																						        <img src="${base}/resources/console/images/announcementUpload.jpg" id="wednesdayLunchPreview" class="newsPreview" >
																						    [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delWednesdayLunch">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="wednesdayLunchUpload" />
																							<input type="hidden" name="recipeWeekDays[2].recipeSections[1].recipeImages[0].id" value="${recipe.recipeWeekDays[2].recipeSections[1].recipeImages[0].id}">
																							<input type="hidden" id="wednesdayLunch" name="recipeWeekDays[2].recipeSections[1].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[2].recipeSections[1].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.snack")}:
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[2].id" value="${recipe.recipeWeekDays[2].recipeSections[2].id}"/>
																				<input type="hidden" name="recipeWeekDays[2].recipeSections[2].sectionName" value="${message("RecipeSection.snack")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[2].recipeSections[2].description">${recipe.recipeWeekDays[2].recipeSections[2].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						   [#if recipe.recipeWeekDays[2].recipeSections[2].recipeImages[0].dishImage??]
																						      <img src="${recipe.recipeWeekDays[2].recipeSections[2].recipeImages[0].dishImage}" id="wednesdaySnackPreview" class="newsPreview" >
																						   [#else]
																						      <img src="${base}/resources/console/images/announcementUpload.jpg" id="wednesdaySnackPreview" class="newsPreview" >
																						   [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delWednesdaySnack">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="wednesdaySnackUpload" />
																							<input type="hidden"  name="recipeWeekDays[2].recipeSections[2].recipeImages[0].id" value="${recipe.recipeWeekDays[2].recipeSections[2].recipeImages[0].id}">
																							<input type="hidden" id="wednesdaySnack" name="recipeWeekDays[2].recipeSections[2].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[2].recipeSections[2].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</table>
							                                    </div>
							                                     <div id="thursdayTab" class="tab-pane">
							                                         <table class="table table-striped">
																		<tr>
																			<th>
																				${message("RecipeSection.breakfast")}:
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[0].id" value="${recipe.recipeWeekDays[3].recipeSections[0].id}"/>
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[0].sectionName" value="${message("RecipeSection.breakfast")}"/>
																			</th>
																			<td>
																			    <div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[3].recipeSections[0].description" >${recipe.recipeWeekDays[3].recipeSections[0].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						    [#if recipe.recipeWeekDays[3].recipeSections[0].recipeImages[0].dishImage??]
																							    <img src="${recipe.recipeWeekDays[3].recipeSections[0].recipeImages[0].dishImage}" id="thursdayBreakfastPreview" class="newsPreview" >
																							 [#else]
																							    <img src="${base}/resources/console/images/announcementUpload.jpg" id="thursdayBreakfastPreview" class="newsPreview" >
																							 [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delThursdayBreakfast">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="thursdayBreakfastUpload" />
																							
																							<input type="hidden"  name="recipeWeekDays[3].recipeSections[0].recipeImages[0].id" value="${recipe.recipeWeekDays[3].recipeSections[0].recipeImages[0].id}">
																							<input type="hidden" id="thursdayBreakfast" name="recipeWeekDays[3].recipeSections[0].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[3].recipeSections[0].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.lunch")}:
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[1].id" value="${recipe.recipeWeekDays[3].recipeSections[1].id}"/>
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[1].sectionName" value="${message("RecipeSection.lunch")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[3].recipeSections[1].description">${recipe.recipeWeekDays[3].recipeSections[1].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						    [#if recipe.recipeWeekDays[3].recipeSections[1].recipeImages[0].dishImage??]
																							    <img src="${recipe.recipeWeekDays[3].recipeSections[1].recipeImages[0].dishImage}" id="thursdayLunchPreview" class="newsPreview" >
																							[#else]
																							    <img src="${base}/resources/console/images/announcementUpload.jpg" id="thursdayLunchPreview" class="newsPreview" >
																							[/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delThursdayLunch">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="thursdayLunchUpload" />
																							<input type="hidden"  name="recipeWeekDays[3].recipeSections[1].recipeImages[0].id" value="${recipe.recipeWeekDays[3].recipeSections[1].recipeImages[0].id}">
																							<input type="hidden" id="thursdayLunch" name="recipeWeekDays[3].recipeSections[1].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[3].recipeSections[1].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.snack")}:
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[2].id" value="${recipe.recipeWeekDays[3].recipeSections[2].id}"/>
																				<input type="hidden" name="recipeWeekDays[3].recipeSections[2].sectionName"  value="${message("RecipeSection.snack")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[3].recipeSections[2].description">${recipe.recipeWeekDays[3].recipeSections[2].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						    [#if recipe.recipeWeekDays[3].recipeSections[2].recipeImages[0].dishImage??]
																							    <img src="${recipe.recipeWeekDays[3].recipeSections[2].recipeImages[0].dishImage}" id="thursdaySnackPreview" class="newsPreview" >
																							[#else]
																							    <img src="${base}/resources/console/images/announcementUpload.jpg" id="thursdaySnackPreview" class="newsPreview" >
																							[/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delThursdaySnack">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="thursdaySnackUpload" />
																							<input type="hidden" name="recipeWeekDays[3].recipeSections[2].recipeImages[0].id" value="${recipe.recipeWeekDays[3].recipeSections[2].recipeImages[0].id}">
																							<input type="hidden" id="thursdaySnack" name="recipeWeekDays[3].recipeSections[2].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[3].recipeSections[2].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</table>
							                                    </div>
							                                     <div id="fridayTab" class="tab-pane">
							                                         <table class="table table-striped">
																		<tr>
																			<th>
																				${message("RecipeSection.breakfast")}:
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[0].id" value="${recipe.recipeWeekDays[4].recipeSections[0].id}"/>
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[0].sectionName" value="${message("RecipeSection.breakfast")}"/>
																			</th>
																			<td>
																			    <div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[4].recipeSections[0].description">${recipe.recipeWeekDays[4].recipeSections[0].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						     [#if recipe.recipeWeekDays[4].recipeSections[0].recipeImages[0].dishImage??]
																						     <img src="${recipe.recipeWeekDays[4].recipeSections[0].recipeImages[0].dishImage}" id="fridayBreakfastPreview" class="newsPreview" >
																						     [#else]
																						     <img src="${base}/resources/console/images/announcementUpload.jpg" id="fridayBreakfastPreview" class="newsPreview" >
																						     [/#if]
																							
																							<a href="javascript:;" title="删除图片" class="delImg" id="delFridayBreakfast">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="fridayBreakfastUpload" />
																							<input type="hidden"   name="recipeWeekDays[4].recipeSections[0].recipeImages[0].id" value="${recipe.recipeWeekDays[4].recipeSections[0].recipeImages[0].id}">
																							<input type="hidden" id="fridayBreakfast"  name="recipeWeekDays[4].recipeSections[0].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[4].recipeSections[0].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.lunch")}:
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[1].id" value="${recipe.recipeWeekDays[4].recipeSections[1].id}"/>
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[1].sectionName" value="${message("RecipeSection.lunch")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[4].recipeSections[1].description">${recipe.recipeWeekDays[4].recipeSections[1].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						     [#if recipe.recipeWeekDays[4].recipeSections[1].recipeImages[0].dishImage??]
																						     <img src="${recipe.recipeWeekDays[4].recipeSections[1].recipeImages[0].dishImage}" id="fridayLunchPreview" class="newsPreview" >
																						     [#else]
																						     <img src="${base}/resources/console/images/announcementUpload.jpg" id="fridayLunchPreview" class="newsPreview" >
																						     [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delFridayLunch">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="fridayLunchUpload" />
																							
																							<input type="hidden"  name="recipeWeekDays[4].recipeSections[1].recipeImages[0].id" value="${recipe.recipeWeekDays[4].recipeSections[1].recipeImages[0].id}">
																							<input type="hidden" id="fridayLunch"  name="recipeWeekDays[4].recipeSections[1].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[4].recipeSections[1].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<th>
																				${message("RecipeSection.snack")}:
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[2].id" value="${recipe.recipeWeekDays[4].recipeSections[2].id}"/>
																				<input type="hidden" name="recipeWeekDays[4].recipeSections[2].sectionName" value="${message("RecipeSection.snack")}"/>
																			</th>
																			<td>
																				<div class="left">
																					<textarea class="textAreaMain J_input J_swfDisc" name="recipeWeekDays[4].recipeSections[2].description">${recipe.recipeWeekDays[4].recipeSections[2].description}</textarea>
																				</div>
																			</td>
																			<td>
																			    <div class="left addicon J_swf J_uploadflash" style="position: relative; left: 0px;">
																					<div class="itemList-upload">
																						<div class="imgShowBox">
																						     [#if recipe.recipeWeekDays[4].recipeSections[2].recipeImages[0].dishImage??]
																						        <img src="${recipe.recipeWeekDays[4].recipeSections[2].recipeImages[0].dishImage}" id="fridaySnackPreview" class="newsPreview" >
																						     [#else]
																						        <img src="${base}/resources/console/images/announcementUpload.jpg" id="fridaySnackPreview" class="newsPreview" >
																						     [/#if]
																							<a href="javascript:;" title="删除图片" class="delImg" id="delFridaySnack">×</a>
																						</div>
																						<div class="uploadContainer" style="margin-left: 7px;">
																							<input type="file" id="fridaySnackUpload" />
																							
																							<input type="hidden" name="recipeWeekDays[4].recipeSections[2].recipeImages[0].id" value="${recipe.recipeWeekDays[4].recipeSections[2].recipeImages[0].id}">
																							<input type="hidden" id="fridaySnack" name="recipeWeekDays[4].recipeSections[2].recipeImages[0].dishImage" value="${recipe.recipeWeekDays[4].recipeSections[2].recipeImages[0].dishImage}">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</table>
							                                    </div>
							                                </div>
							                            </div>
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
	             <!-- end 食谱更新-->
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
    elem: '#weekStartDate',
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
    elem: '#weekEndDate',
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