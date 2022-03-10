package question.controller;

import java.io.File;
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

import question.model.service.ContactService;
import question.model.vo.Contact;

/**
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet(name = "QuestionUpdate", urlPatterns = { "/questionUpdate" })
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.view에서 보낸 데이터 추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype 오류");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		
//		1. 기존 첨부파일이 있는경우
//		1-1삭제만한경우
//		1-2삭제하고 새 파일을 업로드 하는경우
//		1-3삭제안하고 기존파일 유지하는 경우
//
//		2. 기존 첨부파일이 없는경우
//		2-1그대로 첨부파일이 없는경우
//		2-2새 첨부파일이 있는경우
		
		//1)파일업로드 경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/notice";
		//2)파일업로드 최대 크기 지정(10MB)
		int maxSize = 10*1024*1024;
		//3)request->MultipartRequest로 변환(파일이 업로드되는 시점)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy()); 
		
		Contact c = new Contact();
		c.setContactNo(Integer.parseInt(mRequest.getParameter("contactNo")));
		c.setContactTitle(mRequest.getParameter("contactTitle"));
		c.setContactContent(mRequest.getParameter("contactContent"));
		
		//파일명 저장(새로운 파일이 업로드되면 새로운 파일정보, 새로운 파일이 업로드 되지않으면 null); 
		//1-1 null null변경
		//1-2 새 파일명 새 파일명
		//1-3 null null변경
		//2-1 null null 그대로
		//2-2 새 파일명 입력
		c.setFilename(mRequest.getOriginalFileName("upfile"));
		c.setFilepath(mRequest.getFilesystemName("upfile"));
		
		//기존 파일명/파일경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		
		//삭제여부 판단용 값
		int status = Integer.parseInt(mRequest.getParameter("status"));
		
		if(status==2) { //기존파일을 지운경우
			//1-1 파일삭제
			//1-2 파일삭제
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename !=null) {
			//1-3 null null -> 기존이름으로 변경
			c.setFilename(oldFilename);
			c.setFilepath(oldFilepath);
		}
		
		//3.비즈니스로직처리
		int result = new ContactService().updateContact(c);
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "정보 수정 완료");
		}else {
			request.setAttribute("msg", "정보 수정 실패");
		}
		request.setAttribute("loc", "/questionView?contactNo="+c.getContactNo());
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
