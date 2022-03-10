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
 * Servlet implementation class JoinFrmServlet
 */
@WebServlet("/cartFrm")
public class CartFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
	
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		 ArrayList<Cart> list = new MemberService().selectOneOrder(memberNo);
		
	System.out.println(list);
		
		//4. ���ó��
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