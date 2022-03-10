package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.ShoppingListService;

/**
 * Servlet implementation class MembershipServlet
 */
@WebServlet(name = "Membership", urlPatterns = { "/membership" })
public class MembershipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembershipServlet() {
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
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int selectmenu = Integer.parseInt(request.getParameter("selectmenu"));
		//3.비즈니스로직
		//ShoppingList orderCount = new ShoppingListService().getOrderCount(memberNo);
		int oc = new ShoppingListService().getOrderCount(memberNo);
		//4.결과처리
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/member/Membership.jsp");
		request.setAttribute("oc", oc);
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
