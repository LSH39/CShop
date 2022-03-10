package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellerService;
import seller.model.vo.InquiryPageData;

/**
 * Servlet implementation class ProductInquiryListServlet
 */
@WebServlet(name = "ProductInquiryList", urlPatterns = { "/productInquiryList" })
public class ProductInquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInquiryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int sellerNo = Integer.parseInt(request.getParameter("memberNo"));
		InquiryPageData ipd = new SellerService().selectInquiryList(reqPage, sellerNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/productInquiryList.jsp");
		request.setAttribute("list", ipd.getList());
		request.setAttribute("pageNavi", ipd.getPageNavi());
		request.setAttribute("start", ipd.getStart());
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
