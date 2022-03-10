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
.table th,td{
	text-align:center;
}
.pagination{
	height:60px;
}
#pageNavi{
	display:flex;
	width:420px;
	margin: 0 auto;
	justify-content: center;
}
.table td>a {
  color: #1a1a1a;
  text-decoration: none;
  background-color: transparent;
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
          <h1 style="border-left:2px solid #5f755a;">상품 문의</h1>
          <br>
          <fieldset>
			<table class="table table-hover" style="width:100%;">
				<tr class="table-primary">
					<th>문의번호</th><th>제목</th><th>작성일</th><th>답변여부</th>
				</tr>
				<c:forEach items="${list }" var="i">
					<tr class="table-light">
						<c:if test="${i.pqLevel eq 1 }">
							<td>${i.pqNo }</td>
							<td style="text-align:left;width:50%;"><a href='/inquiryView?pqNo=${i.pqNo }'>${i.pqTitle }</a></td>
							<td>${i.pqDate }</td>
							<c:choose>
								<c:when test="${i.pqStatus eq 1 }">
									<td style="color:red;">미완료</td>
								</c:when>
								<c:when test="${i.pqStatus eq 2 }">
									<td style="color:#5F755A;">완료</td>
								</c:when>
							</c:choose>					
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>