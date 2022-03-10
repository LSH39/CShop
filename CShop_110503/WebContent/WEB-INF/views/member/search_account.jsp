<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko"></html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="/css/search_account.css">
    <link rel="stylesheet" href="/css/accountmodal.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
 
    <title>Semi project</title>
</head>

<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<div class="container" style="margin-top=300px;">
   
    <form action="/idSearch" method="post" id="modal1">
        <div id='content' class="modal_window">
            <div class="modal_window_text">
                <div>
                    <input type='button' value='X' class="close" id='btnClose1' />
                </div>
                <div>
                    <h3>회원가입시 사용한 EMAIL을 입력해주세요</h3>
                </div>
                <div class="input_wrap">
                    <div>
                    </div>
                    <div id="search_input">
                      <span style="font-size:20px;">EMAIL : </span>   <input type='text' name="email" id="email" class="search_input" /> <button type="button" style="margin-left:10px;" class="emailcheckbtn" onclick="sendMail();">인증메일전송</button>
                    </div>
                    <div id="auth">
                    <input type="text" id="authCode" style="width:200px;" placeholder="인증코드입력">	<button type="button"  id="authBtn">인증하기</button><span id="timeZone"></span> <span id="authMsg"></span>
                    <input type="hidden" id="authCodeCheck">
				</div>
                </div>
            </div>
            <div class="modal_window_btn">
                <div>
                    <button onclick="return idsubmit();" id='btnCheck1' class="modal_search_btn">조회</button>
                </div>
            </div>
        </div>
    </form>

    <form action="/pwSearch" method="post" id="modal2">
        <div id='content' class="modal_window">
            <div class="modal_window_text">
                <div>
                    <input type='button' value='X' class="close" id='btnClose2' />
                </div>
                <div>
                    <h3>회원가입시 사용한 E-MAIL&ID를 입력하세요</h3>
                </div>
                <div>
                   <span style="font-size:20px;">　　ID : </span> <input type='text' id="id2" name="id" class="search_input" />　　　　　　　　　
                </div>
                <div>
                   <span style="font-size:20px;">EMAIL : </span> <input type='text' id="email2" name="email" class="search_input" />　　<button type="button"  class="emailcheckbtn" onclick="sendMail2();">인증메일전송</button>
                </div>
                <div id="auth2">
                    <input type="text" id="authCode2" placeholder="인증코드입력"> <button type="button" id="authBtn2">인증하기</button><span id="timeZone2"></span> <span id="authMsg2"></span>　　
                    <input type="hidden" id="authCodeCheck2" value="0">
				</div>
            </div>
          
            <div class="modal_window_btn">
            <div>
                <button onclick="return pwSubmit();" id='btnCheck2' class="modal_search_btn">조회</button>
            </div>
            </div>
            </div>
    </form>


    <div class="content">
        <div class="wrap2">
            <div class="text">
                <h2><strong>회원 아이디/비밀번호</strong>기억나지 않으세요?</h2>

                <p>임시 비밀번호 발급을 위한 인증방법을 선택해주세요.</p>
                <hr>
                <br><br>
            </div>

            <div class="search">
                <div class="info-image" style="text-align: center;">
                    <p class="image-back"><img src="/img/icon-member-nomal.svg"></p>
                </div>
                <br>
                <div>
                    <div class="text">
                        <h3>일반 판매자/회원님의 계정정보를 알려드립니다.</h3>
                        아이디와 이메일 입력 후 임시 비밀번호를 조회 하실 수 있습니다.

                    </div>

                    <br><br>
                    <div class="btn_div">
                        <button class="search_btn" value='open' id='btnOpen1'>E-MAIL로 ID 찾기</button>
                    </div>
                    <br>
                    <div class="btn_div">
                        <button class="search_btn" value='open' id='btnOpen2'>E-MAIL&ID로 비밀번호 찾기</button>
                    </div>
                </div>

            </div>


        </div>
        </div>
        
        <script>
            var btnOpen1 = document.getElementById('btnOpen1');
            var btnOpen2 = document.getElementById('btnOpen2');
            var btnCheck1 = document.getElementById('btnCheck1');
            var btnCheck2 = document.getElementById('btnCheck2');
            var btnClose1 = document.getElementById('btnClose1');
            var btnClose2 = document.getElementById('btnClose2');

            // idsearch modal 창을 감춤
            var closeRtn1 = function() {
                    var modal1 = document.getElementById('modal1');
                    modal1.style.display = 'none';
                }
                // idsearch modal 창을 보여줌
            btnOpen1.onclick = function() {
                var modal1 = document.getElementById('modal1');
                modal1.style.display = 'block';
            }

           /*  btnCheck1.onclick = closeRtn1; */
            btnClose1.onclick = closeRtn1;
            btnClose2.onclick = closeRtn2;
            // pwsearch modal 창을 감춤
            var closeRtn2 = function() {
                var modal2 = document.getElementById('modal2');
               
                 modal2.style.display = 'none'; 
            }



            // pwsearch modal 창을 보여줌
            btnOpen2.onclick = function() {
            var modal2 = document.getElementById('modal2');
           modal2.style.display = 'block'; 
            }
            
            btnClose2.onclick = closeRtn2;
        </script>
