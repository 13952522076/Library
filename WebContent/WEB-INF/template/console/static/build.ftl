<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.static.build")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $buildType = $("#buildType");
	var $articleCategoryTr = $("#articleCategoryTr");
	var $articleCategoryId = $("#articleCategoryId");
	var $productCategoryTr = $("#productCategoryTr");
	var $productCategoryId = $("#productCategoryId");
	var $beginDateTr = $("#beginDateTr");
	var $beginDate = $("#beginDate");
	var $endDateTr = $("#endDateTr");
	var $endDate = $("#endDate");
	var $count = $("#count");
	var $lang = $("#lang");
	var $statusTr = $("#statusTr");
	var $status = $("#status");
	var $submit = $(":submit");
	
	var first;
	var buildCount;
	var buildTime;
	var buildType;
	var articleCategoryId;
	var productCategoryId;
	var beginDate;
	var endDate;
	var count;
	var lang;

	$buildType.change(function() {
		var $this = $(this);
		if ($this.val() == "article") {
			$articleCategoryTr.show();
			$productCategoryTr.hide();
			$beginDateTr.show();
			$endDateTr.show();
		} else if ($this.val() == "product") {
			$articleCategoryTr.hide();
			$productCategoryTr.show();
			$beginDateTr.show();
			$endDateTr.show();
		} else {
			$articleCategoryTr.hide();
			$productCategoryTr.hide();
			$beginDateTr.hide();
			$endDateTr.hide();
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			count: {
				required: true,
				integer: true,
				min: 1
			}
		},
		submitHandler: function(form) {
			first = 0;
			buildCount = 0;
			buildTime = 0;
			buildType = $buildType.val();
			articleCategoryId = $articleCategoryId.val();
			productCategoryId = $productCategoryId.val();
			beginDate = $beginDate.val();
			endDate = $endDate.val();
			count = parseInt($count.val());
			lang = $lang.val();
			$buildType.prop("disabled", true);
			$articleCategoryId.prop("disabled", true);
			$productCategoryId.prop("disabled", true);
			$beginDate.prop("disabled", true);
			$endDate.prop("disabled", true);
			$count.prop("disabled", true);
			$submit.prop("disabled", true);
			$statusTr.show();
			build();
		}
	});
	
	function build() {
		$.ajax({
			url: "build.ct",
			type: "POST",
			data: {buildType: buildType, articleCategoryId: articleCategoryId, productCategoryId: productCategoryId, beginDate: beginDate, endDate: endDate, first: first, count: count, lang: lang},
			dataType: "json",
			cache: false,
			success: function(data) {
				buildCount += data.buildCount;
				buildTime += data.buildTime;
				if (!data.isCompleted) {
					if (buildType == "article" || buildType == "product") {
						first = data.first;
						$status.text("${message("console.static.building")} [" + first + " - " + (first + count) + "]");
					} else {
						$status.text("${message("console.static.building")}");
					}
					build();
				} else {
					$buildType.prop("disabled", false);
					$articleCategoryId.prop("disabled", false);
					$productCategoryId.prop("disabled", false);
					$beginDate.prop("disabled", false);
					$endDate.prop("disabled", false);
					$count.prop("disabled", false);
					$submit.prop("disabled", false);
					$statusTr.hide();
					$status.empty();
					var time;
					if (buildTime < 60000) {
						time = (buildTime / 1000).toFixed(2) + "${message("console.static.second")}";
					} else {
						time = (buildTime / 60000).toFixed(2) + "${message("console.static.minute")}";
					}
					$.message("success", "${message("console.static.success")} [${message("console.static.buildCount")}: " + buildCount + " ${message("console.static.buildTime")}: " + time + "]");
				}
			}
		});
	}

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.static.build")}
	</div>
	<form id="inputForm" action="build.ct" method="post">
		<table class="input">
			<tr>
				<th>
					${message("console.static.buildType")}:
				</th>
				<td>
					<select id="buildType" name="buildType">
						[#list buildTypes as buildType]
							<option value="${buildType}">${message("console.static." + buildType)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr id="articleCategoryTr" class="hidden">
				<th>
					${message("Article.articleCategory")}:
				</th>
				<td>
					<select id="articleCategoryId" name="articleCategoryId">
						<option value="">${message("console.static.emptyOption")}</option>
						[#list articleCategoryTree as articleCategory]
							<option value="${articleCategory.id}">
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
										&nbsp;&nbsp;
									[/#list]
								[/#if]
								${articleCategory.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr id="productCategoryTr" class="hidden">
				<th>
					${message("Product.productCategory")}:
				</th>
				<td>
					<select id="productCategoryId" name="productCategoryId">
						<option value="">${message("console.static.emptyOption")}</option>
						[#list productCategoryTree as productCategory]
							<option value="${productCategory.id}">
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
			<tr id="beginDateTr" class="hidden">
				<th>
					${message("console.static.beginDate")}:
				</th>
				<td>
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${defaultBeginDate?string("yyyy-MM-dd")}" title="${message("console.static.beginDateTitle")}" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
				</td>
			</tr>
			<tr id="endDateTr" class="hidden">
				<th>
					${message("console.static.endDate")}:
				</th>
				<td>
					<input type="text" id="endDate" name="endDate" class="text Wdate" value="${defaultEndDate?string("yyyy-MM-dd")}" title="${message("console.static.endDateTitle")}" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("console.static.count")}:
				</th>
				<td>
					<input type="text" id="count" name="count" class="text" value="50" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>国际化语言:
				</th>
				<td>
					<select id="lang" name="lang">
						<option value="zh_CN">zh_CN</option>
						<option value="en_US">en_US</option>
					</select>
				</td>
			</tr>
			<tr id="statusTr" class="hidden">
				<th>
					&nbsp;
				</th>
				<td>
					<span class="loadingBar">&nbsp;</span>
					<div id="status"></div>
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