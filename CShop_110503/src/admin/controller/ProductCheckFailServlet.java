package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.ProductCheckPageData;

/**
 * Servlet implementation class ProductCheckFailServlet
 */
@WebServlet(name = "ProductCheckFail", urlPatterns = { "/productCheckFail" })
public class ProductCheckFailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCheckFailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3. 비즈니스로직
		ProductCheckPageData list = new AdminService().selectProductCheckFail(reqPage);
		
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/ProductCheckFail.jsp");
		request.setAttribute("list", list.getProduct());
		request.setAttribute("pageNavi", list.getPageNavi());
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
