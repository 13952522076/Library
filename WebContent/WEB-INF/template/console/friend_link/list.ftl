<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.friendLink.list")} -图书智能管理推荐系统</title>
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
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.friendLink.list")} <span>(${message("console.page.total", page.total)})</span>
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
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						${message("console.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
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
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;"[#if page.searchProperty == "name"] class="current"[/#if] val="name">${message("FriendLink.name")}</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">${message("FriendLink.name")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="logo">${message("FriendLink.logo")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="url">${message("FriendLink.url")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">${message("console.common.order")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("console.common.createDate")}</a>
				</th>
				<th>
					<span>${message("console.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as friendLink]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${friendLink.id}" />
					</td>
					<td>
						${friendLink.name}
					</td>
					<td>
						[#if friendLink.type == "image"]
							<a href="${friendLink.logo}" target="_blank">${message("console.common.view")}</a>
						[#else]
							-
						[/#if]
					</td>
					<td>
						<a href="${friendLink.url}" target="_blank">${friendLink.url}</a>
					</td>
					<td>
						${friendLink.order}
					</td>
					<td>
						<span title="${friendLink.createDate?string("yyyy-MM-dd HH:mm:ss")}">${friendLink.createDate}</span>
					</td>
					<td>
						<a href="edit.ct?id=${friendLink.id}">[${message("console.common.edit")}]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/console/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>