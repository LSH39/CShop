
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/shopping.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.imgPr > img{
   width:100%;
   height:100%;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>상품 관리</h2>
		 </div>
<fieldset>
			<div class="list_shopping">
			<ul class="list_order">
			<c:forEach items="${list }" var="oi" varStatus="i">
				<li>
		            <div class="pr_date">${oi.orderDate}</div> 
		            <div class="order_box">
		            					<!-- 상세주문정보인(shopping 으로이동) order_no 필요 --->
		                <div class="name"><a href="/shopping?orderNo=${oi.orderNo }">${oi.productName }</a></div> 
		                <div class="order_info">
		                    <div class="thumb">
		                 	<c:choose> 
			                    <c:when test="${empty oi.productImage }">	
			                    	 <img src='/img/기본.JPG'>
			                   	</c:when>
			                   	<c:otherwise>
			                    <img src="/upload/product/${oi.productImage }">
			                    </c:otherwise>
		                    </c:choose>
		           	</div>
		                    <div class="desc">
		                        <dl><dt>주문번호</dt> <dd>${oi.orderNo}</dd></dl> 
		                        <dl><dt>결제금액</dt> <dd>${oi.paymentPrice}<span class="won">원</span></dd></dl> 
		                        <dl><dt>주문상태</dt><dd>
		                        <c:choose>
		                        	<c:when test="${oi.orderStatus eq 0 }">
		                        	구매취소
		                        	</c:when>
		                        	<c:when test="${oi.orderStatus eq 1 }">
		                        	결제완료
		                        	</c:when>
		                        	<c:when test="${oi.orderStatus eq 2 }">
		                        	배송중
		                        	</c:when>
		                        	<c:otherwise>
		                        	배송완료
		                        	</c:otherwise>
		                        </c:choose>
		                       	</dd></dl>
		                    </div>
		                </div> 
		                <div class="order_status"><span class="inner_status">
		                </span>
		                </div>
		              
		            </div>
		        </li>
			</c:forEach>
		    </ul>
			</div>
		</fieldset>
		</div>
	</div>
	<script>

	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>