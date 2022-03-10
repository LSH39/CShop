<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/shopping2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>주문 상품</h2>
		 </div>
    <fieldset>
        <table class="shoppingTable" style="width:100%;">
            <tr class="titlePr">
                <th style="text-align:center;" colspan="3">상품 정보</th><th style="text-align:center;" >수량</th><th style="text-align:center;" >주문 진행</th><th style="text-align:center;">금액</th>
            </tr>
			<c:forEach items="${slist}" var="s">
        <tr class="list" style="text-align:center;">
            <td  class="imgPr"  style="	width: 100px;height: 100px;"><img src="/upload/product/${s.productImage}"></td>
            <td colspan="2" style="text-align:left;">
                [${s.productName}]<br>
                색상 : ${s.productColor }<br>
                사이즈 : ${s.productSize }
            </td>            
            <td>${s.orderCount}</td>
            <td>		  
                           <c:choose>
                            <c:when test="${s.orderStatus eq 0 }">
                            구매취소
                            </c:when>
                            <c:when test="${s.orderStatus eq 1 }">
                            결제완료
                            </c:when>
                            <c:when test="${s.orderStatus eq 2 }">
                            배송중
                            </c:when>
                            <c:otherwise>
                            배송완료<br>
                            <a class="linkbtn2"  href="/reviewFrm?productNo=${s.productNo }">리뷰쓰기</a>
                            </c:otherwise>
                        </c:choose>
             </td>
            <td>${s.productPrice}</td>
       </c:forEach>
       </table>
       <div class="tbtitle">
        <h4 style="padding-bottom:10px;border-bottom:2px solid; ">주문자 및 배송정보</h4>
		
       </div> 
        <table class="orderinfo">
        <tr>
            <td>주문자명  </td><td>${o.memberName }</td>
        </tr>
        <tr>
            <td>핸드폰  </td><td>${o.memberPhone}</td>
        </tr>
        <tr>
            <td>주소 </td><td>(${o.postcode })<br>${o.addressRoad }${o.addressDetail} </td>
        </tr>
        </table>
        <div class="tbtitle">
        <h4 style="padding-bottom:10px; border-bottom:2px solid; ">결제 정보</h4>
        </div>
       
        <table class="seller">
        <tr>
            <td>주문 금액 </td><td>${o.orderPrice }<span>원</span> </td>
        </tr>
        <tr>
            <td>포인트 사용  </td><td>${o.orderPoint }<span>원</span> </td>
        </tr>
        <tr>
            <td>배송비</td><td>${o.deliveryPrice }<span>원</span>  </td>
        </tr>
        <tr>
            <td>최종 결제 금액</td><td>${o.paymentPrice }<span>원</span> </td>
        </tr>
   	 </table>
        </fieldset>
		</div>
	</div>
	<script>

	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>