package product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Order;
import member.model.vo.OrderShopping;
import product.model.dao.ReviewDao;
import product.model.vo.Product;
import product.model.vo.ProductReview;
import product.model.vo.ProductReviewList;

public class ReviewService {

	public int insertReview(ProductReview pr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ReviewDao().insertReview(conn,pr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);	
		return result;
	}
	public int totalCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new ReviewDao().totalCount(conn);
		JDBCTemplate.close(conn);
		return totalCount;
	}
	public ArrayList<ProductReviewList> selectReviewList(String prWriter) {
		Connection conn = JDBCTemplate.getConnection();	
		ReviewDao dao = new ReviewDao();
		ArrayList<ProductReviewList> list = dao.selectProductReviewList(conn,prWriter);
		JDBCTemplate.close(conn);
		return list;
	}
	public Product reviewListView(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		Product p = new ReviewDao().ReviewListView(conn,productNo);
		return p;
	}

//	public ProductReview selectOneReview(int prNo) {
//		Connection conn = JDBCTemplate.getConnection();
//		ReviewDao dao = new ReviewDao();
//		ProductReview pr = dao.selectOneReview(conn,prNo);
//		JDBCTemplate.rollback(conn);
//		JDBCTemplate.close(conn);
//		return pr;
//	}
//	public ArrayList<ProductReviewList> selectReviewList(int memberNo) {
//		Connection conn = JDBCTemplate.getConnection();
//		int numPerPage = 5;
//		int end = reqPage*numPerPage;
//		int start = end - numPerPage + 1;
//		ReviewDao dao = new ReviewDao();
//		ArrayList<ProductReviewList> list = dao.selectProductReview(conn,start,end,prWriter);
//		JDBCTemplate.close(conn);
//		return list;
//	}
}