<script>
		var mailCode;
		function sendMail(){
			var email = $("#email").val();
			$.ajax({
				url :"/sendMail",
				data : {email:email},
				type : "post",
				success : function(data){
					mailCode = data;
					$("#auth").slideDown();
					authTime();
				}
			});
		}
		var intervalId;
		function authTime(){
			$("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
			intervalId = window.setInterval(function(){
				timeCount();
			},1000);
		}
		function timeCount(){
		var min = Number($("#min").html());
		var sec = $("#sec").html();
		if(sec == "00"){
			if(min == 0){
				mailCode = null;
				clearInterval(intervalId);
			}else{
				$("#min").html(--min);
				$("#sec").html(59);
			}
		}else{
			var newSec = Number(sec);
			newSec--;
			if(newSec<10){
				$("#sec").html("0"+newSec);
			}else{
				$("#sec").html(newSec);
			}
		}
		}
		
		function idsubmit(){
			if($("#authCode").val() == mailCode){
				
			 
					if($("#authCode").val() && mailCode != ""){
						
						if($("#authCodeCheck").val() == '1'){
							
							return true;
						}else{
							alert("메일인증버튼을 눌러주세요.");
							return false;
							
						}
					}else{
						alert("메일인증키를 입력해주세요.");
						return false;
					}
				
			}else{
				alert("메일인증을 실패했습니다.");
				return false;
			}
		 
		}
		$("#authBtn").click(function(){
			
			if(mailCode == null){
				$("#authMsg").html("인증시간 만료");
				$("#authMsg").css("color","red");
				 btnCheck1.onclick = alert("인증메일을 확인해주세요");
			
			}else{
				console.log(mailCode);
				console.log($("#authCodeCheck").val());
				if($("#authCode").val() == mailCode){
					$("#authMsg").html("인증성공");
					$("#authCodeCheck").val("1");
					$("#authMsg").css("color","blue");
					clearInterval(intervalId);
					console.log($("#authCodeCheck").val());	
					$("#timeZone").empty();
					
			   

				}else{
					$("#authMsg").html("인증코드를 확인하세요");
					$("#authMsg").css("color","red");
					 btnCheck1.onclick = alert("인증메일을 확인해주세요");
					return false;
				}
			}
			
		})
		</script>
		<script>
		var mailCode2;
		function sendMail2(){
			var email = $("#email2").val();
			$.ajax({
				url :"/sendMail",
				data : {email:email},
				type : "post",
				success : function(data){
					mailCode2 = data;
					$("#auth2").slideDown();
					authTime2();
				}
			});
		}
		var intervalId2;
		function authTime2(){
			$("#timeZone2").html("<span id='min2'>3</span> : <span id='sec2'>00</span>");
			intervalId = window.setInterval(function(){
				timeCount2();
			},1000);
		}
		function timeCount2(){
		var min2 = Number($("#min2").html());
		var sec2 = $("#sec2").html();
		if(sec2 == "00"){
			if(min2 == 0){
				mailCode2 = null;
				clearInterval(intervalId2);
			}else{
				$("#min2").html(--min2);
				$("#sec2").html(59);
				
			}
		}else{
			var newSec2 = Number(sec2);
			newSec2--;
			if(newSec2<10){
				$("#sec2").html("0"+newSec2);
			}else{
				$("#sec2").html(newSec2);
			}
		}
		}
		$("#authBtn2").click(function(){
			
			if(mailCode2 == null){
				$("#authMsg2").html("인증시간 만료");
				$("#authMsg2").css("color","red");
			}else{
				console.log(mailCode2);
				console.log($("#authCode2").val());
				if($("#authCode2").val() == mailCode2){
					$("#authMsg2").html("인증성공");
					$("#authMsg2").css("color","blue");
					clearInterval(intervalId);
					$("#timeZone2").empty();
					$("#authCodeCheck2").val("1");
				}else{
					$("#authMsg2").html("인증코드를 확인하세요");
					$("#authMsg2").css("color","red");
				}
			}
			
		})
		</script>
		<script>
		function pwSubmit(){
			
			//아이디값이 null이 아닌 경우
			if($("#id2").val() != ""){
			//메일인증 실패한경우	
			
			if($("#authCode2").val() == mailCode2 && $("#authCode2").val() && mailCode2 != "" && $("#authCodeCheck2").val() =="1"){
				return true;
				
			}else{
				alert("메일인증을 확인해주세요");
				
				return false;
			}
			
		}else{
			alert("아이디를 입력하세요");
			return false;
		}
			}
						
		</script>
		</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>