<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("教师请假列表")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});

function description(msg){
	layer.open({
	    type: 1,
	    skin: 'layui-layer-demo', //样式类名
	    closeBtn: false, //不显示关闭按钮
	    shift: 2,
	    shadeClose: true, //开启遮罩关闭
	    area: ['400px', '200px'],
	    content: msg
	});
}
</script>
</head>
<body>
<body class="fixed-navigation">
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
                    <h2>${message("教师请假列表")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("教师请假列表")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start  教师请假列表 -->
	             <form id="listForm" action="list.ct" method="get">
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-12 m-b-xs">
                                    	<div class="btn-group">
											<a href="javascript:;" id="refreshButton" class="btn btn-primary">
												<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
											</a>
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
										<div class="input-group pull-right">
											<form action="list.ct" method="get">
												<input type="text" name="username" value="${username}" class="input-sm form-control" style="width:100px;margin-top: 12px;" placeholder="搜索姓名">
												<select data-placeholder="请假类型" name="askLeaveType" class="chosen-select" style="width:100px;margin-top:5px;"  tabindex="4">
		                                            <option value="" hassubinfo="true" ></option>
		                                            <option value="sick" [#if askLeaveType == "sick"]selected="true"[/#if] hassubinfo="true" >病假</option>
		                                            <option value="compassionate" [#if askLeaveType == "compassionate"]selected="true"[/#if] hassubinfo="true" >事假</option>
		                                            <option value="annual" [#if askLeaveType == "annual"]selected="true"[/#if] hassubinfo="true" >年假</option>
		                                            <option value="off" [#if askLeaveType == "off"]selected="true"[/#if] hassubinfo="true" >调休</option>
		                                            <option value="marriage" [#if askLeaveType == "marriage"]selected="true"[/#if] hassubinfo="true" >婚假</option>
		                                            <option value="maternity" [#if askLeaveType == "maternity"]selected="true"[/#if] hassubinfo="true" >产假</option>
		                                            <option value="accompany" [#if askLeaveType == "accompany"]selected="true"[/#if] hassubinfo="true" >陪产</option>
		                                            <option value="way" [#if askLeaveType == "way"]selected="true"[/#if] hassubinfo="true" >路途假</option>
		                                            <option value="funeral" [#if askLeaveType == "funeral"]selected="true"[/#if] hassubinfo="true" >丧假</option>
		                                            <option value="other" [#if askLeaveType == "other"]selected="true"[/#if] hassubinfo="true" >其他</option>
		                                        </select>
		                                        <input type="text" value="${leaveStartDate}" name="leaveStartDate" id="startDate" 
                                                	class="laydate-icon form-control layer-date" style="width:120px;margin-top:12px;height:30px;"
                                                	placeholder="开始时间" />
                                                <input type="text" value="${leaveEndDate}" name="leaveEndDate" id="endDate" 
                                                	class="laydate-icon form-control layer-date" style="width:120px;margin-top:12px;height:30px;"
                                                	placeholder="结束时间"/>
												<input type="submit" class="btn btn-primary" value="搜索"/>
											</form>
										</div>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                     <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
												<tr>
													<th class="check">
														<input type="checkbox" id="selectAll" />
													</th>
													<th>
														<a href="javascript:;" class="sort" name="leaveMember">${message("老师")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="askLeaveType">${message("请假类型")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="leaveStartDate">${message("请假时间")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="leaveDay">${message("请假天数")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="isAgree">${message("是否同意")}</a>
													</th>
													<th>
														${message("请假说明")}
													</th>
													<th>
														${message("审批老师")}
													</th>
													<th>
														${message("审批意见")}
													</th>
												</tr>
												[#list page.content as teacherAskLeave]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${teacherAskLeave.id}" />
														</td>
														<td>
															<a onclick="layer.tips('${teacherAskLeave.leaveMember.username}', this);">${teacherAskLeave.leaveMember.realName}</a>
														</td>
														<td>
															[#if teacherAskLeave.askLeaveType = "sick"]病假
															[#elseif teacherAskLeave.askLeaveType = "compassionate"]事假
															[#elseif teacherAskLeave.askLeaveType = "annual"]年假
															[#elseif teacherAskLeave.askLeaveType = "off"]调休
															[#elseif teacherAskLeave.askLeaveType = "marriage"]婚假
															[#elseif teacherAskLeave.askLeaveType = "maternity"]产假
															[#elseif teacherAskLeave.askLeaveType = "accompany"]陪产
															[#elseif teacherAskLeave.askLeaveType = "way"]路途假
															[#elseif teacherAskLeave.askLeaveType = "funeral"]丧假
															[#elseif teacherAskLeave.askLeaveType = "other"]其他
															[/#if]
														</td>
														<td>
															${teacherAskLeave.leaveStartDate} 至 ${teacherAskLeave.leaveEndDate}
														</td>
														<td>
															${teacherAskLeave.leaveDay}天
														</td>
														<td>
															[#if teacherAskLeave??]
																[#if teacherAskLeave]
																	是
																[#else]
																	否
																[/#if]
															[#else]
																未审批
															[/#if]
														</td>
														<td>
															<a onclick="description('${teacherAskLeave.description}')">查看</a>
														</td>
														<td>
															${teacherAskLeave.approvalMember.realName}
														</td>
														<td>
															<a onclick="description('${teacherAskLeave.approvalOpinion}')">查看</a>
														</td>
													</tr>
												[/#list]
											</table>
											[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
												[#include "/console/include/pagination.ftl"]
											[/@pagination]
	                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
	             <!-- end 教师请假列表 -->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  	</div>
  	 <script>
	    var config = {
	        '.chosen-select': {},
	        '.chosen-select-deselect': {
	            allow_single_deselect: true
	        },
	        '.chosen-select-no-single': {
	            disable_search_threshold: 10
	        },
	        '.chosen-select-no-results': {
	            no_results_text: '无选择项'
	        },
	        '.chosen-select-width': {
	            width: "95%"
	        }
	    }
	    for (var selector in config) {
	        $(selector).chosen(config[selector]);
	    }
	</script>
</div>
<!-- start layerDate plugin javascript -->
<!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
<script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js"></script>
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