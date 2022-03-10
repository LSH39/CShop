<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String memberId = (String)request.getAttribute("memberId");
    boolean result = (Boolean)request.getAttribute("result");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<style>
.check-container{
padding-top: 50px;
text-align: center;
}
</style>
</head>
<body>
<div class="check-container">
<%if(result) { %>
[<span><%=memberId %></span>]는 사용이 가능합니다.
<br><br>
<button onclick="setMemberId('<%=memberId%>');">닫기</button>

<%}else{ %>
[<span id="checked"><%=memberId %></span>]는 이미 사용중입니다.
<br><br>
<form action="/checkId" method="post">
<input type="text" name="checkId" placeholder="사용할 아이디를 입력하세요"><br> 
<input type="submit" value="중복검사"><br>
</form> 


<%} %>
</div>
<script>
function setMemberId(memberId){
	//opener.document 현재 브라우저를 오픈한 부모요소 페이지의 #idChk를 가져옴
	//새로 띄운 창에서 입력받은 값을 부모 창으로 보내주기 위해 사용
	$("#idChk",opener.document).prev().val(memberId);
	//현재 본인 창 닫기
	self.close();
}
</script>
</body>
</html>