package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인서블릿시작");
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.데이터추출
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		//3.비즈니스 로직
		Member member = new MemberService().selectOneMember(memberId,memberPw);
		//4. 결과 처리
		if(member != null) {
			//세션 생성
			HttpSession session = request.getSession();
			//세션 등록
			session.setAttribute("m", member);
			//페이지 이동
			response.sendRedirect("/");
			
		}else {
			//alert에 띄울 메세지는 msg에 등록
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			//msg.jsp는 alert를 화면에 띄운 후 페이지를 이동하는 기능을 가진 jsp
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
			//이동할 페이지를 loc에 등록
			request.setAttribute("loc", "/");
			//페이지이동
			view.forward(request, response);
		}
		System.out.println("로그인 서블릿 끝");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
