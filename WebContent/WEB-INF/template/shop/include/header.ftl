<link href="${base}/resources/shop/css/header.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/resources/shop/js/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/menu_min.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/header.js"></script>


<!--右上角链接-->
<div class="topNavDiv">
	<ul class="topNav">
		<li id="language" class="headerLine">
		   <a id="lang-1" href="http://en.mo-co.com" style="padding-right: 10px;">English</a>|
		</li>
		<li id="showlogin" class="headerLine"><a id="login" style="padding-right: 10px;">${message("shop.header.login")}</a>|
		</li>
		<li id="showusername" class="headerLine" style="display:none"><a id="loginusername" style="padding-right: 10px;"></a>|
		</li>
		<li id="logout" class="headerLine" style="display:none"><a href="${base}/logout.ct" id="logout">${message("shop.header.logout")}</a>
		</li>
		<li class="headerLine"><a id="register"
			style="padding-right: 10px;">${message("shop.header.register")}</a>|</li>
		<li id="usercenter" class="headerLine" style="display:none"> 
		    <a href="${base}/member/personalCenter/personalCenter.ct" style="padding-right: 10px;" id="profile"><span id="profile_name"></span></a>|
	    </li>
		<li class="headerLine">
		    <a href="javascript:;" id="shoppingbag" style="padding-right: 10px;">${message("shop.header.cart")}-<span id="miniCartQuantity">0</span></a>|
		 </li>
		<li class="headerLine">
		    <a href="javascript:;" id="online" style="padding-right: 10px;">${message("shop.header.phone")}</a>
		</li>
		<li class="headerLine" style="line-height: 28px;">
			<form id="productSearchForm" action="${base}/product/search.ct" method="get">
				<input class="search" name="keyword" class="keyword"
					placeholder="${productKeyword!message("shop.header.keyword")}" maxlength="30" />
				<button class="searchButton" type="submit"></button>
			</form>
		</li>
	</ul>
</div>

