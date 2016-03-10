<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${message("添加成长记标签")} -图书智能管理推荐系统</title>
    <meta name="author" content="福州盛云软件技术有限公司 Team" />
    <meta name="copyright" content="福州盛云软件技术有限公司" />
   	[#include "/console/include/resources.ftl" /]
    <script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
    <script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
    <script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
    <style type="text/css">
        table th { width: 150px; line-height: 25px; padding: 5px 10px 5px 0px;
        font-weight: normal; white-space: nowrap; }
    </style>
    <script type="text/javascript">
        $().ready(function() {
            var $inputForm = $("#inputForm");
            [@flash_message /]

            // 表单验证
            $inputForm.validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 15,
                        remote: {
							url: "checkNameExsit.ct",
							cache: false
						}
                    }
                },
                messages: {
                	name: {
						remote: "${message("console.validate.exist")}"
					}
                }
            });

        });
    </script>
</head>
    
<body>
    <div id="wrapper">
        <!-- start 导航 -->
        [#include "/console/include/nav.ftl" /]
        <!-- end 导航-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <!-- start 头部 -->
            [#include "/console/include/header.ftl" /]
            <!-- end 头部-->
            <!-- start 头部面包屑区域 -->
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>${message("添加成长记标签")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("添加成长记标签")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <!-- end 头部面包屑区域 -->
            <!-- start 中间内容部分 -->
            <div class="wrapper wrapper-content animated fadeIn">
                <!-- start 新增成长记标签-->
                <form id="inputForm" action="save.ct" method="post">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content" style="width:50%;margin:0 auto;">
                                    <div class="row">
                                        <div class="col-sm-4 m-b-xs"></div>
                                    </div>
                                    <div class="table-responsive">
                                        <table id="listTable" class="table table-striped">
                                            <tr>
                                                <th>
                                                	<span class="requiredField">*</span>${message("标签名")}:
                                            	</th>
                                                <td><input type="text" name="name" class="form-control" maxlength="30" /></td>
                                            </tr>
                                        </table>
                                        <table class="input">
                                            <tr>
                                                <th>&nbsp;</th>
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
                <!-- end 新增成长记标签-->
            </div>
            <!-- end 中间内容部分-->
            [#include "/console/include/footer.ftl" /]
        </div>
    </div>
</body>

</html>