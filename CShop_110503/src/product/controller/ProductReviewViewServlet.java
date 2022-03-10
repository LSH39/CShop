package product.controller;

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
import product.model.vo.ProductOption;
import product.model.vo.ProductPageData;
import product.model.vo.ProductReview;

/**
 * Servlet implementation class ProductReviewViewServlet
 */
@WebServlet(name = "ProductReviewView", urlPatterns = { "/productReviewView" })
public class ProductReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductReviewViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String productId = request.getParameter("id");
		int sellerNo = Integer.parseInt(request.getParameter("seller"));	
		
		//비즈니스 로직
		Product p = new ProductService().selectOneProduct(productId);
		int productNo = p.getProductNo();
		ArrayList<ProductOption> poList = new ProductService().selectOneOption(productId);
		String sellerId = new ProductService().selectProductSellerId(sellerNo);
		ArrayList<String> pColor = new ProductService().selectProductColor(productId);
		ArrayList<String> pSize = new ProductService().selectProductSize(productId);
		ProductPageData ppd = new ProductService().selectReviewList(reqPage, productNo, productId, sellerNo);
		ArrayList<ProductReview> prList2 = new ProductService().selectAllReviews(productNo);
		int averageStar = new ProductService().selectAverageStar(productNo); 
		int reviewCount = new ProductService().selectReviewCount(productNo); 
		int qnaCount = new ProductService().selectQnaCount(productNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/ProductReview.jsp");
		request.setAttribute("p", p);
		request.setAttribute("poList", poList);
		request.setAttribute("sellerId", sellerId);
		request.setAttribute("pColor", pColor);
		request.setAttribute("pSize", pSize);
		request.setAttribute("prList", ppd.getPrList());
		request.setAttribute("prList2", prList2);
		request.setAttribute("pageNavi", ppd.getPageNavi());
		request.setAttribute("start", ppd.getStart());
		request.setAttribute("averageStar", averageStar);
		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("qnaCount", qnaCount);
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
