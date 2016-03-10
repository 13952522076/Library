<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.main.RecipeSectionManager")} - MO&Co.</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${base}/resources/console/css/plugins/iCheck/custom.css" >
<script type="text/javascript">
	//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorClass: " m-b-none",
        validClass: " m-b-none"
    });
	$().ready(function() {
	[@flash_message /]
		
		var $inputForm = $("#inputForm");
		// 表单验证
		$inputForm.validate({
			rules: {
				addSectionName:{
					required: true,
					maxlength:10
				},
				addOrder:{
					required: true,
					digits:true,
					range:[0,100] 
				}
			},
			messages: {}
		});
		$("#editForm").validate({
			rules: {
				editOrder:{
					required: true,
					digits:true
				},
				editSectionName:{
					required: true
				}
			},
			messages: {}
		});
	});
	
	function addRecipeSection(){
		
	}
	
	function showAddDiv(){
		$("#addDiv").fadeIn();
	}
	
	function closeDiv(){
		
		$("#addDiv").fadeOut();
		$("#editDiv").fadeOut();
		$("#inputForm")[0].reset(); 
	}
	
	function update(id){
		$.ajax({
		type: "GET",
		url: "edit.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(data){
			$("#editDiv").fadeIn();
			$("#editId").val(data.id);
			$("#editSectionName").val(data.sectionName);
			$("#editOrder").val(data.order);
		}
	});
	}
	
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
                    <h2>${message("console.main.RecipeSectionManager")} </h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.main.RecipeSectionManager")}  <span>(${message("console.page.total", (recipesSections?size))})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
				<!-- start  管理员列表管理 -->
				<!-- start 弹出新增页面  -->
				<div id="addDiv" class="modal in" style="overflow: auto; display: none;" tabindex="-1"
				aria-hidden="false">
				    <div class="modal-backdrop in" onclick="closeDiv()"></div>
				    <div class="modal-dialog">
				        <div class="modal-content" style="margin-top:20%;">
				            <div class="modal-header">
				                <a type="button" onclick="closeDiv()" class="close" data-dismiss="modal" aria-hidden="true">
				                    ×
				                </a>
				                <h4 class="modal-title">
				                    ${message("common.new")}
				                </h4>
				            </div>
				            <div class="modal-body ui-front" style="overflow-y: auto;text-align: center;">
				            <form id="inputForm" action="${base}/console/recipe_section/add.ct" method="post">
								<div class="form-group">
                                    <label class="col-sm-3 control-label">${message("console.recipes.recipesSectionName")}：</label>
                                    <div class="col-sm-8">
										<input type="text" id="addSectionName" name="addSectionName" class="form-control" required>
	                                </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">${message("common.orderNumber")}：</label>
                                    <div class="col-sm-8">
										<input type="text" id="addOrder" value="0" name="addOrder" class="form-control">
	                                </div>
                                </div>
                            	<button type="submit" class="btn btn-outline btn-success">${message("common.new")}</button>
			            	</form>
				            </div>
				        </div>
				    </div>
				</div>
				<!-- end 弹出新增页面-->
				<!-- start 弹出修改页面  -->
				<div id="editDiv" class="modal in" style="overflow: auto; display: none;" tabindex="-1"
				aria-hidden="false">
				    <div class="modal-backdrop in" onclick="closeDiv()"></div>
				    <div class="modal-dialog">
				        <div class="modal-content" style="margin-top:20%;">
				            <div class="modal-header">
				                <a type="button" onclick="closeDiv()" class="close" data-dismiss="modal" aria-hidden="true">
				                    ×
				                </a>
				                <h4 class="modal-title">
				                    ${message("common.edit")}
				                </h4>
				            </div>
				            <div class="modal-body ui-front" style="overflow-y: auto;text-align: center;">
				            <form id="editForm" action="${base}/console/recipe_section/update.ct" method="post">
								<input type="hidden" name="editId" id="editId">
								<div class="form-group">
                                    <label class="col-sm-3 control-label">${message("console.recipes.recipesSectionName")}：</label>
                                    <div class="col-sm-8">
										<input type="text" id="editSectionName" name="editSectionName" class="form-control">
	                                </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">${message("common.orderNumber")}：</label>
                                    <div class="col-sm-8">
										<input type="text" id="editOrder"  name="editOrder" class="form-control">
	                                </div>
                                </div>
                            	<button type="submit" class="btn btn-outline btn-success">${message("common.edit")}</button>
			            	</form>
				            </div>
				        </div>
				    </div>
				</div>
				<!-- end 弹出修改页面-->
				
				<form id="listForm" action="list.ct" method="get">
					<div class="row">
						<div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-7 m-b-xs">
											<div class="btn-group">
		                                        [@shiro.hasPermission name = "console:addRecipeSection"]
													<a href="javascript:;" onclick="showAddDiv()" class="btn btn-primary">
														<span class="addIcon">&nbsp;</span>${message("console.common.add")}
													</a>
												[/@shiro.hasPermission]
												[@shiro.hasPermission name = "console:deleteRecipeSection"]
													<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
														<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
													</a>
												[/@shiro.hasPermission]
												<a href="javascript:;" id="refreshButton"  class="btn btn-primary">
													<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
												</a>
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
													<a href="javascript:;">${message("console.recipes.recipesSectionName")}</a>
												</th>
												<th>
													<a href="javascript:;">${message("common.orderNumber")}</a>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list recipesSections as recipeSection]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${recipeSection.id}" />
													</td>
													<td>
														${recipeSection.sectionName}
													</td>
													<td>
														${recipeSection.order}
													</td>
													<td>
													[@shiro.hasPermission name = "console:editRecipeSection"]
														<a href="javascript:;" onclick="update(${recipeSection.id})" href="editRecipeSection.ct?id=${member.id}">[${message("console.common.edit")}]</a>
													[/@shiro.hasPermission]
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
	             <!-- end 管理员列表-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>