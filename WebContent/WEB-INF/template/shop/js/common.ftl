/*
 * Copyright 2012-2014 福州盛云软件技术有限公司.com.cn. All rights reserved.
 * Support: http://www.福州盛云软件技术有限公司.com.cn
 * License: http://www.福州盛云软件技术有限公司.com.cn/license
 * 
 * JavaScript - Common
 * Version: 3.0
 */

var preschoolEdu = {
	base: "${base}",
	locale: "${locale}"
};

var setting = {
	priceScale: "${setting.priceScale}",
	priceRoundType: "${setting.priceRoundType}",
	currencySign: "${setting.currencySign}",
	currencyUnit: "${setting.currencyUnit}",
	stockAlertCount:"${setting.stockAlertCount}",
	uploadImageExtension: "${setting.uploadImageExtension}",
	uploadFlashExtension: "${setting.uploadFlashExtension}",
	uploadMediaExtension: "${setting.uploadMediaExtension}",
	uploadFileExtension: "${setting.uploadFileExtension}",
	passwordMinLength:"${setting.passwordMinLength}",
	passwordMaxLength:"${setting.passwordMaxLength}"
};

var messages = {
	"shop.message.success": "${message("shop.message.success")}",
	"shop.message.error": "${message("shop.message.error")}",
	"shop.dialog.ok": "${message("shop.dialog.ok")}",
	"shop.dialog.cancel": "${message("shop.dialog.cancel")}",
	"shop.dialog.deleteConfirm": "${message("shop.dialog.deleteConfirm")}",
	"shop.dialog.clearConfirm": "${message("shop.dialog.clearConfirm")}",
	"shop.validate.required": "${message("shop.validate.required")}",
	"shop.validate.email": "${message("shop.validate.email")}",
	"shop.validate.url": "${message("shop.validate.url")}",
	"shop.validate.date": "${message("shop.validate.date")}",
	"shop.validate.dateISO": "${message("shop.validate.dateISO")}",
	"shop.validate.pointcard": "${message("shop.validate.pointcard")}",
	"shop.validate.number": "${message("shop.validate.number")}",
	"shop.validate.digits": "${message("shop.validate.digits")}",
	"shop.validate.minlength": "${message("shop.validate.minlength")}",
	"shop.validate.maxlength": "${message("shop.validate.maxlength")}",
	"shop.validate.rangelength": "${message("shop.validate.rangelength")}",
	"shop.validate.min": "${message("shop.validate.min")}",
	"shop.validate.max": "${message("shop.validate.max")}",
	"shop.validate.range": "${message("shop.validate.range")}",
	"shop.validate.accept": "${message("shop.validate.accept")}",
	"shop.validate.equalTo": "${message("shop.validate.equalTo")}",
	"shop.validate.remote": "${message("shop.validate.remote")}",
	"shop.validate.integer": "${message("shop.validate.integer")}",
	"shop.validate.positive": "${message("shop.validate.positive")}",
	"shop.validate.negative": "${message("shop.validate.negative")}",
	"shop.validate.decimal": "${message("shop.validate.decimal")}",
	"shop.validate.pattern": "${message("shop.validate.pattern")}",
	"shop.validate.extension": "${message("shop.validate.extension")}",
	"shop.validate.empty": "${message("shop.validate.empty")}",
	"shop.validate.notmatch": "${message("shop.validate.notmatch")}",
	"shop.validate.emailIllegal": "${message("shop.validate.emailIllegal")}",
	"shop.validate.loginFailed": "${message("shop.validate.loginFailed")}",
	"shop.validate.areaError": "${message("shop.validate.areaError")}",
	"shop.validate.zipcode.error":"${message("shop.validate.zipcode.error")}",
	"shop.validate.phoneNumberIllegal":"${message("shop.validate.phoneNumberIllegal")}",
	"shop.validate.phoneNumberempty":"${message("shop.validate.phoneNumberempty")}",
	"shop.validate.waitinginfomsg":"${message("shop.validate.waitinginfomsg")}",
	"shop.validate.none.type":"${message("shop.validate.none.type")}",
	"shop.validate.type.warn":"${message("shop.validate.type.warn")}",
	"shop.validate.resetpassword.error":"${message("shop.validate.resetpassword.error")}",
	"shop.validate.phone.type":"${message("shop.validate.phone.type")}",
	"shop.validate.emial.type":"${message("shop.validate.emial.type")}",
	"shop.validate.emailIllegal": "${message("shop.validate.emailIllegal")}",
	"shop.header.welcome": "${message("shop.header.welcome")}",
	"shop.header.keyword": "${message("shop.header.keyword")}",
	"shop.cart.empty": "${message("shop.cart.empty")}",
	"shop.cart.accessDenied.error": "${message("shop.cart.accessDenied.error")}",
	"shop.cart.gift":"${message("shop.cart.gift")}",
	"shop.cart.notempty":"${message("shop.cart.notempty")}",
	"shop.cart.quantity.error":"${message("shop.cart.quantity.error")}",
	"shop.cart.lowStock":"${message("shop.cart.lowStock")}",
	"shop.product.content.stockNumber":"${message("shop.product.content.stockNumber")}",
	"shop.product.content.stockNumber.end":"${message("shop.product.content.stockNumber.end")}",
	"shop.product.content.stockNumber.tension":"${message("shop.product.content.stockNumber.tension")}",
	"shop.product.content.stockNumber.shortage":"${message("shop.product.content.stockNumber.shortage")}",
	"shop.product.content.stockNumber.arrivalNotice":"${message("shop.product.content.stockNumber.arrivalNotice")}",
	"shop.product.content.sizeOrColorNot":"${message("shop.product.content.sizeOrColorNot")}",
	"shop.product.content.selectedSize":"${message("shop.product.content.selectedSize")}",
	"shop.product.content.collection.title":"${message("shop.product.content.collection.title")}",
	"shop.product.content.stockNumber.shortage":"${message("shop.product.content.stockNumber.shortage")}",
	"shop.product.content.stockNumber.arrivalNotice":"${message("shop.product.content.stockNumber.arrivalNotice")}",
	"shop.product.content.sizeOrColorNot":"${message("shop.product.content.sizeOrColorNot")}",
	"shop.product.content.popTitle":"${message("shop.product.content.popTitle")}",
	"shop.paymentstatus.unpaid":"${message("shop.paymentstatus.unpaid")}",
	"shop.paymentstatus.partialPayment":"${message("shop.paymentstatus.partialPayment")}",
	"shop.paymentstatus.paid":"${message("shop.paymentstatus.paid")}",
	"shop.paymentstatus.partialRefunds":"${message("shop.paymentstatus.partialRefunds")}",
	"shop.paymentstatus.refunded":"${message("shop.paymentstatus.refunded")}",
	"shop.member.order.hasExpired":"${message("shop.member.order.hasExpired")}",
	"Order.OrderStatus.unconfirmed":"${message("Order.OrderStatus.unconfirmed")}",
	"Order.OrderStatus.confirmed":"${message("Order.OrderStatus.confirmed")}",
	"Order.OrderStatus.completed":"${message("Order.OrderStatus.completed")}",
	"Order.OrderStatus.cancelled":"${message("Order.OrderStatus.cancelled")}",
	"Order.OrderStatus.receipt":"${message("Order.OrderStatus.receipt")}",
	"Order.PaymentStatus.unpaid":"${message("Order.PaymentStatus.unpaid")}",
	"Order.PaymentStatus.partialPayment":"${message("Order.PaymentStatus.partialPayment")}",
	"Order.PaymentStatus.paid":"${message("Order.PaymentStatus.paid")}",
	"Order.PaymentStatus.partialRefunds":"${message("Order.PaymentStatus.partialRefunds")}",
	"Order.PaymentStatus.refunded":"${message("Order.PaymentStatus.refunded")}",
	"Order.PaymentStatus.applyRefunded":"${message("Order.PaymentStatus.applyRefunded")}",
	"Order.ShippingStatus.unshipped":"${message("Order.ShippingStatus.unshipped")}",
	"Order.ShippingStatus.partialShipment":"${message("Order.ShippingStatus.partialShipment")}",
	"Order.ShippingStatus.shipped":"${message("Order.ShippingStatus.shipped")}",
	"Order.ShippingStatus.partialReturns":"${message("Order.ShippingStatus.partialReturns")}",
	"Order.ShippingStatus.returned":"${message("Order.ShippingStatus.returned")}",
	"shop.shippingstatus.returned":"${message("shop.shippingstatus.returned")}",
	"shop.shippingstatus.unshipped":"${message("shop.shippingstatus.unshipped")}",
	"shop.shippingstatus.shipped":"${message("shop.shippingstatus.shipped")}",
	"shop.register.vipcardnumber.empty":"${message("shop.register.vipcardnumber.empty")}",
	"shop.register.vipUserName.empty":"${message("shop.register.vipUserName.empty")}",
    "shop.register.vipMobile.empty":"${message("shop.register.vipMobile.empty")}",
	"shop.register.vipcardnumber.bindingSuccess":"${message("shop.register.vipcardnumber.bindingSuccess")}",
	"shop.register.vipcardnumber.bindingFailed":"${message("shop.register.vipcardnumber.bindingFailed")}",
	"shop.registar.vipcardnumber.agreen":"${message("shop.registar.vipcardnumber.agreen")}",
	"shop.register.loginreg":"${message("shop.register.loginreg")}",
	"shop.register.success" : "${message("shop.register.success")}",
	"shop.register.emailExist": "${message("shop.register.emailExist")}",
	"shop.register.mobileExist":"${message("shop.register.mobileExist")}",
	"shop.login.findPassword":"${message("shop.login.findPassword")}",
	"shop.login.username.notEmpty":"${message("shop.login.username.notEmpty")}",
	"shop.login.password.notEmpty":"${message("shop.login.password.notEmpty")}",
	"shop.login.password.length.greater":"${message("shop.login.password.length.greater")}",
	"shop.login.password.length.lessThan":"${message("shop.login.password.length.lessThan")}",
	"shop.include.popTitle":"${message("shop.include.popTitle")}",
	"shop.include.validateWayDesc":"${message("shop.include.validateWayDesc")}",
    "shop.include.validateWayMailDesc":"${message("shop.include.validateWayMailDesc")}",
    "shop.include.effectivePrice":"${message("shop.include.effectivePrice")}",
    "shop.include.productTotalPrice":"${message("shop.include.productTotalPrice")}",
    "shop.include.productDiscountPrice":"${message("shop.include.productDiscountPrice")}",
    "shop.info.title": "${message("shop.info.title")}",
	"shop.info.province":"${message("shop.info.province")}",
	"shop.info.city":"${message("shop.info.city")}",
	"shop.include.validateCodeNotEmpty":"${message("shop.include.validateCodeNotEmpty")}",
    "shop.resetpassword.success":"${message("shop.resetpassword.success")}",
    "shop.mail.choose.one":"${message("shop.mail.choose.one")}",
    "shop.common.delete":"${message("shop.common.delete")}",
    "shop.orderDetail.shoppingBasket":"${message("shop.orderDetail.shoppingBasket")}",
    "shop.orderDetail.shoppingBasket2":"${message("shop.orderDetail.shoppingBasket2")}",
    "shop.orderDetail.shoppingBasket1":"${message("shop.orderDetail.shoppingBasket1")}",
    "shop.promotion.product.title":"${message("shop.promotion.product.title")}"
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

//删除Cookie
function delCookie(name){
    var  exp  =  new  Date();
    exp.setTime  (exp.getTime()  -  1);
    var  cval  =  getCookie(name);
    document.cookie  =  name  +  "="  +  cval  +  ";  expires="+  exp.toGMTString();
}

// 货币格式化
function currency(value, showSign, showUnit) {
	if (value != null) {
		var price;
		if (setting.priceRoundType == "roundHalfUp") {
			price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else if (setting.priceRoundType == "roundUp") {
			price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else {
			price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		}
		if (showSign) {
			price = setting.currencySign + price;
		}
		if (showUnit) {
			price += setting.currencyUnit;
		}
		return price;
	}
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			}
		}
	}
}

