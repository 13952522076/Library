<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("升级年级")} - 小书僮™智慧幼教管理平台</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<link href="${base}/resources/console/css/plugins/chosen/chosen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/plugins/chosen/chosen.jquery.js"></script>
<style type="text/css">

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
    
    var $selectAll = $("#selectAll");
    // 全选
    $selectAll.click(function() {
        var $this = $(this);
        var $enabledIds = $("input[name='classIds']:enabled");
        if ($this.prop("checked")) {
            $enabledIds.prop("checked", true);
        } else {
            $enabledIds.prop("checked", false);
        }
    });
    
    //验证班级名称是否已经存在
    $('#cardDiv').on('blur', '.dictClassName',
    function() {
        var $this = $(this);
        var className = $this.val();
        $.ajax({
            type: "GET",
            url: "isExistCName.ct",
            data: {
                className: className
            },
            dataType: "json",
            success: function(isExist) {
                if (isExist) {
                    $this.parent('td').next().text("已存在");
                } else {
                    $this.parent('td').next().text("");
                }
            }
        });
    });

    $('#cardDiv').on('submit', '#upgradeSave',
    function() {
        var flag = true;
        $(".showExist").each(function() {
            if ($(this).prev('td').find('input').val() == "") {
                $(this).prev('td').find('input').focus();
                flag = false;
                return flag;
            } else {
                if ($(this).text() == '已存在') {
                    $(this).prev('td').find('input').focus();
                    flag = false;
                    return flag;
                } else {
                    flag = true;
                    return flag;
                }
            }
        });
        return flag;
    });
    
});

function changeName(flag){
	//获取到选中的班级ids
	var classArr=document.getElementsByName("classIds");
	if(classArr ==null || classArr.length == 0){
		$("#gradeNameNotice").show();
		return ;
	}
	var classIds = new Array();
	for(i=0;i<classArr.length;i++){
		if(classArr[i].checked){
			var a = classArr[i].value;
			classIds.push(a);
		}
	}
	if(classIds.length == 0){
		$("#gradeNameNotice").show();
		return ;
	}
	if(flag == 0){
		if($("#updictGradeId").val() == null || $("#updictGradeId").val() == ""){
			$("#gradeNotice").show();
			return ;
		}
	}
	$.ajax({
		type: "GET",
		url: "changeName.ct",
		data: {
		    classIds:classIds
		},
		success:function(data){
			showDiv();
			$("#dictGradeId").val($("#updictGradeId").val());
			$("#flag").val(flag);
			var tableContent = '<tr><th>原班级名</th><th>升级班级名</th><th style="width:25px"></th></tr>';
			var dataJson = JSON.parse(data);
			var dictClasses = dataJson["dictClasses"];
			if(dictClasses!=null && dictClasses.length>0){
				for(var i=0;i<dictClasses.length;i++){
					var dictClass = dictClasses[i];
					tableContent+='<input type="hidden" name="dictClassIds" value="'+dictClass.id+'">';
					tableContent = tableContent + 
						'<tr><td>'+dictClass.name+'</td><td><input class="dictClassName" name="'+dictClass.id+'" class="form-control"/></td><td class="showExist" style="font-weight:700;color:#ffb042"></td></tr>';
				}
				tableContent = tableContent +'<tr><td>&nbsp;</td><td><input id="className" type="submit" value="确认" class="btn  btn-primary"></td></tr>';
			}
			$("#nameTable").html(tableContent);
			
		}
	});
}

function closeCardDiv(){
	$("#cardDiv").fadeOut();
}
//弹出弹出层div
function showDiv(){
	$("#cardDiv").fadeIn();
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
                    <h2> ${message("升级年级")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a>
                        </li>
                        <li>
                            <strong>${message("升级年级")}</strong>
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
			    <form id="upgradeSave" action="upgradeSave.ct" method="post">
			    <input type="hidden" name="dictGradeId" id="dictGradeId">
			    <input type="hidden" name= "flag" id="flag">
			    <div class="modal-dialog">
			        <div class="modal-content">
			        	
			            <div class="modal-header">
			                <a type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeCardDiv()">
			                    ×
			                </a>
			                <h4 class="modal-title">
			                    班级名称升级修改:
			                </h4>
			            </div>
			            <div class="modal-body ui-front">
            	 			<table id="nameTable" class="table table-striped">
	            	 			<tr>
	            	 				<th>原班级名</th>
	            	 				<th>升级班级名</th>
	            	 				<th></th>
	            	 			</tr>
	            	 			<tr><td>&nbsp;</td><td><input type="submit" value="确认" class="btn  btn-primary"></td></tr>
	            	 		</table>
	            	 		
			            </div>
			        </div>
			    </div>
			    </form>
			</div>
	       	<!--end   card弹出层 -->
	       
	        <!-- start 中间内容部分 -->
	        <div class="wrapper wrapper-content animated fadeIn">
	             <!-- start 升级年级-->
	             <form id="inputForm" action="changeName.ct" method="post">
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content" style="width:80% ;margin:0 auto;">
	                                <div class="table-responsive">
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th>
													<span class="requiredField">*</span>${message("当前年级")}:
												</th>
												<td>
													<input type="checkbox" id="selectAll">
													<input type="hidden" name="predictGradeId" value="${dictGrade.id}">
													<label for="selectAll">${dictGrade.gradeName}</label>
													<label style="display:none;" id="gradeNameNotice" class="text-warning">请选择相关班级</label>
												</td>
											</tr>
											<tr>
												<th>
													&nbsp;
												</th>
												<td style="padding-left:25px;">
													[#list dictClasses as dictClass]
														<label>
															<input type="checkbox" name="classIds" value="${dictClass.id}">
															${dictClass.name}
														</label>
														<br>
													[/#list]
												</td>
											</tr>
											<tr>
												<th>
													<span class="requiredField">*</span>${message("升级年级")}:
												</th>
												<td>
													<select data-placeholder="升级年级" id="updictGradeId" name="updictGradeId" class="chosen-select" style="width:200px;"  tabindex="4">
		                                            <option></option>
		                                            [#list dictGrades as dictGrades]
		                                            <option value="${dictGrades.id}" hassubinfo="true" >${dictGrades.gradeName}</option>
		                                            [/#list]
		                                        	</select>
		                                        	<a onclick="changeName(0)" class="btn btn-primary">升年级</a>
		                                        	<a onclick="changeName(1)" class="btn btn-primary">毕业</a>
		                                        	<label class="text-warning" style="display:none;" id="gradeNotice">请选择升级年级</label>
												</td>
											</tr>
										</table>
										<table class="input" style="margin-top:130px;">
											<tr>
												<th>
													&nbsp;
												</th>
												<td>
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
	             <!-- end 升级年级-->
	        </div>
	       <!-- end 中间内容部分-->
	       [#include "/console/include/footer.ftl" /]
  </div>
</div>
<script type="text/javascript">
   
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