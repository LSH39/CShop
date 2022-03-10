<%@page import="product.model.vo.ProductQNA"%>
<%@page import="product.model.vo.ProductOption"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%
    	Product p = (Product)request.getAttribute("p");
    	ArrayList<ProductOption> poList = (ArrayList<ProductOption>)request.getAttribute("poList");
    	String sellerId = (String)request.getAttribute("sellerId");
    	ArrayList<String> pColor = (ArrayList<String>)request.getAttribute("pColor");
    	ArrayList<String> pSize = (ArrayList<String>)request.getAttribute("pSize");
    	int reviewCount = (Integer)request.getAttribute("reviewCount");
    	int qnaCount = (Integer)request.getAttribute("qnaCount");
    	int start = (Integer)request.getAttribute("start");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	ArrayList<ProductQNA> pqList = (ArrayList<ProductQNA>)request.getAttribute("pqList");
    	ArrayList<ProductQNA> pqList2 = (ArrayList<ProductQNA>)request.getAttribute("pqList2");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connect Shop</title>
<link rel="stylesheet" href="/css/productView.css?ver=2">
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top:130px">
		<%--선택 카테고리 표시 ^^...--%>
		<%if(p.getProductCategory1() == 1) {%>
			<%if(p.getProductCategory2() == 1) {%>
				<h4>HOME ＞ MEN ＞ OUTER</h4>
			<%} else if(p.getProductCategory2() == 2) {%>
				<h4>HOME ＞ MEN ＞ TOP</h4>
			<%} else if(p.getProductCategory2() == 3) {%>
				<h4>HOME ＞ MEN ＞ BOTTOM</h4>
			<%} else if(p.getProductCategory2() == 4) {%>
				<h4>HOME ＞ MEN ＞ UNDERWEAR</h4>
			<%} else if(p.getProductCategory2() == 5) {%>
				<h4>HOME ＞ MEN ＞ SPORTSWEAR</h4>
			<%} else if(p.getProductCategory2() == 6) {%>
				<h4>HOME ＞ MEN ＞ ETC</h4>
			<%} %>
		<%}else if(p.getProductCategory1() == 2) {%>
			<%if(p.getProductCategory2() == 1) {%>
				<h4>HOME ＞ WOMEN ＞ OUTER</h4>
			<%} else if(p.getProductCategory2() == 2) {%>
				<h4>HOME ＞ WOMEN ＞ TOP</h4>
			<%} else if(p.getProductCategory2() == 3) {%>
				<h4>HOME ＞ WOMEN ＞ BOTTOM</h4>
			<%} else if(p.getProductCategory2() == 4) {%>
				<h4>HOME ＞ WOMEN ＞ UNDERWEAR</h4>
			<%} else if(p.getProductCategory2() == 5) {%>
				<h4>HOME ＞ WOMEN ＞ SPORTSWEAR</h4>
			<%} else if(p.getProductCategory2() == 6) {%>
				<h4>HOME ＞ WOMEN ＞ ETC</h4>
			<%} %>
		<%}else if(p.getProductCategory1() == 3) {%>
			<%if(p.getProductCategory2() == 1) {%>
				<h4>HOME ＞ KIDS ＞ OUTER</h4>
			<%} else if(p.getProductCategory2() == 2) {%>
				<h4>HOME ＞ KIDS ＞ TOP</h4>
			<%} else if(p.getProductCategory2() == 3) {%>
				<h4>HOME ＞ KIDS ＞ BOTTOM</h4>
			<%} else if(p.getProductCategory2() == 4) {%>
				<h4>HOME ＞ KIDS ＞ UNDERWEAR</h4>
			<%} else if(p.getProductCategory2() == 5) {%>
				<h4>HOME ＞ KIDS ＞ SPORTSWEAR</h4>
			<%} else if(p.getProductCategory2() == 6) {%>
				<h4>HOME ＞ KIDS ＞ ETC</h4>
			<%} %>
		<%} %>
		<%------------------------------------------------------------------------ --%>
		<%--상품정보 --%>
		<div class="productBox">
			<%--이미지 띄우기 --%>
			<div class="productImageBox">
				<img src="/upload/product/${p.productImage }" style="width:450px;height: 500px;">
			</div>
			<%--장바구니로 상품정보 / 상품데이터 넘겨주기 --%>
			<form action="/cart" method="post">
			<div class="productInfoBox">
				<%--브랜드, 상품명, 가격, 배송정보, 판매자 --%>
				<p id="pBrand">${p.productBrand }</p>
				<p id="pName">${p.productName }</p>
				<p id="pPrice" class="price">${poList[0].productPrice} 원</p>
				<hr>
				<p id="dPrice">배송비 : 3,000원</p>
				<span id="seller">판매자 : ${sellerId }</span>
				<button type="button" id="sellerMemo" class="btn" data-toggle="modal" data-target="#myModal">판매자 보장각서 보기</button><br>
				<%--판매자 보장각서 modal --%>
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3 class="modal-title">판매자 보장각서</h3>
     						 </div>
     						 <div class="modal-body">
     						 	<p>브랜드: ${p.productBrand }</p>
     						 	<p>상품명 : ${p.productName }</p>
     						 	<p>상품번호 : ${p.productId }</p>
     						 	<p>판매자 ID : ${sellerId }</p>
     						 	<hr>
     						 	<p class="MemoContent">각서 내용</p>
     						 	<p class="MemoContent">본인은 CShop에서 판매하는 모든 상품이 상표법등에 법적하자가 없는 정품임을 보장하며, 만일 위조상품(가품)임이 밝혀질 경우 민/형사상의 모든 책임을 지며 판매가격의 2배를 보상할 것을 약속합니다.</p>
     						 	<hr>
     						 	<p>※ 본 각서는 판매자가 상품을 판매하는 일로부터 적용됩니다.</p>
     						 	<p>※ 본 보장각서는 위조상품(가품)판매로 인한 분쟁발생시 민사적 해결에 그 이유가 있습니다.</p>
     						 	<p>※  타인명의를 도용하여 판매하는 경우에도 명의도용에 대한 법적책임까지 묻게 됩니다.</p>
     						 	<br><br>
     						 	<span><img src="/img/logo.jpg" ></span><span id="sellersign">작성자 : ${sellerId }</span>
     						 </div>
						</div>
					</div>
				</div>
				<%--사이즈 버튼--%>
				<p id="pSize" style="font-size:18px">사이즈 선택 : 
					<%for(int i=0;i<pSize.size();i++) {%>
						<button type="button" class="btn sizeBtn" value="<%=pSize.get(i)%>"><%=pSize.get(i)%></button>
					<%} %>
					<%-- jQuery로 선택한 사이즈 넘겨주기 --%>
					<input type="hidden" name="productSize">
				</p>
				<%--컬러 버튼 --%>
				<p id="pColor" style="font-size:18px">색상 선택 :
					<%for(int i=0;i<pColor.size();i++) {%>
						<button type="button" class="btn colorBtn" value="<%=pColor.get(i) %>"><%=pColor.get(i)%></button>	
					<%} %>
					<%-- jQuery로 선택한 컬러 넘겨주기 --%>
					<input type="hidden" name="productColor">
				</p>
				<%--수량 --%>
				<p class="count">
					<span>
						수량 : 
						<button type="button" class="pm btn">―</button>
						<%-- 선택한 수량 넘겨주기 --%>
						<input type="text" class="amount" name="orderAmount" value="1">
						<button type="button" class="pm btn">＋</button>
					</span>
					<%--가격 --%>
					<span class="total" style="margin-left:15px;">
						총
					<span id="totalPrice" class="price"><%=poList.get(0).getProductPrice() %></span>원
					<%-- jQuery로 총 가격 넘겨주기 --%>
					</span>
				</p>		
			</div>
			<%if(m != null && m.getMemberLevel() == 1){ %>
				<button type="submit" class="btn" id="cart">장바구니 이동</button>			
				<%--나머지 값 Cart로 넘겨주기 --%>
				<input type="hidden" name="productNo" value="<%=p.getProductNo() %>">
				<input type="hidden" name="memberNo" value="<%=m.getMemberNo() %>">
				<input type="hidden" name="productPrice" value="<%=poList.get(0).getProductPrice()%>">
				<input type="hidden" name="productSeller" value="<%=p.getProductSeller()%>">
				<input type="hidden" name="productName" value="<%=p.getProductName() %>">
				<input type="hidden" name="productImage" value="<%=p.getProductImage() %>">
				<input type="hidden" name="productId" value="<%=p.getProductId() %>">
				<input type="hidden" name="sellerNo" value="<%=p.getProductSeller() %>">
				<input type="hidden" name="selectNum">
			<%}else{ %>
				<button type="button" class="btn" id="noCart">장바구니 이동</button>
			<%} %>	
			</form>
		</div>
		
		<%------------------------------------------------------------------------ --%>
		
		<%--네비 --%>
		<div class="selectMenu">
			<a href="productView?id=<%=p.getProductId() %>&seller=<%=p.getProductSeller() %>&reqPage=1" >상품정보</a> 
			<a href="productReviewView?id=<%=p.getProductId() %>&seller=<%=p.getProductSeller() %>&reqPage=1">상품리뷰(<%=reviewCount %>)</a> 
			<a href="productQnAView?id=<%=p.getProductId() %>&seller=<%=p.getProductSeller() %>&reqPage=1"  style="color:#5F755A">상품문의(<%=qnaCount %>)</a>
		</div>
		
		<%------------------------------------------------------------------------ --%>
		
		<%--QnA --%>
		<div class="qnaBox">
		<%--직거래 주의 박스 --%>
			<div class="cautionBox">
				<span><img src="/img/caution.png"></span>
				<span id="cautionMsg">CShop을 통해 거래하지 않고 직거래를 유도하는 판매자와 직접 거래하신 경우에 발생한 피해는 책임지지 않습니다.</span>
			</div>
		</div>
		<%--QNA 리스트 --%>
		<%if(pqList.size() > 0){ %>
			<div class="qnaList">
				<%for(ProductQNA pq : pqList) {%>
				<span class="q1">
					<%if(pq.getPqStatus() == 1){ %>
						접수중
					<%} else{%>
						답변완료
					<%} %>
				</span>
				<span>
					<a class="toggleQnA">
						<%if(pq.getPqTitle().length() > 40) {%>
							<%String subString = pq.getPqTitle().substring(0, 40);
							subString = subString.trim() + "..."; %>
							<%=subString %>
						<%}else{ %>
							<%=pq.getPqTitle() %>
						<%} %>
					</a>
				</span>
				<span class="q2">
					<%String writerString = pq.getPqWriterId().substring(0,3);
					writerString = writerString.trim() + "****"; %>
					<%=writerString %>
				</span>
				<span><%=pq.getPqDate() %></span>
				<%--문의 제목 클릭 시 --%>
				<div class="qnaDetailView">
					<div class="questionBox">
						<span>Q</span><span class="qbox1"><%=pq.getPqContent() %></span>
					</div>
					<%for(ProductQNA pqq : pqList2) {%>
						<%if(pq.getPqNo() == pqq.getPqRef()) {%>
						<div class="answerBox">
							<span class="qb3">A</span><span class="qbox2"><%=pqq.getPqContent() %></span>
						</div>
						<%} %>
					<%} %>
				</div>
				<hr>	
				<%} %>
			</div>
		<%} %>
		<%--문의하기 Modal --%>
		<div class="qnaBtnBox">
		<%if(m != null ) {%>
			<button type="button" class="btn qnaBtn" data-toggle="modal" data-target="#myModal2">문의하기</button>
		<%} %>
		</div>
		<div class="modal" id="myModal2">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="modal-title">상품문의하기</h3>
     				</div>
     				<div class="modal-body">
     					<form action="/productQnA" method="post">
	     					<br>
	     					<table class="qTBL">
	     						<tr>
	     							<th>문의 제목</th>
	     							<td><input type="text" name="pqTitle" class="form-control" style="width:470px;"></td>
	     						</tr>
	     						<tr style="margin-top:10px">
	     							<th>문의 내용</th>
	     							<td><textarea name="pqContent" class="form-control" style="width:470px;resize: none; height: 300px; margin-right:20px;" ></textarea></td>
	     						</tr>
	     					</table>
	     					<%if(m != null ) {%>
	     					<button type="submit" class="btn btn-block intputBtn">문의하기</button>
	     					<input type="hidden" name="pqWriter" value="<%=m.getMemberNo()%>">
	     					<input type="hidden" name="productNo" value="<%=p.getProductNo() %>">
	     					<input type="hidden" name="productId" value="<%=p.getProductId() %>">
	     					<input type="hidden" name="productSeller" value="<%=p.getProductSeller() %>">
	     					<%} %>
     					</form>
     				</div>
				</div>
			</div>
		</div>
		<div id="reviewPageNavi" style="text-align: center; " ><%=pageNavi %></div>
		<%------------------------------------------------------------------------ --%>
		
	</div>
	<script>
		//가격에 ' , '  표시
		$(".price").click(function(){
			var price = $(this).html();
			 $(this).html(price.replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		});
		$(".price").trigger("click");
		
		//수량 증감
		$(".pm").click(function(){
			var currAmount = $(".amount").val();
			
			if($(this).html() == "＋"){
				$(".amount").val(++currAmount);
			}else{
				if(currAmount != 1){
					$(".amount").val(--currAmount);					
				}
			}
			$("#totalPrice").html(currAmount*<%=poList.get(0).getProductPrice()%>);
			
		});
		
		//사이즈 클릭 시  버튼 색 
		$(".sizeBtn").click(function(){
			//리셋
			$(".sizeBtn").css("border","1px solid #a9a9a9");
			$(".sizeBtn").css("background-color","#fff");
			$(".sizeBtn").css("color","black");
			//클릭하면 바껴!
			$(this).css("color", "#fff");
			$(this).css("background-color","#5F755A ");
			$(this).css("border","1px solid #5F755A");
			$("[name=productSize]").val($(this).val());
		})
		
		//컬러 클릭 시 버튼 색
		$(".colorBtn").click(function(){
			//리셋
			$(".colorBtn").css("border","1px solid #a9a9a9");
			$(".colorBtn").css("background-color","#fff");
			$(".colorBtn").css("color","black");
			//클릭하면 바껴!
			$(this).css("color", "#fff");
			$(this).css("background-color","#5F755A ");
			$(this).css("border","1px solid #5F755A");
			//클릭한 값 hidden value에 넣기
			$("[name=productColor]").val($(this).val());
		});
	
		//장바구니 유효성검사 
		$("#cart").click(function(){
			if($("input[name=productSize]").val() == ""){
				alert("사이즈를 선택해주세요.");
				return false;
			}
			if($("input[name=productColor]").val() == ""){
				alert("컬러를 선택해주세요.");
				return false;
			}
		});
		$("#cart").click(function(){
			if($("input[name=productSize]").val() != "" && $("input[name=productColor]").val() != ""){
				if(confirm("장바구니로 이동하시겠습니까?")){
					$("[name=selectNum]").val(1);
				}else{
					$("[name=selectNum]").val(2);
				}
			}
			
		});
		
		//문의 제목 클릭시
		$(".toggleQnA").click(function(){
			var index = $(".toggleQnA").index(this);
			$(".qnaDetailView").eq(index).toggle();
		})
		$(".toggleQnA").trigger("click");
		
		//문의하기 유효성검사
		$(".intputBtn").click(function(){
			if($("[name=pqTitle]").val() == ""){
				alert("문의 제목을 입력해주세요.");
				return false;
			}
			if($("[name=pqContent]").val() == ""){
				alert("문의 내용을 입력해주세요.");
				return false;
			}
		})
		$("#noCart").click(function(){
			alert("구매자로 로그인 후 이용해주세요.");
		})

	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>