<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图书智能管理推荐系统</title>
[#include "/console/include/resource.ftl" /]
<link href="${base}/resources/console/css/freelancer.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]	
});

function showFavorites(adminId,studentName){
	$("#myModalLabel").html(studentName+"喜欢的书籍:");
	$.ajax({
		type: "GET",
		url: "favorite.ct",
		data: {
		    id:adminId
		},
		dataType: "json",
		success:function(marks){
			var content ='<ul class="list-group">';
                           
			if((marks!=null)&&(marks.length>0)){
				for(var i=0;i<marks.length;i++){
					var mark = 	marks[i];
					content+=' <li class="list-group-item">';
					content+='<a class="text-info" href="${base}/console/book/detail.ct?id='+ mark.book.id +'">《'+mark.book.name+'》</a> ';
					content+='<span class="pull-right">给了 '+mark.mark+' 颗星</span>';
					content+='</li>';
				}
			}
			content +='</ul>';
			$("#modal_body").html(content);
		}
	});
	
	
}

</script>
<style>
</style>
</head>
<body class="gray-bg">
<div id="wrapper">
	<!-- start  导航 -->
	[#include "/console/include/nav.ftl" /]
	<!-- end 导航-->
 	<div class="wrapper wrapper-content">
 		<!--主体内容 start-->
	    <section id="portfolio">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">
						<h2>The Classmate Of Similar</h2>
						<h3>相 似 同 学 推 荐</h3>
						<hr class="star-primary">
					</div>
				</div>
				<div class="row">
				[#list simStudents as simStudent]
					<div class="col-sm-4 portfolio-item">
						<a onclick="showFavorites(${simStudent.id},'${simStudent.name}')" data-toggle="modal" data-target="#myModal" class="portfolio-link">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
									<h3>${simStudent.name}</h3>
								</div>
							</div>
							<img src="${base}/resources/console/images/similar/submarine.png" class="img-responsive" alt="">
						</a>
					</div>
				[/#list]	
				</div>
			</div>
		</section>
	 	<!--主体内容 end  -->
	 	<!--弹出层start-->
	 	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                    <h4 class="modal-title" id="myModalLabel">推荐的书籍</h4>
	                </div>
	                <div class="modal-body" id="modal_body">
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                </div>
	            </div>
	        </div>
	    </div>
	 	<!--弹出层end  -->
    </div>
</div>
</body>
</html>