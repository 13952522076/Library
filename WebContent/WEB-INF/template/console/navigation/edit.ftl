<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.navigation.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $systemUrl = $("#systemUrl");
	var $url = $("#url");
	
	[@flash_message /]

	// 将选择的系统内容地址填充至链接地址中
	$systemUrl.change(function() {
		$url.val($systemUrl.val());
	});
	
	// 链接地址内容修改时,系统内容选择框修改为不选择任何项目
	$url.keypress(function() {
		$systemUrl.val("");
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			url: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.navigation.edit")}
	</div>
	<form id="inputForm" action="update.ct" method="post">
		<input type="hidden" name="id" value="${navigation.id}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Navigation.name")}:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" value="${navigation.name}" maxlength="200" />	 
				</td>
			</tr>
			<tr>
				<th>
					${message("console.navigation.systemUrl")}:
				</th>
				<td>
					<select id="systemUrl">
						<option value="">------------</option>
						<option value="${base}/"[#if navigation.url == base + "/"] selected="selected"[/#if]>${message("console.navigation.home")}</option>
						<option value="${base}/product_category.ct"[#if navigation.url == base + "/product_category.ct"] selected="selected"[/#if]>${message("console.navigation.productCategory")}</option>
						<option value="${base}/friend_link.ct"[#if navigation.url == base + "/friend_link.ct"] selected="selected"[/#if]>${message("console.navigation.friendLink")}</option>
						<option value="${base}/member/index.ct"[#if navigation.url == base + "/member/index.ct"] selected="selected"[/#if]>${message("console.navigation.member")}</option>
						[#list articleCategoryTree as articleCategory]
							<option value="${base}${articleCategory.path}"[#if base + articleCategory.path == navigation.url] selected="selected"[/#if]>
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${articleCategory.name}
							</option>
						[/#list]
						[#list productCategoryTree as productCategory]
							<option value="${base}${productCategory.path}"[#if base + productCategory.path == navigation.url] selected="selected"[/#if]>
								[#if productCategory.grade != 0]
									[#list 1..productCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${productCategory.nameZh}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Navigation.url")}:
				</th>
				<td>
					<input type="text" id="url" name="url" class="text" value="${navigation.url}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Navigation.position")}:
				</th>
				<td>
					<select name="position">
						[#list positions as position]
							<option value="${position}"[#if position == navigation.position] selected="selected"[/#if]>${message("Navigation.Position." + position)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("console.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isBlankTarget" value="true"[#if navigation.isBlankTarget] checked="checked"[/#if] />${message("Navigation.isBlankTarget")}
						<input type="hidden" name="_isBlankTarget" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					${message("console.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${navigation.order}" maxlength="9" />
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