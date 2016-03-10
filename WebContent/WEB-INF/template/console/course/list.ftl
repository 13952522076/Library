<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.main.curriculumScheduleNav")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/course.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.form.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/excelImport.js"></script>

<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
});
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
                        <h2>${message("console.TheCurriculumManagement")}</h2>
                        <ol class="breadcrumb">
                            <li>
                                <a href="${base}/console/common/main.ct">${message("console.path.index")}</a>
                            </li>
                            <li>
                                <strong> ${message("console.TheCurriculum")}</strong>
                            </li>
                        </ol>
                    </div>
                    <div class="col-lg-2"></div>
                </div>
                <!-- end 头部面包屑区域 -->
                <!-- start 中间内容部分 -->
                <div class="wrapper wrapper-content animated fadeIn">
                	<!-- start 文件上传插件  -->
					<div id="excelImportDiv" class="modal in" style="overflow: auto; display: none;" tabindex="-1"
					aria-hidden="false">
					    <div class="modal-backdrop in" onclick="closeExcelImportDiv()"></div>
					    <div class="modal-dialog">
					        <div class="modal-content" style="margin-top:20%;">
					            <div class="modal-header">
					                <a type="button" onclick="closeExcelImportDiv()" class="close" data-dismiss="modal" aria-hidden="true">
					                    ×
					                </a>
					                <h4 class="modal-title">
					                    ${message("common.importFile")}
					                </h4>
					            </div>
					            <div class="modal-body ui-front" style="overflow-y: auto;">
					            <form id="excelImportForm" action="${base}/console/excel/import_course.ct" method="post" enctype="multipart/form-data">
									<div class="form-group">
                                    	<label class="col-sm-3 control-label ">
                                    		<span class="requiredField ">*</span>学年：
                                		</label>
	                                    <div class="col-sm-8 m-b-xs ">
		                                    <select name="schoolYearMngId" class="input-sm form-control input-s-sm inline ">
			                                    [#list schoolYearMngs as schoolYearMng]
                                                <option value="${schoolYearMng.id}">
                                                    ${schoolYearMng.startYear}-${schoolYearMng.endYear}第${schoolYearMng.term}学期
                                                </option>
                                                [/#list]
                                            </select>
	                                    </div>
                                    </div>
                                    <div class="form-group " style="margin-bottom:20px;">
                                        <label class="col-sm-3 control-label">
                                            <span class="requiredField">*</span>班级：
                                        </label>
                                        <div class="col-sm-8 m-b-xs">
                                            <select name="classId" class="input-sm form-control input-s-sm inline">
                                                [#list classes as class]
                                                <option value="${class.id}">
                                                    ${class.name}
                                                </option>
                                                [/#list]
                                            </select>
                                        </div>
                                    </div>
									<!--<input id="excel_file" type="file" name="file"/>-->
									<br>
									<label class="btn btn-info  dim" type="button">
										<i class="fa fa-paste"></i>
										<input id="excel_file" name="file" type="file" style="display: none" onchange="changeLabel()"/>
										<label for="excel_file">
										  	<span id="fileLabel">请上传文件<span>
										</label>
                            		</label>
									<a class="btn btn-outline btn-success dim" style="float:right;" onclick="excelImportSubmit()">
				                		<i class="fa fa-upload"></i>${message("common.import")}
				            		</a>
				            		<div style="  text-align: center;font-size: larger;color: #E55FD9;" id="fileTypeErrorMsg"></div>
				            	</form>
				            	<div style="border-top: 1px solid #ADAAAA; width: 350px; margin-top: 30px;color:rgb(103, 106, 108);">
				            		<span style="font-size: 18px;color: rgb(142, 138, 138);">注意：</span><br>
				            		<span>1，星期为1-7的数字代表周一至周日。</span><br>
				            		<span>2，课节为1-8的数字代表上午4节和下午4节。</span><br>
				            		<span>3，开始时间，结束时间形如8:30 14:30</span><br>
				            	</div>
					            </div>
					        </div>
					    </div>
					</div>
					<!-- end   文件上传插件-->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <!-- start 列表管理 -->
                                    <div class="btn-group">
                                        <a href="add.ct" class="btn btn-primary">
                                            <span class="addIcon">&nbsp;</span>
                                            ${message("console.common.add")}
                                        </a>
                                        <a href="${base}/console/excel/downloadExcel.ct?fileName=course.xls" class="btn btn-primary">
											<span class="addIcon">&nbsp;</span>${message("common.downloadTemplate")}
										</a>
										<a href="javascript:;" onclick="showExcelImport()" class="btn btn-primary">
											<span class="addIcon">&nbsp;</span>${message("common.batchImport")}
										</a>
                                    </div>
                                    <div class="btn-group">
                                        <div class="row CycleShow semester">
                                            <span style="margin-left: 30px;">
                                                <select id="schoolYearMngId" onchange="showCourse()">
                                                    [#list schoolYearMngs as schoolYearMng]
                                                    <option value="${schoolYearMng.id}">
                                                        ${schoolYearMng.startYear}-${schoolYearMng.endYear}第${schoolYearMng.term}学期
                                                    </option>
                                                    [/#list]
                                                </select>
                                            </span>
                                            <span>
                                                <select id="classId" onchange="showCourse()">
                                                    [#list classes as class]
                                                    <option value="${class.id}">
                                                        ${class.name}
                                                    </option>
                                                    [/#list]
                                                </select>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- start table展示区 -->
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <tr>
                                                <th colspan="2" class="lessonsDate">时间</th>
                                                <th>星期一</th>
                                                <th>星期二</th>
                                                <th>星期三</th>
                                                <th>星期四</th>
                                                <th>星期五</th>
                                            </tr>
                                            <tr>
                                                <td rowspan="4"><span class="morningStyle">上</br>午</span></td>
                                                <td><span>第一节</span></td>
                                                <td id="1-1" name="courseName"></td>
                                                <td id="1-2" name="courseName"></td>
                                                <td id="1-3" name="courseName"></td>
                                                <td id="1-4" name="courseName"></td>
                                                <td id="1-5" name="courseName"></td>
                                            </tr>
                                            <tr>
                                                <td><span>第二节</span>
                                                </td>
                                                <td id="2-1" name="courseName"></td>
                                                <td id="2-2" name="courseName"></td>
                                                <td id="2-3" name="courseName"></td>
                                                <td id="2-4" name="courseName"></td>
                                                <td id="2-5" name="courseName"></td>
                                            </tr>
                                            <tr>
                                                <td><span>第三节</span></td>
                                                <td id="3-1" name="courseName"></td>
                                                <td id="3-2" name="courseName"></td>
                                                <td id="3-3" name="courseName"></td>
                                                <td id="3-4" name="courseName"></td>
                                                <td id="3-5" name="courseName"></td>
                                            </tr>
                                            <tr>
                                                <td><span>第四节</span></td>
                                                <td id="4-1" name="courseName"></td>
                                                <td id="4-2" name="courseName"></td>
                                                <td id="4-3" name="courseName"></td>
                                                <td id="4-4" name="courseName"></td>
                                                <td id="4-5" name="courseName"></td>
                                            </tr>
                                            <tr><td colspan="7"></td>
                                            </tr>
                                            <tr>
                                                <td rowspan="4"><span class="morningStyle">下</br>午</span></td>
                                                <td><span>第一节</span></td>
                                                <td id="5-1" name="courseName"></td>
                                                <td id="5-2" name="courseName"></td>
                                                <td id="5-3" name="courseName"></td>
                                                <td id="5-4" name="courseName"></td>
                                                <td id="5-5" name="courseName"></td>
                                            </tr>
                                            <tr>
                                                <td><span>第二节</span></td>
                                                <td id="6-1" name="courseName"></td>
                                                <td id="6-2" name="courseName"></td>
                                                <td id="6-3" name="courseName"></td>
                                                <td id="6-4" name="courseName"></td>
                                                <td id="6-5" name="courseName"></td>
                                            </tr>
                                            <tr>
                                                <td><span>第三节</span></td>
                                                <td id="7-1" name="courseName"></td>
                                                <td id="7-2" name="courseName"></td>
                                                <td id="7-3" name="courseName"></td>
                                                <td id="7-4" name="courseName"></td>
                                                <td id="7-5" name="courseName"></td>
                                            </tr>
                                            <tr><td><span>第四节</span></td>
                                                <td id="8-1" name="courseName"></td>
                                                <td id="8-2" name="courseName"></td>
                                                <td id="8-3" name="courseName"></td>
                                                <td id="8-4" name="courseName"></td>
                                                <td id="8-5" name="courseName"></td>
                                            </tr>
                                        </table>
                                        <!-- end table展示区 -->
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
        </div>
    </body>

</html>