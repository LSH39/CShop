<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <h1 style="border-left:2px solid #5f755a;">회원정보수정</h1>  
          <br>   
          <div class="content">
          	<div>
          		<form action="/updateSeller" method="post">
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="memberId">아이디</label>
							<input type="text" id="memberId" name="memberId" class="form-control" value="${sessionScope.m.memberId }" readonly>
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="memberPW">비밀번호</label>
							<input type="password" id="memberPw" name="memberPw" class="form-control" value="${sessionScope.m.memberPassword }">
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="memberName">이름</label>
							<input type="text" id="memberName" name="memberName" class="form-control" value="${sessionScope.m.memberName }" readonly>
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="memberEmail">이메일</label>
							<input type="text" id="memberEmail" name="memberEmail" class="form-control" value="${sessionScope.m.memberEmail }" readonly>
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="phone">전화번호</label>
							<input type="text" id="phone" name="phone" class="form-control" value="${sessionScope.m.memberPhone }">
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="address">주소1</label>
							<input type="text" id="address" name="postcode1" class="form-control" value="${sessionScope.m.postcode1 }">
							<input type="text" id="address" name="addressRoad1" class="form-control" value="${sessionScope.m.addressRoad1 }">
							<input type="text" id="address" name="addressDetail1" class="form-control" value="${sessionScope.m.addressDetail1 }">
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="address">주소2</label>
							<input type="text" id="address" name="postcode2" class="form-control" value="${sessionScope.m.postcode2 }">
							<input type="text" id="address" name="addressRoad2" class="form-control" value="${sessionScope.m.addressRoad2 }">
							<input type="text" id="address" name="addressDetail2" class="form-control" value="${sessionScope.m.addressDetail2 }">
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset>
							<label class="control-label" for="enrollDate">가입일</label>
							<input type="text" id="enrollDate" name="enrollDate" class="form-control" placeholder="${sessionScope.m.enrollDate }" value="${sessionScope.m.enrollDate }" readonly>
						</fieldset>
					</div>
					<div class="form-group">
						<fieldset style="text-align:center;">
							<button type="submit" class="btn">정보수정</button>
							<a href="/deleteSellerFrm?" class="btn btn-danger">회원탈퇴</a>
						</fieldset>
					</div>
				</form>
          	</div>
          </div>
        </div>
      </div>
    </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	<script>
	
	</script>
</body>
</html>