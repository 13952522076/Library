<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.weeklyPlanSection.add")} -图书智能管理推荐系统</title>
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
 	var $coverImageUpload = $("#coverImageUpload");
	var $coverImagePreview = $("#coverImagePreview");
	var $delCoverImage = $("#delCoverImage");
	var $coverImage = $("#coverImage");
    $coverImageUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '浏览',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//图片选择描述  
        'fileTypeExts'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式      
        'formData'      : {'token' : getCookie("token") },
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
            var dataJson = JSON.parse(data);
            var contentImgFileUrl = dataJson['url'];
            var messageContent = dataJson['message']['content'];
            var messageType = dataJson['message']['type'];
            if(messageType == 'success'){
	            $coverImagePreview.attr("src",contentImgFileUrl);
	            $coverImage.val(contentImgFileUrl);
	            $delCoverImage.css('display','inline'); 
            }else{
                $.message("warn", messageContent);
            }
        },
	    onComplete: function (event, queueID, fileObj, response, data) {
	           
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    }
    });
	<!--end上传预览图 -->
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$delCoverImage.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
			    $coverImagePreview.attr("src","${base}/resources/console/images/newsUpload.png");
	            $coverImage.val("");
	            $delCoverImage.css('display','none'); 
			}
		});
	});
	<!-- end  删除图片插件-->
	
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
                    <h2> ${message("console.weeklyPlanSection.add")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.weeklyPlanSection.add")}</strong>
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
													<span class="requiredField">*</span>${message("WeeklyPlanSection.schoolYearMng")}:
												</th>
												<td>
													<select name="schoolYearMngId" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list schoolYearMngs as schoolYearMng]
															<option value="${schoolYearMng.id}" [#if schoolYearMng.isCurrent]selected="selected"[/#if]>
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
															<option value="${dictClass.id}" >
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
															<option value="${i}" >
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
													<input type="text" name="weekStartDate" id="weekStartDate" class="laydate-icon form-control layer-date"/>-<input type="text" name="weekEndDate" id="weekEndDate" class="laydate-icon form-control layer-date"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanSection.weekSubject")}:
												</th>
												<td>
												    <textarea name="weekSubject" rows="5" cols="80" id="weekSubject" class="form-control"></textarea>
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
													<input type="text" name="weeklyPlanDetails[0].monday" id="weeklyPlanDetails[0].monday" class="form-control"/>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.tuesday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[0].tuesday" id="weeklyPlanDetails[0].tuesday" class="form-control"/>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.wednesday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[0].wednesday" id="weeklyPlanDetails[0].wednesday" class="form-control"/>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.thursday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[0].thursday" id="weeklyPlanDetails[0].thursday" class="form-control"/>
												</td>
											</tr>
											<tr class="morning">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.friday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[0].friday" id="weeklyPlanDetails[0].friday" class="form-control"/>
												</td>
											</tr>
											<!-- end 上午 -->
											
											<!-- start 下午 -->
											<tr class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.monday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[1].monday" id="weeklyPlanDetails[1].monday" class="form-control"/>
												</td>
											</tr>
											<tr  class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.tuesday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[1].tuesday" id="weeklyPlanDetails[1].tuesday" class="form-control"/>
												</td>
											</tr>
											<tr  class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.wednesday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[1].wednesday" id="weeklyPlanDetails[1].wednesday" class="form-control"/>
												</td>
											</tr>
											<tr  class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.thursday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[1].thursday" id="weeklyPlanDetails[1].thursday" class="form-control"/>
												</td>
											</tr>
											<tr  class="afternoon">
												<th>
													<span class="requiredField">*</span>${message("WeeklyPlanDetail.friday")}:
												</th>
												<td>
													<input type="text" name="weeklyPlanDetails[1].friday" id="weeklyPlanDetails[1].friday" class="form-control"/>
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