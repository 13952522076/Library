[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.template.list")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $type = $("#type");
	var $typeSelect = $("#typeSelect");
	var $typeOption = $("#typeOption a");

	[@flash_message /]
	
	$typeSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$typeOption.click(function() {
		var $this = $(this);
		$type.val($this.attr("val"));
		$listForm.submit();
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.template.list")}
	</div>
	<form id="listForm" action="list.ct" method="get">
		<input type="hidden" id="type" name="type" value="${type}" />
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="typeSelect" class="button">
						${message("Template.type")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="typeOption">
							<li>
								<a href="javascript:;"[#if !type??] class="current"[/#if] val="">${message("console.template.allType")}</a>
							</li>
							[#assign currentType = type]
							[#list types as type]
								<li>
									<a href="javascript:;"[#if type == currentType] class="current"[/#if] val="${type}">${message("Template.Type." + type)}</a>
								</li>
							[/#list]
						</ul>
					</div>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>${message("Template.name")}</span>
				</th>
				<th>
					<span>${message("Template.type")}</span>
				</th>
				<th>
					<span>${message("Template.templatePath")}</span>
				</th>
				<th>
					<span>${message("Template.description")}</span>
				</th>
				<th>
					<span>${message("console.common.handle")}</span>
				</th>
			</tr>
			[#list templates as template]
				<tr>
					<td>
						${template.name}
					</td>
					<td>
						${message("Template.Type." + template.type)}
					</td>
					<td>
						${template.templatePath}
					</td>
					<td>
						[#if template.description??]
							<span title="${template.description}">${abbreviate(template.description, 50, "...")}</span>
						[/#if]
					</td>
					<td>
					[@shiro.hasPermission name = "console:order_a_edit"]
						<a href="edit.ct?id=${template.id}">[${message("console.common.edit")}]</a>
					[/@shiro.hasPermission]
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>