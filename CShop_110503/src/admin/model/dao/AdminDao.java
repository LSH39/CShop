package admin.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;
import product.model.vo.Product;

public class AdminDao {

	public ArrayList<Member> selectJoinMember1List(Connection conn, int memberLevel, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM (SELECT * FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 1 ORDER BY MEMBER_NO)M) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberPhone(rset.getString("member_phone"));
				list.add(m);
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

	public ArrayList<Member> selectJoinMember2List(Connection conn, int memberLevel, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*, (SELECT COUNT(*) FROM ORDER_INFO O WHERE O.SELLER_NO= M.MEMBER_NO) AS SALE_PRODUCT FROM MEMBER M WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 1 ORDER BY M.MEMBER_NO) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setSaleProduct(rset.getInt("sale_product"));
				list.add(m);
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
	
	public int selectJoinTotalCount(Connection conn, int memberLevel) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
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

	public ArrayList<Member> selectRetireMemberList(Connection conn, int memberLevel, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM (SELECT * FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 2 ORDER BY MEMBER_NO)M) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setWithdrawDate(rset.getString("withdraw_date"));
				// 탈퇴일이 왜 조회가 안되는지...
				System.out.println("dao date  : "+rset.getString("withdraw_date"));
				list.add(m);
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

	
	public int selectRetireTotalCount(Connection conn, int memberLevel) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 2";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
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

