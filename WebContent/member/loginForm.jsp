<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/member/login.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<div id="1Status">
			<ul>
				<li><label for="cid">아이디</label>
					<input type="email" id="cid" name="cid" size="20" maxlength="50" placeholder="example@kings.com">
				</li>
				<li><label for="cpasswd">비밀번호</label>
					<input type="password" id="cPasswd" name="cPasswd" size="20" maxlength="16" placeholder="6~16자 숫자/문자">
				</li>
			</ul>
			<button id="uLogin">로그인</button>
			<button id="uRes">회원가입</button>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.id}">
		<div id="1Status">
			<ul>
				<li>${sessionScope.id}님이 로그인 하셨습니다.
				<div id="info">
					<table>
						<tr height="10">
							<td> <button id="uLogout">로그아웃</button> </td>
							<td> <button id="uUpdate">회원 정보 변경</button> </td>
							<td>
								<form id="cartForm" method="post" action="/shoppingmall/cartList.do">
									<input type="hidden" name="buyer" value="${sessionScope.id}">
									<input type="submit" name="cart" value="장바구니">
								</form>
							</td>
							<td>
								<form id="buyForm" method="post" action="/shoppingmall/buyList.do">
									<input type="hidden" name="buyer" value="${sessionScope.id}">
									<input type="submit" name="buy" value="구매내역">
								</form>
							</td>
						</tr>
					</table>
				</div>
			</ul>
		</div>
	</c:if>
</body>
</html>