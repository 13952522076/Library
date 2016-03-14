<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.admin.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resource.ftl" /]
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
});

function editName(){
	var id = $("#adminId").val();
	var name = $("#adminName").val();
	var email = $("#adminEmail").val();
	if(name == null){
		$.message("warn", "姓名不可为空!");
		return false;
	}
	$.ajax({
		type: "GET",
		url: "editInfo.ct",
		data: {
		    id:id,
		    name:name,
		    email:email
		},
		dataType: "json",
		success:function(data){
			if(data=="success"){
				$.message("success", "修改成功");
			}
			else{
				return false;
			}
		}
	});
	
}
</script>
<style type="text/css">
	.ibox-content span{
		 cursor:pointer;
	}
	.info-input{
	    border: none;
	    font-size: 23px;
	    color: gray;
	    overflow: hidden;
	    overflow-y: hidden;
	    width: 100%;
	}
</style>
</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
	 <div class="wrapper wrapper-content">
	 	
	 
	 	<input type="hidden" id="adminId" value="${admin.id}">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<div><input id="adminName" class="info-input" value="${admin.name}" required/></div>
                        <span onclick="editName()" class="label label-success pull-right"><i class="fa fa-floppy-o"></i>保存编辑</span>
                        <small>姓名</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<div><a class="info-input">${admin.username}</a></div>
                        <small>用户名</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                     <div class="ibox-content">
                    	<div><input id="adminEmail" class="info-input" value="${admin.email}" required/></div>
                        <span class="label label-success pull-right"><i class="fa fa-floppy-o"></i>保存编辑</span>
                        <small>邮箱</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                     <div class="ibox-content">
                    	<div><a class="info-input">${admin.loginDate}</a></div>
                        <small>登录时间</small>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>订单</h5>
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-xs btn-white active">天</button>
                                <button type="button" class="btn btn-xs btn-white">月</button>
                                <button type="button" class="btn btn-xs btn-white">年</button>
                            </div>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="flot-chart">
                                    <div class="flot-chart-content" id="flot-dashboard-chart"></div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <ul class="stat-list">
                                    <li>
                                        <h2 class="no-margins">2,346</h2>
                                        <small>订单总数</small>
                                        <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 48%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">4,422</h2>
                                        <small>最近一个月订单</small>
                                        <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 60%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">9,180</h2>
                                        <small>最近一个月销售额</small>
                                        <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i>
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 22%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>消息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content ibox-heading">
                        <h3><i class="fa fa-envelope-o"></i> 新消息</h3>
                        <small><i class="fa fa-tim"></i> 您有22条未读消息</small>
                    </div>
                    <div class="ibox-content">
                        <div class="feed-activity-list">
                            <div class="feed-element">
                                <div>
                                    <small class="pull-right">5月前</small>
                                    <strong>DMG电影 </strong>
                                    <div>《和外国男票乘地铁，被中国大妈骂不要脸》妹子实在委屈到不行，中国妹子找外国男友很令人不能接受吗？大家都来说说自己的看法</div>
                                    <small class="text-muted">11月8日 20:08 </small>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-8">

                <div class="row">
                    <div class="col-sm-6">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>用户项目列表</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                    <a class="close-link">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <table class="table table-hover no-margins">
                                    <thead>
                                        <tr>
                                            <th>状态</th>
                                            <th>日期</th>
                                            <th>用户</th>
                                            <th>值</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><small>进行中...</small>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 11:20</td>
                                            <td>青衣5858</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 24%</td>
                                        </tr>
                                        <tr>
                                            <td><span class="label label-warning">已取消</span>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 10:40</td>
                                            <td>徐子崴</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 66%</td>
                                        </tr>
                                        <tr>
                                            <td><small>进行中...</small>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 01:30</td>
                                            <td>姜岚昕</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 54%</td>
                                        </tr>
                                        <tr>
                                            <td><small>进行中...</small>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 02:20</td>
                                            <td>武汉大兵哥</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 12%</td>
                                        </tr>
                                        <tr>
                                            <td><small>进行中...</small>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 09:40</td>
                                            <td>荆莹儿</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 22%</td>
                                        </tr>
                                        <tr>
                                            <td><span class="label label-primary">已完成</span>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 04:10</td>
                                            <td>栾某某</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 66%</td>
                                        </tr>
                                        <tr>
                                            <td><small>进行中...</small>
                                            </td>
                                            <td><i class="fa fa-clock-o"></i> 12:08</td>
                                            <td>范范范二妮</td>
                                            <td class="text-navy"> <i class="fa fa-level-up"></i> 23%</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                </div>
               
            </div>
        </div>
    </div>
</div>
</body>
</html>