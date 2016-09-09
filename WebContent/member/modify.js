$(document).ready(function(){
	//수정 버튼 클릭
	$("#modifyProcess").click(function(){
		var query = {
				id : $("#id").val(),
				passwd : $("#passwd").val(),
				name : $("#name").val(),
				address : $("#address").val(),
				tel : $("#tel").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/modifyPro.do",
			data : query,
			success : function(data){
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc+len, 1);
				if(check == "1"){
					alert("회원정보가 수정되었습니다.");
					var url = "/shoppingmall/modify.do";
					$(location).attr('href', url);
				} else{
					alert("비밀번호가 틀렸습니다.");
					$("#passwd").val("");
					$("#passwd").focus();
				}//end if(check == "1")
			}
		});
	});//end $("#modifyProcess").click(function()
	//취소 버튼 클릭
	$("#cancle").click(function(){
		var url = "/shoppingmall/modify.do";
		$(location).attr('href', url);
	});
});

function shopMain(){
	var url = "/shoppingmall/index.do";
	$(location).attr('href', url);
}