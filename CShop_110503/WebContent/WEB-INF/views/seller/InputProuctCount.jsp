<%@page import="product.model.vo.ProductOption"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/product.css?ver=3">
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
		</div>
		<div class="c-right">
			<div id="title">
			<h2>상품 등록</h2>
			<form action="/inputProductCount" method="post" >
			<table class="table countTb">
			<tr>
				<th>판매 수량</th>
				<td>
				<%--색 / 사이즈 를 띄우고 개수를 입력받기 위한  for문 --%>
				<c:forEach items="${color }" var="pc">
					<c:forEach items="${size }" var="ps">
						<p>${pc } / ${ps } : <input type="text" class="form-control" name="productCount" style="width:500px;display:inline-block;"> 개</p>
					</c:forEach>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<button type="button" id="frmCheck" class="btn" style="width:48%;">상품등록</button>
				<button type="button" id="cancelBtn" class="btn" style="width:48%;">등록취소</button>
				</td>
			</tr>
			</table>
				<%--옵션번호를 보내기 위한 for문 --%>
				<c:forEach items="${poList }" var="poList" varStatus="i">
					<input type="hidden" name="optionNo" value=${poList.optionNo }>
				</c:forEach>
				<input type="hidden" name="productId" value=${poList[0].productId }>
				<input type="hidden" name="memberNo" value=${memberNo }>
				<input type="hidden" name="btnnum">
			</form>
			</div>
		</div>
	</div>
	<script>
	//유효성검사
	$("#frmCheck").click(function() {
		var productCount = $("input[name=productCount]");
		var countLength = $("input[name=productCount]").length;
		var numCheck = /^[0-9]*$/;
		for (var i = 0; i < countLength; i++) {
			if (productCount.eq(i).val() == "") {
				alert("수량을 모두 입력해주세요");
				return false;
			}
		}
		for (var i = 0; i < countLength; i++) {
			if(!numCheck.test(productCount.eq(i).val())){
				alert("숫자만 입력 가능합니다.");
				return false;
			}
		}
		$("[name=btnnum]").val(1);
		$("form").submit();
	})
	//등록취소버튼 눌렀들 때
	$("#cancelBtn").click(function(){
		$("[name=btnnum]").val(2);
		$("form").submit();
	})
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>