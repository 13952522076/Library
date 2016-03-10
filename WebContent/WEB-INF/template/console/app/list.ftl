<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("应用管理列表")} -图书智能管理推荐系统</title>
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
                    <h2>${message("应用管理列表")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("应用管理列表")} <span>(${message("console.page.total", page.total)})</span></strong>
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
		                                    <select id="search_class" name="appCategoryId" class="btn-white dropdown-toggle" style="  height: 30px;width: 100px;">
	                                        	<option value="">
		                                            ${message("全部分类")}
		                                        </option>
		                                        [#list appCategories as appCategory]
		                                          <option value= ${appCategory.id} [#if "${appCategory.id}" == appCategoryId] selected="selected" [/#if]>${appCategory.name}</option>
		                                        [/#list]
		                                    </select>
		                                    <select name="runType" class="btn-white dropdown-toggle" style="  height: 30px;width: 100px;">
		                                        <option value="">${message("全部类型")}</option>
		                                        <option value="build_in" [#if runType=="build_in"] selected="selected"[/#if]>${message("内嵌应用")}</option>
		                                        <option value="thirdparty" [#if runType=="thirdparty" ] selected="selected" [/#if]>${message("第三方应用")}</option>
		                                    </select>
		                                    <select name="operatingSystem" class="btn-white dropdown-toggle" style="  height: 30px;width: 100px;">
		                                        <option value="">${message("全部系统")}</option>
		                                        <option value="android" [#if operatingSystem=="android"] selected="selected"[/#if]>${message("android")}</option>
		                                        <option value="ios" [#if operatingSystem=="ios"] selected="selected" [/#if]>${message("ios")}</option>
		                                        <option value="hybird" [#if operatingSystem=="hybird"] selected="selected"[/#if]>${message("hybird")}</option>
		                                        <option value="hbuilder" [#if operatingSystem=="hbuilder"] selected="selected" [/#if]>${message("hbuilder")}</option>
		                                    </select>
		                                    <input type="text" name="appName" id="appName" value= "${appName}"  class="laydate-icon form-control layer-date" placeholder="${message("搜索应用名称")}" style="width:160px;display: initial;"/>
		                                    <input id="submitButton" onclick="checkSearchForm()" type="button" value="${message("console.attendance.status.search")}" class="btn btn-primary" />
	                                 	</div>
							       </div>
							       <!-- end 查询条件区 -->
                                   <!-- start tab区域 -->
							       <div class="row border-bottom white-bg-bak dashboard-header mainContent" style="border-top: 0px;">
						           		<input type="hidden" id ="isOnline" name="isOnline" value="${isOnline?string('true','false')}"/>
						           		<div class="panel blank-panel">
				                            <div class="panel-heading">
				                                <div class="panel-options">
				                                    <ul class="nav nav-tabs">
				                                    	<li [#if isOnline]class="active"[/#if]>
				                                            <a data-toggle="tab"  href="javascript:;" onclick="changeTab('onlineTab')">
				                                                ${message("已上架应用")}
				                                            </a>
				                                        </li>
				                                        <li [#if !isOnline]class="active"[/#if]>
				                                            <a data-toggle="tab"  href="javascript:;" onclick="changeTab('unOnlineTab')">
				                                                ${message("未上架应用")}
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
															<th>
																<a href="javascript:;"  name="dictSchool">${message("应用介绍")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="code">${message("系统类型")}</a>
															</th>
															<th>
																<a href="javascript:;" name="name">${message("当前状态")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="cmaster">${message("时间")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="classStatus">${message("来源")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="description">${message("用户数据")}</a>
															</th>
															<th>
																<span>${message("console.common.handle")}</span>
															</th>
													  </tr>
													  [#list page.content as app]
														<tr>
															<td>
															  <table>
															     <tr> 
															       <td>
															        <img src="${app.logoAppImg}" alt="${app.logoAppImg}" style="width:60px;height:60px;margin-right: 10px;">
															       </td>
															       <td>
															          ${app.appName}
																      <p></p>
																     ${app.appSize}|版本号：${app.versionName}|版本：${app.versionCode}
																     <p></p>
																     评价:[#if (app.appReviews?size)>0 ]${app.appReviews?size}条[#else]暂无评论[/#if]
															       </td>
															     </tr>
															  </table>
															</td>
															<td style="vertical-align: middle;">
																${app.operatingSystem}
															</td>
															<td style="vertical-align: middle;">
															    [#if app.isOnline == 'true']已上架[#else]未上架[/#if]
																
															</td>
															<td style="vertical-align: middle;">
															    <span title="${app.modifyDate?string("yyyy-MM-dd HH:mm:ss")}">最近更新：${app.modifyDate}</span>
																</br>
																<span title="${app.createDate?string("yyyy-MM-dd HH:mm:ss")}">创建时间：${app.createDate}</span>
															</td>
															<td style="vertical-align: middle;">
																${app.developer}
															</td>
															<td style="vertical-align: middle;">
																${app.appUsers?size}
															</td>
															
															<td style="vertical-align: middle;">
															[@shiro.hasPermission name = "console:editApp"]
																<a href="edit.ct?id=${app.id}">[${message("管理应用")}]</a>
															[/@shiro.hasPermission]
															</td>
														</tr>
														[/#list]
												      </table>
				                                    </div>
				                                    <div id="UonlineTab" class="tab-pane" style=" visibility: hidden;  height: 0px;">
				                                     <table class="table table-striped">
														<tr>
															<th>
																<a href="javascript:;"  name="dictSchool">${message("应用介绍")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="code">${message("系统类型")}</a>
															</th>
															<th>
																<a href="javascript:;" name="name">${message("当前状态")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="cmaster">${message("时间")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="classStatus">${message("来源")}</a>
															</th>
															<th>
																<a href="javascript:;"  name="description">${message("用户数据")}</a>
															</th>
															<th>
																<span>${message("console.common.handle")}</span>
															</th>
													      </tr>
														  [#list uonlineApps as app]
															<tr>
																<td>
																    <table>
																     <tr> 
																       <td>
																         <img src="${app.logoAppImg}" alt="${app.logoAppImg}" style="width:60px;height:60px;margin-right: 10px;">
																       </td>
																       <td>
																          ${app.appName}
																          <p></p>
																	      ${app.appSize}|版本号：${app.versionName}|版本：${app.versionCode}
																	      <p></p>
																	      评价:[#if (app.appReviews?size)>0 ]${app.appReviews?size}条[#else]暂无评论[/#if]
																       </td>
																     </tr>
																  </table>
																</td>
																<td style="vertical-align: middle;">
																	${app.operatingSystem}
																</td>
																<td style="vertical-align: middle;">
																    [#if app.isOnline == 'true']已上架[#else]未上架[/#if]
																	
																</td>
																<td style="vertical-align: middle;">
																    <span title="${app.modifyDate?string("yyyy-MM-dd HH:mm:ss")}">最近更新：${app.modifyDate}</span>
																	</br>
																	<span title="${app.createDate?string("yyyy-MM-dd HH:mm:ss")}">创建时间：${app.createDate}</span>
																</td>
																<td style="vertical-align: middle;">
																	${app.developer}
																</td>
																<td style="vertical-align: middle;">
																	${app.appUsers?size}
																</td>
																
																<td style="vertical-align: middle;">
																[@shiro.hasPermission name = "console:editApp"]
																	<a href="edit.ct?id=${app.id}">[${message("管理应用")}]</a>
																[/@shiro.hasPermission]
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