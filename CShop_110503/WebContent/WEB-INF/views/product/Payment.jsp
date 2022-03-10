<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment</title>
<link href="/css/payment.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top : 70px;">
        <div>
	        <p>주문서</p>
	        <form>
	        	<c:set var="deliveryPrice" value="3000"/>
	        	
	            <table>
	                <caption>주문상품</caption>
	                <tr class="product-table-title">
	                    <th colspan="2">상품정보</th>
	                    <th>수량</th>
	                    <th>배송비</th>
	                    <th>상품금액</th>
	                </tr>
	                
	                
	                <c:forEach items="${list }" var="c" varStatus="i">
                        <tr class="product-table-container">
                            <td><img src="/upload/product/${c.productImage }" style="width:170px;"></td>
                            <td>
	                        <p>[ ${c.sellerName } ]</p>
	                        <p>${c.productName }</p>
	                        <p>색상 : ${c.productColor} / 사이즈 : ${c.productSize}</p>
	                    	</td>
                            <td>${c.orderAmount }개</td>
                            <c:set var="deliveryPrice" value="${deliveryPrice }"/>
                            <c:set var="deliveryPriceTotal" value="${deliveryPriceTotal+deliveryPrice }"/>
                            <td class="deliveryPrice">${deliveryPrice}원</td>
                            <td>${c.productPrice}원</td>
                        </tr>
                    </c:forEach>
	                
	            </table>
	            <table class="delivery">
	                <caption>주문자 및 배송정보<span id="nojusoupdate">배송지 변경은 마이페이지에서 가능합니다.</span></caption>
	                <tr>
	                    <th>주문자명</th>
	                    <td colspan="4">${sessionScope.m.memberName }</td>
	                </tr>
	                <tr>
	                    <th>휴대폰</th>
	                    <td colspan="4">${sessionScope.m.memberPhone }</td>
	                </tr>
	                <tr>
	                    <th>이메일</th>
	                    <td colspan="4">${sessionScope.m.memberEmail }</td>
	                </tr>
	                <tr>
	                    <th rowspan="2">배송지</th>
	                     
	                    <td><input type="radio" name="juso" value="1" id="juso1" checked><label for="juso1">주소 1</label></td>
	                    <td>( <span id="postcode1">${sessionScope.m.postcode1 }</span> )</td>
	                    <td>${sessionScope.m.addressRoad1 }</td>
	                    <td>${sessionScope.m.addressDetail1 }</td>
	                    
	                </tr>
	                <tr>
	                    <td><input type="radio" name="juso" value="2" id="juso2"><label for="juso2">주소 2</label></td>
	                    <td>( <span id="postcode2">${sessionScope.m.postcode2 }</span> )</td>
	                    <td>${sessionScope.m.addressRoad2 }</td>
	                    <td>${sessionScope.m.addressDetail2 }</td>
	                </tr>
	               	
	                <tr>
	                    <th>요청사항</th>
	                    <td colspan="4"><input type="text" name="deliveryRequest" id="deliveryRequest" value="[없음]"></td>
	                </tr>
	            </table>
	            <table class="point">
	                <caption>포인트사용</caption>
	                <tr>
	                    <th>사용가능 포인트 : </th>
	                    <td><span>${sessionScope.m.memberPoint }</span> 점</td>
	                </tr>
	                <tr>
	                    <th>사용 포인트 : </th>
	                    <td><input type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="0" id="usePoint" name="usePoint"> 점</td>
	                </tr>
	            </table>
	            <table class="pay">
	                <tr>
	                    <td>    
	                        <table class="pay-left-top">
	                            <caption>결제</caption>
	                            <tr>
	                                <th>결제방법</th>
	                                <td><input type="radio" name="payMethod" value="1" id="card" checked><label for="card">신용카드</label></td>
	                            </tr>
	                        </table>
	                    </td>
	                    <td rowspan="2">
	                        <table class="pay-right">
	                            <caption>결제 금액</caption>
	                            <tr>
	                                <td>주문금액</td>
	                                <td>${orderPrice} 원</td>
	                            </tr>
	                            <tr>
	                                <td>포인트 사용</td>
	                                <td id="u_p">- 0 원</td>
	                            </tr>
	                            <tr>
	                                <td>배송비</td>
	                                <td>+ ${deliveryPriceTotal} 원</td>
	                            </tr>
	                            <tr id="line">
	                                <td>최종결제금액</td>
	                                <td><span id="totalPrice">${orderPrice + deliveryPriceTotal }</span>원</td>
	                            </tr>
	                        </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                        <table class="pay-left-bottom">
	                            <caption>개인정보 수집/제공</caption>
	                            <tr>
	                                <th>개인정보 제공 동의</th>
	                            </tr>
	                            <tr>
	                                <td>
	                                    <textarea readonly>
