<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/shopping.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
.pointlist {
	margin: 50px; 
}
.table-primary th{
	font-weight : bold;
	text-align : center;
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
		        	<h2>포인트 내역</h2>
		 </div>
		<fieldset>
			<div class="pointlist">
			<p> Point : ${totalPoint } </p>
				<table class="table-light" style="width:100% ;">
					<tr class="table-primary">
						<th>날짜</th>
						<th>내용</th>
						<th>금액</th>
					</tr>
					<c:forEach items="${list}" var="p" varStatus="i">
					<tr class="trname">
						<td>${p.pointDate}</td>
						
							<c:choose>
		                       	<c:when test="${p.pointLevel eq 1 }">
		                		<td class="pointa">적립</td>
		                       	</c:when>
		                       	<c:when test="${p.pointLevel eq 2 }">
		                       	<td>사용</td>
		                       	</c:when>
		               			<c:otherwise>
		                       	뭐징
		                       	</c:otherwise>
		                    </c:choose>
						
						<td>
							<c:choose>
		                       	<c:when test="${p.pointLevel eq 1 }">
		                       	+${p.pointValue}<span>원</span>
		                       	</c:when>
		                       	<c:when test="${p.pointLevel eq 2 }">
		                       	-${p.pointValue}<span>원</span>
		                       	</c:when>
		               			<c:otherwise>
		                       	뭐징
		                       	</c:otherwise>
		                    </c:choose>
						
						</td>
					</tr>
					</c:forEach>
				</table>	
			</div>
		</fieldset>
		</div>
	</div>
	<script>

	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>