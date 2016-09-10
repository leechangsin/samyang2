<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
	</c:if>
	
	<div id="orderResult">
		<p>${orderStatus}
	</div>
	
	<div id="buyProcess">
		<form id="buyPro" method="post" action="/shoppingmall/buyList.do">
			<input type="hidden" name="buyer" value="${sessionScope.id}">
			<input type="submit" value="주문확인">
		</form>
	</div>
</body>
</html>