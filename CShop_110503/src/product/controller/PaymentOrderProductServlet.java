package product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import admin.model.service.AdminService;


/**
 * Servlet implementation class PaymentOrderProductServlet
 */
@WebServlet(name = "PaymentOrderProduct", urlPatterns = { "/paymentOrderProduct" })
public class PaymentOrderProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentOrderProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderProductData = request.getParameter("orderProductData");
		// JSON Parser
		JsonParser parser = new JsonParser();
		//JsonObject orderProduct = (JsonObject)parser.parse(orderProductData);
		//JsonObject sellerNo = (JsonObject)orderProduct.get("sellerNo");
		JsonArray opdlist = (JsonArray)parser.parse(orderProductData);
		
		//ArrayList<OrderProduct> list = new ArrayList<OrderProduct>();
		
		int result = 0;
		int[] order = new int[opdlist.size()];
		
		for(int i=0; i<opdlist.size(); i++) {
			
			JsonObject opd = (JsonObject)opdlist.get(i);
			int sellerNo = opd.get("sellerNo").getAsInt();
			int orderNo = opd.get("orderNo").getAsInt();
			int productNo = opd.get("productNo").getAsInt();
			String productColor = opd.get("productColor").getAsString();
			String productSize = opd.get("productSize").getAsString();
			int productPrice = opd.get("productPrice").getAsInt();
			int productCount = opd.get("productCount").getAsInt();
			
			result = new AdminService().paymentOrderProduct(sellerNo, orderNo, productNo, productColor, productSize, productPrice, productCount);
			order[i] = orderNo;
		}
		
		
		int[] success = null;
		if(result>0) {
			success = order;
		}else {
			success = null;
		}
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new Gson().toJson(success,out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
