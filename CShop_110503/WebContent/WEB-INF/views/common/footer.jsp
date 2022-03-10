<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    	#footer{
    		background-color:black;
			display:flex;
			align-items:center;
			justify-content:center;
    	}
    	.foottainer{
    		width:1200px;
    		height:200px;
			display:flex;
			align-items:center;
    	}
    	.foottainer div{
    		color:white;
    	}
    	span a{
			display:flex;
			justify-content:center;
	    	text-decoration: none;
			font-size:25px;
	    	color:#5F755A;
    	}
    	span a:hover{
	    	text-decoration: none;
			font-size:25px;
	    	color:#ADC2A9;
    	}
    </style>
	<footer id="footer">
		<div class="foottainer">
			<div class="footleft col-md-3">
				<span><a href="#"><img src="img/logo1.jpg"></a></span><br>
				<span><a href="/notice?reqPage=1">고객센터</a></span>
			</div>
			<div class="footleft col-md-5">
			<p>CShop | 대표자:이소현<br>
			사업자등록번호:000-00-00000<br>
			주소:서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F, 20F<br> 
			TEL:010-0000-0000</p>
			</div>
			<div class="footleft col-md-4">
				<p style="color:#f52644;">CShop에서는 진품만을 거래할 수 있으며, 불확실한 상품의 거래는 엄격히 제한됩니다.<br><br>
				CShop은 통신판매중개자이며, 상품은 개별판매자가 등록한 것으로 상품 거래에 관한 의무와 책임은 판매자에게 있습니다.</p>
			</div>		
		</div>
	</footer>