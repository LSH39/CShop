package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxIdCheckServlet
 */
@WebServlet(name = "AjaxbNoCheck", urlPatterns = { "/ajaxbNoCheck" })
public class AjaxbNoCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxbNoCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.인코딩
			
		request.setCharacterEncoding("utf-8");
		//
		String business = request.getParameter("business");
		Member m = new MemberService().selectOneMemberBno(business);
		System.out.println(business);
		PrintWriter out = response.getWriter();
		if (m == null) {
			// 사용가능한 아이디
			out.print(0);
		} else {
			// 사용중인 아이디
			out.print(1);
		}
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
