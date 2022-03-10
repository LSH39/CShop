<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/leftmenu.css?ver=1">
</head>
<body>
	<div class="menu-left">
            <h4>MY PAGE<br> 
            <c:choose>
            	<c:when test="${empty sessionScope.m }">
            		로그인 전	
            	</c:when>
            	<c:otherwise>
            		${sessionScope.m.memberId } 님 환영합니다.	
            	</c:otherwise>
            </c:choose>
            </h4>
            <ul class="left-menu">
                <li><a class="leftmenu" href="#">회원정보<span class="rc">></span></a></li>
                <li><a class="submenu" href="/checkSellerMember">회원정보수정</a></li> 
                <li><a class="leftmenu" href="/saleManage?reqPage=1&memberNo=${sessionScope.m.memberNo }&orderStatus=4">주문관리<span class="rc">></span></a></li>
                <li><a class="leftmenu" href="#">상품관리<span class="rc">></span></a></li>
                <li><a class="submenu" href="/inputProductFrm">상품등록</a></li>
                <li><a class="submenu" href="/selectProduct?memberNo=${sessionScope.m.memberNo }&reqPage=1">상품조회/수정</a></li>
                <li><a class="submenu" href="/productInquiryList?reqPage=1&memberNo=${sessionScope.m.memberNo }">상품문의</a></li>
            </ul>
        </div>
</body>
</html>