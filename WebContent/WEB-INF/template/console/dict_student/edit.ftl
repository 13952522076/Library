<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictStudent.edit")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
.roles label {
	width: 150px;
	display: block;
	float: left;
	padding-right: 6px;
}
table th {
  width: 150px;
  line-height: 25px;
  padding: 5px 10px 5px 0px;
  font-weight: normal;
  white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    dictClassId: {
		        required: true
		    },
			studentNo: {
				required: true,
				maxlength:15,
				pattern: /^\w+$/,
				remote: {
					url: "check_studentNo.ct?previousStudentNo=${dictStudent.studentNo}",
					cache: false
				}
			},
			studentName: {
				required: true,
				maxlength:20
			},
			iconPhoto: {
			    required: true
			},
			studentStatus: {
				required: true
			},
			gender: {
				required: true
			},
			stuDate: {
				required: true
			},
			success: function() {
                layer.load();
        	}
		},
		messages: {
			studentNo: {
				remote: "${message("console.validate.exist")}"
			}
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
                    <h2> ${message("console.dictStudent.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.dictStudent.edit")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 新增管理员-->
	             <form id="inputForm" action="update.ct" method="post">
	                 <input type="hidden" name="id" value="${dictStudent.id}"/>
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content" style="width:70%;margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table class="table table-striped">
	                                        <tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.dictClass")}:
												</th>
												<td>
													<select name="dictClassId" class="form-control m-b">
														<option value="">${message("console.common.select")}</option>
														 [#list dictClasses as dictClass]
															<option value="${dictClass.id}" [#if  dictClass == dictStudent.dictClass] selected="selected" [/#if]>
																(${dictClass.dictSchool.name})${dictClass.name}
															</option>
														 [/#list]
													</select>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.studentNo")}:
												</th>
												<td>
													<input type="text" name="studentNo" class="form-control"  value="${dictStudent.studentNo}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("相关家长")}:
												</th>
												<td>
													<div class="input-group" style="width:100%;">
				                                        <select data-placeholder="请选择关联家长(可搜索名字)" name="memberIds" class="chosen-select" multiple style="width:100%;"  tabindex="4">
				                                            [#list members as member]
				                                            <option value="${member.id}" 
				                                            [#list linkMembers as linkMember]
					                                            [#if member.id==linkMember.id ]
					                                            selected
					                                            [/#if]
				                                            [/#list]
				                                            >${member.realName}(${member.mobile})</option>
				                                            [/#list]
				                                        </select>
				                                    </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.studentName")}:
												</th>
												<td>
													<input type="text" id="studentName" name="studentName" class="form-control" value="${dictStudent.studentName}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.gender")}:
												</th>
												<td>
													<div style="float: left;">
													     <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="gender" name="gender" value="male" [#if  dictStudent.gender == 'male'] checked="checked" [/#if] >
							                                ${message("DictStudent.gender.male")}
							                             </label>
							                              <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="gender" name="gender" value="female" [#if  dictStudent.gender == 'female'] checked="checked" [/#if]>
							                                ${message("DictStudent.gender.female")}
							                             </label>
							                        </div>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.stuDate")}:
												</th>
												<td style="text-align: left;">
													<input type="text" name="stuDate" id="stuDate" class="laydate-icon form-control layer-date" maxlength="50"  value="${dictStudent.stuDate}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.studentStatus")}:
												</th>
												<td>
													<div style="float: left;">
													     <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="studentStatus" name="studentStatus" value="active" [#if  dictStudent.studentStatus == 'active'] checked="checked" [/#if]>
							                                ${message("DictStudent.studentStatus.active")}
							                             </label>
							                             <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="studentStatus" name="studentStatus" value="graduated" [#if  dictStudent.studentStatus == 'graduated'] checked="checked" [/#if]>
							                                ${message("DictStudent.studentStatus.graduated")}
							                             </label>
							                             <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="studentStatus" name="studentStatus" value="quit" [#if  dictStudent.studentStatus == 'quit'] checked="checked" [/#if]>
							                                ${message("DictStudent.studentStatus.quit")}
							                             </label>
							                             <label class="radio-inline">
							                                <input type="radio" class="checkbox checkbox-inline" id="studentStatus" name="studentStatus" value="dropouts" [#if  dictStudent.studentStatus == 'dropouts'] checked="checked" [/#if]>
							                                ${message("DictStudent.studentStatus.dropouts")}
							                             </label>
							                        </div>
												</td>
											</tr>
											<tr>
												<th>
													${message("DictStudent.birthday")}:
												</th>
												<td style="text-align: left;">
													<input type="text" name="birthday" id="birthday"   class="laydate-icon form-control layer-date" maxlength="50" value="${dictStudent.birthday}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("DictStudent.stuAddress")}:
												</th>
												<td>
													<input type="text" name="stuAddress" class="form-control" maxlength="20" value="${dictStudent.stuAddress}"/>
												</td>
											</tr>
											<tr>
												<th>
													${message("DictStudent.stuRmark")}:
												</th>
												<td>
													<input type="text" name="stuRmark" class="form-control" maxlength="20" value="${dictStudent.stuRmark}"/>
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("DictStudent.iconPhoto")}:
												</th>
												<td>
													<input type="file" id="columnCoverImageUpload" />
													<img src="${dictStudent.iconPhoto}" style="display: none;" height="150px" id="showColumnCoverImage" />
													<br style="display: none;" id="showColumnCoverImageBr"/>
													<a href="#" style="display: none;" id="showColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
													<a href="javascript:;" style="display: none;" id="deleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
													<input type="hidden" id="columnCoverImage" name="iconPhoto" class="form-control" value="${dictStudent.iconPhoto}"/>
												</td>
											</tr>
										</table>
										<table class="input">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
													<input type="submit" class="btn  btn-primary" value="${message("console.common.submit")}" />
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='list.ct'" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 新增管理员-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
<!-- 注意一定要放在结尾，不能放在上面，否则无效，原因加载顺序 -->
        <script type="text/javascript" src="${base}/resources/console/js/plugins/layer/laydate/laydate.js"></script>
        <script>
        //外部js调用
        laydate({
            elem: '#stuDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#birthday', 
            event: 'focus' 
        });
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
		    $(".chosen-select").trigger("chosen:updated");
		</script>
</body>
</html>