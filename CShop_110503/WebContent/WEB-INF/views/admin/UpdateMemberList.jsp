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
    <link href="/css/updateMemberList.css" rel="stylesheet" type="text/css">
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
				        	<h2>회원 등급 변경</h2>
				 	</div>
					<div class="c-right-inner">
		                    <div class="admin-button-top">
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(1);">FAMILY</button>
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(2);">VIP</button>
		                        <button type="button" class="admin-button admin-button-choice" onclick="admin_button_choice(3);">SVIP</button>
		                        <button type="button" class="admin-button admin-button-dbupdate" onclick="dbupdate();" title="">회원등급 변경</button>
		                    </div>
		                    <table class="admin-table">
		                        <tr>
		                            <th>회원번호</th>
		                            <th>이름</th>
		                            <th>6개월 이내 구매 건수</th>
		                            <th>기존 등급</th>
		                            <th>변경 후 등급</th>
		                        </tr>
		                        <c:forEach items="${list1 }" var="m" varStatus="i">
		                        <tr>
		                            <td>${m.memberNo }</td>
		                            <td>${m.memberName }</td>
		                            <td>${m.orderCount }</td>
		                            <td>
			                            <c:choose>
			                            	<c:when test="${m.membership == 1}">FAMILY</c:when>
			                            	<c:when test="${m.membership == 2}">VIP</c:when>
											<c:when test="${m.membership == 3}">SVIP</c:when>
										</c:choose>
									</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${newMembership1 == 1}">FAMILY</c:when>
			                            	<c:when test="${newMembership1 == 2}">VIP</c:when>
											<c:when test="${newMembership1 == 3}">SVIP</c:when>
										</c:choose>
		                            </td>
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
				                        </tr>
			                    	</c:forEach>
			                    </c:if>
		                    </table>
		                    <div id="pageNavi" class="admin-navi">${pageNavi1 }</div>
		
		                    <table class="admin-table">
		                        <tr>
		                            <th>회원번호</th>
		                            <th>이름</th>
		                            <th>6개월 이내 구매 건수</th>
		                            <th>기존 등급</th>
		                            <th>변경 후 등급</th>
		                        </tr>
		                        <c:forEach items="${list2 }" var="m" varStatus="i">
		                        <tr>
		                            <td>${m.memberNo }</td>
		                            <td>${m.memberName }</td>
		                            <td>${m.orderCount }</td>
		                            <td>
			                            <c:choose>
			                            	<c:when test="${m.membership == 1}">FAMILY</c:when>
			                            	<c:when test="${m.membership == 2}">VIP</c:when>
											<c:when test="${m.membership == 3}">SVIP</c:when>
										</c:choose>
									</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${newMembership2 == 1}">FAMILY</c:when>
			                            	<c:when test="${newMembership2 == 2}">VIP</c:when>
											<c:when test="${newMembership2 == 3}">SVIP</c:when>
										</c:choose>
		                            </td>
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
				                        </tr>
			                    	</c:forEach>
			                    </c:if>
		                    </table>
		                    <div id="pageNavi" class="admin-navi">${pageNavi2 }</div>
		                    
		                    <table class="admin-table">
		                        <tr>
		                            <th>회원번호</th>
		                            <th>이름</th>
		                            <th>6개월 이내 구매 건수</th>
		                            <th>기존 등급</th>
		                            <th>변경 후 등급</th>
		                        </tr>
		                        <c:forEach items="${list3 }" var="m" varStatus="i">
		                        <tr>
		                            <td>${m.memberNo }</td>
		                            <td>${m.memberName }</td>
		                            <td>${m.orderCount }</td>
		                            <td>
			                            <c:choose>
			                            	<c:when test="${m.membership == 1}">FAMILY</c:when>
			                            	<c:when test="${m.membership == 2}">VIP</c:when>
											<c:when test="${m.membership == 3}">SVIP</c:when>
										</c:choose>
									</td>
		                            <td>
		                            	<c:choose>
			                            	<c:when test="${newMembership3 == 1}">FAMILY</c:when>
			                            	<c:when test="${newMembership3 == 2}">VIP</c:when>
											<c:when test="${newMembership3 == 3}">SVIP</c:when>
										</c:choose>
		                            </td>
		                        </tr>
		                        </c:forEach>
		                        <c:if test="${fn:length(list3) <= 9}">
			                      	<c:forEach begin="${fn:length(list3)}" end="9">
				                        <tr>
				                            <td></td>
				                            <td></td>
				                            <td></td>
				                            <td></td>
				                            <td></td>
				                        </tr>
			                    	</c:forEach>
			                    </c:if>
		                    </table>
		                    <div id="pageNavi" class="admin-navi">${pageNavi3 }</div>
		                    
		                </div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container" style="margin-top : 70px; height:600px"></div>
		</c:otherwise>
	</c:choose>
	<script>
		var choiceNo;
        $(function(){
        	if(${sessionScope.m.memberLevel != 3 }){
	    		alert("잘못된 접근입니다.");
	    		location.href="/";
	    	} 
        	
            var choice_button = $(".admin-button-choice").eq(0);
            choice_button.css("background-color","#5F755A").css("color","#ffffff").css("font-weight","bold");
            $(".admin-table").eq(0).css("display","table");
            $(".admin-table").eq(1).css("display","none");
            $(".admin-table").eq(2).css("display","none");
            $(".admin-navi").eq(0).css("display","block");
            $(".admin-navi").eq(1).css("display","none");
            $(".admin-navi").eq(2).css("display","none");
            $(".admin-button-dbupdate").attr("title","변경 대상 회원의 등급이 FAMILY로 변경됩니다.");
            choiceNo = 1;

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
            var titleGrade = choice_button.html();
            var newTitle = "변경 대상 회원의 등급이 "+titleGrade+"로 변경됩니다.";
            $(".admin-button-dbupdate").attr("title",newTitle);
            choiceNo = choice;
        }
        
        function dbupdate(){
        	var memberNoArr1 = []; var memberNoArr2 = []; var memberNoArr3 = [];
        	<c:forEach items="${list1 }" var="m" varStatus="i">
        		memberNoArr1.push(${m.memberNo });
        	</c:forEach>
        	<c:forEach items="${list2 }" var="m" varStatus="i">
	    		memberNoArr2.push(${m.memberNo });
	    	</c:forEach>
	    	<c:forEach items="${list3 }" var="m" varStatus="i">
				memberNoArr3.push(${m.memberNo });
			</c:forEach>
        	
			var selectArr = [];
			if(choiceNo == 1){
				selectArr = memberNoArr1;
			}else if(choiceNo == 2){
				selectArr = memberNoArr2;
			}else if(choiceNo == 3){
				selectArr = memberNoArr3;
			}
			var selectStr = selectArr.toString();
        	$.ajax({
				url : "/updateMemberDo",
				type : "post",
				data : {choiceNo:choiceNo,selectArr:selectStr},
				success : function(data){
					if(data>0){
						alert("업데이트 성공");
					}else{
						alert("업데이트 실패. 에러 발생");
					}
				}
			});
        }
        
    </script>
	
	<%@include file = "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>