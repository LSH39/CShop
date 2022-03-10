<%@page import="product.model.vo.ProductOption"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<ProductOption> poList = (ArrayList<ProductOption>)request.getAttribute("poList");
    	ArrayList<String> color = (ArrayList<String>)request.getAttribute("color");
    	ArrayList<String> size = (ArrayList<String>)request.getAttribute("size");
    	int memberNo = (Integer)request.getAttribute("memberNo");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/product.css?ver=4">
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
		<form action="/updateProductCount" method="post" >
		<table class="table countTb">
		<tr>
			<th>판매 수량</th>
			<td>
			<%for(int i=0;i<color.size();i++){ %>
				<%for(int j=0;j<size.size();j++){ %>
					<p><%=color.get(i) %> / <%=size.get(j) %> : <input type="text" class="form-control" name="productCount" style="width:500px;display:inline-block;"> 개</p>
				<%} %>
			<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" id="frmCheck" class="btn btn-block">수정하기</button>
			</td>
		</tr>
		</table>
			<%for(int i=0;i<poList.size();i++) {%>
				<input type="hidden" name="optionNo" value=<%=poList.get(i).getOptionNo() %>>
			<%} %>
			<input type="hidden" name="productId" value=<%=poList.get(0).getProductId() %>>
			<input type="hidden" name="memberNo" value=<%=memberNo %>>
			<input type="hidden" name="poList" value=<%=poList %>>
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
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>