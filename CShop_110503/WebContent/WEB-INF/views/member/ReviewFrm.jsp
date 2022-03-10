<%@page import="product.model.vo.ProductReview" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>리뷰 작성</h2>
		 </div>
			<!-- 파일을 전송하는 form 고정속성~ -->
			<form action="/review" method="post" enctype="multipart/form-data">
			<fieldset>
				<table class="table" style="width:100%;">
					<tr class="table-active">
						
						<th>작성자</th>
							<td>
							<input type="hidden" name="prWriter" value="${sessionScope.m.memberId }">
							${sessionScope.m.memberId }
							<input type="hidden" name="productNo" value="${productNo }">
						</td>
					</tr>
					<tr class="table-active">
						<th>별 점 </th>
						 <td>
							<input type="hidden" name="star" value="0">
							<div class="star-wrap">
								<img class="star" src="/img/star-off.png">
								<img class="star" src="/img/star-off.png">
								<img class="star" src="/img/star-off.png">
								<img class="star" src="/img/star-off.png">
								<img class="star" src="/img/star-off.png">
							</div>
						</td>
						<th>첨부파일</th>
						<td style="text-align:left">
							<!-- 스프링전이니까 하나만 리뷰하자ㅎ --> <!-- 업로드 확장자 제한해주기!! 이미지만됩니다 IE8(은 js에서 씀 필요없음)-->
							<input type="file" name="img" onchange="loadImg(this);" accept=".jpg,.jpeg,.png,.gif">
						</td>
					</tr>
					<tr class="table-active">
						<th>리뷰사진</th>
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
						<button type="submit" class="btn linkbtn">등록하기</button>
						</td>
					</tr>
				</table>
			</fieldset>
			</form>
			</div>
		</div>
	<script>
		$(function() {
			$("[name=reviewContent]").summernote({
				height:400,
				lang : "ko-KR",
				callbacks:{
					onImgeUpload : function(files){
						uploadImage(file[0],this);
					}
				}
			});
		});
		function uploadImage(file,editor){
			//form와 같은 효과를 내는 객체 생성
			var form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "uploadImage",
				type : "post",
				data : form, //넘겨줄값
				processData : false, //모두를 스트링으로 만드는거
				contentType : false, //
				success : function(data){
					//결과로 받은 업로드 경로를 이용해서 에디터에 이미지 추가
					$(editor).summernote("insertImage",data);
				}
			});
			//ajax로 파일전송시 추가할거~
			//processData : 기본값이 true {key1:value1,key2:value2,key3:value3}넘겨주고 받을때 string으로 받았어여ㅠㅠ
			//					-> 파일전송시 String 이 아닌 파일형태로 전송하기위해 기본설정을 제거함
			//review sevlet star은 스트링으로 받아서 java에서 숫자로 바꿔야함
			//contentType : 기본값 " applicate ion/x-www-form-urlencoded;charset=UTF-8"
			//					-> 파일전송시 enctype="multipart/form-data"로 변환하기위해 기본값제거
			
		}
		function loadImg(obj){
			var files = obj.files; //input type=file 에서 선택한 파일을 배열로 가져옴
			console.log(files);
			if(files.length !=0) {//첨부파일이 있는경우
				var reader = new FileReader(); //파일에 대한 정보를 읽어오는 객체
				reader.readAsDataURL(files[0]); //파일경로를 읽어옴
				//경로를 다읽어오면 실행할 함수 설정
				reader.onload= function(e){
					$("#img-view").attr("src",e.target.result); //읽어온경로를 img태그의 src속성에 설정
				}
			}else{ //첨부파일이 없는 경우
				$("#img-view").attr("src","");
			}
		}
		$(".star-wrap>img").mouseover(function(){
            //$("선택자").index(this); -> 선택자 중 this가 몇번째 요소인지 리턴(시작숫자 0)
            var idx = $(".star-wrap>img").index(this);                
            $(".star-wrap>img").each(function(index,item){
                if(index<=idx){
                    $(item).attr("src","img/star-on.png");
                }else{
                    $(item).attr("src","img/star-off.png");
                }
            });
            $("[name=star]").val(idx+1);
        });
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<style>
.linkbtn {
	margin:auto;
    display: block;
    width: 100px;
    height: 34px;
    border: 1px solid #ADC2A9;
    background-color: #fff;
    font-size: 12px;
    color: #ADC2A9;
    line-height: 32px;
    text-align: center;
    cursor: pointer;
	}
.linkbtn:hover{
	text-decoration: none;
	background-color: #ADC2A9;
    font-size: 12px;
    color: #fff;
    line-height: 32px;
}
.star {
	width: 16px;
}
	</style>
</html>