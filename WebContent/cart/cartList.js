$(document).ready(function(){
	//쇼핑 계속 버튼 클릭
	$("#conShopping").click(function(){
		var url="/shoppingmall/list.do?book_kind=all";
		$(location).attr('href', url);
	});
	//메인으로 버튼 클릭
	$("#shopMain").click(function(){
		var url="/shoppingmall/inedex.do";
		$(location).attr('href', url);
	});
});

function editSu(editBtn){
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = {
			cart_id : arr[0],
			buy_count : arr[1]
	};
	
	$.ajax({
		type : "post",
		url : "/shoppingmall/cartUpdatePro.do",
		data : query,
		success : function(data){
			var str1 = '<p id="ck">';
			var loc = data.indexOf(str1);
			var len = str1.length;
			var check = data.substr(loc+len, 1);
			if(check == "1"){
				alert("수량이 수정되었습니다.");
				location.reload();
			} else{
				alert("알 수 없는 오류가 발생하였습니다.");
			}//end if(check == "1")
		}
	});
}

function delList(delBtn){
	var rStr = delBtn.name;
	var url = "/shoppingmall/deleteCart.do?list="+rStr;
	$(location).attr('href', url);
}