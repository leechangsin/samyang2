$(document).ready(function(){
	//관리자 메인으로 버튼 클릭
	$("#bookMain").click(function(){
		var url = "/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	});
});

//답변하기 버튼 클릭
function reply(replyBtn){
	var sStr = replyBtn.name;
	var query = "/shoppingmall/mg/qnaReplyForm.do?qna_id=" + rStr;
	var url = query;
	$(location).attr('href', url);
}

//수정버튼 클릭
function edit(editBtn){
	var rStr = editBtn.name;
	var query = "/shoppingmall/mg/qnaReplyUpdateForm.do?qna_id=" + rStr;
	var url = query;
	$(location).attr('href', url);
}