<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictStudent.list")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<link href="${base}/resources/console/css/excelImport.css" rel="stylesheet">
<script type="text/javascript" src="${base}/resources/console/js/excelImport.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}
.uploadify {
  position: relative;
  margin-top: 0px;
  display: inline-block;
}
.uploadify-queue {
  margin-bottom: 1em;
  display: none;
}
</style>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]
	<!-- start 上传图集 -->
	var $batchSaveStudentUpload = $("#batchSaveStudentUpload");
    $batchSaveStudentUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 34,   
        'width'         : 80,    
        'buttonText'    : '${message("common.batchImport")}',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/dict_student/batchSaveStudents.ct?fileType=file',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.xls',//选择描述  
        'fileTypeExts'  :  '*.xls',//允许的格式      
        'formData'      : {'token' : getCookie("token")},
        //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	layer.closeAll();
            var dataJson = JSON.parse(data);
            var contentImgFileUrl = dataJson['url'];
            var messageContent = dataJson['message']['content'];
            var messageType = dataJson['message']['type'];
            if(messageType == 'success'){
		        $.message("success", "数据导入成功！");
	        	location.reload();
            }else{
                $.message("warn", messageContent);
            }
        },
	    onUploadStart :function(){
    		layer.load('文件处理中,请稍等。。。');
	    },  
	    onError: function(event, queueID, fileObj) {     
	        alert("文件:" + fileObj.name + "上传失败");     
	    }
    });
	<!-- end 上传图集 -->
});

//弹出学生卡号
function showCardDiv(id,name){
	$("#cardDiv").fadeIn();
	$("#cardStudentId").val(id);
	$("#cardDivTitle").html(name);
	$("#addCardBUtton").removeClass("disabled");
	$("#studentId").html(id);
	$("#cardsTable").html("<tr><th>卡号</th><th>家长</th><th>状态</th><th>操作</th></tr>");
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/getCards.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(cards){
			if(cards==null||cards.length==0){
				$("#cardsTable").append("<tr><td colspan='4'>无数据</td></tr>");
				return;
			}
			else{
				for(num in cards){
					var card = cards[num];
					var status = card.cardStatus;
					var handle="";
					if(status=="normal"){
						status = "<span style='color:green;'>正常</span>";
						handle="<a onclick='cardChangeStatus("+card.id+",\"disable\")'>[禁用]</a><a onclick='cardChangeStatus("+card.id+",\"loss\")'>[挂失]</a>";
					}
					else if(status=="disable"){
						status = "<span style='color:red;'>禁用</span>";
					}
					else if(status=="loss"){
						status = "<span style='color:darkgray;'>挂失</span>";
						handle="<a onclick='cardChangeStatus("+card.id+",\"disable\")'>[禁用]</a><a onclick='cardChangeStatus("+card.id+",\"normal\")'>[解挂]</a>";
					}					
					var contentTD = "<tr><td>"+card.cardNumber+"</td><td>"+card.member.realName+"</td><td>"+status+"</td><td>";
					contentTD += handle;
					contentTD += "</td></tr>";
					$("#cardsTable").append(contentTD);
				}
			}
			
		}
	});
	//设置家长
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/getPatriarches.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(members){
			$("#patriarchId").html("");
			if(members==null||members.length==0){
				$("#patriarchId").append("<option value=''>请先关联家长</option>");
				$("#addCardBUtton").addClass("disabled");
				return;
			}
			else{
				for(num in members){
					$("#patriarchId").append("<option value='"+members[num].id+"'>"+members[num].realName+"</option>");
				}
			}
			
		}
	});
	//判断有是否有状态正常的卡
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/getNormalByStudent.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(num){
			$("#normalCount").val(num);
			if(num>0){
				$("#addCardBUtton").addClass("disabled");
			}
		}
	});
	
}
function closeCardDiv(){
	$("#cardDiv").fadeOut();
}

function submitCard(){
	var id = $("#cardStudentId").val();
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/addCard.ct",
		data: {
		    studentId:id,
		    cardNumber:$("#cardNumber").val(),
		    memberId:$("#patriarchId").val()
		},
		dataType: "json",
		success:function(mark){
			if(mark=="success"){
				var name = $("#cardDivTitle").html();
				$.message("success", "数据导入成功！");
				showCardDiv(id,name);
				$.message("success", "操作成功！");
			}
			
		}
	});
	
}

