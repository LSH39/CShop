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
@WebServlet("/kjoin")
public class KJoinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KJoinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("utf-8");
	
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
		m.setMemberId(request.getParameter("id"));
		m.setMemberPassword(request.getParameter("id"));
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
		
		
		//3.����Ͻ� ���� ����
		int result = new MemberService().insertsMember(m);
		//4. ���ó��
		if(result>0) {
			//ȸ������ ���� �� -> ����������
			response.sendRedirect("/");
		}else {
			//���н� -> ���� �˶� ��� �� ȸ������������ �̵�
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "ȸ�� ���Կ� �����߽��ϴ�.");
			request.setAttribute("loc", "/sJoinFrm");
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
