   <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/cart.css">
    <link rel="stylesheet" href="/css/default.css">

</head>

<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>

    <div class="main">
        <div class="title">
            <h1>장바구니</h1>
        </div>
        <div class="content">
            <div class="cart-table">
                <table>
                    <thead>
                        <tr id="cart_index">
                            <th colspan="3" >상품정보</th>
                            <th>수량</th>
                            <th>금액</th>
                         
                            <th>배송비</th>
                        </tr>
                    </thead>
                    <tbody class="addCart_item">
                   <c:forEach items="${list }" var="cartitem">
            
                    <tr>
                            <td><input type="checkbox" name="chk" id="chk" value="${cartitem.cartNo }"></td>
                            <td><img src="/upload/product/${cartitem.productImage }"></td>
                    
                            <td>${cartitem.productName }<br>${cartitem.productColor }/${cartitem.productSize }</td>
                            <td>${cartitem.orderAmount }</td>
                            <td>${cartitem.productPrice }원</td> 
                            <td id="delevery">3000원</td>
                    </tr>

                    </c:forEach>
                        
                    </tbody>
                    <tfoot>
                   
                        <tr>
                        
                            <td colspan="6">
                                상품가격
                                <span class="price">
<c:set var = "total" value = "0" />

<c:forEach var="result" items="${list}" varStatus="status">     

<c:set var= "total" value="${total + result.productPrice}"/>

</c:forEach>

<c:out value="${total}"/></span>원 + 배송비
                                <span class="price">3000</span>원 = 총
                                <span class="price">
<c:set var = "total" value = "0" />

<c:forEach var="result" items="${list}" varStatus="status">     
  
<c:set var= "total" value="${total + 3000 + result.productPrice}"/>

</c:forEach>

<c:out value="${total}"/>원 </span>
                            </td>
                            
                        </tr>
                     

                    </tfoot>
               

                        
                </table>
            </div>
            <div class="button-box">
                <button>계속 쇼핑하기</button>
                <button id="cartpay_btn">주문하기</button>
                <button id="cartdelete_btn">삭제하기</button>
            </div>

        </div>
    </div>
<script>
$("#cartdelete_btn").click(function(){
	if($('input:checkbox[id="chk"]').is(":checked")){		 
	var cartNo = $('#chk').val(); //체크 박스 안에 넣어둔 카트번호 값
	var inputs = $("#chk:checked"); //체크 되었을경우
	var num = new Array();	//카트번호 저장할 배열
	console.log(cartNo);
	inputs.each(function(idx,item){
		var cartNo = $(item).val();
		num.push(cartNo);
		console.log(num);
	
	});
	location.href="chkDeleteCart?num="+num.join("/");
	
	 }else{
	 $.ajax({
			url : "/ajaxCartAllDelete",				
			success : function(data){
				location.reload();
				}
})
};

});


$("#cartpay_btn").click(function(){
	if($('input:checkbox[id="chk"]').is(":checked")){
		

    	//체크됐을경우
    	
	var cartNo = $('#chk').val(); //체크 박스 안에 넣어둔 카트번호 값
	var inputs = $("#chk:checked"); //체크 되었을경우
	var num = new Array();	//카트번호 저장할 배열
	//console.log(cartNo);
	inputs.each(function(idx,item){
		var cartNo = $(item).val();
		num.push(cartNo);
	
	});
	location.href="payment?num="+num.join("/");
	}else{
		alert("결제하실 상품을 선택해주세요.");
		/*
	$.ajax({
   		//체크안되어있을경우 세션에서 값 가져옴
   	
   		url : "/payment",		
   		success : function(data){
   			
   		}
	});
		*/
}
});


</script>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>

</html>    