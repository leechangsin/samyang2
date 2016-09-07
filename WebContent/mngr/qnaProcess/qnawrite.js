$(document).ready(function(){
	//답변하기 버튼 클릭
	$("#replyPro").click(function(){
		var query = {
				qna_content : $("#rContent").val(),
				qna_writer : $("#qna_writer").val(),
				book_title : $("#book_title").val(),
				book_id : $("#book_id").val(),
				qna_id : $("#qna_id").val(),
				qora : $("#qora").val()
		};
		
		$.ajax({
			type :"post",
			url : "/shoppingmall/mg/qnaReplyPro.jsp",
			data : query,
			success : function(data){
				var url = "/shoppingmall/mg/qnaList.do";
				$(location).attr('href', url);
			}
		});
	});
	
	//취소버튼 클릭
	$("#cancle").click(function(){
		var url = "/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	})
})