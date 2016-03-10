<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("添加排班")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery-validation/additional-methods.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/css/plugins/iCheck/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
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
	var $areaId = $("#areaId");
	
	[@flash_message /]
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/console/common/area.ct"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			workSettingId:{
				required: true
			},
			memberIds: {
				required: true
			},
			schedulingWay:{
				required:  true
			},
			weeks:{
			},
			layerDates:{
			}
			
		},
		messages: {
		}
	});
	
	 
	
});

function showTab(id){
	$("#tab-week").hide();
	$("#tab-date").hide();
	$("#"+id).show();
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
                    <h2> ${message("添加排班")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("添加排班")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增排班-->
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
													<span class="requiredField">*</span>${message("班次")}:
												</th>
												<td>
													<div class="dropdown">
					                               		<div class="input-group" style="width:100%;">
					                                        <select data-placeholder="请选择班次" name="workSettingId" class="chosen-select" tabindex="2" style="width:250px;">
					                                            <option value="">请选择</option>
					                                            [#list workSettings as workSetting]
					                                            <option value="${workSetting.id}">${workSetting.name}</option>
					                                            [/#list]
					                                        </select>
					                                    </div>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("人员")}:
												</th>
												<td>
													<div class="dropdown">
					                               		<div class="input-group" style="width:100%;">
					                                        <select data-placeholder="请选择老师(可搜索老师)" name="memberIds" class="chosen-select" multiple tabindex="4" style="width:70%;">
					                                            <option value="">请选择</option>
					                                            [#list members as member]
					                                            <option value="${member.id}">${member.realName}</option>
					                                            [/#list]
					                                        </select>
					                                    </div>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													${message("日期")}:
												</th>
												<td>
													<div id="schedulingWay" style="padding: 20px 20px 20px 0px;">
			                                        	<label onclick="showTab('tab-week')">
			                                        		<input type="radio" value="week" name="schedulingWay" id="schedulingWayWeek" checked>
			                                        		按周重复
			                                        	</label>
			                                        	<label onclick="showTab('tab-date')">
			                                        		<input type="radio" value="date" name="schedulingWay" id="schedulingWayDate">
			                                        		指定日期
			                                        	</label>
				                                    </div>
				                                    <div id="tab-week">
				                                    	<!-- 按周重复 -->
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="monday"/>星期一
			                                    		</label>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="tuesday"/>星期二
				                                    	</label>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="wednesday"/>星期三
				                                    	</label>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="thursday"/>星期四
				                                    	</label>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="friday"/>星期五
				                                    	</label><br>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="saturday"/>星期六
				                                    	</label>
				                                    	<label>
				                                    		<input type="checkbox" name="weeks" class="i-checks" value="sunday"/>星期日
				                                    	</label>
				                                    </div>
				                                    <div id="tab-date" style="display:none;">
				                                    <!--  -->
				                                    <input id="layerDates" name="layerDates" style="width:500px;" class="laydate-icon">
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
	             <!-- end 新增排班-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  	</div>	
</div>
<!-- iCheck -->
<script type="text/javascript" src="${base}/resources/console/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.dev.js"></script>
<script>
	$(document).ready(function () {
		var config = {
            '.chosen-select': {},
            '.chosen-select-deselect': {
                allow_single_deselect: true
            },
            '.chosen-select-no-single': {
                disable_search_threshold: 10
            },
            '.chosen-select-no-results': {
                no_results_text: 'Oops, nothing found!'
            },
            '.chosen-select-width': {
                width: "95%"
            }
        };
        for (var selector in config) {
            $(selector).chosen(config[selector]);
        };
        
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
		
	})
	laydate({
	    elem: '#layerDates', 
	    event: 'focus' 
	});
</script>
</body>
</html>