<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.member.index")}[#if systemShowPowered] - 小书僮™智慧幼教管理平台 官方网站[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/personal_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
var totalPages = 0;
var favTotalPages = 0;
var optionType;
$().ready(function() {

	[@flash_message /]
	var $modalInfo = $("#modalInfo");
	var $order_list = $(".order-list");
	var $nonDelivery = $("#nonDelivery");
	var $nonDeliveryPanel = $("#nonDeliveryPanel");
	var $orderDetailPanel = $("#orderDetailPanel");
	var $orderdetialClose = $("#orderdetialClose");
	var $modifyPersonalInfo = $("#modifyPersonalInfo");
	var $personalModifyPanel = $("#personalModifyPanel");
	var $personalModifyClose = $("#personalModifyClose");
	var $gender_male_lable = $("#gender_male_lable");
	var $gender_female_lable = $("#gender_female_lable");
	var $personalcenterModifyBtn = $("#personalcenterModifyBtn");
	var $gender = $("#gender");
	var $personalCenterModifyEmailBtn = $("#personalCenterModifyEmailBtn");
	var $personalCenterModifyPwdBtn = $("#personalCenterModifyPwdBtn");
	var $deliveryAddressBtn = $(".deliveryAddressBtn");
	var $deliveryAddressAddBtn = $(".deliveryAddressAddBtn");
	var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
	var $modifyReceiverSubmit = $("#modifyReceiverSubmit");
	var $deliveryAddressAdd = $("#deliveryAddressAdd");
	var $liveLocationAreaId = $("#liveLocationAreaId");
	var $occupationId = $("#occupationId");
	var $personalModifyEmailForm = $("#personalModifyEmailForm");
	var $personalModifyPwdForm = $("#personalModifyPwdForm");
	var $personalModifyMobileForm = $("#personalModifyMobileForm");
	var $personalCenterModifyMobileBtn = $("#personalCenterModifyMobileBtn");
	var $vipCardBindPanel = $("#vipCardBindPanel");
	var $bindVipCardBtn = $("#bindVipCardBtn");
	var $vipCardBindClose = $("#vipCardBindClose");
	var $vipCardBindBtnSubmit = $("#vipCardBindBtnSubmit");
	var $allPastOrders = $("#allPastOrders");
	var $shippedList = $("#shippedList");
	var $unShippedList = $("#unShippedList");

    var $ordersConditionsRadioGroup = document.getElementById('ordersConditionsRadioGroup');
	radioGroupControl($ordersConditionsRadioGroup);
	
     //显示第一页收藏商品
    var favPageNumber = 1;
    pageFavList(favPageNumber);
    
     $(".span-nav-next").click(function(){
        if(!$(this).hasClass("disabled")){
             if(favPageNumber < favTotalPages){
	            favPageNumber++;
	            pageFavList(favPageNumber);
	            if(favPageNumber == favTotalPages){
	                $(this).addClass("disabled");
	            }
	            $(".span-nav-prev").removeClass("disabled");
	         }else{
	            $(this).addClass("disabled");
	         }
        }
    });
    
    $(".span-nav-prev").click(function(){
        if(!$(this).hasClass("disabled")){
            if(favPageNumber > 1){
	            favPageNumber--;
	            pageFavList(favPageNumber);
	            if(favPageNumber == 1){
	                $(this).addClass("disabled");
	            }
	            $(".span-nav-next").removeClass("disabled");
	        }else{
	            $(this).addClass("disabled");
	        }
        }
    });
    
    //订单部分
    var pageNumber = 1;
    var shippingStatus = null;
    
    pageOrderList(pageNumber,shippingStatus);
    //显示所有订单
     $allPastOrders.click(function(){
         pageNumber = 1;
         shippingStatus = null;
         pageOrderList(pageNumber,shippingStatus);
    });
    
    //显示已发货订单
    $shippedList.click(function(){
        pageNumber = 1;
        shippingStatus = "shipped";
        pageOrderList(pageNumber,shippingStatus);
    });
    
    //显示未发货订单
    $unShippedList.click(function(){
        pageNumber = 1;
        shippingStatus = "unshipped";
        pageOrderList(pageNumber,shippingStatus);
    });
    
    $(".table-nav-next").click(function(){
        if(!$(this).hasClass("disabled")){
             if(pageNumber < totalPages){
	            pageNumber++;
	            pageOrderList(pageNumber,shippingStatus);
	            if(pageNumber == totalPages){
	                $(this).addClass("disabled");
	            }
	            $(".table-nav-prev").removeClass("disabled");
	         }else{
	            $(this).addClass("disabled");
	         }
        }
    });
    
    $(".table-nav-prev").click(function(){
        if(!$(this).hasClass("disabled")){
            if(pageNumber > 1){
	            pageNumber--;
	            pageOrderList(pageNumber,shippingStatus);
	            if(pageNumber == 1){
	                $(this).addClass("disabled");
	            }
	            $(".table-nav-next").removeClass("disabled");
	        }else{
	            $(this).addClass("disabled");
	        }
        }
    });
    
    $orderdetialClose.click(function(){
        $orderDetailPanel.fadeOut('normal');
        hiddenMask();
    });
    
    $vipCardBindClose.click(function(){
        $vipCardBindPanel.fadeOut('normal');
        hiddenMask();
    });
    
     //关闭修改用户信息弹出层
    $personalModifyClose.click(function(){
        $personalModifyPanel.fadeOut("noraml");
        hiddenMask();
    });
    
    //地区选择
	$liveLocationAreaId.lSelect({
		 url: preschoolEdu.base+"/common/area.ct"
	});
	
	 //职业选择
	$occupationId.lSelect({
		 url: preschoolEdu.base+"/member/personalCenter/occupation.ct"
	});
    
    //修改用户信息
    $modifyPersonalInfo.click(function(){
        getUserInfo();
    });
    
    $personalcenterModifyBtn.click(function(){
        modifyProfile();
    });
    
    $personalCenterModifyEmailBtn.click(function(){
        modifyUserEmail();
    });
    
    $personalCenterModifyPwdBtn.click(function(){
        modifyUserPwd();
    });
    
    $personalCenterModifyMobileBtn.click(function(){
        modifyUserMobile();
    });
    
    $vipCardBindBtnSubmit.click(function(){
        var vipCardNumber = $("#userVipCardNumber");
    	var vipUserName = $("#userVipUserName");
    	var vipMobile = $("#userVipMobile");
    	activationVipInfo(vipCardNumber.val(),vipUserName.val(),vipMobile.val());
    });
    
    $bindVipCardBtn.click(function(){
        showMask();
	    $vipCardBindPanel.fadeIn("noraml");
	    verticalCenterWin($vipCardBindPanel);
    });
    
    $gender_male_lable.click(function(){
        $gender_female_lable.removeClass("checked");
        $gender_female_lable.addClass("unchecked");
        if($(this).hasClass("unchecked")){
            $(this).removeClass("unchecked");
            $(this).addClass("checked");
        }
    });
    
    $gender_female_lable.click(function(){
        $gender_male_lable.removeClass("checked");
        $gender_male_lable.addClass("unchecked");
        if($(this).hasClass("unchecked")){
            $(this).removeClass("unchecked");
            $(this).addClass("checked");
        }
    });
        
    if("${member.gender}" == "male"){
        $gender.text("${message("shop.personalcenter.modify.honorific.male")}")
    }else if("${member.gender}" == "female"){
        $gender.text("${message("shop.personalcenter.modify.honorific.female")}")
    }
    var $receiverFormModify = $("#receiverFormModify");
     
    //表单验证
	$personalModifyEmailForm.validate({
		rules: {
		    password:{
		        required: true
		    },
			newEmail: {
				required: true,
				email: true
			},
			newAgainEmail: {
			    required: true,
				email: true
			}
		}
	});
	
	$personalModifyPwdForm.validate({
		rules: {
		    password:{
		        required: true
		    },
			newpassword: {
				required: true
			},
			newpasswordconfirm: {
			    required: true
			}
		}
	});
	
	$personalModifyMobileForm.validate({
		rules: {
		    password:{
		        required: true
		    },
			newMobile: {
				required: true,
				isMobile: true
			},
			newAgainMobile: {
			    required: true,
				isMobile: true
			}
		}
	});
	
     // 修改表单验证
	$receiverFormModify.validate({
		rules: {
			consignee: "required",
			modify_orderAreaId: "required",
			address: "required",
			phone: {
			    required: true,
				isMobile: true
			},
			zipCode:{
			   required: true,
			   isZipCode: true
			} 
			
		},
		submitHandler: function(form) {
		    var $receiverModifyAreaSpan = $("#receiverModifyAreaSpan");
		    var $areaSelects = $receiverModifyAreaSpan.find("select");
		    var requestUrl;
		    var $areaIsNotEmpty = true;
		    for(i=0; i< $areaSelects.length; i++){
		         if(!$areaSelects[i].value){
		           $.message("warn",message("shop.validate.areaError"));
		           $(this).focus();
		           return false;
		         }
		    }
		    if(optionType == "modify"){
		        requestUrl = "${base}/member/order/update_receiver.ct";
		    }else if(optionType == "add"){
		        requestUrl = "${base}/member/receiver/add_receiver.ct";
		    }
		    if($isDefaultYes_lable.hasClass("checked")){
		        $("#isDefault_Modify").val("true");
		    }
		    if($isDefaultNo_lable.hasClass("checked")){
		        $("#isDefault_Modify").val("false");
		    }
			$.ajax({
				url: requestUrl,
				type: "POST",
				data: $receiverFormModify.serialize(),
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$modifyReceiverSubmit.prop("disabled", true);
				},
				success: function(data) {
					if (data.message.type == "success") {
						$modifyOrderAddressPanel.fadeOut('normal');
						hiddenMask();
						location.reload(true);
					} else {
						$.message("warn",data.message.content);
					}
				},
				complete: function() {
					$modifyReceiverSubmit.prop("disabled", false);
				}
			});
		}
	});
    
    $modifyReceiverCancle = $("#modifyReceiverCancle");
    $modifyReceiverCancle.click(function(){
         hiddenMask();
	     $modifyOrderAddressPanel.fadeOut('normal');
	     location.reload(true);
    });
    
    $deliveryAddressAdd.click(function(){
        $receiverFormModify[0].reset();
        $receiverFormModify.attr("action",preschoolEdu.base + "/member/receiver/add_receiver.ct");
        showMask();
        $("#pop_title_receiver").text("${message("shop.orderDetail.modifyReceiptAddress.add")}");
        $("#modifyReceiverSubmit span").text("${message("shop.orderDetail.modifyReceiverSubmit.add")}");
	    var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
	    var $modify_orderAreaId = $("#modify_orderAreaId");
	    $modifyOrderAddressPanel.show();
	    $modify_orderAreaId.lSelect({
			    url: preschoolEdu.base+"/common/area.ct"
		});
		optionType = "add";
	    verticalCenterWin($modifyOrderAddressPanel);
    });
    
    var $isDefaultYes_lable = $("#isDefaultYes_lable");
    var $isDefaultNo_lable = $("#isDefaultNo_lable");
    $isDefaultYes_lable.click(function(){
        if(!$isDefaultYes_lable.hasClass("checked") && $isDefaultYes_lable.hasClass("unchecked")){
            $isDefaultYes_lable.removeClass("unchecked");
            $isDefaultYes_lable.addClass("checked");
            $("#isDefault_Modify").val("true");
        }
        if($isDefaultNo_lable.hasClass("checked")){
            $isDefaultNo_lable.removeClass("checked");
            $isDefaultNo_lable.addClass("unchecked");
        }
    });
    
    $isDefaultNo_lable.click(function(){
        if(!$isDefaultNo_lable.hasClass("checked") && $isDefaultNo_lable.hasClass("unchecked")){
            $isDefaultNo_lable.removeClass("unchecked");
            $isDefaultNo_lable.addClass("checked");
            $("#isDefault_Modify").val("false");
        }
        if($isDefaultYes_lable.hasClass("checked")){
            $isDefaultYes_lable.removeClass("checked");
            $isDefaultYes_lable.addClass("unchecked");
        }
    });
    
});
//获取用户详情
function getUserInfo(){
	var $name = $("#name");
	var $birth = $("#birth");
	var $gender_male_lable = $("#gender_male_lable");
	var $gender_female_lable = $("#gender_female_lable");
	var $personalModifyPanel = $("#personalModifyPanel");
	var $originpassword4modify = $("#originpassword4modify");
	var $currentUserEmail = $("#currentUserEmail");
	var $currentUserMobile = $("#currentUserMobile");
	var $languagePreferenceId = $("#languagePreferenceId");
	var $height = $("#height");
    var $weight = $("#weight");
    var $bust = $("#bust");
    var $waist = $("#waist");
    var $hip = $("#hip");
    var $age = $("#age");
	$.ajax({
		url: "getUserInfo.ct",
        type: "GET",
		dataType: "json",
		cache: false,
	    success:function(data){
	    	var member = data.member;
	    	var languagePreferences = data.languagePreferences;
	        $name.val(member.name);
	        $height.val(member.height);
			$weight.val(member.weight);
			$bust.val(member.bust);
			$waist.val(member.waist);
			$hip.val(member.hip);
			$age.val(member.age);
	        $currentUserEmail.text(member.email);
	        if(member.mobile){
	            $currentUserMobile.text(member.mobile);
	        }else{
	            $currentUserMobile.text("-");
	        }
	        if(member.gender == "male"){
	            $gender_male_lable.removeClass("unchecked");
	            $gender_male_lable.addClass("checked");
	        }else if(member.gender == "female"){
	            $gender_female_lable.removeClass("unchecked");
	            $gender_female_lable.addClass("checked");
	        }
	        $languagePreferenceId.empty();
	        for(i=0,len=languagePreferences.length; i<len; i++){
	           var languageId   = languagePreferences[i]['id'];
	           var languageName = languagePreferences[i]['languageName'];
	           var isSelect = languagePreferences[i]['languageCode'] == member['languageCode']? 'selected':'';
	           var option = "<option value='"+languageId+"' "+isSelect+">"+languageName+"</option>";
	           $(option).appendTo($languagePreferenceId)
	        }
	        if(member.birth){
	            $birth.val(timeStamp2String(member.birth));
	        }else{
	            $birth.val(null);
	        }
	        
	        $originpassword4modify.val("");
	    	showMask();
	    	$personalModifyPanel.fadeIn("noraml");
	        verticalCenterWin($personalModifyPanel);
	    }
	});
}
//更新资料
function modifyProfile(){
    var $personalModifyForm = $("#personalModifyForm");
    var $name4Modify = $("#name");
    var $gender_male_lable = $("#gender_male_lable");
    var $gender_female_lable = $("#gender_female_lable");
    var $birth4Modify = $("#birth");
    var $token = $("#token");
    var $languagePreferenceId = $("#languagePreferenceId");
    var $liveLocationAreaId = $("#liveLocationAreaId");
    var $liveCountry = $("#liveCountry");
    var $height = $("#height");
    var $weight = $("#weight");
    var $bust = $("#bust");
    var $waist = $("#waist");
    var $hip = $("#hip");
    var $age = $("#age");
    var $occupationId = $("#occupationId");
    var regular = /^(\d*\.)?\d+$/;
    
    var gender;
    if(!$name4Modify.val()){
        $.message("warn","${message("shop.personalcenter.modify.nameempty")}");
        return;
    }
    if($gender_male_lable.hasClass("checked")){
        gender = 0;
    }else if($gender_female_lable.hasClass("checked")){
        gender = 1;
    }else{
       $.message("warn","${message("shop.personalcenter.modify.genderempty")}");
       return; 
    }
    if(!regular.test($height.val())){
       $.message("warn","${message("shop.personalCenter.heightError")}");
       return; 
    }
    if(!regular.test($weight.val())){
       $.message("warn","${message("shop.personalCenter.weightError")}");
       return;  
    }
    if(!regular.test($bust.val())){
        $.message("warn","${message("shop.personalCenter.bustError")}");
        return; 
    }
    if(!regular.test($waist.val())){
        $.message("warn","${message("shop.personalCenter.waistError")}");
        return; 
    }
    if(!regular.test($hip.val())){
        $.message("warn","${message("shop.personalCenter.hipError")}");
        return; 
    }
    var token = getCookie("token");
    $token.val(token);
    $.ajax({
            url: preschoolEdu.base+"/common/public_key.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			success: function(data){
			    $.ajax({
			        url: $personalModifyForm.attr("action"),
					type: "POST",
					dataType: "json",
					cache: false,
					data: {
						name:$name4Modify.val(),
						gender:gender,
						birth:$birth4Modify.val(),
						liveLocation:$liveLocationAreaId.val(),
						country:$liveCountry.val(),
						languagePreferenceId:$languagePreferenceId.val(),
						height:$height.val(),
						weight:$weight.val(),
						bust:$bust.val(),
						waist:$waist.val(),
						hip:$hip.val(),
						occupation:$occupationId.val()
					},
					success: function(message) {
						if (message.type == "success") {
						    var options = [];
						    options['path'] = '/';
						    addCookie("name",$name4Modify.val(),options)
						    var value = $name4Modify.val();
							$.message("success","${message("shop.personalcenter.modify.success")}");
							setTimeout(function() {
					            location.reload(true);
				            }, 2000);
						} else {
							$.message('error',message.content);
                            return;
						}
					}
			    });
			}
        });
}

