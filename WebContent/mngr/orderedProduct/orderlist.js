$(document).ready(function(){
	$("#bookMain").click(function(){
		var url="/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	});
});

function reply(replyBtn){
	var rStr = replyBtn.name;
	var url = "/shoppingmall/mg/qnaReplyForm.do?qna_id="+rStr;
	$(location).attr('href', url);
}

function edit(editBtn){
	var rStr = editBtn.name;
	var url = "/shoppingmall/mg/qnaReplyUpdateForm.do?qna_id="+rStr;
	$(location).attr('href', url);
}