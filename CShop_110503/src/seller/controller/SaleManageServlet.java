package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellerService;
import seller.model.vo.OrderPageData;

/**
 * Servlet implementation class SaleManageServlet
 */
@WebServlet(name = "SaleManage", urlPatterns = { "/saleManage" })
public class SaleManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleManageServlet() {
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
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		OrderPageData opd = new SellerService().selectOrderList(reqPage, sellerNo, orderStatus);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/saleManage.jsp");
		request.setAttribute("list", opd.getList());
		request.setAttribute("pageNavi", opd.getPageNavi());
		request.setAttribute("start", opd.getStart());
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
