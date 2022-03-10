package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellerService;

/**
 * Servlet implementation class UpdateOrderServlet
 */
@WebServlet(name = "UpdateOrder", urlPatterns = { "/updateOrder" })
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		String deliveryCompany = request.getParameter("deliveryCompany");
		int deliveryNo = Integer.parseInt(request.getParameter("deliveryNo"));
		
		int result = new SellerService().updateOrder(orderNo,deliveryCompany,deliveryNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "등록성공");
		}else {
			request.setAttribute("msg", "등록실패");
		}
		request.setAttribute("loc", "/saleManage?reqPage=1&memberNo="+memberNo+"&orderStatus=4");
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
