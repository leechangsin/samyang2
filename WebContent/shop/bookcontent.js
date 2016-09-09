$(document).ready(function(){
	//장바구니에 담기 버튼 클릭
	$("#insertCart").click(function(){
		var buyer = $("#buyer").val();
		var book_kind = $("#book_kind").val();
		var query = {
				book_id : $("#book_id").val(),
				buy_count : $("#buy_count").val(),
				book_image : $("#book_image").val(),
				book_title : $("#book_title").val(),
				buy_price : $("#buy_price").val(),
				buyer : buyer
		};

		$.ajax({
			type : "post",
			url : "/shoppingmall/insertCart.do",
			data : query,
			success : function(data){
				alert("장바구니에 담겼습니다.");
			}
		});
	});//end $("#insertCart").click(function()

	//상품QnA쓰기 버튼 클릭
	$("#writeQna").click(function(){
		var book_id = $("#book_id").val();
		var book_kind = $("#book_kind").val();
		var url = "/shoppingmall/qnaForm.do?book_id="+book_id+"&book_kind="+book_kind;
		$(location).attr('href', url);
	});
});

//수정버튼 클릭
function edit(editBtn){
	var rStr = editBtn.name;
	var arr =rStr.split(",");
	var url = "/shoppingmall/qnaUpdateForm.do?qna_id="+arr[0]+"&book_kind="+arr[1];
	$(location).attr('href', url);
}

//삭제버튼 클릭
function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = { qna_id : arr[0] };

	$.ajax({
		type : "post",
		url : "/shoppingmall/qnaDeletePro.do",
		data : query,
		success : function(data){
			var str1 = '<p id="ck">';
			var loc = data.indexOf(str1);
			var len = str1.length;
			var check = data.substr(loc+len,1);
			if(check == "1"){
				alert("QnA가 삭제되었습니다.");
				var url = "/shoppingmall/bookContent.do?book_id="+arr[1]+"&book_kind="+arr[2];
				$(location).attr('href', url);
			} else
				alert("QnA삭제를 실패하였습니다.");
		}
	});
}