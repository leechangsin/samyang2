$(document).ready(function(){
	//회원가입 버튼 클릭 
	$("#uRes").click(function(){
		var url = "/shoppingmall/registerForm.do";
		$(location).attr('href', url);
	});
	//로그인 버튼 클릭
	$("#uLogin").click(function(){
		var query = {
				id : $("#cid").val(),
				passwd : $("#cPasswd").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/loginPro.do",
			success : function(data){
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc+len,1);
				if(check == "1"){//로그인 성공
					var url = "/shoppingmall/index.do";
					$(location).attr('href', url);
				} else if(check == "0"){
					alert("비밀번호가 틀렸습니다.");
					$("#cPasswd").val("");
					$("#cPasswd").focus();
				} else {
					alter("아이디가 틀렸습니다.");
					$("#cid").val("");
					$("#cPasswd").val("");
					$("#cid").focus();
				}//end if(check == "1")
			}
		});
	});
	
	
})