<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.area.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $delete = $("#listTable a.delete");
	
	[@flash_message /]
	
	// 删除
	$delete.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("console.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.ct",
					type: "POST",
					data: {id: $this.attr("val")},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$this.parent().html("&nbsp;");
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
                    <h2>${message("console.area.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("console.area.list")} <span>(${message("console.page.total", areas?size)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start  地区管理 -->
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-4 m-b-xs">
                                       <a href="add.ct[#if parent??]?parentId=${parent.id}[/#if]" class="btn btn-w-m btn-primary">
											<span class="addIcon">&nbsp;</span>${message("console.common.add")}
										</a>
										[#if parent??]
												[#if parent.parent??]
													<a href="list.ct?parentId=${parent.parent.id}" class="btn btn-w-m btn-primary">
														<span class="upIcon">&nbsp;</span>${message("console.area.parent")}
													</a>
												[#else]
													<a href="list.ct" class="btn btn-white">
														<span class="upIcon">&nbsp;</span>${message("console.area.parent")}
													</a>
												[/#if]
										[/#if]
                                    </div>
                                </div>
                                <div class="table-responsive">
                                     <table id="listTable" class="table table-striped">
										<tr>
											<th colspan="5" class="green" style="text-align: center;">
												[#if parent??]${message("console.area.parent")} - ${parent.name}[#else]${message("console.area.root")}[/#if]
											</th>
										</tr>
										[#list areas?chunk(5, "") as row]
											<tr>
												[#list row as area]
													[#if area?has_content]
														<td>
															<a href="list.ct?parentId=${area.id}" title="${message("console.common.view")}">${area.name}</a>
															<a href="edit.ct?id=${area.id}">[${message("console.common.edit")}]</a>
															<a href="javascript:;" class="delete" val="${area.id}">[${message("console.common.delete")}]</a>
														</td>
													[#else]
														<td>
															&nbsp;
														</td>
													[/#if]
												[/#list]
											</tr>
										[/#list]
										[#if !areas?has_content]
											<tr>
												<td colspan="5" style="text-align: center; color: red;">
													${message("console.area.emptyChildren")} <a href="add.ct[#if parent??]?parentId=${parent.id}[/#if]" style="color: gray">${message("console.common.add")}</a>
												</td>
											</tr>
										[/#if]
									</table>
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