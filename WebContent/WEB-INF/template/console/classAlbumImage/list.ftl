<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.campusviewImg.list")} -图书智能管理推荐系统</title>
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
<style type="text/css">
.datagrid-cell,
.datagrid-cell-rownumber {
  margin: 0;
  padding: 0 4px;
  white-space: nowrap;
  word-wrap: normal;
  overflow: hidden;
  height: 18px;
  line-height: 18px;
  font-size: 12px;
}
.datagrid-cell-c1-item {
  width: 772px;
}
.imgBox {
  float: left;
  width: 85px;
  height: 85px;
  margin-left: 10px;
}
.imgBox img {
  width: 85px;
  height: 85px;
}
.contBox {
  margin-left: 20px;
  width: 440px;
  float: left;
}
.contBox .cont-tit {
  font-weight: bold;
  color: #4E4E4E;
}
.contBox p {
  display: block;
  width: 500px;
  word-break: break-all;
  word-wrap: break-word;
}
.contBox .cont-detail {
  color: #909090;
}
.mt10 {
  margin-top: 10px;
}
</style>
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
                    <h2>${message("班级相册列表")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("班级相册列表")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
        		<form id="listForm" action="list.ct" method="get">
             	<!-- start  地区管理 -->
             	<div class="row">
           	 		<div class="col-lg-12">
                    	<div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-6 m-b-xs">
                                    <div class="btn-group">
	                                       	[@shiro.hasPermission name = "console:addClassAlbumImage"]
	                                        <a href="add.ct" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("console.common.add")}
											</a>
										   	[/@shiro.hasPermission]
	                                        [@shiro.hasPermission name = "console:deleteClassAlbumImage"]
												<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
													<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
												</a>
											[/@shiro.hasPermission]
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
														<span>${message("相关班级")}</span>
													</th>
													<th>
														<span>${message("描述")}</span>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list page.content as classAlbumImage]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${classAlbumImage.id}" />
														</td>
														<td>
															${classAlbumImage.dictClass.name}
														</td>
														<td>
															<div style=";white-space:normal;height:auto;" class="datagrid-cell datagrid-cell-c1-item">
																[#if classAlbumImage.coverImage??]
																	<div class="imgBox">	
																		<img src="${classAlbumImage.coverImage}" />
																	</div>
																[/#if]
																<div class="contBox">	
																	<p class="cont-tit">${classAlbumImage.description}<span>【${classAlbumImage.creator}】</span></p>	
																	<p class="cont-detail mt10">状态：
																	[#if classAlbumImage.status == 1]
																		屏蔽
																	[#else]
																		正常
																	[/#if]
																	</p>
																</div>
															</div>
														</td>
														<td>
														[@shiro.hasPermission name = "console:editClassAlbumImage"]
															<a href="edit.ct?id=${classAlbumImage.id}">[${message("console.common.edit")}]</a>
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
             	<!-- end 地区管理 -->
             	</form>
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>