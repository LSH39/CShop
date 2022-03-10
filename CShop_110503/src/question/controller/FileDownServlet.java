package question.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.ContactService;
import question.model.vo.Contact;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.view에서 보낸 데이터 추출
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		//3.비즈니스로직처리
		Contact c = new ContactService().getContact(contactNo); 
		//4.화면처리
		//파일위치를 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice/";
		String file = saveDirectory+c.getFilepath(); //저장되어있는 이름
		System.out.println("다운로드 파일 전체 경로 : "+file);
		//서버의 물리공간에서 서블릿으로 파일을 읽어오는 객체
		FileInputStream fis = new FileInputStream(file);
		//파일을 읽어오는 속도를 개선하기위한 보조스트림
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		//클라이언트로 파일을 보내주는 객체
		ServletOutputStream sos = response.getOutputStream();
		//파일 전송속도를 개선하기위한 보조스트림
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		//브라우저에따른 파일이름 처리
		String resFilename = ""; //최종 다운로드할 파일이름
		//브라우저가 IE확인
		boolean bool =
				request.getHeader("user-agent").indexOf("MSIE") != -1 ||
				request.getHeader("user-agent").indexOf("Trident") != -1;
		System.out.println("IE 여부 : "+bool);
		if(bool) {//IE인 경우
			resFilename = URLEncoder.encode(c.getFilename(),"UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
		}else {//다른 브라우저인 경우
			resFilename = new String(c.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		}
		//파일다운로드를 위한 HTTP headert 설정
		//(사용자 브라우저에 파일다운로드임을 선언)
		response.setContentType("application/octet-stream");
		//다운로드할 파일 이름 지정
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		
		//파일전송
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bis.close();
		bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
