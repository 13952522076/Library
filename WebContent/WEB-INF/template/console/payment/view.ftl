<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("console.payment.view")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/console/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/jquery.js"></script>
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
		<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> &raquo; ${message("console.payment.view")}
	</div>
	<table class="input">
		<tr>
			<th>
				${message("Payment.businessSn")}:
			</th>
			<td>
				${payment.sn}
			</td>
			<th>
				${message("console.common.createDate")}:
			</th>
			<td>
				${payment.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.status")}:
			</th>
			<td>
				${message("Payment.Status." + payment.status)}
			</td>
			<th>
				${message("Payment.paymentDate")}:
			</th>
			<td>
				${(payment.paymentDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.type")}:
			</th>
			<td>
				${message("Payment.Type." + payment.type)}
			</td>
			<th>
				${message("Payment.method")}:
			</th>
			<td>
				${message("Payment.Method." + payment.method)}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.paymentMethod")}:
			</th>
			<td>
				${payment.paymentMethod}
			</td>
			<th>
				${message("Payment.operator")}:
			</th>
			<td>
				${(payment.operator)!message("console.payment.member")}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.bank")}:
			</th>
			<td>
				${(payment.bank)!"-"}
			</td>
			<th>
				${message("Payment.account")}:
			</th>
			<td>
				${(payment.account)!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.payer")}:
			</th>
			<td>
				${(payment.payer)!"-"}
			</td>
			<th>
				${message("Payment.amount")}:
			</th>
			<td>
				${currency(payment.amount, true)}
				[#if payment.fee > 0]
					(${message("console.payment.withPoundage")}: ${currency(payment.fee, true)})
				[/#if]
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.member")}:
			</th>
			<td>
				${(payment.member.username)!"-"}
			</td>
			<th>
				${message("Payment.order.sn")}:
			</th>
			<td>
				${(payment.order.sn)!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Payment.memo")}:
			</th>
			<td colspan="3">
				${payment.memo}
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td colspan="3">
				<input type="button" class="button" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
			</td>
		</tr>
	</table>
</body>
</html>