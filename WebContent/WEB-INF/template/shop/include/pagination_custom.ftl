[#if totalPages > 1]
	<div class="pagination" style="width:100%;margin-bottom:10px;">

		[#if hasNext]
			<a style="float:right" class="nextPage" href="[@pattern?replace("{pageNumber}", "${nextPageNumber}")?interpret /]">&nbsp;</a>
		[#else]
			<span style="float:right" class="nextPage">&nbsp;</span>
		[/#if]
		
		[#if hasPrevious]
			<a style="float:right" class="previousPage" href="[@pattern?replace("{pageNumber}", "${previousPageNumber}")?interpret /]">&nbsp;</a>
		[#else]
			<span style="float:right" class="previousPage">&nbsp;</span>
		[/#if]
		
		<!--
			<div class="pagination_info"><span>${message("shop.personalcenter.orderlist.no1")}${pageNumber}${message("shop.personalcenter.orderlist.no2")} / ${message("shop.personalcenter.orderlist.no3")}
		    ${totalPages}${message("shop.personalcenter.orderlist.no2")}
		    </span></div>
	    -->
	</div>
[/#if]