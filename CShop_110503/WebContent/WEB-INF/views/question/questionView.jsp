<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CShop</title>
<link rel="stylesheet" href="css/contact.css?after">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top:70px">
		<div class="notice-left col-md-3">
			<h1>고객센터</h1><br>
			<h3><a href="/notice?reqPage=1">공지사항</a></h3><br>
			<h3><a href="/question?reqPage=1">1:1문의</a></h3>
		</div>
		<div class="notice-right col-md-9">
			<div class="notice-title">
				<div class="notice-title-left col-md-10">
					<h1 style="font-size:24px !important">1:1문의</h1>
				</div>
			</div>
			
			<div class="noticeView-head">
				<div>${c.regDate }</div>
				<h2><div>${c.contactTitle }</div></h2>
				<p style="text-align:right">
					<c:choose>
						<c:when test="${c.filename != null }">
							<img src="/img/file.png" width="16px">
							<a href="/fileDown?contactNo=${c.contactNo }">${c.filename }</a>
							 | ${c.contactWriter }
						</c:when>
						<c:otherwise>
							첨부파일없음 | ${c.contactWriter }
						</c:otherwise>
					</c:choose>
				</p>
			</div>
			
			<div class="noticeView-contents">
				${c.contactContent }
			</div>
			
			<div class="noticeView-foot">
				<button class="btn btn-default" onclick="history.go(-1);">목록</button>
					<c:if test="${sessionScope.m != null && sessionScope.m.memberId==c.contactWriter }">
						<a href="/questionUpdateFrm?contactNo=${c.contactNo }" class="btn btn-default">수정하기</a>
						<a href="/questionDelete?contactNo=${c.contactNo }" class="btn btn-default">삭제하기</a>
					</c:if>
					<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
						<a href="/questionDelete?contactNo=${c.contactNo }" class="btn btn-default">삭제하기</a>
					</c:if>					
			</div>
		
	
	
	<%--댓글 작성 --%>
		<c:if test="${sessionScope.m != null}">
		<div class = "inputCommentBox">
			<form action="/insertComment" method="post">
				<ul>
					<li>
						<i class="glyphicon glyphicon-pencil"></i><!-- 아이콘 -->
					</li>
					<li>
						<input type="hidden" name="ctLevel" value="1"><!-- 일반댓글 -->
						<input type="hidden" name="ctWriter" value="${sessionScope.m.memberId }"><!-- 로그인을 해야 댓글을남김 -->
						<input type="hidden" name="contactRef" value="${c.contactNo }"><!-- 글 번호 -->
						<input type="hidden" name="ctRef" value="0"><!-- 대댓글이니깐 0 -->
						<textarea name="ctContent" class="form-control" style="resize:none;"></textarea>
					</li>
					<li>
						<button type="submit" class="btn btn-success btn-lg">등록</button>
					</li>
				</ul>
			</form>
		</div>
		</c:if>
		
		<%--댓글 출력 --%>
		<div class="commentBox">
			<c:forEach items="${list }" var="ct" varStatus="i">
				<%--ncLevel==1 -> 일반 댓글인경우 (2는대댓글)--%>
				<c:if test="${ct.ctLevel==1 }">
					<ul class="comments">
						<li>
							<%--아이콘/작성자/작성일 --%>
							<i class="glyphicon glyphicon-user"></i>
							<p>${ct.ctWriter }</p>
							<p>${ct.ctDate }</p>
						</li>
						<li>
							<p>${ct.ctContentBr }</p>
							<textarea name = "ctContent" class="form-control" style="display:none;">${ct.ctContent }</textarea>
							<p class="commentBtn">
							
							<c:if test="${sessionScope.m != null }">
								<c:if test="${sessionScope.m.memberId eq ct.ctWriter }">
									<a href="javascript:void(0)" onclick="modifyComment(this,'${ct.ctNo }','${c.contactNo }');">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this,'${ct.ctNo }','${c.contactNo }');">삭제</a>
								</c:if><!-- 로그인한 회원과 출력되는 댓글 작성자가 같은지 체크 -->
								<a href="javascript:void(0)" class="recShow">답글달기</a>
							</c:if><!-- 댓글 수정,삭제,대댓글달기 버튼용 로그인체크 -->
							</p>
							<c:if test="${sessionScope.m != null }">
								<form action="/insertComment" class="recoment">
									<input type="hidden" name="ctLevel" value="2">
									<input type="hidden" name="ctWriter" value="${sessionScope.m.memberId }">
									<input type="hidden" name="contactRef" value="${c.contactNo}">
									<input type="hidden" name="ctRef" value="${ct.ctNo }">
									<textarea name="ctContent" class="form-control"></textarea>
									<div>
										<button type="submit" class="btn btn-outline-primary">등록</button>
										<button type="reset" class="btn btn-outline-primary recCancel">취소</button>
									</div>
								</form>
							</c:if><!-- 로그인이 되어있는 경우 대댓글 작성용 form태그 미리 생성 -->
						</li>
					</ul><!-- 일반댓글 출력끝 -->
					<!-- 대댓글 출력시작 -->
					<c:forEach items="${list }" var="ctt" varStatus="i">
						<!-- 댓글의 번호와 대댓글이 참조하는 댓글번호가 같을때 -->
						<c:if test="${ctt.ctLevel==2 && ct.ctNo==ctt.ctRef }">
							<ul class="recomments">
								<li>
									<i class="glyphicon glyphicon-menu-right"></i>
								</li>
								<li>
									<i class="glyphicon glyphicon-user"></i>
									<p>${ctt.ctWriter }</p>
									<p>${ctt.ctDate }</p>
								</li>
								<li>
									<p>${ctt.ctContentBr }</p>
									<textarea name = "ctContent" class="form-control" style="display:none;">${ctt.ctContent }</textarea>
									<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel == 3 }">
										<p class="commentsBtn">
												<a href="javascript:void(0)" onclick="deleteComment(this,'${ctt.ctNo }','${c.contactNo }');">삭제</a>
										</p>
									</c:if>
								</li>
							</ul>
						</c:if>
					</c:forEach><!-- 대댓글 반복문 끝 -->
				</c:if><!-- 일반댓글 if문 -->
			</c:forEach><!-- 일반댓글 반복문 -->
			</div>
		</div>
		
	</div>
	<script>
		$(".recShow").click(function(){
			//몇번째 답글달기 버튼을 클릭했는지
			var idx = $(".recShow").index(this);
			//안보이게
			$(this).hide();
			//몇번째꺼의 대댓글을 보여줘라
			$(".recoment").eq(idx).css("display","flex");
		});
		$(".recCancel").click(function(){
			//취소동작
			var idx=$(".recCancel").index(this);
			$(".recoment").eq(idx).css("display","none");
			$(".recShow").eq(idx).show();
		});
		function modifyComment(obj,ctNo,contactNo){
			//현재 상태 수정 삭제 답글달기 (현재 위치는 수정)
			
			//textarea를 화면에 표현 - a태그 부모인p 이전요소인 textarea
			$(obj).parent().prev().show();
			//기존 본문 내용을 숨김 a태그 부모인p의 이전요소textarea의 이전요소인 p를 숨김
			$(obj).parent().prev().prev().hide();
			//수정 -> 수정완료로 변경
			$(obj).html('수정완료');
			$(obj).attr("onclick","modifyComplete(this,'"+ctNo+"','"+contactNo+"');");
			//삭제 -> 취소
			$(obj).next().html('취소');
			$(obj).next().attr("onclick","modifyCancel(this,'"+ctNo+"','"+contactNo+"');");
			//답글달기버튼 숨기기
			$(obj).next().next().hide();
		}
		function modifyCancel(obj,ctNo,contactNo){
			//현재 상태 수정완료 취소 (현재위치는 취소)
			
			//textarea를 화면에 숨김 - a태그 부모인p 이전요소인 textarea
			$(obj).parent().prev().hide();
			//기존 본문 내용을 숨김 a태그 부모인p의 이전요소textarea의 이전요소인 p를 표현
			$(obj).parent().prev().prev().show();
			//수정완료 -> 수정로 변경
			$(obj).prev().html('수정');
			$(obj).prev().attr("onclick","modifyComment(this,'"+ctNo+"','"+contactNo+"');");
			//삭제 -> 취소
			$(obj).html('삭제');
			$(obj).attr("onclick","deleteComment(this,'"+ctNo+"','"+contactNo+"');");
			//답글달기버튼 숨기기
			$(obj).next().show();
		}
		function modifyComplete(obj,ctNo,contactNo){
			//서버에 보내는 함수
			//새로운 form태그를 생성
			var form = $("<form action='/updateComment' method='post'></form>");
			//form안에 수정댓글번호 설정
			form.append($("<input type='text' name='ctNo' value='"+ctNo+"'>"));
			//form안에 공지사항번호 설정
			form.append($("<input type='text' name='contactNo' value='"+contactNo+"'>"));
			//수정한 댓글 내용을 성정
			form.append($(obj).parent().prev());
			//전송할 form태그를 현재 페이지에 추가
			$("body").append(form);
			//form태그 전송
			form.submit();
		}
		function deleteComment(obj,ctNo,contactNo){
			//확인 취소가 뜨는 confirm
			if(confirm("댓글을 삭제하시겠습니까?")){
				//되돌아오기위해 noticeNo를 보냄
				location.href="/deleteComment?ctNo="+ctNo+"&contactNo="+contactNo;
				
			}

		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>