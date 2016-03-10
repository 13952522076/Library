<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictClass.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/switch/bootstrap-switch.js"></script>
<link rel="stylesheet" href="${base}/resources/console/switch/bootstrap-switch.css" >
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]
	$("#switchlight").bootstrapSwitch({
		//state: false //
		[#if classStatus =="graduated"]
		state: false
		[#else]
		state: true
		[/#if]
	});
	$("#switchlight").on('switchChange.bootstrapSwitch', function (e, state) {
		if(state){
			$("#classStatus").val("active");
		}else{
			$("#classStatus").val("graduated");
		}
     	$("#listForm").submit();
	});

});

function batchStatic(id){
	$.ajax({
		type: "GET",
		url: "${base}/console/dict_class/batchStatic.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(){
			$.message("success", "操作成功");
		}
	});
	
}
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
                    <h2>${message("console.dictClass.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("console.dictClass.list")} <span>(${message("console.page.total", page.total)})</span></strong>
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
	             <form id="listForm" action="list.ct" method="get">
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-12 m-b-xs">
                                    	<div class="btn-group">
	                                       	[@shiro.hasPermission name = "console:addDictClass"]
	                                        <a href="add.ct" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("console.common.add")}
											</a>
										   	[/@shiro.hasPermission]
	                                        [@shiro.hasPermission name = "console:deleteDictClass"]
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
	                                       	<!--活跃／毕业-->
	                                       	<div class="input-group pull-right" style="margin-top:10px;">
	                                       		<input type="hidden"  name="classStatus" id="classStatus">
												<input class="btn" type="checkbox" id="switchlight"  data-on-text="活跃" data-off-text="毕业" data-label-text="班级"/>
											 </div>
											<!--活跃／毕业-->
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
														<a href="javascript:;" class="sort" name="dictSchool">${message("DictClass.dictSchool")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="code">${message("DictClass.code")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="name">${message("DictClass.name")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="cmaster">${message("DictClass.cmaster")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="classStatus">${message("DictClass.classStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="description">${message("DictClass.description")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("console.common.createDate")}</a>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list page.content as dictClass]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${dictClass.id}" />
														</td>
														<td>
															${dictClass.dictSchool.name}
														</td>
														<td>
															${dictClass.code}
														</td>
														<td>
															${dictClass.name}
														</td>
														<td>
															${dictClass.cmaster}
														</td>
														<td>
															[#if dictClass.classStatus == "active"]
																${message("console.class.active")}
															[#elseif dictClass.classStatus == "graduated"]
																${message("console.class.graduated")}
															[/#if]
															
														</td>
														<td>
															[#if dictClass.description? length lt 10]
																${dictClass.description}
															[#else]
																${dictClass.description?substring(0,9)}...
															[/#if]
														</td>
														<td>
															<span title="${dictClass.createDate?string("yyyy-MM-dd HH:mm:ss")}">${dictClass.createDate}</span>
														</td>
														<td>
														[@shiro.hasPermission name = "console:editDictSchool"]
															<a href="edit.ct?id=${dictClass.id}">[${message("console.common.edit")}]</a>
															<a onclick="batchStatic(${dictClass.id})" >[${message("批量静态化")}]</a>
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
                </form>
	             <!-- end 地区管理 -->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>