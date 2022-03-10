<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="css/notice.css?after">
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
					<h1 style="font-size:24px !important">공지사항</h1>
				</div>
			</div>
			
			<div class="noticeView-head">
				<div>${n.regDate }</div>
				<h2><div>${n.noticeTitle }</div></h2>
				<p style="text-align:right">
					<c:choose>
						<c:when test="${n.filename != null }">
							<img src="/img/file.png" width="16px">
							<a href="/fileDown?noticeNo=${n.noticeNo }">${n.filename }</a>
							 | 
						</c:when>
						<c:otherwise>
							첨부파일없음 | 
						</c:otherwise>
					</c:choose>
					조회수 : ${n.readCount }
				</p>
			</div>
			
			<div class="noticeView-contents">
				${n.noticeContent }
			</div>
			
			<div class="noticeView-foot">
				<button class="btn btn-default" onclick="history.go(-1);">목록</button>
					<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
						<a href="/noticeUpdateFrm?noticeNo=${n.noticeNo }" class="btn btn-default">수정하기</a>
						<a href="/noticeDelete?noticeNo=${n.noticeNo }" class="btn btn-default">삭제하기</a>
					</c:if> 
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>