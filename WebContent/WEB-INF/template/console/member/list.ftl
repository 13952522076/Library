<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.${memberType}.list")}</title>
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
	var $batchSaveMemberUpload = $("#batchSaveMemberUpload");
    $batchSaveMemberUpload.uploadify({  
        'successTimeout' : 50000,
        'height'        : 34,   
        'width'         : 80,    
        'buttonText'    : '${message("common.batchImport")}',  
        'swf'           : '${base}/resources/console/uploadify/uploadify.swf',  
        'uploader'      : '${base}/console/member/batchSaveMember.ct?fileType=file',  
        'auto'          : true,
        'multi'          : true, //是否支持多文件上传  
	    'simUploadLimit' : 1, //一次同步上传的文件数目     
	    'sizeLimit'      : 19871202, //设置单个文件大小限制     
	    'queueSizeLimit' : 1, //队列中同时存在的文件个数限制
	    'fileObjName'    :  'file',
	    'fileTypeDesc'  :  '*.xls',//选择描述  
        'fileTypeExts'  :  '*.xls',//允许的格式      
        'formData'      : {'token' : getCookie("token"),'memberType': '${memberType}'},
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


//弹出卡号层
function showCardDiv(id,name){
	$("#cardDiv").fadeIn();
	$("#cardpatriarchId").val(id);
	$("#cardDivTitle").html(name);
	$("#cardNumber").val("");
	$("#addCardBUtton").removeClass("disabled");
	$("#studentId").html(id);
	$("#cardsTable").html("<tr><th>卡号</th><th>学生</th><th>状态</th><th>操作</th></tr>");
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/getCardsByMember.ct",
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
					var contentTD = "<tr><td>"+card.cardNumber+"</td><td>"+card.dictStudent.studentName+"</td><td>"+status+"</td><td>";
					contentTD += handle;
					contentTD += "</td></tr>";
					$("#cardsTable").append(contentTD);
				}
			}
			
		}
	});
	//设置学生
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/getDictStudents.ct",
		data: {
		    id:id
		},
		dataType: "json",
		success:function(DictStudents){
			$("#dictStudentId").html("");
			if(DictStudents==null||DictStudents.length==0){
				$("#dictStudentId").append("<option value=''>请先关联学生</option>");
				$("#addCardBUtton").addClass("disabled");
				return;
			}
			else{
				for(num in DictStudents){
					$("#dictStudentId").append("<option value='"+DictStudents[num].id+"'>"+DictStudents[num].studentName+"</option>");
				}
			}
			
		}
	});
	
}
function closeCardDiv(){
	$("#cardDiv").fadeOut();
}

function submitCard(){
	var id = $("#cardpatriarchId").val();
	var cardNumber = $("#cardNumber").val();
	if(cardNumber==null||cardNumber==""){
		$.message("warn", "卡号不能为空");
		return;
	}
	$.ajax({
		type: "GET",
		url: "${base}/console/timeCard/addCardMember.ct",
		data: {
		   	memberId:id,
		    cardNumber:$("#cardNumber").val(),
		    studentId:$("#dictStudentId").val()
		},
		dataType: "json",
		success:function(mark){
			if(mark=="success"){
				var name = $("#cardDivTitle").html();
				$.message("success", "操作成功！");
				showCardDiv(id,name);
				$("#cardNumber").val("");
			}
			else{
				var name = $("#cardDivTitle").html();
				showCardDiv(id,name);
				$.message("warn", mark);
				$("#cardNumber").val("");
			}
			
		}
	});
	
}

