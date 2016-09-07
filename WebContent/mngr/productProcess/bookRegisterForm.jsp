<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/js/jquery.form.min.js"></script>
<script src="/shoppingmall/mngr/productProcess/bookregist.js"></script>
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.id }">
	<meta http-equiv="Refresh" content="0;hul=/shoppingmall/mg/managerMain.do">
</c:if>

<div id="listHeader">
	<button id="bookMain">관리자 메인으로</button>
	<button id="bookList">목록으로</button>
</div>
<form id="upForm1" action="/shoppingmall/mg/bookRegisterPro.do" method="post" enctype="multipart/form-data">
	<div id="bookRegisterForm" class="box">
		<ul>
			<li>
				<label for="book_kind">분류 선택</label>
				<select id="book_kind" name="book_kind">
					<option value="100">문학</option>
					<option value="200">외국어</option>
					<option value="300">컴퓨터</option>
				</select>
			</li>
			<li>
				<label for="book_title">제목</label>
				<input type="text" id="book_title" name="book_title" size="50" maxlength="50" placeholder="책 제목">
			</li>
			<Li>
				<label for="book_price">가격</label>
				<input type="text" id="book_price" name="book_price" size="10" maxlength="9" placeholder="책 가격">원
			</Li>
			<li>
				<label for="book_count">수량</label>
				<input type="text" id="book_count" name="book_count" size="10" maxlength="5" placeholder="책 수량">권
			</li>
			<li>
				<label for="author">저자</label>
				<input type="text" id="author" name="author" size="20" maxlength="30" placeholder="책 저자">
			</li>
			<li>
				<label for="publishing_com">출판사</label>
				<input type="text" id="publishing_com" name="publishing_com" size="20" maxlength="30" placeholder="책 출판사">
			</li>
			<li>
				<label for="publishing_date">출판일</label>
				<div id="publising_date"> 
					<jsp:useBean id="nowTime" class="java.util.Date"/>
					<fmt:formatDate var="nowTimeStr" pattern="yyyy-MM-dd" value="${nowTime}"/>
					<fmt:parseNumber var="lastYear" type="NUMBER" value="${nowTimeStr.toString().substring(0,4)}"/>
					<select name="publishing_year">
						<c:forEach var="i" begin="2010" end="${lastYear}">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>년
					<select name="publishing_month">
						<c:forEach var="i" begin="1" end="12">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>월
					<select name="publishing_day">
						<c:forEach var="i" begin="1" end="31">
						<option value="${i}">${i}</option>
						</c:forEach>
					</select>일
				</div>
			</li>
			<li>
				<label for="book_image">책 이미지</label>
				<input type="file" id="book_image" name="book_image">
			</li>
			<li>
				<label for="book_content">내용</label>
				<textarea id="book_content" name="book_content" rows="13" cols="50"></textarea>
			</li>
			<li><label for="discount_rate">할인율</label>
				<input type="text" id="discount_rate" name="discount_rate" size="5" maxlength="2" placeholder="10">
			</li>
			<li>
				<input type="submit" id="registBook" value="책 등록">
			</li>
		</ul>
	</div>
</form>
</body>
</html>