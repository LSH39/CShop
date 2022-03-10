package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.ProductPageData;

/**
 * Servlet implementation class ProductList2Servlet
 */
@WebServlet(name = "ProductList2", urlPatterns = { "/productList2" })
public class ProductList2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int productCategory2 = Integer.parseInt(request.getParameter("cate2"));
		//비즈니스 로직
		ProductPageData ppd = new ProductService().selectProductList2(reqPage, productCategory2);
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/ProductList2.jsp");
		request.setAttribute("pList", ppd.getpList());
		request.setAttribute("poList", ppd.getPoList());
		request.setAttribute("pageNavi", ppd.getPageNavi());
		request.setAttribute("start", ppd.getStart());
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
