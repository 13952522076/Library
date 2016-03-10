<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.weeklyPlanSection.edit")} -图书智能管理推荐系统</title>
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

.morning{
  display:grid;
}
.afternoon{
  display:none;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    description: {
		        required: true
		    },
			coverImage: {
				required: true,
				maxlength:255
			},
			summary: {
				required: true,
				maxlength:500
			},
			content: {
			    required: true
			},
			schoolYearMngId:{
		    	required:true,
		    },
		    dictClassId:{
		    	required:true,
		    },
		    week:{
		    	required:true,
		    },
		    weekStartDate:{
		    	required:true,
		    },
		    weekEndDate:{
		    	required:true,
		    },
		    weekSubject:{
		    	required:true,
		    	maxlength:255
		    },
		    "weeklyPlanDetails[0].monday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[1].monday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[0].tuesday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[1].tuesday":{
		    	required:true,
		    	maxlength:255
		    },
		    "weeklyPlanDetails[0].wednesday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[1].wednesday":{
		    	required:true,
		    	maxlength:255
		    },
		   	"weeklyPlanDetails[0].thursday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[1].thursday":{
		    	required:true,
		    	maxlength:255
		    },
		    "weeklyPlanDetails[0].friday":{
		    	required:true,
		    	maxlength:255
		    },
	     	"weeklyPlanDetails[1].friday":{
		    	required:true,
		    	maxlength:255
		    }
		},
		messages: {
		}
	});
	
	<!--start上传预览图 -->
 	var $mondayImageUpload0 = $("#mondayImageUpload0");
	var $mondayImagePreview0 = $("#mondayImagePreview0");
	var $mondayImage0 = $("#mondayImage0");
	
	var $tuesdayImageUpload0 = $("#tuesdayImageUpload0");
	var $tuesdayImagePreview0 = $("#tuesdayImagePreview0");
	var $tuesdayImage0 = $("#tuesdayImage0");
	
	var $wednesdayImageUpload0 = $("#wednesdayImageUpload0");
	var $wednesdayImagePreview0 = $("#wednesdayImagePreview0");
	var $wednesdayImage0 = $("#wednesdayImage0");
	
	var $thursdayImageUpload0 = $("#thursdayImageUpload0");
	var $thursdayImagePreview0 = $("#thursdayImagePreview0");
	var $thursdayImage0 = $("#thursdayImage0");
	
	var $fridayImageUpload0 = $("#fridayImageUpload0");
	var $fridayImagePreview0 = $("#fridayImagePreview0");
	var $fridayImage0 = $("#fridayImage0");
	
	var $mondayImageUpload1 = $("#mondayImageUpload1");
	var $mondayImagePreview1 = $("#mondayImagePreview1");
	var $mondayImage1 = $("#mondayImage1");
	
	var $tuesdayImageUpload1 = $("#tuesdayImageUpload1");
	var $tuesdayImagePreview1 = $("#tuesdayImagePreview1");
	var $tuesdayImage1 = $("#tuesdayImage1");
	
	var $wednesdayImageUpload1 = $("#wednesdayImageUpload1");
	var $wednesdayImagePreview1 = $("#wednesdayImagePreview1");
	var $wednesdayImage1 = $("#wednesdayImage1");
	
	var $thursdayImageUpload1 = $("#thursdayImageUpload1");
	var $thursdayImagePreview1 = $("#thursdayImagePreview1");
	var $thursdayImage1 = $("#thursdayImage1");
	
	var $fridayImageUpload1 = $("#fridayImageUpload1");
	var $fridayImagePreview1 = $("#fridayImagePreview1");
	var $fridayImage1 = $("#fridayImage1");
	
	loadUploadify($mondayImageUpload0,$mondayImagePreview0,$mondayImage0);
	loadUploadify($tuesdayImageUpload0,$tuesdayImagePreview0,$tuesdayImage0);
	loadUploadify($wednesdayImageUpload0,$wednesdayImagePreview0,$wednesdayImage0);
	loadUploadify($thursdayImageUpload0,$thursdayImagePreview0,$thursdayImage0);
	loadUploadify($fridayImageUpload0,$fridayImagePreview0,$fridayImage0);
	
	loadUploadify($mondayImageUpload1,$mondayImagePreview1,$mondayImage1);
	loadUploadify($tuesdayImageUpload1,$tuesdayImagePreview1,$tuesdayImage1);
	loadUploadify($wednesdayImageUpload1,$wednesdayImagePreview1,$wednesdayImage1);
	loadUploadify($thursdayImageUpload1,$thursdayImagePreview1,$thursdayImage1);
	loadUploadify($fridayImageUpload1,$fridayImagePreview1,$fridayImage1);
	<!--end上传预览图 -->
	
	var $planSectionMorning =  $("#planSectionMorning");
	var $planSectionAfternoon =  $("#planSectionAfternoon");
	$planSectionMorning.on("click", function() {
		$(".morning").show();
		$(".afternoon").hide()
		$planSectionMorning.attr("checked","checked"); 
		$planSectionAfternoon.removeAttr("checked"); 
	});
	
	$planSectionAfternoon.on("click", function() {
		$(".morning").hide()
		$(".afternoon").show();
		$planSectionAfternoon.attr("checked","checked"); 
		$planSectionMorning.removeAttr("checked"); 
	});

});
function deleteCampusviewImageAttach(obj){
	var $this = $(obj);
	$.dialog({
		type: "warn",
		content: "${message("admin.dialog.deleteConfirm")}",
		onOk: function() {
			$this.prev().closest("a").remove();
			$this.closest("a").remove();
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
                    <h2> ${message("console.weeklyPlanSection.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.weeklyPlanSection.edit")}</strong>
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
	             <form id="inputForm" action="update.ct" method="post">
	                 <input name="id" type="hidden" value="${weeklyPlanSection.id}"/>
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
													<span class="requiredField">*</span>${message("WeeklyPlanSection.schoolYearMng")}:
												</th>
												<td>
													<select name="schoolYearMngId" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list schoolYearMngs as schoolYearMng]
															<option value="${schoolYearMng.id}" [#if schoolYearMng == weeklyPlanSection.schoolYearMng] selected="selected"[/#if]>
																${schoolYearMng.startYear}-${schoolYearMng.endYear}${message("SchoolYearMng.academicYear")}
																(${message("SchoolYearMng.term.${schoolYearMng.term}")})
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanSection.dictClass")}:
												</th>
												<td>
													<select name="dictClassId" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list dictClasses as dictClass]
															<option value="${dictClass.id}"  [#if dictClass == weeklyPlanSection.dictClass] selected="selected"[/#if]>
																${dictClass.name}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanSection.week")}:
												</th>
												<td>
													<select name="week" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list 1..22 as i]
															<option value="${i}" [#if i == weeklyPlanSection.week] selected="selected"[/#if]>
																${message("WeeklyPlanSection.week.${i}")}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanSection.timeSlot")}:
												</th>
												<td>
													<input type="text" name="weekStartDate" id="weekStartDate" class="laydate-icon form-control layer-date" value="${weeklyPlanSection.weekStartDate}"/>-<input type="text" name="weekEndDate" id="weekEndDate" class="laydate-icon form-control layer-date" value="${weeklyPlanSection.weekEndDate}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanSection.weekSubject")}:
												</th>
												<td>
												    <textarea name="weekSubject" rows="5" cols="80" id="weekSubject" class="form-control">${weeklyPlanSection.weekSubject}</textarea>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.planSection")}:
												</th>
												<td>
													<div style="float: left;">
													     <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="planSectionMorning" checked="checked">
							                                ${message("WeeklyPlanDetail.planSection.MORNING")}
							                             </label>
							                              <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline"  id="planSectionAfternoon"  >
							                                ${message("WeeklyPlanDetail.planSection.AFTERNOON")}
							                             </label>
							                              <input type="hidden" name="weeklyPlanDetails[0].planSection" value="MORNING" >
							                              <input type="hidden" name="weeklyPlanDetails[1].planSection" value="AFTERNOON" >
							                        </div>
												</td>
											</tr>
											<!-- start 上午 -->
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.monday")}:
												</th>
												<td>
													<table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[0].monday" id="weeklyPlanDetails[0].monday" placeholder="标题" class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[0].monday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																	   [#if weeklyPlanSection.weeklyPlanDetails[0].mondayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[0].mondayImage}" alt="上传预览图" id="mondayImagePreview0" class="newsPreview" >
																	   [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="mondayImagePreview0" class="newsPreview" >
																	   [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delMondayImage">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="mondayImageUpload0" />
																		<input type="hidden" id="mondayImage0" name="weeklyPlanDetails[0].mondayImage" value="${weeklyPlanSection.weeklyPlanDetails[0].mondayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[0].mondayDesc" rows="3" id="mondayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[0].mondayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.tuesday")}:
												</th>
												<td>
												<table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[0].tuesday" id="weeklyPlanDetails[0].tuesday" placeholder="标题" class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[0].tuesday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																	   [#if weeklyPlanSection.weeklyPlanDetails[0].tuesdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[0].tuesdayImage}" alt="上传预览图" id="tuesdayImagePreview0" class="newsPreview" >
																	   [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="tuesdayImagePreview0" class="newsPreview" >
																	   [/#if]
																	   <a href="javascript:;" title="删除图片" class="delImg" id="delTuesdayImage">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="tuesdayImageUpload0" />
																		<input type="hidden" id="tuesdayImage0" name="weeklyPlanDetails[0].tuesdayImage" value="${weeklyPlanSection.weeklyPlanDetails[0].tuesdayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[0].tuesdayDesc" rows="3" id="tuesdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[0].tuesdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.wednesday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[0].wednesday" id="weeklyPlanDetails[0].wednesday"  placeholder="标题"  class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[0].wednesday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[0].wednesdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[0].wednesdayImage}" alt="上传预览图" id="wednesdayImagePreview0" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="wednesdayImagePreview0" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delWednesday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="wednesdayImageUpload0" />
																		<input type="hidden" id="wednesdayImage0" name="weeklyPlanDetails[0].wednesdayImage" value="${weeklyPlanSection.weeklyPlanDetails[0].wednesdayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[0].wednesdayDesc" rows="3" id="wednesdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[0].wednesdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.thursday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[0].thursday" id="weeklyPlanDetails[0].thursday" placeholder="标题"  class="form-control"  value="${weeklyPlanSection.weeklyPlanDetails[0].thursday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[0].thursdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[0].thursdayImage}" alt="上传预览图" id="thursdayImagePreview0" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="thursdayImagePreview0" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delThursday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="thursdayImageUpload0" />
																		<input type="hidden" id="thursdayImage0" name="weeklyPlanDetails[0].thursdayImage" value="${weeklyPlanSection.weeklyPlanDetails[0].thursdayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[0].thursdayDesc" rows="3" id="thursdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[0].thursdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.friday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[0].friday" id="weeklyPlanDetails[0].friday" placeholder="标题"  class="form-control"  value="${weeklyPlanSection.weeklyPlanDetails[0].friday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[0].fridayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[0].fridayImage}" alt="上传预览图" id="fridayImagePreview0" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="fridayImagePreview0" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delFriday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="fridayImageUpload0" />
																		<input type="hidden" id="fridayImage0" name="weeklyPlanDetails[0].fridayImage" value="${weeklyPlanSection.weeklyPlanDetails[0].fridayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[0].fridayDesc" rows="3" id="fridayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[0].fridayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												
												</td>
											</tr>
											<!-- end 上午 -->
											
											<!-- start 下午 -->
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.monday")}:
												</th>
												<td>
													<table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[1].monday" id="weeklyPlanDetails[1].monday" placeholder="标题" class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[1].monday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[1].mondayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[1].mondayImage}" alt="上传预览图" id="mondayImagePreview1" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="mondayImagePreview1" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delMondayImage">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="mondayImageUpload1" />
																		<input type="hidden" id="mondayImage1" name="weeklyPlanDetails[1].mondayImage" value="${weeklyPlanSection.weeklyPlanDetails[1].mondayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[1].mondayDesc" rows="3" id="mondayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[1].mondayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.tuesday")}:
												</th>
												<td>
												<table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[1].tuesday" id="weeklyPlanDetails[1].tuesday" placeholder="标题" class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[1].tuesday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[1].tuesdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[1].tuesdayImage}" alt="上传预览图" id="tuesdayImagePreview1" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="tuesdayImagePreview1" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delTuesdayImage">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="tuesdayImageUpload1" />
																		<input type="hidden" id="tuesdayImage1" name="weeklyPlanDetails[1].tuesdayImage" value="${weeklyPlanSection.weeklyPlanDetails[1].tuesdayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[1].tuesdayDesc" rows="3" id="tuesdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[1].tuesdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.wednesday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[1].wednesday" id="weeklyPlanDetails[1].wednesday"  placeholder="标题"  class="form-control" value="${weeklyPlanSection.weeklyPlanDetails[1].wednesday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[1].wednesdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[1].wednesdayImage}" alt="上传预览图" id="wednesdayImagePreview1" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="wednesdayImagePreview1" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delWednesday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="wednesdayImageUpload1" />
																		<input type="hidden" id="wednesdayImage1" name="weeklyPlanDetails[1].wednesdayImage" value="${weeklyPlanSection.weeklyPlanDetails[1].wednesdayImage}" />
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[1].wednesdayDesc" rows="3" id="wednesdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[1].wednesdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.thursday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[1].thursday" id="weeklyPlanDetails[1].thursday" placeholder="标题"  class="form-control"  value="${weeklyPlanSection.weeklyPlanDetails[1].thursday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[1].thursdayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[1].thursdayImage}" alt="上传预览图" id="thursdayImagePreview1" class="newsPreview" >
																	    [#else]
																	      <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="thursdayImagePreview1" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delThursday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="thursdayImageUpload1" />
																		<input type="hidden" id="thursdayImage1" name="weeklyPlanDetails[1].thursdayImage" value="${weeklyPlanSection.weeklyPlanDetails[1].thursdayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[1].thursdayDesc" rows="3" id="thursdayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[1].thursdayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												</td>
											</tr>
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.friday")}:
												</th>
												<td>
												    <table class="table table-bordered"> 
													    <tr>
													        <td>
													           <input type="text" name="weeklyPlanDetails[1].friday" id="weeklyPlanDetails[1].friday" placeholder="标题"  class="form-control"  value="${weeklyPlanSection.weeklyPlanDetails[1].friday}"/>
													        </td>
													        <td>
													            <div class="itemList-upload">
																	<div class="imgShowBox">
																		[#if weeklyPlanSection.weeklyPlanDetails[1].fridayImage??] 
																	      <img src="${weeklyPlanSection.weeklyPlanDetails[1].fridayImage}" alt="上传预览图" id="fridayImagePreview1" class="newsPreview" >
																	    [#else]
																	     <img src="${base}/resources/console/images/announcementUpload.jpg" alt="上传预览图" id="fridayImagePreview1" class="newsPreview" >
																	    [/#if]
																		<a href="javascript:;" title="删除图片" class="delImg" id="delFriday">×</a>
																	</div>
																	<div class="uploadContainer" style="margin-left: 7px;">
																		<input type="file" id="fridayImageUpload1" />
																		<input type="hidden" id="fridayImage1" name="weeklyPlanDetails[1].fridayImage" value="${weeklyPlanSection.weeklyPlanDetails[1].fridayImage}"/>
																	</div>
																</div>
													        </td>
													    </tr>
													    <tr>
													      <td colspan="2">
													           <textarea name="weeklyPlanDetails[1].fridayDesc" rows="3" id="fridayDesc" placeholder="描述" class="form-control">${weeklyPlanSection.weeklyPlanDetails[1].fridayDesc}</textarea>
													       </td>
													    </tr>
													</table>
												
												</td>
											</tr>
											<!-- end 下午 -->
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