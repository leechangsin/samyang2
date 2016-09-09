$(document).ready(function(){
	//ID중복확인 버튼 클릭
	$("#checkId").click(function(){
		if($("#id").val()){
			var query = { id : $("#id").val() };
			
			$.ajax({
				type : "post",
				url : "/shoppingmall/confirmId.do",
				data : query,
				success : function(data){
					var str1 = '<p id="ck">';
					var loc = data.indexOf(str1);
					var len = str1.length;
					var check = data.substr(loc+len, 1);
					if(check == 1){
						alert("사용할 수 없는 아이디 입니다.");
						$("#id").val("");
					} else{
						alert("사용할 수 있는 아이디 입니다.");
					}//end if(check == 1)
				}
			});
		} //아이디를 입력하지 않고 ID중복확인 버튼을 클릭한 경우 
		else {
			alert("사용할 아이디를 입력하세요.");
			$("#id").focus();
		}//end if($("#id").val())
	});//end $("#checkId").click(function()
	//가입하기 버튼 클릭
	$("#process").click(function(){
		var query = {
				id : $("#id").val(),
				passwd : $("#passwd").val(),
				name : $("#name").val(),
				address : $("#address").val(),
				tel : $("#tel").val()
		};
		
		$.ajax({
			type : "post",
			url : "/shoppingmall/registerPro.do",
			data : query,
			success : function(data){
				var url = "/shoppingmall/index.do";
				$(location).attr('href', url);
			}
		});
	});
	//취소 버튼 클릭
	$("#cancle").click(function(){
		var url = "/shoppingmall/index.do";
		$(location).attr('href', url);
	})
});