package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ReviewService;
import product.model.vo.Product;

/**
 * Servlet implementation class ReviewListViewServlet
 */
@WebServlet(name = "ReviewListView", urlPatterns = { "/reviewListView" })
public class ReviewListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		//3.비즈니스로직
		Product p = new ReviewService().reviewListView(productNo);
		//4.결과처리
		response.sendRedirect("/productReviewView?id="+p.getProductId()+"&seller="+p.getProductSeller()+"&reqPage=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
