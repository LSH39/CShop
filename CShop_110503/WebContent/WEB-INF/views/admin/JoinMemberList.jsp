<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminPage</title>
    <link href="/css/admin.css" rel="stylesheet" type="text/css">
    <link href="/css/joinMemberList.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>

	<c:choose>
	     <c:when test="${sessionScope.m.memberLevel != 3 }">
	          <script>
	          	alert("잘못된 접근입니다.");
	    		location.href="/";
	          </script>
	     </c:when>
	     <c:otherwise>
	     
	     	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	     	
			<div class="container" style="margin-top : 70px;">
				<div class="c-left">
					<jsp:include page="/WEB-INF/views/common/leftNavi_admin.jsp"/>
				</div>
				<div class="c-right">
				 	<div id="title">
				        	<h2>가입 회원 정보</h2>
				 	</div>
					<div class="c-right-inner">
	                    <div class="admin-button-top">
	                        <button type="button" class="admin-button admin-button-choice" id="btn1" onclick="admin_button_choice(1);">구매자</button>
	                        <button type="button" class="admin-button admin-button-choice" id="btn2" onclick="admin_button_choice(2);">판매자</button>
	                    </div>
	                    <table class="admin-table" id="choice1">
	                        <tr>
	                            <th>회원번호</th>
	                            <th>닉네임</th>
	                            <th>이름</th>
	                            <th>아이디</th>
	                            <th>연락처</th>
	                            <th>가입일</th>
	                        </tr>
	                        
	                        <c:forEach items="${list1 }" var="m" varStatus="i">
	                        <tr>
	                            <td>${m.memberNo }</td>
	                            <td>${m.memberNickname }</td>
	                            <td>${m.memberName }</td>
	                            <td>${m.memberId }</td>
	                            <td>${m.memberPhone }</td>
	                            <td>${m.enrollDate }</td>
	                        </tr>
	                        </c:forEach>
	                        <c:if test="${fn:length(list1) <= 9}">
		                      	<c:forEach begin="${fn:length(list1)}" end="9">
			                        <tr>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                        </tr>
		                    	</c:forEach>
		                    </c:if>
	                    </table>
	                    
	                    <div id="pageNavi" class="admin-navi">${pageNavi1 }</div>
	                    
	                    <table class="admin-table" id="choice2">
	                        <tr>
	                            <th>회원번호</th>
	                            <th>판매자명</th>
	                            <th>이름</th>
	                            <th>아이디</th>
	                            <th>연락처</th>
	                            <th>등록물품수</th>
	                            <th>가입일</th>
	                        </tr>
	                        
	                        <c:forEach items="${list2 }" var="m" varStatus="i">
	                        <tr>
	                            <td>${m.memberNo }</td>
	                            <td>${m.memberNickname }</td>
	                            <td>${m.memberName }</td>
	                            <td>${m.memberId }</td>
	                            <td>${m.memberPhone }</td>
	                            <td>${m.saleProduct }
	                            <td>${m.enrollDate }</td>
	                        </tr>
	                        </c:forEach>
	                        <c:if test="${fn:length(list2) <= 9}">
		                      	<c:forEach begin="${fn:length(list2)}" end="9">
			                        <tr>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                            <td></td>
			                        </tr>
		                    	</c:forEach>
		                    </c:if>
	                    </table>
	                    <div id="pageNavi" class="admin-navi">${pageNavi2 }</div>
	                </div>
				</div>
				</div>
			<script>
				var memberLevel;
		        $(function(){
		        	/*
		        	<c:if test="${sessionScope.m.memberLevel != 3 }">
			        	alert("잘못된 접근입니다.");
			    		location.href="/";
		        	</c:if>
		        	*/
		        	
		        	memberLevel = 1;
		            var choice_button = $(".admin-button-choice").eq(0);
		            choice_button.css("background-color","#5F755A").css("color","#ffffff").css("font-weight","bold");
		            $(".admin-table").eq(0).css("display","table");
		            $(".admin-table").eq(1).css("display","none");
		            $(".admin-navi").eq(0).css("display","block");
		            $(".admin-navi").eq(1).css("display","none");
		            
		        });
		
		        function admin_button_choice(choice) {
		            for(var i=0; i<$(".admin-button-choice").length; i++){
		                $(".admin-button-choice").eq(i).css("background-color","#e0e0e0").css("color","#000000").css("font-weight","normal");
		                $(".admin-table").eq(i).css("display","none");
		                $(".admin-navi").eq(i).css("display","none");
		            }
		            var choice_button = $(".admin-button-choice").eq(choice-1);
		            choice_button.css("background-color","#5F755A").css("color","#ffffff").css("font-weight","bold");
		            $(".admin-table").eq(choice-1).css("display","table");
		            $(".admin-navi").eq(choice-1).css("display","block");
		            
		        }
		        
		    </script>
			
			<%@include file = "/WEB-INF/views/common/footer.jsp" %>
			
			
	     </c:otherwise>
	</c:choose>
	
	
</body>
</html>