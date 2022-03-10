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
					<h1 style="font-size:24px !important">1:1문의사항 수정</h1>
				</div>
			</div>
			<!-- 파일을 전송하는 form은 method="post" enctype="multipart/form-data" 고정속성 -->
			<form action="/questionUpdate" method="post" enctype="multipart/form-data">
				<!-- 넘겨줄 번호 -->
				<input type="hidden" name="contactNo" value="${c.contactNo }">
				<fieldset>
					<table class="table" style="width:100%;">
						<tr class="table-active">
							<th>제목</th>
							<td colspan="3">
								<input type="text" name="contactTitle" class="form-control" value="${c.contactTitle}">
							</td>
						</tr>
						<tr class="table-active">
							<th>작성자</th>
							<td>
							 	<!-- 보이는이름 -->
							 	${m.memberId }
							</td>
							<th>첨부파일</th>
							<td style="text-align:left;">
								<!-- 삭제을 했나 안했나 구분하는 status 안했으면 value는 1 -->
								<input type="hidden" name="status" value="1">
								<c:choose>
									<c:when test="${c.filename != null }">
										<!-- <input type="file"> 에는 value를 설정할 수 없음 -->
										<img src="/img/file.png" width="16px" class="delFile">
										<span class="delFile">${c.filename }</span>
										<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">
										삭제
										</button>
										<!-- 삭제시 다시 신규파일을 등록할 input -->
										<input type="file" name="upfile" style="display:none;">
										<!-- 기존파일 유지할시 파일 이름 저장할 공간 -->
										<input type="hidden" name="oldFilename" value="${c.filename }">
										<input type="hidden" name="oldFilepath" value="${c.filepath }">
									</c:when>
									<c:otherwise>
										<input type="file" name="upfile">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr class="table-active">
							<th>내용</th>
							<td colspan="3" style="text-align:left;">
								<!--br은 DB에서 가져올때 br을 처리할려는거고 얘는 textarea라 원본데이터 그대로  -->
								<textarea id="contactContent" name="contactContent" class="form-control">${c.contactContent }</textarea>
							</td>
						</tr>
						<tr class="table-active">
							<th colspan="4">
								<button type="submit" class="btn btn-success btn-block">1:1문의사항수정</button> 
							</th>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
	<script>
		//삭제시 동작할 함수
		$("#delBtn").click(function(){
			//삭제버튼가림
			$(".delFile").hide();
			//첨부파일등록 보여줌
			$(this).next().show();
			//삭제했나 안했나 하는 구분인 status갑을 2로 바꿈
			$("[name=status]").val(2);
		});
		$(function(){
			$("#contactContent").summernote({
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