<%@page import="product.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
   	 	ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("pList");
    	int start = (Integer)request.getAttribute("start");
		String pageNavi = (String)request.getAttribute("pageNavi");
		int productTotal = (Integer)request.getAttribute("productTotal");
		
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/product.css?ver=2">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<div class="container" style="margin-top:70px">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
		</div>
			<div class="c-right">
		 <div id="title">
		     <h2>상품 조희</h2>   
		     <div class="productTotal">
		     	<img src="/img/hanger.png" width="50px" height="50px">
		     	<span>판매중인 상품 </span><span> [${productTotal }]</span>
		     </div>	
		     <div>
		     	<table class="table total-table">
		     		<tr>
		     		<th id="check"><input type="checkbox" id="checkAll"></th><th>상품이미지</th><th>상품명</th><th>가격</th><th>인증상태</th><th>관리</th>
		     		</tr>
		     		<%--판매자 상품 리스트 띄우는 for문 --%>
		     		<c:forEach items="${pList }" var="p">
		     			<tr class="totalInfo">
		     				<td><input type="checkbox" class="checkProduct" name="checkId" value=${p.productId }></td>
		     				<td><a href="/productView?id=${p.productId }&seller=${p.productSeller }&reqPage=1"><img src="/upload/product/${p.productImage }" width="100px" height="100px"></a></td>
		     				<td><a href="/productView?id=${p.productId }&seller=${p.productSeller }&reqPage=1" style="color:black">${p.productName }</a></td>
		     				<td class="priceTd">${p.productPrice }</td>
		     				<td>
		     					<%--인증 상태에 따라 다르게 띄우는 if문 --%>
		     					<c:choose>
		     						<c:when test="${p.productStatus == 0 }">
		     							인증실패
		     						</c:when>
		     						<c:when test="${p.productStatus == 1 }">
		     							인증대기
		     						</c:when>
		     						<c:otherwise>
		     							인증완료
		     						</c:otherwise>
		     					</c:choose>
		     				</td>
		     				<td>
		     					<%--인증상태가 대기(1), 완료(2)일 경우에만 삭제, 수정가능한 if문 --%>
		     					<c:if test="${p.productStatus == 1 or p.productStatus == 2 }">
			     					<form action="/updateProductInfoFrm">
			     					<button class="btn updateBtn">상품수정 </button>
			     					<input type="hidden" name="productId" value=${p.productId }>
			     					<input type="hidden" name="memberNo" value=${p.productSeller }>
			     					</form>
			     					<br>
			     					<button class="btn inventory" value=${p.productId } data-toggle="modal" data-target="#myModal">재고확인</button>
		     					</c:if>
		     				</td>
		     			</tr>
		     		</c:forEach>
		     	</table>
		     	<%--재고확인 modal --%>
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">재고 확인</h3>
				     		</div>
				     		<div class="modal-body">
				     		</div>
						</div>
					</div>
				</div>
				<%--jQuery로 선택한 checkBox 삭제 --%>
		     	<button class="btn selectDelBtn">선택삭제</button>
		     	<div id="PageNavi" style="text-align: center; " >${pageNavi }</div>
		     </div>
		 </div>
		</div>
	</div>
	<script>
	//가격에 ' , '  표시
	$(".priceTd").click(function(){
		var price = $(this).html();
		 $(this).html(price.replace(/\B(?=(\d{3})+(?!\d))/g, ","))
	});
	$(".priceTd").trigger("click");
	//재고 ajax
	$(".inventory").click(function(){
		var index = $("#deleteBtn").index(this);
		var productId = $(this).eq(index).val();
		var modalBody = $(".modal-body");
		$.ajax({
			url:"/inventoryCheck",
			type:"get",
			data:{productId:productId},
			success: function(data){
				modalBody.empty();
				var html = "<table class='table'><tr><th>Color</th><th>Size</th><th>수량</th></tr>";
				for(var i=0;i<data.length;i++){
					html += "<tr><td>"+data[i].productColor+"</td><td>"+data[i].productSize+"</td><td> "+data[i].productCount+"개</td></tr>";
				}
				html += "</table>";
				modalBody.append(html);

			}
		});
	})
	//체크박스 전체선택
		$("#checkAll").click(function(){
			if($("#checkAll").prop("checked")){
	            $(".checkProduct").prop("checked",true);
	        }else{
	            $(".checkProduct").prop("checked",false);
	        }
		});
	$(".selectDelBtn").click(function(){
		var checkId = $(".checkProduct:checked");
		var delId = new Array();
		var memberNo = $("[name=memberNo]").val();
		checkId.each(function(idx,item){
			var productId = $(item).val();
			delId.push(productId);
		});
		//선택상품이 없다면
		if(checkId.length == 0){
			alert("삭제할 상품을 선택해주세요");
			return false;
		}
		if(confirm("상품을 삭제하시겠습니까?")){
			location.href="/deleteProduct?productId="+delId.join("/")+"&memberNo="+memberNo;
		}
		console.log(delId);
	});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>