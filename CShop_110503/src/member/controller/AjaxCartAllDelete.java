package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class AjaxIdCheckServlet
 */
@WebServlet(name = "AjaxCartAllDelete", urlPatterns = { "/ajaxCartAllDelete" })
public class AjaxCartAllDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxCartAllDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		//view에서 전달받은 값 추출
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		System.out.println(memberNo);

		
		//결과처리
		int c = new MemberService().deleteAllCart(memberNo);
		ArrayList<Cart> list = new MemberService().selectOneOrder(memberNo);
		PrintWriter out = response.getWriter();
		session.setAttribute("m", m);
		request.setAttribute("list", list);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