<!--左侧菜单栏-->
<div class="mainMenu">
	<div>
		<a href="${base}/" title="${message("shop.preschoolEdu.home")}"><img src='${base}/resources/shop/images/logo.png' class="logo_image"></a>
		<div id="content">
			<div class="allmenu">
				<ul class="allmenu_ul">
					<li>
					[#if orderTypes??]
					   <a id="eshop" href="${base}/product/list.ct">E-SHOP</a>
					[#else]
					<a id="eshop" class="active" href="${base}/product/list.ct">E-SHOP</a>
					<!--<a id="eshop" class="active" href="${base}/product/listPage.ct">E-SHOP</a>-->
					[/#if]
					<input id="zh_eshop" type="hidden" value="${message("shop.include.onlineShop")}" /> <input
					id="en_eshop" type="hidden" value="E-SHOP" />
						<ul id="firstul" style="display: none;">
							<li>
							    <a id="trendspot" href="#">Trend Spot</a>
							    <input id="zh_trendspot" type="hidden" value="${message("shop.include.trendspot")}"/>
                                <input id="en_trendspot" type="hidden" value="Trend Spot" />
                                <ul id="ultrendspot" class="ul">
                                    [@article_category_children_list articleCategoryId=58] 
                                        [#list articleCategories as articleCategorie]
                                            <li>
                                               <a id="${articleCategorie.id}"  href="${base}/eshop/list.ct?id=${articleCategorie.id}">- ${articleCategorie.nameEn}</a>
                                            </li>
                                        [/#list]
                                     [/@article_category_children_list]
                                </ul>
                            </li>
                            <!--
							<li>
							   <a id="closetupdate"
								href="${base}/eshop/list.ct?id=59">Closet Update</a>
								<input id="zh_closetupdate" type="hidden" value="${message("shop.include.closetUpdate")}" /> 
								<input id="en_closetupdate" type="hidden" value="Closet Update" />
						     </li>
						     -->
							<li>
							    <a href="${base}/product/list.ct?brandId=18" id="MOCo">图书智能管理推荐系统</a>
							    <ul id="brand_catagory_18" class="brand_catagory">
									
						    </ul>
							</li>
							<li>
							<a href="${base}/product/list.ct?brandId=19" id="Edition10">Edition10</a>
							    <ul id="brand_catagory_19" class="brand_catagory">
									
						    </ul>
							</li>
							<li>
							    <a id="newarrival" href="${base}/product/list.ct?tagIds=2">New Arrival</a> 
								<input id="zh_newarrival" type="hidden" value="${message("shop.include.newArrival")}" /> 
								<input id="en_newarrival" type="hidden" value="New Arrival" />
								<ul id="newarrival_ul" class="brand_catagory">
									
						       </ul>
							</li>
							<li>
							  <a id="bestseller" href="${base}/product/list.ct?tagIds=1">Best Seller</a> 
							  <input id="zh_bestseller" type="hidden" value="${message("shop.include.bestSeller")}" /> 
							  <input id="en_bestseller" type="hidden" value="Best Seller" />
							  <ul id="bestseller_ul" class="brand_catagory">
									
						       </ul>
							</li>
							<li>
								<a id="sale" href="${base}/product/list.ct?tagIds=5">Sale</a> 
							    <input id="zh_sale" type="hidden" value="${message("shop.include.sale")}" /> 
								<input id="en_sale" type="hidden" value="On sale" />
								<ul id="sale_ul" class="brand_catagory">
									
						    </ul>
					</li>
				</ul>
				 [@article_category_root_list count = 7]
				[#list articleCategories as category]
				<li>
				<a [#if category.order== 7] style="background: #000;height: 13px;color: #9fa0a0;width: 36px;" [/#if] id="${category.id}"
					href="[#if category.tplChannel??]${base}/${category.tplChannel!}?id=${category.id}[#else]#page[/#if]">${category.nameEn}</a>
					<input id="zh_${category.id}" type="hidden"value="${category.nameZh}" /> 
					<input id="en_${category.id}" type="hidden" value="${category.nameEn}" />
					[#if category.order!= 6 ]
					<ul id="ul_${category.id}" class="ul">
						[#list category.children as articleCategory]
						[#if articleCategory.nameZh == '10周年活动' && articleCategory.nameEn == '10th Anniversary']
						<li>
						   <a id="${articleCategory.id}" target="_blank" href="http://ten.mo-co.com/ ">${articleCategory.nameEn}</a>
							<input id="zh_${articleCategory.id}" type="hidden" value="${articleCategory.nameZh}" /> 
							<input id="en_${articleCategory.id}" type="hidden" value="${articleCategory.nameEn}" /> 
							[#if articleCategory.children ??]
								[#list articleCategory.children as children]
								<ul id="ul_${children.id}" class="ul">
									[#if children.children ??]
										<li>
										    <a id="${children.id}" target="_blank" href="http://ten.mo-co.com/">${children.nameEn}</a>
											<input id="zh_${children.id}" type="hidden" value="${children.nameZh}" /> 
											<input id="en_${children.id}" type="hidden" value="${children.nameEn}" /> 
											[#list children.children as child]
												<ul id="ul_${child.id}" class="ul">
													<li>
														<a id="${child.id}" target="_blank" href="http://ten.mo-co.com/">${child.nameEn}</a>
														<input id="zh_${child.id}" type="hidden" value="${child.nameZh}" />
														<input id="en_${child.id}" type="hidden" value="${child.nameEn}" />
													</li>
												</ul> 
											[/#list]
										</li> 
									[#else]
									<li>
									    <a id="${children.id}" target="_blank" href="http://ten.mo-co.com/ ">${children.nameEn}</a>
										<input id="zh_${children.id}" type="hidden" value="${children.nameZh}" />
										<input id="en_${children.id}" type="hidden" value="${children.nameEn}" /></li> 
								   [/#if]
								</ul> 
								[/#list] 
							[/#if]
						</li>
						[#else]
						   [#if articleCategory.typeName == 'moFriends' && articleCategory.typeName == 'moFriends']
						        
						   [#else]
						      <li>
							   <a id="${articleCategory.id}"
								href="[#if articleCategory.tplChannel??]${base}/${articleCategory.tplChannel!}?id=${articleCategory.id}[#else]javascript:;[/#if]">${articleCategory.nameEn}</a>
								<input id="zh_${articleCategory.id}" type="hidden" value="${articleCategory.nameZh}" /> 
								<input id="en_${articleCategory.id}" type="hidden" value="${articleCategory.nameEn}" /> 
								[#if articleCategory.children ??]
									[#list articleCategory.children as children]
									<ul id="ul_${children.id}" class="ul">
										[#if children.children ??]
											<li>
											    <a id="${children.id}" href="[#if children.tplChannel??]${base}/${children.tplChannel!}?id=${children.id}[#else]javascript:;[/#if]">${children.nameEn}</a>
												<input id="zh_${children.id}" type="hidden" value="${children.nameZh}" /> 
												<input id="en_${children.id}" type="hidden" value="${children.nameEn}" /> 
												[#list children.children as child]
													<ul id="ul_${child.id}" class="ul">
														<li>
															<a id="${child.id}" href="[#if child.tplChannel??]${base}/${child.tplChannel!}?id=${child.id}[#else]javascript:;[/#if]">${child.nameEn}</a>
															<input id="zh_${child.id}" type="hidden" value="${child.nameZh}" />
															<input id="en_${child.id}" type="hidden" value="${child.nameEn}" />
														</li>
													</ul> 
												[/#list]
											</li> 
										[#else]
										<li>
										    <a id="${children.id}" href="[#if children.tplChannel??]${base}/${children.tplChannel!}?id=${children.id}[#else]javascript:;[/#if]">${children.nameEn}</a>
											<input id="zh_${children.id}" type="hidden" value="${children.nameZh}" />
											<input id="en_${children.id}" type="hidden" value="${children.nameEn}" /></li> 
									   [/#if]
									</ul> 
									[/#list] 
								[/#if]
							</li>
						   [/#if]
						  
							[/#if]
						[/#list]
					</ul>
					[/#if]
				  </li> 
				  [/#list] 
				  [/@article_category_root_list]
				</ul>
			</div>
		</div>
	</div>
</div>

<!--登录和注册弹出层-->
<div id="pupPanel" class="modal"
	style="zoom: 1; opacity: 1; top: 15%; left: 30%; z-index: 1002; display: none">
	<div class="modalClose">
		<a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	</div>
	<div class="modalContent">
		<div class="modalTitle">
			<h2 id="pop-title">${message("shop.include.popTitle")}</h2>
		</div>
		<div class="modalInfo">
			<!--登录弹出层-->
			<div id="logonPanel" class="infoLogin logonPanel"
				style="zoom: 1; opacity: 1;">
				<div class="unit size1of2">
					<div class="rPadded">
						<h3>
							${message("shop.include.rPadded1")} <strong>图书智能管理推荐系统</strong> ${message("shop.include.rPadded2")}
						</h3>
							<p>${message("shop.include.rPadded3")}</p>

						<form id="userLogonForm" method="post" name="Logon" action="${base}/login/loginSubmit.ct">
							<ul>
								<li class="formControl">
									<div class="radio-group" id="loginRadioGroup">
										<div class="radio-group-panel">
											<input name="loginMode" type="radio" class="signin-radio" id="login_mobile_radio"/>
											<label class="unchecked" name="login_mobile_radio" for="login_mobile_radio">${message("shop.include.mobile")}</label>
										</div>
										<div class="radio-group-panel">
											<input name="loginMode" type="radio" class="signin-radio" id="login_email_radio" checked="checked"/>
											<label class="checked" name="login_email_radio" for="login_email_radio">${message("shop.include.email")}</label>
										</div>
									</div> 
									<input class="inputText"  type="text" id="loginEmail" name="loginEmail" maxlength="60"/>
								</li>
								<li class="formControl">
								   <label class="labelUp" for="loginPassword"> ${message("shop.include.passWord")}&nbsp; </label> 
								   <input class="inputText" type="password" id="loginPassword" name="loginPassword" maxlength="35" autocomplete="off"/>
								   <div class="passHelpLink">
										<a id="forgetPasswordLink" href="javascript:;" title="${message("shop.include.passHelpLink")}">
											${message("shop.include.passHelpLink")} 
										</a>
									</div>
								 </li>
							</ul>
							<div class="actions" style="margin:8px 0 2px">
								<button id="loginBtn" type="button"
									class="button butBlack actionButton" value="${message("shop.include.loginButton")}">
									<span> ${message("shop.include.loginButton")} </span>
								</button>
							</div>
							<label id="error_result" style="" for="result" generated="true" class="error"></label>
						</form>
					</div>
				</div>

				<div class="unit size1of2 lastUnit">
					<div class="rPadded">
						<h3>
							${message("shop.include.lastUnit.rPadded1")} <strong>图书智能管理推荐系统 </strong>${message("shop.include.rPadded2")}
						</h3>
						<p>
							${message("shop.include.lastUnit.rPadded2")} <strong>图书智能管理推荐系统 </strong> ${message("shop.include.lastUnit.rPadded3")}
						</p>
						<p>
							${message("shop.include.lastUnit.rPadded4")} <strong>图书智能管理推荐系统</strong> ${message("shop.include.lastUnit.rPadded5")}
						</p>
						<div class="actions">
							<button id="registerButton" type="button" value="${message("shop.include.registerButton")}"
								class="button butBlack actionButton">
								<span>${message("shop.include.registerButton")}</span>
							</button>
						</div>
					</div>
				</div>
			</div>

			<!--注册弹出层-->
			<form id="registerForm" action="${base}/register/registSubmit.ct" method="post" >
			<div id="registerUserPanel" class="infoLogin" style="zoom: 1; opacity: 1; display: none;">
				<div class="infoSubtitle">
					<span>${message("shop.include.createUser")}</span>
					<p>${message("shop.include.createUserTitle")}</p>
				</div>
				<div class="radio-group" id="regRadioGroup" style="display: none;">
				<!--
					<div class="signin-sina">
						<input name="signin-radio" type="radio" class="signin-radio" id="signin-radio_sina"/> 
						<label class="singin-label" for="signin-radio_sina" name="signin-radio_sina">${message("shop.include.sina")}</label>
					</div>
					<div class="signin-alipay">
						<input name="signin-radio" type="radio" class="signin-radio" id="signin-radio_alipay" /> 
						<label class="singin-label" for="signin-radio_alipay" name="signin-radio_alipay">${message("shop.include.alipay")}</label>
					</div>
					-->
					<div class="radio-group-panel">
						<input name="signin-radio" type="radio" class="signin-radio" id="reg_mobile_radio"/> 
						<label class="unchecked" for="reg_mobile_radio">${message("shop.include.mobile")}</label>
					</div>
					<div class="radio-group-panel">
						<input name="signin-radio" type="radio" class="signin-radio" checked="checked" id="reg_email_radio"/> 
						<label class="checked" for="reg_email_radio">${message("shop.include.email")}</label>
					</div>
					<!--
					<div class="signin-qq">
						<input name="signin-radio" type="radio" class="signin-radio" id="signin-radio_qq"/> 
						<label class="singin-label" for="signin-radio_qq" name="signin-radio_qq">QQ</label>
					</div>-->
				</div>

				<div class="signup-info">
					<div class="username">
						<input class="inputText" placeholder="   ${message("shop.include.firstName")}" type="text" id="firstname" name="firstname" maxlength="35" autocomplete="off">
						<input class="inputText" placeholder="   ${message("shop.include.givenName")}" type="text" id="givenname" name="givenname" maxlength="35" autocomplete="off">
					</div>
				    <div class="mod_select" style="margin-top: 0px;">
		   				 <ul>
		   				     <li>
		     			    	<div class="select_box_a" tabindex="0">
		      			       		<span id="select_txt_provice" class="select_txt"></span>
		                			<div id="provice_select" class="option_a" >
		                			</div>
		            			</div>
		            			<br clear="all" />
		        			</li>
		        			<li>
		                		<div class="select_box_b" tabindex="0">
		                    		<span id="select_txt_city" class="select_txt"></span>
		                    		<div id="city_select" class="option_b" >
		                    		</div>
		                		</div>
		            		</li>
		        		</ul>
		    			<input type="hidden" id="areaId" name="areaId"/>
					</div>
					<div>
						<input class="inputText" placeholder="   ${message("shop.include.signUpEmail")}"  type="text" id="regEmail" name="regEmail" maxlength="35" autocomplete="off"></input>
						<input class="inputText" placeholder="   ${message("shop.include.signUpMobile")}" type="text" id="regMobile" name="regMobile" maxlength="35" autocomplete="off"></input>
					    <input type="hidden" id="emailIsUnique" value="false"/>
					    <input type="hidden" id="mobileIsUnique" value="false"/>
					</div>
					<div>
						<input class="inputText" placeholder="   ${message("shop.include.signUpPassWord")}" type="password" id="regPassword" name="regPassword" maxlength="35" autocomplete="off" />
					</div>
					<div>
						<input class="inputText" placeholder="   ${message("shop.include.signUpRePassWord")}"  type="password" id="registRepassword" name="repassword" maxlength="35" autocomplete="off" />
					</div>
					<div class="actions" style="float: right;">
						<button id="registerBtn" type="button" value="   ${message("shop.include.registerNow")}"
							class="button butBlack actionButton signupButton">
							<span>${message("shop.include.registerNow")}</span>
						</button>
					</div>
				</div>
			</div>
			</form>

			<!-- 注册成功弹出层 -->
			<div id="registerUserSuccessPanel"
				class="infoLogin" style="zoom: 1; opacity: 1; display: none">
				<div class="infoSubtitle">
					<span>${message("shop.include.infoSubtitleSuccess")}</span>
					<div class="radio-group" id="vipCardRadioGroup" style="display: inline-table;">
						<div class="radio-group-panel">
						    <input name="signin-radio" type="radio" id="hasVIPCardRadio" class="signin-radio" /> 
						    <label id="hasVIPCard" name="hasVIPCardRadio" class="unchecked">${message("shop.include.infoSubtitleYes")}</label>
						</div>
						<div class="radio-group-panel">
						    <input name="signin-radio" type="radio" class="signin-radio" checked="checked" id="noVIPCardRadio"/>
						    <label id="noVIPCard" name="noVIPCardRadio" class="checked">${message("shop.include.infoSubtitleNo")} </label>
						</div>
				    </div>
				</div>

               <div id="validateVIP" style="display:none">
					<table class="validateVip">
					<tr>
					    <th><label for="vipUserName">${message("shop.include.vipUserName")}</label></th>
					    <td><input class="inputText" type="text" id="vipUserName"  maxlength="50" autocomplete="off"/></td>
					  </tr>
					  <tr>
					    <th><label for="vipMobile">${message("shop.include.vipMobile")}</label></th>
					    <td><input class="inputText"  type="text" id="vipMobile" maxlength="50" autocomplete="off"/> </td>
					  </tr>
					  <tr>
					    <th><label for="vipCardNumber">${message("shop.include.vipCardnumber")}</label></th>
					    <td><input class="inputText" type="text" id="vipCardNumber" maxlength="50" autocomplete="off"/></td>
					  </tr>
					</table>
					<div class="vip-description">
						<span>${message("shop.include.vipDescription")}</span>
					</div>
					<div class="actions" style="margin: 8px 0 10px">
						<button id="activationVipInfoBtn" type="button" value="${message("shop.include.vipSure")}" class="button butBlack actionButton" style="width: auto">
							<span>${message("shop.include.vipSure")}</span>
						</button>
					</div>
				</div>
				
				<div class="vip-agreen" style="margin-top:10px;">
				   <div class="confirmCheckBox" id="regPrivacyCheckBoxGroup"  style="overflow: hidden;">
				    <div class="checkBoxGroupPanel">
				        <input type="checkbox" class="confirmCheckBox" id="vip_agreen_checkbox"/>
				        <label class="unconfirm" name="vip_agreen_checkbox" for="vip_agreen_checkbox">${message("shop.include.vipAgreen")}</label>
				    </div>
				  </div>
				</div>

				<div class="actions" style="margin: 8px 0 10px">
					<button id="continueGo" type="button" value="${message("shop.include.goOn")}" style="width: auto"
						class="button butBlack actionButton">
						<span>${message("shop.include.goOn")}</span>
					</button>
				</div>

				<div class="vip-desc">
					<span style="display: block; padding-bottom: 5px;">${message("shop.include.vipDesc")}</span>
					<span style="display: block; padding-bottom: 5px;">${message("shop.include.vipDesc1")}</span>
					<span style="">${message("shop.include.vipDesc2")}</span>
				</div>
			</div>

			<!-- start 找回密码 -->
			<div id="findPasswordPanel" class="infoLogin" style="zoom: 1; opacity: 1; display: none">
				<div class="infoSubtitle">
					<span>${message("shop.include.infoSubtitle")}</span>
				</div>
				<div class="mod_select">
	 		    	<ul>
	   				     <li>
	     			    	<div class="select_box_forgot">
	      			       		<span id="select_txt_validatetype" class="select_txt">${message("shop.validate.none.type")}</span>
	      			        	<a class="selet_open">
	            			 	  <b></b>
	                			</a>
	                			<div id="provice_select" class="option_forgot">
		                			<a >${message("shop.validate.phone.type")}</a>
		                			<a >${message("shop.validate.emial.type")}</a>
	                			</div>
	            			</div>
	            			<br clear="all" />
	        			</li>
	        		</ul><p id="validateWayDesc" style="padding-top: 15px;font-size: 12px;"></p>
				</div>

                <div id="validateByPhoneNumber">
                    <li id="validateByPhoneControl" class="formControl"><label class="labelUp" for="mobileNum">
							${message("shop.include.mobileNum")}&nbsp; </label> <input class="inputText"  type="text"
						id="mobileNum" name="mobileNum" maxlength="35"
						autocomplete="off">
						<a style="font-size:10px;text-decoration:underline;" id="getValidateCode" href="javascript:;" onclick="countDown(this,60);" title="${message("shop.include.getValidateCode")}"> ${message("shop.include.getValidateCode")}</a>
						<span id="validtaWaitInfo"></span>
				     </li>
				     
				     <li id="validateCodeControl" class="formControl">
				        <label class="labelUp" for="validateCode"> ${message("shop.include.validateCode")}&nbsp; </label> 
				        <input class="inputText" type="text" id="validateCode" name="validateCode" maxlength="35"
						autocomplete="off" />
				      </li>
	
					<div class="actions" style="margin: 20px 0 28px -1px">
						<button onclick="findPassword()" id="findPassword" type="button" value="${message("shop.include.nextStep")}" style="width: auto"
							class="button butBlack actionButton">
							<span>${message("shop.include.nextStep")}</span>
						</button>
					</div>
                </div>
				<div id="validateByEmailDiv" style="display:none">
				     <li id="validateByEmialControl" class="formControl"><label class="labelUp" for="validateEmail">
							${message("shop.include.validateByEmail")}&nbsp; </label> <input class="inputText gaFormField" type="text"
						id="validateEmail" name="validateEmail" maxlength="35"
						autocomplete="off">
				     </li>
				     
				     <div class="actions" style="margin: 20px 0 28px -1px">
						<button onclick="findPasswordByMail()" id="sendEmailforfindpassword" type="button" value="${message("shop.include.nextStep")}" style="width: auto"
							class="button butBlack actionButton">
							<span>${message("shop.include.nextStep")}</span>
						</button>
					</div>
				</div>
			</div>
            <!-- end 找回密码 -->
            
			<!-- start 购物篮 -->
			<div id="shoppingBagPanel" class="infoLogin" style="zoom: 1; opacity: 1; display: none;width:100%">
				<div class="shoppingBag-header">
				    <div class="product-info"><span>${message("shop.include.productInfo")}</span></div>
				    <div class="product-color"><span>${message("shop.include.productColor")}</span></div>
				    <div class="product-size"><span>${message("shop.include.productSize")}</span></div>
				    <div class="product-price"><span>${message("shop.include.productPrice")}</span></div>
				    <div class="product-count"><span>${message("shop.include.productCount")}</span></div>
				    <div class="product-priceTotal"><span>${message("shop.include.productPriceTotal")}</span></div>
				    <div class="product-option"><span>${message("shop.include.productOption")}</span></div>
				</div>
				<div class="product-list">
				    <div class="empty-show">
				        <a href="${base}/product/list.ct" style="text-decoration: underline;">${message("shop.cart.empty")}</a>
				    </div>
				</div>
				<!-- 
				<div class="bottom-line"></div>
				-->
                <div class="product-paymentinfo" id="cartToolPriceBarArea">
                    <div class="product-totalcount">
                        <span>${message("shop.include.productTotalcount")}</span>
                    </div>
                    <div class="product-paymentdesc" id="cartToolPriceBar" style="display: none;">
                        <p id="product-totalPrice">${message("shop.include.productTotalPrice")}</p>
                        <p id="product-ems">${message("shop.include.productEms")}</p>
                        <p id="discountPrice"></p>
                        <p id="effectivePrice" style="font-size: 12px;float: right;">${message("shop.include.effectivePrice")}</p>
                    </div>
                </div>
                <div class="actionsControls fRight">
                    <button type="button" id="product-gotoshopping" class="button butWhite">
	                    <span>${message("shop.include.productGotoShopping")}</span>
	                </button>
	                <button type="button" id="product-gotopayment" class="button butBlack">
	                    <span>${message("shop.include.productGotoPayment")}</span>
	                </button>
		         </div>
			</div>
			<!-- end 购物篮 -->
		</div>
	</div>
	<div id="itxLoading"></div>
</div>

<!-- 在线客服 -->
<div class="online-service">
	<div class="modalClose">
		<a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	</div>
	<div class="serviceContent">
		<div class="serviceTitle">
			<div class="logo-new"></div>
		</div>
		<form  id="chatThreadForm" method="post" target="_blank" action="${base}/login/loginChat.ct" >
		<div class="serviceInfo">
			<div class="service-login">
				<div class="login-title">
					<span>${message("shop.include.loginTitle")}</span>
				</div>
				<div class="login-username" style="width: 350px;">
					<span>${message("shop.include.loginUserName")}</span>
					<div class="div-input" style="float:right;width:265px;">
						<input type="text" name="serviceUsername" id="serviceUsername" style="width:262px;"/>
					</div>
				</div>
				<div class="login-email" style="width: 350px;">
					<span style="height: 27px;">${message("shop.include.loginEmail")}</span>
					<div class="div-input" style="float:right;width:265px;">
						<input type="text" name="serviceEmail" id="serviceEmail" style="width:262px;"/>
					</div>
				</div>
				<div class="mod_select">
					<ul>
						<li style="width: 350px;">
						    <span class="select_label" style="margin-top: 12px;">${message("shop.include.selectLanguage")}</span>
							<div class="select_box" style="float:right;width:265px;margin-left: 20px;">
								<span class="select_txt">${message("shop.include.selectChinese")}</span><a class="selet_open"><b></b></a>
								<div class="option" style="width:265px;">
									<a>${message("shop.include.selectChinese")}</a> 
									<a>English</a>
								</div>
							</div> 
							<br clear="all" />
						</li>
					</ul>
					<input type="hidden" id="select_value" name="serviceLanguage" value="${message("shop.include.selectChinese")}"/>
				</div>
  			    <div class="actions" style="margin:30px auto;float: right; margin-right:10px;">
					<button id="chatSubmit" type="button" value="${message("shop.include.chatSubmit")}" class="button butBlack actionButton" style="width: 80px; height: 35px;">
						<span>${message("shop.include.chatSubmit")}</span>
					</button>
			    </div>
  			 </div>
			</form>
			<div class="chat-pop">
				<div class="service-chat">
					<div class="chat-desc">
						<div class="chat-tail">
							<span>${message("shop.include.chatTail")}</span>
						</div>
						<div class="chat-tail">
							<span>${message("shop.include.chatTail1")}</span>
						</div>
						<div class="chat-usertxt">
							<span class="transcripttxt usertxt"><span class="username">图书智能管理推荐系统
									:</span>&nbsp;${message("shop.include.chatUsertxt")}</span><br>
						</div>
					</div>
					<div class="chat-info">
						<textarea class="user-input"></textarea>
					</div>
				</div>
				<div class="chat-option">
					<a>${message("shop.include.chatOptionPrint")}</a><a>${message("shop.include.chatOptionHistory")}</a>
				</div>
				<div class="chatmassage-send">
					<span>${message("shop.include.chatMassageSend")}</span>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 产品防伪验证   -->
<div class="validation-service" style="display: none; zoom: 1; opacity: 1; z-index: 1002; position: fixed;">
	<div class="modalClose">
		<a href="javascript:;" title="${message("shop.include.serviceClose")}"></a>
	</div>
	<div class="serviceContent" style="padding:50px 60px 0px 60px;">
		<div class="serviceTitle">
			<div class="logo-new"></div>
		</div>
		<div class="serviceInfo">
			<div class="validation-input" style="border-top: 1px solid #000000 ">
				<div class="validation-title" style="margin-top: 30px;">
					<span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationTitle")}</span>
				</div>
				<div class="div-input" style="margin-top: 10px; width:350px;">
					<input type="text" name="validationInput" id="validationInput"/>
				</div>
				<div class="validation-declare" style="margin-top: 50px;">
					<a style="color: #9c9e9f; text-decoration:underline;" target="_blank" href="${base}/info/buyingguide.ct?id=56&param=antiCounterfeitingDeclare">
					    <span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationDeclare")}</span>
					</a>
				</div>
  			    <div class="actions" style="margin:30px auto;float: right; margin-right:10px;">
					<button id="validationSubmit" type="button" value="${message("shop.include.validation")}" class="button butBlack actionButton" style="width: 80px; height: 35px;">
						<span>${message("shop.include.validation")}</span>
					</button>
			    </div>
  			 </div>
  			 <div class="validation-pass" style="display: none; border-top: 1px solid #000000 ">
				<div class="validationpass-title" style="margin-top: 30px;">
					<span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationPassTitle")}</span>
				</div>
				<div class="pluCode" style="margin-top: 10px; width:350px;">
					<span style="font-size: 12px" id="pluCode">${message("shop.include.antiCounterfeitingValidationPlucode")}:</span>
				</div>
				<div class="validationpass-end" style="margin-top: 10px; width:350px;">
					<span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationPassEndTitle")}</span>
				</div>
  			    <div class="actions" style="margin:30px auto;float: right; margin-right:10px;">
					<button id="passBackSubmit" type="button" value="${message("shop.include.back")}" class="button butBlack actionButton" style="width: 80px; height: 35px;">
						<span>${message("shop.include.back")}</span>
					</button>
			    </div>
  			 </div>
  			 <div class="validation-failed" style="display: none; border-top: 1px solid #000000 ">
				<div class="validationfailed-title" style="margin-top: 30px;">
					<span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationFailedTitle")}</span><br/>
				</div>
				<div class="validationfailed-end" style="margin-top: 20px; width:350px;">
					<span style="font-size: 12px">${message("shop.include.antiCounterfeitingValidationFailedEndTitle")}</span>
				</div>
  			   	<div class="actions" style="margin:30px auto;float: right; margin-right:10px;">
					<button id="failedBackSubmit" type="button" value="${message("shop.include.back")}" class="button butBlack actionButton" style="width: 80px; height: 35px;">
						<span>${message("shop.include.back")}</span>
					</button>
			    </div>
  			 </div>
		</div>
	</div>
</div>

<!--遮罩层-->
<div id="itxOverlay-header"></div> 