//操作改变卡的状态
function cardChangeStatus(cardId,status){
	if(status=="normal"){
		var normalCount = $("#normalCount").val();
		if(normalCount>0){
			$.message("warn", "当前学生存在正常状态卡!");
			return false;
		}
	}
	$.ajax({
		type:"GET",
		url:"${base}/console/timeCard/cardChangeStatus.ct",
		data:{
			carId:cardId,
			status:status
		},
		dataType:"json",
		success:function(){
			var studentId = $("#studentId").html();
			var name = $("#cardDivTitle").html();
			$.message("warn", "操作成功!");
			showCardDiv(studentId,name);
		}
	});
}

//静态化毕业相册
function staticGraduationAlbum(id){
	$.ajax({
		type: "GET",
		url: "${base}/console/dict_student/buildDictStudent.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(data){
			if(data=="success"){
			  $.message("warn", "操作成功!");
			}
		}
	});
}


</script>
</head>
<body>
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
                    <h2>${message("console.dictStudent.list")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                           <strong>${message("console.dictStudent.list")} <span>(${message("console.page.total", page.total)})</span></strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                
                </div>
            </div>
	       	<!-- end 头部面包屑区域 -->
	       	<!--start card弹出层 -->
	       	<div id="cardDiv" class="modal in" style="overflow: auto; display: none;" tabindex="-1"
			aria-hidden="false">
			    <div class="modal-backdrop in" onclick="closeCardDiv()"></div>
			    <div class="modal-dialog">
			        <div class="modal-content">
			        	<input type="hidden" id="normalCount" value="0">
			            <div class="modal-header">
			                <a type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeCardDiv()">
			                    ×
			                </a>
			                <h4 class="modal-title">
			                    <a id="studentId" style="display:none;"></a><a id="cardDivTitle"></a>同学的卡号列表:
			                </h4>
			            </div>
			            <div class="modal-body ui-front">
            	 			<table id="cardsTable" class="table table-striped">
	            	 			<tr>
	            	 				<th>卡号</th>
	            	 				<th>家长</th>
	            	 				<th>状态</th>
	            	 				<th>操作</th>
	            	 			</tr>
	            	 		</table>
	            	 		<h3 class="modal-title" style="border-top: solid 1px lightgray;margin-top: 10px;padding-top: 10px;padding-bottom: 10px;">
			                    添加卡号：
			                </h3>
			               	<div class="input-group" style="width:100%;margin-bottom: -30px;">
			               		<input type="hidden" id="cardStudentId">
			               		<table class="table table-striped">
			               			<tr>
			               				<td><label>卡号:</label></td>
			               				<td>
			               					<input class="form-control" id="cardNumber"/>
		               					</td>
		               					<td>
											<select placeholder="请选择关联家长" id="patriarchId" class="form-control" style="width:100%;">
	                                        </select>
		               					</td>
			               				<td>
			               					<button onclick="submitCard()" id="addCardBUtton" class="btn btn-sm btn-primary" >确认</button>
	               						</td>
			               			</tr>
			               		</table>
			               	</div>
			            </div>
			        </div>
			    </div>
			</div>
	       	<!--end   card弹出层 -->
	       
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
        		<form id="listForm" action="list.ct" method="get">
             	<!-- start  地区管理 -->
             	<div class="row">
           	 		<div class="col-lg-12">
                    	<div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <div class="row">
                                    <div class="col-sm-10 m-b-xs">
                                    	<div class="btn-group">
	                                       	[@shiro.hasPermission name = "console:addDictStudent"]
	                                        <a href="add.ct" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("console.common.add")}
											</a>
											<a href="${base}/console/excel/downloadExcel.ct?fileName=student.xls" class="btn btn-primary">
												<span class="addIcon">&nbsp;</span>${message("common.downloadTemplate")}
											</a>
										   	[/@shiro.hasPermission]
	                                        [@shiro.hasPermission name = "console:deleteDictStudent"]
												<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
													<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
												</a>
											[/@shiro.hasPermission]
											<a href="javascript:;" id="refreshButton" class="btn btn-primary">
												<span class="refreshIcon">&nbsp;</span>${message("console.common.refresh")}
											</a>
											<div class="btn-group">
		                                        <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" aria-expanded="false">${message("console.page.pageSize")} <span class="caret"></span>
		                                        </button>
		                                        <ul class="dropdown-menu" id="pageSizeOption">
													<li>
														<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
													</li>
													<li>
														<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
													</li>
													<li>
														<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
													</li>
													<li>
														<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
													</li>
												</ul>
	                                       </div>
										</div>
										[@shiro.hasPermission name = "console:addDictStudent"]
										<div class="btn-group">
									  		<input type="file" id="batchSaveStudentUpload" class="btn btn-primary"/>
										</div>
										[/@shiro.hasPermission]
										<div class="input-group pull-right">
										<form action="list.ct" method="get">
											<input type="text" name="searchName" value="${searchName}" class="input-sm form-control" style="width:100px;margin-top: 12px;" placeholder="搜索学生姓名">
											<input type="text" name="studentNo" value="${studentNo}" class="input-sm form-control" style="width:150px;margin-top: 12px;" placeholder="搜索学生学号">
											<input type="submit" class="btn btn-primary" value="搜索"/>
										</form>
										</div>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                     <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
												<tr>
													<th class="check">
														<input type="checkbox" id="selectAll" />
													</th>
													<th>
														<a href="javascript:;" class="sort" name="dictClass">${message("DictStudent.dictClass")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="studentNo">${message("DictStudent.studentNo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="studentName">${message("DictStudent.studentName")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="gender">${message("DictStudent.gender")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="birthday">${message("DictStudent.birthday")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="studentStatus">${message("DictStudent.studentStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="stuDate">${message("DictStudent.stuDate")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="stuAddress">${message("DictStudent.stuAddress")}</a>
													</th>
													
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("console.common.createDate")}</a>
													</th>
													<th>
														<span>${message("console.common.handle")}</span>
													</th>
												</tr>
												[#list page.content as dictStudent]
													<tr>
														<td>
															<input type="checkbox" name="ids" value="${dictStudent.id}" />
														</td>
														<td>
															${dictStudent.dictClass.name}
														</td>
														<td>
															${dictStudent.studentNo}
														</td>
														<td>
															${dictStudent.studentName}
														</td>
														<td>
															[#if dictStudent.gender ??]
														    ${message("DictStudent.gender.${dictStudent.gender}")}
														    [/#if]
														</td>
														<td>  
														    [#if dictStudent.birthday??]
															    ${dictStudent.birthday?string("yyyy-MM-dd")}
															[#else]
															    -
															[/#if]
														</td>
														<td>
															[#if dictStudent.studentStatus=="active"]
															    ${message("console.dictStudent.status.active")}
															[#elseif dictStudent.studentStatus=="graduated"]
																${message("console.dictStudent.status.graduated")}
															[#elseif dictStudent.studentStatus=="quit"]
																${message("console.dictStudent.status.quit")}
															[#elseif dictStudent.studentStatus=="dropouts"]
															    ${message("console.dictStudent.status.dropouts")}
															[/#if]
														</td>
														<td>
															${dictStudent.stuDate}
														</td>
														<td>
															${dictStudent.stuAddress}
														</td>
														<td>
															<span title="${dictStudent.createDate?string("yyyy-MM-dd HH:mm:ss")}">${dictStudent.createDate}</span>
														</td>
														<td>
														[@shiro.hasPermission name = "console:editDictStudent"]
															<a href="edit.ct?id=${dictStudent.id}">[${message("console.common.edit")}]</a>
															<a href="staticEdit.ct?id=${dictStudent.id}">[${message("制作毕业相册")}]</a>
															<!--<a href="#" onclick="staticGraduationAlbum(${dictStudent.id})">[${message("静态化毕业相册")}]</a>-->
														[/@shiro.hasPermission]
														</td>
													</tr>
												[/#list]
											</table>
											[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
												[#include "/console/include/pagination.ftl"]
											[/@pagination]
	                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
             	<!-- end 地区管理 -->
             	</form>
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>