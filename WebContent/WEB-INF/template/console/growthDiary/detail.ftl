<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${message("成长记详情")} -图书智能管理推荐系统</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        [#include "/console/include/resources.ftl" /]
        <script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/jquery.lSelect.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
        <link rel="stylesheet" href="${base}/resources/console/css/animate.css" >
        <style type="text/css">
            table th { width: 150px; line-height: 25px; padding: 5px 10px 5px 0px;
            font-weight: normal; white-space: nowrap; }
        </style>
        <script type="text/javascript">
            $().ready(function() {
                var $inputForm = $("#inputForm");
                [@flash_message /]

                // 表单验证
                $inputForm.validate({
                    rules: {
                        name: {
                            required: true,
                            maxlength: 15
                        },
                        dictSchoolId: "required"
                    },
                    messages: {}
                });
            });
            function showBigImge(url){
				var content= 
				'<div>'+
				'<img src="'+url+'"  width="400" />'+
				'</div>';
				layer.open({
				    type: 1,
				    title: false,
				    area: '400px',
				    skin: 'layui-layer-demo', //样式类名
				    closeBtn: true, //显示关闭按钮
				    //shade: 0.6, //遮罩透明度
				    shift: 2,
				    //fadeIn : [300 , false],//控制层的渐显。300：渐显频率，false：是否开启渐显（默认不开启，true:开启）
				    shadeClose: true, //开启遮罩关闭
				    content: content
				});
			}
        </script>
    </head>
    <body>
        <div id="wrapper">
            <!-- start 导航 -->
            [#include "/console/include/nav.ftl" /]
            <!-- end 导航-->
            <div id="page-wrapper" class="gray-bg dashbard-1">
                <!-- start 头部 -->
                [#include "/console/include/header.ftl" /]
                <!-- end 头部-->
                <!-- start 头部面包屑区域 -->
                <div class="row wrapper border-bottom white-bg page-heading">
                    <div class="col-lg-10">
                        <h2>${message("成长记详情")}</h2>
                        <ol class="breadcrumb">
                            <li>
                                <a href="${base}/console/common/index.ct">
                                    ${message("console.path.index")}
                                </a>
                            </li>
                            <li>
                                <strong>${message("成长记详情")}</strong>
                            </li>
                        </ol>
                    </div>
                    <div class="col-lg-2">
                    </div>
                </div>
                <!-- end 头部面包屑区域 -->
                <!-- start 中间内容部分 -->
                <div class="wrapper wrapper-content animated fadeIn">
                	<!-- start tab页 -->
                	<div class="col-lg-12">
                        <div class="panel blank-panel">
                            <div class="panel-heading">
                                <div class="panel-options">
                                    <ul class="nav nav-tabs">
                                        <li><a data-toggle="tab" href="#tab-base"><i class="fa fa-font"></i>基础数据</a>
                                        </li>
                                        <li class=""><a data-toggle="tab" href="#tab-attach"><i class="fa fa-camera"></i>附件</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div id="tab-base" class="tab-pane active">
                                    	<!-- start tab基础数据 -->
                                    	<div class="row">
				                            <div class="col-lg-12">
				                                <div class="ibox float-e-margins">
				                                    <div class="ibox-content" style="width:80%;margin:0 auto;">
				                                        <div class="row">
				                                            <div class="col-sm-4 m-b-xs"></div>
				                                        </div>
				                                        <div class="table-responsive">
				                                            <table id="listTable" class="table table-striped">
				                                            	<tr>
				                                                    <th>${message("地理位置")}:</th>
				                                                    <td>
				                                                    	<span class="text-info">
				                                                    	[#if growthDiary.address??]
				                                                    	${growthDiary.address}
				                                                    	[#else]
				                                                    		无
				                                                    	[/#if]
				                                                    	</span>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th>${message("日记内容")}:</th>
				                                                    <td>
				                                                    	<span>
				                                                    	[#if growthDiary.diaryMsg??]
				                                                    	${growthDiary.diaryMsg}
				                                                    	[#else]
				                                                    		无
				                                                    	[/#if]
				                                                    	</span>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th>${message("点赞数")}:</th>
				                                                    <td>
				                                                    	共<span class="text-info">${growthDiary.agreeCount}</span>人点赞
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th>${message("点赞列表")}:</th>
				                                                    <td>
				                                                    	[#if agreeMembers?exists && agreeMembers?size > 0 ]
				                                                    		[#list agreeMembers as agreeMember]
				                                                    			${agreeMember.realName},
				                                                    		[/#list]
			                                                    		[#else]
			                                                    		无数据
				                                                    	[/#if]
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th>${message("分享数")}:</th>
				                                                    <td>
				                                                    	共<span class="text-info">${growthDiary.transpondCount}</span>人分享
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th>${message("标签")}:</th>
				                                                    <td>
				                                                    	[#if growthDiary.diaryTags?exists && growthDiary.diaryTags?size > 0 ]
				                                                    		[#list growthDiary.diaryTags as diaryTag]
				                                                    			<span class="label label-primary" style="margin-right:5px;">
			                                                    				<i class="fa fa-tag"></i>${diaryTag.name}
				                                                    			</span>
				                                                    		[/#list]
			                                                    		[#else]
			                                                    		无标签
				                                                    	[/#if]
				                                                    </td>
				                                                </tr>	
				                                            </table>
				                                            <table class="input">
																<tr>
																	<th>&nbsp;</th>
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
                                    	<!-- end   tab基础数据 -->
                                    </div>
                                    <div id="tab-attach" class="tab-pane">
                                    	<!-- start tab附件 -->
                                    	<div class="row">
				                            <div class="col-lg-12">
				                                <div class="ibox float-e-margins">
				                                    <div class="ibox-content" style="width:80%;margin:0 auto;">
				                                        <div class="row">
				                                            <div class="col-sm-4 m-b-xs"></div>
				                                        </div>
				                                        <div class="table-responsive">
				                                            <table id="listTable" class="table table-striped">
				                                                <tr>
				                                                    <th><i class="fa fa-camera"></i>${message("图片")}:</th>
				                                                    <td>
				                                                    	[#if growthDiary.diaryPictures?exists && growthDiary.diaryPictures?size > 0 ]
				                                                    		[#list growthDiary.diaryPictures as diaryPicture]
				                                                    			<a href="javscript:;" onclick='showBigImge("${diaryPicture.pictureUrl}")'>
																					<img class="img_plus" src="${diaryPicture.pictureUrl}"  height="50" width="50" />
																				</a>
				                                                    		[/#list]
			                                                    		[#else]
			                                                    		无图片
				                                                    	[/#if]
				                                                    </td>
				                                                </tr>	
				                                            </table>
				                                            <table class="input">
																<tr>
																	<th>&nbsp;</th>
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
                                    	<!-- end   tab附件 -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                	<!-- end   tab页 -->
                    <!-- start 新增管理员-->
                    <!-- end 新增管理员-->
                </div>
                <!-- end 中间内容部分-->
                [#include "/console/include/footer.ftl" /]
            </div>
        </div>
        <script>
	        $(document).ready(function () {
	            $('.img_plus').each(function () {
	                animationHover(this, 'pulse');
	            });
	        });
	    </script>
    </body>

</html>