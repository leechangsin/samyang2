<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="mngr/managermain.js"></script>

<c:if test="${empty sessionScope.id}">
	<div id="mList"><p>로그인 하세요.</div>
</c:if>
<c:if test="${!empty sessionScope.id}">
	<div id="mList">
		<ul>
			<li>상품관련 작업
			<li><button id="registProduct">상품등록</button>
			<li><button id="updateProduct">상품수정/삭제</button>
		</ul>
		<ul>
			<li>판매한 상품관련 작업
			<li><button id="orderedProduct">전체 판매목록 확인</button>
		</ul>
		<ul>
			<li>상품 QnA작업
			<li><button id="qna">상품 QnA답변</button>
		</ul>
	</div>
</c:if>