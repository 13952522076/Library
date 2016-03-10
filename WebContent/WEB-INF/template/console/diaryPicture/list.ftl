<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("成长记图片列表")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<link rel="stylesheet" href="${base}/resources/console/css/animate.css" >
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});

function showBigImge(url){
	var content= 
	'<div>'+
	'<img src="'+url+'"  width="300" />'+
	'</div>';
	layer.open({
	    type: 1,
	    title: false,
	    area: '300px',
	    shift:1,
	    skin: 'layui-layer-demo', //样式类名
	    closeBtn: true, //不显示关闭按钮
	    shift: 2,
	    shadeClose: true, //开启遮罩关闭
	    content: content
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
                    <h2>${message("成长记图片列表")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           	<strong>${message("成长记图片列表")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
            		</ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       	<!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start  成长记列表 -->
	             <form id="listForm" action="list.ct" method="get">
	             <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-12 m-b-xs">
                                    	<div class="btn-group">
	                                        [@shiro.hasPermission name = "console:deleteDiaryPicture"]
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
										</div>
										<div class="input-group pull-right">
											<form action="list.ct" method="get">
												<input type="text" value="${returnStartDate}" name="startDate" id="startDate" 
                                                	class="laydate-icon form-control layer-date" style="width:120px;margin-top:12px;height:30px;"
                                                	placeholder="开始时间" />
                                                <input type="text" value="${returnEndDate}" name="endDate" id="endDate" 
                                                	class="laydate-icon form-control layer-date" style="width:120px;margin-top:12px;height:30px;"
                                                	placeholder="结束时间"/>
												<input type="text" name="realName"  class="input-sm form-control" style="width:100px;margin-top: 12px;" placeholder="搜索发表人">
												<input type="submit" class="btn btn-primary" value="搜索"/>
											</form>
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
														${message("发表人")}
													</th>
													<th>
														${message("图片缩略图")}
													</th>
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("时间")}</a>
													</th>
													
													<th>
														<a href="javascript:;" class="sort" name="growthDiary">${message("隶属成长记ID")}</a>
													</th>
													<th>
														<span>${message("详情")}</span>
													</th>
												</tr>
												[#list page.content as diaryPicture]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${diaryPicture.id}" />
														</td>
														<td>
															${diaryPicture.growthDiary.member.realName}
														</td>
														<td>
															<a href="javscript:;" onclick='showBigImge("${diaryPicture.pictureUrl}")'>
																<img class="img_plus" src="${diaryPicture.pictureUrl}" height="50" width="50" />
															</a>
														</td>
														<td>
															${diaryPicture.pictureTime?string("yyyy-MM-dd HH:mm:ss")}
														</td>
														<td>
															${diaryPicture.growthDiary.id}
														</td>
														<td>
														[@shiro.hasPermission name = "console:addDictClass"]
															<a href="${diaryPicture.pictureUrl}" target="_black">[${message("新页面查看原图")}]</a>
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
	             <!-- end 成长记列表 -->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
<script>
    $(document).ready(function () {
        $('.img_plus').each(function () {
            animationHover(this, 'pulse');
        });
    });
</script>
<!-- start layerDate plugin javascript -->
<!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
<script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js">
</script>
<script>
    //日期范围限制
    var start = {
        elem: '#startDate',
        format: 'YYYY-MM-DD',
        min: '1900-01-01', //最小日期
        //设定最小日期为当前日期
        max: '2099-06-16',
        //最大日期
        istime: true,
        istoday: false,
        choose: function(datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endDate',
        format: 'YYYY-MM-DD',
        min: '1900-01-01', //最小日期
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
<!-- end layerDate plugin javascript -->
</body>
</html>