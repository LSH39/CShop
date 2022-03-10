package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriterServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 업로드를 수행할 예정으로 enctype이 multipart/form-data인지 확인하는 코드
		if(!ServletFileUpload.isMultipartContent(request)) {//아닌경우
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성 오류 [enctype확인]");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		//파일업로드준비
		//1)파일업로드 경로 지정
		String root = getServletContext().getRealPath("/");//WebContent폴더의 경로를 가져옴
		System.out.println("root : "+root);
		String saveDirectory = root+"upload/notice";
		System.out.println("파일 저장경로 : " + saveDirectory);
		//2)업로드 파일의 최대크기 지정(일반적으로 웹의경우 10MB 정도 사용)
		int maxSize = 10*1024*1024;//(10MB를 byte 단위로 변환)
		//3)request객체를 MultipartRequest객체로 변환(변환하면서 파일이 서버에 업로드 됨)
		//MultipartRequest객체 생성 시 매개변수 총 5개
		//request객체,파일저장경로,파일최대크기,인코딩타입,파일중복처리객체
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",new DefaultFileRenamePolicy());
		//데이터를 추출해서 Notice객체로 저장
		Notice n = new Notice();
		//데이터 추출시 request로 추출하면 모두 null로 처리 -> mRequest로 추출해야함
		n.setNoticeTitle(mRequest.getParameter("noticeTitle"));
		n.setNoticeContent(mRequest.getParameter("noticeContent"));
		n.setFilename(mRequest.getOriginalFileName("upfile"));//사용자가 올린 파일이름
		n.setFilepath(mRequest.getFilesystemName("upfile"));//서버에 업로드된 파일이름
		//getOriginalFileName(),getFilesystemName()의 매개변수는
		//<input type="file" name="upfile"> -> name 속성값을 매개변수로
		System.out.println("original : "+n.getFilename());
		System.out.println("filesystem : "+n.getFilepath());
		//3.비즈니스로직처리
		int result = new NoticeService().insertNotice(n);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/notice?reqPage=1");
		if(result>0) {
			request.setAttribute("msg", "공지사항 등록 성공");
		}else {
			request.setAttribute("msg", "에러다");
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
