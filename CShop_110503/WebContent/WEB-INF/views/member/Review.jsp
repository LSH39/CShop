<%@page import="product.model.vo.ProductReview" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<%ProductReview pr =(ProductReview)session.getAttribute("pr");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
				 <div id="title">
		        	<h2>리뷰 작성</h2>
		 		</div>
			<!-- 파일을 전송하는 form 고정속성~ -->
			<form action="/prreview" method="post" enctype="multipart/form-data">
			<fieldset>
				<table class="table" style="width:100%;">
					<tr class="table-active">
						<th>날짜</th>
							<td>
							<input type="hidden" name="prDate" value="<%=pr.getPrDate()%>">
							<%=pr.getPrDate()%>
						</td>
						<th>작성자</th>
							<td>
							<!-- 수정해줘야해용! -->
							<input type="hidden" name="prWriter" value="<%=pr.getPrWriter()%>">
							<%=pr.getPrWriter()%>
						</td>
					</tr>
					<tr class="table-active">
						<th>별 점 </th>
						 <td>
							<input type="hidden" name="prStar" value="<%=session.getAttribute("pr_star")%>">
							<%=session.getAttribute("pr_star")%>
						</td>
						<th>첨부파일</th>
						<td style="text-align:left">
							<!-- 스프링전이니까 하나만 리뷰하자ㅎ --> <!-- 업로드 확장자 제한해주기!! 이미지만됩니다 IE8(은 js에서 씀 필요없음)-->
							<input type="file" name="img" onchange="LoadRimg(this);" accept=".jpg,.jpeg,.png,.gif">
						</td>
					</tr>
					<tr class="table-active">
						<th>이미지</th>
						<td colspan="3">
							<div class="img-viewer">
								<img id="img-view" width="500px">
							</div>
					</tr>
					<tr class="table-active">
						<th>내용</th>
						<td colspan="3">
						<!-- 여러줄입력받아야해서 textarea -->
						<textarea name="reviewContent" class="form-control"></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<td colspan="4">
						<button type="submit" class="btn btn-danger btn-block">등록하기</button>
						</td>
					</tr>
				</table>
			</fieldset>
			</form>
			</div>
		</div>
	<script>
		function loadImg(obj){
			var files = obj.files; //input type=file 에서 선택한 파일을 배열로 가져옴
			console.log(files);
			if(files.length !=0) {//첨부파일이 있는경우
				var reader = new FileReader(); //파일에 대한 정보를 읽어오는 객체
				reader.readAsDataURL(files[0]); //파일경로를 읽어옴
				//경로를 다읽어오면 실행할 함수 설정
				reader.onload= function(e){
					$("#rimg-view").attr("src",e.target.result); //읽어온경로를 img태그의 src속성에 설정
				}
			}else{ //첨부파일이 없는 경우
				$("#rimg-view").attr("src","");
			}
		}
		
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>			
</body>
</html>