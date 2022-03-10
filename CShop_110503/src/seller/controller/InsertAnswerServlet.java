package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.vo.ProductQNA;
import seller.model.service.SellerService;

/**
 * Servlet implementation class InsertAnswerServlet
 */
@WebServlet(name = "InsertAnswer", urlPatterns = { "/insertAnswer" })
public class InsertAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductQNA pq = new ProductQNA();
		pq.setPqWriter(Integer.parseInt(request.getParameter("pqWriter")));
		pq.setPqContent(request.getParameter("pqContent"));
		pq.setProductNo(Integer.parseInt(request.getParameter("productNo")));
		pq.setPqRef(Integer.parseInt(request.getParameter("pqRef")));
		pq.setPqStatus(Integer.parseInt(request.getParameter("pqStatus")));
		int result=0;
		
		if(pq.getPqStatus() == 1) {
			result = new SellerService().insertAnswer(pq);
		}else {
			result = new SellerService().updateAnswer(pq);
			result++;
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>1) {
			request.setAttribute("msg", "등록성공");
		}else {
			request.setAttribute("msg", "등록실패");
		}
		request.setAttribute("loc", "/inquiryView?pqNo="+pq.getPqRef());
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
