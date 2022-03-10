package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class IdSearchServlet
 */
@WebServlet(name = "IdSearch", urlPatterns = { "/idSearch" })
public class IdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.view에서 보낸 데이터 추출
		String memberEmail = request.getParameter("email");
		System.out.println("중복체크 할 이메일 :" + memberEmail);
		//3. 비즈니스로직
		Member member = new MemberService().selectOneMemberEmail(memberEmail);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/searchidresult.jsp");
		if(member == null) {
			// 입력받은 아이디로 사용자 없는 경우
			String memberid = "존재하지 않습니다.";
			request.setAttribute("result", false);
			request.setAttribute("memberid", memberid);
			System.out.println("가져올 id(x) : "+ memberid);
			System.out.println(member);
		}else {
			// 입력받은 아이디로 사용자가 있는 경우
			String memberid = member.getMemberId();
			request.setAttribute("result", true);
			request.setAttribute("memberid", memberid);
			System.out.println("가져올 id(o) :" + memberid);
		}
		
				view.forward(request, response);
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
