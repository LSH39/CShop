<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="css/index.css?after">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="slide" style="margin-top:70px">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1"></li>
		      <li data-target="#myCarousel" data-slide-to="2"></li>
		    </ol>
	
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner">
		
		      <div class="item active">
		      	<a href="productView?id=suser01_20211105121929&seller=15&reqPage=1">
		        	<img src="img/carousel01.jpg" alt="carousel01" style="width:100%;">
		        </a>
		      </div>
		
		      <div class="item">
		      	<a href="productView?id=suser01_20211105123246&seller=15&reqPage=1">
		        	<img src="img/carousel02.png" alt=carousel02" style="width:100%;">
		        </a>
		      </div>
		    
		      <div class="item">
		        <a href="productView?id=suser01_20211105121136&seller=15&reqPage=1">
		        	<img src="img/carousel03.jpg" alt="carousel03" style="width:100%;">
		        </a>
		      </div>
	  
	    	</div>
	    

		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right"></span>
		      <span class="sr-only">Next</span>
		    </a>
		  </div>
	</div>
		
		
	<div class="container">
		<br><br><br><br>
		<h2>추천상품</h2><br><br><br><br>
		<div class="bestPlace">
			<c:forEach items="${best }" var="b" varStatus="i">
				<div class="bestPro col-md-4">
					<a href="productView?id=${b.productId }&seller=${b.productSeller }&reqPage=1" id="productView">
						<img id="bestImg" src="/upload/product/${b.productImage }"><br>
						<h3>${b.productName }</h3>
					</a>
				</div>
			</c:forEach>
		</div>
		<br><br><br><br><br><br><br><br>
		<iframe style="border: 10px solid #5f755a;" width="1140" height="650" src="https://www.youtube.com/embed/-SBsT032jVI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		<br><br><br><br>
		<h2>신상품</h2><br><br><br><br>
		<div class="newPlace">
			<c:forEach items="${newlist }" var="n" varStatus="i">
				<div class="newPro col-md-3">
					<a href="productView?id=${n.productId }&seller=${n.productSeller }&reqPage=1" id="productView">
						<img id="newImg" src="/upload/product/${n.productImage }"><br>
						<h3>${n.productName }</h3>
					</a>
				</div>
			</c:forEach>
		</div>
		<br><br><br><br><br><br><br><br>
		<div class="logoPlace">
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo01.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo02.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo03.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo04.jpg">
			</div>
		</div>
		<div class="logoPlace">
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo05.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo06.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo07.jpg">
			</div>
			<div class="mainLogo col-md-3">
				<img src="/img/mainlogo08.jpg">
			</div>
		</div>
		<br><br><br><br>
			
	  <br><br><br>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>