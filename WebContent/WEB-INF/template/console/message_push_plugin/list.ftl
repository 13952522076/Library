<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.messagePushPlugin.list")} -图书智能管理推荐系统</title>
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
			content: "${message("console.messagePushPlugin.installConfirm")}",
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
                    <h2>${message("console.messagePushPlugin.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.messagePushPlugin.list")}<span>(${message("console.page.total", messagePushPlugins?size)})</span></strong>
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
													<span>${message("MessagePushPlugin.name")}</span>
												</th>
												<th>
													<span>${message("MessagePushPlugin.version")}</span>
												</th>
												<th>
													<span>${message("MessagePushPlugin.author")}</span>
												</th>
												<th>
													<span>${message("MessagePushPlugin.isEnabled")}</span>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list messagePushPlugins as messagePushPlugin]
												<tr>
													<td>
														[#if messagePushPlugin.siteUrl??]
															<a href="${messagePushPlugin.siteUrl}" target="_blank">${messagePushPlugin.name}</a>
														[#else]
															${messagePushPlugin.name}
														[/#if]
													</td>
													<td>
														${messagePushPlugin.version!'-'}
													</td>
													<td>
														${messagePushPlugin.author!'-'}
													</td>
													<td>
														<span class="${messagePushPlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
													</td>
													<td>
														[#if messagePushPlugin.isInstalled]
															[#if messagePushPlugin.settingUrl??]
																[@shiro.hasPermission name = "console:settingMessagePushPlugin"]
																	<a href="${messagePushPlugin.settingUrl}">[${message("console.messagePushPlugin.setting")}]</a>
																[/@shiro.hasPermission]
															[/#if]
															[#if messagePushPlugin.uninstallUrl??]
																[@shiro.hasPermission name = "console:uninstallMessagePushPlugin"]
																	<a href="${messagePushPlugin.uninstallUrl}" class="uninstall">[${message("console.messagePushPlugin.uninstall")}]</a>
																[/@shiro.hasPermission]
															[/#if]
														[#else]
															[#if messagePushPlugin.installUrl??]
																[@shiro.hasPermission name = "console:installMessagePushPlugin"]
																	<a href="${messagePushPlugin.installUrl}" class="install">[${message("console.messagePushPlugin.install")}]</a>
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