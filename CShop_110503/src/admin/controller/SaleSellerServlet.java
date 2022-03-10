package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.SalePageData;
import product.model.vo.Product;

/**
 * Servlet implementation class SaleSellerServlet
 */
@WebServlet(name = "SaleSeller", urlPatterns = { "/saleSeller" })
public class SaleSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleSellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 값추출
		//int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3. 비즈니스로직
		ArrayList<SalePageData> spdlist = new AdminService().selectSaleSellerList();
		
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/SaleSeller.jsp");
		for(int i=0; i<spdlist.size(); i++) {
			request.setAttribute("memberNo"+i, spdlist.get(i).getMemberNo());
			request.setAttribute("totalSale"+i, spdlist.get(i).getTotalSale());
			request.setAttribute("p"+i, spdlist.get(i).getP());			
		}
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
