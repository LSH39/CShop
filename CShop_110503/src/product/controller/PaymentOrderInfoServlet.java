package product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.model.service.AdminService;

/**
 * Servlet implementation class PaymentOrderInfoServlet
 */
@WebServlet(name = "PaymentOrderInfo", urlPatterns = { "/paymentOrderInfo" })
public class PaymentOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentOrderInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// orderInfo
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String memberPhone = request.getParameter("memberPhone");
		String memberEmail = request.getParameter("memberEmail");
		String postcode = request.getParameter("postcode");
		String addressRoad = request.getParameter("addressRoad");
		String addressDetail = request.getParameter("addressDetail");
		int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		int deliveryPrice = Integer.parseInt(request.getParameter("deliveryPrice"));
		int orderPoint = Integer.parseInt(request.getParameter("orderPoint"));
		int paymentPrice = Integer.parseInt(request.getParameter("paymentPrice"));
		String deliveryRequest = request.getParameter("deliveryRequest");
		String impUid = request.getParameter("impUid");
		int repeat = Integer.parseInt(request.getParameter("repeat"));
		
		int[] order = new int[repeat];
		for(int i=0;i<repeat;i++) {
			int orderNo = new AdminService().paymentOrderInfo(i, memberNo, memberPhone, memberEmail, postcode,addressRoad, addressDetail, paymentMethod, orderPrice, deliveryPrice, orderPoint, paymentPrice, deliveryRequest,impUid);
			order[i] = orderNo;
		}
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// GSON 사용
		new Gson().toJson(order,out);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
