<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.poster.list")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/console/switch/bootstrap-switch.js"></script>
<link rel="stylesheet" href="${base}/resources/console/switch/bootstrap-switch.css" >
<script type="text/javascript">

	$().ready(function() {
	
		var $listForm = $("#listForm");
		
		[@flash_message /]
	 //上移
    $(".upIcon").click(function() {
        var $tr = $(this).parents("tr");
        if ($tr.index() == 1) {
            $.message("warn", "${message("console.appPoster.top")}");
            return false;
        } else {
            var $currentId = this.attributes.posterOrder.nodeValue;
            var $changeId = $tr.prev().find("td:eq(7)").find("a:eq(0)")[0].attributes.posterOrder.nodeValue;
            $.ajax({
                url: "changeOrders.ct",
                type: "POST",
                data: {
                    currentId: $currentId,
                    changeId: $changeId,
                    btnType: "up"
                },
                dataType: "json",
                cache: false,
                async: false,
                success: function(message) {
                    if (message.type == "success") {
                        $tr.prev().before($tr);
                    }
                }
            });
        }
    });

    //下移
    var $downbtns = $(".downIcon");
    $downbtns.click(function() {
        var $tr = $(this).parents("tr");
        
        var len =  $(this).parents("table").find("tr").length-1;
        if ($tr.index() == len) {
            $.message("warn", "${message("console.appPoster.bottom")}");
            return false;
        } else {
            var $currentId = this.attributes.posterOrder.nodeValue;
            var $changeId = $tr.next().find("td:eq(7)").find("a:eq(0)")[0].attributes.posterOrder.nodeValue;
            $.ajax({
                url: "changeOrders.ct",
                type: "POST",
                data: {
                    currentId: $currentId,
                    changeId: $changeId,
                    btnType: "down",
                },
                dataType: "json",
                cache: false,
                async: false,
                success: function(message) {
                    if (message.type == "success") {
                        $tr.next().after($tr);
                    }
                }
            });
        }
    });
    
	});
	function changeTab(isOnline){
		var $isOnline = $("#isOnline")
		if(isOnline == "unOnlineTab"){
			$isOnline.val("false");
		}
		if(isOnline == "onlineTab"){
			$isOnline.val("true");
		}
		$("#listForm").submit();
	}
	
	function checkSearchForm(){
	   $("#listForm").submit();
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
                    <h2>${message("console.poster.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("console.poster.list")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
            	<form id="listForm" action="list.ct" method="get">
                	<div class="row">
						<div class="col-lg-12">
                        	<div class="ibox float-e-margins">
                            	<div class="ibox-content">
	                                <!-- start 查询条件区 -->
	                              	<div class="padding">  
	                                	<div class="row">
	                                    	<div class="btn-group">
		                                      	[@shiro.hasPermission name = "console:addApp"]
				                                <a href="add.ct" class="btn btn-primary">
													<span class="addIcon">&nbsp;</span>${message("console.common.add")}
												</a>
												[/@shiro.hasPermission]
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
			                            	</div>
			                            	${message("console.appPoster.operatingSystem")}
                                            <select name="operatingSystem" class="btn-white dropdown-toggle" style="height: 30px;width: 80px;margin-right:9px;">
                                            	<option value="android" [#if operatingSystem=="android" ] selected="selected"[/#if]>Android</option>
                                            	<option value="ios" [#if operatingSystem=="ios" ] selected="selected"[/#if]>IOS</option>
                                            </select>
		                                    <input type="text" name="posterName" id="posterName" value= "${posterName}"  class="laydate-icon form-control layer-date" placeholder="${message("搜索海报名称")}" style="width:160px;display: initial;"/>
		                                    <input id="submitButton" onclick="checkSearchForm()" type="button" value="${message("console.attendance.status.search")}" class="btn btn-primary" />
	                                 	</div>
							       </div>
							       <!-- end 查询条件区 -->
                                   <!-- start tab区域 -->
							       <div class="row border-bottom white-bg-bak dashboard-header mainContent" style="border-top: 0px;">
						           		<input type="hidden" id ="isOnline" name="isOnline" value="${isOnline}"/>
						           		<div class="panel blank-panel">
				                            <div class="panel-heading">
				                                <div class="panel-options">
				                                    <ul class="nav nav-tabs">
				                                    	<li [#if isOnline]class="active"[/#if]>
				                                            <a data-toggle="tab"  href="javascript:;" onclick="changeTab('onlineTab')">
				                                                ${message("已上架海报")}
				                                            </a>
				                                        </li>
				                                        <li [#if !isOnline]class="active"[/#if]>
				                                            <a data-toggle="tab"  href="javascript:;" onclick="changeTab('unOnlineTab')">
				                                                ${message("未上架海报")}
				                                            </a>
				                                        </li>
				                                    </ul>
				                                </div>
				                            </div>
				                            <div class="panel-body">
				                                <div class="tab-content">
				                                    <div id="onlineTab" class="tab-pane active">
				                                        <table class="table table-striped">
														<tr>
															<th class="check">
																<input type="checkbox" id="selectAll" />
															</th>
															<th>
																<a href="javascript:;" name="stateSort">${message("console.appPoster.state")}</a>
															</th>
															<th>
																<a href="javascript:;" name="titleSort">${message("console.appPoster.title")}</a>
															</th>
															<th style="width:15%">
																<a href="javascript:;" name="posterImgSort">${message("console.appPoster")}</a>
															</th>
															<th [#if isOnline=="true"] style="display:true;width:15%;" [#else] style="display:none;width:15%;" [/#if]>
																<a href="javascript:;" class="isOnlineDate" name="onLineSort">${message("console.appPoster.onlineDate")}</a>
															</th>
															<th>
																<a href="javascript:;" name="operatingSystemort">${message("console.app.terminal")}</a>
															</th>
															<th>
																<span>${message("console.common.handle")}</span>
															</th>
															<th>
																<span>${message("console.common.order")}</span>
															</th>
													  </tr>
													  [#list page.content as appPoster]
														<tr>
															<td>
																<input type="checkbox" name="ids" value="${appPoster.id}" />
															</td>
															<td>
																[#if appPoster.isOnline=="true"]
																	${message("console.appPoster.shelves")}
																[#else]
																	${message("console.appPoster.poShelves")}
																[/#if]
															</td>
															<td>
																${appPoster.posterName}
															</td>
															<td>
																<img src="${appPoster.posterImg}" style="width: 160px;"/>
															</td>
															<td [#if isOnline=="true"] style="display:block" [#else] style="display:none" [/#if]>
															    ${(appPoster.onlineDate?string("yyyy-MM-dd HH:mm"))!}
															</td>
															<td>
																${appPoster.operatingSystem}
															</td>
															<td>
															[#if isOnline=="true"]
															    <a href="onLine.ct?id=${appPoster.id}">${message("console.common.offShelves")}</a><br/>
															[#else]
															    <a href="onLine.ct?id=${appPoster.id}">${message("console.common.online")}</a><br/>
															[/#if]
															[@shiro.hasPermission name = "console:editAppPoster"]
																<a href="edit.ct?id=${appPoster.id}">${message("console.common.edit")}</a>
															[/@shiro.hasPermission]
															</td>
															<td>
																<a class="upIcon" posterOrder="${appPoster.id}">${message("console.common.shiftUp")}</a><br/>
																<a class="downIcon" posterOrder="${appPoster.id}">${message("console.common.shiftDown")}</a><br/>
															</td>
														</tr>
													[/#list]
												      </table>
				                                    </div>
				                                    <div id="UonlineTab" class="tab-pane" style=" visibility: hidden;  height: 0px;">
				                                     <table class="table table-striped">
														<tr>
															<th class="check">
																<input type="checkbox" id="selectAll" />
															</th>
															<th>
																<a href="javascript:;" name="stateSort">${message("console.appPoster.state")}</a>
															</th>
															<th>
																<a href="javascript:;" name="titleSort">${message("console.appPoster.title")}</a>
															</th>
															<th style="width:15%">
																<a href="javascript:;" name="posterImgSort">${message("console.appPoster")}</a>
															</th>
															<th [#if isOnline=="true"] style="display:true;width:15%;" [#else] style="display:none;width:15%;" [/#if]>
																<a href="javascript:;" class="isOnlineDate" name="onLineSort">${message("console.appPoster.onlineDate")}</a>
															</th>
															<th>
																<a href="javascript:;" name="operatingSystemort">${message("console.app.terminal")}</a>
															</th>
															<th>
																<span>${message("console.common.handle")}</span>
															</th>
															<th>
																<span>${message("console.common.order")}</span>
															</th>
													      </tr>
														[#list page.content as appPoster]
														<tr>
															<td>
																<input type="checkbox" name="ids" value="${appPoster.id}" />
															</td>
															<td>
																[#if appPoster.isOnline=="true"]
																	${message("console.appPoster.shelves")}
																[#else]
																	${message("console.appPoster.poShelves")}
																[/#if]
															</td>
															<td>
																${appPoster.posterName}
															</td>
															<td>
																<img src="${appPoster.posterImg}" style="width: 160px;"/>
															</td>
															<td [#if isOnline=="true"] style="display:block" [#else] style="display:none" [/#if]>
															    ${(appPoster.onlineDate?string("yyyy-MM-dd HH:mm"))!}
															</td>
															<td>
																${appPoster.operatingSystem}
															</td>
															<td>
															[#if isOnline=="true"]
															    <a href="onLine.ct?id=${appPoster.id}">${message("console.common.offShelves")}</a><br/>
															[#else]
															    <a href="onLine.ct?id=${appPoster.id}">${message("console.common.online")}</a><br/>
															[/#if]
															[@shiro.hasPermission name = "console:editAppPoster"]
																<a href="edit.ct?id=${appPoster.id}">${message("console.common.edit")}</a>
															[/@shiro.hasPermission]
															</td>
															<td>
																<a class="upIcon" posterOrder="${appPoster.id}">${message("console.common.shiftUp")}</a><br/>
																<a class="downIcon" posterOrder="${appPoster.id}">${message("console.common.shiftDown")}</a><br/>
															</td>
														</tr>
													 [/#list]
												   </table>
				                                  </div>
				                                </div>
				                            </div>
				                        </div>
				                        [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
										   [#include "/console/include/pagination.ftl"]
								        [/@pagination]
	                              </div>
	                               <!-- end tab区域 -->
                              </div>
                           </div>
	                    </div>
	                </div>
	             </form>
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>