package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.ShoppingList;
import product.model.vo.Product;
import product.model.vo.ProductReview;
import product.model.vo.ProductReviewList;

public class ReviewDao {

	public int insertReview(Connection conn, ProductReview pr) {
		PreparedStatement pstmt = null;
		int result = 0; //시퀀스 , 작성날짜, 작성자 , 별점, 내용,데이터,상품명,사용자 레벨
		String query = "insert into PRODUCT_REVIEW values(review_seq.nextval,?,?,1,?,?,?,null,to_char(sysdate,'yyyy-mm-dd'))";
		try { 
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, pr.getProductNo());
			pstmt.setString(2, pr.getPrWriter());
			pstmt.setString(3, pr.getPrContent());
			pstmt.setString(4, pr.getPrFilepath());
			pstmt.setInt(5, pr.getPrStar());						
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
//select * from (select rownum as rnum, p.* from(select * from product_review by 1 desc)p where rnum between 1 and 5;
	public ArrayList<ProductReview> moreReview(Connection conn, int start, int end,int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductReview> list = new ArrayList<ProductReview>();
		String query = "select * from (select rownum as rnum, p.* from (select * from product_review order by 1 desc)p) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductReview pr = new ProductReview();
				pr.setPrNo(rset.getInt("pr_no"));
				pr.setPrDate(rset.getString("pr_date"));
				pr.setPrWriter(rset.getString("pr_writer"));
				pr.setPrStar(rset.getInt("pr_star"));
				pr.setPrContent(rset.getString("pr_content"));
				pr.setPrFilepath(rset.getString("pr_filepath"));
				list.add(pr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//여기 물어봐야함
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product_review";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
//	public ProductReview selectOneReview(Connection conn, int prNo) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ProductReview pr = null;
//		String query = "select * from ProductReview where pr_no=?"; 
//		try {
//			pstmt=conn.prepareStatement(query);
//			pstmt.setInt(1, prNo);
//			rset = pstmt.executeQuery();
//			if(rset.next()) { 
//				pr = new ProductReview();
//				pr.setPrNo(rset.getInt("pr_No"));
//				pr.setPrDate(rset.getString("pr_Date"));
//				pr.setPrWriter(rset.getString("pr_Writer"));
//				pr.setPrStar(rset.getInt("pr_Star"));
//				pr.setPrContent(rset.getString("pr_Content"));
//				pr.setPrFilepath(rset.getString("pr_Filepath"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return pr;
//	}
	public ArrayList<ProductReviewList> selectProductReviewList(Connection conn,String prWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductReviewList> list = new ArrayList<ProductReviewList>();
		String query = "select \r\n" + 
				"    pr_no,product_no, pr_writer, pr_star, pr_date, pr_content, pr_filepath,\r\n" + 
				"    (select product_name from product where product_no=pr.product_no) as product_name\r\n" + 
				"from product_review pr where pr_writer=? order by pr_no desc";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prWriter);
			rset = pstmt.executeQuery();
			while(rset.next()){
				ProductReviewList prl = new ProductReviewList();
				prl.setPrNo(rset.getInt("pr_no"));
				prl.setProductNo(rset.getInt("product_no"));
				prl.setPrStar(rset.getInt("pr_star"));
				prl.setPrWriter(rset.getString("pr_writer"));
				prl.setPrContent(rset.getString("pr_content"));
				prl.setPrFilepath(rset.getString("pr_filepath"));
				prl.setPrDate(rset.getString("pr_date"));
				prl.setProductName(rset.getString("product_name"));
				list.add(prl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	public Product ReviewListView(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "select * from product where product_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return p;
	}
}
