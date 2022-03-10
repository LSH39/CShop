<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.container{
	margin-top: 100px;
	margin-bottom: 50px;	
}
.btn{
	background-color:#5F755A; 
	color:#ffffff;
}
.btn:hover{
	color: #fff;
	background-color: #ADC2A9;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
    	<div class="row">
        <div class="col-sm-4 col-md-3">
          	<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
        </div>
        <div class="col-sm-8 col-md-9">
          <h1 style="border-left:2px solid #5f755a;">회원정보수정</h1>
          <br>
          <h3>비밀번호 재확인</h3>
          <h5>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요</h5>
          <div class="content">
          <div class="well well-lg" style="background-color:white;">
          	<form action="/sellerUpdate" method="post">
				<div>
					<div class="form-group">
						<label for="memberId">아이디</label>
					 	<input type="text" name="memberId" id="memberId" class="form-control" placeholder="${sessionScope.m.memberId }" value="${sessionScope.m.memberId }" readonly>
					</div>
					<div class="form-group">
						<label for="memberPw">비밀번호</label>
					 	<input type="password" name="memberPw" id="memberPw" class="form-control" placeholder="비밀번호입력">
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-block">확인</button>
				</div>					 	
			</form>          	        
          </div>
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>