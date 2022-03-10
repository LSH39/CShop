package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;
import seller.model.vo.OrderInfo;
import seller.model.vo.OrderProduct;

/**
 * Servlet implementation class PaymentSuccessServlet
 */
@WebServlet(name = "PaymentSuccess", urlPatterns = { "/paymentSuccess" })
public class PaymentSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentSuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderNo = request.getParameter("orderNo");
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/PaymentSuccess.jsp");
		request.setAttribute("orderNo", orderNo);
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
