<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("年级列表")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
 	//上移
    $("a.iconButton.upIcon").click(function() {
        var $tr = $(this).parents("tr");
        if ($tr.index() == 1) {
           $.message("warn", "${message("admin.productCategory.upbtnMessage")}");
            return false;
        }
        else{
          var $currentId= this.attributes.dictGradeOrder.nodeValue;
          var $changeId =  $tr.prev().find("td:eq(2)").find("a:eq(0)")[0].attributes.dictGradeOrder.nodeValue;
          $.ajax({
					url: "changeOrders.ct",
					type: "POST",
					data: {
					    currentId: $currentId, 
					    changeId: $changeId, 
					    btnType:"up"
						},
					dataType: "json",
					cache: false,
					async:false,
					success: function(message) {
					if (message.type == "success"){  
					    $tr.prev().before($tr);
				  }
				}
			});
        }
    });
    //下移
    var $downbtns = $("a.iconButton.downIcon");
    var len = $downbtns.length;
    $downbtns.click(function() {
        var $tr = $(this).parents("tr");
        if ($tr.index() == len) {
            $.message("warn", "${message("admin.productCategory.downbtnMessage")}");
            return false;
        }
        else{
          var $currentId= this.attributes.dictGradeOrder.nodeValue;
          var $changeId =  $tr.next().find("td:eq(2)").find("a:eq(0)")[0].attributes.dictGradeOrder.nodeValue;
          $.ajax({
				url: "changeOrders.ct",
				type: "POST",
				data: {
				    currentId: $currentId, 
				    changeId: $changeId, 
				    btnType:"down",
					},
				dataType: "json",
				cache: false,
				async:false,
				success: function(message) {
				if (message.type == "success"){  
				    $tr.next().after($tr);
			  	}
			  }
		  });
        }
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
                    <h2>${message("年级列表")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("年级列表")} <span>(${message("console.page.total", page.total)})</span></strong>
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
                                    <div class="col-sm-4 m-b-xs">
                                    	<div class="btn-group">
	                                       	[@shiro.hasPermission name = "console:addDictGrade"]
	                                        <a href="add.ct" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("console.common.add")}
											</a>
										   	[/@shiro.hasPermission]
	                                        [@shiro.hasPermission name = "console:deleteDictGrade"]
											<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
												<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
											</a>
											[/@shiro.hasPermission]
											<a href="javascript:;" id="refreshButton" class="btn btn-primary">
												<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
											</a>
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
														<a href="javascript:;" class="sort" name="gradeName">${message("年级")}</a>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list page.content as dictGrade]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${dictGrade.id}" />
														</td>
														<td>
															${dictGrade.gradeName}
														</td>
														
														<td>
														[@shiro.hasPermission name = "console:editDictGrade"]
															<a href="#"class="iconButton upIcon" dictGradeOrder="${dictGrade.id}" style="float: none;">上移</a>
				        									<a href="#"class="iconButton downIcon" dictGradeOrder="${dictGrade.id}" style="float: none;">下移</a>
															<a href="edit.ct?id=${dictGrade.id}">[${message("console.common.edit")}]</a>
														    <a href="upgrade.ct?id=${dictGrade.id}" >[${message("升级")}]</a>
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