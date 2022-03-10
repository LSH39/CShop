package question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
//import member.modal.service.MemberService;
import member.model.vo.Member;
import question.model.service.ContactService;
import question.model.vo.Contact;
import question.model.vo.ContactViewData;

/**
 * Servlet implementation class QuestionViewServlet
 */
@WebServlet(name = "QuestionView", urlPatterns = { "/questionView" })
public class QuestionViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		
		//3.비즈니스로직처리
		ContactViewData cvd = new ContactService().selectOneContact(contactNo);
		
		
		if(cvd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/question/questionView.jsp");
			request.setAttribute("c", cvd.getC());
			request.setAttribute("list", cvd.getList());
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "오륜데 어디서 났냐?");
			request.setAttribute("loc", "/question?reqPage=1");
			view.forward(request, response);
		}
		//Member member = new MemberService().selectOneMember(memberId,memberPw);
		//4.화면처리
		//HttpSession session = request.getSession();
		//session.setAttribute("check", member);
		//Member check = (Member)session.getAttribute("check");
		/*
		if(cvd.getC().getContactWriter()==check.getMemberId()) {
			if(cvd != null) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/question/questionView.jsp");
				request.setAttribute("c", cvd.getC());
				request.setAttribute("list", cvd.getList());
				view.forward(request, response);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "오륜데 어디서 났냐?");
				request.setAttribute("loc", "/question?reqPage=1");
				view.forward(request, response);
			}
//		}else {
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
//			request.setAttribute("msg", "작성자와 관리자만 볼수있습니다.");
//			request.setAttribute("loc", "/question?reqPage=1");
//			view.forward(request, response);
//		}
 * * */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
