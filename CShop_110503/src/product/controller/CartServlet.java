package product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Cart;
import product.model.service.ProductService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "Cart", urlPatterns = { "/cart" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pColor = request.getParameter("productColor");
		String pSize = request.getParameter("productSize");
		int orderAmount = Integer.parseInt(request.getParameter("orderAmount"));
		
		Cart c = new Cart();
		c.setProductNo(Integer.parseInt(request.getParameter("productNo")));
		c.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
		c.setProductColor(pColor);
		c.setProductSize(pSize);
		c.setOrderAmount(orderAmount);
		c.setProductPrice(Integer.parseInt(request.getParameter("productPrice")));
		c.setProductSeller(Integer.parseInt(request.getParameter("productSeller")));
		c.setProductName(request.getParameter("productName"));
		c.setProductImage(request.getParameter("productImage"));
		
		int selectNum = Integer.parseInt(request.getParameter("selectNum"));
		String productId = request.getParameter("productId");
		int sellerNo = Integer.parseInt(request.getParameter("productSeller"));	
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		//재고확인
		int result2 = new ProductService().productCount2(pColor,pSize,productId);
		if(result2-orderAmount == 0 || result2-orderAmount > 0 ) {
			//재고가 있으면 장바구니에 넣어
			int result = new ProductService().insertCart(c);
			if(result>0) {
				if(selectNum == 1) {
					response.sendRedirect("/cartFrm");
				}else if(selectNum == 2) {
					response.sendRedirect("/productView?id="+productId+"&seller="+sellerNo+"&reqPage=1");
				}
			}else {
				request.setAttribute("msg", "장바구니 등록 실패");
				request.setAttribute("loc", "/productView?id="+productId+"&seller="+sellerNo+"&reqPage=1");
				view.forward(request, response);
			}	
		}else{
			request.setAttribute("msg", "고객님 죄송합니다. 현재 상품의 재고는 "+result2+"개 입니다.");
			request.setAttribute("loc", "/productView?id="+productId+"&seller="+sellerNo+"&reqPage=1");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