	public int deleteRetireMember(Connection conn, int memberLevel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 2 AND TO_DATE(WITHDRAW_DATE,'YYYY-MM-DD') < ADD_MONTHS(SYSDATE,-6)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectDeleteRetireMember(Connection conn, int memberLevel) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM MEMBER WHERE MEMBER_LEVEL = ? AND MEMBER_STATUS = 2 AND TO_DATE(WITHDRAW_DATE,'YYYY-MM-DD') < ADD_MONTHS(SYSDATE,-6)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				list.add(m);
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

	public ArrayList<Member> selectUpdateMember1List(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.MEMBER_NO, M.MEMBER_NAME, (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) AS ORDER_COUNT, M.MEMBERSHIP FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) < 4 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberName(rset.getString("member_name"));
				m.setOrderCount(rset.getInt("order_count"));
				m.setMembership(rset.getInt("membership"));
				list.add(m);
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

	public ArrayList<Member> selectUpdateMember2List(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.MEMBER_NO, M.MEMBER_NAME, (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) AS ORDER_COUNT, M.MEMBERSHIP FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) BETWEEN 4 AND 9 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberName(rset.getString("member_name"));
				m.setOrderCount(rset.getInt("order_count"));
				m.setMembership(rset.getInt("membership"));
				list.add(m);
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
	public ArrayList<Member> selectUpdateMember3List(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.MEMBER_NO, M.MEMBER_NAME, (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) AS ORDER_COUNT, M.MEMBERSHIP FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) > 9 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberName(rset.getString("member_name"));
				m.setOrderCount(rset.getInt("order_count"));
				m.setMembership(rset.getInt("membership"));
				list.add(m);
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
	
	public int selectUpdateTotal1Count(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) < 4 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1";
		try {
			pstmt = conn.prepareStatement(query);
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

	public int selectUpdateTotal2Count(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) BETWEEN 4 AND 9 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1";
		try {
			pstmt = conn.prepareStatement(query);
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
	
	public int selectUpdateTotal3Count(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*)  AS CNT FROM MEMBER M WHERE (SELECT COUNT(CASE WHEN TO_DATE(O.ORDER_DATE) BETWEEN ADD_MONTHS(TO_DATE(SYSDATE),-6) AND SYSDATE THEN 1 END) FROM ORDER_INFO O WHERE O.MEMBER_NO=M.MEMBER_NO AND O.ORDER_STATUS=3) > 9 AND MEMBER_LEVEL = 1 AND MEMBER_STATUS = 1";
		try {
			pstmt = conn.prepareStatement(query);
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

	public ArrayList<Member> selectSaleSellerList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, PO.* FROM (SELECT P.PRODUCT_SELLER, SUM(O.ORDER_COUNT) AS TOTAL_COUNT FROM ORDER_PRODUCT O JOIN PRODUCT P ON P.PRODUCT_NO=O.PRODUCT_NO GROUP BY P.PRODUCT_SELLER) PO) WHERE RNUM BETWEEN ? AND ? ORDER BY TOTAL_COUNT DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("product_seller"));
				m.setSaleProduct(rset.getInt("total_count"));  // total_count
				list.add(m);
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

	public Product selectSaleSellerInfo(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, P1.* FROM (SELECT P.PRODUCT_SELLER, P.PRODUCT_NAME, P.PRODUCT_CATEGORY1,P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND, (SELECT COUNT(*) FROM ORDER_PRODUCT O WHERE P.PRODUCT_NO=O.PRODUCT_NO) AS SALE_COUNT FROM PRODUCT P WHERE PRODUCT_SELLER = ? ORDER BY SALE_COUNT DESC)P1) WHERE RNUM = 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Product();
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductName(rset.getString("product_name"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductSellCount(rset.getInt("sale_count"));
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

	public ArrayList<Product> selectProductCheckList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, SELECT1.* FROM (SELECT PRODUCT_NO, P.PRODUCT_NAME, (SELECT NVL(SUM(PRODUCT_COUNT),0) FROM PRODUCT_OPTION PO WHERE PO.PRODUCT_ID=P.PRODUCT_ID) AS SUM, M.MEMBER_NICKNAME, BUSINESS_NO, PRODUCT_FILE, PRODUCT_STATUS FROM PRODUCT P JOIN MEMBER M ON M.MEMBER_NO=P.PRODUCT_SELLER WHERE P.PRODUCT_STATUS=1) SELECT1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductSellCount(rset.getInt("sum"));  // 등록물품수
				p.setSellerName(rset.getString("member_nickname"));
				p.setBusinessNo(rset.getString("business_no"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductStatus(rset.getInt("product_status"));
				list.add(p);
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

	public int selectProductCheckCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM(SELECT PRODUCT_NO, P.PRODUCT_NAME, (SELECT SUM(PRODUCT_COUNT) FROM PRODUCT_OPTION PO WHERE PO.PRODUCT_ID=P.PRODUCT_ID) AS SUM, M.MEMBER_NICKNAME, BUSINESS_NO, PRODUCT_FILE, PRODUCT_STATUS FROM PRODUCT P JOIN MEMBER M ON M.MEMBER_NO=P.PRODUCT_SELLER WHERE P.PRODUCT_STATUS=1)";
		try {
			pstmt = conn.prepareStatement(query);
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

	public ArrayList<Product> selectProductCheckFailList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, SELECT1.* FROM (SELECT PRODUCT_NO, P.PRODUCT_NAME, (SELECT NVL(SUM(PRODUCT_COUNT),0) FROM PRODUCT_OPTION PO WHERE PO.PRODUCT_ID=P.PRODUCT_ID) AS SUM, M.MEMBER_NICKNAME, BUSINESS_NO, PRODUCT_FILE, PRODUCT_STATUS FROM PRODUCT P JOIN MEMBER M ON M.MEMBER_NO=P.PRODUCT_SELLER WHERE P.PRODUCT_STATUS=0) SELECT1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductSellCount(rset.getInt("sum"));  // 등록물품수
				p.setSellerName(rset.getString("member_nickname"));
				p.setBusinessNo(rset.getString("business_no"));
				p.setProductFile(rset.getString("product_file"));
				p.setProductStatus(rset.getInt("product_status"));
				list.add(p);
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

	public int selectProductCheckFailCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM(SELECT PRODUCT_NO, P.PRODUCT_NAME, (SELECT SUM(PRODUCT_COUNT) FROM PRODUCT_OPTION PO WHERE PO.PRODUCT_ID=P.PRODUCT_ID) AS SUM, M.MEMBER_NICKNAME, BUSINESS_NO, PRODUCT_FILE, PRODUCT_STATUS FROM PRODUCT P JOIN MEMBER M ON M.MEMBER_NO=P.PRODUCT_SELLER WHERE P.PRODUCT_STATUS=0)";
		try {
			pstmt = conn.prepareStatement(query);
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

	public int productCheckUpdate(Connection conn, int productNo, int productStatus) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PRODUCT SET PRODUCT_STATUS=? WHERE PRODUCT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productStatus);
			pstmt.setInt(2, productNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> selectSaleProductDaily(Connection conn, String selectDaily, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, SELECT1.* FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE=? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC) SELECT1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectDaily);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setrNum(rset.getInt("rnum"));
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductSellCount(rset.getInt("sell_count"));
				p.setSellerName(rset.getString("member_nickname"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductBrand(rset.getString("product_brand"));
				list.add(p);
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

	public ArrayList<Product> selectSaleProductMonth(Connection conn, String selectMonth, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, SELECT1.* FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE LIKE ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC) SELECT1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectMonth+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setrNum(rset.getInt("rnum"));
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductSellCount(rset.getInt("sell_count"));
				p.setSellerName(rset.getString("member_nickname"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductBrand(rset.getString("product_brand"));
				list.add(p);
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

	public ArrayList<Product> selectSaleProductPeriod(Connection conn, String selectPeriodStart, String selectPeriodEnd,int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, SELECT1.* FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE BETWEEN ? AND ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC) SELECT1) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectPeriodStart);
			pstmt.setString(2, selectPeriodEnd);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setrNum(rset.getInt("rnum"));
				p.setProductNo(rset.getInt("product_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductSellCount(rset.getInt("sell_count"));
				p.setSellerName(rset.getString("member_nickname"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductBrand(rset.getString("product_brand"));
				list.add(p);
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

	public Product selectProduct(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product p = null;
		String query = "SELECT M.MEMBER_NO, M.MEMBER_NICKNAME, P.PRODUCT_NO, P.PRODUCT_NAME, P.PRODUCT_IMAGE FROM MEMBER M JOIN PRODUCT P ON M.MEMBER_NO=P.PRODUCT_SELLER WHERE PRODUCT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setSellerName(rset.getString("member_nickname"));
				p.setProductName(rset.getString("product_name"));
				p.setProductImage(rset.getString("product_image"));
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

	public int paymentOrderInfo(Connection conn, int memberNo, String memberPhone, String memberEmail, String postcode, String addressRoad, String addressDetail, int paymentMethod, int orderPrice, int deliveryPrice, int orderPoint,int paymentPrice, String deliveryRequest, String impUid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_INFO VALUES (ORDER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD'),1,'','','',?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberEmail);
			pstmt.setString(4, postcode);
			pstmt.setString(5, addressRoad);
			pstmt.setString(6, addressDetail);
			pstmt.setInt(7, paymentMethod);
			pstmt.setInt(8, orderPrice);
			pstmt.setInt(9, deliveryPrice);
			pstmt.setInt(10, orderPoint);
			pstmt.setInt(11, paymentPrice);
			pstmt.setString(12, deliveryRequest);
			pstmt.setString(13, impUid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectPaymentOrderNo(Connection conn, int i, String impUid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int arr[] = new int[30];
		int j = 0;
		int result = 0;
		String query = "SELECT ORDER_NO FROM ORDER_INFO WHERE IMP_UID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, impUid);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("order_no");
				arr[j] = result;
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return arr[i];
	}

	public int paymentOrderInfoUpdate(Connection conn, int sellerNo, int orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE ORDER_INFO SET SELLER_NO=? WHERE ORDER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			pstmt.setInt(2, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int paymentOrderProduct(Connection conn, int orderNo, int productNo, String productColor, String productSize,
			int productPrice, int productCount) {

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_PRODUCT VALUES (OP_SEQ.NEXTVAL,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, productNo);
			pstmt.setString(3, productColor);
			pstmt.setString(4, productSize);
			pstmt.setInt(5, productPrice);
			pstmt.setInt(6, productCount);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectSaleCategoryDaily(Connection conn, String selectDaily) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE=? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectDaily);
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

	public int selectSaleCategoryDaily(Connection conn, String selectDaily, int i) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND ORDER_DATE=? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setString(2, selectDaily);
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

	public int selectSaleCategoryDailyDetail(Connection conn, String selectDaily, int i, int j) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND PRODUCT_CATEGORY2 = ? AND ORDER_DATE=? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setInt(2, j);
			pstmt.setString(3, selectDaily);
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

	public int selectSaleCategoryMonth(Connection conn, String selectMonth) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE LIKE ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectMonth+"%");
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

	public int selectSaleCategoryMonth(Connection conn, String selectMonth, int i) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND ORDER_DATE LIKE ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setString(2, selectMonth+"%");
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

	public int selectSaleCategoryMonthDetail(Connection conn, String selectMonth, int i, int j) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND PRODUCT_CATEGORY2 = ? AND ORDER_DATE LIKE ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setInt(2, j);
			pstmt.setString(3, selectMonth+"%");
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
	
	public int selectSaleCategoryPeriod(Connection conn,String selectPeriodStart, String selectPeriodEnd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE ORDER_DATE BETWEEN ? AND ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectPeriodStart);
			pstmt.setString(2, selectPeriodEnd);
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

	public int selectSaleCategoryPeriod(Connection conn, String selectPeriodStart, String selectPeriodEnd, int i) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND ORDER_DATE BETWEEN ? AND ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setString(2, selectPeriodStart);
			pstmt.setString(3, selectPeriodEnd);
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

	public int selectSaleCategoryPeriodDetail(Connection conn, String selectPeriodStart, String selectPeriodEnd, int i, int j) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) AS CNT FROM (SELECT OP.PRODUCT_NO,P.PRODUCT_NAME, SUM(OP.ORDER_COUNT) AS SELL_COUNT, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND FROM ORDER_INFO OI JOIN ORDER_PRODUCT OP ON OP.ORDER_NO=OI.ORDER_NO JOIN PRODUCT P ON P.PRODUCT_NO=OP.PRODUCT_NO JOIN MEMBER M ON M.MEMBER_NO = P.PRODUCT_SELLER WHERE PRODUCT_CATEGORY1 = ? AND PRODUCT_CATEGORY2 = ? AND ORDER_DATE BETWEEN ? AND ? GROUP BY OP.PRODUCT_NO,P.PRODUCT_NAME, M.MEMBER_NICKNAME, P.PRODUCT_CATEGORY1, P.PRODUCT_CATEGORY2, P.PRODUCT_BRAND ORDER BY SELL_COUNT DESC)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setInt(2, j);
			pstmt.setString(3, selectPeriodStart);
			pstmt.setString(4, selectPeriodEnd);
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

	

	public int selectMemberCount1(Connection conn, String month) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) FROM (SELECT M.MEMBER_NO, COUNT(O.ORDER_NO) AS CNT  FROM MEMBER M JOIN ORDER_INFO O ON O.MEMBER_NO=M.MEMBER_NO WHERE O.ORDER_DATE BETWEEN ? AND O.ORDER_DATE GROUP BY M.MEMBER_NO) WHERE CNT BETWEEN 4 AND 9";
		//String query = "SELECT COUNT(*) AS CNT FROM (SELECT M.MEMBER_NO, COUNT(O.ORDER_NO) AS CNT  FROM MEMBER M JOIN ORDER_INFO O ON O.MEMBER_NO=M.MEMBER_NO WHERE TO_DATE(O.ORDER_DATE) BETWEEN ? AND TO_DATE(O.ORDER_DATE) GROUP BY M.MEMBER_NO) WHERE CNT < 4";
		try {
			pstmt = conn.prepareStatement(query);
			//String str = "ADD_MONTHS(TO_DATE("+month+"),-6)";
			String str = "TO_CHAR(ADD_MONTHS(TO_DATE("+month+"-01),-6),'YYYY-MM')";
			pstmt.setString(1, str);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
			System.out.println("result : "+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectUpdateMemberDo(Connection conn, int choiceNo, String memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBER SET MEMBERSHIP=? WHERE MEMBER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, choiceNo);
			pstmt.setString(2, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}



	
	
	

}
