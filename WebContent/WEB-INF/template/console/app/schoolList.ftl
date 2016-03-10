<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.dictSchool.list")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $ids = $("#listTable input[name='ids']");
	var $selectAll = $("#selectAll");
	var $alreadyChoiceSchoolIdArr = [];
	var $alreadyChoiceSchoolArr = [];
	var $alreadyChoiceSchoolIds = $("#alreadyChoiceSchoolIds");
	var $alreadyChoiceSchools = $("#alreadyChoiceSchools");
	//var $alreadyChoiceCount = $("#alreadyChoiceCount");
	[@flash_message /]
	
	<!-- start 已经选择的productId 重新放入数组 -->
	[#if alreadyChoiceSchoolIds??]	
		[#list alreadyChoiceSchoolIds as alreadyChoiceSchoolId]
		    if($alreadyChoiceSchoolIdArr.indexOf(${alreadyChoiceSchoolId}) == -1){
			    $alreadyChoiceSchoolIdArr.push(${alreadyChoiceSchoolId});
			}
		[/#list]
	[/#if]
	$alreadyChoiceSchoolIds.val($alreadyChoiceSchoolIdArr);
	//$alreadyChoiceCount.text($alreadyChoiceSchoolIdArr.length);
	
	[#if alreadyChoiceSchools??]
		[#list alreadyChoiceSchools as alreadyChoiceSchool]
		var school = new Object();
	    school.id = '${alreadyChoiceSchool.id}';
	    school.code = '${alreadyChoiceSchool.code}';
	    school.name = '${alreadyChoiceSchool.name}';
	    $alreadyChoiceSchoolArr.push(school);
		[/#list]
		$alreadyChoiceSchools.val(JSON.stringify($alreadyChoiceSchoolArr));
	[/#if]
    <!-- end 已经选择的productId 重新放入数组 -->
	
	// 全选
	$selectAll.click(function() {
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		var $dictSchoolIds = $("#dictSchoolIds").val();
		var dictSchoolIds = $("#dictSchoolIds").val();
		$enabledIds.each(function(){
		    var $this = $(this);
		    var $currSchoolId = $this.val();
		    if ($this.prop("checked")) {
				$this.closest("tr").addClass("selected");
				if($alreadyChoiceSchoolIdArr.indexOf(parseInt($currSchoolId)) == -1){
				    $alreadyChoiceSchoolArr.push(_getChoiceSchool($this));
				    $alreadyChoiceSchoolIdArr.push(parseInt($currSchoolId));
				    //$dictSchoolIds.val(dictSchoolIds+","+$currSchoolId);
				}
			} else {
				$this.closest("tr").removeClass("selected");
				$alreadyChoiceSchoolIdArr.splice($.inArray(parseInt($currSchoolId),$alreadyChoiceSchoolIdArr),1);
				//$alreadyChoiceSchoolArr.splice($.inArray(_getChoiceSchool($this),$alreadyChoiceSchoolArr),1);
				$alreadyChoiceSchoolArr.splice(getIndex(parseInt($currSchoolId),$alreadyChoiceSchoolArr),1);
			}
	   });
	   
	   $alreadyChoiceSchoolIds.val($alreadyChoiceSchoolIdArr);
	   $alreadyChoiceSchools.val(JSON.stringify($alreadyChoiceSchoolArr));
	   //$alreadyChoiceCount.text($alreadyChoiceSchoolIdArr.length);
	});
	
	// 选择
	$ids.click( function() {
		var $this = $(this);
		var $currSchoolId = $this.val();
		var $dictSchoolIds = $("#dictSchoolIds").val();
		var dictSchoolIds = $("#dictSchoolIds").val();
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			if($alreadyChoiceSchoolIdArr.indexOf(parseInt($currSchoolId)) == -1){
			    $alreadyChoiceSchoolIdArr.push(parseInt($currSchoolId));
			    $alreadyChoiceSchoolArr.push(_getChoiceSchool($this));
			    //$dictSchoolIds.val(dictSchoolIds+","+$currSchoolId);
			}
		} else {
			$this.closest("tr").removeClass("selected");
			$alreadyChoiceSchoolIdArr.splice($.inArray(parseInt($currSchoolId),$alreadyChoiceSchoolIdArr),1);
			//$alreadyChoiceSchoolArr.splice($.inArray(_getChoiceSchool($this),$alreadyChoiceSchoolArr),1);
			$alreadyChoiceSchoolArr.splice(getIndex(parseInt($currSchoolId),$alreadyChoiceSchoolArr),1);
		}
		 $alreadyChoiceSchoolIds.val($alreadyChoiceSchoolIdArr);
		 
		 $alreadyChoiceSchools.val(JSON.stringify($alreadyChoiceSchoolArr));
		 //$alreadyChoiceCount.text($alreadyChoiceSchoolIdArr.length);
	});

});

	/**
	 * 获取选择的学校信息，构建school对象
	 */
	function _getChoiceSchool(obj){
	     var school = new Object();
	     var schoolId =  obj.val();
	     school.id = schoolId;
	     school.code = $("#code_" + schoolId).val();
	     school.name = $("#name_" + schoolId).val();
	     return school;
	}

	/**
	 * 父窗口返回函数
	 */
    function _select(){
	     var $alreadyChoiceSchools  = $("#alreadyChoiceSchools").val();
	     return JSON.parse($alreadyChoiceSchools);
	}
	
	function getIndex($currSchoolId,$alreadyChoiceSchoolArr){
		
		for(var i=0,l=$alreadyChoiceSchoolArr.length;i<l;i++){
			var obj = $alreadyChoiceSchoolArr[i];
		    if($currSchoolId == obj.id){
		    	return i;
		    }
		}
	}

</script>
</head>
<body>
	    <!-- start 中间内容部分 -->
		<div class="wrapper wrapper-content animated fadeIn">
			<div class="col-lg-10">
	            <ol class="breadcrumb">
	                <li>
	                   <strong>${message("console.dictSchool.list")} <span>(${message("console.page.total", page.total)})</span></strong>
	                </li>
	            </ol>
	        </div>
	        <!-- start  学校管理 -->
	        <form id="listForm" action="schoolList.ct" method="get">
	        	<input type="hidden" class="text" name="alreadyChoiceSchoolIds" value="" id="alreadyChoiceSchoolIds" />
			    <input type="hidden" class="text" name="alreadyChoiceSchools" value="" id="alreadyChoiceSchools" />
			    <input type="hidden" class="text" name="dictSchoolIds" value="${dictSchoolIds}" id="dictSchoolIds" />
	         	<div class="row">
	            	<div class="col-lg-12">
	                	<div class="ibox float-e-margins">
	                    	<div class="ibox-content">
	                        	<div class="row">
	                            	<div class="col-sm-4 m-b-xs">
	                                	<div class="btn-group">
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
													<a href="javascript:;" class="sort" name="code">${message("DictSchool.code")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="name">${message("DictSchool.name")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="kindergartenName">${message("DictSchool.kindergartenName")}</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="createDate">${message("console.common.createDate")}</a>
												</th>
											</tr>
											[#list page.content as dictSchool]
											<tr>
												<td>
													<input type="checkbox" name="ids" 
													[#if alreadyChoiceSchools??]
														   [#list alreadyChoiceSchoolIds as alreadyChoiceSchoolId]
														       [#if alreadyChoiceSchoolId == dictSchool.id ] checked="checked"[/#if]
														   [/#list]
													 [/#if]  
													value="${dictSchool.id}" />
													<input type="hidden" id="code_${dictSchool.id}" value="${dictSchool.code}" />
													<input type="hidden" id="name_${dictSchool.id}" value="${dictSchool.name}" />
												</td>
												<td>
													${dictSchool.code}
												</td>
												<td>
													${dictSchool.name}
												</td>
												<td>
													${dictSchool.kindergartenName}
												</td>
												<td>
													<span title="${dictSchool.createDate?string("yyyy-MM-dd HH:mm:ss")}">${dictSchool.createDate}</span>
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
	       </form>
	       <!-- end 学校管理 -->
		</div>
</body>
</html>