//更新邮件
function modifyUserEmail(){
    var $personalModifyEmailForm = $("#personalModifyEmailForm");
    var $modifyEmailPwd = $("#modifyEmailPwd");
    var $newEmail = $("#newEmail");
    var $newAgainEmail = $("#newAgainEmail");
    var $token = $("#token");
    var token = getCookie("token");
    $token.val(token);
    if(!$modifyEmailPwd.val()){
        $.message('error',message("shop.login.password.notEmpty"));
        return;
    }
    $.ajax({
            url: preschoolEdu.base+"/common/public_key.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			success: function(data){
			    var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
				var originPassword4ModifyValue = $modifyEmailPwd.val();
				var enOriginPassword4Modify = hex2b64(rsaKey.encrypt(originPassword4ModifyValue));
			    $.ajax({
			        url: $personalModifyEmailForm.attr("action"),
					type: "POST",
					dataType: "json",
					cache: false,
					data: {
						password:enOriginPassword4Modify,
						email:$newEmail.val(),
						againEmail:$newAgainEmail.val()
					},
					success: function(message) {
						if (message.type == "success") {
						    var options = [];
						    options['path'] = '/';
							$.message("success","${message("shop.personalcenter.modify.success")}");
							setTimeout(function() {
					            location.reload(true);
				            }, 2000);
						} else {
							$.message('error',message.content);
                            return;
						}
					}
			    });
			}
        });
}

//更新邮件
function modifyUserMobile(){
    var $personalModifyMobileForm = $("#personalModifyMobileForm");
    var $modifyMobilePwd = $("#modifyMobilePwd");
    var $newMobile = $("#newMobile");
    var $newAgainMobile = $("#newAgainMobile");
    var $token = $("#token");
    var token = getCookie("token");
    $token.val(token);
    if(!$modifyMobilePwd.val()){
        $.message('error',message("shop.login.password.notEmpty"));
        return;
    }
    $.ajax({
            url: preschoolEdu.base+"/common/public_key.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			success: function(data){
			    var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
				var originPassword4ModifyValue = $modifyMobilePwd.val();
				var enOriginPassword4Modify = hex2b64(rsaKey.encrypt(originPassword4ModifyValue));
			    $.ajax({
			        url: $personalModifyMobileForm.attr("action"),
					type: "POST",
					dataType: "json",
					cache: false,
					data: {
						password:enOriginPassword4Modify,
						mobile:$newMobile.val(),
						againMobile:$newAgainMobile.val()
					},
					success: function(message) {
						if (message.type == "success") {
						    var options = [];
						    options['path'] = '/';
							$.message("success","${message("shop.personalcenter.modify.success")}");
							setTimeout(function() {
					            location.reload(true);
				            }, 2000);
						} else {
							$.message('error',message.content);
                            return;
						}
					}
			    });
			}
        });
}

