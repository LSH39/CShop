<%@page import="product.model.vo.ProductReview" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 ProductReview pr = (ProductReview)request.getAttribute("pr");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>리뷰 상세 보기</h2>
		        	<table class="table" id="reviewView">
		        	<tr class="table-info">
						<th>날짜</th>
							<th colspan="4"><%=pr.getPrDate() %></th>
					</tr>
					<tr class="table-light">
						<th>작성자</th>
						<th><%=pr.getPrWriter()%></th>
						<th>작성일 </th>
						<th><%=pr.getPrDate() %></th>
					</tr>
					<tr class="table-light">
					<tr>
						<th>첨부파일(이미지)</th>
						<th>
							<%if(pr.getPrFilepath() != null) { %>
							<img src="/img/file.png" width="16px">
							<a href="#"><%=pr.getPrFilepath() %></a>
							<%} %>
						</th>
						<th>별 점 </th>
						 <th><=%pr.getPrStar() %></th>
					</tr>
					<tr class="table-light">
						<th>내용</th>
						<th colspan="3">
						<%=pr.getPrContent() %>
						</th>
					</tr>
					<tr class="table-active">
						<th colspan="4">
						<button type="submit" class="btn btn-info" onclick="history.go(-1);">이전화면</button>
							<%--if(m!=null && m.getMemberId().equals(pr.getPrWriter())) {--%>
							<a href="#" class="btn btn-info">수정하기</a>
							<a href="#" class="btn btn-info">삭제하기</a>
							<%--} --%>
						</th>
					</tr>
		        </table>
		 </div>
	</div>
	<script>

	</script>
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>

</body>
</html>