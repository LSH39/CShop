<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	 
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
	
	 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link rel="stylesheet" href="/css/joinFrm.css">
<title>Document</title>
</head>

<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
    <div class="container">

        <div class="joinFrm_title">
            <br><br><br>
            <h2><strong style="color: rgb(95, 117, 90);"> 커넥츠</strong> 온라인 스토어 회원만의 <strong style="color: rgb(95, 117, 90);">특별한 혜택</strong></h2>
        </div>
        <div class="joinFrm_img">
            <img src="/img/bg_benefit.png" alt="">
            <div id="joinFrm_imgtext">
                <button onclick = "location.href = '/noticeView?noticeNo=162'">회원등급별 혜택 더 보러가기</button>
            </div>
        </div>
        <br>
        <div class="joinbtn_wrap">
            <div class="joinbtn"> <a style="background-color: #fee500; color: black;" href="javascript:kakaoLogin()"><b>카카오 계정으로 신규가입</b></a>
            </div>
            <div class="joinbtn "><a style="background-color: rgb(95, 117, 90); color: white;" href="/bjoinFrm">만 14세 이상 회원가입</a></div>
            <div class="joinbtn "><a style="background-color: rgb(95, 117, 90); color: white;" href="/checkSjoin">판매자 회원가입</a></div>
        </div>
    </div>
    
      <script>
		window.Kakao.init("0417eb3b0836c5c51ed345afebe6be0b");
		function kakaoLogin() {
			Kakao.Auth.login({
				success : function(data) {
					console.log(data);
					window.Kakao.API.request({
						url : '/v2/user/me',
						success : function(data1) {
							console.log(data1.id);
							var id = data1.id;

							$.ajax({
								url : "/ajaxLogin",
								data : {
									id : data1.id
								},
								success : function(result) {
									if (result == 0) {
										alert("추가 정보 입력을 위한 페이지로 이동합니다");
										
										(function kjoin() {
											//form 태그 생성
											var form = $("<form action='/kjoinFrm' method='post'></form>");
											//form 태그 추가
											form.append($("<input type='hidden' name='id' value='"+data1.id+"'>"));
											//전송할 form태그를 현재 페이지에 추가
											$("body").append(form);
											//form태그 전송
											form.submit();
										})();
									
										
									} else {
										alert("이미 계정이 존재합니다.");
										location.href="/";
									}
								}
							})

						}

					});
				}
			});
		}
        
    </script>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
    
</body>

</html>