//更新Pwd
function modifyUserPwd(){
    var $personalModifyPwdForm = $("#personalModifyPwdForm");
    var $originpassword4modify = $("#originpassword4modify");
    var $newpassword = $("#newpassword")
    var $modifyEmailPwd = $("#modifyEmailPwd");
    var $newpasswordconfirm = $("#newpasswordconfirm");
    var $newEmail = $("#newEmail");
    var $newAgainEmail = $("#newAgainEmail");
    var $token = $("#token");
    var token = getCookie("token");
    $token.val(token);
    if(!$originpassword4modify.val()){
        $.message('error',message("shop.login.password.notEmpty"));
        return;
    }
     $.ajax({
            url: preschoolEdu.base+"/common/public_key.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			success: function(data){
			    var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
				var originPassword4ModifyValue = $originpassword4modify.val();
				var enOriginPassword4Modify = hex2b64(rsaKey.encrypt(originPassword4ModifyValue));
				var enNewPassword = hex2b64(rsaKey.encrypt($newpassword.val()));
				if(originPassword4ModifyValue){
				    if($newpassword.val().length < 1 || $newpassword.val().length > 117){
				        $.message("warn","${message("shop.personalcenter.modify.newpassword.error")}");
				        $newpassword.focus();
				        return;
				    }
				    if($newpassword.val() && $newpassword.val() != $newpasswordconfirm.val()){
					    $.message("warn","${message("shop.validate.resetpassword.error")}");
					    $newpasswordconfirm.focus();
					    return;
					}
				}
			    $.ajax({
			        url: $personalModifyPwdForm.attr("action"),
					type: "POST",
					dataType: "json",
					cache: false,
					data: {
					    originPasswordModify:enOriginPassword4Modify,
						newPassword:enNewPassword
					},
					success: function(message) {
						if (message.type == "success") {
						    var options = [];
						    options['path'] = '/';
							$.message("success","${message("shop.personalcenter.modify.success")}");
							setTimeout(function() {
					            location.reload(true);
				            }, 2000);
						} else {
							$.message('error',message.content);
                            return;
						}
					}
			    });
			}
        });
}
//个人中心的订单列表需要分页处理，及用Ajax取数据
function pageOrderList(pageNumber,shippingStatus){
    $.ajax({
        url: "${base}/member/order/orderList.ct",
		type: "GET",
		traditional: true,
		data: {
			pageNumber:pageNumber,
			shippingStatus:shippingStatus
		 },
		dataType: "json",
		cache: false,
		success: function(data) {
		    if(data.page){
		    	var content = data.page.content;
		    	var orderListHTML = '';
		    	var token = getCookie("token");
		    	if(content && content.length > 0){
		    		for(var i = 0; i < content.length; i++){
			    		var order = content[i];
			    		var create_date = formatDate(order.createDate);
			    		var orderStatus = message('shop.shippingstatus.'+order.shippingStatus);
			    		var paymentStatus = message('shop.paymentstatus.'+order.paymentStatus);
			    		var paymentBtnHTML = "";
			    		if(!order.expired && (order.orderStatus == "unconfirmed" || order.orderStatus == "confirmed") && (order.paymentStatus == "unpaid" || order.paymentStatus == "partialPayment")){
			    		    paymentBtnHTML = '<form action="${base}/member/orderdetail/payment.ct" method="get">'+
			    		                     '<input type="hidden" value="'+order.sn+'" name="orderSn"/>'+
			    		                     '<div class="personalcenter_option">'+
			    		                           '<button type="submit" id="paymentSubmit" class="button butBlack">'+
		                                                 '<span>${message("shop.finishorder.payment")}</span>'+
		       										'</button>'+
			    		                     '</div></form>'
			    		}
					    var orderExpired = '';
					    var hasRefunding = '';
					    var orderStatus = '';
					    var paymentStatus ='';
					    var shippingStatus ='';
					    if(order.expired){
							orderExpired = "${message("shop.member.order.hasExpired")}";
							orderStatus = orderExpired;
						}else{
						    if(order.hasReturns){
							    hasRefunding = "(${message("Order.PaymentStatus.refunding")})";
							    orderStatus = message("Order.PaymentStatus." + order.paymentStatus) + hasRefunding;
							}else if(order.paymentStatus == "paid" && order.shippingStatus == "unshipped"){
				                orderStatus = "${message("shop.member.order.waitingShipping")}";
					        }else if(order.paymentStatus == "paid" && (order.shippingStatus == "shipped" || order.shippingStatus == "partialShipment")){
				                orderStatus = message("Order.ShippingStatus." + order.shippingStatus);
					        }else{
							    orderStatus = message("Order.PaymentStatus." + order.paymentStatus);
							}
						}
			    		var thumbnail = order.orderItems[0].thumbnail;
			    		if(!thumbnail || thumbnail == "null"){
			    		    thumbnail = preschoolEdu.base + "/resources/shop/images/noimage_thumb.png"; 
			    		}			    		   
			    		orderListHTML += '<li class="prodcutItem " data-index="0">'+
					        				'<table><tbody>'+
				              				 	'<tr>'+
					        						'<td valign="top" class="product_thumbimage"><img class="prodcut-img" src="'+thumbnail+'"/></td>'+
				                  					'<td>'+
				                  					      '<div class="pc_orderstatus"><a href="javascript:;" onclick="showOrderDetail('+order.id+')" id="nonDelivery" style="text-decoration:underline;"><span style="text-align: left;">${message('shop.payment.viewOrder')}</span></a></div>'+
				                  					    paymentBtnHTML +
										      			'<ul>'+
															'<li class="personalControl">'+
																'<label class="vipLabelUp" for="orderNum">${message("shop.order.sn")}:</label>'+
																'<span id="orderNum">'+order.sn+'</span>'+
															'</li>'+
															'<li class="personalControl">'+
																'<label class="vipLabelUp" for="orderNum">${message("Order.orderStatus")}：</label>'+
																'<span id="orderNum">'+orderStatus+'</span>'+
															'</li>'+
															'<li class="personalControl">'+
																'<label class="vipLabelUp" for="orderNum">${message("shop.personalcenter.transactionDate")}:</label>'+
																'<span id="orderNum">'+create_date+'</span>'+
															'</li>'+
															'<li class="personalControl">'+
																'<label class="vipLabelUp" for="orderNum">${message("shop.personalcenter.totalNumOfGoods")}:</label>'+
																'<span id="orderNum">'+order.quantity+'</span>'+
														    '</li>'+
															'<li class="personalControl">'+
																'<label class="vipLabelUp" for="orderNum">${message("shop.personalcenter.totalOrderNum")}:</label>'+
																'<span id="orderNum">'+currency(order.amount,true)+'</span>'+
															'</li>'+
														'</ul>'+
				                  					'</td>'+
				               					'</tr>'+
				           					'</tbody></table>'+
		               					 '</li>';	
                	$(".table-nav-total-pages").text(data.page.totalPages);
			    	$(".table-nav-current-page").text(pageNumber);
			    	$(".prodcutItemList").html(orderListHTML);
			    	totalPages = data.page.totalPages;		               					 	               					 
			    	}
		    	}else{
		    		if(shippingStatus = "shipped"){
		    			orderListHTML = '<div class="order_empty"><span>${message("shop.personalcenter.shippedorderempty")}</span></div>';
		    			$(".table-nav-total-pages").text(data.page.totalPages);			    				    		
			    		$(".table-nav-current-page").text(pageNumber);
			    		$(".prodcutItemList").html(orderListHTML);
			    		totalPages = data.page.totalPages;
			    	}else if(shippingStatus = "unshipped"){
		    			orderListHTML = '<div class="order_empty"><span>${message("shop.personalcenter.unshippedorderempty")}</span></div>';
		    			$(".table-nav-total-pages").text(data.page.totalPages);			    	
			    		$(".table-nav-current-page").text(pageNumber);
			    		$(".prodcutItemList").html(orderListHTML);
			    		totalPages = data.page.totalPages;
			    	}else if(shippingStatus = "null"){
		    			orderListHTML = '<div class="order_empty"><span>${message("shop.personalcenter.orderempty")}</span><a href="${base}/product/list.ct">${message("shop.personalcenter.ordergotoeshop")}</a></div>';
		    			$(".table-nav-total-pages").text(data.page.totalPages);			    	
			    		$(".table-nav-current-page").text(pageNumber);
			    		$(".prodcutItemList").html(orderListHTML);
			    		totalPages = data.page.totalPages;
			    	}
			    	$(".table-nav-prev").addClass("disabled");
			    	$(".table-nav-next").addClass("disabled");
		    	}		    				    
		    }
		    buttonDisable(pageNumber,totalPages);
		}
    });
}

function buttonDisable(pageNumber,totalPages){
    if(pageNumber == totalPages){
        if(pageNumber > 1){
            $(".table-nav-prev").removeClass("disabled");
            $(".table-nav-next").addClass("disabled");
        }else if(pageNumber == 1){
            $(".table-nav-prev").addClass("disabled");
            $(".table-nav-next").addClass("disabled");
        }
    }else if(pageNumber < totalPages){
    	if(pageNumber == 1){
            $(".table-nav-prev").addClass("disabled");
            $(".table-nav-next").removeClass("disabled");
        }else if(pageNumber > 1){           
        	$(".table-nav-prev").removeClass("disabled");
        	$(".table-nav-next").removeClass("disabled");
        }
    }
}

//个人中心的收藏商品需要分页处理，及用Ajax取数据

