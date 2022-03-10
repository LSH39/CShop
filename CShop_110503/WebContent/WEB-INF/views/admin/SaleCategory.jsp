<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
    <link href="/css/admin.css" rel="stylesheet" type="text/css">
    <link href="/css/saleCategory.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/admin_date.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<c:choose>
		<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
			<div class="container" style="margin-top : 70px;">
				<div class="c-left">
					<jsp:include page="/WEB-INF/views/common/leftNavi_admin.jsp"/>
				</div>
				<div class="c-right">
				 	<div id="title">
				        	<h2>카테고리별 판매량</h2>
				 	</div>
					<div class="c-right-inner">
		                    <div class="admin-button-top">
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(1);" style="width:70px; height:40px">일별</button>
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(2);">월별</button>
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(3);">기간별</button>
		                        <form class="date-form">
		                            <div class="date">
		                                <img src="/img/admin_left.png" onclick="prevDaily();">
		                                <input type="date" id="daily" class="date-input" min="2000-01-01" max="${todayDate }" value="${selectDate }" onkeyup="removeChar(event)">
		                                <!-- max 값은 자바에서 오늘날짜로 가져오기 -->
		                                <img src="/img/admin_right.png" onclick="nextDaily();">
		                            </div>
		                            <button type="button" class="admin-button admin-button-dbselect" onclick="update1();">조회</button>
		                        </form>
		                        <form class="date-form">
		                            <div class="date">
		                                <img src="/img/admin_left.png" onclick="prevMonth();">
		                                <input type="month" id="month" class="date-input" min="2000-01" max="${prevMonth }" value="${selectMonth }" onkeyup="removeChar(event)">
		                                <img src="/img/admin_right.png" onclick="nextMonth();">
		                            </div>
		                            <button type="button" class="admin-button admin-button-dbselect" onclick="update2();">조회</button>
		                        </form>
		                        <form class="date-form">
		                            <div class="date period">
		                                <input type="date" id="periodStart" class="date-input" min="2000-01-01" max="${todayDate }" value="${selectDate }" onkeyup="removeChar(event)" onchange="changeStart();">
		                                <img src="/img/admin_tilde.png" id="tilde">
		                                <input type="date" id="periodEnd" class="date-input" min="2000-01-01" max="${todayDate }" value="${selectDate }" onkeyup="removeChar(event)" onchange="changeEnd();">
		                            </div>
		                            <button type="button" class="admin-button admin-button-dbselect" onclick="update3();">조회</button>
		                        </form>
		                    </div>
		                    <div class="admin-graph">
		                        <div class="admin-graph-main">
		                            <div>
		                                <h3>남성</h3>
		                                <div id="graph1"></div>
		                                <button type="button" class="admin-button detail-button" onclick="detail(1);">상세보기</button>
		                            </div>
		                            <div>
		                                <h3>여성</h3>
		                                <div id="graph2"></div>
		                                <button type="button" class="admin-button detail-button" onclick="detail(2);">상세보기</button>
		                            </div>
		                            <div>
		                                <h3>키즈</h3>
		                                <div id="graph3"></div>
		                                <button type="button" class="admin-button detail-button" onclick="detail(3);">상세보기</button>
		                            </div>
		                        </div>
		                        <div class="admin-graph-detail" id="graph_detail"></div>
		                    </div>
		        	</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container" style="margin-top : 70px; height:600px"></div>
		</c:otherwise>
	</c:choose>
	<script>
		$(function(){
	    	if(${sessionScope.m.memberLevel != 3 }){
	    		alert("잘못된 접근입니다.");
	    		location.href="/";
	    	} 
	    });
		
		
		// 방법 1.
		var total;
		var cate10; var cate11; var cate12; var cate13; var cate14; var cate15; var cate16;
		var cate20; var cate21; var cate22; var cate23; var cate24; var cate25; var cate26;
		var cate30; var cate31; var cate32; var cate33; var cate34; var cate35; var cate36;
		// 방법 2.
		// var arr;

		function update1(){
			for(var j=0; j<3; j++){
				$(".detail-button").eq(j).css("background-color","#ffffff").css("color","#5F755A");
			}
			
			var selectDaily = $("#daily").val();
			$("#graph_detail").children().remove();
			
			$.ajax({
				url : "/saleCategoryDaily",
				type : "post",
				data : {selectDaily:selectDaily},
				success : function(data){
					total = data[0][0];
					cate10 = data[1][0]; cate11 = data[1][1]; cate12 = data[1][2]; cate13 = data[1][3]; cate14 = data[1][4]; cate15 = data[1][5]; cate16 = data[1][6];
					cate20 = data[2][0]; cate21 = data[2][1]; cate22 = data[2][2]; cate23 = data[2][3]; cate24 = data[2][4]; cate25 = data[2][5]; cate26 = data[2][6];
					cate30 = data[3][0]; cate31 = data[3][1]; cate32 = data[3][2]; cate33 = data[3][3]; cate34 = data[3][4]; cate35 = data[3][5]; cate36 = data[3][6];
					
					// 남성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart1);
					function drawChart1() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['남성',     cate10],
					    ['',     total-cate10]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#81a3ff','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph1'));
					  chart.draw(data, options);
					}
					// 여성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart2);
					function drawChart2() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['여성',     cate20],
					    ['',     total-cate20]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#ff8192','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph2'));
					  chart.draw(data, options);
					}
					// 키즈
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart3);
					function drawChart3() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['키즈',     cate30],
					    ['',     total-cate30]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#fff781','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph3'));
					  chart.draw(data, options);
					}
					
				}
			});
		}
		
		function update2(){
			for(var j=0; j<3; j++){
				$(".detail-button").eq(j).css("background-color","#ffffff").css("color","#5F755A");
			}
			
			var selectMonth = $("#month").val();
			$("#graph_detail").children().remove();
			
			$.ajax({
				url : "/saleCategoryMonth",
				type : "post",
				data : {selectMonth:selectMonth},
				success : function(data){
					total = data[0][0];
					cate10 = data[1][0]; cate11 = data[1][1]; cate12 = data[1][2]; cate13 = data[1][3]; cate14 = data[1][4]; cate15 = data[1][5]; cate16 = data[1][6];
					cate20 = data[2][0]; cate21 = data[2][1]; cate22 = data[2][2]; cate23 = data[2][3]; cate24 = data[2][4]; cate25 = data[2][5]; cate26 = data[2][6];
					cate30 = data[3][0]; cate31 = data[3][1]; cate32 = data[3][2]; cate33 = data[3][3]; cate34 = data[3][4]; cate35 = data[3][5]; cate36 = data[3][6];
					
					// 남성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart1);
					function drawChart1() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['남성',     cate10],
					    ['',     total-cate10]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#81a3ff','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph1'));
					  chart.draw(data, options);
					}
					// 여성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart2);
					function drawChart2() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['여성',     cate20],
					    ['',     total-cate20]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#ff8192','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph2'));
					  chart.draw(data, options);
					}
					// 키즈
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart3);
					function drawChart3() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['키즈',     cate30],
					    ['',     total-cate30]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#fff781','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph3'));
					  chart.draw(data, options);
					}
					
				}
			});
		}
		
		function update3(){
			for(var j=0; j<3; j++){
				$(".detail-button").eq(j).css("background-color","#ffffff").css("color","#5F755A");
			}
			
			var selectPeriodStart = $("#periodStart").val();
			var selectPeriodEnd = $("#periodEnd").val();
			$("#graph_detail").children().remove();
			
			$.ajax({
				url : "/saleCategoryPeriod",
				type : "post",
				data : {selectPeriodStart:selectPeriodStart,selectPeriodEnd:selectPeriodEnd},
				success : function(data){
					// 방법 1.
					total = data[0][0];
					cate10 = data[1][0]; cate11 = data[1][1]; cate12 = data[1][2]; cate13 = data[1][3]; cate14 = data[1][4]; cate15 = data[1][5]; cate16 = data[1][6];
					cate20 = data[2][0]; cate21 = data[2][1]; cate22 = data[2][2]; cate23 = data[2][3]; cate24 = data[2][4]; cate25 = data[2][5]; cate26 = data[2][6];
					cate30 = data[3][0]; cate31 = data[3][1]; cate32 = data[3][2]; cate33 = data[3][3]; cate34 = data[3][4]; cate35 = data[3][5]; cate36 = data[3][6];
					// 방법 2.
					// arr = data;  // data 배열을 arr에 넣음 (배열로 저장됨)
					// 방법 3.
					// var cate10 = data[1][0];  // 이렇게 해도 사용 가능
					
					// 남성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart1);
					function drawChart1() {
						// console.log(cate10);  // 방법 1에서. 전역변수라서 값 존재
						// console.log(data[1][0]);  // 방법 1에서. 지역변수라서 undefined
						
						// 방법 1.
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['남성',     cate10],
					    ['',     total-cate10]
					  ]);
						// 방법 2.
					  /*
						var data = google.visualization.arrayToDataTable([
						    ['cate1', '판매량'],
						    ['남성',     arr[1][0]],  // arr[1][0]으로 arr배열 바로 꺼내서 사용
						    ['',     total-arr[1][0]]
					  	]);
					  */
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#81a3ff','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph1'));
					  chart.draw(data, options);
					}
					// 여성
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart2);
					function drawChart2() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['여성',     cate20],
					    ['',     total-cate20]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#ff8192','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph2'));
					  chart.draw(data, options);
					}
					// 키즈
					google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawChart3);
					function drawChart3() {
					  var data = google.visualization.arrayToDataTable([
					    ['cate1', '판매량'],
					    ['키즈',     cate30],
					    ['',     total-cate30]
					  ]);
					  var options = {
					    chartArea:{width:'90%',height:'90%'},
					    colors:['#fff781','#e0e0e0'],
					    slices: [{},{textStyle: {color: '#e0e0e0'}}],
					    legend: { position: 'none'}
					  };
					  var chart = new google.visualization.PieChart(document.getElementById('graph3'));
					  chart.draw(data, options);
					}
					
				}
			});
			
		}
		
		
		function detail(i) {
			for(var j=0; j<3; j++){
				$(".detail-button").eq(j).css("background-color","#ffffff").css("color","#5F755A");
			}
			$(".detail-button").eq(i-1).css("background-color","#5F755A").css("color","#ffffff");
			
			
			var detail1=0; var detail2=0; var detail3=0; var detail4=0; var detail5=0; var detail6=0;
			if(i == 1){
				detail1 = cate11; detail2 = cate12; detail3 = cate13; detail4 = cate14; detail5 = cate15; detail6 = cate16;
			}else if(i == 2){
				detail1 = cate21; detail2 = cate22; detail3 = cate23; detail4 = cate24; detail5 = cate25; detail6 = cate26;
			}else if(i == 3){
				detail1 = cate31; detail2 = cate32; detail3 = cate33; detail4 = cate34; detail5 = cate35; detail6 = cate36;
			}
			  
			google.charts.load('current', {'packages':['corechart']});
		    google.charts.setOnLoadCallback(drawChart3);
			function drawChart3() {
			  var data = google.visualization.arrayToDataTable([
			    ['cate2', '판매량'],
			    ['아우터',    detail1],
			    ['상의',     detail2],
			    ['하의',     detail3],
			    ['언더웨어',   detail4],
			    ['스포츠웨어',  detail5],
			    ['ETC',     detail6]
			  ]);
			  var options = {
			    chartArea:{width:'90%',height:'90%', left:200},
			    //colors:['#fff781','#e0e0e0'],
			    //slices: [{},{textStyle: {color: '#e0e0e0'}}],
			    legend: { position: 'right'}
			  };
			  var chart = new google.visualization.PieChart(document.getElementById('graph_detail'));
			  chart.draw(data, options);
			}
		};
	
	</script>
	
	
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body></body>
</html>