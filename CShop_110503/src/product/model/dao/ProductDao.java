package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Cart;
import product.model.vo.Product;
import product.model.vo.ProductOption;
import product.model.vo.ProductQNA;
import product.model.vo.ProductReview;
//import sun.management.jdp.JdpBroadcaster;

public class ProductDao {

	public int inputProductInfo(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into product values(product_seq.nextval,?,?,?,?,?,?,?,?,1,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, p.getProductId());
			pstmt.setInt(i++, p.getProductSeller());
			pstmt.setInt(i++, p.getProductCategory1());
			pstmt.setInt(i++, p.getProductCategory2());
			pstmt.setString(i++, p.getProductName());
			pstmt.setString(i++, p.getProductContent());
			pstmt.setString(i++, p.getProductBrand());
			pstmt.setInt(i++, p.getProductImports());
			pstmt.setString(i++, p.getProductImage());
			pstmt.setString(i++, p.getProductFile());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int inputProductOption(Connection conn, ProductOption po, ArrayList<String> color, ArrayList<String> size) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into product_option values(OPTION_SEQ.nextval,?,?,?,?,1)";
		try {
			for(int i=0;i<color.size();i++) {
				for(int j=0;j<size.size();j++) {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, po.getProductId());
					pstmt.setString(2, color.get(i));
					pstmt.setString(3, size.get(j));
					pstmt.setInt(4, po.getProductPrice());
					result = pstmt.executeUpdate();					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ProductOption> selectAllOption(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductOption> poList = new ArrayList<ProductOption>();
		String query = "select * from product_option where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductOption po = new ProductOption();
				po.setOptionNo(rset.getInt("option_no"));
				po.setProductId(rset.getString("product_id"));
				po.setProductColor(rset.getString("product_color"));
				po.setProductSize(rset.getString("product_size"));
				po.setProductPrice(rset.getInt("product_price"));
				po.setProductCount(rset.getInt("product_count"));
				poList.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return poList;
	}

	public int updateProductCount(Connection conn, int[] productCount, int[] optionNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update product_option set product_count = ? where option_no = ?";
		try {
			for(int i=0;i<optionNo.length;i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, productCount[i]);
				pstmt.setInt(2, optionNo[i]);
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> selectProductList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = new ArrayList<Product>();
		String query = "SELECT * FROM(SELECT ROWNUM RNUM, P.* FROM(SELECT * FROM PRODUCT JOIN (SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION) USING (PRODUCT_ID)ORDER BY PRODUCT_NO DESC)P) WHERE rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductPrice(rset.getInt("product_price"));
				pList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}

	public ArrayList<ProductOption> selectAllOption(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductOption> poList = new ArrayList<ProductOption>();
		String query = "select * from product_option";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductOption po = new ProductOption();
				po.setOptionNo(rset.getInt("option_no"));
				po.setProductId(rset.getString("product_id"));
				po.setProductColor(rset.getString("product_color"));
				po.setProductSize(rset.getString("product_size"));
				po.setProductPrice(rset.getInt("product_price"));
				po.setProductCount(rset.getInt("product_count"));
				poList.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return poList;
	}

	public int selectTotalCount(Connection conn, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product where product_category1=1 and product_category2 = ? and product_status = 2";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> selectProductList(Connection conn, int start, int end, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = new ArrayList<Product>();
		String query = "SELECT * FROM(SELECT ROWNUM RNUM, P.* FROM(SELECT * FROM PRODUCT PRO JOIN (SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION) USING (PRODUCT_ID) WHERE PRO.PRODUCT_CATEGORY1 = 1 AND PRO.PRODUCT_CATEGORY2 = ? AND PRO.PRODUCT_STATUS = 2 ORDER BY PRODUCT_NO DESC)P)  WHERE rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductPrice(rset.getInt("product_price"));
				pList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}

	public Product selectOneProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "select * from product where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return p;
	}

	public ArrayList<ProductOption> selectOneOption(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductOption> poList = new ArrayList<ProductOption>();
		String query = "select * from product_option where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductOption po = new ProductOption();
				po.setOptionNo(rset.getInt("option_no"));
				po.setProductId(rset.getString("product_id"));
				po.setProductColor(rset.getString("product_color"));
				po.setProductSize(rset.getString("product_size"));
				po.setProductPrice(rset.getInt("product_price"));
				po.setProductCount(rset.getInt("product_count"));
				poList.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return poList;
	}

	public String selectProductSellerId(Connection conn, int sellerNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sellerId = "";
		String query = "select member_id from member where member_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				sellerId = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return sellerId;
	}

	public ArrayList<String> selectProductColor(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> pColor = new ArrayList<String>();
		String query = "select DISTINCT product_color from PRODUCT_OPTION where product_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pColor.add(rset.getString("product_color"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pColor;
	}

	public ArrayList<String> selectProductSize(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> pSize = new ArrayList<String>();
		String query = "select DISTINCT product_size from PRODUCT_OPTION where product_id = ? ORDER BY PRODUCT_SIZE desc";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pSize.add(rset.getString("product_size"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pSize;
	}

	public ArrayList<ProductReview> selectAllReviews(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductReview> prList = new ArrayList<ProductReview>();
		String query = "select * from product_review where product_no = ? order by pr_no DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductReview pr = new ProductReview();
				pr.setPrNo(rset.getInt("pr_no"));
				pr.setProductNo(rset.getInt("product_no"));
				pr.setPrWriter(rset.getString("pr_writer"));
				pr.setPrLevel(rset.getInt("pr_level"));
				pr.setPrContent(rset.getString("pr_content"));
				pr.setPrFilepath(rset.getString("pr_filepath"));
				pr.setPrStar(rset.getInt("pr_star"));
				pr.setPrRef(rset.getInt("pr_ref"));
				pr.setPrDate(rset.getNString("pr_date"));
				prList.add(pr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return prList;
	}

	public int selectAverageStar(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int averageStar = 0;
		String query = "select ROUND(avg(pr_star),0) star from product_review where product_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				averageStar = rset.getInt("star");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return averageStar;
	}

	public int insertReReview(Connection conn, ProductReview pr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into product_review values(pr_seq.nextval, ?, ?, ?, ?, null , null ,?,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pr.getProductNo());
			pstmt.setString(2, pr.getPrWriter());
			pstmt.setInt(3, pr.getPrLevel());
			pstmt.setString(4, pr.getPrContent());
			pstmt.setInt(5, pr.getPrRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectReviewCount(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int reviewCount = 0;
		String query = "select count(*) cnt from product_review where product_no = ? and pr_level = 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				reviewCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return reviewCount;
	}

	public int updateReview(Connection conn, int prNo, String prContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="update product_review set pr_content = ? where pr_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prContent);
			pstmt.setInt(2, prNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteReview(Connection conn, int prNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from product_review where pr_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, prNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ProductReview> selectAllReviewsList(Connection conn, int start, int end, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductReview> prList = new ArrayList<ProductReview>();
		String query = "select prr.* from (select rownum rnum, pr.* from (select * from PRODUCT_REVIEW where pr_level = 1 and product_no = ? order by pr_no desc)pr)prr where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductReview pr = new ProductReview();
				pr.setPrNo(rset.getInt("pr_no"));
				pr.setProductNo(rset.getInt("product_no"));
				pr.setPrWriter(rset.getString("pr_writer"));
				pr.setPrLevel(rset.getInt("pr_level"));
				pr.setPrContent(rset.getString("pr_content"));
				pr.setPrFilepath(rset.getString("pr_filepath"));
				pr.setPrStar(rset.getInt("pr_star"));
				pr.setPrRef(rset.getInt("pr_ref"));
				pr.setPrDate(rset.getNString("pr_date"));
				prList.add(pr);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return prList;
	}

	public int selectQnaCount(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int qnaCount = 0;
		String query = "select count(*) cnt from product_qna where pq_level=1 and product_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				qnaCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qnaCount;
	}

	public ArrayList<ProductQNA> selectAllQnaList(Connection conn, int start, int end, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductQNA> pqList = new ArrayList<ProductQNA>();
		String query = "select pqq.* from (select rownum rnum, pq.* from(select pq.*, member_id from product_qna pq join member on (member_no=pq_writer) where pq_level=1 and product_no=? order by pq_no desc)pq)pqq where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  productNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductQNA pq = new ProductQNA();
				pq.setPqNo(rset.getInt("pq_no"));
				pq.setPqWriter(rset.getInt("pq_writer"));
				pq.setPqLevel(rset.getInt("pq_level"));
				pq.setPqTitle(rset.getString("pq_title"));
				pq.setPqContent(rset.getString("pq_content"));
				pq.setProductNo(rset.getInt("product_no"));
				pq.setPqRef(rset.getInt("pq_ref"));
				pq.setPqStatus(rset.getInt("pq_status"));
				pq.setPqDate(rset.getString("pq_date"));
				pq.setPqWriterId(rset.getString("member_id"));
				pqList.add(pq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pqList;
	}

	public ArrayList<ProductQNA> selectAllQna(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductQNA> pqList2 = new ArrayList<ProductQNA>();
		String query = "select * from product_qna where product_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductQNA pq = new ProductQNA();
				pq.setPqNo(rset.getInt("pq_no"));
				pq.setPqWriter(rset.getInt("pq_writer"));
				pq.setPqLevel(rset.getInt("pq_level"));
				pq.setPqTitle(rset.getString("pq_title"));
				pq.setPqContent(rset.getString("pq_content"));
				pq.setProductNo(rset.getInt("product_no"));
				pq.setPqRef(rset.getInt("pq_ref"));
				pq.setPqStatus(rset.getInt("pq_status"));
				pq.setPqDate(rset.getString("pq_date"));
				pqList2.add(pq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pqList2;
	}

	public int inputQna(Connection conn, ProductQNA pq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into PRODUCT_QNA values(pq_seq.nextval,?,1,?,?,?,null,1,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pq.getPqWriter());
			pstmt.setString(2, pq.getPqTitle());
			pstmt.setString(3, pq.getPqContent());
			pstmt.setInt(4, pq.getProductNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertCart(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into cart values(cart_seq.nextval,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setInt(i++, c.getProductNo());
			pstmt.setInt(i++, c.getMemberNo());
			pstmt.setString(i++, c.getProductColor());
			pstmt.setString(i++, c.getProductSize());
			pstmt.setInt(i++, c.getProductPrice());
			pstmt.setInt(i++, c.getProductSeller());
			pstmt.setString(i++, c.getProductName());
			pstmt.setString(i++, c.getProductImage());
			pstmt.setInt(i++, c.getOrderAmount());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> selectProductAllList(Connection conn, int start, int end, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = new ArrayList<Product>();
		String query = "SELECT * FROM(SELECT ROWNUM RNUM, P.* FROM(SELECT * FROM PRODUCT PRO JOIN (SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION) USING (PRODUCT_ID) WHERE PRO.PRODUCT_SELLER = ? ORDER BY PRODUCT_NO DESC)P)  WHERE rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductPrice(rset.getInt("product_price"));
				pList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}

	public int selectAllTotalCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product where product_seller = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectProductPrice(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int price = 0;
		String query = "SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION where product_id =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				price = rset.getInt("product_price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return price;
	}

	public ArrayList<Product> selectProductList2(Connection conn, int start, int end, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = new ArrayList<Product>();
		String query = "SELECT * FROM(SELECT ROWNUM RNUM, P.* FROM(SELECT * FROM PRODUCT PRO JOIN (SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION) USING (PRODUCT_ID) WHERE PRO.PRODUCT_CATEGORY1 = 2 AND PRO.PRODUCT_CATEGORY2 = ? AND PRO.PRODUCT_STATUS = 2 ORDER BY PRODUCT_NO DESC)P)  WHERE rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductPrice(rset.getInt("product_price"));
				pList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}

	public int selectTotalCount2(Connection conn, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product where product_category1=2 and product_category2 = ? and product_status = 2";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> selectProductList3(Connection conn, int start, int end, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> pList = new ArrayList<Product>();
		String query = "SELECT * FROM(SELECT ROWNUM RNUM, P.* FROM(SELECT * FROM PRODUCT PRO JOIN (SELECT DISTINCT PRODUCT_ID, PRODUCT_PRICE FROM PRODUCT_OPTION) USING (PRODUCT_ID) WHERE PRO.PRODUCT_CATEGORY1 = 3 AND PRO.PRODUCT_CATEGORY2 = ? AND PRO.PRODUCT_STATUS = 2 ORDER BY PRODUCT_NO DESC)P)  WHERE rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductPrice(rset.getInt("product_price"));
				pList.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}

	public int selectTotalCount3(Connection conn, int productCategory2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product where product_category1=3 and product_category2 = ? and product_status = 2";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productCategory2);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateProductInfo(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PRODUCT SET PRODUCT_CATEGORY1 = ?, PRODUCT_CATEGORY2 = ?,PRODUCT_NAME = ?, PRODUCT_CONTENT = ?, PRODUCT_BRAND = ?, PRODUCT_IMPORTS = ?, PRODUCT_IMAGE = ? WHERE product_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setInt(i++, p.getProductCategory1());
			pstmt.setInt(i++, p.getProductCategory2());
			pstmt.setString(i++, p.getProductName());
			pstmt.setString(i++, p.getProductContent());
			pstmt.setString(i++, p.getProductBrand());
			pstmt.setInt(i++, p.getProductImports());
			pstmt.setString(i++, p.getProductImage());
			pstmt.setString(i++, p.getProductId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteProductOption(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE from product_option where product_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE from product where product_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int productCount2(Connection conn, String pColor, String pSize, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select PRODUCT_COUNT pcount from PRODUCT_OPTION where PRODUCT_COLOR = ? and PRODUCT_SIZE = ? and PRODUCT_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pColor);
			pstmt.setString(2, pSize);
			pstmt.setString(3, productId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("pcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	

}























