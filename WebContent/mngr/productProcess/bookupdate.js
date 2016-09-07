$(document).ready(function(){
	//책 수정 버튼 클릭
	$("#upForm1").ajaxForm({
		success : function(date, status){
			var url = "/shoppingmall/mg/bookList.do?book_kind=all";
			$(location).attr('href', url);
		}
	});
	//관리자 메인으로 버튼 클릭
	$("#bookMain").click(function(){
		var url = "/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	});
	//목록으로 버튼 클릭
	$("#bookList").click(function(){
		var url = "/shoppingmall/mg/bookList.do?book_kind=all";
		$(location).attr('href', url);
	});
})