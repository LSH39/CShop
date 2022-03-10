package seller.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellerService;
import seller.model.vo.OrderViewData;



/**
 * Servlet implementation class OrderManageServlet
 */
@WebServlet(name = "OrderManage", urlPatterns = { "/orderManage" })
public class OrderManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		OrderViewData ovd = new SellerService().orderManage(orderNo, memberNo);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/orderManage.jsp");
		request.setAttribute("oi", ovd.getOi());
		request.setAttribute("list", ovd.getList());
		request.setAttribute("pageNavi", ovd.getPageNavi());
		request.setAttribute("start", ovd.getStart());
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
