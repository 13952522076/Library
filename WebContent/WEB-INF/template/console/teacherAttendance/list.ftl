<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.attendance.list")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/enumAndDateHelper.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/attendance.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<link href="${base}/resources/console/css/attendance.css" rel="stylesheet" type="text/css" />
<script>
	Date.prototype.Format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	};

	function showDetail(id){
		$("#showDetail").fadeIn();
		$.ajax({
			type: "GET",
			url: "${base}/console/teacherAttendance/getDetail.ct",
			data: {
			    id:id
			},
			dataType: "json",
			success:function(details){
				if(details==null||details.length==0){
					$("#detailBody").html("");
					$("#detailBody").append("<tr><td colspan='2'>无数据</td></tr>");
					return;
				}
				else{
					$("#detailBody").html("");
					for(num in details){
						var detail = details[num];
						var newTime = new Date(detail.clockInDate);
						var time = newTime.toLocaleDateString()+" "+newTime.toLocaleTimeString();
						$("#detailBody").append("<tr><td>"+newTime.Format('yyyy-MM-dd hh:mm:ss')+"</td><td>"+detail.cardNumber+"</td></tr>");
					}
					return;
				}
			}
		});
	}
	function closeDiv(){
		$("#showDetail").fadeOut();
	}
</script>


</head>
<body>
        <div id="wrapper">
            <!-- start 导航 -->
            [#include "/console/include/nav.ftl" /]
            <!-- end 导航 -->
            <div id="page-wrapper" class="gray-bg dashbard-1">
                <!-- start 头部 -->
                [#include "/console/include/header.ftl" /]
                <!-- end 头部-->
                <!-- start 头部面包屑区域 -->
                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>${message("教师考勤列表")}</h2>
                        <ol class="breadcrumb">
                            <li>
                                <a href="${base}/console/common/index.ct"> ${message("console.path.index")}</a>
                            </li>
                            <li>
                                <strong>
                                    ${message("教师考勤列表")}
                                    <span>(${message("console.page.total", page.total)})</span>
                                </strong>
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
                                <div class="ibox-content">
                                    <!-- start 列表管理 -->
                                    <form id="listForm" action="list.ct" method="get">
                                        <!-- start 查询条件区 -->
                                        <div class="padding">
                                            <div class="row">
                                            	${message("老师")}
                                            	 <select name = "memberId" class="btn-white dropdown-toggle" style="height: 30px;width: 70px;">
                                            	 	<option value="">${message("console.attendance.choose")}</option>
                                            	 	[#list teachers as teacher]
                                            	 	<option value="${teacher.id}" [#if memberId=="${teacher.id}" ] selected="selected"[/#if]>${teacher.realName}</option>
                                            	 	[/#list]
                                            	 </select>
                                            	${message("迟到情况")}
                                            	<select name="workStatus" class="btn-white dropdown-toggle" style="height: 30px;width: 70px;">
                                            		<option value="">${message("console.attendance.choose")}</option>
                                            		<option value="normal" [#if workStatus=="normal" ] selected="selected"[/#if]>${message("console.attendance.status.normal")}</option>
                                                    <option value="late" [#if workStatus=="late" ] selected="selected" [/#if]>${message("console.attendance.status.late")}</option>
                                                    <option value="early" [#if workStatus=="early" ] selected="selected"[/#if]>${message("console.attendance.status.early")}</option>
                                                    <option value="leave" [#if workStatus=="leave" ] selected="selected"[/#if]>${message("console.attendance.status.leave")}</option>
                                                    <option value="absenteeism" [#if workStatus=="absenteeism" ] selected="selected"[/#if]>${message("早退")}</option>
                                            	</select>
                                                ${message("早退情况")}
                                                <select name="closingStatus" class="btn-white dropdown-toggle" style="  height: 30px;width: 70px;">
                                                    <option value="">${message("console.attendance.choose")}</option>
                                                    <option value="normal" [#if closingStatus=="normal" ] selected="selected"[/#if]>${message("console.attendance.status.normal")}</option>
                                                    <option value="late" [#if closingStatus=="late" ] selected="selected" [/#if]>${message("console.attendance.status.late")}</option>
                                                    <option value="early" [#if closingStatus=="early" ] selected="selected"[/#if]>${message("console.attendance.status.early")}</option>
                                                    <option value="leave" [#if closingStatus=="leave" ] selected="selected"[/#if]>${message("console.attendance.status.leave")}</option>
                                                    <option value="absenteeism" [#if workStatus=="absenteeism" ] selected="selected"[/#if]>${message("早退")}</option>
                                                </select>
                                                ${message("console.attendance.attendanceTime")}
                                                <input type="text" value="${startDate}" name="startDate" id="startDate" class="laydate-icon form-control layer-date" placeholder="${message("console.attendance.clickforstart")}" style="width:150px;"/>
                                                ${message("console.attendance.to")}
                                                <input type="text" value="${endDate}" name="endDate" id="endDate" class="laydate-icon form-control layer-date" placeholder="${message("console.attendance.clickforend")}"  style="width:150px;"/>
                                                <input id="submitButton" onclick="checkSearchForm()" type="button" value="${message("console.attendance.status.search")}" class="btn btn-primary" />
                                                <div class="btn-group">
			                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" aria-expanded="false">${message("console.page.pageSize")} <span class="caret"></span>
			                                        </button>
			                                        <ul class="dropdown-menu" id="pageSizeOption">
														<li>
															<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
														</li>
														<li>
															<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
														</li>
														<li>
															<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
														</li>
														<li>
															<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
														</li>
													</ul>
		                                       </div>
                                            </div>
                                        </div>
                                        <!-- end 查询条件区 -->
                                        <!-- start table展示区 -->
                                        <div class="table-responsive">
                                            <table id="attendance_table" class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th><a href="javascript:;" class="sort" name="member">${message("老师")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workSettingName">${message("班次")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workSwipeTime">${message("上班打卡时间")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workStatus">${message("迟到情况")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="closingSwipeTime">${message("下班打卡时间")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="closingStatus">${message("早退情况")}</a></th>
                                                        <th>${message("console.attendance.detail")}</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    [#list page.content as teacherAttendance]
                                                    <tr>
                                                    	<td>${teacherAttendance.member.realName}</td>
                                                    	<td>${teacherAttendance.workSettingName}</td>
                                                    	<td>[#if teacherAttendance.workSwipeTime??]${teacherAttendance.workSwipeTime?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
                                                    	<td>
                                                    		[#if teacherAttendance.workStatus=="normal"]
                                                    		正常
                                                    		[#elseif teacherAttendance.workStatus=="late"]
                                                    		迟到
                                                    		[#elseif teacherAttendance.workStatus=="early"]
                                                    		早退
                                                    		[#elseif teacherAttendance.workStatus=="leave"]
                                                    		请假
                                                    		[#elseif teacherAttendance.workStatus=="absenteeism"]
                                                    		旷工
                                                    		[/#if]
                                                    	</td>
                                                    	<td>[#if teacherAttendance.closingSwipeTime??]${teacherAttendance.closingSwipeTime?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
                                                    	<td>
                                                    		[#if teacherAttendance.closingStatus=="normal"]
                                                    		正常
                                                    		[#elseif teacherAttendance.closingStatus=="late"]
                                                    		迟到
                                                    		[#elseif teacherAttendance.closingStatus=="early"]
                                                    		早退
                                                    		[#elseif teacherAttendance.closingStatus=="leave"]
                                                    		请假
                                                    		[#elseif teacherAttendance.closingStatus=="absenteeism"]
                                                    		旷工
                                                    		[/#if]
                                                		</td>
                                                    	<td><a onclick="showDetail(${teacherAttendance.id})">[详情]</a></td>
                                                    </tr>
                                                    [/#list]
                                                </tbody>
                                            </table>
                                        </div>
                                        [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
                                        	[#include "/console/include/pagination.ftl"] 
                                        [/@pagination]
                                        <!-- end table展示区 -->
                                        <!-- start 详情展示区 -->
                                        <div id="showDetail" class="modal in" style="overflow: auto; display: none;"
                                        tabindex="-1" aria-hidden="false">
                                            <div class="modal-backdrop  in" onclick="closeDiv()">
                                            </div>
                                            <div class="modal-dialog">
                                                <div class="modal-content marginTop">
                                                    <div class="modal-header">
                                                        <a onclick="closeDiv()" type="button" class="close" data-dismiss="modal"  aria-hidden="true">
                                                            ×
                                                        </a>
                                                        <h4 class="modal-title">
                                                            ${message("console.attendance.detail")}
                                                        </h4>
                                                    </div>
                                                    <div class="modal-body ui-front">
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>${message("刷卡时间")}</th>
                                                                    <th>${message("卡号")}</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="detailBody">
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end 详情展示区 -->
                                    </form>
                                    <!-- end 列表管理 -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end 中间内容部分 -->
                [#include "/console/include/footer.ftl" /]
            </div>
        </div>
        <!-- start layerDate plugin javascript -->
        <!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
        <script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js">
        </script>
        <script>
            //日期范围限制
            var start = {
                elem: '#startDate',
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
                elem: '#endDate',
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
        <!-- end layerDate plugin javascript -->
    </body>

</html>