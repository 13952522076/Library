<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("应用授权")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src = "${base}/resources/console/js/ligerUI/js/ligerui.all.js"></script>
<link rel="stylesheet" href="${base}/resources/console/css/ligerUI/css/ligerui-all.css" />
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
.itemList-upload{
	left:650px;
	top:45px;
	width:340px;
	padding:8px;
	border-radius:2px;
	border:1px solid #D6D6D6;
}
.newsPreview{
	width:320px;
	height:200px;
}
.imgShowBox{
	position:relative;
}
.tipDiv {
  float: left;
  margin-top: 10px;
  color: #BCBCBC;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $school_id = $("#school_id");
	var $allSchools = $("#allSchools");
	var $selectAllSchool = $("#selectAllSchool");
	[@flash_message /]
	
	//ligerui初始化学校选择按钮
	$school_id.ligerComboBox({
		data: [
                { text: '请选择学校', id: 'default' }
              ],
		onBeforeOpen: _selectSchool, 
		valueFieldID: 'school_id', 
		width:100,
		height:20,
		value:"default",
		cancelable: false
	});
	
	//选择所有学校
	$selectAllSchool.click(function(){
		var $this = $(this);
		if ($this.prop("checked")){
			$allSchools.val("allSchools");
			$school_id.attr("disabled","disabled");
			$(".l-text-wrapper").hide();
			
		}
		else{
			$allSchools.val("");
			$school_id.removeAttr("disabled");
			$(".l-text-wrapper").show()
		}
	});
	
});

	//选择学校
	function _selectSchool()
	{
		var dictSchoolIds = $("#dictSchoolIds").val();
		if($("#supselect").val()==="0")
		{
		    $.ligerDialog.warn('${message("请选择学校")}');
			return;
		}
	    $.ligerDialog.open({ title: '${message("选择学校")}', name:'selectSchool',width: 700, height:480, url: '${base}/console/app/schoolList.ct?dictSchoolIds='+dictSchoolIds, buttons: [
			{ text: '${message("确定")}', onclick: _selectSchoolOK },
			{ text: '${message("取消")}', onclick: _selectSchoolCancel }
		]
		});
		return false;
	}
	
	//弹出dialog确定按钮
	function _selectSchoolOK(item, dialog)
	{
		var data= document.getElementById('selectSchool').contentWindow._select();
		if (!data || data.length < 1)
		{
			$.ligerDialog.warn('${message("请选择学校")}');
			return;
		}
		var trs = "";
		for(var i = 0; i < data.length; i++)
		{
			var school = data[i];
		    trs += '<tr class="schoolTr" id="schoolTr_'+school.id+'">'+
			                '<input type="hidden" class="schoolId" name="schoolIds" value="'+school.id+'">'+
			                '<td>'+
			                	school.code+
		                	'</td>'+
			                '<td>'+
			                	school.name+
		                	'</td>'+
		                	'<td style="vertical-align: middle;"><a href="javascript:void(0);" onclick="deleteTr(this)">[${message("删除")}]</a>'+
							'</td>'+
			        '</tr>';
		}
		$("#listTbody").html("");
		$("#listTbody").append(trs);
		dialog.close();
	}
	
	//弹出dialog取消按钮
	function _selectSchoolCancel(item, dialog)
	{
		dialog.close();
	}
	
	
	function contains(a, obj){
	  for(var i = 0; i < a.length; i++) {
	    if(a[i] === obj){
	      return true;
	    }
	  }
	  return false;
	}
	
	//删除选择行
	function deleteTr(obj){
		var id = obj.parentElement.parentElement.children[0].value;
		$("#schoolTr_"+id).remove();
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
                    <h2> ${message("应用授权")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("应用授权")}</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
	       <!-- end 头部面包屑区域 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 应用授权-->
	             <form id="inputForm" action="updateAuthorization.ct" method="post">
	             	<input type="hidden" name="id" value="${app.id}">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content" style="margin:0 auto;">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <table class="table table-striped">
											 <tr>
												<th>
													${message("应用角色")}:
												</th>
												<td>
													<div class="col-sm-9">
													[#list appRoles as appRole]
							                            <label class="radio-inline">
							                                <input type="checkbox" class="checkbox checkbox-inline" name="appRoleIds" value="${appRole.id}" [#if app.appRoles?seq_contains(appRole)] checked="checked" [/#if] >
							                                ${appRole.name}
						                                </label>
						                                [#if (appRole_index+1)%3 == 0]
											               <p></p>
											           [/#if]
						                             [/#list]
						                            </div>
												</td>
											</tr>
											<tr>
												<th>
													${message("应用授权学校")}:
												</th>
												<td>
													<div class="col-sm-9" style="width: 120px;">
														<label class="radio-inline">
							                                <input type="checkbox" class="checkbox checkbox-inline" id="selectAllSchool"/>
							                                <input type="hidden" id="allSchools" name="allSchools" value=""/>
							                                是否全选
						                                </label>
					                                </div>
					                                <div style="float: left;">
														<input type='text' id="school_id" name="school_id" value='${message("请选择学校")}' style="font-size:12px;height:20px;"/>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													已选择学校:
												</th>
												<td>
													<table class="table table-striped" id="listTable">
														<thead>
															<input type="hidden" value="${dictSchoolIds}" id="dictSchoolIds"/>
															 <tr>
																<th>
																	${message("学校编号")}:
																</th>
																<th>
																	${message("学校名称")}:
																</th>
																<th>
																	<span>${message("console.common.handle")}</span>
																</th>
															</tr>
														</thead>
														<tbody id="listTbody">
														[#list dictschools as dictschool]
														<tr class="schoolTr" id="schoolTr_${dictschool.id}">
			                								<input type="hidden" class="schoolId" name="schoolIds" value="${dictschool.id}">
											                <td>
											                	${dictschool.code}
										                	</td>
											                <td>
											                	${dictschool.name}
										                	</td>
										                	<td style="vertical-align: middle;"><a href="javascript:void(0);" onclick="deleteTr(this)">[${message("删除")}]</a>
															</td>
														</tr>
														[/#list]
														</tbody>
													</table>
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
													<input type="button" class="btn btn-white" value="${message("console.common.back")}" onclick="location.href='edit.ct?id=${app.id}'" />
												</td>
											</tr>
										</table>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </form>
	             <!-- end 应用授权-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
</body>
</html>