[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.payment.list")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
	[@flash_message /]
	
	// 收款状态筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.payment.list")} <span>(${message("console.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.ct" method="get">
		<input type="hidden" id="payStatus" name="payStatus" value="${payStatus}" />
		<div class="bar">
			<div class="buttonWrap">
			[@shiro.hasPermission name = "console:payment_button_deleteButton"]
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
				</a>
			[/@shiro.hasPermission]
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						${message("console.payment.filter")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
						 	<li>
						 		<a href="javascript:;" name="payStatus" val="success"[#if payStatus == "success"] class="checked" [/#if]>${message("Payment.Status.success")}</a>	
						 	</li>
							<li>
								<a href="javascript:;" name="payStatus" val="failure"[#if payStatus == "failure"] class="checked"[/#if]>${message("Payment.Status.failure")}</a>
							</li>
							<li>
								<a href="javascript:;" name="payStatus" val="wait"[#if payStatus == "wait"] class="checked"[/#if]>${message("Payment.Status.wait")}</a>
							</li>
						</ul>
					</div>
				</div>
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
					<span id="searchPropertySelectValue" class="value">${message("Page.searchPropertyValue")}</span>
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;"[#if page.searchProperty == "sn"] class="current"[/#if] val="sn">${message("Payment.businessSn")}</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.searchProperty == "payer"] class="current"[/#if] val="payer">${message("Payment.payer")}</a>
						</li>
						<li>
							<a href="javascript:;"[#if page.searchProperty == "orderSn"] class="current"[/#if] val="orderSn">${message("Payment.order.sn")}</a>
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
					<a href="javascript:;" class="sort" name="sn">${message("Payment.businessSn")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">${message("Payment.type")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="method">${message("Payment.method")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="paymentMethod">${message("Payment.paymentMethod")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">${message("Payment.amount")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">${message("Payment.member")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">${message("Payment.order.sn")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">${message("Payment.status")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="paymentDate">${message("Payment.paymentDate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("console.common.createDate")}</a>
				</th>
				<th>
					<span>${message("console.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as payment]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${payment.id}" />
					</td>
					<td>
						${payment.sn}
					</td>
					<td>
						${message("Payment.Type." + payment.type)}
					</td>
					<td>
						${message("Payment.Method." + payment.method)}
					</td>
					<td>
						${payment.paymentMethod}
					</td>
					<td>
						${currency(payment.amount, true)}
					</td>
					<td>
						${(payment.member.username)!"-"}
					</td>
					<td>
						${(payment.order.sn)!"-"}
					</td>
					<td>
						${message("Payment.Status." + payment.status)}
					</td>
					<td>
						[#if payment.paymentDate??]
							<span title="${payment.paymentDate?string("yyyy-MM-dd HH:mm:ss")}">${payment.paymentDate}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						<span title="${payment.createDate?string("yyyy-MM-dd HH:mm:ss")}">${payment.createDate}</span>
					</td>
					<td>
						<a href="view.ct?id=${payment.id}">[${message("console.common.view")}]</a>
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