$(document).ready(function(){
	//책 등록 버튼 클릭
	$("#regist").click(function(){
		var url = "/shoppingmall/mg/bookRegisterForm.do";
		$(location).attr('href', url);
	});
	
	//관리자 메인으로 버튼 클릭
	$("#bookMain").click(function(){
		var url = "/shoppingmall/mg/managerMain.do";
		$(location).attr('href', url);
	});
});

//수정버튼 클릭
function edit(editBtn){
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/mg/bookUpdateForm.do?book_id=" + arr[0];
	query += "&book_kind=" + arr[1];
	
	var url = query;
	$(location).attr('href', url);
}
//삭제버튼을 클릭
function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "/shoppingmall/mg/bookDeletePro.do?book_id=" + arr[0];
	query += "&book_kind=" + arr[1];
	
	var url = query;
	$(location).attr('href', url);
}