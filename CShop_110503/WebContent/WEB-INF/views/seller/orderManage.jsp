<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.container{
	margin-top: 100px;
	margin-bottom: 50px;	
}
#pageNavi{
	display:flex;
	width:420px;
	margin: 0 auto;
	justify-content: center;
}
#orderProductList>th,td{
	text-align:center;
}
#deliveryView>th{
	text-align:center;
}
a>button{
	text-decoration: none;
}
.deliveryComplete{
	text-decoration: none;
}
.btn{
	background-color:#5F755A; 
	color:#ffffff;
}
.btn:hover{
	color: #fff;
	background-color: #ADC2A9;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
    	<div class="row">
        	<div class="col-sm-4 col-md-3">
          		<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
        	</div>
        <div class="col-sm-8 col-md-9">
          <h1 style="border-left:2px solid #5f755a;">주문정보 조회 관리</h1>
          <br>
          <div class="content">
          <div class="well well-lg" style="background-color:white;">
          	<fieldset>
          	<legend>주문정보</legend>
			<table class="table table-bordered" id="orderyView" style="width:100%;">
				<tr>
					<th style="width:15%;">주문번호</th>
					<td style="width:35%;">${oi.orderNo }</td>
					<th style="width:15%;">주문일자</th>
					<td style="width:35%;">${oi.orderDate }</td>
				</tr>
				<tr>
					<th>주문금액(원)</th>
					<td>${oi.paymentPrice }</td>
					<th>주문상태</th>
					<c:choose>
						<c:when test="${oi.orderStatus eq 1}">
							<td>결제완료(배송전)</td>
						</c:when>
						<c:when test="${oi.orderStatus eq 2}">
							<td>배송중</td>
						</c:when>
						<c:when test="${oi.orderStatus eq 3}">
							<td>배송완료</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<th style="width:15%;">주문자</th>
					<td style="width:35%;">${oi.memberName }</td>
					<th style="width:15%;">연락처</th>
					<td style="width:35%;">${oi.memberPhone }</td>
				</tr>
				<tr>
					<th style="width:15%;">우편번호</th>
					<td colspan="3">${oi.postcode }</td>
				</tr>
				<tr>
					<th style="width:15%;">도로명주소</th>
					<td style="width:35%;">${oi.addressRoad }</td>
					<th style="width:15%;">상세주소</th>
					<td style="width:35%;">${oi.addressDetail }</td>
				</tr>
				<tr>
					<th style="width:15%;">주문요청사항</th>
					<td colspan="3">${oi.deliveryRequest }</td>
				</tr>								
			</table>
			</fieldset>
          </div>
          <div class="well well-lg" style="background-color:white;">
          	<legend>주문상품</legend>
          	<fieldset>
			<table class="table table-bordered" style="width:100%;">
				<tr class="table-primary" id="orderProductList">
					<th style="width:15%;">상품주문번호</th><th style="width:45%;">상품명</th><th style="width:20%;">색상</th><th style="width:10%;">사이즈</th><th style="width:10%;">수량</th>
				</tr>
				<c:forEach items="${list }" var="p">
					<tr class="table-light" id="orderProductList">
						<td>${p.opNo }</td>
						<td>${p.productName }</td>
						<td>${p.productColor }</td>
						<td>${p.productSize }</td>
						<td>${p.orderCount }</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
		  </div> 
          <div class="well well-lg" style="background-color:white;">
          	<legend>배송정보</legend>
          	<form action="/updateOrder" method="post">
          	<input type="hidden" name="orderNo" value="${oi.orderNo }">
          	<input type="hidden" name="memberNo" value="${oi.sellerNo }" >
          	<table class="table table-bordered" style="width:100%;">
          		<tr id="deliveryView">
					<th style="width:50%;">택배사</th>
					<th style="width:50%;">송장번호</th>
				</tr>
				<tr id="deliveryView">
					<c:choose>
						<c:when test="${empty oi.deliveryCompany }">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1" selected>배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 1}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1" selected>배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 2}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1">배송정보 없음</option>
	          						<option value="2" selected>한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 3}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1">배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3" selected>CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 4}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1">배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4" selected>우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 5}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1">배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5" selected>편의점</option>
	          						<option value="6">기타배송</option>
	          					</select>
							</td>
						</c:when>
						<c:when test="${oi.deliveryCompany eq 6}">
							<td>
								<select class="btn btn-default" name="deliveryCompany">
	          						<option value="1">배송정보 없음</option>
	          						<option value="2">한진</option>
	          						<option value="3">CJ</option>
	          						<option value="4">우체국</option>
	          						<option value="5">편의점</option>
	          						<option value="6" selected>기타배송</option>
	          					</select>
							</td>
						</c:when>
					</c:choose>
					<td>
						<input type="text" value="${oi.deliveryNo }" name="deliveryNo">
					</td>
				</tr>				 	
			</table>
			<c:if test="${oi.orderStatus lt 3 }">
				<button type="submit" class="btn btn-primary btn-block">배송정보등록</button>
			</c:if>
			
		  	</form>
		  </div>
		  <c:if test="${oi.orderStatus lt 3 }">
		  <div>
		  	<form action="/deliveryComplete" method="post">
		  		<input type="hidden" name="orderNo" value="${oi.orderNo }">
		  		<input type="hidden" name="memberNo" value="${oi.memberNo }">
		  		<input type="hidden" name="orderPrice" value="${oi.orderPrice }">
		  		<input type="hidden" name="sellerNo" value="${oi.sellerNo }">
		  		<button class="btn btn-block">배송완료</button>
		  	</form>
		  </div>
		  </c:if>	            	        
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
							