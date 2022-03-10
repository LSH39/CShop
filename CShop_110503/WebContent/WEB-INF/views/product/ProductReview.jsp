<%@page import="product.model.vo.ProductReview"%>
<%@page import="product.model.vo.ProductOption"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Product p = (Product)request.getAttribute("p");
    	ArrayList<ProductOption> poList = (ArrayList<ProductOption>)request.getAttribute("poList");
    	String sellerId = (String)request.getAttribute("sellerId");
    	ArrayList<String> pColor = (ArrayList<String>)request.getAttribute("pColor");
    	ArrayList<String> pSize = (ArrayList<String>)request.getAttribute("pSize");
    	int averageStar = (Integer)request.getAttribute("averageStar");
    	ArrayList<ProductReview> prList = (ArrayList<ProductReview>)request.getAttribute("prList");
    	int reviewCount = (Integer)request.getAttribute("reviewCount");
    	int start = (Integer)request.getAttribute("start");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	ArrayList<ProductReview> prList2 = (ArrayList<ProductReview>)request.getAttribute("prList2");
    	int qnaCount = (Integer)request.getAttribute("qnaCount");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connect Shop</title>
<link rel="stylesheet" href="/css/productView.css?ver=3">
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
				<img src="/upload/product/<%=p.getProductImage()%>" style="width:450px;height: 500px;">
			</div>
			<%--장바구니로 상품정보 / 상품데이터 넘겨주기 --%>
			<form action="/cart" method="post">
			<div class="productInfoBox">
				<%--브랜드, 상품명, 가격, 배송정보, 판매자 --%>
				<p id="pBrand"><%=p.getProductBrand() %></p>
				<p id="pName"><%=p.getProductName() %></p>
				<p id="pPrice" class="price"><%=poList.get(0).getProductPrice() %> 원</p>
				<hr>
				<p id="dPrice">배송비 : 3,000원</p>
				<span id="seller">판매자 : <%=sellerId %></span>
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
     						 	<p>브랜드: <%=p.getProductBrand() %></p>
     						 	<p>상품명 : <%=p.getProductName() %></p>
     						 	<p>상품번호 : <%=p.getProductId() %></p>
     						 	<p>판매자 ID : <%=sellerId %></p>
     						 	<hr>
     						 	<p class="MemoContent">각서 내용</p>
     						 	<p class="MemoContent">본인은 CShop에서 판매하는 모든 상품이 상표법등에 법적하자가 없는 정품임을 보장하며, 만일 위조상품(가품)임이 밝혀질 경우 민/형사상의 모든 책임을 지며 판매가격의 2배를 보상할 것을 약속합니다.</p>
     						 	<hr>
     						 	<p>※ 본 각서는 판매자가 상품을 판매하는 일로부터 적용됩니다.</p>
     						 	<p>※ 본 보장각서는 위조상품(가품)판매로 인한 분쟁발생시 민사적 해결에 그 이유가 있습니다.</p>
     						 	<p>※  타인명의를 도용하여 판매하는 경우에도 명의도용에 대한 법적책임까지 묻게 됩니다.</p>
     						 	<br><br>
     						 	<span><img src="/img/logo.jpg" ></span><span id="sellersign">작성자 : <%=sellerId %></span>
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
			<a href="productReviewView?id=<%=p.getProductId() %>&seller=<%=p.getProductSeller() %>&reqPage=1" style="color:#5F755A">상품리뷰(<%=reviewCount %>)</a> 
			<a href="productQnAView?id=<%=p.getProductId() %>&seller=<%=p.getProductSeller() %>&reqPage=1">상품문의(<%=qnaCount %>)</a>
		</div>
		
		<%------------------------------------------------------------------------ --%>
		
		<%--리뷰 --%>
		<div class="reviewBox">
			<%--리뷰 개수와 평균별점(나머지반올림) --%>
			<div class="reviewTotalBox">
				<span><%=reviewCount %> REVIEWS</span>
				<span class="star">
					<%--별점이 있으면 별점 표시 , 별점이 없으면 빈 별 --%>
					<%if(prList.size() == 0){ %>
						<%for(int i=0;i<5;i++){ %>
						<img src="/img/greenStar_off.png" style="width:50px; height: 50px;">
						<%} %>
					<%} else{%>
						<%for(int i=0;i<averageStar;i++){ %>
						<img src="/img/greenStar.png" style="width:50px; height: 50px;">
						<%} %>
					<%} %>
				</span>
			</div>
			<%--리뷰 목록 --%>
			<div class="reviewList">
				<%for(ProductReview pr : prList){ %>
					<%--댓글 레벨 1일때만 목록에 띄우기 --%>
					<%if(pr.getPrLevel() == 1){ %>
					<hr style="border: 0; height: 1px; background: #ccc; margin-top:10px; margin-bottom:10px">
					<div class="reviewInfo">
						<%--별점 --%>
						<p>
							<%for(int i=0;i<pr.getPrStar();i++){%>
								<img src="/img/greenStar.png"  style="width:20px; height: 20px; ">
							<%} %>
						</p>
						<%--아이디 (앞에 3글자만 보여주기) --%>
						<p>
							<%String writerString = pr.getPrWriter().substring(0,3);
							writerString = writerString.trim() + "****"; %>
							<%=writerString %>
						</p>
						<%--리뷰 날짜 --%>	
						<p><%=pr.getPrDate() %></p>
					</div>
					<%--리뷰 내용 --%>
					<div class="reviewContent">
						<span>
							<%--리뷰 내용이 30글자가 넘어가면 뒷 내용은 ...으로 표시 --%>
							<%if(pr.getPrContent().length() > 30) {%>
								<%String subString = pr.getPrContent().substring(0, 30);
								subString = subString.trim() + "..."; %>
								<%=subString %>
								<br><br><br>
								<p class="reviewMore"> 더보기</p>
							<%}else{ %>
								<%=pr.getPrContent() %>
								<br><br><br>
								<p class="reviewMore"> 더보기</p>
							<%} %>
						</span>
						<%--리뷰 사진 (있는경우에만 표시, 있으면 눌러서 modal로 사진보기 가능)  --%>
						<%if(pr.getPrFilepath() != null) {%>
							<span class="reviewImg">
							<img src="/upload/review/<%=pr.getPrFilepath() %>" style="width:130px; height: 130px;" data-toggle="modal" data-target="#myModal2">
							</span>
						<%} else {%>
							<span style="width:130px; height: 130px;"></span>
						<%} %>
					</div>
					<%if(pr.getPrFilepath() != null) {%>
					<div class="modal" id="myModal2">
						<div class="modal-dialog">
							<div class="modal-content">
	     						<div class="modal-body">
	     							<button type="button" class="close" data-dismiss="modal">&times;</button>
	     							<br><br>
	     							<img src="/upload/product/<%=pr.getPrFilepath() %>" style="width:570px; height: 650px;">
	     						</div>
							</div>
						</div>
					</div>
					<%} %>
					<%--더보기 눌렀을 시 --%>
					<div class="reviewMoreView" style="display:none">
						<div id="moreContent">
							<%=pr.getPrContent() %>
						</div>
						<br>
						<%--리뷰대댓글 작성 --%>
						<%if(m != null && m.getMemberNo() == p.getProductSeller()){ %>
						<form action="/productReview" class="recoment">
							<div class="recomentInput">
							<textarea name="prContent" class="form-control" style="width:1020px;float: left;resize: none; height: 100px; margin-right:20px;" ></textarea>
							<input type="hidden" name="productNo" value="<%=p.getProductNo()%>">
							<input type="hidden" name="prWriter" value="<%=sellerId%>">
							<input type="hidden" name="prLevel" value="2">
							<input type="hidden" name="prRef" value="<%=pr.getPrNo()%>">
							<input type="hidden" name="productId" value="<%=p.getProductId() %>">
							<input type="hidden" name="sellerNo" value="<%=p.getProductSeller() %>">
							<button type="submit" class="btn" id="recomentBtn">답글달기</button>
							</div>
						</form>
						<%} %>
						<br>
						<%--리뷰 대댓글 보이기 --%>
						<%for(ProductReview prr : prList2){ %>
							<%if(prr.getPrLevel() == 2 && pr.getPrNo() == prr.getPrRef()) {%>
							<hr style="overflow:visible">
							<div class="reComent" style="width:100%">
								<span><%=prr.getPrWriter() %>(판매자)</span> 
								<span>
									<p class="reRe"><%=prr.getPrContent() %></p>
									<textarea style="display:none; width:850px;float: left;resize: none; height: 100px;" class="form-control rereText" name="prContent"><%=prr.getPrContent() %></textarea>
									<button style="display:none;float: left; margin-right:10px " class="btn upFinish" id="btn1">완료</button>
									<button style="display:none;" class="btn upCancel" id="btn2">취소</button>
								</span>
								<%if(m != null && prr.getPrWriter().equals(m.getMemberId())) {%>
									<div>
										<a href="javascript:void(0)" class="updel upRe" onclick="modifyComment(this,'<%=prr.getPrNo()%>','<%=p.getProductId()%>','<%=p.getProductSeller()%>')">수정하기</a> 
										<a href="javascript:void(0)" class="updel delRe" onclick="deleteComment(this,'<%=prr.getPrNo()%>','<%=p.getProductId()%>','<%=p.getProductSeller()%>')">삭제하기</a>
									</div>
								<%} %>
							</div>
							<%} %>
						<%} %>
						<button class="btn btn-block closeMore">접기</button>
						
					</div>
					<%} %>
				<%} %>
				<hr style="border: 0; height: 1px; background: #ccc; margin-top:10px; margin-bottom:10px">
				<div id="reviewPageNavi" style="text-align: center; color:red " ><%=pageNavi %></div>
			</div>
		</div>
		
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
		
		//더보기 클릭한 리뷰 보여주기
		$(".reviewMore").click(function(){
			var index = $(".reviewMore").index(this);
			$(".closeMore").trigger("click");
			$(".reviewMoreView").eq(index).css("display","block");
			$(".reviewMore").eq(index).html("");
			$(".reviewContent").eq(index).css("display","none");
		});
		
		//더보기 클릭한 리뷰 접기
		$(".closeMore").click(function(){
			var index = $(".closeMore").index(this);
			$(".reviewMoreView").eq(index).css("display","none");
			$(".reviewMore").eq(index).html("더보기");
			$(".reviewContent").eq(index).css("display","block");
		})
		
		//리뷰 수정하기 버튼 눌렀을 때 
		function modifyComment(obj,prNo,productId,sellerNo){
			var index = $(".upRe").index(obj);
			$(".reRe").eq(index).css("display","none");
			$(".upRe").eq(index).css("display","none");
			$(".delRe").eq(index).css("display","none");
			$(".rereText").eq(index).css("display","block");
			$(".upFinish").eq(index).css("display","block");
			$(".upCancel").eq(index).css("display","block");
			$(".upFinish").attr("onclick","modifyComplete(this,'"+prNo+"','"+productId+"','"+sellerNo+"');");
			$(".upCancel").attr("onclick","modifyCancel(this);");
		}
		
		//리뷰 수정완료 버튼 눌렀을 때 
		function modifyComplete(obj,prNo,productId,sellerNo){
			var form = $("<form action='/updateReview' method='post'></form>");
			form.append($("<input type='text' name='prNo' value='"+prNo+"'>"));
			form.append($("<input type='text' name='productId' value='"+productId+"'>"));
			form.append($("<input type='text' name='sellerNo' value='"+sellerNo+"'>"));
			form.append($(obj).prev());
			$("body").append(form);
			form.submit();
			$(obj).css("display","none");
			$(obj).next().css("display","none");
		}
		
		//리뷰 수정취소 버튼 눌렀을 때
		function modifyCancel(obj){
			$(obj).hide();
			$(obj).prev().hide();
			$(obj).prev().prev().hide();
			$(obj).prev().prev().prev().show();
			$(obj).parent().next().children().show();
		}
		
		//리뷰 삭제버튼 눌렀을 때
		function deleteComment(obj,prNo,productId,sellerNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				location.href="/deleteReview?prNo="+prNo+"&productId="+productId+"&sellerNo="+sellerNo;
			}
		}
		$("#reviewPageNavi").click(function(){
			console.log("efef");
		})
		
		//답글달기 유효성검사
		$("#recomentBtn").click(function(){
			if($("[name=prContent]").val() == ""){
				alert("답글 내용을 입력해주세요.");
				return false;
			}
		})
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
		$("#noCart").click(function(){
			alert("구매자로 로그인 후 이용해주세요.");
		})

	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>