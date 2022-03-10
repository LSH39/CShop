package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;

/**
 * Servlet implementation class UpdateReviewServlet
 */
@WebServlet(name = "UpdateReview", urlPatterns = { "/updateReview" })
public class UpdateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prNo = Integer.parseInt(request.getParameter("prNo"));
		String productId = request.getParameter("productId");
		int sellerNo = Integer.parseInt(request.getParameter("sellerNo"));
		String prContent = request.getParameter("prContent");
		
		int result = new ProductService().updateReview(prNo, prContent);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {		
			request.setAttribute("msg", "수정성공");
		}else {
			request.setAttribute("msg", "수정실패");
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
