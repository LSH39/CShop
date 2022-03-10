<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="css/noticeWriteFrm.css?after">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container" style="margin-top:70px">
		<div class="notice-left col-md-3">
			<h1>고객센터</h1><br>
			<h3><a href="/notice?reqPage=1">공지사항</a></h3><br>
			<h3><a href="/question?reqPage=1">1:1문의</a></h3>
		</div>
		<div class="notice-right col-md-9">
			<div class="notice-title">
				<div class="notice-title-left col-md-10">
					<h1 style="font-size:24px !important">공지사항 작성</h1>
				</div>
			</div>
			<!-- 파일을 전송하는 form은 method="post" enctype="multipart/form-data" 고정속성 -->
			<form action="/noticeWrite" method="post" enctype="multipart/form-data">
				<fieldset>
					<table class="table" style="width:100%;">
						<tr>
							<th>제목</th>
							<td colspan="3">
								<input type="text" name="noticeTitle" class="form-control">
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td style="text-align:left;">
								<input type="file" name="upfile">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3" style="text-align:left;">
								<textarea id="noticeContent" name="noticeContent" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<th colspan="4">
								<button type="submit" class="btn btn-success btn-block">공지사항등록</button> 
							</th>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		
	</div>
	<script>
		$(function(){
			$("#noticeContent").summernote({
				height:400,
				lang:"ko-KR",
				callbacks:{
					//우리가 직접 작업할꺼라는선언 + 이미지 넣는것도 구현해야됌
					onImageUpload:function(files){ //files에 업로드한 이미지가 들어가잇음(배열구조)
							uploadImage(files[0],this); //첫번째이미지, 에디터
					}
				}
			});
		});
		function uploadImage(file,editor){
			//form과 같은 효과를 내는 객체 생성
			var form = new FormData();
			form.append("file",file);//file이란이름으로 file을 보냄
			$.ajax({
				url : "/uploadImage",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					//결과로받은 업로드 경로를 이용해서 에디터에 이미지 추가
					$(editor).summernote("insertImage",data);
				}
			});
			//processData : 기본값이 true {key:value} 즉 기본설정으론 String 으로 전송한다
			//				->파일전송 시 String이 아닌 파일형태로 전송하기위해 기본설정 제거
			//contentType : 기본값 "applicat ion/x-www-form-urlencodedlcharset=utf-8"
			//				->파일전송 시 이 값이 아닌 multipart/form-data로 변경하기위해 기본설정 제거
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>