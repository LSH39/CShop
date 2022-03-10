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
.btn{
	background-color:#5F755A; 
	color:#ffffff;
}
.btn:hover{
	color: #fff;
	background-color: #ADC2A9;
}
</style>
<script src="/chart/Chart.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
    	<div class="row">
        	<div class="col-sm-4 col-md-3">
          		<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
        	</div>
        <div class="col-sm-8 col-md-9">
          <h1 style="border-left:2px solid #5f755a;">판매자 페이지</h1>
          <br>
          <div class="content">
          	<div class="well" style="background-color:white;">
          		<div>
          			<h4>주문/배송</h4>
          		</div>
          		<br>
          		<div>
	          		<div class="col-lg-6">
	          			<table class="table">
	          				<tr>
	          					<th style="width:50%;">오늘 주문</th><td style="width:50%;"><span>${orderTodayCnt }</span><span>건</span></td>
	          				</tr>
	          				<tr>
	          					<th style="width:50%;">오늘 매출</th><td style="width:50%;"><span>${orderAmountSum }</span><span>원</span></td>
	          				</tr>
	          			</table>
	          		</div>
	          		<div class="col-lg-6">
	          			<table class="table">
	          				<tr>
	          					<th style="width:50%;">결재완료</th><td style="width:50%;"><span>${payCompleteCnt }</span><span>건</span></td>
	          				</tr>
	          				<tr>
	          					<th>배송중</th><td><span>${deliveryingCnt }</span><span>건</span></td>
	          				</tr>
	          				<tr>
	          					<th>배송완료</th><td><span>${deliveredCnt }</span><span>건</span></td>
	          				</tr>
	          			</table>
	          		</div>
	          		<br><br><br><br><br><br>
          		</div>
          		<br>
          	</div>       	
          	<div class="well col-lg-6" style="background-color:white;">
          		<div>
          			<h4>일별 판매량</h4>
          		</div>
          		<div>
          			<canvas id="myChart"></canvas>
          		</div>
          	</div>
          	<div class="well col-lg-6" style="background-color:white;">
          		<div>
          			<h4>일별 매출</h4>
          		</div>
          		<div>
          			<canvas id="myChart2"></canvas>
          		</div>
          	</div>      	
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
	    // The type of chart we want to create
	    type: 'bar',

	    // The data for our dataset
	    data: {
	        labels: ['${orderDay[6]}', '${orderDay[5]}', '${orderDay[4]}', '${orderDay[3]}', '${orderDay[2]}', '${orderDay[1]}', '${orderDay[0]}'],
	        datasets: [{
	            label: '판매 건',
	            backgroundColor: '#ADC2A9',
	            borderColor: '#5F755A',
	            data: [${orderCount[6]}, ${orderCount[5]}, ${orderCount[4]}, ${orderCount[3]}, ${orderCount[2]}, ${orderCount[1]}, ${orderCount[0]}]
	        }]
	    },

	    // Configuration options go here
	    options: {}
	});
	
	var ctx2 = document.getElementById('myChart2').getContext('2d');
	var chart2 = new Chart(ctx2, {
	    // The type of chart we want to create
	    type: 'line',

	    // The data for our dataset
	    data: {
	        labels: ['${orderDay[6]}', '${orderDay[5]}', '${orderDay[4]}', '${orderDay[3]}', '${orderDay[2]}', '${orderDay[1]}', '${orderDay[0]}'],
	        datasets: [{
	            label: '판매 금액',
	            backgroundColor: '#ADC2A9',
	            borderColor: '#5F755A',
	            data: [${orderAmount[6] }, ${orderAmount[5]}, ${orderAmount[4]}, ${orderAmount[3]}, ${orderAmount[2]}, ${orderAmount[1]}, ${orderAmount[0]}]
	        }]
	    },

	    // Configuration options go here
	    options: {}
	});
	</script>
</body>
</html>