function pageFavList(pageNumber){
    $.ajax({
        url: "${base}/member/personalCenter/favList.ct",
		type: "GET",
		traditional: true,
		data: {
			pageNumber:pageNumber
        },
		dataType: "json",
		cache: false,
		success: function(data) {
		    	var  favListHTML = '';
		    	var  deleteHTML ='';  
		    	deleteHTML +='<a href="javascript:void(0)" onclick="deleteFavorite()" style="text-decoration:underline;float: left;font-size: 13px;">'+
		                                     '<span>${message("shop.common.delete")} </span>'+
		                              '</a>';
		    	var token = getCookie("token");
		    	if(data.page && data.page.length > 0) {
		    		for(var i = 0; i < data.page.length; i++) {
			    		var product = data.page[i];
			    		var brand = product.brand;
			    		var productCategory = product.productCategory;
			    		var path = product.path;
			    		var brandId = product.brand.id;
			    		var productCategoryId = product.productCategory.id;
			    		var productId = product.id;
			    		var productImage = product.image;
			    		var productName = product.name;
			    		if(productName.length > 10){
			    		 productName = productName.substring(0,10);
			    		}
			    		var productPrice = product.price;
			    		var productIsMarketable = product.isMarketable;
		    		    var imageHtml = '';
				    	favListHTML +=  '<div style="width: 215px;margin-top: 25px;float: left;">';
				    	 if(brand || productCategory) {
				    	    imageHtml += '<a target="_blank" href='+'"${base}'+ path +'?brandId='+brandId+'&productCategoryId='+productCategoryId+'&productId='+productId+'">';
				    	 } else {
				    	    imageHtml += '<a target="_blank" href='+'"${base}'+path+'?productId='+productId+'">';
				    	 }
				    	 favListHTML += imageHtml ;
				    	 favListHTML+= '<img style="width: 145px;" src='+'"'+productImage+'"'+'data-original='+'"'+productImage+'"/>'+
													'</a>'+
													'<div style="margin-right: 5px;" id ="productNameCheckGroup_'+ productId +'"'+'>'+
													     '<input type="checkbox" class="confirmCheckBox" name="ids" id ='+'"'+ productId + '"'+'value='+'"'+ productId +'"/>'+
				                                         '<label class="unconfirm" style="padding-left: 15px;text-align: left;" for='+'"'+productId+'"'+'>'+productName+'${message("...")}</label>';
				    	 favListHTML += '</a>'+
											  '</span>'+
								               '<p style="font-size: 11px;">价格:'+currency(productPrice,true)+'</p>';
		                 if(!productIsMarketable) {
		                 	 favListHTML +=  '<p style="font-size: 11px;">${message("shop.personalcenter.offSale")}</p>';
		                 }
		                 favListHTML +=    '</div>'+
								        '</div>'+
							      '</div>';		
			        	$(".span-nav-total-pages").text(data.totalPages);
				    	$(".span-nav-current-page").text(pageNumber);
				    	$(".delteProdcutList").html(deleteHTML);
				    	$(".favProdcutList").html(favListHTML);
				    	favTotalPages = data.totalPages;	
			    	}
		    	} else {
	    			favListHTML = '<div style="line-height: 200px;text-align: center;font-size: 14px; margin-left: 413px;">'+
	    			                  '<span>${message("暂无收藏商品数据")}</span>'+
	    			               '</div>'; 
	    			$(".span-nav-total-pages").text(data.totalPages);			    	
		    		$(".span-nav-current-page").text(pageNumber);
		    		$(".favProdcutList").html(favListHTML);
		    		favTotalPages = data.totalPages;
			    	$(".span-nav-prev").addClass("disabled");
			    	$(".span-nav-next").addClass("disabled");
		    	}
		    	favButtonDisable(pageNumber,favTotalPages);
		    	var $subscribeAcceptCheckBoxGroup = document.getElementById('addFavorite');
	            checkBoxGroupControl($subscribeAcceptCheckBoxGroup);	 
		    }
    });
}

function favButtonDisable(pageNumber,totalPages) {
    if(pageNumber == totalPages) {
        if(pageNumber > 1) {
            $(".span-nav-prev").removeClass("disabled");
            $(".span-nav-next").addClass("disabled");
        } else if(pageNumber == 1) {
            $(".span-nav-prev").addClass("disabled");
            $(".span-nav-next").addClass("disabled");
        }
    } else if(pageNumber < totalPages) {
    	if(pageNumber == 1) {
            $(".span-nav-prev").addClass("disabled");
            $(".span-nav-next").removeClass("disabled");
        } else if(pageNumber > 1) {           
        	$(".span-nav-prev").removeClass("disabled");
        	$(".span-nav-next").removeClass("disabled");
        }
    }
}

function cancelOrder(order_sn){
	if (confirm("${message("shop.member.order.cancelConfirm")}")) {
			$.ajax({
				url: "${base}/member/order/cancel.ct?sn="+order_sn,
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(message) {
					if (message.type == "success") {
						location.reload(true);
					} else {
						$.message(message);
					}
				}
			});
		}
		return false;
}

/*整单申请退款*/
function applyForRefund(order_sn){
	if (confirm("${message("shop.member.order.refundConfirm")}")) {
			$.ajax({
				url: "${base}/member/order/cancel.ct?sn="+order_sn,
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(message) {
					if (message.type == "success") {
						location.href="${base}/member/order/refundResult.ct";
					} else {
						$.message(message);
					}
				}
			});
		}
		return false;
}

//确认收货订单
function confirmOrder(orderItemId,sn){
    if (confirm("${message("shop.member.order.confirm.revceived")}")){
        $.ajax({
            url: "${base}/member/order/confirmReceiver.ct?orderItemId="+orderItemId+"&sn="+sn,
            type: "GET",
			dataType: "json",
			cache: false,
			success: function(message) {
					if (message.type == "success") {
						location.reload(true);
					} else {
						$.message(message);
					}
				}
        });
    }
}

function showOrderDetail(orderId){ 
    var $orderDetailPanel = $("#orderDetailPanel");
    showMask();
    $orderDetailPanel.fadeIn("normal");
    verticalCenterWin($orderDetailPanel);
    $.ajax({
        url: "${base}/member/order/orderDetail.ct",
		type: "GET",
		traditional: true,
		data: {
			orderId:orderId
		},
		dataType: "json",
		cache: false,
		success: function(data){
		    var order = data.order;
		    $("#receiver_consignee").text(order.consignee);
		    $("#receiver_phone").text(order.phone);
		    $("#receiver_address").text(order.areaName+order.address);
		    $("#receiver_zipCode").text(order.zipCode);
		    $("#view_ordersn").text(order.sn);
		    $("#orderAmountPayable").text(currency(order.amount,true));
		    var orderItemHTML = "";
		    var orderStatusHTML = "";
		    var paymentMethod = message("shop.paymentstatus."+order.paymentStatus);
		    var orderStatusOptionHTML = "";
		    var token = getCookie("token");
		    var paymentButton = '<form action="${base}/member/orderdetail/payment.ct" method="get">'+
		    		                     '<input type="hidden" value="'+order.sn+'" name="orderSn"/>'+
		    		                     '<div class="personalcenter_option" style="float:left">'+
		    		                           '<button type="submit" id="paymentSubmit" class="button butBlack">'+
	                                                 '<span>${message("shop.finishorder.payment")}</span>'+
	       										'</button>'+
		    		                     '</div></form>'
		    var orderCancelBtn = '';
		    var confirmOrderHTML = '';
		    var returnGoodsApply = '';
		    var returnGoodsHistory = '';
		    var logisticsInfo = '';
		    var orderExpired = '';
		    var hasRefunding ='';
		    if(order.expired){
				orderExpired = "(${message("shop.member.order.hasExpired")})";
				$("#userInfoOrderStatus").text(message("Order.OrderStatus." + order.orderStatus) + orderExpired);
			}else{
			   $("#userInfoOrderStatus").text(message("Order.OrderStatus." + order.orderStatus));
			}
			if(order.hasReturns){
			    hasRefunding = "(${message("Order.PaymentStatus.refunding")})";
			    $("#userInfoPaymentStatus").text(message("Order.PaymentStatus." + order.paymentStatus) + hasRefunding);
			}else{
			    $("#userInfoPaymentStatus").text(message("Order.PaymentStatus." + order.paymentStatus));
			}
		    $("#userInfoShippingStatus").text(message("Order.ShippingStatus." + order.shippingStatus));
		    
		    if(!order.expired && (order.orderStatus == "unconfirmed" || order.orderStatus == "confirmed") && (order.paymentStatus == "unpaid" || order.paymentStatus == "partialPayment")){
		       orderStatusOptionHTML = paymentButton;
		    }
		    if(!order.expired && order.returnQuantity == 0 && ((order.orderStatus == "unconfirmed" && order.paymentStatus == "unpaid"))){
		        orderCancelBtn = '<div class="personalcenter_option" style="float:left">'+
			                           '<button type="button" onclick="cancelOrder('+order.sn+')" id="cancle" class="button butBlack">'+
	                                         '<span>${message("shop.member.order.cancel")}</span>'+
										'</button>'+
			    		              '</div>';
		    }
		    if(order.paymentStatus == "paid" && order.shippingStatus == "unshipped" && !order.hasReturns){
		    
		        orderCancelBtn = '<div class="personalcenter_option" style="float:left">'+
			                           '<button type="button" onclick="applyForRefund('+order.sn+')" id="cancle" class="button butBlack">'+
	                                         '<span>${message("shop.member.order.applyRefund")}</span>'+
										'</button>'+
			    		              '</div>';
		    }
		    
		    //确认收货
		    if((order.shippingStatus == "partialShipment" || order.shippingStatus == "shipped") && order.orderStatus != 'receipt' && 
		        order.orderStatus != "completed"){
		        confirmOrderHTML = '<div onclick="confirmOrder('+0+','+order.sn+')" class="personalcenter_option" style="float:left">'+
		    		                           '<button style="width:60px;" type="button" id="confirmOrder" class="button butBlack">'+
	                                                 '<span>${message("shop.member.order.confirom.receiver")}</span>'+
	       										'</button>'+
		    		                '</div>'
		        orderStatusOptionHTML = confirmOrderHTML;
		    }
		    //申请退货
		    if((order.orderStatus == "receipt" && order.paymentStatus != "refunded") && order.shippingStatus != 'returned'){
		        returnGoodsApply = '<div class="personalcenter_option" style="float:left">'+
		    		                           '<button style="width:60px;" type="button" id="returnGoodsApply" class="button butBlack" onclick="location.href=\'${base}/member/returnGoods/goodsInfo.ct?orderId='+order.id+'\'">'+
	                                                 '<span>${message("shop.personalCenter.returnGoodsApply")}</span>'+
	       										'</buttona>'+
		    		                '</div>'
		    }
		    //退货历史
		    var returns = order.returns;
		    if(returns && returns.length > 0){
			    returnGoodsHistory = '<div class="personalcenter_option" style="float:left">'+
		    		                           '<button type="button" id="returnGoodsHistory" class="button butBlack" onclick="location.href=\'returnGoodsHistory.ct?orderId='+order.sn+'\'">'+
	                                                 '<span>${message("shop.personalCenter.returnGoodsHistory")}</span>'+
	       										'</buttona>'+
			    		                '</div>'
		    }
		    //订单物流数据
		    if(order.shippingStatus == "partialShipment" || order.shippingStatus == "shipped" || order.shippedQuantity > 0){
		         logisticsInfo = '<div class="personalcenter_option" style="float:left">'+
		    		                           '<button style="width:60px;" type="button" id="logisticsInfo" class="button butBlack" onclick="location.href=\'logisticsInfo.ct?orderId='+order.sn+'\'">'+
	                                                 '<span>${message("shop.personalCenter.logisticsInfo")}</span>'+
	       										'</buttona>'+
			    		                '</div>'
		    }
		    $("#orderOperation").html(orderStatusHTML + orderStatusOptionHTML + orderCancelBtn + returnGoodsApply + returnGoodsHistory + logisticsInfo);
		    
		    if(order.orderItems && order.orderItems.length > 0){
		        for(var i =0; i < order.orderItems.length; i++){
		            var orderItem = order.orderItems[i];
		            var singleOrderConfirmHTML = '';
		            if((order.shippingStatus == "partialShipment" || order.shippingStatus == "shipped") && order.orderStatus != "receipt"){
					        singleOrderConfirmHTML = '<div class="personalcenter_option" style="float:left">'+
					    		                           '<button style="width:60px;" onclick="confirmOrder('+orderItem.id+','+order.sn+')" type="button" id="confirmOrderSingle'+orderItem.id+'" class="button butBlack">'+
				                                                 '<span>${message("shop.member.order.confirom.receiver")}</span>'+
				       										'</button>'+
					    		                '</div>'
				    }else{
				        singleOrderConfirmHTML = '<div class="personalcenter_option" style="float:left">'+
				        						      '<span>${message("shop.member.order.confirm.hadReceive.tips")}</span>'+
				        						  '</div>';
				    }
		    		var product = orderItem.product;
		    		var brand = product.brand;
		    		var isMarketable = product.isMarketable
		    		var brandId = '';
		    		if(brand){
		    		    brandId = brand.id;
		    		}
		    		var productCategory = product.productCategory;
		    		var productCategoryId = '';
		    		if(productCategory){
		    		    productCategoryId = productCategory.id;
		    		}
		    		var productOptionHtml ='<div class="product-option">'+
								           '<a target="_blank" href="'+preschoolEdu.base+product.path+'?brandId='+brandId+'&productCategoryId='+productCategoryId+'&productId='+product.id+'">'+
							                 '${message("shop.personalcenter.buyAgain")}'+
							               '</a>'+
							            '</div>'
		    		if(!isMarketable){
		    		    productOptionHtml ='<div class="product-option">'+
							                 '${message("shop.personalcenter.offSale")}'+
							            '</div>'
		    		}
		            orderItemHTML += '<div class="order-single-info orderitem_single">'+
								        '<div class="order-single-info single_info">'+ 
								            '<div class="single_info_desc">'+
								                '<p>'+orderItem.name+'</p>'+
								                '<span>'+orderItem.sn+'</span>'+
								            '</div>'+
								        '</div>'+
								        '<div class="order-single-info single_color">'+
								            '<span>'+orderItem.product.colorVal+'</span>'+
								        '</div>'+
								        '<div class="order-single-info single_size" >'+
								            '<span>'+orderItem.product.sizeVal+'</span>'+
								        '</div>'+
								        '<div class="order-single-info single_price">'+
								            '<span>'+currency(product.price,true)+'</span>'+
								        '</div>'+
								        '<div class="order-single-info single_quantity" style="width:60px;">'+
								            '<span>'+orderItem.quantity+'</span>'+
								        '</div>'+
								        '<div class="order-single-info single_quantity">'+
								            '<span>'+orderItem.returnQuantity+'</span>'+
								        '</div>'+
								        '<div class="order-single-info single_totalPrice">'+
								            '<span>'+currency(orderItem.subtotal,true)+'</span>'+
								        '</div>'+
								        productOptionHtml+
								    '</div>';
		        }
		        $(".order-list").html(orderItemHTML);
		    }else{
		        $("#order_item_emptyshow").css({'display':'block'});
		    }
		}
    });
}

