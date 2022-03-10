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
 * Servlet implementation class Joinservlet
 */
@WebServlet("/bjoin")
public class BJoinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BJoinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 보내온 값 추출
		String mail1 = request.getParameter("email1");
		String mail2 = request.getParameter("email2");
		String mail = mail1+"@"+mail2;
		System.out.println(mail);
		System.out.println(mail1);
		System.out.println(mail2);
		/*
		 * String birthyy = request.getParameter("birthyy"); String birthdd =
		 * request.getParameter("birthdd"); String birthmm =
		 * request.getParameter("birthmm"); String birth =
		 * birthyy+"-"+birthmm+"-"+birthdd;
		 */
		String memberPhone1 = request.getParameter("memberPhone1");
		String memberPhone2 = request.getParameter("memberPhone2");
		String memberPhone3 = request.getParameter("memberPhone3");
		String memberPhone = memberPhone1 + "-" + memberPhone2 + "-" + memberPhone3;
		Member m = new Member();
		m.setMemberId(request.getParameter("memberId"));
		m.setMemberPassword(request.getParameter("memberPw"));
		m.setMemberName(request.getParameter("memberName"));
		m.setMemberNickname(request.getParameter("memberNickname"));
		m.setMemberEmail(mail);
		m.setMemberBirthday(request.getParameter("birth"));
		m.setMemberPhone(memberPhone);
		m.setPostcode1(request.getParameter("postCode1"));
		m.setAddressRoad1(request.getParameter("addressRoad1"));
		m.setAddressDetail1(request.getParameter("addressDetail1"));
		m.setMemberReceiveSms(Integer.parseInt(request.getParameter("memberReceivesms")));
		m.setMemberReceiveEmail(Integer.parseInt(request.getParameter("memberReceiveemail")));
		
		//3.비즈니스 로직 수행
		int result = new MemberService().insertbMember(m);
		//4. 결과처리
		if(result>0) {
			//회원가입 성공 시 -> 메인페이지
			response.sendRedirect("/");
		}else {
			//실패시 -> 실패 알람 띄운 후 회원가입페이지 이동
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "회원 가입에 실패했습니다.");
			request.setAttribute("loc", "/joinFrm");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
