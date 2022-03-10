<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/default.css">
<link rel="stylesheet" href="/css/login.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<title>Semi project</title>

</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
	<form action="/login" method="post" name="loginFrm">
		<div class="content">
			<div class="wrap2">


				<div class="login">

					<div class="title-text">

						<H2>고객님의 방문을 환영합니다.</H2>
						<br> <br> <span style="font-size: 15px;">로그인을 하시면
							다양한 회원전용 서비스를 이용하실 수 있습니다.</span>

					</div>
					<br> <br> <br>
					<div class="login_id">
						<input type="text" name="memberId" placeholder="아이디를 입력해주세요">
					</div>
					<div class="login_pw">
						<input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요">
					</div>
					<div class="etc_warp">
						<div class="login_etc">

							<div>아직 회원이 아니신가요?</div>
							<div class="join">
								<a href="joinFrm">회원가입</a>
							</div>
						</div>
						<div class="login_etc">
							<div>비밀번호를 잊으셨나요?</div>
							<div class="forgot_pw">
								<a href="/accountSearch">ID/PASSWORD 찾기</a>
							</div>
						</div>
					</div>
					<div class="submit">
						<input type="submit" value="로그인">

					</div>
					<div class="submit">
						
							<a href='javascript:void(0);' onclick="kakaoLogin();"><img width="100%" height="69.59px" src="/img/kakao_login_large_wide.png" alt=""></a>

					</div>

				</div>

			</div>

		</div>
	</form>
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
										alert("추가회원가입페이지로 이동");
										
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
										
										location.href="/";
									}
								}
							})

						}

					});
				}
			});
		}
		
		/*
		function kakaoLogin() {
		    window.Kakao.Auth.login({
		        scope: 'profile_nickname, account_email, gender',
		        success: function(authObj) {
		            console.log(authObj);
		            window.Kakao.API.request({
		                url: '/v2/user/me',
		                success: res => {
		                    const kakao_account = res.kakao_account;
		                    console.log(kakao_account)
		                }
		            });
		        }
		    });
		}
		 */
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
</body>