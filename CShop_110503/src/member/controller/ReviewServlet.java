package member.controller;

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

import product.model.service.ReviewService;
import product.model.vo.ProductReview;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet(name = "Review", urlPatterns = { "/review" })
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		//파일업로드를 수행할 예정으로 enctype이 multipart/form-data인지 확인하는 코드
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "리뷰 작성 오류 [enctype확인]");
			request.setAttribute("loc", "/ReviewList");
			rd.forward(request, response);
			return;
		}
		//파일업로드 준비
		//1) 파일업로드 경로 지정
		String root = getServletContext().getRealPath("/");//WebContent폴더의 경로를 가져옴
		String savaDirectory = root+"upload/review";
		System.out.println("파일저장경로 : "+savaDirectory);
		//2)업로  드 파일의 최대 크기 지정(일반적으로 웹의경우 10MB 정보 사용)
		int maxSize = 10*1024*1024; // (10MB byte 단위로 변환)
		//3)request객체를 MultipartRequest객체로 변환(변환하면서 파일이 서버에 업로드 됨)
		//MultipartRequest객체 생성시 매개변수 총 5개
		//request객체 . 파일저장경로 , 파일최대크기, 인코딩타입, 파일 중복 처리 객체
		
		
		//[질문] 1 
		MultipartRequest mRequest
		= new MultipartRequest(request, savaDirectory, maxSize,"UTF-8",new DefaultFileRenamePolicy());
		//데이터추출해서 review객체로 저장합시다!
		ProductReview  pr = new ProductReview();		
		pr.setProductNo(Integer.parseInt(mRequest.getParameter("productNo")));
		pr.setPrWriter(mRequest.getParameter("prWriter"));
		pr.setPrStar(Integer.parseInt(mRequest.getParameter("star")));
		pr.setPrContent(mRequest.getParameter("reviewContent"));
		pr.setPrFilepath(mRequest.getFilesystemName("img")); //업로드파일용
		//3.비즈니스로직
		int result = new ReviewService().insertReview(pr);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc","/reviewList");
		if(result>0) {
			request.setAttribute("msg", "리뷰 남겨주셔서 감사합니다~");
		}else {
			request.setAttribute("msg", "ㅠㅠ소중한데이터가 날라가셨어요");
	}	
		request.setAttribute("loc","/reviewList");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
