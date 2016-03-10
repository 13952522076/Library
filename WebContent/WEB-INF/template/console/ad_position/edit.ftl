<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.adPosition.edit")} - 小书僮™智慧幼教管理平台</title>
<meta name="Author" content="福州盛云软件技术有限公司 Team" />
<meta name="Copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]

	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			width: {
				required: true,
				min: 1
			},
			height: {
				required: true,
				min: 1
			},
			template: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.adPosition.edit")}
	</div>
	<form id="inputForm" action="update.ct" method="post">
		<input type="hidden" name="id" value="${adPosition.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("AdPosition.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" value="${adPosition.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("AdPosition.width")}:
				</th>
				<td>
					<input type="text" name="width" class="text" value="${adPosition.width}" maxlength="9" title="${message("console.adPosition.widthTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("AdPosition.height")}:
				</th>
				<td>
					<input type="text" name="height" class="text" value="${adPosition.height}" maxlength="9" title="${message("console.adPosition.heightTitle")}" />
				</td>
			</tr>
			<tr>
				<th>
					${message("AdPosition.description")}:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${adPosition.description}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("AdPosition.template")}:
				</th>
				<td>
					<textarea name="template" class="text" style="width: 98%; height: 300px;">${adPosition.template?html}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("console.common.submit")}" />
					<input type="button" class="button" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>