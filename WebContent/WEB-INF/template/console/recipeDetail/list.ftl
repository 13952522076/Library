<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${message("console.attendance.list")} -图书智能管理推荐系统</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        [#include "/console/include/resources.ftl" /]
        <script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/enumAndDateHelper.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
	    <script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
		<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
		<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
		<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
		<script type="text/javascript" src="${base}/resources/console/datePicker/WdatePicker.js"></script>
        <link href="${base}/resources/console/css/TheRecipe.css" type="text/css" />
        <link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
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
	        errorClass: " m-b-none",
	        validClass: " m-b-none"
	    });
		$().ready(function() {
			[@flash_message /]
			// 表单验证
			$("#addForm").validate({
				rules: {
					dishName: {
						required: true,
						maxlength:20
					},
					description:{
						required: true,
						maxlength:50
					}
				}
			});
			$("#editForm").validate({
				rules: {
					dishName: {
						required: true,
						maxlength:20
					},
					description:{
						required: true,
						maxlength:50
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
		//_____________________________________________	
			//编辑div的弹出框图片上传插件处理
			<!--start上传图片插件 -->
		 	var $editColumnCoverImageUpload = $("#editColumnCoverImageUpload");
			var $editShowColumnCoverImage = $("#editShowColumnCoverImage");
			var $editShowColumnCoverImageHref = $("#editShowColumnCoverImageHref");
			var $editDeleteColumnCoverImage = $("#editDeleteColumnCoverImage");
			var $editColumnCoverImage = $("#editColumnCoverImage");
			var $editShowColumnCoverImageBr = $("#editShowColumnCoverImageBr");
		    $editColumnCoverImageUpload.uploadify({  
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
			            $editShowColumnCoverImage.attr("src",contentImgFileUrl);
			            $editShowColumnCoverImageHref.attr("href",contentImgFileUrl);
			            $editDeleteColumnCoverImage.attr("val",contentImgFileUrl);
			            $editColumnCoverImage.val(contentImgFileUrl);
			            $("#editColumnCoverImageUpload").hide(100);
			            $editShowColumnCoverImage.show(100);
			            $editShowColumnCoverImageHref.show(100);
			            $editDeleteColumnCoverImage.show(100);
			            $editShowColumnCoverImageBr.show(100);
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
			$editDeleteColumnCoverImage.on("click", function() {
				var $this = $(this);
				$.dialog({
					type: "warn",
					content: "${message("admin.dialog.deleteConfirm")}",
					onOk: function() {
			            $editShowColumnCoverImage.attr("src","");
			            $editShowColumnCoverImageHref.attr("href","");
			            $editDeleteColumnCoverImage.attr("val","");
			            $editColumnCoverImage.val("");
			            $("#editColumnCoverImageUpload").show(100);
			            $editShowColumnCoverImage.hide(100);
			            $editShowColumnCoverImageHref.hide(100);
			            $editDeleteColumnCoverImage.hide(100);
			            $editShowColumnCoverImageBr.hide(100);
					}
				});
			});
			<!-- end  删除图片插件-->
					
			
			
		});
		<!--ready 的结束-->
		
		//新增页面弹出
		function showDiv(){
			$("#editDiv").fadeOut();
			$("#addDiv").fadeIn();
		}
		//关闭两个div窗口
		function closeDiv(){
			$("#editDiv").fadeOut();
			$("#addDiv").fadeOut();
		}
		
		//打开编辑弹出框
		function showEditDiv(id){
			$.ajax({
				type: "GET",
				url: "edit.ct",
				data: {
				    id:id
				},
				dataType: "json",
				success:function(data){
					$("#editDiv").fadeIn();
					$("#editId").val(data.id);
					$("#editDishName").val(data.dishName);
					$("#editDescription").val(data.description);
					if(data.dishImage!=null){
						$("#editShowColumnCoverImage").attr("src",data.dishImage);
						$("#editShowColumnCoverImage").show();
						$("#editShowColumnCoverImageHref").show();
						$("#editDeleteColumnCoverImage").show();
						$("#editDishImage").val(data.dishImage);
						$("#editShowColumnCoverImageBr").show();
						$("#editShowColumnCoverImageHref").attr("href",data.dishImage);
					}
				}
			});
		}
		
	</script>
    </head>
    
    <body>
	    <div id="wrapper">
	        <!-- start 导航 -->
	        [#include "/console/include/nav.ftl" /]
	        <!-- end 导航 -->
	        <div id="page-wrapper" class="gray-bg dashbard-1">
	            <!-- start 头部 -->
	            [#include "/console/include/header.ftl" /]
	            <!-- end 头部-->
	            <!-- start 头部面包屑区域 -->
	            <div class="row wrapper border-bottom white-bg page-heading">
	                <div class="col-lg-10">
	                    <h2>${message("console.EditTheRecipe")}</h2>
	                    <ol class="breadcrumb">
	                        <li>
	                            <a href="${base}/console/common/index.ct">
	                                ${message("console.path.index")}
	                            </a>
	                        </li>
	                        <li>
	                            <strong>
	                                ${message("console.EditTheRecipe")}
	                                <span>(${message("console.page.total", recipesDetails?size)})</span>
	                            </strong>
	                        </li>
	                    </ol>
	                </div>
	                <div class="col-lg-2">
	                </div>
	            </div>
	            <!-- end 头部面包屑区域 -->
	            <!-- start 中间内容部分 -->
	            <div class="wrapper wrapper-content animated fadeIn">
	                <!-- start 列表 -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                            	<!--start列表内容-->
                                <div class="ibox-content">
                                    <div class="row">
                                        <div class="col-sm-4 m-b-xs">
                                            <div class="btn-group">
                                                <div class="btn-group">
                                                    [@shiro.hasPermission name = "console:log_button_deleteButton"]
                                                    <a href="javascript:void();" class=" btn btn-primary" onClick="showDiv()">
                                                        <span class="addIcon">&nbsp;</span>
                                                        ${message("console.common.add")}
                                                    </a>
                                                    <a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
                                                        <span class="deleteIcon">&nbsp;</span>
                                                        ${message("console.common.delete")}
                                                    </a>
                                                    [/@shiro.hasPermission]
                                                    <a class="btn  btn-warning" href="${base}/console/recipes/list.ct" >${message("console.common.back")}</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table id="listTable" class="table table-striped">
                                            <tr>
                                                <th class="check"><input type="checkbox" id="selectAll" /></th>
                                                <th><span>菜品</span></th>
                                                <th><span>描述</span></th>
                                                <th><span>图片</span></th>
                                                <th><span>${message("console.common.handle")}</span></th>
                                            </tr>
                                            [#list recipesDetails as recipesDetail]
                                            <tr>
                                                <td><input type="checkbox" name="ids" value="${recipesDetail.id}" /></td>
                                                <td><span>${recipesDetail.dishName}</span></td>
                                                <td><small>${recipesDetail.description}</small></td>
                                                <td class="project-title">
                                                    <img alt="image" src="${recipesDetail.dishImage}" height="50px" class="img-circle">
                                                </td>
                                                <td>
                                                    <a href="javascript:;" class="Edithidden_btn" onClick='showEditDiv(${recipesDetail.id})'>
                                                        [${message("console.common.edit")}]
                                                    </a>
                                                </td>
                                            </tr>
                                            [/#list]
                                        </table>
                                    </div>
                                </div>
                                <!--end  列表内容-->

                                <!--弹出框 form Start-->
                                <div id="addDiv" style="width: 50%;margin: 0 auto;display:none;">
	                                <form id="addForm" class="form-horizontal" action="${base}/console/recipesDetail/add.ct" method="post">
	                                    <p>菜单添加</p>
	                                    <input type="hidden" name="recipeId" value="${recipeId}">
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">菜单名：</label>

	                                        <div class="col-lg-8">
	                                            <input class="form-control" name="dishName">
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">描述：</label>

	                                        <div class="col-lg-8">
	                                            <input class="form-control" name="description">
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">图片上传：</label>
	                                        <div class="col-lg-8">
	                                            <input type="file" id="columnCoverImageUpload" />
												<img style="display: none;" height="150px" id="showColumnCoverImage" />
												<br style="display: none;" id="showColumnCoverImageBr"/>
												<a href="#" style="display: none;" id="showColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
												<a href="javascript:;" style="display: none;" id="deleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
												<input type="hidden" name="dishImage"  class="text" id="columnCoverImage"  />
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <div class="col-lg-offset-3 col-lg-8">
	                                            <button class="btn btn-info" type="submit" onclick="submitAddDetail()">添加</button>
	                                            <a href="javascript:;" class="btn btn-info"  onclick="closeDiv()">返回</a>
	                                        </div>
	                                    </div>
	                                </form>
                            	</div>
                                <!--弹出框 form End-->
                                
                                <!--修改框 form Start-->
                                <div id="editDiv" style="width: 50%;margin: 0 auto;display:none;">
	                                <form id="editForm" class="form-horizontal" action="${base}/console/recipesDetail/update.ct" method="post">
	                                    <p>菜单添加</p>
	                                    <input type="hidden" name="editId" id="editId">
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">菜单名：</label>
	                                        <div class="col-lg-8">
	                                            <input class="form-control" name="editDishName" id="editDishName">
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">描述：</label>
	                                        <div class="col-lg-8">
	                                            <input class="form-control" name="editDescription" id="editDescription">
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <label class="col-lg-3 control-label">图片上传：</label>
	                                        <div class="col-lg-8">
	                                            <input type="file" id="editColumnCoverImageUpload" />
												<img style="display: none;" height="150px" id="editShowColumnCoverImage" />
												<br style="display: none;" id="editShowColumnCoverImageBr"/>
												<a href="#" style="display: none;" id="editShowColumnCoverImageHref" target="_blank">[${message("admin.common.view")}]</a>
												<a href="javascript:;" style="display: none;" id="editDeleteColumnCoverImage" val="">[${message("admin.common.delete")}]</a>
												<input type="hidden" name="editDishImage"  class="text" id="editColumnCoverImage" />
	                                        </div>
	                                    </div>
	                                    <div class="form-group">
	                                        <div class="col-lg-offset-3 col-lg-8">
	                                            <button class="btn btn-info" type="submit">修改</button>
	                                            <a href="javascript:;" class="btn btn-info" onclick="closeDiv()">返回</a>
	                                        </div>
	                                    </div>
	                                </form>
                            	</div>
                                <!--修改框 form End-->
                            </div>
                        </div>
                    </div>
	                <!-- end 列表-->
	            </div>
	            <!-- end 中间内容部分-->
	            [#include "/console/include/footer.ftl" /]
	        </div>
	    </div>
	</body>
</html>