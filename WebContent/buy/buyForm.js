$(docuemtn).ready(function(){
	$("#cancle").click(function(){
		var url="/shoppingmall/index.do";
		$(location).attr('href', url);
	});
})