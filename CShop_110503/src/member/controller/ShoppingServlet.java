package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.ShoppingListService;
import member.model.vo.OrderShopping;
/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet(name = "Shopping", urlPatterns = { "/shopping" })
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
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
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		//3.비즈니로직
		OrderShopping os = new ShoppingListService().selectShoppingPro(orderNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/Shopping.jsp");
		request.setAttribute("slist",os.getSlist());
		request.setAttribute("o", os.getO());
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
