<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.cache.clear")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.cache.clear")}
	</div>
	<form id="inputForm" action="clear.ct" method="post">
		<table class="input">
			<tr>
				<th>
					${message("console.cache.cacheSize")}:
				</th>
				<td>
					${cacheSize}
				</td>
			</tr>
			<tr>
				<th>
					${message("console.cache.freeMemory")}:
				</th>
				<td>
					[#if maxMemory?? && totalMemory?? && freeMemory??]
						${(maxMemory - totalMemory + freeMemory)?string("0.##")}MB
					[#else]
						-
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("console.cache.diskStorePath")}:
				</th>
				<td>
					${diskStorePath}
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("console.common.submit")}" />
					<input type="button" class="button" value="${message("console.common.back")}" onclick="location.href='../common/index.ct'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>