(function($) {

	var zIndex = 100;

	// 检测登录
	$.checkLogin = function() {
		var result = false;
		$.ajax({
			url: preschoolEdu.base + "/login/check.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}

	// 跳转登录
	$.redirectLogin = function (redirectUrl, message) {
		var href = preschoolEdu.base + "/login.ct";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			$.message("warn", message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	}

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}
		
		if (message.type == null || message.content == null) {
			return false;
		}
		
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}
		
		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		
		$message.css({'z-index':'100009','margin-left':'0px'});
		
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 令牌	
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});
	
	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");
		
		if (loginStatus == "accessDenied") {
			$.redirectLogin(location.href, "${message("shop.login.accessDenied")}");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global: false,
					headers: {token: token}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$().ready(function() {

	$("form").submit(function() {
		var $this = $(this);
		if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
			var token = getCookie("token");
			if (token != null) {
				$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
			}
		}
	});

});

// 验证消息
if ($.validator != null) {
	$.extend($.validator.messages, {
		required: message("shop.validate.required"),
		email: message("shop.validate.email"),
		url: message("shop.validate.url"),
		date: message("shop.validate.date"),
		dateISO: message("shop.validate.dateISO"),
		pointcard: message("shop.validate.pointcard"),
		number: message("shop.validate.number"),
		digits: message("shop.validate.digits"),
		minlength: $.validator.format(message("shop.validate.minlength")),
		maxlength: $.validator.format(message("shop.validate.maxlength")),
		rangelength: $.validator.format(message("shop.validate.rangelength")),
		min: $.validator.format(message("shop.validate.min")),
		max: $.validator.format(message("shop.validate.max")),
		range: $.validator.format(message("shop.validate.range")),
		accept: message("shop.validate.accept"),
		equalTo: message("shop.validate.equalTo"),
		remote: message("shop.validate.remote"),
		integer: message("shop.validate.integer"),
		positive: message("shop.validate.positive"),
		negative: message("shop.validate.negative"),
		decimal: message("shop.validate.decimal"),
		pattern: message("shop.validate.pattern"),
		extension: message("shop.validate.extension")
	});
	
	$.validator.setDefaults({
		errorClass: "fieldError",
		ignore: ".ignore",
		ignoreTitle: true,
		errorPlacement: function(error, element) {
			var fieldSet = element.closest("span.fieldSet");
			if (fieldSet.size() > 0) {
				error.appendTo(fieldSet);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler: function(form) {
			$(form).find(":submit").prop("disabled", true);
			form.submit();
		}
	});
}

function contains(attr,str){
    for(var i = 0; i < attr.length; i++){
        if(attr[i] == str){
            return true;
        }
    }
    return false;
}

// 判断是否包含
function containsSize(attr,str,currentStockNum){
    var currentStockNum = null;
    for(var i = 0; i < attr.length; i++){
        if(attr[i].split('_')[1] == str){
            currentStockNum = attr[i].split('_')[2];
            return currentStockNum;
        }
    }
    return currentStockNum;
}

//删除数组的元素
//emp.remove('test')
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

//时间格式化
/** 
 * 时间对象的格式化; 
 */  
function formatDate(val)
{
    var arrays = new Array();
    var arrays2 = new Array();
    arrays = val.split(" ");
    arrays2 = arrays[0].split("-");
    return new Date(arrays2[0],arrays2[1],arrays2[2]).Format("yyyy-MM-dd");
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth(), //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date;
}

/**
 * 支付方式选择
 */
function paymentModeChoice(paymentPluginId){
    var $selectedPaymentType = $("#selectedPaymentType");
    var $selectedPaymentImage = $("#" + paymentPluginId +"Image");
    var $bankNameObject = $selectedPaymentImage.next();//[0].nextElementSibling;
    var $paymentPluginId = $("#paymentPluginId");
    var $alipayFormControl = $("#alipayFormControl");
    var $paymentSelectedTips = $("#paymentSelectedTips");
    var $bankCode = $("#bankCode");
    var $bankName = $("#bankName")
    var $paymentBankNameArray = $('.paymentGroupWrapper .formControl span');
    for(var i = 0,len = $paymentBankNameArray.length; i < len; i++){
        $paymentBankNameArray[i].style.visibility = "hidden";
    }
    $bankNameObject.css({visibility:"visible"});
    $alipayFormControl.find("img").removeClass("paymentImageSection");
    $selectedPaymentType.text($selectedPaymentImage.attr('alt'));
    $selectedPaymentImage.addClass("paymentImageSection");
    $paymentPluginId.val($selectedPaymentImage.attr('plugin'));
    $bankCode.val($selectedPaymentImage.attr('bank'));
    $bankName.val($selectedPaymentImage.attr('bankName'))
    $paymentSelectedTips.show();
}

/**
 * 尺码数据
 */
function productSizeGuidePopUp(pluCode){
	var targetWin = window.open('${base}/common/productSizeGuide.ct?pluCode='+pluCode,'sizeGuide','width=910,height=800,toolbar=no, location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no, resizable=yes');
	targetWin.focus(); //ZD781
	return false;
}

/**
 * 淡入淡出效果
 */
function showMask(){
	var $itxOverlay_header = $("#itxOverlay-header");
	$itxOverlay_header.fadeIn('slow',
        function() {
		$(this).fadeTo('normal', 0.7);
	});
};

/**
 * 单击事件，Mask 被销毁
 */ 
function hiddenMask (){
	var $itxOverlay_header = $("#itxOverlay-header");
	$itxOverlay_header.fadeTo('slow', 0,
	function() {
		$itxOverlay_header.hide();
	});
};

/**
 * 垂直居中窗体
 * @param winObj 窗体对象
 */
function verticalCenterWin(winObj){
	 winObj.css({ 
	    left: ($(window).width() - winObj.outerWidth())/2,
	    top: ($(window).height() - winObj.outerHeight())/2 
	});
}

/**
 * 弹出防伪验证
 */
 $(document).ready(function(){
 	var $validation = $("#validation");
 	var $validation_service = $(".validation-service");
 	var $validation_input = $(".validation-input");
 	var $validation_pass = $(".validation-pass");
 	var $validation_failed = $(".validation-failed");
 	var $modalClose = $(".modalClose");
 	var $validationSubmit = $("#validationSubmit");
 	var $validationInput = $("#validationInput");
 	var $pluCode = $("#pluCode");
 	var $passBackSubmit = $("#passBackSubmit");
 	var $failedBackSubmit = $("#failedBackSubmit");
 	
 	//处理弹出层相对屏幕居中
	$(window).resize(function(){ 
	    var validationServiceWin = $(".validation-service");
	    
	    verticalCenterWin(validationServiceWin);
	});
 	
 	//点击产品防伪验证弹出
 	$validation.click(function(){
 		showMask();
 		$validation_service.fadeIn('normal');
    	$validation_input.fadeIn('normal');
    	
    	var validationServiceWin = $(".validation-service");
    	verticalCenterWin(validationServiceWin);
 	});
 	
 	//关闭层点击
 	$modalClose.click(function(){
    	hiddenMask();
    	$validation_service.fadeOut('normal');
    	$validation_input.fadeOut('normal');
    	$validation_pass.fadeOut('normal');
    	$validation_failed.fadeOut('normal');
    });
    
    //验证成功返回
    $passBackSubmit.click(function(){
    	$validation_pass.fadeOut('fast');
    	$validation_input.fadeIn('normal');
    });
    
    //验证失败返回
    $failedBackSubmit.click(function(){
    	$validation_failed.fadeOut('fast');
    	$validation_input.fadeIn('normal');
    });
    
    //产品防伪验证 
    $validationSubmit.click(function(){
    	var data = "validationCode=" + $validationInput.val();
    	$.ajax({
				url: "${base}/product/securityProducts.ct",
				type: "GET",
				data:data,
				dataType: "json",
				cache: false,
				success: function(data) {
					if (data.message == "success") {
						$validation_input.fadeOut('normal');
						$validation_pass.fadeIn('normal');
						$pluCode.append(data.data.plu);
					}
					else{
						$validation_input.fadeOut('normal');
						$validation_failed.fadeIn('normal');
					}
				}
			});
    	});
 });