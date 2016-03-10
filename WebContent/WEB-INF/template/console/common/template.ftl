<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("console.profile.edit")} -图书智能管理推荐系统</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        <link href="${base}/resources/console/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${base}/resources/console/css/animate.css" rel="stylesheet" type="text/css" />
        [#include "/console/include/resources.ftl" /]
        <script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
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
                            <li><a href="${base}/console/common/index.ct"> ${message("console.path.index")}</a></li>
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
                                            <!-- content-->
                                            </div>
                                        </div>
                                        <!-- end 查询条件区 -->
                                        <!-- start table展示区 -->
                                        <div class="table-responsive">
                                            <table id="attendance_table" class="table table-striped">
                                            <!-- 中间的表格 -->
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
        <!-- start layerDate plugin javascript -->
        <!-- end layerDate plugin javascript -->
    </body>

</html>