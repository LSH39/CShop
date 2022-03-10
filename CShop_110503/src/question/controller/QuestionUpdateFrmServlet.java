package question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.ContactService;
import question.model.vo.Contact;


/**
 * Servlet implementation class QuestionUpdateFrmServlet
 */
@WebServlet(name = "QuestionUpdateFrm", urlPatterns = { "/questionUpdateFrm" })
public class QuestionUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		//3.비즈니스로직처리
		Contact c = new ContactService().getContact(contactNo);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/question/questionUpdateFrm.jsp");
		request.setAttribute("c", c);
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
