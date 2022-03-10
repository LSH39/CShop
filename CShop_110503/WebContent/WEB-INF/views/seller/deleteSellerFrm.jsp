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
          <h1 style="border-left:2px solid #5f755a;">회원탈퇴</h1>
          <br>   
          <div class="content">
          	<div>
          		<form action="/deleteSeller" method="post">
         			<input type="hidden" name="memberNo" value="${sessionScope.m.memberNo }"> 			
					<div class="well" style="background-color:white;">
						<div>
          					<strong>판매자 회원탈퇴 주의 사항</strong>
          					<ul>
          						<li>판매 중인 상품이 있을경우 탈퇴 할 수 없습니다.</li>
          						<li>관리자의 승인이 있어야 최종 탈퇴가 가능합니다.</li>
          						<li>탈퇴 시 판매자의 모든 기록이 삭제 됩니다.</li>
          						<li>재가입 하여도 판매기록등을 복구할 수 없습니다.</li>
          					</ul>
          				</div>
					</div>
					<c:choose>
						<c:when test="${sessionScope.m.memberStatus eq 1 }">
							<button type="submit" class="btn btn-danger btn-block">탈퇴요청</button>
						</c:when>
						<c:when test="${sessionScope.m.memberStatus eq 2 }">
							<h1>탈퇴 심사중 입니다.</h1>
						</c:when>
					</c:choose>
				</form>
          	</div>
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>