var status = true;

$(document).ready(function(){
	//상품등록 버튼 클릭
	$("#registProduct").click(function(){
		var url = "/shoppingmall/mg/bookRegisterForm.do";
		$(location).attr('href', url);
	});
	//상품수정/삭제 버튼 클릭
	$("#updateProduct").click(function(){
		var url = "/shoppingmall/mg/bookList.do?book_kind=all";
		$(location).attr('href', url);
	});
	//전체 판매목록 확인 버튼 클릭
	$("#orderedProduct").click(function(){
		var url = "/shoppingmall/mg/orderList.do";
		$(location).attr('href', url);
	});
	//상품 QnA답변 버튼 클릭
	$("#qna").click(function(){
		var url = "/shoppingmall/mg/qnaList.do";
		$(location).attr('href', url);
	});
});