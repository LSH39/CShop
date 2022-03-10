<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
    <link href="/css/admin.css" rel="stylesheet" type="text/css">
    <link href="/css/saleProduct.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/admin_date.js"></script>
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
				        	<h2>상품별 판매량</h2>
				 	</div>
					<div class="c-right-inner">
		                    <div class="admin-button-top">
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(1);">일별</button>
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
		                    <table class="admin-table table">
		                        <tr class="admin-table-title">
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">순위</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품번호</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매량</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매자명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">카테고리</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">브랜드</th>
		                        </tr>
		                    </table>
		                    <table class="admin-table table">
		                        <tr class="admin-table-title">
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">순위</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품번호</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매량</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매자명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">카테고리</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">브랜드</th>
		                        </tr>
		                    </table>
		                    <table class="admin-table table">
		                        <tr class="admin-table-title">
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">순위</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품번호</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">상품명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매량</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">판매자명</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">카테고리</th>
		                            <th style="padding:10px 5px 10px 5px; height:43px; line-height:43px;">브랜드</th>
		                        </tr>
		                    </table>
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
	
		function update1(){
			var selectDaily = $("#daily").val();
			var result = $(".table").append();
			var title = $(".admin-table-title");
			$.ajax({
				url : "/saleProductDaily",
				type : "post",
				data : {selectDaily:selectDaily},
				success : function(data){
					for(j=0;j<3;j++){
						title.eq(j).nextAll('tr').remove();
					}
					//title.nextAll().remove();
					for(i=0; i<10;i++){
						var html = "<tr><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].rnum+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productNo+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productName+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productSellCount+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].sellerName+"</td>";
						var cate1 = "";
						if(data.product[i].productCategory1 == 1){
							cate1="남성"
						}else if(data.product[i].productCategory1 == 2){
							cate1="여성"
						}else if(data.product[i].productCategory1 == 3){
							cate1="키즈"
						}
						var cate2 = "";
						if(data.product[i].productCategory2 == 1){
							cate2="아우터"
						}else if(data.product[i].productCategory2 == 2){
							cate2="상의"
						}else if(data.product[i].productCategory2 == 3){
							cate2="하의"
						}else if(data.product[i].productCategory2 == 4){
							cate2="언더웨어"
						}else if(data.product[i].productCategory2 == 5){
							cate2="스포츠웨어"
						}else if(data.product[i].productCategory2 == 6){
							cate2="ETC"
						}
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+cate1+" / "+cate2+"</td>";
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productBrand+"</td><tr>";
						result.append(html);
					
					}
					
				}
			});
		}
		
		function update2(){
			var selectMonth = $("#month").val();
			var result = $(".table").append();
			var title = $(".admin-table-title");
			$.ajax({
				url : "/saleProductMonth",
				type : "post",
				data : {selectMonth:selectMonth},
				success : function(data){
					for(j=0;j<3;j++){
						title.eq(j).nextAll('tr').remove();
					}
					for(i=0; i<10;i++){
						var html = "<tr><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].rnum+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productNo+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productName+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productSellCount+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].sellerName+"</td>";
						var cate1 = "";
						if(data.product[i].productCategory1 == 1){
							cate1="남성"
						}else if(data.product[i].productCategory1 == 2){
							cate1="여성"
						}else if(data.product[i].productCategory1 == 3){
							cate1="키즈"
						}
						var cate2 = "";
						if(data.product[i].productCategory2 == 1){
							cate2="아우터"
						}else if(data.product[i].productCategory2 == 2){
							cate2="상의"
						}else if(data.product[i].productCategory2 == 3){
							cate2="하의"
						}else if(data.product[i].productCategory2 == 4){
							cate2="언더웨어"
						}else if(data.product[i].productCategory2 == 5){
							cate2="스포츠웨어"
						}else if(data.product[i].productCategory2 == 6){
							cate2="ETC"
						}
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+cate1+" / "+cate2+"</td>";
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productBrand+"</td><tr>";
						result.append(html);
					
					}
						
				}
			});
		}
		
		function update3(){
			var selectPeriodStart = $("#periodStart").val();
			var selectPeriodEnd = $("#periodEnd").val();
			var result = $(".table").append();
			var title = $(".admin-table-title");
			$.ajax({
				url : "/saleProductPeriod",
				type : "post",
				data : {selectPeriodStart:selectPeriodStart,selectPeriodEnd:selectPeriodEnd},
				success : function(data){
					for(j=0;j<3;j++){
						title.eq(j).nextAll('tr').remove();
					}
					for(i=0; i<10;i++){
						var html = "<tr><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].rnum+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productNo+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productName+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productSellCount+"</td><td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].sellerName+"</td>";
						var cate1 = "";
						if(data.product[i].productCategory1 == 1){
							cate1="남성"
						}else if(data.product[i].productCategory1 == 2){
							cate1="여성"
						}else if(data.product[i].productCategory1 == 3){
							cate1="키즈"
						}
						var cate2 = "";
						if(data.product[i].productCategory2 == 1){
							cate2="아우터"
						}else if(data.product[i].productCategory2 == 2){
							cate2="상의"
						}else if(data.product[i].productCategory2 == 3){
							cate2="하의"
						}else if(data.product[i].productCategory2 == 4){
							cate2="언더웨어"
						}else if(data.product[i].productCategory2 == 5){
							cate2="스포츠웨어"
						}else if(data.product[i].productCategory2 == 6){
							cate2="ETC"
						}
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+cate1+" / "+cate2+"</td>";
						html += "<td style='padding:10px 5px 10px 5px; height:43px; line-height:43px;'>"+data.product[i].productBrand+"</td><tr>";
						result.append(html);
					
					}
						
				}
			});
		}
	
	</script>

	
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>