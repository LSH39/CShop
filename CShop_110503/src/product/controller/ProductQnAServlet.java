package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.ProductQNA;

/**
 * Servlet implementation class ProductQnAServlet
 */
@WebServlet(name = "ProductQnA", urlPatterns = { "/productQnA" })
public class ProductQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductQnAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		ProductQNA pq = new ProductQNA();
		pq.setPqWriter(Integer.parseInt(request.getParameter("pqWriter")));
		pq.setPqTitle(request.getParameter("pqTitle"));
		pq.setPqContent(request.getParameter("pqContent"));
		pq.setProductNo(Integer.parseInt(request.getParameter("productNo")));
		String productId = request.getParameter("productId");
		int sellerNo = Integer.parseInt(request.getParameter("productSeller"));	
		
		int result = new ProductService().inputQna(pq);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {		
			request.setAttribute("msg", "문의등록 성공");
		}else {
			request.setAttribute("msg", "문의등록 실패");
		}
		request.setAttribute("loc", "/productQnAView?id="+productId+"&seller="+sellerNo+"&reqPage=1");
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
