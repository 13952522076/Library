<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.main.title")} -图书智能管理推荐系统</title>
<meta name="keywords" content="图书智能管理推荐系统">
<meta name="description" content="图书智能管理推荐系统">
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required:true,
				maxlength:15
			},
			order: "digits"
		}
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
		                <h2>${message("console.area.add")}</h2>
	                    <ol class="breadcrumb">
	                        <li>
	                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
	                        </li>
	                        <li>
	                            <strong>${message("console.area.add")}</strong>
	                        </li>
	                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	        <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	       <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增区域-->
	              <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="table-responsive">
									<form id="inputForm" action="save.ct" method="post">
										[#if parent??]
											<input type="hidden" name="parentId" value="${parent.id}" />
										[/#if]
										<table class="table table-striped">
											<tr>
												<th>
													${message("console.area.parent")}:
												</th>
												<td>
													[#if parent??]${parent.name}[#else]${message("console.area.root")}[/#if]
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Area.name")}:
												</th>
												<td>
													<input type="text" name="name" class="form-control" maxlength="100" />
												</td>
											</tr>
											<tr>
												<th>
													${message("console.common.order")}:
												</th>
												<td>
													<input type="text" name="order"  class="form-control" maxlength="9" />
												</td>
											</tr>
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input type="submit" class="btn btn-primary" value="${message("console.common.submit")}" />
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct[#if parent??]?parentId=${parent.id}[/#if]'" />
												</td>
											</tr>
										</table>
									</form>
 								</div>
                            </div>
                        </div>
                    </div>
                </div>
                 <!-- end 新增区域-->
		</div>
	 <!-- end 中间内容部分 -->
	</div>
</div>
</body>
</html>