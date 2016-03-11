[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!--  导航 -->
<script type="text/javascript">
	function changeNavHeight(){
		var height = $("#page-wrapper").height();
		var navHeight = $("#leftSideNavbar").height();
		//alert(height);
		if(height!=null&&navHeight!=null){
			if(height>navHeight){
				$("#leftSideNavbar").height(height);
			}
		}
	}
	$().ready(function() {
		changeNavHeight();
	});	
	$(window).resize(function(){
	  	changeNavHeight();
 	});
 	window.onload = function (){ 
 		changeNavHeight();
 	}
</script>
<nav id="leftSideNavbar" class="navbar-default navbar-static-side navbar-background" role="navigation" style="overflow-y: visible;">
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header" style="width:200px;">
                <div class="dropdown profile-element" style="margin-left: 20px;"> 
                	<span>
                    	<img alt="image" class="img-circle" src="
                    	[#if Session["iconPhoto"]??]
                    	${Session['iconPhoto']}
                    	[#else]
                    	${base}/resources/console/images/userAvatar.jpg
                    	[/#if]
                    	" width="70px" height="70px"/>
                 	</span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                        <span class="clear"> 
                            <span class="block m-t-xs"> 
                                <strong class="font-bold"></strong>
                            </span>  
                            <span class="text-muted text-xs block">
                            	${Session["schoolName"]} <br>  
								${Session["realName"]}  
                                <b class="caret"></b>
                            </span>
                        </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li>
                            <a href="${base}/console/profile/edit.ct" >${message("console.main.personalData")}</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${base}/console/logout.jsp">[${message("console.main.logout")}]</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">
                    ${message("console.common.title")}
                </div>
            </li>
           	<!--  _____ 模块化导航 start_____-->
           	<!--个人信息管理 start-->
             <li name="menu">
                <a href="index.html">
                   	<i class="fa fa-user"></i> 
                   	<span class="nav-label">${message("个人管理")}</span> 
                   	<span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level"> 
					<li id="menu.Admin">
						<a href="${base}/console/admin/info.ct?" >个人信息</a>
					</li>
                </ul>
            </li>
            <!--个人管理 end -->
            <!--图书管理 start-->
             <li name="menu">
                <a href="index.html">
	               	<i class="fa fa-university"></i> 
	               	<span class="nav-label">${message("图书管理")}</span> 
	               	<span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level"> 
					<li id="menu.BookList">
						<a href="${base}/console/admin/list.ct" >图书列表</a>
					</li>
					<li id="menu.BookAdd">
						<a href="${base}/console/admin/list.ct" >图书新增</a>
					</li>
                </ul>
            </li>
            <!--图书管理 end -->
           	<!--  _____ 模块化导航 end  _____-->
            
            
            
            
            
           
           
           
           
            <li><div class="for-nav-bottom-rainbow"></div></li>
        </ul>
    </div>
</nav>

