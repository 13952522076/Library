<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.navigation.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.navigation.list")}
	</div>
	<form id="listForm" action="list.ct" method="get">
		<div class="bar">
			<a href="add.ct" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("console.common.add")}
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>${message("Navigation.name")}</span>
				</th>
				<th>
					<span>${message("Navigation.position")}</span>
				</th>
				<th>
					<span>${message("Navigation.isBlankTarget")}</span>
				</th>
				<th>
					<span>${message("console.common.order")}</span>
				</th>
				<th>
					<span>${message("console.common.handle")}</span>
				</th>
			</tr>
			[#list topNavigations as navigation]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
					</td>
					<td>
						${navigation.name}
					</td>
					<td>
						${message("Navigation.Position." + navigation.position)}
					</td>
					<td>
						${message(navigation.isBlankTarget?string('console.common.true', 'console.common.false'))}
					</td>
					<td>
						${navigation.order}
					</td>
					<td>
						<a href="edit.ct?id=${navigation.id}">[${message("console.common.edit")}]</a>
					</td>
				</tr>
			[/#list]
			[#if topNavigations?has_content]
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
			[/#if]
			[#list middleNavigations as navigation]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
					</td>
					<td>
						${navigation.name}
					</td>
					<td>
						${message("Navigation.Position." + navigation.position)}
					</td>
					<td>
						${message(navigation.isBlankTarget?string('console.common.true', 'console.common.false'))}
					</td>
					<td>
						${navigation.order}
					</td>
					<td>
						<a href="edit.ct?id=${navigation.id}">[${message("console.common.edit")}]</a>
					</td>
				</tr>
			[/#list]
			[#if middleNavigations?has_content]
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
			[/#if]
			[#list bottomNavigations as navigation]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
					</td>
					<td>
						${navigation.name}
					</td>
					<td>
						${message("Navigation.Position." + navigation.position)}
					</td>
					<td>
						${message(navigation.isBlankTarget?string('console.common.true', 'console.common.false'))}
					</td>
					<td>
						${navigation.order}
					</td>
					<td>
						<a href="edit.ct?id=${navigation.id}">[${message("console.common.edit")}]</a>
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>