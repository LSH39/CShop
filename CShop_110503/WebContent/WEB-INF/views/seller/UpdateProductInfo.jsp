<%@page import="java.util.ArrayList"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Product p = (Product)request.getAttribute("p");
    %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/product.css?ver=3">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top:70px">
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
		</div>
			<div class="c-right">
		 <div id="title">
		    <h2>상품 수정</h2>
		    <form action="/updateProductInfo" method="post" enctype="multipart/form-data">
			<table class="table" style="margin-top:70px">
				<tr>
					<th>카테고리</th>
					<td>
						<select class="form-control" id="productCategory1" name="productCategory1">
							<option value="0">대분류 선택</option>
							<option value="1">남성</option>
							<option value="2">여성</option>
							<option value="3">키즈</option>
						</select>
					</td>
					<td>
						<select class="form-control" id="productCategory2" name="productCategory2">
							<option value="0">소분류 선택</option>
							<option value="1">아우터</option>
							<option value="2">상의</option>
							<option value="3">하의</option>
							<option value="4">언더웨어</option>
							<option value="5">스포츠웨어</option>
							<option value="6">ETC</option>
						</select>
					</td>
				</tr>
				<tr>
				<th>브랜드</th>
				<td colspan="2">
					<input type="text" id="productBrand" name="productBrand" class="form-control" value=${p.productBrand }> 
				</td>
				</tr>
				<tr>
				<th>브랜드 구분</th>
				<td colspan="2">
					<input type="radio" id="overseas" name="productImports" value="1"><label for="overseas">해외브랜드</label>
					<input type="radio" id="domestic" name="productImports" value="2"><label for="domestic">국내브랜드</label>
				</td>
				</tr>
				<tr>
				<th>판매가</th>
				<td colspan="2">
					<input type="text" id="productPrice" name="productPrice" class="form-control" style="width:200px; float:left" value=${price }>원
				</td>
				</tr>
				<tr>
				<th>사이즈</th>
				<td colspan="2">
					<input type="text" id="productSize" name="productSize" class="form-control" placeholder="','로 구분해주세요." style="width:200px" value=${pSize }>
				</td>
				</tr>
				<tr>
				<th>색상</th>
				<td colspan="2">
					<input type="text" id="productColor" name="productColor" class="form-control" placeholder="','로 구분해주세요." style="width:200px"  value=${pColor }>
				</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td colspan="2">
						<input type="text" id="productName" name="productName" class="form-control" value="<%=p.getProductName()%>">  
					</td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td colspan="2" id="productContentTd">
						<textarea id="productContent" name="productContent" class="form-control" style="height:600px">${p.productContent }</textarea>
					</td>
				</tr>
				<tr>
					<th>썸네일 파일</th>					
					<td colspan="2">
						<input type="hidden" name="status" value="1">
						<%--썸네일파일을 수정할 경우 if문 --%>
						<c:choose>
							<c:when test="${not empty p.productImage }">
								<span class="delFile">${p.productImage }</span>
								<button type="button" id="delBtn" class="btn btn-sm delFile">삭제</button>
								<input type="file" name="productImage" style="display:none" accept=".png,.jpg,.jpeg">
								<input type="hidden" name="oldFilepath" value=${p.productImage }> 
							</c:when>
							<c:otherwise>
								<input type="file" id="productImage" name="productImage" accept=".png,.jpg,.jpeg" style="float:left" >
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>상품 인증서</th>
					<td colspan="2">
						<c:choose>
							<c:when test="${p.productStatus == 2 }">
								<span id="sival">인증 완료 되었습니다.</span>		
							</c:when>
							<c:otherwise>
								<span id="sival">인증 대기중입니다.</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<button type="button" id="frmCheck" class="btn btn-block">수량수정</button>
					</td>
				</tr>
			</table>
			<%--나중에 session으로 받아서 memberNo 추가로 보내주기 --%>
			<input type="hidden" name="memberNo" value=${sessionScope.m.memberNo }>
			<input type="hidden" name="productId" value=${p.productId }>
		</form>	
		 </div>
		</div>	
	</div>
</body>
<script>
		//summerNote 사용
		$(function(){
			$("#productContent").summernote({
				height : 400,
				lang  : "ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});			
		})	
		//summerNote 이미지 업로드
		function uploadImage(file,editor){
			var form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImage",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					$(editor).summernote("insertImage",data);
				}
			});
		}
		
		//유효성검사
		$("#frmCheck").click(function(){
			if($("#productCategory1").val() == 0){
				alert("대분류를 선택해주세요.");
				return;
			}
			if($("#productCategory2").val() == 0){
				alert("소분류를 선택해주세요.");
				return;
			}
			if($("#productBrand").val() == ""){
				alert("브랜드를 입력해주세요.");
				return;
			}
			var radioCheck = $('input[name=productImports]').is(":checked");
			if(!radioCheck){
				alert("브랜드구분을 체크해주세요.");
				return;
			}
			if($("#productPrice").val() == ""){
				alert("판매가를 입력해주세요.");
				return;
			}
			var numCheck = /^[0-9]*$/;
			if(!numCheck.test($("#productPrice").val())){
				alert("판매가는 숫자만 입력가능합니다.");
				return;
			}
			if($("#productSize").val() == ""){
				alert("사이즈를 입력해주세요.");
				return;
			}
			if($("#productColor").val() == ""){
				alert("색상을 입력해주세요.");
				return;
			}
			if($("#productName").val() == ""){
				alert("상품명을 입력해주세요.");
				return;
			}
			if($("#productContent").val() == ""){
				alert("상품설명을 입력해주세요.");
				return;
			}
			if($("#productImage").val() == ""){
				alert("썸네일 파일을 첨부해주세요.");
				return;
			}
			if($("#productFile").val() == ""){
				alert("상품인증서를 첨부해주세요.");
				return;
			}
			$("form").submit();
		})
		//썸네일 삭제 눌렀을 때
		$("#delBtn").click(function(){
			$(".delFile").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		})
		//선택한 카테고리 값 불러오기
		$(function(){
			function selectCate(){
				//번 카테고리
				if(<%=p.getProductCategory1()%>  == 1){
					$("#productCategory1").val("1").prop("selected", true);
				}else if(<%=p.getProductCategory1()%>  == 2){
					$("#productCategory1").val("2").prop("selected", true);
				}else if(<%=p.getProductCategory1()%>  == 3){
					$("#productCategory1").val("3").prop("selected", true);
				}
				
				//2번 카테고리
				if(<%=p.getProductCategory2()%>  == 1){
					$("#productCategory2").val("1").prop("selected", true);
				}else if(<%=p.getProductCategory2()%>  == 2){
					$("#productCategory2").val("2").prop("selected", true);
				}else if(<%=p.getProductCategory2()%>  == 3){
					$("#productCategory2").val("3").prop("selected", true);
				}else if(<%=p.getProductCategory2()%>  == 4){
					$("#productCategory2").val("4").prop("selected", true);
				}else if(<%=p.getProductCategory2()%>  == 5){
					$("#productCategory2").val("5").prop("selected", true);
				}else if(<%=p.getProductCategory2()%>  == 6){
					$("#productCategory2").val("6").prop("selected", true);
				}
				
				if(<%=p.getProductImports()%> == 1){
					$('#overseas').prop('checked', true);
				}else if(<%=p.getProductImports()%> == 2){
					$('#domestic').prop('checked', true);
				}
				
			}	
			selectCate();
		})
		
		
	</script>
</html>