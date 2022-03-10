package seller.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductOption;

/**
 * Servlet implementation class UpdateProductInfoServlet
 */
@WebServlet(name = "UpdateProductInfo", urlPatterns = { "/updateProductInfo" })
public class UpdateProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ----파일처리
		// 1. 파일저장경로
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/product";
		// 2. 파일최대크기
		int maxSize = 10 * 1024 * 1024;
		// 3. MultipartRequest객체로 변환(파일업로드)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		// -----값 추출
		// 1. 필요한 객체 생성		
		Product p = new Product();
		ProductOption po = new ProductOption();
		String productId = mRequest.getParameter("productId");
		int memberNo = Integer.parseInt(mRequest.getParameter("memberNo"));
		// 2. 값 추출하여 객체에 입력
		// 2-1. Product 객체 값 입력
		p.setProductId(productId);
		p.setProductCategory1(Integer.parseInt(mRequest.getParameter("productCategory1")));
		p.setProductCategory2(Integer.parseInt(mRequest.getParameter("productCategory2")));
		p.setProductName(mRequest.getParameter("productName"));
		p.setProductContent(mRequest.getParameter("productContent"));
		p.setProductBrand(mRequest.getParameter("productBrand"));
		p.setProductImports(Integer.parseInt(mRequest.getParameter("productImports")));
		//이미지 저장
		p.setProductImage(mRequest.getFilesystemName("productImage"));
		String oldFilepath = mRequest.getParameter("oldFilepath");
		int status = Integer.parseInt(mRequest.getParameter("status"));
		if(status == 2) {  		//기존 파일을 지운경우
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilepath != null) {
			p.setProductImage(oldFilepath);
		}
		// 2-2 ProductOption 객체 값 입력		
		po.setProductId(productId);
		po.setProductPrice(Integer.parseInt(mRequest.getParameter("productPrice")));
		// 2-3 color, size 토크나이저로 잘라서 ArrayList에 저장
		String productColor = mRequest.getParameter("productColor");
		String productSize = mRequest.getParameter("productSize");
		ArrayList<String> color = new ArrayList<String>();
		ArrayList<String> size = new ArrayList<String>();
		StringTokenizer colorTn = new StringTokenizer(productColor, ",");
		StringTokenizer sizeTn = new StringTokenizer(productSize, ",");
		while (colorTn.hasMoreElements()) {
			color.add(colorTn.nextToken());
		}
		while (sizeTn.hasMoreElements()) {
			size.add(sizeTn.nextToken());
		}
		// ----비즈니스 로직
		// 1. Product, productOption DB수정 (update)
		int result = new ProductService().updateProductInfo(p, po, color, size);
		ArrayList<ProductOption> poList = new ProductService().selectAllOption(productId);
		if (result > 0) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/UpdateProductCount.jsp");
			request.setAttribute("poList", poList);
			request.setAttribute("color", color);
			request.setAttribute("size", size);
			request.setAttribute("memberNo", memberNo);
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("loc", "/");
			request.setAttribute("msg", "수정실패!");
			view.forward(request, response);
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
