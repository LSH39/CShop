<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="css/contact.css?after">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top:70px">
		<div class="notice-left col-md-3">
			<h1>고객센터</h1><br>
			<h3><a href="/notice?reqPage=1">공지사항</a></h3><br>
			<h3><a href="/question?reqPage=1">1:1문의</a></h3>
		</div>
		<div class="notice-right col-md-9">
			<div class="notice-title">
				<div class="notice-title-left col-md-10">
					<h1 style="font-size:24px !important">1:1문의</h1>
				</div>
				<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel == 2 }">
					<div class="notice-title-right col-md-2">
						<h1><a class="btn btn-info" href="/questionWriteFrm">글쓰기</a></h1>
					</div>
				</c:if>
			</div>
			<table class="table table-hover" style="width:100%;">
				<thead>
					<tr>
						<th class="thead-no"><div>번호</div></th>
						<th><div>제목</div></th>
						<th class="thead-member"><div>작성자</div></th>
						<th class="thead-date"><div>작성일</div></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="c" varStatus="i">
						<tr>
							<td><div>${start + i.index }</div></td>
							<td style="text-align:left;width:70%;">
								<c:choose>
									<c:when test="${not empty sessionScope.m && sessionScope.m.memberId eq c.contactWriter }">
										<a href='/questionView?contactNo=${c.contactNo }'><div>${c.contactTitle } [${c.ctCount }]</div></a>
									</c:when>
									<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 3 }">
										<a href='/questionView?contactNo=${c.contactNo }'><div>${c.contactTitle } [${c.ctCount }]</div></a>
									</c:when>
									<c:otherwise>
										<a href='javascript:void(0);'><div>${c.contactTitle } [${c.ctCount }]</div></a>
									</c:otherwise>
								</c:choose>
								
							</td>
							<td><div>${c.contactWriter }</div></td>
							<td><div>${c.regDate }</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>