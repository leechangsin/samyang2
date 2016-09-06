$(document).ready(function(){
	//이미지를 포함한 상품 등록
	$("#upForm1").ajaxForm({
		//업로드에 성공하면 수행
		success : function(data, status){
			var url = "/shoppingmall/mg/bookList.do?book_kind=all";
			$(location).attr('href', url);
		}
	});
	//관리자 메인으로 버튼 클릭시 실행
	$("#bookMain").click(function(){
		var url = "/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	});
	//목록으로 버튼 클릭시 실행
	$("#bookList").click(function(){
		var url = "/shoppingmall/mg/bookList.do?book_kind=all";
		$(location).attr('href', url);
	});
});