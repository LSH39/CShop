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
    <link href="/css/retireMemberList.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<%@include file = "/WEB-INF/views/common/header.jsp" %>
	<c:choose>
		<c:when test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
			<div class="container" style="margin-top : 70px;">
				<div class="c-left">
					<jsp:include page="/WEB-INF/views/common/leftNavi_admin.jsp"/>
				</div>
				<div class="c-right">
				 	<div id="title">
				        	<h2>탈퇴 회원 정보</h2>
				 	</div>
					<div class="c-right-inner">
		                    <div class="admin-button-top">
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(1);">구매자</button>
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(2);">판매자</button>
		                        <button type="button" class="admin-button admin-button-dbupdate" onclick="dbupdate();" title="탈퇴 6개월이 지난 회원의 정보가 삭제됩니다.">회원정보삭제</button>
		                    </div>
		                    <table class="admin-table">
		                        <tr>
		                            <th>회원번호</th>
		                            <th>닉네임</th>
		                            <th>이름</th>
		                            <th>아이디</th>
		                            <th>연락처</th>
		                            <th>탈퇴일</th>
		                        </tr>
		                        <c:forEach items="${list1 }" var="m" varStatus="i">
			                        <tr>
			                            <td>${m.memberNo }</td>
			                            <td>${m.memberNickname }</td>
			                            <td>${m.memberName }</td>
			                            <td>${m.memberId }</td>
			                            <td>${m.memberPhone }</td>
			                            <td>${m.withdrawDate }</td>
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
		                    
		                    <table class="admin-table">
		                        <tr>
		                            <th>회원번호</th>
		                            <th>판매자명</th>
		                            <th>이름</th>
		                            <th>아이디</th>
		                            <th>연락처</th>
		                            <th>탈퇴일</th>
		                        </tr>
		                        <c:forEach items="${list2 }" var="m" varStatus="i">
			                        <tr>
			                            <td>${m.memberNo }</td>
			                            <td>${m.memberNickname }</td>
			                            <td>${m.memberName }</td>
			                            <td>${m.memberId }</td>
			                            <td>${m.memberPhone }</td>
			                            <td>${m.withdrawDate }</td>
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
				                        </tr>
			                    	</c:forEach>
			                    </c:if>
		                    </table>
		                    <div id="pageNavi" class="admin-navi">${pageNavi2 }</div>
		                    
		                </div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container" style="margin-top : 70px; height:600px"></div>
		</c:otherwise>
	</c:choose>
	<script>
		var memberLevel;
        $(function(){
        	if(${sessionScope.m.memberLevel != 3 }){
        		alert("잘못된 접근입니다.");
        		location.href="/";
        	}
        	
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
            memberLevel = choice;
        }
        
        function dbupdate(){
        	$.ajax({
				url : "/deleteRetireMember",
				data : {memberLevel:memberLevel},
				type : "post",
				success : function(data){
					console.log(data);
					if(data > 0){
						alert("삭제 성공");
					}else if(data == -1){
						alert("삭제할 데이터가 없습니다.");
					}
				},
				error : function(){
					alert("삭제 실패");
				}
			});
        }
        
    </script>
	
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>