//操作改变卡的状态
function cardChangeStatus(cardId,status){
/*	if(status=="normal"){
		$.ajax({
			type:"GET",
			url:"${base}/console/timeCard/cardChangeValidate.ct",
			data:{
				carId:cardId
			},
			dataType:"json",
			success:function(data){
				if(data==true){
					$.message("warn", "当前学生存在正常状态卡!");
					return;
				}
			}
		});
	}*/
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
                    <h2>${message("console.${memberType}.list")} </h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("console.${memberType}.list")}  <span>(${message("console.page.total", page.total)})</span></strong>
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
			            <div class="modal-header">
			                <a type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeCardDiv()">
			                    ×
			                </a>
			                <h4 class="modal-title">
			                    <a id="studentId" style="display:none;"></a><a id="cardDivTitle"></a>的卡号列表:
			                </h4>
			            </div>
			            <div class="modal-body ui-front">
            	 			<table id="cardsTable" class="table table-striped">
	            	 			<tr>
	            	 				<th>卡号</th>
	            	 				<th>学生</th>
	            	 				<th>状态</th>
	            	 				<th>操作</th>
	            	 			</tr>
	            	 		</table>
	            	 		<h3 class="modal-title" style="border-top: solid 1px lightgray;margin-top: 10px;padding-top: 10px;padding-bottom: 10px;">
			                    添加卡号：
			                </h3>
			               	<div class="input-group" style="width:100%;margin-bottom: -30px;">
			               		<input type="hidden" id="cardpatriarchId">
			               		<table class="table table-striped">
			               			<tr>
			               				<td><label>卡号:</label></td>
			               				<td>
			               					<input class="form-control" id="cardNumber"/>
		               					</td>
		               					<td>
											<select placeholder="请选择关联学生" id="dictStudentId" class="form-control" style="width:100%;">
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
				<!-- start  管理员列表管理 -->
				<form id="listForm" action="list.ct" method="get">
					<div class="row">
						<div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-12 m-b-xs">
											<div class="btn-group">
		                                        [@shiro.hasPermission name = "console:addPatriarch"]
													<a href="add.ct?memberType=${memberType}" class="btn btn-primary">
														<span class="addIcon">&nbsp;</span>${message("console.common.add")}
													</a>
													<a href="${base}/console/excel/downloadExcel.ct?fileName=member.xls" class="btn btn-primary">
														<span class="addIcon">&nbsp;</span>${message("common.downloadTemplate")}
													</a>
												[/@shiro.hasPermission]
												[@shiro.hasPermission name = "console:deletePatriarch"]
													<a href="javascript:;" id="deleteButton" class="btn btn-primary disabled">
														<span class="deleteIcon">&nbsp;</span>${message("console.common.delete")}
													</a>
												[/@shiro.hasPermission]
												<a href="javascript:;" id="refreshButton"  class="btn btn-primary">
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
										 	[@shiro.hasPermission name = "console:addPatriarch"]
											<div class="btn-group">
											  <input type="file" id="batchSaveMemberUpload" class="btn btn-primary"/>
											</div>
											[/@shiro.hasPermission]
											<div class="input-group pull-right">
											<form action="list.ct" method="get">
												<input type="text" name="searchName" value="${searchName}" class="input-sm form-control" style="width:100px;margin-top: 12px;" placeholder="搜索家长姓名">
												<input type="text" name="mobile" value="${mobile}" class="input-sm form-control" style="width:150px;margin-top: 12px;" placeholder="搜索家长手机">
												<input type="submit" class="btn btn-primary" value="搜索"/>
											</form>
											</div>
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th class="check">
													<input type="checkbox" id="selectAll" />
												</th>
												<th>
													<a href="javascript:;" class="sort" name="username">${message("Member.username")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="mobile">${message("Member.mobile")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="realName">${message("Admin.realName")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="isEnabled">${message("Member.isEnabled")}</a>
												</th>
												<th>
													<span>${message("console.common.handle")}</span>
												</th>
											</tr>
											[#list page.content as member]
												<tr>
													<td>
														<input type="checkbox" name="ids" value="${member.id}" />
													</td>
													<td>
														${member.username}
													</td>
													<td>
														${member.mobile}
													</td>
													<td>
														${member.realName}
													</td>
													<td>
														[#if !member.isEnabled]
															<span class="red" style="color:red;">${message("console.member.disabled")}</span>
														[#else]
															<span class="green">${message("console.member.normal")}</span>
														[/#if]	
													</td>
													<!--
													<td>
														[#if member.memberType="teacher"]
															${message("console.common.teacher")}
														[#elseif member.memberType="patriarch"]
															${message("console.common.patriarch")}
														[/#if]	
													</td>
													-->
													<td>
													[@shiro.hasPermission name = "console:editPatriarch"]
														<a href="edit.ct?id=${member.id}&memberType=${memberType}">[${message("console.common.edit")}]</a>
													[/@shiro.hasPermission]
													<a href="#" onclick="showCardDiv(${member.id},'${member.realName}')">[${message("卡号管理")}]</a>
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
                </form>
	             <!-- end 管理员列表-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>