<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/leftmenu.css">

       	<div class="menu-left">
            <h4>ADMIN PAGE<br> 
            <c:choose>
            	<c:when test="${empty sessionScope.m }">
            		로그인 전	
            	</c:when>
            	<c:otherwise>
            		${sessionScope.m.memberName } 님 환영합니다.	
            	</c:otherwise>
            </c:choose>
            </h4>
            <ul class="left-menu">
                <li><a class="leftmenu" href="/productCheck?reqPage=1">등록 상품 인증<span class="rc">></span></a></li>
                <li><a class="submenu" href="/productCheck?reqPage=1">등록 신청 상품</a></li>
                <li><a class="submenu" href="/productCheckFail?reqPage=1">인증 실패 상품</a></li>
                <li><a class="leftmenu" href="/saleCategory">사이트 통계<span class="rc">></span></a></li>
                <li><a class="submenu" href="/saleCategory">카테고리별 판매량</a></li>
                <li><a class="submenu" href="/saleProduct?reqPage=1">상품별 판매량</a></li>
                <li><a class="submenu" href="/saleSeller?reqPage=1">판매자별 판매량</a></li>
                <li><a class="leftmenu" href="/joinMemberList?reqPage=1">회원 관리<span class="rc">></span></a></li>
                <li><a class="submenu" href="/joinMemberList?reqPage=1">가입 회원 정보</a></li>
                <li><a class="submenu" href="/retireMemberList?reqPage=1">탈퇴 회원 정보</a></li>
                <li><a class="submenu" href="/updateMemberList?reqPage=1">회원 등급 변경</a></li>
            </ul>
        </div>