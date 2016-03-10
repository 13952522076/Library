<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.index.build")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $buildType = $("#buildType");
	var $count = $("#count");
	var $isPurge = $("#isPurge");
	var $statusTr = $("#statusTr");
	var $status = $("#status");
	var $submit = $(":submit");
	
	var first;
	var buildCount;
	var buildTime;
	var buildType;
	var count;
	var isPurge;
	
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
			count = parseInt($count.val());
			isPurge = $isPurge.prop("checked");
			$buildType.prop("disabled", true);
			$count.prop("disabled", true);
			$isPurge.prop("disabled", true);
			$submit.prop("disabled", true);
			$statusTr.show();
			build();
		}
	});
	
	function build() {
		$.ajax({
			url: "build.ct",
			type: "POST",
			data: {buildType: buildType, isPurge: isPurge, first: first, count: count},
			dataType: "json",
			cache: false,
			success: function(data) {
				buildCount += data.buildCount;
				buildTime += data.buildTime;
				if (!data.isCompleted) {
					first = data.first;
					$status.text("${message("console.index.building")} [" + first + " - " + (first + count) + "]");
					build();
				} else {
					$buildType.prop("disabled", false);
					$count.prop("disabled", false);
					$isPurge.prop("disabled", false);
					$submit.prop("disabled", false);
					$statusTr.hide();
					$status.empty();
					var time;
					if (buildTime < 60000) {
						time = (buildTime / 1000).toFixed(2) + "${message("console.index.second")}";
					} else {
						time = (buildTime / 60000).toFixed(2) + "${message("console.index.minute")}";
					}
					$.message("success", "${message("console.index.success")} [${message("console.index.buildCount")}: " + buildCount + " ${message("console.index.buildTime")}: " + time + "]");
				}
			}
		});
	}

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.index.build")}
	</div>
	<form id="inputForm" action="build.ct" method="post">
		<table class="input">
			<tr>
				<th>
					${message("console.index.buildType")}:
				</th>
				<td>
					<select id="buildType" name="buildType">
						[#list buildTypes as buildType]
							<option value="${buildType}">${message("console.index." + buildType)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("console.index.count")}:
				</th>
				<td>
					<input type="text" id="count" name="count" class="text" value="50" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					${message("console.index.isPurge")}:
				</th>
				<td>
					<input type="checkbox" id="isPurge" name="isPurge" value="true" />
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