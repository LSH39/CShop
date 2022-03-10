<%@page import="product.model.vo.ProductOption"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    	String  cate2 = request.getParameter("cate2");
    	int j = 0;
    	int cate = Integer.parseInt(cate2);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/product.css?ver=3">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top:100px">
		<h1>MEN</h1><br>
		<%if(cate == 1){ %>
		 <h4>HOME ＞ MEN ＞ OUTER</h4>
		 <div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate" style="color : #545454; border-bottom : 3px solid #5F755A;">아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate">etc</a>
		</div>
		<%} else if(cate == 2){%>
		<h4>HOME ＞ MEN ＞ TOP</h4>
		<div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate" >아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate" style="color : #545454; border-bottom : 3px solid #5F755A;">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate">etc</a>
		</div>
		<%} else if(cate == 3){%>
		<h4>HOME ＞ MEN ＞ BOTTOM</h4>
		<div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate">아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate" style="color : #545454; border-bottom : 3px solid #5F755A;">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate">etc</a>
		</div>
		<%} else if(cate == 4){%>
		<h4>HOME ＞ MEN ＞ UNDERWEAR</h4>
		<div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate">아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate"style="color : #545454; border-bottom : 3px solid #5F755A;">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate">etc</a>
		</div>
		<%} else if(cate == 5){%>
		<h4>HOME ＞ MEN ＞ SPORTSWEAR</h4>
		<div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate">아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate" style="color : #545454; border-bottom : 3px solid #5F755A;">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate">etc</a>
		</div>
		<%} else if(cate == 6){%>
		<h4>HOME ＞ MEN ＞ ETC</h4>
		<div id="categoryBox" >
			<a href="/productList?reqPage=1&cate2=1"class="cate">아우터</a>
			<a href="/productList?reqPage=1&cate2=2" class="cate">상의</a>
			<a href="/productList?reqPage=1&cate2=3" class="cate">하의</a>
			<a href="/productList?reqPage=1&cate2=4" class="cate">언더웨어</a>
			<a href="/productList?reqPage=1&cate2=5" class="cate">스포츠웨어</a>
			<a href="/productList?reqPage=1&cate2=6" class="cate" style="color : #545454; border-bottom : 3px solid #5F755A;">etc</a>
		</div>
		<%} %>

		<div class="productBigBox">
			<c:forEach items="${pList }" var="p">
				<div class="productBox">
					<a href="productView?id=${p.productId }&seller=${p.productSeller }&reqPage=1" id="productView">
					<div class="pImageBox">
						<img id="pImage" src="/upload/product/${p.productImage }">
					</div>
					<div class="productText">
						<p class="brand">${p.productBrand }</p>
						<p class="name">${p.productName }</p></a>
						<p class="price" id="price">${p.productPrice } 원</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="pageNavi" >${pageNavi }</div>
	</div>
	<script>
		//가격 , 표시
		$(".price").click(function(){
			var price = $(this).html();
			 $(this).html(price.replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		});
		$(".price").trigger("click");
		
		
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>













