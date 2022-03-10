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
@WebServlet(name = "AjaxEmailCheck", urlPatterns = { "/ajaxEmailCheck" })
public class AjaxEmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxEmailCheckServlet() {
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
		//
		String memberEmail = request.getParameter("memberEmail");
		Member m = new MemberService().selectOneMemberEmail(memberEmail);
		PrintWriter out = response.getWriter();
		if (m == null) {
			// 이메일 조회 안됐을때
			
			out.print(0);
		} else {
			// 조회 됐을때
			out.print(1);
		}
		System.out.println(memberEmail);
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
