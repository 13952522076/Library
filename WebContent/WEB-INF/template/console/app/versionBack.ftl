<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.app.versionBack")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/switch/bootstrap-switch.js"></script>
<link rel="stylesheet" href="${base}/resources/console/switch/bootstrap-switch.css" >
<script type="text/javascript">
$().ready(function() {
	
	var $versionBack = $("#versionBack");
	[@flash_message /]
	$versionBack.click(function(){
		var $this = $(this);
		var id = $this.closest("tr").find("input[name='ids']").val();
		$.dialog({
			type: "warn",
			content: "${message("确认回退至该版本么？")}",
			ok: message("console.dialog.ok"),
			cancel: message("console.dialog.cancel"),
			onOk: function() {
				layer.load();
				$.ajax({
					url: "versionBack.ct",
					type: "POST",
					data: {id:id},
					dataType: "json",
					cache: false,
					success: function(message) {
						layer.closeAll('loading');
						$.message(message);
						if (message.type == "success") {
								setTimeout(function() {
									location.href="${base}/console/app/list.ct";
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
                    <h2>${message("console.app.versionBack")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("console.app.versionBack")}</strong>
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
				<form id="listForm" action="appVersionList.ct" method="get">
					<div class="row">
						<div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th>
													<span>${message("App.introduction")}</span>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list page.content as app]
											<tr>
												<input type="hidden" value="${app.id}" name="ids"/>
												<td>
													<div style="float:left;">
														<img src="${app.logoAppImg}" alt="${App.logoAppImg}" style="width:60px;height:60px;">
													</div>
												    <p style="color:#FDA72B;margin-left:75px;"><strong>${app.appName}</strong></p>
												    <p style="margin-left:75px;">${app.appSize}|${message("App.versionCode")}：${app.versionCode}|${message("App.versionName")}：${app.versionName}</p>
												</td>
												<td style="vertical-align: middle;">
													[#if app.parent??]
														[#if app.operatingSystem == 'android' ||  app.operatingSystem == 'ios']
															-
														[#else]
															[@shiro.hasPermission name = "console:backApp"]
																<a href="javascript:;" id="versionBack">[${message("console.app.back")}]</a>
															[/@shiro.hasPermission]
														[/#if]
													[#else]
														当前使用版本
													[/#if]
												</td>
											</tr>
											[/#list]
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='edit.ct?id=${id}'" />
												</td>
											</tr>
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