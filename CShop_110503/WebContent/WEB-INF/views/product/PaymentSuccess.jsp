<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment</title>
<link href="/css/paymentSuccess.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top:70px;">
        <div>
            <div>
                <pre id="pre1"><span id="span1">CShop</span>을 이용해주셔서 감사합니다.</pre>
                <pre id="pre2">고객님의 주문이 완료되었습니다.</pre>
                <pre id="pre3">고객님이 주문하신 주문번호는<br><span id="span2">${orderNo }</span> 입니다.</pre>
                <br>
                <pre id="pre4">주문내역 및 배송에 관한 안내는 <button type="button" id="myorder-button" onclick="location.href='/shoppingList?reqPage=1&selectmenu=0'">주문조회</button> 를 통하여 가능합니다.</pre>
            </div>
        </div>
    </div>
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>