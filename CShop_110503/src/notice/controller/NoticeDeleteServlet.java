package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 보낸 데이터 추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//3.비즈니스로직처리
		NoticeService service = new NoticeService();
		//파일삭제는 따로 만들어야됌 - 순서중요
		Notice n = service.getNotice(noticeNo); //삭제 성공시 파일을 삭제하기위해 filepath를 알아오기위한거
		int result = service.deleteNotice(noticeNo);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(n.getFilepath()!=null) {//게시글 삭제를 성공하고, 해당 게시글에 첨부파일이 있는경우
				String root = getServletContext().getRealPath("/");//webcontent
				String file = root + "upload/notice/"+n.getFilepath();
				System.out.println(file);
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/notice?reqPage=1");
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/noticeView?noticeNo="+noticeNo);
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
