package question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.ContactService;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet(name = "UpdateComment", urlPatterns = { "/updateComment" })
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.view에서 보낸 데이터 추출
		int ctNo = Integer.parseInt(request.getParameter("ctNo"));
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		String ctContent = request.getParameter("ctContent");
		//3.비즈니스로직처리
		int result = new ContactService().updateComment(ctNo,ctContent);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정 성공");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/questionView?contactNo="+contactNo);
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
