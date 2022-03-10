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

import member.model.service.MemberService;
import member.model.vo.Cart;
import member.model.vo.Member;

/**
 * Servlet implementation class ChkDeleteCartServlet
 */
@WebServlet(name = "ChkDeleteCart", urlPatterns = { "/chkDeleteCart" })
public class ChkDeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkDeleteCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2.보내온 값 추출
		String num = request.getParameter("num");
		System.out.println("chkdelsvletnum : " + num);
		//3.비즈니스 로직
		boolean result = new MemberService().chkDeleteCart(num);
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		 ArrayList<Cart> list = new MemberService().selectOneOrder(memberNo);
		
		//4.인코딩
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/cart.jsp");
		request.setAttribute("list", list);
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