function modifyDeliveryAddress(receiverId){
    showMask();
    $("#pop_title_receiver").text("${message("shop.orderDetail.modifyReceiptAddress")}");
    $("#modifyReceiverSubmit span").text("${message("shop.orderDetail.modifyReceiverSubmit")}");
    var $modify_id = $("#modify_id");
    var $modify_consignee = $("#modify_consignee");
    var $receiverConsignee = $("#consignee_" + receiverId);
    var $modify_orderAreaId = $("#modify_orderAreaId");
    var $area_id = $("#areadId_" + receiverId);
    var $tree_path = $("#tree_path_" + receiverId);
    var $modify_address = $("#modify_address");
    var $detailAddress = $("#address_" + receiverId);
    var $modify_zipCode = $("#modify_zipCode");
    var $receiverZipCode = $("#zipCode_" + receiverId);
    var $modify_phone = $("#modify_phone");
    var $receiverPhone = $("#phone_" + receiverId);
    var $modifyOrderAddressPanel = $("#modifyOrderAddressPanel");
    var $idDefault = $("#idDefault_" + receiverId);
    var $isDefaultModify = $("#isDefault_Modify");
    var $isDefaultYes_lable = $("#isDefaultYes_lable");
    var $isDefaultNo_lable = $("#isDefaultNo_lable");
    $modify_id.val(receiverId);
    $modify_consignee.val($receiverConsignee.text());
    $modify_orderAreaId.val($area_id.val());
    $modify_orderAreaId.attr('treePath',$tree_path.val());
    $modify_address.val($detailAddress.text());
    $modify_zipCode.val($receiverZipCode.text());
    $modify_phone.val($receiverPhone.text());
    $modifyOrderAddressPanel.show();
    $modify_orderAreaId.lSelect({
		    url: preschoolEdu.base+"/common/area.ct"
	});
	if($idDefault.val()){
	    if($isDefaultYes_lable.hasClass("unchecked")){
	        $isDefaultYes_lable.removeClass("unchecked");
	        $isDefaultYes_lable.addClass("checked");
	        $("#isDefaul").val("true");
	        $isDefaultNo_lable.removeClass("checked");
	    }
	}else{
	    if($isDefaultNo_lable.hasClass("unchecked")){
	        $isDefaultNo_lable.removeClass("unchecked");
	        $isDefaultNo_lable.addClass("checked");
	        $("#isDefaul").val("false");
	        $isDefaultYes_lable.removeClass("checked");
	    }
	}
	optionType = "modify";
	verticalCenterWin($modifyOrderAddressPanel);
}

