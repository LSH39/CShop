package product.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import common.JDBCTemplate;
//import javafx.scene.layout.Background;
import member.model.vo.Cart;
import product.model.dao.ProductDao;
import product.model.vo.Product;
import product.model.vo.ProductOption;
import product.model.vo.ProductPageData;
import product.model.vo.ProductQNA;
import product.model.vo.ProductReview;

public class ProductService {

	public int inputProductInfo(Product p, ProductOption po, ArrayList<String> color, ArrayList<String> size) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().inputProductInfo(conn, p);
		int result2 = 0;
		if(result > 0) {
			result2 = new ProductDao().inputProductOption(conn,po,color,size);
			if(result2>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result2;
	}

	public ArrayList<ProductOption> selectAllOption(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductOption> poList = new ProductDao().selectAllOption(conn, productId);
		JDBCTemplate.close(conn);
		return poList;
	}

	public int updateProductCount(int[] productCount, int[] optionNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().updateProductCount(conn, productCount, optionNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	

	public ProductPageData selectProductList(int reqPage, int productCategory2) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 12개
		int numPerPage = 12;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<Product> pList = dao.selectProductList(conn,start,end,productCategory2);
		ArrayList<ProductOption> poList = dao.selectAllOption(conn);
		//전체 게시물의 수
		int totalCount = dao.selectTotalCount(conn, productCategory2);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productList?reqPage="+(pageNo-1)+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/productList?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		ProductPageData ppd= new ProductPageData(pList, poList, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public Product selectOneProduct(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		Product p = new ProductDao().selectOneProduct(conn, productId);
		JDBCTemplate.close(conn);
		return p;
	}

	public ArrayList<ProductOption> selectOneOption(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductOption> poList = new ProductDao().selectOneOption(conn, productId);
		JDBCTemplate.close(conn);
		return poList;
	}

	public String selectProductSellerId(int sellerNo) {
		Connection conn = JDBCTemplate.getConnection();
		String sellerId = new ProductDao().selectProductSellerId(conn, sellerNo); 
		JDBCTemplate.close(conn);
		return sellerId;
	}

	public ArrayList<String> selectProductColor(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> pColor = new ProductDao().selectProductColor(conn, productId);
		JDBCTemplate.close(conn);
		return pColor;
	}

	public ArrayList<String> selectProductSize(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> pSize = new ProductDao().selectProductSize(conn, productId);
		JDBCTemplate.close(conn);
		return pSize;
	}

	public ArrayList<ProductReview> selectAllReviews(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductReview> prList = new ProductDao().selectAllReviews(conn, productNo);
		JDBCTemplate.close(conn);
		return prList;
	}

	public int selectAverageStar(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		int averageStar = new ProductDao().selectAverageStar(conn, productNo);
		JDBCTemplate.close(conn);
		return averageStar;
	}

	public int insertReReview(ProductReview pr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().insertReReview(conn, pr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int selectReviewCount(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		int reviewCount = new ProductDao().selectReviewCount(conn, productNo);
		JDBCTemplate.close(conn);
		return reviewCount;
	}

	public int updateReview(int prNo, String prContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().updateReview(conn,  prNo, prContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteReview(int prNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().deleteReview(conn,  prNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ProductPageData selectReviewList(int reqPage, int productNo, String productId, int sellerNo) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 5개
		int numPerPage = 5;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<ProductReview> prList = dao.selectAllReviewsList(conn,start,end,productNo);
		//전체 게시물의 수
		int totalCount = dao.selectReviewCount(conn, productNo);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productReviewView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/productReviewView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link'  style='color:#5F755A' href='/productReviewView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productReviewView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		ProductPageData ppd= new ProductPageData(prList, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int selectQnaCount(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		int qnaCount = new ProductDao().selectQnaCount(conn, productNo);
		JDBCTemplate.close(conn);
		return qnaCount;
	}

	public ProductPageData selectQnaList(int productNo, int reqPage, String productId, int sellerNo) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 5개
		int numPerPage = 5;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<ProductQNA> pqList = dao.selectAllQnaList(conn,start,end,productNo);
		//전체 게시물의 수
		int totalCount = dao.selectQnaCount(conn, productNo);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productQnAView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/productQnAView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link'  style='color:#5F755A' href='/productQnAView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productQnAView?id="+productId+"&seller="+sellerNo+"&reqPage="+(pageNo)+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		int num = 1;
		ProductPageData ppd= new ProductPageData(pqList, pageNavi, start, num);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public ArrayList<ProductQNA> selectAllQna(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ProductQNA> pqList2 = new ProductDao().selectAllQna(conn, productNo);
		JDBCTemplate.close(conn);
		return pqList2;
	}

	public int inputQna(ProductQNA pq) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().inputQna(conn, pq);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertCart(Cart c) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().insertCart(conn, c);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ProductPageData selectProductAllList(int reqPage, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 12개
		int numPerPage = 10;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<Product> pList = dao.selectProductAllList(conn,start,end,memberNo);
		ArrayList<ProductOption> poList = dao.selectAllOption(conn);
		//전체 게시물의 수
		int totalCount = dao.selectAllTotalCount(conn, memberNo);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/selectProduct?memberNo="+memberNo+"&reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/selectProduct?memberNo="+memberNo+"&reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' style='color:#5F755A'  href='/selectProduct?memberNo="+memberNo+"&reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A'  href='/selectProduct?memberNo="+memberNo+"&reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		ProductPageData ppd= new ProductPageData(pList, poList, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int selectAllTotalCount(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new ProductDao().selectAllTotalCount(conn, memberNo);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public int selectProductPrice(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		int price = new ProductDao().selectProductPrice(conn, productId);
		JDBCTemplate.close(conn);
		return price;
	}

	public ProductPageData selectProductList2(int reqPage, int productCategory2) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 12개
		int numPerPage = 12;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<Product> pList = dao.selectProductList2(conn,start,end,productCategory2);
		ArrayList<ProductOption> poList = dao.selectAllOption(conn);
		//전체 게시물의 수
		int totalCount = dao.selectTotalCount2(conn, productCategory2);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productList2?reqPage="+(pageNo-1)+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/productList2?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList2?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList2?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		ProductPageData ppd= new ProductPageData(pList, poList, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public ProductPageData selectProductList3(int reqPage, int productCategory2) {
		Connection conn = JDBCTemplate.getConnection();
		//한페이지당 게시물 수 : 12개
		int numPerPage = 12;
		//시작 번호, 끝번호
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		//값 가져오기
		ProductDao dao = new ProductDao();
		ArrayList<Product> pList = dao.selectProductList3(conn,start,end,productCategory2);
		ArrayList<ProductOption> poList = dao.selectAllOption(conn);
		//전체 게시물의 수
		int totalCount = dao.selectTotalCount3(conn, productCategory2);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/productList3?reqPage="+(pageNo-1)+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/productList3?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList3?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A'  href='/productList3?reqPage="+pageNo+"&cate2="+(productCategory2)+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		ProductPageData ppd= new ProductPageData(pList, poList, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int updateProductInfo(Product p, ProductOption po, ArrayList<String> color, ArrayList<String> size) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().updateProductInfo(conn, p);
		int result2 = 0;
		int result3 = 0;
		if(result > 0) {
			result2 = new ProductDao().deleteProductOption(conn,p.getProductId());
			if(result2>0) {
				result3 = new ProductDao().inputProductOption(conn,po,color,size);
				if(result3>0) {
					JDBCTemplate.commit(conn);
				}
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result3;
	}

	public boolean deleteProduct(String delId) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = true;
		ProductDao dao = new ProductDao();
		StringTokenizer sT1 = new StringTokenizer(delId,"/");
		while(sT1.hasMoreTokens()) {
			String productId = sT1.nextToken();
			System.out.println(productId);
			int result1 = dao.deleteProduct(conn, productId); 
			if(result1 == 0) {
				result = false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

	}

	public int deleteProduct2(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().deleteProduct(conn,productId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int productCount2(String pColor, String pSize, String productId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProductDao().productCount2(conn, pColor, pSize, productId);
		JDBCTemplate.close(conn);
		return result;
	}



	
}


















