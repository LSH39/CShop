<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
.memberShip {
	font-size : 18px;
	/*display: flex;
    margin: auto;
    justify-content: space-evenly;
    */
    position: absolute;
 	top: 40%;
 	left: 50%;
 	transform: translate(-50%,-50%);
}
td{
	text-align : center;
}
img {
	padding : 20px;
}
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>등급 혜택 </h2>
		 </div>
		<fieldset>
			<div class="memberShip">
			<table>
				<tr>
					<td colspan="3">
			${sessionScope.m.memberId} 님의 구매등급은  
	         <c:choose>
		       	<c:when test="${sessionScope.m.membership eq 0 }">
		       	Family
		       	</c:when>
		       	<c:when test="${sessionScope.m.membership eq 1 }">
		       	Vip
		       	</c:when>
		       	<c:otherwise>
		       	SVip
		       	</c:otherwise>
	         </c:choose>
			입니다. <br>
			  
			<c:choose>
		       	<c:when test="${sessionScope.m.membership eq 1 }">
		       	<c:choose>
		       		<c:when test="${4-oc > 0 }">
		    		다음 구매 등급 까지 구매 건수는 ${4-oc }건이 필요합니다.
		       		</c:when>
		       		<c:otherwise>
		    		관리자가 처리중입니다.   		
		       		</c:otherwise>
		       	</c:choose>
		       	</c:when>
		       	<c:when test="${sessionScope.m.membership eq 2 }">
		       	<c:choose>
		       		<c:when test="${10-oc > 4 }">
		       		다음 구매 등급 까지 구매 건수는 ${10-oc }건이 필요합니다. 
		       		</c:when>
		       		<c:otherwise>
		       		관리자가 처리중입니다.  
		       		</c:otherwise>
	        	 </c:choose>
	        	 </c:when>
	        	<c:when test="${sessionScope.m.membership eq 3 }">
		       	<c:choose>
		       		<c:when test="${oc > 10 }">
					축하드립니다 SVIP입니다. 이용해주셔서 감사합니다
		       		</c:when>
		       		<c:otherwise>
		       		관리자가 처리중입니다.  
		       		</c:otherwise>
	        	 </c:choose>
	        	 </c:when>
			</c:choose>
			
			 <br>
				</td>
				</tr>
				<tr>
				<td colspan="3">
				<img src="/img/membership.jpg">
				</td>
				<tr>
				<tr>
				<td>6개월간 10건 이상</td><td>6개월간 4건 이상</td><td>6개월간 4건이하</td>
				</tr>
				<tr>
				<td>15%적립</td><td>10%적립</td><td>5%적립</td>
				</tr>
			</table>
			</div>
		</fieldset>
		</div>
	</div>

</body>
</html>