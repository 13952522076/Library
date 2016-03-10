[#if setting.isCnzzEnabled && setting.cnzzSiteId?has_content && setting.cnzzPassword?has_content]
	<span style="display: none;">
		<script type="text/javascript" src="http://pw.cnzz.com/c.php?id=${setting.cnzzSiteId}&l=2" charset="gb2312"></script>
		
		<!-- 百度统计 -->
		<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?ae32e1f9670d775f1cc4d59aee24fc0b";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
		</script>
		
		<!--Google 统计-->
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-45366647-2', 'auto');
		  ga('send', 'pageview');
		
		</script>
		
	</span>
[/#if]