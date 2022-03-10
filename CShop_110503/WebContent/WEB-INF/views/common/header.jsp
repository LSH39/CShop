<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	Member m = (Member)session.getAttribute("m");
    %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
	nav{
		background-color:white;
		border-bottom: 1px solid #c4c4c4;
	}
	.header-left{
		height:70px;
		display:flex;
		align-items:center;
	}
	
	.header-right{
		height:70px;
		display:flex;
		align-items:center;
		justify-content:flex-end;
	}
	.nav>li>a{
		margin: 0px 10px;
		height:70px;
		display:flex;
		align-items:center;
		font-size:15px;
		color:black;
		font-weight: 900;
        border-top: 5px solid white;
        border-bottom: 5px solid white;
	}
	.nav>li>a:focus, .nav>li>a:hover {
		height:70px;
		display:flex;
		align-items:center;
    	text-decoration: none;
		font-size:15px;
    	color:#5f755a;
    	background-color: white;
        border-top: 5px solid white;
        border-bottom: 5px solid #5f755a;
	}
  </style>
</head>
<body>


<nav class="navbar-fixed-top">
  <div class="container-fluid">
  	<div class="header-left col-md-5">
	    <div class="navbar-header">
	    	<a href="/"><img src="img/logo.jpg"></a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="productList?reqPage=1&cate2=1">남성</a></li>
	      <li><a href="productList2?reqPage=1&cate2=1">여성</a></li>
	      <li><a href="productList3?reqPage=1&cate2=1">키즈</a></li>
	    </ul>
    </div>
    
    <div class="header-right col-md-7">
    	<c:choose>
	    	<c:when test="${empty sessionScope.m }">
			    <ul class="nav navbar-nav navbar-right Lv0">
			      <li><a href="/loginFrm"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
			      <li><a href="/joinFrm"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
			    </ul>
			</c:when>
			<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel == 1 }">
				<ul class="nav navbar-nav navbar-right Lv1" style="display:block">
			      <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
			      <li><a href="/cartFrm"><span class="glyphicon glyphicon-shopping-cart"></span> 장바구니</a></li>
			      <li><a href="/shoppingList?reqPage=1&selectmenu=0"><span class="glyphicon glyphicon-home"></span> 마이페이지</a></li>
			    </ul>
			</c:when>
			<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel == 2 }">
				 <ul class="nav navbar-nav navbar-right Lv2" style="display:block">
	     		<li><a href="/inputProductFrm">상품등록</a></li>
			      <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
			      <li><a href="/sellerPage?memberNo=${sessionScope.m.memberNo }"><span class="glyphicon glyphicon-home"></span> 마이페이지</a></li>
			    </ul>
			</c:when>
			<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
			    <ul class="nav navbar-nav navbar-right Lv3" style="display:block">
			      <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
			      <li><a href="/productCheck?reqPage=1"><span class="glyphicon glyphicon-wrench"></span> 관리자페이지</a></li>
			    </ul>
			</c:when>
		</c:choose>
	    <form class="navbar-form navbar-right" action="/mainSearchProduct">
	      <div class="input-group ">
	        <input type="text" class="form-control" placeholder="검색" name="search" value="${search }">
	        <div class="input-group-btn">
	          <button class="btn btn-default" type="submit">
	            <i class="glyphicon glyphicon-search"></i>
	          </button>
	        </div>
	      </div>
	    </form>
	</div>
  </div>
</nav>

</body>