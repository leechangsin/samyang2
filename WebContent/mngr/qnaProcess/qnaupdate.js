$(document).ready(function(){
	//수정버튼 클릭
	$("#update").click(function(){
		var qna_id = $("#qna_id").val();
		var query = {
				qna_content : $("#uRContent").val(),
				qna_id : $("#qna_id").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/mg/qnaReplyUpdatePro.do",
			data : query,
			success : function(data){
				var url = "/shoppingmall/mg/qnaList.do";
				$(location).attr('href', url);
			}
		});
	});
	
	$("#cancal").click(function(){
		var url = "/shoppingmall/mg/qnaList.do";
		$(location).attr('href', url);
	});
})