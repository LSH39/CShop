<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //Servlet에서 등록한 데이터 꺼내는 작업
    String msg = (String)request.getAttribute("msg");
    String loc = (String)request.getAttribute("loc");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert("<%=msg%>");
		location.href="<%=loc%>";
	</script>
</body>
</html>