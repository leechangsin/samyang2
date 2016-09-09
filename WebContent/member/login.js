$(document).ready(function(){
	//회원가입 버튼 클릭 
	$("#uRes").click(function(){
		var url = "/shoppingmall/registerForm.do";
		$(location).attr('href', url);
	});
	//로그인 버튼 클릭
	$("#uLogin").click(function(){
		var query = {
				id : $("#cId").val(),
				passwd : $("#cPasswd").val()
		};
		
		$.ajax({
			type : "post",
			data : query,
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
					alert("아이디가 틀렸습니다.");
					$("#cid").val("");
					$("#cPasswd").val("");
					$("#cid").focus();
				}//end if(check == "1")
			}
		});
	});//end $("#uLogin").click(function()
	//회원정보변경 버튼 클릭
	$("#uUpdate").click(function(){
		var url = "/shoppingmall/modify.do";
		$(location).attr('href', url);
	});
	//로그아웃 버튼 클릭
	$("#uLogout").click(function(){
		$.ajax({
			type : "post",
			url : "/shoppingmall/logout.do",
			success : function(data){
				var url = "/shoppingmall/index.do";
				$(location).attr('href', url);
			}
		});
	});
	//장바구니 버튼 클릭
	$("#cart").click(function(){
		var url = "/shoppingmall/cartList.do";
		$(location).attr('href', url);
	});
	//구매내역 버튼 클릭
	$("#buy").click(function(){
		var url = "/shoppingmall/buyList.do";
		$(location).attr('href', url);
	});
});
