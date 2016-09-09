$(document).ready(function(){
	$("#update").click(function(){
		var book_id = $("#book_id").val();
		var book_kind = $("#book_kind").val();
		
		var query = {
				qna_content : $("#updateCont").val(),
				qna_id : $("#qna_id").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/qnaUpdatePro.do",
			data : query,
			success : function(data){
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc+len, 1);
				if(check == "1"){
					alert("QnA가 수정되었습니다.");
					var url = "/shoppingmall/bookContent.do?book_id="+book_id+"&book_kind="+book_kind;
					$(location).attr('href', url);
				} else{
					alert("QnA수정을 실패하였습니다.");
				}//end if(check == 1)
			}
		});
	});
	
	$("#cancle").click(function(){
		var book_id = $("#book_id").val();
		var book_kind = $("#book_kind").val();
		var url = "/shoppingmall/bookContent.do?book_id="+book_id+"&book_kind="+book_kind;
		$(location).attr('href', url);
	});
});