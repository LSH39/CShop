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
 * Servlet implementation class InquiryViewServlet
 */
@WebServlet(name = "InquiryView", urlPatterns = { "/inquiryView" })
public class InquiryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pqNo = Integer.parseInt(request.getParameter("pqNo"));
		//3. 비즈니스로직 
		ProductQNA pq = new SellerService().selectOneInquiry(pqNo);
		ProductQNA pa = new SellerService().selectOneAnswer(pqNo);
		//4. 결과처리
		if(pq != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/inquiryView.jsp");
			request.setAttribute("pq", pq);
			request.setAttribute("pa", pa);
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회오류");
			request.setAttribute("loc", "/sellerPage");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
