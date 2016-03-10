<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>${message("shop.returnGoods.returnGoodsSuccess")}[#if systemShowPowered] -图书智能管理推荐系统 官方网站[/#if]</title>
        <meta name="author" content="福州盛云软件技术有限公司 Team" />
        <meta name="copyright" content="福州盛云软件技术有限公司" />
        <link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
        <script type="text/javascript" src="${base}/resources/shop/js/mainframe.js"></script>
        <script type="text/javascript">
            $().ready(function() {

                [@flash_message /] 

            });
        </script>
    </head>
    
    <body>
        [#include "/shop/include/header.ftl" /]
        <div class="rightWrapper">
	        <div class="container" style="width:65%;">
	            <section id="main" class="content-main body">
	                <div class="head">
	                </div>
	                <div class="body">
	                    <div class="line">
	                        <div class="unit size1of1">
	                            <h2 class="h2">
	                                ${message("shop.returnGoods.returnGoodsSuccess")}
	                            </h2>
	                        </div>
	                    </div>
	                    <div class="line mod gaView">
	                        <div class="unit size1of1">
	                            <div class="actionsControls fLeft">
	                                <a id="continueShoppingBt" type="button" class="button butWhite" href="${base}/member/personalCenter/personalCenter.ct" >
	                                    <span>
	                                        ${message("shop.returnGoods.returnUserCenter")}
	                                    </span>
	                                </a>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="line">
	                    </div>
	                </div>
	            </section>
	        </div>
	    </div>
        [#include "/shop/include/footer.ftl" /]
    </body>

</html>