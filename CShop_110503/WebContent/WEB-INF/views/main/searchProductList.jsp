<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="/css/product.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top:100px">
		<h1>검색결과</h1><br>
		<div id="categoryBox" >
		</div>
		
		<div class="productBigBox">
			<c:forEach items="${list }" var="p" varStatus="i">
			<div class="productBox">
				<a href="productView?id=${p.productId }&seller=${p.productSeller }&reqPage=1" id="productView">
				<img id="pImage" src="/upload/product/${p.productImage }">
				<div class="productText">
				<p class="brand">${p.productBrand }</p>
				<p class="name">${p.productName }</p></a>
				</div>
			</div>
			</c:forEach>
		</div>
		<div id="pageNavi" >${pageNavi }</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>













