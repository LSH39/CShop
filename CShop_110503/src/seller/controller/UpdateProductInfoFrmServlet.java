package seller.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class UpdateProductInfoFrmServlet
 */
@WebServlet(name = "UpdateProductInfoFrm", urlPatterns = { "/updateProductInfoFrm" })
public class UpdateProductInfoFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductInfoFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		Product p = new ProductService().selectOneProduct(productId);
		int price = new ProductService().selectProductPrice(productId);
		ArrayList<String> pcList = new ProductService().selectProductColor(productId);
		String pColor = String.join(",", pcList);
		ArrayList<String> psList = new ProductService().selectProductSize(productId);
		String pSize = String.join(",", psList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/UpdateProductInfo.jsp");
		request.setAttribute("p", p);
		request.setAttribute("price", price);
		request.setAttribute("pColor", pColor);
		request.setAttribute("pSize", pSize);
		request.setAttribute("memberNo", memberNo);
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
