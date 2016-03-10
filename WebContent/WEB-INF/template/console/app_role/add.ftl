<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.role.add")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<style type="text/css">
.authorities label {
  cursor: pointer;
}

table.input1 td {
	line-height: 26px;
}

.authorities li {float:left;  margin-left: 20px;}

li{list-style-type:none;}

</style>
<script type="text/javascript">
$().ready(function() {

	// 表单验证
	$("#inputForm").validate({
		rules: {
			name:{
				required:true,
				maxlength:200
			},
			description:{
				maxlength:200
			}
		}
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
                    <h2>${message("console.path.index")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.appRole.add")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	    
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增应用角色-->
	             <form id="inputForm" action="save.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table  class="table table-bordered">
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Role.name")}:
												</th>
												<td colspan="2">
													<input type="text" name="name"  class="form-control" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Role.description")}:
												</th>
												<td colspan="2">
													<input type="text" name="description"  class="form-control" value="" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>${message("console.appRole.attribute")}</th>
												<td>
													<ul class="authorities" style="padding-left:0px">
														<li>
															<input type="checkbox"  name="memberTypes" id="teacherIcon" value="teacher">
														 	<label for="teacherIcon">${message("console.appRole.teacher")}</label>
														 </li>
														 <li>
															<input type="checkbox"  name="memberTypes" id="parentsIcon" value="patriarch">
														    <label for="parentsIcon">${message("console.appRole.parent")}</label>
														 </li>
													 </ul>
												</td>	
											</tr>
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td colspan="2">
													<input type="submit" class="btn  btn-primary" value="${message("console.common.submit")}" />
					                                <input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 新增角色-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>