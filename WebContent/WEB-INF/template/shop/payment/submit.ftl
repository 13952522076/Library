<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=[#if requestCharset?has_content]${requestCharset}[#else]utf-8[/#if]" />
<title>${message("shop.payment.submit")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
</head>
<body onload="javascript: document.forms[0].submit();">
	<form action="${requestUrl}"[#if requestMethod??] method="${requestMethod}"[/#if][#if requestCharset?has_content] accept-charset="${requestCharset}"[/#if]>
		[#list parameterMap.entrySet() as entry]
			<input type="hidden" name="${entry.key}" value="${entry.value}" />
		[/#list]
	</form>
</body>
</html>