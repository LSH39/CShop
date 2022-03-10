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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name = "MemberUpdate", urlPatterns = { "/memberUpdate" })
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 보낸값추출
		Member member = new Member();
		member.setMemberPassword(request.getParameter("memberPassword"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberBirthday(request.getParameter("memberBirthday"));
		member.setMemberPhone(request.getParameter("memberPhone"));
		member.setMemberEmail(request.getParameter("memberEmail"));
		member.setPostcode1(request.getParameter("postcode1"));
		member.setAddressDetail1(request.getParameter("detailAddr1"));
		member.setAddressRoad1(request.getParameter("roadAddr1"));
		member.setPostcode2(request.getParameter("postcode2"));
		member.setAddressDetail2(request.getParameter("detailAddr2"));
		member.setAddressRoad2(request.getParameter("roadAddr2"));
		member.setMemberId(request.getParameter("memberId"));
		
		//3.비즈니스로직수행
		int result = new MemberService().updateMember(member);
		//4.결과처리
		RequestDispatcher view =request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/mypage");
		if(result>0) {
			request.setAttribute("msg", "정보변경 성공");
			//세션의데이터를 최신화하는코드
			Member m = new MemberService().selectOneMember(member.getMemberId(),member.getMemberPassword());
			HttpSession session = request.getSession(false);
			session.setAttribute("m", m);
		}else {
			request.setAttribute("msg", "정보변경 실패");
		}
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
