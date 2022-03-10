<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link rel="stylesheet" href="/css/leftmenu.css">
		<style>
		.container{
			min-height: 800px;
		}
		</style>
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
                <li><a class="leftmenu" href="/shoppingList?reqPage=1&selectmenu=0">주문 내역<span class="rc">></span></a></li>
                <li><a class="leftmenu" href="/reviewList?selectmenu=1&prWriter=${sessionScope.m.memberId }">리뷰 관리<span class="rc">></span></a></li>
                <li><a class="leftmenu" href="/membership?selectmenu=2&memberNo=${sessionScope.m.memberNo }">등급 혜택<span class="rc">></span></a></li>
                <li><a class="leftmenu" href="/pointList?selectmenu=3">적립금 내역<span class="rc">></span></a></li>
                <li><a class="leftmenu" href="/mypage?selectmenu=5&selectmenu=4">정보 수정<span class="rc">></span></a></li>
             </ul>
        </div>
        <script>
        	$(function(){
        		var selectmenu = '${selectmenu}';
        		$(".leftmenu").eq(selectmenu).css("color","#5F755A");
        		$(".leftmenu").eq(selectmenu).css("font-weight","bold");
        	});
        </script>

