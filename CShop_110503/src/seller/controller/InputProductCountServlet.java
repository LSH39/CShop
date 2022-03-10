package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;


/**
 * Servlet implementation class InputProuctCountServlet
 */
@WebServlet(name = "InputProductCount", urlPatterns = { "/inputProductCount" })
public class InputProductCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputProductCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		
		
		int btnnum = Integer.parseInt(request.getParameter("btnnum"));
		String productId = request.getParameter("productId");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		
		//비즈니스 로직
		if(btnnum == 1) {
			String[] Count = request.getParameterValues("productCount");
			int[] productCount = new int[Count.length];
			for(int i=0; i<Count.length; i++) {
				productCount[i] = Integer.parseInt(Count[i]);
	        }
			String[] no = request.getParameterValues("optionNo");
			int[] optionNo = new int[no.length];
			for(int i=0; i<no.length; i++) {
				optionNo[i] = Integer.parseInt(no[i]);
	        }
			int result = new ProductService().updateProductCount(productCount, optionNo);
			//결과처리
			if(result>0) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("loc", "/selectProduct?memberNo="+memberNo+"&reqPage=1");
				request.setAttribute("msg", "등록성공. 관리자 승인 후 판매 가능합니다.");
				view.forward(request, response);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("loc", "/selectProduct?memberNo="+memberNo+"&reqPage=1");
				request.setAttribute("msg", "등록실패");
				view.forward(request, response);
			}
		}else if(btnnum == 2) {
			int result = new ProductService().deleteProduct2(productId);
			//결과처리
			if(result>0) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("loc", "/selectProduct?memberNo="+memberNo+"&reqPage=1");
				request.setAttribute("msg", "등록이 취소되었습니다.");
				view.forward(request, response);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("loc", "/selectProduct?memberNo="+memberNo+"&reqPage=1");
				request.setAttribute("msg", "취소 실패");
				view.forward(request, response);
			}
			
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