필수 개인정보 제공 동의(판매자)
CShop에서 제공하는 상품 및 서비스를 구매하고자 할 경우, CShop은 거래 당사자간 원활한 의사소통 및 배송, 상담 등 거래이행을 위하여 필요한 최소한의 개인정보를 아래와 같이 제공하고 있습니다.
1. 개인정보를 제공받는 자 : 상품 및 서비스 판매자
2. 제공하는 개인정보 항목 : 이름, 닉네임, (휴대)전화번호, 상품 구매정보, 결제수단, 상품 수령인 정보(배송상품:수령인명, 주소, (휴대)전화번호)
3. 개인정보를 제공받는 자의 이용목적 : 판매자와 구매자의 원활한 거래 진행, 본인의사의 확인, 고객상담 및 불만처리/부정이용 방지 등의 고객관리, 물품배송, 새로운 상품/서비스 정보와 고지사항의 안내, 상품/서비스 구매에 따른 혜택 제공
4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시까지 보존합니다. 단, 관계 법령의 규정에 의하여 일정 기간 보존이 필요한 경우에는 해당 기간만큼 보관 후 삭제합니다.
위 개인정보 제공 동의를 거부할 권리가 있으나, 거부 시 CShop을 이용한 상품 구매가 불가능합니다. 그 밖의 내용은 CShop의 자체 이용약관 및 개인정보처리방침에 따릅니다.</textarea>
	                                </td>
	                            </tr>
	                        </table>
	                    </td>
	                </tr>
	            </table>
	            <div>
	                <p>주문 내용을 확인하였으며, 정보 제공 등에 동의합니다.</p>
	                <button type="button" id="payment">결제하기</button>
	            </div>
	        </form>
        </div>
    </div>
    
    <script>
    	var point;
    	var paymentPrice;  // 총결제금액
    	
    	$("#usePoint").change(function() {
    		point = $(this).val();
    		if(point > 0 && point <= ${sessionScope.m.memberPoint } && point <= ($("#totalPrice").html()-10) ){
	    		var html = "- "+point+" 원";
	    		$("#u_p").html(html);

	    		paymentPrice = ${orderPrice + deliveryPriceTotal} - point;
	    		$("#totalPrice").html(paymentPrice);
   			}else{
    			alert("다시 입력해주세요.");
    			$(this).val("0");
    			var html = "- 0 원";
	    		$("#u_p").html(html);
   			}
    			
   		});
    	
    	$("#payment").click(function(){
			var addr1;
			var addr2;
			var addr;
			var postcode;
			var paymentMethod = $("#card").val();
			var deliveryRequest = $("#deliveryRequest").val();
			point = $("#usePoint").val();
			paymentPrice = $("#totalPrice").html();
			
			
			if($('#juso1').is(":checked") == true){
				addr1 = $("#juso1").parent().next().next().html();
				addr2 = $("#juso1").parent().next().next().next().html();
				addr = addr1 + addr2;
				postcode = $("#postcode1").html();
			}else{
				addr1 = $("#juso2").parent().next().html();
				addr2 = $("#juso2").parent().next().next().html();
				addr = addr1 + addr2;
				postcode = $("#postcode2").html();
			}
			
			// impUid
			var d = new Date();
			var date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
			var impUid = postcode+"_"+date;


			// 결제
			IMP.init("imp73163939");  // 결제 API 사용을 위해 가맹점 식별코드 입력
			IMP.request_pay({
				merchant_uid : impUid, // 거래아이디 (productNo_date)
				name : "CShop 결제",
				amount : paymentPrice,
				buyer_email : "${sessionScope.m.memberEmail }",
				buyer_name : "${sessionScope.m.memberName }",
				buyer_phone : "${sessionScope.m.memberPhone }",
			}, function(rsp){
					if(rsp.success){  // 결제 성공시
						//alert("결제 성공"+impUid);
						var memberNo = ${sessionScope.m.memberNo };
						var memberPhone = "${sessionScope.m.memberPhone }";
						var memberEmail = "${sessionScope.m.memberEmail }";
						var orderPrice = ${orderPrice};
						var deliveryPrice = ${deliveryPrice};
						
						var repeat = 0;
						<c:forEach items="${list }" var="c" varStatus="i">
							repeat = ${i.count};
						</c:forEach>
						
						var orderInfoData = {memberNo:memberNo,memberPhone:memberPhone,memberEmail:memberEmail,postcode:postcode,addressRoad:addr1,addressDetail:addr2,
								paymentMethod:paymentMethod,orderPrice:orderPrice,deliveryPrice:deliveryPrice,orderPoint:point,paymentPrice:paymentPrice,
								deliveryRequest:deliveryRequest,impUid:impUid, repeat:repeat};
						
						$.ajax({
							url : "/paymentOrderInfo",
							type : "post",
							data : orderInfoData,
							success : function(data){
								//alert("db성공");
								
								var orderProduct = [];
								<c:forEach items="${list }" var="c" varStatus="i">
									var orderNo = data[${i.index}];
									var sellerNo = ${c.productSeller};
									var productNo = ${c.productNo};
									var productColor = "${c.productColor}";
									var productSize = "${c.productSize}";
									var productPrice = ${c.productPrice};
									var productCount = ${c.orderAmount};
									
									var opd = {orderNo:orderNo,sellerNo:sellerNo,productNo:productNo,productColor:productColor,productSize:productSize,productPrice:productPrice,productCount:productCount};
									orderProduct.push(opd);
									
								</c:forEach>
								
									// 직렬화
									var orderProductData = JSON.stringify(orderProduct);
	
	
		    						$.ajax({
		    					        url : "/paymentOrderProduct",
		    					        type: "POST",
		    					        data: {orderProductData:orderProductData},
		    					        success: function(data) {
		    					            //alert("paymentPD 성공");
		    					            var orderNos ="";
		    					            for(var i=0; i<data.length;i++){
		    					            	orderNos = orderNos + data[i];
		    					            	if(i == data.length-1){
		    					            		orderNos = orderNos;
		    					            	}else{
		    					            		orderNos = orderNos+ " / ";
		    					            	}
		    					            }
		    					            location.href="/paymentSuccess?orderNo="+orderNos;
		    					        },
		    					        error: function() {
		    					      		// 에러 : orderProduct DB 등록 오류
		    					            alert("결제에 실패했습니다.");
		    					        }
		    					    });
	
	
								},error : function(){
									console.log("에러발생");
								}
						
							});
							
						
	
				
					}else{
						alert("결제실패");
					}
					
				
			});
	
		});
    	
    	
    </script>
    
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>