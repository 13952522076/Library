<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.paymentPlugin.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
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
			content: "${message("console.paymentPlugin.installConfirm")}",
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
			content: "${message("console.paymentPlugin.uninstallConfirm")}",
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
                    <h2>${message("console.paymentPlugin.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a> 
                        </li>
                        <li>
                           <strong>${message("console.paymentPlugin.list")} <span>(${message("console.page.total", paymentPlugins?size)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start  支付插件列表 -->
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-4 m-b-xs">
                                        <a href="javascript:;" id="refreshButton" class="btn btn-w-m btn-primary">
											<span>&nbsp;</span>${message("console.common.refresh")}
										</a>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                     <form id="listForm" action="list.ct" method="get">
											<table id="listTable" class="table table-striped">
												<tr>
													<th>
														<span>${message("PaymentPlugin.name")}</span>
													</th>
													<th>
														<span>${message("PaymentPlugin.version")}</span>
													</th>
													<th>
														<span>${message("PaymentPlugin.author")}</span>
													</th>
													<th>
														<span>${message("PaymentPlugin.isEnabled")}</span>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list paymentPlugins as paymentPlugin]
													<tr>
														<td>
															[#if paymentPlugin.siteUrl??]
																<a href="${paymentPlugin.siteUrl}" target="_blank">${paymentPlugin.name}</a>
															[#else]
																${paymentPlugin.name}
															[/#if]
														</td>
														<td>
															${paymentPlugin.version!'-'}
														</td>
														<td>
															${paymentPlugin.author!'-'}
														</td>
														<td>
															<span class="${paymentPlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
														</td>
														<td>
															[#if paymentPlugin.isInstalled]
																[#if paymentPlugin.settingUrl??]
																	[@shiro.hasPermission name = "console:paymentPlugin_a_setting"]
																		<a href="${paymentPlugin.settingUrl}">[${message("console.paymentPlugin.setting")}]</a>
																	[/@shiro.hasPermission]
																[/#if]
																[#if paymentPlugin.uninstallUrl??]
																	[@shiro.hasPermission name = "console:paymentPlugin_a_uninstall"]
																		<a href="${paymentPlugin.uninstallUrl}" class="uninstall">[${message("console.paymentPlugin.uninstall")}]</a>
																	[/@shiro.hasPermission]
																[/#if]
															[#else]
																[#if paymentPlugin.installUrl??]
																	[@shiro.hasPermission name = "console:paymentPlugin_a_install"]
																		<a href="${paymentPlugin.installUrl}" class="install">[${message("console.paymentPlugin.install")}]</a>
																	[/@shiro.hasPermission]
																[/#if]
															[/#if]
														</td>
													</tr>
												[/#list]
											</table>
										</form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
	             <!-- end 地区管理 -->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
      </div>
    </div>
</body>
</html>