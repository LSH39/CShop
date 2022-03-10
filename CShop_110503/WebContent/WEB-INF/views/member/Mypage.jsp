<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/mypage.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>정보 수정</h2>
		 </div>

		 <form action="/memberUpdate" method="post" style="padding-left: 200px; padding-top:50px">
		 <div class="form-group">
			<fieldset>
				<label class="control-label" for="memberId">아이디</label>
				<input type="text" id="memberId" name="memberId" class="mypagecontrol"
				value="${sessionScope.m.memberId}" readonly> <!-- redonly 고정 -->
			</fieldset>
		</div>	
		<div class="form-group">
			<fieldset>
				<label class="control-label" for="memberPw">비밀번호</label>
				<input type="password" id="memberPassword" name="memberPassword" class="mypagecontrol"
				value="${sessionScope.m.memberPassword}">
				</fieldset>
		</div>	
		<div class="form-group">
			<fieldset>
			 <label class="control-label" for="memberName">이름</label>
			 <input type="text" id="memberName" name="memberName" class="mypagecontrol"
			 value="${sessionScope.m.memberName}">	
			</fieldset>
		</div>
		<div class="form-group">
			<fieldset>
			 <label class="control-label" for="memberBirthday">생년월일</label>
			 <input type="text" id="memberName" name="memberBirthday" class="mypagecontrol" value="${m.memberBirthday}">	
			</fieldset>
		</div>				
		<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberPhone">전화번호</label>
					<input type="text" id="memberPhone" name="memberPhone" class="mypagecontrol" value="${sessionScope.m.memberPhone}">
				</fieldset>
		</div>
				<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberEmail">이메일</label>
					<input type="text" id="memberEmail" name="memberEmail" class="mypagecontrol" value="${sessionScope.m.memberEmail}">
				</fieldset>
		</div>
		<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberAddress">주소1</label>
					<input type="text" id="postcode1" name="postcode1" class="mypagecontrol2" value="${sessionScope.m.postcode1}">
					<button type="button" onclick="addrSearch1();" class="btnAdd">주소검색</button> 
					<input type="text" id="roadAddr1" name="roadAddr1" class="mypagecontrol" value="${sessionScope.m.addressRoad1}">
					<input type="text" id="detailAddr1" name="detailAddr1" class="mypagecontrol" value="${sessionScope.m.addressDetail1}">
				</fieldset>
		</div>
		<div class="form-group">
				<fieldset>
					<label class="control-label" for="memberAddress">주소2</label>
					<input type="text" id="postcode2" name="postcode2" class="mypagecontrol2" value="${sessionScope.m.postcode2}">
					<button type="button" onclick="addrSearch2();" class="btnAdd">주소검색</button> 
					<input type="text" id="roadAddr2" name="roadAddr2" class="mypagecontrol" value="${sessionScope.m.addressRoad2}">
					<input type="text" id="detailAddr2" name="detailAddr2" class="mypagecontrol" value="${sessionScope.m.addressDetail2}">
				</fieldset>
		</div>				
		<div class="form-group">
				<fieldset>
					<button type="submit" class="btnAdd">정보수정</button> 
					<a href="/memberDelete?memberNo=${sessionScope.m.memberNo}" class="btnAdd2">회원탈퇴</a>
				</fieldset>
		</div>
	</form>
	</div>
</div>
	<script>
	function addrSearch1(){
		 new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            $("#postcode1").val(data.zonecode); //우편번호
		            $("#roadAddr1").val(data.roadAddress); //도로명주소
		            $("#detailAddr1").focus(); //상세주소
		        }
		 }).open();
	}
	function addrSearch2(){
		 new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            $("#postcode2").val(data.zonecode); //우편번호
		            $("#roadAddr2").val(data.roadAddress); //도로명주소
		            $("#detailAddr2").focus(); //상세주소
		        }
		 }).open();
	}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


</body>
</html>