<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/member/register.js"></script>
</head>
<body>
	<div id="regForm" class="box">
		<ul>
			<li><label for="id">아이디</label>
				<input type="email" id="id" name="name" size="20" maxlength="50" placeholder="example@kings.com" autofocus>
				<button id="checkId">ID중복 확인</button>
			</li>
			<li><label for="passwd">비밀번호</label>
				<input type="password" id="passwd" name="passwd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
			</li>
			<li><label for="rePasswd">비밀번호 재입력</label>
				<input type="password" id="rePasswd" name="rePasswd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
			</li>
			<li><label for="name">이름</label>
				<input type="text" id="name" name="name" size="20" maxlength="10" placeholder="홍길동">
			</li>
			<li><label for="address">주소</label>
				<input type="text" id="address" name="name" size="30" maxlength="50" placeholder="주소 입력">
			</li>
			<li><label for="tel">전화번호</label>
				<input type="text" id="tel" name="tel" size="20" maxlength="20" placeholder="전화번호 입력">
			</li>
			<li class="label2">
				<button id="process">가입하기</button>
				<button id="cancle">취소하기</button>
			</li>
		</ul>
	</div>
</body>
</html>