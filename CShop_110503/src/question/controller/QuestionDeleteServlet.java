package question.controller;

import java.io.File;
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
 * Servlet implementation class QuestionDeleteServlet
 */
@WebServlet(name = "QuestionDelete", urlPatterns = { "/questionDelete" })
public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		//3.비즈니스로직처리
		ContactService service = new ContactService();
		//파일삭제는 따로 만들어야됌 - 순서중요
		Contact c = service.getContact(contactNo); //삭제 성공시 파일을 삭제하기위해 filepath를 알아오기위한거
		int result = service.deleteContact(contactNo);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(c.getFilepath()!=null) {//게시글 삭제를 성공하고, 해당 게시글에 첨부파일이 있는경우
				String root = getServletContext().getRealPath("/");//webcontent
				String file = root + "upload/contact/"+c.getFilepath();
				System.out.println(file);
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/question?reqPage=1");
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/questionView?contactNo="+contactNo);
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
