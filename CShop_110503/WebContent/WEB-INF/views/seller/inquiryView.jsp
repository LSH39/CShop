<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.container{
	margin-top: 100px;
	margin-bottom: 50px;	
}
ul {
 	padding-left: 0;
	list-style: none;
}
.btn{
	background-color:#5F755A; 
	color:#ffffff;
}
.btn:hover{
	color: #fff;
	background-color: #ADC2A9;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
    	<div class="row">
        	<div class="col-sm-4 col-md-3">
          		<jsp:include page="/WEB-INF/views/common/leftNavi.jsp"/>
        	</div>
        <div class="col-sm-8 col-md-9">
          <h1 style="border-left:2px solid #5f755a;">상품 문의</h1>
          <br>
          <div class="content">
        	<fieldset class="well well-lg" style="background-color:white;">
			<table class="table" id="inquiryView" style="width:100%;">
				<tr>
					<th>제목</th>
					<th>${pq.pqTitle }</th>
					<th>작성일</th>
					<th>${pq.pqDate }</th>
				</tr>
				<tr>
					<th style="width:15%;">문의상품</th>
					<th style="width:35%;">${pq.productName }</th>
					<th>답변일</th>
					<th>${pa.pqDate }</th>
					
				</tr>
				<tr>
					<th style="height:100px;">내용</th>
					<th colspan="3">
						${pq.pqContent }
					</th>
				</tr>
				<tr>
					<th style="height:100px;">답변</th>
					<th colspan="3">
						${pa.pqContent }
					</th>
				</tr>
			</table>
			</fieldset>
			<br>
			<div class="well well-lg" style="background-color:white;"">
				<h4>답변 작성</h4>
				<form action="/insertAnswer" method="post">
					<ul>
						<li>
							<input type="hidden" name="pqWriter" value="15">
							<input type="hidden" name="productNo" value="${pq.productNo }">
							<input type="hidden" name="pqRef" value="${pq.pqNo }">
							<input type="hidden" name="pqStatus" value="${pq.pqStatus }">
							<textarea name="pqContent" class="form-control"></textarea>
						</li>
						<li>
							<c:if test="${pq.pqStatus eq 1 }">
								<button type="submit" class="btn btn-block">등록</button>
							</c:if>
							<c:if test="${pq.pqStatus eq 2 }">
								<button type="submit" class="btn btn-block">수정</button>
							</c:if>
						</li>
					</ul>
				</form>
			</div>
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>