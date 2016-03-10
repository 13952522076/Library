<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${message("console.seo.edit")} -图书智能管理推荐系统</title>
<meta name="author" content="福州盛云软件技术有限公司 Team" />
<meta name="copyright" content="福州盛云软件技术有限公司" />
[#include "/console/include/resources.ftl" /]
<script type="text/javascript" src="${base}/resources/console/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/console/js/input.js"></script>
<style type="text/css">
.tag {
	padding-top: 8px;
	margin-top: 6px;
	display: none;
	border-top: 1px dashed #c7deff;
}

.tag a {
	padding: 6px;
	margin-right: 6px;
	border: 1px solid #98bbd2;
	background-color: #f5f9ff;
}
</style>
<script type="text/javascript">
$().ready(function() {
	
	var $inputForm = $("#inputForm");
	var $text = $(":text");
	var $tag = $("div.tag");
	
	[@flash_message /]
	
	$text.click(function() {
		var $this = $(this);
		$tag.hide();
		$this.next().show();
	});
	
	$tag.find("a").click(function() {
		var $this = $(this);
		var $text = $this.parent().prev();
		$text.val($text.val() + $this.attr("val"));
	});
	
	// 表单验证
	$inputForm.validate();
	
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
                    <h2>${message("console.seo.edit")}</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${base}/console/common/index.ct">${message("console.path.index")}</a> 
                        </li>
                        <li>
                            <strong>${message("console.seo.edit")}</strong>
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
		             <div class="row">
	                    <div class="col-lg-12">
	                        <div class="ibox float-e-margins">
	                            <div class="ibox-content">
	                                <div class="row">
	                                    <div class="col-sm-4 m-b-xs">
											
	                                    </div>
	                                </div>
	                                <div class="table-responsive">
	                                     <input type="hidden" name="id" value="${seo.id}" />
	                                     <table id="listTable" class="table table-striped">
											<tr>
												<th>
													${message("Seo.type")}:
												</th>
												<td>
													${message("Seo.Type." + seo.type)}
												</td>
											</tr>
											<tr>
												<th>
													${message("Seo.title")}:
												</th>
												<td>
													<input type="text" name="title" class="form-control" value="${seo.title}" maxlength="200" />
													<div class="tag">
														[#if seo.type == "productList"]
															<a href="javascript:;" val="[#noparse]${productCategory.nameZh}[/#noparse]">${message("console.seo.productCategoryName")}</a>
														[#elseif seo.type == "productSearch"]
															<a href="javascript:;" val="[#noparse]${productKeyword}[/#noparse]">${message("console.seo.productKeyword")}</a>
														[#elseif seo.type == "productContent"]
															<a href="javascript:;" val="[#noparse]${product.name}[/#noparse]">${message("console.seo.productName")}</a>
															<a href="javascript:;" val="[#noparse]${product.fullName}[/#noparse]">${message("console.seo.productFullName")}</a>
															<a href="javascript:;" val="[#noparse]${product.sn}[/#noparse]">${message("console.seo.productSn")}</a>
															<a href="javascript:;" val="[#noparse]${product.productCategory.nameZh}[/#noparse]">${message("console.seo.productProductCategoryName")}</a>
														[#elseif seo.type == "articleList"]
															<a href="javascript:;" val="[#noparse]${articleCategory.name}[/#noparse]">${message("console.seo.articleCategoryName")}</a>
														[#elseif seo.type == "articleSearch"]
															<a href="javascript:;" val="[#noparse]${articleKeyword}[/#noparse]">${message("console.seo.articleKeyword")}</a>
														[#elseif seo.type == "articleContent"]
															<a href="javascript:;" val="[#noparse]${article.title}[/#noparse]">${message("console.seo.articleTitle")}</a>
															<a href="javascript:;" val="[#noparse]${article.author}[/#noparse]">${message("console.seo.articleAuthor")}</a>
															<a href="javascript:;" val="[#noparse]${article.pageNumber}[/#noparse]">${message("console.seo.articlePageNumber")}</a>
															<a href="javascript:;" val="[#noparse]${article.articleCategory.name}[/#noparse]">${message("console.seo.articleArticleCategoryName")}</a>
														[#elseif seo.type == "brandContent"]
															<a href="javascript:;" val="[#noparse]${brand.name}[/#noparse]">${message("console.seo.brandName")}</a>
														[/#if]
														<a href="javascript:;" val="[#noparse]${setting.siteName}[/#noparse]">${message("console.seo.settingSiteName")}</a>
														<a href="javascript:;" val="[#noparse]${setting.siteUrl}[/#noparse]">${message("console.seo.settingSiteUrl")}</a>
														<a href="javascript:;" val="[#noparse]${setting.address}[/#noparse]">${message("console.seo.settingAddress")}</a>
														<a href="javascript:;" val="[#noparse]${setting.phone}[/#noparse]">${message("console.seo.settingPhone")}</a>
														<a href="javascript:;" val="[#noparse]${setting.zipCode}[/#noparse]">${message("console.seo.settingZipCode")}</a>
														<a href="javascript:;" val="[#noparse]${setting.email}[/#noparse]">${message("console.seo.settingEmail")}</a>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													${message("Seo.keywords")}:
												</th>
												<td>
													<input type="text" name="keywords" class="form-control" value="${seo.keywords}" maxlength="200" title="${message("console.seo.keywordsTitle")}" />
													<div class="tag">
														[#if seo.type == "productList"]
															<a href="javascript:;" val="[#noparse]${productCategory.name}[/#noparse]">${message("console.seo.productCategoryName")}</a>
														[#elseif seo.type == "productSearch"]
															<a href="javascript:;" val="[#noparse]${productKeyword}[/#noparse]">${message("console.seo.productKeyword")}</a>
														[#elseif seo.type == "productContent"]
															<a href="javascript:;" val="[#noparse]${product.name}[/#noparse]">${message("console.seo.productName")}</a>
															<a href="javascript:;" val="[#noparse]${product.fullName}[/#noparse]">${message("console.seo.productFullName")}</a>
															<a href="javascript:;" val="[#noparse]${product.sn}[/#noparse]">${message("console.seo.productSn")}</a>
															<a href="javascript:;" val="[#noparse]${product.productCategory.name}[/#noparse]">${message("console.seo.productProductCategoryName")}</a>
														[#elseif seo.type == "articleList"]
															<a href="javascript:;" val="[#noparse]${articleCategory.name}[/#noparse]">${message("console.seo.articleCategoryName")}</a>
														[#elseif seo.type == "articleSearch"]
															<a href="javascript:;" val="[#noparse]${articleKeyword}[/#noparse]">${message("console.seo.articleKeyword")}</a>
														[#elseif seo.type == "articleContent"]
															<a href="javascript:;" val="[#noparse]${article.title}[/#noparse]">${message("console.seo.articleTitle")}</a>
															<a href="javascript:;" val="[#noparse]${article.author}[/#noparse]">${message("console.seo.articleAuthor")}</a>
															<a href="javascript:;" val="[#noparse]${article.pageNumber}[/#noparse]">${message("console.seo.articlePageNumber")}</a>
															<a href="javascript:;" val="[#noparse]${article.articleCategory.name}[/#noparse]">${message("console.seo.articleArticleCategoryName")}</a>
														[#elseif seo.type == "brandContent"]
															<a href="javascript:;" val="[#noparse]${brand.name}[/#noparse]">${message("console.seo.brandName")}</a>
														[/#if]
														<a href="javascript:;" val="[#noparse]${setting.siteName}[/#noparse]">${message("console.seo.settingSiteName")}</a>
														<a href="javascript:;" val="[#noparse]${setting.siteUrl}[/#noparse]">${message("console.seo.settingSiteUrl")}</a>
														<a href="javascript:;" val="[#noparse]${setting.address}[/#noparse]">${message("console.seo.settingAddress")}</a>
														<a href="javascript:;" val="[#noparse]${setting.phone}[/#noparse]">${message("console.seo.settingPhone")}</a>
														<a href="javascript:;" val="[#noparse]${setting.zipCode}[/#noparse]">${message("console.seo.settingZipCode")}</a>
														<a href="javascript:;" val="[#noparse]${setting.email}[/#noparse]">${message("console.seo.settingEmail")}</a>
													</div>
												</td>
											</tr>
											<tr>
												<th>
													${message("Seo.description")}:
												</th>
												<td>
													<input type="text" name="description" class="form-control" value="${seo.description}" maxlength="200" />
													<div class="tag">
														[#if seo.type == "productList"]
															<a href="javascript:;" val="[#noparse]${productCategory.nameZh}[/#noparse]">${message("console.seo.productCategoryName")}</a>
														[#elseif seo.type == "productSearch"]
															<a href="javascript:;" val="[#noparse]${productKeyword}[/#noparse]">${message("console.seo.productKeyword")}</a>
														[#elseif seo.type == "productContent"]
															<a href="javascript:;" val="[#noparse]${product.name}[/#noparse]">${message("console.seo.productName")}</a>
															<a href="javascript:;" val="[#noparse]${product.fullName}[/#noparse]">${message("console.seo.productFullName")}</a>
															<a href="javascript:;" val="[#noparse]${product.sn}[/#noparse]">${message("console.seo.productSn")}</a>
															<a href="javascript:;" val="[#noparse]${product.productCategory.nameZh}[/#noparse]">${message("console.seo.productProductCategoryName")}</a>
														[#elseif seo.type == "articleList"]
															<a href="javascript:;" val="[#noparse]${articleCategory.name}[/#noparse]">${message("console.seo.articleCategoryName")}</a>
														[#elseif seo.type == "articleSearch"]
															<a href="javascript:;" val="[#noparse]${articleKeyword}[/#noparse]">${message("console.seo.articleKeyword")}</a>
														[#elseif seo.type == "articleContent"]
															<a href="javascript:;" val="[#noparse]${article.title}[/#noparse]">${message("console.seo.articleTitle")}</a>
															<a href="javascript:;" val="[#noparse]${article.author}[/#noparse]">${message("console.seo.articleAuthor")}</a>
															<a href="javascript:;" val="[#noparse]${article.pageNumber}[/#noparse]">${message("console.seo.articlePageNumber")}</a>
															<a href="javascript:;" val="[#noparse]${article.articleCategory.name}[/#noparse]">${message("console.seo.articleArticleCategoryName")}</a>
														[#elseif seo.type == "brandContent"]
															<a href="javascript:;" val="[#noparse]${brand.name}[/#noparse]">${message("console.seo.brandName")}</a>
														[/#if]
														<a href="javascript:;" val="[#noparse]${setting.siteName}[/#noparse]">${message("console.seo.settingSiteName")}</a>
														<a href="javascript:;" val="[#noparse]${setting.siteUrl}[/#noparse]">${message("console.seo.settingSiteUrl")}</a>
														<a href="javascript:;" val="[#noparse]${setting.address}[/#noparse]">${message("console.seo.settingAddress")}</a>
														<a href="javascript:;" val="[#noparse]${setting.phone}[/#noparse]">${message("console.seo.settingPhone")}</a>
														<a href="javascript:;" val="[#noparse]${setting.zipCode}[/#noparse]">${message("console.seo.settingZipCode")}</a>
														<a href="javascript:;" val="[#noparse]${setting.email}[/#noparse]">${message("console.seo.settingEmail")}</a>
													</div>
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
</body>
</html>