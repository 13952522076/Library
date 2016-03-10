<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.member.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${base}/resources/console/css/plugins/iCheck/custom.css" >
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorClass: "m-b-none",
    validClass: "m-b-none"
});
$().ready(function() {
	// 地区选择
	
	
	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	
	[@flash_message /]
	$areaId.lSelect({
		url: "${base}/console/common/area.ct"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			password: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength},
				maxlength: ${setting.passwordMaxLength}
			},
			email: {
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "check_email.ct?previousEmail=${member.email?url}",
						cache: false
					}
				[/#if]
			},
			mobile:{
			    required: true,
			    pattern: /^1[345678]\d{9}$/,
			    remote: {
					url: "check_mobile.ct?previousMobile=${member.mobile}",
					cache: false
				}
			},
			point: {
				required: true,
				digits: true
			},
			zipCode: {
				pattern: /[1-9]\d{5}(?!\d)/
			},
			phone: {
				pattern: /\d{3}-\d{8}|\d{4}-\{7,8}/
			},
			areaId_select: "required",
			success: function() {
                layer.load();
        	}
			
		},
		messages: {
			password: {
				pattern: "${message("console.validate.illegal")}"
			},
			zipCode: {
				pattern: "${message("console.validate.illegal")}"
			},
			phone: {
				pattern: "${message("console.validate.illegal")}"
			}
			[#if !setting.isDuplicateEmail]
				,email: {
					remote: "${message("console.validate.exist")}"
				}
			[/#if]
		}
	});
	
	
	<!--start上传图片插件 -->
 	var $columnCoverImageUpload = $("#columnCoverImageUpload");
	var $showColumnCoverImage = $("#showColumnCoverImage");
	var $showColumnCoverImageHref = $("#showColumnCoverImageHref");
	var $deleteColumnCoverImage = $("#deleteColumnCoverImage");
	var $columnCoverImage = $("#columnCoverImage");
	var $showColumnCoverImageBr = $("#showColumnCoverImageBr");
    $columnCoverImageUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '浏览',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/file/upload.ct?fileType=image',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',       //图片选择描述  
        'fileTypeExts'  :  '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式      
        'formData'      : {'token' : getCookie("token") },
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
            var dataJson = JSON.parse(data);
            var contentImgFileUrl = dataJson['url'];
            var messageContent = dataJson['message']['content'];
            var messageType = dataJson['message']['type'];
            if(messageType == 'success'){
	            $showColumnCoverImage.attr("src",contentImgFileUrl);
	            $showColumnCoverImageHref.attr("href",contentImgFileUrl);
	            $deleteColumnCoverImage.attr("val",contentImgFileUrl);
	            $columnCoverImage.val(contentImgFileUrl);
	            $("#columnCoverImageUpload").hide(100);
	            $showColumnCoverImage.show(100);
	            $showColumnCoverImageHref.show(100);
	            $deleteColumnCoverImage.show(100);
	            $showColumnCoverImageBr.show(100);
            }else{
                $.message("warn", messageContent);
            }
        },
	    onComplete: function (event, queueID, fileObj, response, data) {
	           
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    }
    });
	<!--end上传图片插件 -->
	
	<!-- start删除图片插件-->
	// 删除封面图片
	$deleteColumnCoverImage.on("click", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
	            $showColumnCoverImage.attr("src","");
	            $showColumnCoverImageHref.attr("href","");
	            $deleteColumnCoverImage.attr("val","");
	            $columnCoverImage.val("");
	            $("#columnCoverImageUpload").show(100);
	            $showColumnCoverImage.hide(100);
	            $showColumnCoverImageHref.hide(100);
	            $deleteColumnCoverImage.hide(100);
	            $showColumnCoverImageBr.hide(100);
			}
		});
	});
	<!-- end  删除图片插件-->
	
	<!-- start头像加载编辑 -->
	var src = $("#showColumnCoverImage").attr("src");
	if(src!=""){
		$("#columnCoverImageUpload").hide();
		$("#showColumnCoverImage").show();
		$("#showColumnCoverImageHref").show();
		$("#deleteColumnCoverImage").show();
		$("#showColumnCoverImageBr").show();
		$("#showColumnCoverImageHref").attr("href",src);
	}
	<!-- end头像加载编辑 -->


});
</script>
</head>
<body>
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
					<h2> ${message("console.member.edit")} </h2>
	                <ol class="breadcrumb">
						<li>
						    <!--首页-->
							<a href="${base}/console/common/index.ct">${message("console.path.index")}</a> 
	                    </li>
	                    <li>
	                        <strong>${message("console.member.edit")}</strong>
	                    </li>
					</ol>
	            </div>
	            <div class="col-lg-2">
	
	            </div>
	        </div>
	       	<!-- end 头部面包屑区域 -->	
			<!-- start 中间内容部分 -->
			<div class="wrapper wrapper-content animated fadeIn">
				<div class="row">
			        <div class="col-lg-12">
			            <div class="ibox float-e-margins">
			                <div class="ibox-content" style="width:50%;margin:0 auto;">
			                    <form id="inputForm" action="update.ct" method="post" class="form-horizontal m-t">
			                        <input type="hidden" name="id" value="${member.id}" />
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Admin.username")}：</label>
			                            <td>
											${member.username}
										</td>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">相关班级：</label>
			                            <div class="col-sm-8">
			                            	<div class="input-group" style="width:100%;">
		                                        <select data-placeholder="请选择关联班级(可搜索名字)" name="dictClassIds" class="chosen-select" multiple style="width:100%;"  tabindex="4">
		                                            [#list dictClasses as dictClass]
		                                            <option value="${dictClass.id}" hassubinfo="true" 
		                                            [#list linkClasses as linkClass]
			                                            [#if linkClass.id == dictClass.id ]
			                                            selected="true"
			                                            [/#if]
		                                            [/#list]
		                                            >${dictClass.name}</option>
		                                            [/#list]
		                                        </select>
		                                    </div>
			                               
		                            	</div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("Member.mobile")}：</label>
			                            <div class="col-sm-8">
			                                <input id="mobile" name="mobile" class="form-control" value="${member.mobile}" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("Admin.realName")}：</label>
			                            <div class="col-sm-8">
			                                <input id="realName" name="realName" class="form-control" value="${member.realName}" autocomplete="off">
			                            </div>
			                        </div>
			                        <!--
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Member.identity")}：</label>
			                            <div class="col-sm-8">
			                                <input id="idCard" name="idCard" class="form-control" value="${member.idCard}">
			                            </div>
			                        </div>
			                        -->
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("console.login.password")}：</label>
			                            <div class="col-sm-8">
			                                <input id="password" name="password" class="form-control" type="password" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Member.email")}：</label>
			                            <div class="col-sm-8">
			                                <input id="email" name="email" class="form-control" type="email" value="${member.email}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                           <!-- <label class="col-sm-3 control-label">${message("Member.point")}：</label>
			                            <div class="col-sm-8">
			                                <input id="point" name="point" class="form-control" value="${member.point}"  type="hidden">
			                            </div>
			                            -->
			                             <input id="point" name="point" class="form-control" value="${member.point}"  type="hidden">
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">
			                                ${message("MemberAttribute.Type.gender")}：
			                            </label>
			                            <div class="col-sm-8 col-sm-10">
			                                <input type="radio" [#if member.gender=="male"]checked="checked"[/#if] class="checkbox checkbox-inline" id="gender" name="gender" value="male">${message("Member.Gender.male")}
			                                <input type="radio" [#if member.gender=="female"]checked="checked"[/#if] class="checkbox checkbox-inline" id="gender" name="gender" value="female">${message("Member.Gender.female")}
			                            </div>
			                        </div>
			                        <div class="form-group">
			                           <input  type="hidden" name="memberType" value="${memberType}" />
			                            <!-- 
			                            <label class="col-sm-3 control-label">
			                               ${message("Member.memberType")}：
			                            </label>
			                            <div class="col-sm-8">
			                                <input type="radio" [#if member.memberType=="teacher"]checked="checked"[/#if] class="checkbox checkbox-inline" id="memberType" name="memberType" value="teacher" checked="checked">${message("console.common.teacher")}
			                                <input type="radio" [#if member.memberType=="patriarch"]checked="checked"[/#if] class="checkbox checkbox-inline" id="memberType" name="memberType" value="patriarch">${message("console.common.patriarch")}
			                            </div>
			                            -->
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"> ${message("console.member.birth")}：</label>
			                            <div class="col-sm-8">
			                                <input id="birth" name="birth" class="laydate-icon form-control layer-date" value="${member.birth}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label"><span class="requiredField">*</span>${message("Member.area")}：</label>
			                            <div class="col-sm-8">
			                                <input type="hidden" id="areaId" name="areaId" value="${(member.area.id)!}" treePath="${(member.area.treePath)!}"/>
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Member.address")}：</label>
			                            <div class="col-sm-8">
			                                <input id="address" name="address" class="form-control" value="${member.address}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Member.zipCode")}：</label>
			                            <div class="col-sm-8">
			                                <input id="zipCode" name="zipCode" class="form-control" value="${member.zipCode}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">${message("Admin.telephone")}：</label>
			                            <div class="col-sm-8">
			                                <input id="phone" name="phone" class="form-control" value="${member.phone}">
			                            </div>
			                        </div>
			                        <div class="form-group">
			                            <label class="col-sm-3 control-label">
			                                ${message("Admin.isEnabled")}：
			                            </label>
			                            <div class="col-sm-8">
		                                	<input type="radio" [#if member.isEnabled]checked="checked"[/#if] class="checkbox checkbox-inline" id="isEnabled" name="isEnabled" value="true">${message("console.common.true")}
			                                <input type="radio" [#if !member.isEnabled]checked="checked"[/#if] class="checkbox checkbox-inline" id="isEnabled" name="isEnabled" value="false">${message("console.common.false")}
			                            </div>
			                        </div>
			                        <div class="form-group">
									 	<label class="col-sm-3 control-label">
			                                ${message("DictStudent.iconPhoto")}
			                            </label>
										<div class="col-sm-8">
											<input type="file" id="columnCoverImageUpload" />
											<img src="${member.iconPhoto}" style="display: none;" height="150px" id="showColumnCoverImage" />
											<br style="display: none;" id="showColumnCoverImageBr"/>
											<a href="#" style="display: none;" id="showColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
											<a href="javascript:;" style="display: none;" id="deleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
											<input type="hidden" id="columnCoverImage" name="iconPhoto" class="form-control" value="${member.iconPhoto}"/>
										</div>
									</div>
			                        
			                        <div class="form-group">
			                            <div class="col-sm-8 col-sm-offset-3">
			                               <input type="submit" class="btn  btn-primary" value="${message("console.common.confirm")}" />
										   <input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
			                            </div>
			                        </div>
			                    </form>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<!-- end 中间内容部分 -->
		   	[#include "/console/include/footer.ftl" /]
		</div>
	</div>
	<script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js">
        </script>
        <script>
            //生日选择
            var birth = {
                elem: '#birth',
                format: 'YYYY-MM-DD',
                //min: laydate.now(),
                //设定最小日期为当前日期
                min: '1900-01-01', //最小日期
                max: '2099-06-16',
                //最大日期
                istime: true,
                istoday: false
            };
            laydate(birth);
       </script>
       <script>
		    var config = {
		        '.chosen-select': {},
		        '.chosen-select-deselect': {
		            allow_single_deselect: true
		        },
		        '.chosen-select-no-single': {
		            disable_search_threshold: 10
		        },
		        '.chosen-select-no-results': {
		            no_results_text: '无选择项'
		        },
		        '.chosen-select-width': {
		            width: "95%"
		        }
		    }
		    for (var selector in config) {
		        $(selector).chosen(config[selector]);
		    }
		</script>
</body>
</html>