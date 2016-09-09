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
<script src="/shoppingmall/member/modify.js"></script>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
	</c:if>
	<div id="mStatus">
		<form id="uForm" method="post" action="/shoppingmall/modifyForm.do">
			<ul>
				<li><label for="passwd">비밀번호</label>
					<input type="password" id="passwd" name="passwd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
					<input type="hidden" id="id" name="id" value="${sessionScope.id}">
					<input type="submit" id="modify" value="정보수정">
			</ul>
		</form>
		
		<form id="dForm" method="post" action="/shoppingmall/deletePro.do">
			<ul>
				<li><label for="passwd">비밀번호</label>
					<input type="password" id="passwd" name="passwd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
					<input type="hidden" id="id" name="id" value="${sessionScope.id}">
					<input type="submit" id="delete" value="탈퇴">
					<small class="cau">[탈퇴]버튼을 누르면 바로 탈퇴 됩니다.</small>
			</ul>
		</form>
		
		<button id="shopMain" onclick="shopMain(this)">메인으로</button>
	</div>
</body>
</html>