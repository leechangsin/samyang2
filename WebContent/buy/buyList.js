$(document).ready(function(){
	$("#conShoppin").click(function(){
		var url="/shoppingmall/list.do?book_kind=all";
		$(location).attr('href', url);
	});
	
	$("#shopMain").click(function(){
		var url="/shoppingmall/index.do";
		$(location).attr('href', url);
	});
})