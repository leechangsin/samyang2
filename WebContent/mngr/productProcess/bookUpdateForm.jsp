<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/js/jquery.form.min.js"></script>
<script src="/shoppingmall/mngr/productProcess/bookupdate.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/shoppingmall/mg/managerMain.do">
</c:if>

<div id="header">
	<button id="bookMain">관리자 메인으로</button>
	<button id="bookList">목록으로</button>
</div>
<form id="upForm1" action="/shoppingmall/mg/bookUpdatePro.do" method="post" enctype="multipart/form-data">
	<div id="bookUpdateForm" class="box">
		<ul>
			<li><label for="book_kind">분류선택</label>
				<select id="book_kind" name="book_kind">
					<option value="100" <c:if test="${book_kind == 100}"> selectd</c:if>>문학
					</option>
					<option value="200" <c:if test="${book_kind == 200}"> selectd</c:if>>외국어
					</option>
					<option value="300" <c:if test="${book_kind == 300}"> selectd</c:if>>컴퓨터
					</option>
				</select>
			</li>
			<li><label for="book_title">제목</label>
				<input type="text" id="book_title" name="book_title" size="50" maxlength="50" value="${book.book_title}">
				<input type="hidden" name="book_id" value="${book_id}">
			</li>
			<li><label for="book_price">가격</label>
				<input type="text" id="book_price" name="book_price" size="10" maxlength="9" value="${book.book_price }">원
			</li>
			<li><label for="book_count">수량</label>
				<input type="text" id="book_count" name="book_count" size="10" maxlength="5" value="${book.book_count}">권
			</li>
			<li><label for="author">저자</label>
				<input type="text" id="author" name="author" size="20" maxlength="30" value="${book.author}">
			</li>
			<li><label for="publishing_com">출판사</label>
				<input type="text" id="publishing_com" name="publishing_com" size="20" maxlength="30" value="${book.publishnig_com}">
			</li>
			<li><label for="publishing_date"></label>
				<fmt:formatDate var="nowTimeStr" pattern="yyyy-MM-dd" value="${nowTime}"/>
				<fmt:parseNumber var="lastYear" type="NUMBER" value="${nowTimeStr.toString().subString(0,4)}"/>
				<c:fi
			</li>
		</ul>
	</div>
</form>
</body>
</html>