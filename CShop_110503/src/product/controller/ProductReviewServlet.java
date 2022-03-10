package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.ProductReview;

/**
 * Servlet implementation class ProductReviewServlet
 */
@WebServlet(name = "ProductReview", urlPatterns = { "/productReview" })
public class ProductReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		ProductReview pr = new ProductReview();
		pr.setProductNo(Integer.parseInt(request.getParameter("productNo")));
		pr.setPrWriter(request.getParameter("prWriter"));
		pr.setPrLevel(Integer.parseInt(request.getParameter("prLevel")));
		pr.setPrContent(request.getParameter("prContent"));
		pr.setPrRef(Integer.parseInt(request.getParameter("prRef")));
		
		String productId = request.getParameter("productId");
		int sellerNo = Integer.parseInt(request.getParameter("sellerNo"));
		
		//비즈니스 로직
		int result = new ProductService().insertReReview(pr);
		
		
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {		
			request.setAttribute("msg", "등록성공");
		}else {
			request.setAttribute("msg", "등록실패");
		}
		request.setAttribute("loc", "/productReviewView?id="+productId+"&seller="+sellerNo+"&reqPage=1");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
