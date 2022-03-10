package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.service.MainSearchPageData;
import main.model.service.MainService;
import notice.model.service.NoticeService;
import notice.model.vo.NoticePageData;

/**
 * Servlet implementation class MainSearchProductServlet
 */
@WebServlet(name = "MainSearchProduct", urlPatterns = { "/mainSearchProduct" })
public class MainSearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		//아직 안넘겨준상태 - null이 들어와서 숫자로바꿀려다가 오류남
		//null 이면 1로 예외처리로해서 처리함
		int reqPage = 0;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e){
			System.out.println("reqPage 예외발생");
			reqPage=1;
		}
		//reqPage는 매개변수로 전달된 경우 -> 전달된 값
		//reqPage가 매개변수로 전달되지 않는경우 -> 1
		
		//3.비즈니스로직처리
		//페이징처리->arraylist,string
		MainSearchPageData mspd = new MainService().searchSearchProduct(reqPage,search);
		
		//4.화면처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/main/searchProductList.jsp");
		request.setAttribute("list", mspd.getList());
		request.setAttribute("pageNavi", mspd.getPageNavi());
		request.setAttribute("start", mspd.getStart());
		request.setAttribute("search", search);
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