function deleteReceiver(receiverId){
    var msg = "${message("shop.personal.receiver.delete.confirm")}";
    if (confirm(msg)==true){ 
        $.ajax({
            url: '${base}/member/receiver/delete_receiver.ct',
			type: "POST",
			data: {
			    id:receiverId
			},
			dataType: "json",
			cache: false,
			beforeSend: function() {
				
			},
			success: function(data) {
				if (data.message.type == "success") {
					location.reload(true);
				} else {
					$.message("warn",data.message.content);
				}
			},
			complete: function() {
				
			}
        });
    }else{ 
        return false; 
    } 
}
//删除收藏商品
function deleteFavorite(){
	var id_array=new Array(); 
	var idstr; 
	$('input[name="ids"]:checked').each(function(){  
	   id_array.push($(this).val());//向数组中添加元素  
	   idstr=id_array.join(',');
	}); 
	if(!idstr){
	   $.message('warn',"${message("shop.personalCenter.selectNull")}");
	   return false;
	}
    $.ajax({
			url: "${base}/member/favorite/delete.ct",
			type: "GET",
			data: 
			{
				ids:idstr
			},
			dataType: "json",
			cache: false,
			success: function(message) {
			  if (message.type == "success") {
			        $.message('warn',"${message("shop.personalCenter.deleteSuccess")}");
					location.reload(true);
				} else {
					$.message(message);
				}
			}
		});
}
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="rightWrapper">
	<div class="container">
	    <div class="personaltop">
			<span>${message("shop.member.index")}</span>
		</div>
	    <!-- 头部导航 -->
		<div id="personalCenterNav" class="personalCenterNav">
            <a href="#profile" class="">${message("shop.personalCenter.profile")}</a>
            <a href="#vip" class="active">${message("shop.personalCenter.vipCard")}</a>
            <a href="#addFavorite" class="active">${message("shop.member.favorite.title")}</a> 
            <a href="#pastOrders" class="">${message("shop.personalCenter.pastOrders")}</a>
            <a href="#deliveryAddressManage" class="active">${message("shop.personalCenter.deliveryAddressManage")}</a>
        </div>
        <!-- 账户资料 -->
        <div id="profile" class="personalCenterProfile">
                <h2>${message("shop.personalCenter.profile")}</h2>
                <span>${message("shop.personalcenter.dear")} ${member.name}，${message("shop.personalCenter.personalCenterProfile")}
                <a id="modifyPersonalInfo" href="javascript:;" class="acctButton">${message("shop.personalCenter.modifyButton")}</a>
                ${message("shop.personalCenter.button")}</span>
                <p></p>
                <div id="personalProfile" class="profile" style="height: 260px;">
                    <h4>${message("shop.personalCenter.profile")}</h4>
			      <ul>
					<li class="personalControl" style="width: 139px;float: left;">
						<label class="personalLabelUp" for="userName">
							${message("shop.personalCenter.userName")}
						</label>
						<span id="gender"></span>
						<span id="userName">
						    [#if member.name ] 
							    ${member.name}
							[#else] 
							   -
							[/#if] 
						</span>					
					</li>
					<li class="personalControl" >
						<label class="personalLabelUp" for="userHeight">
							${message("shop.personalCenter.height")}
						</label>
						<span id="userHeight">
						    [#if member.height ] 
							    ${member.height}(cm)
							[#else] 
							   00
							[/#if]
						</span>					
					</li>
					<li class="personalControl" style="width: 139px;float: left;">
						<label class="personalLabelUp" for="password">
							${message("shop.personalCenter.passWord")}
						</label>
						<span id="password">
						  ******
						</span>	
					</li>
					<li class="personalControl">
						<label class="personalLabelUp" for="userWeight">
							${message("shop.personalCenter.weight")}
						</label>
						<span id="userWeight">
							[#if member.weight ] 
							    ${member.weight}(kg)
							[#else] 
							   00
							[/#if] 
						</span>	
					</li>
					<li class="personalControl" style="width: 139px;float: left;">
						<label class="personalLabelUp" for="country">
							${message("shop.personalCenter.country")}
						</label>
						<span id="country">
						    ${(member.country)!"-"}
						</span>	
					</li>
					<li class="personalControl" >
						<label class="personalLabelUp" for="measurements">
							${message("shop.personalCenter.measurements")}
						</label>
						<span id="measurements">
						    [#if member.bust ] 
						        ${member.bust}
							[#else] 
							   00
							[/#if] 
						    /
						    [#if member.waist ] 
						       ${member.waist}
							[#else] 
							   00
							[/#if] 
						    /
						    [#if member.hip ] 
						       ${member.hip}
							[#else] 
							   00
							[/#if] 
						</span>	
					</li>
					<li class="personalControl" style="width: 139px;float: left;">
						<label class="personalLabelUp" for="vipCardNumber">
							${message("shop.personalCenter.vipCardNumber")}
						</label>
						<span id="vipCardNumber">
						  [#if member.vipNumber=='']
						     -
						  [#else] 
						     ${member.vipNumber}
						  [/#if] 
						</span>
					</li>
					<li class="personalControl" >
						<label class="personalLabelUp" for="userAge">
							${message("shop.personalCenter.age")}
						</label>
						<span id="userAge">
						    [#if member.age ] 
						        ${member.age}
							[#else] 
							    －
							[/#if] 
						</span>	
					</li>
					<li class="personalControl" style="width: 139px;float: right;">
						<label class="personalLabelUp" for="userOccupation" style="margin-left: 8px;">
							${message("shop.personalCenter.occupation")}
						</label>
						<span id="userOccupation" style="margin-left: 8px;">
						    [#if member.occupation.name ] 
						        ${member.occupation.name}
							[#else] 
							    －
							[/#if] 
						</span>	
					</li>
				</ul>
                </div>
                
                <div id="personalContact" class="contact">
                     <h4>${message("shop.personalCenter.personalContact")}</h4>
                     <ul>
						<li class="personalControl">
							<label class="personalLabelUp" for="email">
								${message("shop.personalCenter.email")}
							</label>
							<span id="email">
							   ${member.email}
							</span>					
						</li>
						<li class="personalControl">
							<label class="personalLabelUp" for="phone">
								${message("shop.personalCenter.phone")}
							</label>
							<span id="phone">
							    ${member.mobile}
							</span>	
						</li>
						<li class="personalControl">
							<label class="personalLabelUp" for="language">
								${message("shop.personalCenter.language")}
							</label>
							<span id="language">
							   ${(member.languagePreference.languageName)!"-"}
							</span>	
						</li>
					</ul>
                </div>
        </div>
        <!-- start贵宾卡 -->
        <div id="vip" class="personalCenterVip">
             <h2>${message("shop.personalCenter.vipCard")}</h2>
             <h6>
                <a id="bindVipCardBtn" href="javascript:;" class="acctButton">${message("shop.personalCenter.bindVipCardBtn")}</a>
             </h6>
             <div class="vipProfile" >
                <h4>${message("shop.personalCenter.vipProfile")}</h4>
                <ul>
                    <li class="personalControl">
						<label class="personalLabelUp" for="vipGrade">
							${message("shop.personalCenter.vipGrade")}
						</label>
						<span id="vipGrade">
						  [#if member.memberRank.name ] 
						     ${member.memberRank.name}
						  [#else] 
						   －
						  [/#if] 
						</span>
					</li>
					<li class="personalControl">
					   <label class="personalLabelUp" for="vipNumber">
							${message("shop.personalCenter.vipCardNumber")}
						</label>
						<span id="vipNumber">
						  [#if member.vipNumber ] 
						     ${member.vipNumber}
						  [#else] 
						   －
						  [/#if] 
						</span>	
					</li>
					<li class="personalControl">
					   <label class="personalLabelUp" for="vipDiscount">
							${message("shop.personalCenter.vipDiscount")}
						</label>
						<span id="vipDiscount">
						   [#if member.memberRank.scale != 1] 
						    ${member.memberRank.scale * 10} ${message("shop.personalCenter.discount")}
						   [#else] 
						   －
						   [/#if] 
						</span>
					</li>
				</ul>
             </div>
             <div class="pointsRecord" >
                    <h4>${message("shop.personalCenter.pointsRecord")}</h4>
                    <ul>
                    <li class="personalControl">
                        <label class="vipLabelUp" for="pointsRecord">
							${message("shop.personalCenter.pointsRecord1")}
						</label>
						<label id="pointsRecord">
						    ${member.point}
						</label>
					</li>
					</ul>
             </div>
             <div class="personalCoupon">
                 <h4>${message("优惠券管理")}</h4>
                 <ul>
					<li class="personalControl">
						<a href="${base}/member/coupon_code/list.ct" target="_blank" class="acctButton">我的优惠券</a>					
					</li>
					<li class="personalControl">
						<a href="${base}/member/coupon_code/exchange.ct" target="_blank" class="acctButton">兑换/领取优惠券</a>	
					</li>
				</ul>
             </div>
             <div class="pointsRule">
             <!--
                 <span>${message("shop.personalCenter.pointsRule")}</span>
                  <ul class="pointsRuleStep">
                     <img src="${base}/resources/shop/images/vip_step.jpg" style="float: left;"/>
				 </ul>
				  <ul class="pointsRuleInfo">
                     <li class="personalControl">
                        <label class="personalLabelUp extra" for="accumulatedBonus">
							${message("shop.personalCenter.accumulatedBonus")}
						</label>
						<span id="accumulatedBonus" style="float: none;">
							 ${message("shop.personalCenter.accumulatedBonus1")}
						</span>
                     </li>
                     <li class="personalControl">
                        <label class="personalLabelUp extra" for="conversionBonus">
							${message("shop.personalCenter.conversionBonus")}
						</label>
						<span id="conversionBonus" style="float: none;">
							${message("shop.personalCenter.conversionBonus1")}<br />${message("shop.personalCenter.conversionBonus2")}
						</span>
                     </li>
                     <li class="personalControl">
                        <label class="personalLabelUp extra" for="integralGiftCertificatesRule">
							${message("shop.personalCenter.integralGiftCertificatesRule")}
						</label>
						<span id="integralGiftCertificatesRule" style="line-height: 20px;float: none;">
							${message("shop.personalCenter.CertificatesRule1")}<br />
							${message("shop.personalCenter.CertificatesRule2")}<br />
							${message("shop.personalCenter.CertificatesRule3")}<br />
							${message("shop.personalCenter.CertificatesRule4")}<br />
							${message("shop.personalCenter.CertificatesRule5")}<br />
							${message("shop.personalCenter.CertificatesRule6")}<br />
							${message("shop.personalCenter.CertificatesRule7")}<br />
							${message("shop.personalCenter.CertificatesRule8")}<br />
							    ${message("shop.personalCenter.CertificatesRule81")}<br />
							    ${message("shop.personalCenter.CertificatesRule82")}<br />
							${message("shop.personalCenter.CertificatesRule9")}<br />
							<img src="${base}/resources/shop/images/shop_zone.png" style="width:100%">
							${message("shop.personalCenter.CertificatesRule10")}
						</span>
                     </li>
                     <li class="personalControl">
                        <label class="personalLabelUp extra" for="accumulatedBonus">
							${message("shop.personalCenter.credits")}
						</label>
						<span id="accumulatedBonus" style="float: none;">
							 ${message("shop.personalCenter.published")}
						</span>
                     </li>
				 </ul>-->
			 </div>
        </div>
        <!-- end 贵宾卡 -->
        <!-- 收藏商品 -->
         <div id="addFavorite" class="pastOrders">
            <h2>${message("shop.member.favorite.title")}</h2>
            <div style="margin-left: 35px;" class="delteProdcutList">
            </div>
            <div style="float: left;margin-bottom: 22px;" class="favProdcutList">
            </div>
        </div>
         <div style="float: right;font-size: 12px;">
				<span class="span-nav-next"></span>
				<span class="span-nav-prev disabled"></span>
				<span class="table-nav-page-info">
					<span class="table-nav-page-text">
						${message("shop.personalcenter.orderlist.no1")}
					</span>
					<span class="span-nav-current-page"></span>
					<span class="table-nav-of">
						${message("shop.personalcenter.orderlist.no2")} / ${message("shop.personalcenter.orderlist.no3")}
					</span>
					<span class="span-nav-total-pages"></span>
					<span>${message("shop.personalcenter.orderlist.no2")}</span>
				</span>
		</div>
        <!-- end 收藏商品 -->
        <!--start 过往订单 -->
        <div id="pastOrders" class="pastOrders">
            <h2>${message("shop.personalCenter.pastOrders")}</h2>
            <div class="center">
	             <div class="radio-group" id="ordersConditionsRadioGroup" style="margin: 5px;margin-left:22px;display: inline-flex;">
					<div class="radio-group-panel">
					    <input name="ordersConditions" type="radio" class="signin-radio" checked="checked" value="s"  >
						<label class="checked" name="allPastOrders" id="allPastOrders" for="allPastOrders">${message("shop.personalCenter.allPastOrders")}</label>
					</div>
					<div class="radio-group-panel">
						<input type="radio" name="ordersConditions" class="signin-radio"  value="s"/> 
						<label class="unchecked" name="shippedList" id="shippedList" for="shippedList">${message("shop.personalCenter.shippedList")}</label>
					</div>
					<div class="radio-group-panel">
						<input type="radio" name="ordersConditions" class="signin-radio"  value="s"/> 
						<label class="unchecked" id="unShippedList" name="unShippedList" for="unShippedList">${message("shop.personalCenter.unShippedList")}</label>
					</div>
				</div>
			 </div>
        </div>
        <div class="center">
			 <ul class="prodcutItemList">
			</ul>
			<div class="pagination-controls">
				<span class="table-nav-next"></span>
				<span class="table-nav-prev disabled"></span>
				<span class="table-nav-page-info">
					<span class="table-nav-page-text">
						${message("shop.personalcenter.orderlist.no1")}
					</span>
					<span class="table-nav-current-page"></span>
					<span class="table-nav-of">
						${message("shop.personalcenter.orderlist.no2")} / ${message("shop.personalcenter.orderlist.no3")}
					</span>
					<span class="table-nav-total-pages"></span>
					<span>${message("shop.personalcenter.orderlist.no2")}</span>
				</span>
			</div>
		</div>
		 <!--end 过往订单 -->

		<!--start 收货地址管理 -->
        <div id="deliveryAddressManage" class="deliveryAddressManage" style="overflow: hidden;" >
            <h2>${message("shop.personalCenter.deliveryAddressManage")}</h2>
            <div class="deliveryAddressTitle"><span></span></div>
            [#list member.receivers as receiver]
            <div class="deliveryAddressMain">
                <div class="deliveryAddressInfo"[#if receiver.isDefault || receiver_index == 0]style="border: 1px dotted #000000;"[/#if]>
                    <div class="deliveryAddressDesc"><span>${message("shop.personalCenter.deliveryAddressTitle")}<span></div>
                    <p id="zipCode_${receiver.id}">${receiver.zipCode}</p>
                    [#if receiver.area.parent?? && receiver.area.parent.parent??]
                        <p>${receiver.area.parent.parent.name}</p>
                    [/#if]
                    [#if receiver.area.parent??]
                        <p>${receiver.area.parent.name}</p>
                    [/#if]
                    <p>${receiver.area.name}</p>
                    <input type="hidden" id="areadId_${receiver.id}" value="${receiver.area.id}"/>
                    <input type="hidden" id="tree_path_${receiver.id}" value="${(receiver.area.treePath)!}"/>
                    <input type="hidden" id="idDefault_${receiver.id}" value="${(receiver.isDefault)!}"/>
                    <p id="address_${receiver.id}">${receiver.address}</p>
                    <p id="consignee_${receiver.id}">${receiver.consignee}</p>
                    <p id="phone_${receiver.id}">${receiver.phone}</p>
                </div>
                <div class="deliveryAddressOption">
                    <div id="deliveryAddressDelete" class="deliveryAddressBtn" onclick="deleteReceiver('${receiver.id}')">
                        <span>${message("shop.personalcenter.deliveryAddressDelete")}</span>
                   </div>
                    <div id="deliveryAddressModify" class="deliveryAddressBtn" onclick="modifyDeliveryAddress('${receiver.id}')">
                        <span>${message("shop.personalcenter.deliveryAddressModify")}</span>
                   </div>
                </div>
            </div>
            [/#list]
            <div class="deliveryAddressMain" style="border:0px;">
                <div class="deliveryAddressInfo" style="border:0px;">
                    <div class="deliveryAddressAddDesc"><span>${message("shop.personalCenter.deliveryAddressTitle")}<span></div>
                    <div id="deliveryAddressAdd" class="deliveryAddressAddBtn">
                        <span>${message("shop.personalcenter.deliveryAddressAdd")}</span>
                   </div>
                </div>
                <div class="deliveryAddressOption">
                   <div id="deliveryAddressDelete" class="btnDisable">
                        <span>${message("shop.personalcenter.deliveryAddressDelete")}</span>
                   </div>
                    <div class="btnDisable">
                        <span>${message("shop.personalcenter.deliveryAddressModify")}</span>
                   </div>
                </div>
            </div>
        </div>
	</div>
	<!--end 收货地址管理 -->
	
	 <!-- start 修改收货地址 -->
	<div id="modifyOrderAddressPanel" class="modal" style="zoom: 1; opacity: 1; top: 15%; left: 35%; z-index: 1002; display: none">
		<div class="modalContent">
		    <div class="modalTitle">
		         <h2 id="pop_title_receiver"></h2>
	        </div>
	        <form id="receiverFormModify" action="${base}/member/order/update_receiver.ct" method="post" novalidate="novalidate">
	        <table class="newReceiver" style="display: table;">
	                <input type="hidden" id="modify_id" name="modify_id"/>
					<tbody><tr>
						<th width="100">
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptPerson")}
						</th>
						<td>
							<input type="text" id="modify_consignee" name="consignee" class="inputText" maxlength="200"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptArea")}
						</th>
						<td style="width: 183px;">
							<span class="fieldSet" id="receiverModifyAreaSpan">
								<input type="hidden" id="modify_orderAreaId" name="modify_orderAreaId" treePath=""/>
							</span>
						</td>
					</tr>
					<tr>
						<th>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptAdress")}
						</th>
						<td>
							<input type="text" id="modify_address" name="address" class="inputText" maxlength="500"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptZipCode")}
						</th>
						<td>
							<input type="text" id="modify_zipCode" name="zipCode" class="inputText" maxlength="6"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="requiredField">*</span>${message("shop.orderDetail.receiptphoneAndMobile")}
						</th>
						<td>
							<input type="text" id="modify_phone" name="phone" class="inputText" maxlength="11"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="requiredField"></span>${message("shop.personalcenter.deliveryAddressIsDefault")}
						</th>
						<td>
						    <input type="hidden" name="isDefault" id="isDefault_Modify" value="false"/>
							<div class="radio-group-panel isDefaultYes">
								<input name="isDefaultInput" type="radio" class="signin-radio" id="isDefault_radio">
								<label id="isDefaultYes_lable" class="unchecked" name="isDefault_radio" for="isDefault_radio">${message("shop.personalcenter.deliveryAddressIsDefault.yes")}</label>
							</div>
							<div class="radio-group-panel isDefaultNo">
								<input name="isDefaultInput" type="radio" class="signin-radio" id="isDefault_radio">
								<label id="isDefaultNo_lable" class="unchecked" name="isDefault_radio" for="isDefault_radio">${message("shop.personalcenter.deliveryAddressIsDefault.no")}</label>
						    </div>
						</td>
					</tr>
					<tr>
						<th>
						</th>
						<td style="text-align: right;">
							<div class="requiredLegend">
				                <em class="required">${message("shop.info.acceptClauseMust")}</em>
			                </div>
						</td>
					</tr>
				</tbody>
				</table>
				<div class="actionsControls" style="text-align:center;margin:20px 0 28px">
                    <button type="submit" id="modifyReceiverSubmit" class="button butBlack">
                        <span>
                            ${message("shop.orderDetail.modifyReceiverSubmit")}
                        </span>
                    </button>
                     <button type="button" id="modifyReceiverCancle" class="button butBlack">
                        <span>
                            ${message("shop.orderDetail.newReceiverCancle")}
                        </span>
                    </button>
                </div>
               </form>
		</div>
		<!-- end 修改收货地址 -->
	
	</div>
	<!-- start 订单详情页 -->
	<div class="personalModal" id="orderDetailPanel">
	    <div id="orderdetialClose" class="modalClose">
		    <a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	    </div>
	    <div class="modalContent">
	        <h2>${message("shop.personalcenter.orderdetail.title")}</h2>
	        <div class="order_info">
	            <div class="order_reciver">
	                <div class="order_recivertitle"><p>${message("shop.member.receiver.list")}：</p></div>
	                 <div class="order_statusinfo">
	                   <ul>
	                     <li class="personalControl">
	                         <label for="receiver_consignee">${message("shop.order.consignee")}：</label> 
					         <span id="receiver_consignee"></span>
	                     </li>
	                     <li class="personalControl">
	                         <label for="receiver_phone">${message("shop.order.phone")}：</label> 
					         <span id="receiver_phone"></span>
	                     </li>
	                     <li class="personalControl">
	                         <label for="receiver_address">${message("shop.order.address")}：</label> 
					         <span id="receiver_address"></span>
	                     </li>
	                     <li class="personalControl">
					         <label for="receiver_zipCode">${message("shop.order.zipCode")}：</label>
					         <span id="receiver_zipCode"></span>
	                     </li>
	                   </ul>
		            </div>
	                <div class="order_sn">
	                   <label for="view_ordersn">${message("shop.orderDetail.orderPriviewNum")}</label>
	                   <span id="view_ordersn"></span>
	                </div>
	            </div>
	            <div class="order_status">
	                 <ul>
	                     <li class="personalControl">
	                         <label for="userInfoOrderStatus">${message("Order.orderStatus")}：</label> 
					         <span id="userInfoOrderStatus"></span>
	                     </li>
	                     <li class="personalControl">
	                         <label for="userInfoPaymentStatus">${message("Order.paymentStatus")}：</label> 
					         <span id="userInfoPaymentStatus"></span>
	                     </li>
	                     <li class="personalControl">
	                         <label for="userInfoShippingStatus">${message("Order.shippingStatus")}：</label> 
					         <span id="userInfoShippingStatus"></span>
	                     </li>
	                      <li class="personalControl">
	                         <label for="orderAmountPayable">${message("shop.personalcenter.totalOrderNum")}：</label> 
					         <span id="orderAmountPayable"></span>
	                     </li>
	                   </ul>
	                   <div id="orderOperation">
	                   </div>
	            </div>
	        </div>
	         <div class="modalInfo">
	             <div id="nonDeliveryPanel" class="infoLogin" style="zoom: 1; opacity: 1;width:100%">
					<div class="nonDelivery-header">
					    <div class="product-info" style="line-height:25px;"><span>${message("shop.include.productInfo")}</span></div>
					    <div class="product-color" style="line-height:25px;width: 140px;"><span>${message("shop.include.productColor")}</span></div>
					    <div class="product-size" style="line-height:25px;"><span>${message("shop.include.productSize")}</span></div>
					    <div class="product-price" style="line-height:25px;"><span>${message("shop.include.productPrice")}</span></div>
					    <div class="product-count" style="line-height:25px;width:60px;"><span>${message("shop.include.productCount")}</span></div>
					    <div class="product-count" style="line-height:25px;width:60px;"><span>${message("Order.returnQuantity")}</span></div>
					    <div class="product-priceTotal" style="line-height:25px;width:120px;"><span>${message("shop.include.productPriceTotal")}</span></div>
					    <div class="product-option" style="line-height: 25px;"><span>${message("shop.include.productOption")}</span></div>
						</div>
					</div>
					<div class="order-list" style="height:110px;">
					    <div id="order_item_emptyshow" class="empty-show" style="line-height:200px;display:none">
					        ${message("shop.order.empty")}
					    </div>
					</div>
					<div style="width: 800px;height: 50px"></div>
				</div>
	         </div>
	    </div>
	</div>
	<!-- end 订单详情页 -->
	
	<!-- start 个人信息修改 -->
	<div class="personalModal" id="personalModifyPanel">
	     <div class="modalClose" id="personalModifyClose">
		    <a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	    </div>
	    <div class="modalContent">
	        <div class="modalTitle4personModify">
	            <h2 id="personal_modify_title">${message("shop.personalcenter.modify.title")}</h2>
	        </div>
	        <div class="personal_modify_info">
	            <div class="modalInfo">
	             <!-- start profile -->
	             <h3 class="titleSection">
					${message("shop.personalCenter.modifyUserInfoTitle")}
				</h3>
				 <p>
					${message("shop.personalCenter.modifyUserInfoTitleTips")}
				</p>
	             <form id="personalModifyForm" action="modifyProfile.ct" method="post">
	                <table class="personal_info">
	                    <tbody>
	                        <tr>
	                            <td><span>${message("shop.personalcenter.modify.nickname")}*</span></td>
	                            <td><input id="name" type="text" name="name" class="inputText"/></td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalcenter.modify.sex")}*</span></td>
	                            <td>
	                                <div class="radio-group-panel genderRadioMale">
											<input name="gender" type="radio" class="signin-radio" id="gender_radio">
											<label id="gender_male_lable" class="unchecked" name="gender_radio" for="gender_radio">${message("shop.personalcenter.modify.male")}</label>
								    </div>
								    <div class="radio-group-panel genderRadioFemal">
											<input name="gender" type="radio" class="signin-radio" id="gender_radio">
											<label id="gender_female_lable" class="unchecked" name="gender_radio" for="gender_radio">${message("shop.personalcenter.modify.female")}</label>
								    </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.modify.birthday")}</span></td>
	                            <td>
	                               <input id="birth" type="text" name="birth" class="inputText Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
	                               <em>${message("shop.member.birthTips")}</em>
	                            </td>
	                        </tr>
	                         <tr>
	                            <td>
	                                <span>${message("shop.personalCenter.height")}</span>
	                            </td>
	                            <td>
	                               <input id="height" type="text" name="height" class="inputText" maxlength="3"/>
	                               <em>(cm)</em>
	                            </td>
	                        </tr>
	                         <tr>
	                            <td><span>${message("shop.personalCenter.weight")}</span></td>
	                            <td>
	                                <input id="weight" type="text" name="weight" class="inputText" maxlength="4"/>
	                                <em>(kg)</em>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.bust")}</span></td>
	                            <td>
	                                <input id="bust" type="text" name="bust" class="inputText" maxlength="4"/>
	                                <em>(cm)</em>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.waist")}</span></td>
	                            <td>
	                                <input id="waist" type="text" name="waist" class="inputText" maxlength="4"/>
	                                <em>(cm)</em>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.hip")}</span></td>
	                            <td>
	                                <input id="hip" type="text" name="hip" class="inputText" maxlength="4"/>
	                                <em>(cm)</em>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                               <nobr>${message("shop.personalCenter.occupation")}</nobr>
	                            </td>
	                            <td>
	                                <span class="fieldSet">
										<input type="hidden" id="occupationId" name="occupation" value="${(member.occupation.id)!}" treePath="${(member.occupation.treePath)!}"/>
									</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                               <span>${message("shop.personalCenter.country")}</span>
	                            </td>
	                            <td>
	                                <script>
	                                    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){
											 $("#liveCountrySpan").html(remote_ip_info.country);
											 $("#liveCountry").val(remote_ip_info.country);
										});
	                                </script>
	                                <span id="liveCountrySpan"></span>
									<input type="hidden" name="country" id="liveCountry" />
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                               <nobr>${message("shop.personalCenter.liveLocation")}</nobr>
	                            </td>
	                            <td>
	                                <span class="fieldSet">
										<input type="hidden" id="liveLocationAreaId" name="liveLocation" value="${(member.area.id)!}" treePath="${(member.area.treePath)!}"/>
									</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                               <span>${message("shop.personalCenter.language")}</span>
	                            </td>
	                            <td>
	                                <select name="languagePreferenceId" id="languagePreferenceId" class="defaultSelect">
	                                    
									</select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td></td>
	                            <td style="text-align: right;">
	                                <em>${message("shop.info.acceptClauseMust")}</em>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="personalcenterModifyBtn">
	                    <div class="actionsControls buttonLeft">
                        <button type="button" id="personalcenterModifyBtn" class="button butBlack">
                            <span>${message("shop.personalcenter.modify.submit")}</span>
                        </button>
	                   </div>
	                </div>
	              </form>
	              <!-- end profile  -->
	              <!-- start 邮件Modify -->
		            <h3 class="titleSection">
							${message("shop.personalCenter.modifyEmail")}
					</h3>
					 <p>
						${message("shop.personalCenter.modifyEmailTips")}
					</p>
				 <p>
				     <strong>${message("shop.personalCenter.modifyCurrentEmail")}</strong> 
				     <span id="currentUserEmail"></span>
				 </p>
	              <form id="personalModifyEmailForm" action="modifyUserEmail.ct" method="post">
	                <table class="personal_info">
	                    <tbody>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.currentPwd")}*</span></td>
	                            <td>
	                                <input id="modifyEmailPwd" type="password" name="password" class="inputText"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.newEmail")}*</span></td>
	                            <td><input id="newEmail" name="newEmail" type="text"  class="inputText"/></td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.newAgainEmail")}*</span></td>
	                            <td><input id="newAgainEmail" name="newAgainEmail"  type="text"  class="inputText"/></td>
	                        </tr>
	                        <tr>
	                            <td></td>
	                            <td style="text-align: right;">
	                                <em>${message("shop.info.acceptClauseMust")}</em>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="personalcenterModifyBtn">
	                    <div class="actionsControls buttonLeft">
                        <button type="button" id="personalCenterModifyEmailBtn" class="button butBlack">
                            <span>${message("shop.personalcenter.modify.submit")}</span>
                        </button>
	                   </div>
	                </div>
	              </form>
	              <!-- end 邮件Modify-->
	              
	              <!-- start 邮件mobile -->
		            <h3 class="titleSection">
						${message("shop.personalCenter.modifyMobile")}
					</h3>
					 <p>
						${message("shop.personalCenter.modifyMobileTips")}
					</p>
				 <p>
				     <strong>${message("shop.personalCenter.modifyCurrentMobile")}</strong> 
				     <span id="currentUserMobile"></span>
				 </p>
	              <form id="personalModifyMobileForm" action="modifyUserMobile.ct" method="post">
	                <table class="personal_info">
	                    <tbody>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.currentPwd")}*</span></td>
	                            <td>
	                                <input id="modifyMobilePwd" type="password" name="password" class="inputText"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.newMobile")}*</span></td>
	                            <td><input id="newMobile" name="newMobile" type="text"  class="inputText"/></td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalCenter.newAgainMobile")}*</span></td>
	                            <td><input id="newAgainMobile" name="newAgainMobile"  type="text"  class="inputText"/></td>
	                        </tr>
	                        <tr>
	                            <td></td>
	                            <td style="text-align: right;">
	                                <em>${message("shop.info.acceptClauseMust")}</em>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="personalcenterModifyBtn">
	                    <div class="actionsControls buttonLeft">
                        <button type="button" id="personalCenterModifyMobileBtn" class="button butBlack">
                            <span>${message("shop.personalcenter.modify.submit")}</span>
                        </button>
	                   </div>
	                </div>
	              </form>
	              <!-- end 邮件Modify-->
	              
	              
	              <!-- start pwd modify -->
	              <h3 class="titleSection">${message("shop.personalCenter.modifyPwdTitle")}</h3>
	              <p>${message("shop.personalCenter.modifyPwdTitleTips")}</p>
	               <form id="personalModifyPwdForm" action="modifyUserPwd.ct" method="post">
	                <table class="personal_info">
	                    <tbody>
	                        <tr>
	                            <td><span>${message("shop.personalcenter.modify.originpassword")}*</span></td>
	                            <td>
	                                <input id="originpassword4modify" name="password" type="password"  class="inputText"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalcenter.modify.newpassword")}*</span></td>
	                            <td><input id="newpassword" name="newpassword" type="password"  class="inputText" /></td>
	                        </tr>
	                        <tr>
	                            <td><span>${message("shop.personalcenter.modify.newpasswordconfirm")}*</span></td>
	                            <td><input id="newpasswordconfirm" name="newpasswordconfirm" type="password" class="inputText" /></td>
	                        </tr>
	                        <tr>
	                            <td></td>
	                            <td style="text-align: right;">
	                                <em>${message("shop.info.acceptClauseMust")}</em>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="personalcenterModifyBtn">
	                    <div class="actionsControls buttonLeft">
                        <button type="button" id="personalCenterModifyPwdBtn" class="button butBlack">
                            <span>${message("shop.personalcenter.modify.submit")}</span>
                        </button>
	                   </div>
	                </div>
	              </form>
	              <!-- end pwd modify -->
	            </div>
	        </div>
	    </div>
	    </div>
	    <!-- end 个人信息修改 -->
	   
	<!-- start bind vipCard-->
	<div class="personalModal" id="vipCardBindPanel">
	     <div class="modalClose" id="vipCardBindClose">
		    <a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	    </div>
	    <div class="modalContent">
	        <div class="modalTitle4personModify">
	            <h2 id="personal_modify_title">${message("shop.personalCenter.vipCard.title")}</h2>
	        </div>
	        <div class="personalVipCard">
	            <div class="modalInfo">
	             <h3>
				</h3>
				<p style="max-width: 100%; ">
					${message("shop.include.vipDescription")}
				</p>
	             <form id="vipCardBindForm" action="modifyProfile.ct" method="post">
	                <table class="personal_info">
	                 <tr>
					    <th><label for="userVipUserName">${message("shop.include.vipUserName")}*</label></th>
					    <td><input class="inputText" type="text" id="userVipUserName"  maxlength="50" autocomplete="off"/></td>
					  </tr>
					   <tr>
					    <th><label for="userVipMobile">${message("shop.include.vipMobile")}*</label></th>
					    <td><input class="inputText"  type="text" id="userVipMobile" maxlength="50" autocomplete="off"/> </td>
					  </tr>
					  <tr>
					    <th><label for="userVipCardNumber">${message("shop.include.vipCardnumber")}*</label></th>
					    <td><input class="inputText" type="text" id="userVipCardNumber" maxlength="50" autocomplete="off"/></td>
					  </tr>
					  <tr>
                        <td colspan="2" style="text-align: right;">
                            <em>${message("shop.info.acceptClauseMust")}</em>
                        </td>
                      </tr>
                       <tr>
                        <td colspan="2" style="text-align: center;">
                         <div class="actionsControls">
			                    <button type="button" id="vipCardBindBtnSubmit" class="button butBlack">
			                        <span>${message("shop.personalCenter.vipCard.bind")}</span>
			                    </button>
		                   </div>
	                    </td>
                      </tr>
					</table>
	              </form>
	            </div>
	        </div>
	   </div>
	    <!-- end bind vipCard -->
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>
