<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/mngr/logon/mlogin.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<div>
		<ul>
			<li>
				<label for="id">아이디</label> 
				<input type="email" id="id" name="id" size="20" maxlength="50" placeholder="example@kings.com">
				
				<label for="passwd">비밀번호</label> 
				<input type="password" id="passwd" name="passwd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
				
				<button id="login">로그인</button>
			</li>
		</ul>
	</div>
</c:if>

<c:if test="${!empty sessionScope.id}">
	<div>
		<ul>
			<li>
				관리자 로그인 성공!!
				<button id="logout">로그아웃</button>
			</li>
		</ul>
	</div>
</c:if>
</body>
</html>