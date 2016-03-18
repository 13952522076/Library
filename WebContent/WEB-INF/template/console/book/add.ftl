<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/uploadify/jquery.uploadify.min.js"></script>
<link href="${base}/resources/console/uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$().ready(function() {
	[@flash_message /]	
	var $inputForm = $("#inputForm");
	/**添加正整数验证规则 */
	jQuery.validator.addMethod("positiveinteger", function(value, element) {
		var aint=parseInt(value);	
    	return aint>0&& (aint+"")==value;   
  	}, "请填写一个正整数"); 
	
	
	/** Bootstrap 对jqueryvalidate样式支持 */
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
	
	/** 验证表单*/
	$inputForm.validate({
		rules: {
		    name: {
		        required: true
		    },
		    count:{
		        positiveinteger:true,
		        max:100
		    }
			
		},
		messages: {
		}
	});
	
	
	
	
	
	
	
	
	
	
	
});

</script>
<style>
.self_form_div{
    border: 1px solid lightgray;
    border-radius: 10px;
    padding-top:20px;
    padding-bottom:10px;
}
.self_book_cover{
	background-image: url(${base}/resources/console/images/book_cover.png);
	background-size: cover;
	background-repeat: no-repeat;
	/*background-attachment: fixed;*/
}
.laydate_box, .laydate_box * {
    box-sizing:content-box;
}
</style>


</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
 	<div class="wrapper wrapper-content">
 		<!--主体内容 start-->
	    <div class="col-md-8 col-md-offset-2 form-group self_form_div container">
		    <form id="inputForm" class="form-horizontal">
		        <div class="row">
		        	<div class="col-md-7" style="border-right: 1px solid lightgray">
		        		<div class="form-group">
                            <label class="col-sm-3 control-label">书名<span class="text-danger">*</span></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">作者</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="author" maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出版日期</label>
                            <div class="col-sm-9">
                                <input type="text" readOnly id="publishDate" class="form-control layer-date" name="publishDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">入库日期</label>
                            <div class="col-sm-9">
                                <input type="text" readOnly id="putingDate" class="form-control layer-date" name="putingDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格（元）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control " name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出版社</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="publishCompany" maxlength="30">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">简介</label>
                            <div class="col-sm-9">
                            	<textarea name="description" class="form-control" rows="3" maxlength="50" placeholder="50个字以内"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">藏书数</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="count">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">封面</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="cover">
                            </div>
                        </div>
		        	</div>
		        	<div class="col-md-5">
		        		<div class="row">
		        			<!--<img src="${base}/resources/console/images/book_cover.jpg" alt="..." class="img-thumbnail">-->
		        			<div class="col-md-10 col-md-offset-1 self_book_cover">
		        				<center>
		        					<h2>书名</h2>
		        					<h3>作者</h3>
		        				</center>
		        			</div>
		        		</div>
		        	</div>
		        </div>
		        <div class="row">
                	<center>
                    	<input type="submit" value="保存" class="btn btn-success" style="width:15%;">
                	</center>
	        	</div>
			</form>
		</div>
	 	<!--主体内容 end  -->
    </div>
</div>
<script type="text/javascript" src="${base}/resources/console/laydate/laydate.js"></script>
<script>
laydate.skin('molv');
laydate({
    elem: '#publishDate', 
    event: 'focus',
    max: laydate.now(), 
    format: 'YYYY/MM/DD' 
});
laydate({
    elem: '#putingDate', 
    event: 'focus',
    max: laydate.now(), 
    format: 'YYYY/MM/DD'
});
</script>

</body>
</html>