<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.storagePlugin.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listTable = $("#listTable");
	var $install = $("#listTable a.install");
	var $uninstall = $("#listTable a.uninstall");
	
	[@flash_message /]

	// 安装
	$install.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("console.storagePlugin.installConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
	});
	
	// 卸载
	$uninstall.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("console.storagePlugin.uninstallConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
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
                    <h2>${message("console.storagePlugin.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.storagePlugin.list")}<span>(${message("console.page.total", storagePlugins?size)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增管理员-->
	             <form id="listForm" action="list.ct" method="get">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
	                                        <a href="javascript:;" id="refreshButton" class="btn btn-primary">
												<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
										    </a>
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped list">
											<tr>
												<th>
													<span>${message("StoragePlugin.name")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.version")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.author")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.isEnabled")}</span>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list storagePlugins as storagePlugin]
												<tr>
													<td>
														[#if storagePlugin.siteUrl??]
															<a href="${storagePlugin.siteUrl}" target="_blank">${storagePlugin.name}</a>
														[#else]
															${storagePlugin.name}
														[/#if]
													</td>
													<td>
														${storagePlugin.version!'-'}
													</td>
													<td>
														${storagePlugin.author!'-'}
													</td>
													<td>
														<span class="${storagePlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
													</td>
													<td>
														[#if storagePlugin.isInstalled]
															[#if storagePlugin.settingUrl??]
																[@shiro.hasPermission name = "console:storagePlugin_a_setting"]
																	<a href="${storagePlugin.settingUrl}">[${message("console.storagePlugin.setting")}]</a>
																[/@shiro.hasPermission]
															[/#if]
															[#if storagePlugin.uninstallUrl??]
																[@shiro.hasPermission name = "console:storagePlugin_a_uninstall"]
																	<a href="${storagePlugin.uninstallUrl}" class="uninstall">[${message("console.storagePlugin.uninstall")}]</a>
																[/@shiro.hasPermission]
															[/#if]
														[#else]
															[#if storagePlugin.installUrl??]
																[@shiro.hasPermission name = "console:storagePlugin_a_install"]
																	<a href="${storagePlugin.installUrl}" class="install">[${message("console.storagePlugin.install")}]</a>
																[/@shiro.hasPermission]
															[/#if]
														[/#if]
													</td>
												</tr>
											[/#list]
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 新增管理员-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>