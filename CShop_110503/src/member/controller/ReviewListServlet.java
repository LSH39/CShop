package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.ShoppingListService;
import member.model.vo.Member;
import member.model.vo.ShoppingList;
import product.model.service.ReviewService;
import product.model.vo.ProductReview;
import product.model.vo.ProductReviewList;

/**
 * Servlet implementation class ReviewListServlet
 */
@WebServlet(name = "ReviewList", urlPatterns = { "/reviewList" })
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListServlet() {
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
		//int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int selectmenu = Integer.parseInt(request.getParameter("selectmenu"));
		String prWriter = request.getParameter("prWriter");

		//3.비즈니스로직
		//int totalCount = new ReviewService().totalCount();
		ArrayList<ProductReviewList> list = new ReviewService().selectReviewList(prWriter);
		//4.결과처리
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/member/ReviewList.jsp");
		request.setAttribute("list", list);
		request.setAttribute("selectmenu", selectmenu);
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
