<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.attendanceFigures.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/enumAndDateHelper.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/attendance.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/lang/zh_CN.js"></script>
<link href="${base}/resources/console/css/attendance.css" rel="stylesheet" type="text/css" />
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
                        <h2>${message("console.attendance.statistics")}</h2>
                        <ol class="breadcrumb">
                            <li>
                                <a href="${base}/console/common/index.ct"> ${message("console.path.index")}</a>
                            </li>
                            <li>
                                <strong>
                                    ${message("console.attendance.statistics")}
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
                                                ${message("console.attendance.attendanceTime")}
                                                <input type="text" id="date" name="date" class="text Wdate" value="${date}" onfocus="WdatePicker({dateFmt:  'yyyy-MM',maxDate:'%y-%M'});" style="height:29px"/>
                                                <input type="text" name="searchName" value="${searchName}" class="input-sm form-control" style="width:120px;margin:-29px 217px;position:absolute;height:29px;text-align:center;" placeholder="搜索老师姓名">
                                                <input type="button" id="submitButton" onclick="checkSearchForm()" value="${message("console.attendance.status.search")}" class="btn btn-primary" style="position:absolute;margin:-2px 129px;"/>
                                                <div class="btn-group" style="position: absolute;margin:-12px 189px;">
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
                                                        <th><a href="javascript:;" class="sort" name="member">${message("console.teacher.realname")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workSettingName">${message("console.attendance.days")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workSwipeTime">${message("console.attendance.lateCount")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workStatus">${message("console.attendance.earlyCount")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workStatus">${message("console.attendance.absenteeismCount")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="workStatus">${message("console.attendance.Leave")}</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    [#list teacherList as teacher]
                                                    <tr>
                                                    	<td>${teacher.realName}</td>
                                            			<td>${teacher.ATTENDANCE_DAYS}</td>
                                            			<td>${teacher.LATE_COUNT}</td>
                                            			<td>${teacher.EARLY_COUNT}</td>
                                            			<td>${teacher.ABSENTEEISM_COUNT}</td>
                                            			<td>${teacher.LEAVE_COUNT}</td>
                                                    </tr>
                                                    [/#list]
                                                </tbody>
                                            </table>
                                        </div>
                                        [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
                                        	[#include "/console/include/pagination.ftl"] 
                                        [/@pagination]
                                        <!-- end table展示区 -->
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
    </body>
</html>