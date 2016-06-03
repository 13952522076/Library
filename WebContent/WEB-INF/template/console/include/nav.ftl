[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<!--  导航 -->
<script>
	function syncBookInfo(){
		layer.msg('同步系统统计中', {icon: 16,shade:true});
		$.ajax({
			type: "GET",
			url: "${base}/console/book/sync.ct",
			dataType: "json",
			success:function(){
				layer.msg("系统统计信息更新成功！");
			}
		})
				
	}
	
	
	
</script>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	      	</button>
	      	<a class="navbar-brand" href="${base}/console/common/main.ct">图书智能管理推荐系统</a>
		</div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      	<ul class="nav navbar-nav">
	      		<!-- 个人管理 -->
		        <li class="dropdown">
		          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			          	个人管理
			          	<span class="caret"></span>
					</a>
		          	<ul class="dropdown-menu">
			            <li><a href="${base}/console/admin/info.ct?">个人信息</a></li>
		          	</ul>
	        	</li>
		        
		        <!-- 智能推荐 -->
		        <li class="dropdown">
		          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			          	智能推荐 
			          	<span class="caret"></span>
					</a>
		          	<ul class="dropdown-menu">
			            <li><a href="${base}/console/recommend/hot.ct">最热书籍</a></li><!-- top250 -->
			            <li><a href="${base}/console/recommend/similar.ct">相似同学</a></li><!-- 协同过滤 -->
			            <li><a href="#${base}/console/recommend/history.ct">历史推荐</a></li><!-- 关联规则 -->
		          	</ul>
	        	</li>
	        	
		        <!-- 图书管理 -->
		        <li class="dropdown">
		          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			          	图书管理 
			          	<span class="caret"></span>
					</a>
		          	<ul class="dropdown-menu">
			            <li><a href="${base}/console/book/list.ct?">图书管理</a></li>
			            <li><a href="${base}/console/book/add.ct">新增图书</a></li>
		             	<li><a href="#" onclick="syncBookInfo()">同步统计</a></li>
		          	</ul>
	        	</li>
	      	</ul>
	      	
	      	
	      	<form class="navbar-form navbar-left" role="search"  action="${base}/console/book/search.ct" method="post">
				<div class="form-group">
	          		<input type="text" value="${bookKeyword}" name="bookKeyword" class="form-control" placeholder="查找书籍">
	        	</div>
	        	<button type="submit" class="btn btn-default">搜索</button>
	      	</form>
	      	<ul class="nav navbar-nav navbar-right">
	      		<li><p class="navbar-text">用户名</p></li>
	        	<li><a href="${base}/console/logout.jsp">退出</a></li>
	      	</ul>
		</div>
  	</div>
</nav>
<!--导航结束-->

