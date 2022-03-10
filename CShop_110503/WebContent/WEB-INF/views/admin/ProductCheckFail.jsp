<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css">
<link href="/css/productCheck.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
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
				        	<h2>인증 실패 상품</h2>
				 	</div>
					<div class="c-right-inner">
		                <table class="admin-table">
		                    <tr>
		                        <th>상품 번호</th>
		                        <th>상품명</th>
		                        <th>상품 수</th>
		                        <th>판매자명</th>
		                        <th>사업자 등록번호</th>
		                        <th>인증 파일</th>
		                        <th>인증</th>
		                        <th></th>
		                    </tr>
		                    
		                    <c:forEach items="${list }" var="p" varStatus="i">
		                        <tr>
		                            <td>${p.productNo }</td>
		                            <td>${p.productName }</td>
		                            <td>${p.productSellCount }</td>
		                            <td>${p.sellerName }</td>
		                            <td>${p.businessNo }</td>
		                            <td><a href="productCheckFile?filename=${p.productFile }">${p.productFile }</a></td>
		                            <td>
		                                <div class="select-wrap">
		                                    <select class="admin-select">
		                                        <option value="1">미인증</option>
		                                        <option value="2">인증완료</option>
		                                        <option value="0" selected>인증실패</option>
		                                    </select>
		                                </div>
		                            </td>
		                            <td><button type="button" class="admin-button admin-button-update updatebtn">변경</button></td>
		                        </tr>
		                    </c:forEach>
		                    <c:if test="${fn:length(list) <= 9}">
		                      	<c:forEach begin="${fn:length(list)}" end="9">
		                        <tr>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                        </tr>
		                    	</c:forEach>
		                    </c:if>
		                    
		                    </table>
		                    
		                    <div id="pageNavi" class="admin-navi">${pageNavi }</div>
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
		
		$(".updatebtn").click(function(){
			var productStatus = $(this).parent().prev().children().children().val();
			var productNo = $(this).parent().parent().children().eq(0).html();
			location.href="/productCheckFailUpdate?productNo="+productNo+"&productStatus="+productStatus;
		});
	</script>
	
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>