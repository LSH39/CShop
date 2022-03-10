package seller.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductPageData;

/**
 * Servlet implementation class SelectProductServlet
 */
@WebServlet(name = "SelectProduct", urlPatterns = { "/selectProduct" })
public class SelectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		ProductPageData ppd = new ProductService().selectProductAllList(reqPage, memberNo);
		int productTotal = new ProductService().selectAllTotalCount(memberNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/SelectProduct.jsp");
		
		request.setAttribute("pList", ppd.getpList());
		request.setAttribute("pageNavi", ppd.getPageNavi());
		request.setAttribute("start", ppd.getStart());
		request.setAttribute("productTotal", productTotal);
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
