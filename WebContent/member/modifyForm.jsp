<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/member/modify.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
	</c:if>
	
	<div id="regForm" class="box">
		<ul>
			<li><p class="center">회원 정보 수정
			<li><label for="id">아이디</label>
				<input type="email" id="id" name="id" size="20" maxlength="50" value="${id}" readonly disabled="disabled">
			<li><label for="passwd">비밀번호</label>
				<input type="password" id="passwd" name="passwd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
				<small class="cau">반드시 입력하세요.</small>
			<li><label for="name">이름</label>
				<input type="text" id="name" name="name" size="20" maxlength="10" value="${member.getName()}">
			<li><label for="address">주소</label>
				<input type="text" id="address" name="address" size="30" maxlength="50" value="${member.getAddress()}">
			<li><label for="tel">전화번호</label>
				<input type="text" id="tel" name="tel" size="20" maxlength="20" value="${member.getTel()}">
			<li class="label2">
				<button id="modifyProcess">수정</button>
				<button id="cancle">취소</button>
		</ul>
	</div>
</body>
</html>