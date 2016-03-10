<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.resetPassword.title")}[#if systemShowPowered] -图书智能管理推荐系统[/#if]</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/reset_password.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
<script type="text/javascript">
$(document).ready(function(){
    $("#resetPasswordBtn").click(function(){
        submitReset();
    });
});

function submitReset(){
    var $itxLoading = $("#itxLoading");
    $.ajax({
            url: preschoolEdu.base+"/common/public_key.ct",
			type: "GET",
			dataType: "json",
			cache: false,
			success: function(data){
			    var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
				var enNewPassword = hex2b64(rsaKey.encrypt($("#new_password").val()));
				if($("#new_password").val().length < 1 || $("#new_password").val().length > 117){
				    $.message("warn",${message("shop.common.invalid")});
				    $("#new_password").focus();
				    return;
				}
				if($("#new_password").val() != $("#new_repeat_password").val()){
				    $.message("warn",message("shop.validate.resetpassword.error"));
				    $("#new_repeat_password").focus();
				    return;
				}else{
				    $.ajax({
				        url: $("#resetPasswordForm").attr("action"),
						type: "POST",
						dataType: "json",
						cache: false,
						data: {
							uid: $("#userid").val(),
							newPassword:enNewPassword,
							key:$("#key4user").val()
						},
						beforeSend: function(){
							$itxLoading.show();
 						},
 						success: function(message) {
							$itxLoading.hide();
							if (message.type == "success") {
								$.message("success","${message("shop.resetpassword.success")}");
								setTimeout(function(){
								    window.location.href = preschoolEdu.base + "/";
								},2000);
							} else {
								$.message('error',message.content);
                                return;
							}
						}
				    });
				}
			}
        });
}
</script>
	<div class="rightWrapper">
		<div class="container">
			<div class="resetPassword_title">
			    <div class="mod" style="height:0px;"></div>
			    <div class="mod">
			        <h2 class="mod_h2" style="padding-bottom: 20px;text-align: left;font-size: 16px;">
						${message("shop.resetPassword.title")}
					</h2>
					<div class="title_desc">
			            <p style="padding-bottom:10px;">${message("shop.resetPassword.titleTips1")}</p>
			            <p style="padding-bottom:20px;">${message("shop.resetPassword.titleTips2")}</p>
			        </div>
			    </div>
			</div>
			<div class="mod">
			    <p class="mod_p">
					<strong>${message("shop.resetPassword.userName")}</strong> 
					<span>
						${member.name}
					</span> <br/>
					<strong>${message("shop.resetPassword.address")}</strong>
					<span>
						${member.address}
					</span> <br/>
					<strong>${message("shop.resetPassword.mobile")}</strong>
					<span>
						${member.mobile}
					</span> <br/>
					<strong>${message("shop.resetPassword.zipcode")}</strong>
					<span>
						${member.zipCode}
					</span> <br/>
						
			   </p>
			</div>
			<form id="resetPasswordForm" action="${base}/login/resetPasswordSubmit.ct" method="post">
			<input type="hidden" id="userid"  name="uid" value="${member.id}"/>
			<input type="hidden" id="key4user" name="key" value="${key}" />
			<div class="mod">
			    <div class="reset_info">
			        <div class="new_password">
			            <div class="new_password_title"><span>${message("shop.resetPassword.newPassword")}*</span></div>
			            <div class="new_password_value">
			                <input type="password" name="newPassword" id="new_password" class="new_input_password" name="newpassword"/>
			            </div>
			        </div>
			        <div class="new_password">
			            <div class="new_password_title"><span>${message("shop.resetPassword.repeatPassword")}*</span></div>
			            <div class="new_password_value">
			                <input type="password" id="new_repeat_password" class="new_input_password" name="repeatpassword"/>
			            </div>
			        </div>
			        <div class="requiredLegend">
						<em class="required">*</em> ${message("shop.resetPassword.requiredColumns")}
					</div>
					<div class="actions">
						<button id="resetPasswordBtn" name="resetPasswordBtn" type="button" class="button butBlack" style="height: 30px;line-height: 30px;">
							<span>
								${message("shop.resetPassword.button")}
							</span>
						</button>
					</div>
			    </div>
			</div>
			</form>
		</div>
	</div>

	[#include "/shop/include/footer.ftl" /]
</body>
</html>