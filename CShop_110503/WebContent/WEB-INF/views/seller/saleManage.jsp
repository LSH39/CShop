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
.pagination{
	height:60px;
}
#pageNavi{
	display:flex;
	width:420px;
	margin: 0 auto;
	justify-content: center;
}
.table{
	font-size: 12px;
	overflow: hidden;
	text-align: center;
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
          <h1 style="border-left:2px solid #5f755a;">주문관리</h1>
          <div class="content">
          	<div class="well" style="background-color:white;">
          		<div>
          			<strong>모든 주문건을 조회할 수 있는 통합 주문조회 메뉴입니다.</strong>
          			<ul>
          				<li>주문조회 가이드 문구.</li>
          				<li>해당 메뉴에서는 주문확인/상품발송을 대상 메뉴들에서 진행하실 수 있습니다.</li>
          			</ul>
          		</div>
          	</div>
          	<div class="well well-lg" style="background-color:white;">
          		<form action="/saleManage?reqPage=1&memberNo=${sessionScope.m.memberNo }" method="post">
          		<table>
          			<tbody>
          				<tr>
          					<th style="width:150px;">조회조건</th>
          					<td>
          						<div>
          							<div>
          								<div class="dropdown">
          									<select class="btn btn-default" name="orderStatus">
          										<option selected value="4">전체</option>
          										<option value="1">결제완료</option>
          										<option value="2">발송</option>
          										<option value="3">배송완료</option>
          									</select>
          								</div>
          							</div>
          						</div>
          					</td>
          				</tr>
          			</tbody>
          		</table>
          		<br>
          		<div>
          			<button type="submit" class="btn btn-block" id="searchBtn">조회</button>
          		</div>
          		</form>
          	</div>
          	<div class="well well-lg" style="background-color:white;">
	          	<table class="table table-hover" style="width:100%;">
					<tr class="table-primary">
						<th>주문번호</th>
						<th>구매자</th>
						<th>결제방식</th>
						<th>주문가격</th>
						<th>배송비</th>
						<th>사용포인트</th>
						<th>결제금액</th>
						<th>주문일자</th>
						<th>주문상태</th>
						<th>상세보기</th>
					</tr>
					<c:forEach items="${list }" var="o" varStatus="i">
						<tr class="table-light">
							<td>${o.orderNo }</td>
							<td>${o.memberName }</td>
							<c:choose>
								<c:when test="${o.paymentMethod eq 0 }">
									<td>신용카드</td>
								</c:when>
								<c:when test="${o.paymentMethod eq 1 }">
									<td>계좌이체</td>
								</c:when>
							</c:choose>
							<td>${o.orderPrice }</td>
							<td>${o.deliveryPrice }</td>
							<td>${o.orderPoint }</td>
							<td>${o.paymentPrice }</td>
							<td>${o.orderDate }</td>
							<c:choose>
								<c:when test="${o.orderStatus eq 1 }">
									<td>결재완료</td>
								</c:when>
								<c:when test="${o.orderStatus eq 2 }">
									<td>발송</td>
								</c:when>
								<c:when test="${o.orderStatus eq 3 }">
									<td>배송완료</td>
								</c:when>
							</c:choose>
							<td><a href="/orderManage?orderNo=${o.orderNo }&memberNo=${sessionScope.m.memberNo }"><button class="btn btn-sm">이동</button></a></td>
						</tr>
					</c:forEach>
				</table>
				<div id="pageNavi">${pageNavi }</div>
          	</div>
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>