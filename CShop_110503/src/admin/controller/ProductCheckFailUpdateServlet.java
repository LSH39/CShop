package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class ProductCheckFailUpdateServlet
 */
@WebServlet(name = "ProductCheckFailUpdate", urlPatterns = { "/productCheckFailUpdate" })
public class ProductCheckFailUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCheckFailUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 값추출
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int productStatus = Integer.parseInt(request.getParameter("productStatus"));
		// 3. 비즈니스로직
		int result = new AdminService().productCheckUpdate(productNo,productStatus);
		// 4. 결과처리
		if(result>0) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/ProductCheckFail.jsp");
			view.forward(request, response);
		}else {
			System.out.println("에러");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
