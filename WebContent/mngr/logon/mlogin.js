$(document).ready(function(){
	//로그인버튼을 클릭하면 실행
	$("#login").click(function(){
		var query = {
				id : $("#id").val(),
				passwd : $("#passwd").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/mg/managerLoginPro.do",
			data : query,
			success : function(data){
				var url = "/shoppingmall/mg/managerMain.do";
				$(location).attr('href', url);
			}
		});
	});//end $("#login").click(function()
	
	//로그아웃버튼을 클릭하면 실행
	$("#logout").click(function(){
		$.ajax({
			type : "POST",
			url : "/shoppingmall/mg/managerLogout.do",
			succes : function(data){
				var url = "/shoppingmall/mg/managerMain.do";
				$(location).attr('href', url);
			}
		});
	});

});