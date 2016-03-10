<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.attendance.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/enumAndDateHelper.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/attendance.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
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
                        <h2>${message("console.attendance.list")}</h2>
                        <ol class="breadcrumb">
                            <li>
                                <a href="${base}/console/common/index.ct"> ${message("console.path.index")}</a>
                            </li>
                            <li>
                                <strong>
                                    ${message("console.attendance.list")}
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
                                                <input type="hidden" name="filters[0].property" value="dictStudent" />
                                                <input type="hidden" name="filters[0].operator" value="in" />
                                                ${message("console.attendance.class")}
                                                <select id="search_class" name="dictClass" class="btn-white dropdown-toggle" style="  height: 30px;width: 100px;">
                                                    <option value="">
                                                        ${message("console.attendance.choose")}
                                                    </option>
                                                    [#list dictClasses as dictClass]
                                                    <option value="${dictClass.id}" [#if returnDictClass=="${dictClass.id}"] selected="selected" [/#if]>${dictClass.name}</option>
                                                    [/#list]
                                                </select>
                                                ${message("console.attendance.attendancestatus")}
                                                <input type="hidden" name="filters[1].property" value="status" />
                                                <input type="hidden" name="filters[1].operator" value="eq" />
                                                <select name="status" class="btn-white dropdown-toggle" style="  height: 30px;width: 100px;">
                                                    <option value="">${message("console.attendance.choose")}</option>
                                                    <option value="normal" [#if returnStatus=="normal" ] selected="selected"[/#if]>${message("console.attendance.status.normal")}</option>
                                                    <option value="late" [#if returnStatus=="late" ] selected="selected" [/#if]>${message("console.attendance.status.late")}</option>
                                                    <option value="early" [#if returnStatus=="early" ] selected="selected"[/#if]>${message("console.attendance.status.early")}</option>
                                                    <option value="sick" [#if returnStatus=="sick" ] selected="selected"[/#if]>${message("病假")}</option>
                                                    <option value="compassionate" [#if returnStatus=="compassionate" ] selected="selected"[/#if]>${message("事假")}</option>
                                                </select>
                                                ${message("console.attendance.attendanceTime")}
                                                <input type="text" value="${returnStartDate}" name="startDate" id="startDate" class="laydate-icon form-control layer-date" placeholder="${message("console.attendance.clickforstart")}" style="width:160px;"/>
                                                ${message("console.attendance.to")}
                                                <input type="text" value="${returnEndDate}" name="endDate" id="endDate" class="laydate-icon form-control layer-date" placeholder="${message("console.attendance.clickforend")}"  style="width:160px;"/>
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
                                                        <th><a href="javascript:;" class="sort" name="class">${message("console.attendance.class")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="studentName">${message("console.attendance.studentName")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="attendanceTime">${message("console.attendance.attendanceTime")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="enterTime">${message("console.attendance.enterTime")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="outTime">${message("console.attendance.outTime")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="attendancestatus">${message("console.attendance.attendancestatus")}</a></th>
                                                        <th><a href="javascript:;" class="sort" name="detail">${message("console.attendance.detail")}</a></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    [#assign hasClass="false"/] [#list page.content as attendance]
                                                    <tr>
                                                        [#if hasClass="false"]
                                                        <td class="attendance_table_class" rowspan="${page.total}">${attendance.dictStudent.dictClass.name}</td>
                                                        [#assign hasClass="true"/] 
                                                        [/#if]
                                                        <td>${attendance.dictStudent.studentName}</td>
                                                        <!-- (<a onclick="getweek('${attendance.attendanceDate}')">周五</a>) -->
                                                        <td title="dateTurn2Week">${attendance.attendanceDate}</td>
                                                        <td>[#if attendance.enterDate??]${attendance.enterDate?time}[/#if]</td>
                                                        <td>[#if attendance.leaveDate??]${attendance.leaveDate?time}[/#if]</td>
                                                        <td title="statusTurn">${attendance.status}</td>
                                                        <td>
                                                            <a data-toggle="modal" class="btn btn-primary" onclick="getDetail(${attendance.id})">
                                                                ${message("console.attendance.detail")}
                                                            </a>
                                                        </td>
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
                                            <div class="modal-backdrop  in">
                                            </div>
                                            <div class="modal-dialog">
                                                <div class="modal-content marginTop">
                                                    <div class="modal-header">
                                                        <a onclick="closeDetail()" type="button" class="close" data-dismiss="modal"  aria-hidden="true">
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
                                                                    <th>${message("console.attendance.timeType")}</th>
                                                                    <th>${message("console.attendance.lockTime")}</th>
                                                                    <th>${message("console.attendance.device")}</th>
                                                                    <th>${message("console.attendance.systemInfo")}</th>
                                                                    <th>${message("console.attendance.take")}</th>
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