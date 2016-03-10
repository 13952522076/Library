<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${message("console.main.title")} - 小书僮™智慧幼教管理平台</title>
    <meta name="keywords" content="小书僮™智慧幼教管理平台">
    <meta name="description" content="小书僮™智慧幼教管理平台">
    [#include "/console/include/resources.ftl" /]
    <script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
	<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	$().ready(function() {
		
		var $inputForm = $("#inputForm");
		var $browserButton = $("input.browserButton");
		var $smtpFromMail = $("#smtpFromMail");
		var $smtpHost = $("#smtpHost");
		var $smtpPort = $("#smtpPort");
		var $smtpUsername = $("#smtpUsername");
		var $smtpPassword = $("#smtpPassword");
		var $toMailWrap = $("#toMailWrap");
		var $toMail = $("#toMail");
		var $mailTest = $("#mailTest");
		var $mailTestStatus = $("#mailTestStatus");
		
		[@flash_message /]
		
		$browserButton.browser();
		
		
		$.validator.addMethod("compareLength", 
			function(value, element, param) {
				return this.optional(element) || $.trim(value) == "" || $.trim($(param).val()) == "" || parseFloat(value) >= parseFloat($(param).val());
			},
			"${message("console.setting.compareLength")}"
		);
		
		// 表单验证
		$inputForm.validate({
			rules: {
				siteName: {
					required:true,
					maxlength:50
				},
				siteUrl:{
					required:true
				},
				logo: "required",
				kineditorSite:"required",
				email: "email",
				address:{
					maxlength:200
				},
				phone:{
					pattern:/(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/
				},
				zipCode:{
					pattern:/^[1-9]\d{5}$/
				},
				siteCloseMessage: "required",
				usernameMinLength: {
					required: true,
					integer: true,
					min: 1,
					max: 117
				},
				usernameMaxLength: {
					required: true,
					integer: true,
					min: 1,
					max: 117,
					compareLength: "#usernameMinLength"
				},
				passwordMinLength: {
					required: true,
					integer: true,
					min: 1,
					max: 117
				},
				passwordMaxLength: {
					required: true,
					integer: true,
					min: 1,
					max: 117,
					compareLength: "#passwordMinLength"
				},
				registerPoint: {
					required: true,
					integer: true,
					min: 0
				},
				registerAgreement: "required",
				accountLockCount: {
					required: true,
					integer: true,
					min: 1
				},
				accountLockTime: {
					required: true,
					digits: true
				},
				safeKeyExpiryTime: {
					required: true,
					digits: true
				},
				uploadMaxSize: {
					required: true,
					digits: true
				},
				validateCodeMaxNumber:{
					required: true,
					digits: true,
					min: 1
				},
				initPassword:{
					required:true,
					rangelength: [3, 9]
				},
				imageUploadPath: "required",
				flashUploadPath: "required",
				mediaUploadPath: "required",
				fileUploadPath: "required",
				currencySign: "required",
				currencyUnit: "required",
				stockAlertCount: {
					required: true,
					digits: true
				},
				defaultPointScale: {
					required: true,
					min: 0,
					decimal: {
						integer: 3,
						fraction: 3
					}
				},
				defaultPointExchangeScale:{
				    required: true,
					min: 0,
					decimal: {
						integer: 3,
						fraction: 3
					}
				},
				taxRate: {
					required: true,
					min: 0,
					decimal: {
						integer: 3,
						fraction: 3
					}
				},
				cookiePath: "required",
				parentingCategoryLimit:{
					required: true,
					integer: true,
					min: 1,
					max: 10
				}
				
			}
		});
		
	});
	</script>
	<style>
	    .captchaTypes label{
	          margin-right: 10px;
	    }
	</style>
</head>
<body class="fixed-navigation">
	<div id="wrapper">
	  	<!-- start  导航 -->
       	[#include "/console/include/nav.ftl" /]
       	<!-- end 导航-->
	
	   	<div id="page-wrapper" class="gray-bg dashbard-1">
		   	<!-- start 头部 -->
	       	[#include "/console/include/header.ftl" /]
	       	<!-- end 头部-->
	       
	       	<!-- start 头部面包屑区域 -->
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2>${message("console.main.setting")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/main.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong> ${message("console.main.setting")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2"></div>
            </div>
            <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="row border-bottom white-bg-bak dashboard-header mainContent">
	            <div class="panel blank-panel">
                            <div class="panel-heading">
                                <div class="panel-options">
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a data-toggle="tab" onclick="changeNavHeight()"  href="edit.ct#settingBaseTab">
                                                ${message("console.setting.base")}
                                            </a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" onclick="changeNavHeight()" href="tabs_panels.html#settingRegisterSecurityTab">
                                                ${message("console.setting.registerSecurity")}
                                            </a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" onclick="changeNavHeight()" href="tabs_panels.html#settingOtherTab">
                                                ${message("console.setting.other")}
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <form id="inputForm" action="update.ct" method="post" enctype="multipart/form-data">
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div id="settingBaseTab" class="tab-pane active">
                                        <table class="table table-striped">
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.siteName")}:
												</th>
												<td>
													<input type="text" name="siteName" class="form-control" value="${setting.siteName}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.siteUrl")}:
												</th>
												<td>
													<input type="text" name="siteUrl" class="form-control" value="${setting.siteUrl}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("编辑器图片站点")}:
												</th>
												<td>
													<input type="text" name="kineditorSite" class="form-control" value="${setting.kineditorSite}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.logo")}:
												</th>
												<td>
													<span class="fieldSet">
														<input type="text" name="logo" class="form-control" value="${setting.logo}" maxlength="200" readOnly="true" />
														<input type="button" class="btn btn-primary browserButton" value="${message("console.browser.select")}" />
														<!--<a href="${setting.logo}" target="_blank">${message("console.common.view")}</a>-->
													</span>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("默认图片")}:
												</th>
												<td>
													<span class="fieldSet">
														<input type="text" name="defaultImage" class="form-control" value="${setting.defaultImage}" maxlength="200" readOnly="true" />
														<input type="button" class="btn btn-primary browserButton" value="${message("console.browser.select")}" />
														<!--<a href="${setting.logo}" target="_blank">${message("console.common.view")}</a>-->
													</span>
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.address")}:
												</th>
												<td>
													<input type="text" name="address" class="form-control" value="${setting.address}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.phone")}:
												</th>
												<td>
													<input type="text" name="phone" class="form-control" value="${setting.phone}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.zipCode")}:
												</th>
												<td>
													<input type="text" name="zipCode" class="form-control" value="${setting.zipCode}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.email")}:
												</th>
												<td>
													<input type="text" name="email" class="form-control" value="${setting.email}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.certtext")}:
												</th>
												<td>
													<input type="text" name="certtext" class="form-control" value="${setting.certtext}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.isSiteEnabled")}:
												</th>
												<td>
													<input type="checkbox" name="isSiteEnabled" value="true"[#if setting.isSiteEnabled] checked="checked"[/#if] />
													<input type="hidden" name="_isSiteEnabled" value="false" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.siteCloseMessage")}:
												</th>
												<td>
													<textarea name="siteCloseMessage" class="form-control">${setting.siteCloseMessage?html}</textarea>
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="settingRegisterSecurityTab" class="tab-pane">
                                        <table class="table table-striped">
											<tr>
												<th>
													${message("Setting.isRegisterEnabled")}:
												</th>
												<td>
													<input type="checkbox" name="isRegisterEnabled" value="true"[#if setting.isRegisterEnabled] checked="checked"[/#if] />
													<input type="hidden" name="_isRegisterEnabled" value="false" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.isDuplicateEmail")}:
												</th>
												<td>
													<input type="checkbox" name="isDuplicateEmail" value="true"[#if setting.isDuplicateEmail] checked="checked"[/#if] />
													<input type="hidden" name="_isDuplicateEmail" value="false" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.disabledUsername")}:
												</th>
												<td>
													<input type="text" name="disabledUsername" class="form-control" value="${setting.disabledUsername}" maxlength="200" title="${message("console.setting.disabledUsernameTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.usernameMinLength")}:
												</th>
												<td>
													<input type="text" id="usernameMinLength" name="usernameMinLength" class="form-control" value="${setting.usernameMinLength}" maxlength="3" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.usernameMaxLength")}:
												</th>
												<td>
													<input type="text" name="usernameMaxLength" class="form-control" value="${setting.usernameMaxLength}" maxlength="3" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.passwordMinLength")}:
												</th>
												<td>
													<input type="text" id="passwordMinLength" name="passwordMinLength" class="form-control" value="${setting.passwordMinLength}" maxlength="3" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.passwordMaxLength")}:
												</th>
												<td>
													<input type="text" name="passwordMaxLength" class="form-control" value="${setting.passwordMaxLength}" maxlength="3" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.registerPoint")}:
												</th>
												<td>
													<input type="text" name="registerPoint" class="form-control" value="${setting.registerPoint}" maxlength="9" />
												</td>
											</tr>
											<tr><!--初始密码-->
												<th>
													<span class="requiredField">*</span>${message("初始密码")}:
												</th>
												<td>
													<input type="text" name="initPassword" class="form-control" value="${setting.initPassword}" maxlength="9" />
												</td>
											</tr><!--初始密码-->
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.registerAgreement")}:
												</th>
												<td>
													<textarea name="registerAgreement" class="form-control">${setting.registerAgreement?html}</textarea>
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.isEmailLogin")}:
												</th>
												<td>
													<input type="checkbox" name="isEmailLogin" value="true"[#if setting.isEmailLogin] checked="checked"[/#if] />
													<input type="hidden" name="_isEmailLogin" value="false" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.captchaTypes")}:
												</th>
												<td class="captchaTypes">
													[#list captchaTypes as captchaType]
														<label>
															<input type="checkbox" name="captchaTypes" value="${captchaType}"[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains(captchaType)] checked="checked"[/#if] />${message("Setting.CaptchaType." + captchaType)}
														</label>
													[/#list]
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.accountLockTypes")}:
												</th>
												<td class="captchaTypes">
													[#list accountLockTypes as accountLockType]
														<label>
															<input type="checkbox" name="accountLockTypes" value="${accountLockType}"[#if setting.accountLockTypes?? && setting.accountLockTypes?seq_contains(accountLockType)] checked="checked"[/#if] />${message("Setting.AccountLockType." + accountLockType)}
														</label>
													[/#list]
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.accountLockCount")}:
												</th>
												<td>
													<input type="text" name="accountLockCount" class="form-control" value="${setting.accountLockCount}" maxlength="9" title="${message("console.setting.accountLockCountTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.accountLockTime")}:
												</th>
												<td>
													<input type="text" name="accountLockTime" class="form-control" value="${setting.accountLockTime}" maxlength="9" title="${message("console.setting.accountLockTimeTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.safeKeyExpiryTime")}:
												</th>
												<td>
													<input type="text" name="safeKeyExpiryTime" class="form-control" value="${setting.safeKeyExpiryTime}" maxlength="9" title="${message("console.setting.safeKeyExpiryTimeTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.uploadMaxSize")}:
												</th>
												<td>
													<input type="text" name="uploadMaxSize" class="form-control" value="${setting.uploadMaxSize}" maxlength="9" title="${message("console.setting.uploadMaxSizeTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.uploadImageExtension")}:
												</th>
												<td>
													<input type="text" name="uploadImageExtension" class="form-control" value="${setting.uploadImageExtension}" maxlength="200" title="${message("console.setting.uploadImageExtensionTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.uploadFlashExtension")}:
												</th>
												<td>
													<input type="text" name="uploadFlashExtension" class="form-control" value="${setting.uploadFlashExtension}" maxlength="200" title="${message("console.setting.uploadFlashExtensionTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.uploadMediaExtension")}:
												</th>
												<td>
													<input type="text" name="uploadMediaExtension" class="form-control" value="${setting.uploadMediaExtension}" maxlength="200" title="${message("console.setting.uploadMediaExtensionTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.uploadFileExtension")}:
												</th>
												<td>
													<input type="text" name="uploadFileExtension" class="form-control" value="${setting.uploadFileExtension}" maxlength="200" title="${message("console.setting.uploadFileExtensionTitle")}" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.imageUploadPath")}:
												</th>
												<td>
													<input type="text" name="imageUploadPath" class="form-control" value="${setting.imageUploadPath}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.flashUploadPath")}:
												</th>
												<td>
													<input type="text" name="flashUploadPath" class="form-control" value="${setting.flashUploadPath}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.mediaUploadPath")}:
												</th>
												<td>
													<input type="text" name="mediaUploadPath" class="form-control" value="${setting.mediaUploadPath}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.fileUploadPath")}:
												</th>
												<td>
													<input type="text" name="fileUploadPath" class="form-control" value="${setting.fileUploadPath}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.validateCodeMaxNumber")}: 
												</th>
												<td>
													<input type="text" name="validateCodeMaxNumber" class="form-control" value="${setting.validateCodeMaxNumber}" maxlength="9" />
												</td>
											</tr>
										</table>
                                    </div>
                                    <div id="settingOtherTab" class="tab-pane">
                                         <table class="table table-striped">
											<tr>
												<th>
													${message("Setting.isDevelopmentEnabled")}:
												</th>
												<td>
													<label title="${message("console.setting.isDevelopmentEnabledTitle")}">
														<input type="checkbox" name="isDevelopmentEnabled" value="true"[#if setting.isDevelopmentEnabled] checked="checked"[/#if] />
														<input type="hidden" name="_isDevelopmentEnabled" value="false" />
													</label>
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.isMsgNotified")}:
												</th>
												<td>
													<input type="checkbox" name="isMsgNotified" value="true" [#if setting.isMsgNotified] checked="checked"[/#if] />
													<input type="hidden" name="_isMsgNotified" value="false" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("Setting.cookiePath")}: 
												</th>
												<td>
													<input type="text" name="cookiePath" class="form-control" value="${setting.cookiePath}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													${message("Setting.cookieDomain")}: 
												</th>
												<td>
													<input type="text" name="cookieDomain" class="form-control" value="${setting.cookieDomain}" maxlength="200" />
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("育儿类别最多置顶数")}:
												</th>
												<td>
													<input type="text" name="parentingCategoryLimit" class="form-control" value="${setting.parentingCategoryLimit}" maxlength="9" />
												</td>
											</tr>
										</table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <input type="submit" class="btn btn-primary" value="${message("console.common.submit")}" />
										<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='../common/index.ct'" />
                                    </div>
                                </div>
                              </form>
                            </div>
                        </div>
	        </div>
	       <!-- end 中间内容部分-->
	       <div class="row">
	            <div class="col-lg-12">
	                
	               
	             </div>
	       </div>
	       [#include "/console/include/footer.ftl" /]
      </div>
    </div>
</body>
</html>