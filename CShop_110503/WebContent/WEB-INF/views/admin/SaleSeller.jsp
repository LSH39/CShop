<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
    <link href="/css/admin.css" rel="stylesheet" type="text/css">
    <link href="/css/saleSeller.css" rel="stylesheet" type="text/css">
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
				        	<h2>판매자별 판매량</h2>
				 	</div>
					<div class="c-right-inner">
		                    <table class="admin-table">
		                        <tr>
		                            <th>순위</th>
		                            <th>판매자 No</th>
		                            <th>총 판매량</th>
		                            <th>대표상품명</th>
		                            <th>대표상품<br>판매량</th>
		                            <th>대표상품<br>카테고리</th>
		                            <th>브랜드</th>
		                        </tr>
		                        
		                        <tr>
		                            <td>${1 }</td>
		                            <td>${memberNo0}</td>
		                            <td>${totalSale0}</td>
		                            <td>${p0.productName}</td>
		                            <td>${p0.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p0.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p0.productCategory1 == 2}">여성</c:when>
											<c:when test="${p0.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p0.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p0.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p0.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p0.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p0.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p0.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p0.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${2 }</td>
		                            <td>${memberNo1}</td>
		                            <td>${totalSale1}</td>
		                            <td>${p1.productName}</td>
		                            <td>${p1.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p1.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p1.productCategory1 == 2}">여성</c:when>
											<c:when test="${p1.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p1.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p1.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p1.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p1.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p1.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p1.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p1.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${3 }</td>
		                            <td>${memberNo2}</td>
		                            <td>${totalSale2}</td>
		                            <td>${p2.productName}</td>
		                            <td>${p2.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p2.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p2.productCategory1 == 2}">여성</c:when>
											<c:when test="${p2.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p2.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p2.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p2.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p2.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p2.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p2.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p2.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${4 }</td>
		                            <td>${memberNo3}</td>
		                            <td>${totalSale3}</td>
		                            <td>${p3.productName}</td>
		                            <td>${p3.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p3.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p3.productCategory1 == 2}">여성</c:when>
											<c:when test="${p3.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p3.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p3.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p3.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p3.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p3.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p3.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p3.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${5 }</td>
		                            <td>${memberNo4}</td>
		                            <td>${totalSale4}</td>
		                            <td>${p4.productName}</td>
		                            <td>${p4.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p4.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p4.productCategory1 == 2}">여성</c:when>
											<c:when test="${p4.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p4.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p4.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p4.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p4.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p4.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p4.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p4.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${6 }</td>
		                            <td>${memberNo5}</td>
		                            <td>${totalSale5}</td>
		                            <td>${p5.productName}</td>
		                            <td>${p5.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p5.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p5.productCategory1 == 2}">여성</c:when>
											<c:when test="${p5.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p5.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p5.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p5.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p5.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p5.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p5.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p5.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${7 }</td>
		                            <td>${memberNo6}</td>
		                            <td>${totalSale6}</td>
		                            <td>${p6.productName}</td>
		                            <td>${p6.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p6.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p6.productCategory1 == 2}">여성</c:when>
											<c:when test="${p6.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p6.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p6.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p6.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p6.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p6.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p6.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p6.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${8 }</td>
		                            <td>${memberNo7}</td>
		                            <td>${totalSale7}</td>
		                            <td>${p7.productName}</td>
		                            <td>${p7.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p7.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p7.productCategory1 == 2}">여성</c:when>
											<c:when test="${p7.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p7.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p7.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p7.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p7.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p7.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p7.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p7.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${9 }</td>
		                            <td>${memberNo8}</td>
		                            <td>${totalSale8}</td>
		                            <td>${p8.productName}</td>
		                            <td>${p8.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p8.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p8.productCategory1 == 2}">여성</c:when>
											<c:when test="${p8.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p8.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p8.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p8.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p8.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p8.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p8.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p8.productBrand }</td>
		                        </tr>
		                        <tr>
		                            <td>${10 }</td>
		                            <td>${memberNo9}</td>
		                            <td>${totalSale9}</td>
		                            <td>${p9.productName}</td>
		                            <td>${p9.productSellCount }</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${p9.productCategory1 == 1}">남성	</c:when>
			                            	<c:when test="${p9.productCategory1 == 2}">여성</c:when>
											<c:when test="${p9.productCategory1 == 3}">키즈</c:when>
										</c:choose>
										/
		                            	<c:choose>
										    <c:when test="${p9.productCategory2 == 1}">아우터</c:when>
										    <c:when test="${p9.productCategory2 == 2}">상의</c:when>
										    <c:when test="${p9.productCategory2 == 3}">하의</c:when>
										    <c:when test="${p9.productCategory2 == 4}">언더웨어</c:when>
										    <c:when test="${p9.productCategory2 == 5}">스포츠웨어</c:when>
										    <c:when test="${p9.productCategory2 == 6}">ETC</c:when>
										</c:choose>                            
		                            </td>
		                            <td>${p9.productBrand }</td>
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
	</script>
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>