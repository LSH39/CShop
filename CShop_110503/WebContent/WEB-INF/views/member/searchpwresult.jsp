<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String memberPw = (String) request.getAttribute("memberPw");
boolean result = (Boolean) request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/idsearchresult.css">
</head>
<body>


<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<div id="wrap" style="text-align: center; margin-top: 180px;">
			<h1>아이디 조회 결과 확인</h1>
			<div id="searchResultBox">
				<div>
					<img src="/img/icon-member-seller.svg" alt="">
				</div>

				<%
				if (result) {
				%>
				<div>
					<h2>
						
						<%=memberPw%>
						
					</h2>
				</div>
			</div>
		</div>
		<div id="btn_controll">
			<button type="button" onclick="location.href='/' ">홈으로 가기</button>
			<button type="button" onclick="location.href='/loginFrm'">로그인 하러 가기</button>
		</div>
	</div>


	<%
	} else {
	%>
				<div>
					<h2><%=memberPw%></h2>
				</div>
			</div>
		</div>
		<div id="btn_controll">
			<button type="button" onclick="location.href='/'">홈으로 가기</button>
			<button type="button" onclick="location.href='/joinFrm'">회원가입 하러 가기</button>
		</div>
	</div>
	<%
	}
	%>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>