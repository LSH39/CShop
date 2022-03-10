<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/shopping.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#more-btn{
		margin-top : 100px;
	}
	.reviewWrapper {
		padding-top: 20px;
		clear:right;
		display:flex;
		flex-warp : wrap;
		justify-content: center;
	}
	.review{
		border : 1px solid #ccc;
		margin-top : 30px;
		width : 18%;
		height : 122px;
		overflow : hidden;
	}
	.review>img{
		width : 100%;
		height: 100px;	
	}
	.review>p{
		text-align:center;
	}
	.starRate~img{
		width:16px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" style="margin-top : 70px;">
		<div class="c-left">
			<jsp:include page="/WEB-INF/views/common/leftNaviMyPage.jsp"/>
		</div>
		<div class="c-right">
		 <div id="title">
		        	<h2>리뷰 관리</h2>
		 </div>
		 <fieldset>
			<div class="list_shopping">
			<ul class="list_order">
			<c:forEach items="${list}" var="prl" varStatus="i">
		        <li>
		            <div class="pr_date">${prl.prDate}</div> 
		            <div class="order_box">
		                <div class="name"><a href="/reviewListView?productNo=${prl.productNo }">${prl.productName}</a></div> 
		                <div class="order_info">
		                    <div class="thumb">
		                    <c:choose> 
		                    <c:when test="${empty prl.prFilepath}">	
		                    	 <img src='/img/기본.JPG'>
		                   	</c:when>
		                   	<c:otherwise>
		                    <img src="/upload/review/${prl.prFilepath}">
		                    </c:otherwise>
		                    </c:choose>
		                    </div> 
		                    <div class="desc">
		                    	<dl><dt>별점</dt><dd><span class="starRate" >${prl.prStar}</span></dd></dl>
		                        <dl><dt>내용</dt><dd>${prl.prContent}</dd></dl> 
		                    </div>
		                </div> 
		                <div class="order_status"><span class="inner_status"><!----> <!----> 
		                </span>
		                </div>
		            </div>
		        </li>
			</c:forEach>
		    </ul>
			</div>
		</fieldset>
		</div>
	</div>
	<script>
		$("#more-btn").click(function(){
			var start = $(this).val(); //현재 자기자신의 val this
			$.ajax({
				url: "/reviewMore",
				data : {start:start},
				type : "post" ,
				success : function(data){
					for(var i=0; i<data.lenght;i++){
						var p = data[i];
						var html ="";
						html += "<div class='review'>";
						html += "<img src='/upload/review/"+p.filepath+"''>";
						html += "<p class='caption'>"+p.PrContents+"</p></div>"; 
						$(".reviewWrapper").append(html);
					}
					//가지고온 데이터를 화면에 출력한후 다음요청을 위한 값변경
					$("#more-btn").val(Number(start)+5);
					//다나왔으면 버튼 비활성화해주기
					//지금까지 읽어온 게시물의 수를 변경(읽어온 배열의 길이만큼 기존값에 더함)
					var curr = Number($("#more-btn").attr("currentCount"));
					$("#more-btn").attr("currentCount",curr+data.length);
					//전체게시물수
					var totalCount = $("#more-btn").attr("totalCount");
					//변경된 지금까지 읽어온 게시물 수 
					var currCount = $("#more-btn").attr("currentCount");
					//지금까지 읽어온 게시물과 전체 게시물 수가 같으면 더보기 비홀성화
					if(currCount == totalCount){
						$("more-btn").prop("disabled",true);
					}
				}
			});
		});
		$(function(){
			//별점
			$(".starRate").each(function(index,item){
				var rate = $(item).html();
				for(var i=0;i<5;i++){
					if(i<rate){
						$(item).parent().append("<img src='/img/star-on.png'>");
					}else{
						$(item).parent().append("<img src='/img/star-off.png'>" );
					}
				}
				
			});
			
		}); 
		
		//		 	<c:if test="${not empty sessionScope.m && sessionScope.m.memberLevel eq 1}">
				
		// 	</c:if>
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>