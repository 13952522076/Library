<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.appCategory.list")}</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $search = $("#search");
	var $searchProperty = $("#searchProperty");
	var $ban = $(".ban");
	var $start = $(".start");
	[@flash_message /]
	
	$search.click(function(){
		$searchProperty.val("name");
	})
	
	// 禁用
	$ban.click(function() {
		var $this = $(this);
		var id = $this.closest("tr").find("input[name='ids']").val();
		$.dialog({
			type: "warn",
			content: "${message("console.appCategory.banConfirm")}",
			ok: message("console.dialog.ok"),
			cancel: message("console.dialog.cancel"),
			onOk: function() {
				layer.load();
				$.ajax({
					url: "ban.ct",
					type: "POST",
					data: {id:id},
					dataType: "json",
					cache: false,
					success: function(message) {
						layer.closeAll('loading');
						$.message(message);
						if (message.type == "success") {
								setTimeout(function() {
									location.reload(true);
								}, 1500);
						}
					}
				});
			}
		});
	});
	
	// 启用
	$start.click(function() {
		var $this = $(this);
		var id = $this.closest("tr").find("input[name='ids']").val();
		$.dialog({
			type: "warn",
			content: "${message("console.appCategory.startConfirm")}",
			ok: message("console.dialog.ok"),
			cancel: message("console.dialog.cancel"),
			onOk: function() {
				layer.load();
				$.ajax({
					url: "start.ct",
					type: "POST",
					data: {id:id},
					dataType: "json",
					cache: false,
					success: function(message) {
						layer.closeAll('loading');
						$.message(message);
						if (message.type == "success") {
								setTimeout(function() {
									location.reload(true);
								}, 1500);
						}
					}
				});
			}
		});
	});
});

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
                    <h2>${message("console.appCategory")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.appCategory.list")}<span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       	<!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
				<!-- start  应用分类列表管理 -->
				<form id="listForm" action="list.ct" method="get">
					<div class="row">
						<div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-12 m-b-xs">
											<div class="btn-group">
		                                        [@shiro.hasPermission name = "console:addAppCategory"]
													<a href="add.ct" class="btn btn-primary">
														<span class="addIcon">&nbsp;</span>${message("console.common.add")}
													</a>
												[/@shiro.hasPermission]
												<a href="javascript:;" id="refreshButton"  class="btn btn-primary">
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
												<input type="text" name="searchValue" value="${page.searchValue}" class="input-sm form-control" style="width:200px;margin-top: 12px;" placeholder="输入名称来搜索">
												<input type="submit" class="btn btn-primary" id="search" value="搜索"/>
											</form>
											</div>
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th class="check">
													<input type="checkbox" id="selectAll" />
												</th>
												<th>
													<a href="javascript:;" class="sort" name="name">${message("AppCategory.name")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="description">${message("AppCategory.description")}</a>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list page.content as appCategory]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${appCategory.id}" />
													</td>
													<td>
														${abbreviate(appCategory.name, 25, "...")}
													</td>
													<td>
														${abbreviate(appCategory.description, 25, "...")}
													</td>
													<td>
													[@shiro.hasPermission name = "console:editAppCategory"]
														<a href="edit.ct?id=${appCategory.id}">[${message("console.common.edit")}]</a>
													[/@shiro.hasPermission]
													[@shiro.hasPermission name = "console:deleteAppCategory"]
														[#if appCategory.status]<a href="javascript:;" class="ban">[${message("console.appCategory.ban")}]</a>
														[#else]<a href="javascript:;" class="start">[${message("console.appCategory.start")}]</a>
														[/#if]
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
                </form>
	             <!-- end 应用分类列表-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>