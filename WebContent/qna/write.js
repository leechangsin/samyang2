$(document).ready(function(){
	//등록버튼 클릭
	$("#regist").click(function(){
		var book_kind = $("#book_kind").val();
		var book_id = $("#book_id").val();
		
		var query = {
				qna_content : $("#qnaCont").val(),
				qna_writer : $("#qna_writer").val(),
				book_title : $("#book_title").val(),
				book_id : book_id,
				qora : $("#qora").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/qnaPro.do",
			data : query,
			success : function(data){
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc+len, 1);
				if(check == "1"){
					alert("QnA가 등록되었습니다.");
					var url = "/shoppingmall/bookContent.do?book_id="+book_id+"&book_kind="+book_kind;
					$(location).attr('href', url);
				} else{
					alert("QnA등록이 실패하였습니다.");
				}//end if(check == "1")
			}
		});
	});
	//취소버튼 클릭
	$("#cancle").click(function(){
		var book_kind = $("#book_kind").val();
		var book_id = $("#book_id").val();
		var url = "/shoppingmall/bookContent.do?book_id="+book_id+"&book_kind="+book_kind;
		$(location).attr('href', url);
	});
});