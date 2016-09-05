<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>

<div id="header">
	<div id="logo" class="box">
		<img class="noborder" id="logo" src="/shoppingmaill/images/mollalogo3.png"/>
	</div>
	
	<div id="auth" class="box">
		<c:if test="${type == 0 }">
			<jsp:include page="mngr/logon/mLoginForm.jsp"></jsp:include>
		</c:if>
		<c:if test="${type == 1 }">
			<jsp:include page="member/loginForm.jsp"></jsp:include>
		</c:if>
	</div>
</div>

<div id="content" class="box2">
	<jsp:include page="${cont }"></jsp:include>
</div>