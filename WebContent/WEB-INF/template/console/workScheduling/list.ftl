<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictSchool.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});
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
                    <h2>${message("排班管理")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("排班列表")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start  学校管理 -->
	             <form id="listForm" action="list.ct" method="get">
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-4 m-b-xs">
	                                    <div class="btn-group">
	                                       	[@shiro.hasPermission name = "console:addWorkScheduling"]
	                                        <a href="add.ct" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("console.common.add")}
											</a>
										   	[/@shiro.hasPermission]
											[@shiro.hasPermission name = "console:deleteWorkScheduling"]
												<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
													<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
												</a>
											[/@shiro.hasPermission]
											<a href="javascript:;" id="refreshButton" class="btn btn-primary">
												<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
											</a>
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
														<a href="javascript:;" class="sort" name="member">${message("对应老师")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" >${message("时间")}</a>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list page.content as workScheduling]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${workScheduling.id}" />
														</td>
														<td>
															${workScheduling.member.realName}
														</td>
														<td>
															${bigTimeMap.get(workScheduling.member.id)}
														</td>
														<td>
														[@shiro.hasPermission name = "console:editWorkScheduling"]
															<a href="edit.ct?id=${workScheduling.id}">[${message("console.common.edit")}]</a>
														[/@shiro.hasPermission]
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
	             <!-- end 学校管理 -->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>