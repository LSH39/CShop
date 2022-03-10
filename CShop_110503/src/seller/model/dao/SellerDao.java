package seller.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;
import product.model.vo.ProductQNA;
import seller.model.vo.OrderInfo;
import seller.model.vo.OrderProduct;
import seller.model.vo.SellerPageData;

public class SellerDao {

	public ArrayList<OrderInfo> selectOrderList(Connection conn, int start, int end, int sellerNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
		String query = "select * from(select rownum as rnum, n.*from(select ORDER_NO, MEMBER_NO, PAYMENT_METHOD, ORDER_PRICE, DELIVERY_PRICE, ORDER_POINT, PAYMENT_PRICE, ORDER_DATE, ORDER_STATUS, SELLER_NO, MEMBER_NAME from order_info left join member using(MEMBER_NO) where seller_no=? order by order_no desc)n) where (rnum between ? and ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderInfo o = new OrderInfo();
				o.setOrderNo(rset.getInt("ORDER_NO"));
				o.setMemberNo(rset.getInt("MEMBER_NO"));
				o.setPaymentMethod(rset.getInt("PAYMENT_METHOD"));
				o.setOrderPrice(rset.getInt("ORDER_PRICE"));
				o.setDeliveryPrice(rset.getInt("DELIVERY_PRICE"));
				o.setOrderPoint(rset.getInt("ORDER_POINT"));
				o.setPaymentPrice(rset.getInt("PAYMENT_PRICE"));
				o.setOrderDate(rset.getString("ORDER_DATE"));
				o.setOrderStatus(rset.getInt("ORDER_STATUS"));
				o.setSellerNo(rset.getInt("SELLER_NO"));
				o.setMemberName(rset.getString("MEMBER_NAME"));
				list.add(o);
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

	public int selectTotalCount(Connection conn, int sellerNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from order_info where seller_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
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

	public ArrayList<OrderInfo> selectOrderList(Connection conn, int start, int end, int sellerNo, int orderStatus) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
		String query = "select * from(select rownum as rnum, n.*from(select ORDER_NO, MEMBER_NO, PAYMENT_METHOD, ORDER_PRICE, DELIVERY_PRICE, ORDER_POINT, PAYMENT_PRICE, ORDER_DATE, ORDER_STATUS, SELLER_NO, MEMBER_NAME from order_info left join member using(MEMBER_NO) where seller_no=? and order_status=? order by order_no desc)n) where (rnum between ? and ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			pstmt.setInt(2, orderStatus);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderInfo o = new OrderInfo();
				o.setOrderNo(rset.getInt("ORDER_NO"));
				o.setMemberNo(rset.getInt("MEMBER_NO"));
				o.setPaymentMethod(rset.getInt("PAYMENT_METHOD"));
				o.setOrderPrice(rset.getInt("ORDER_PRICE"));
				o.setDeliveryPrice(rset.getInt("DELIVERY_PRICE"));
				o.setOrderPoint(rset.getInt("ORDER_POINT"));
				o.setPaymentPrice(rset.getInt("PAYMENT_PRICE"));
				o.setOrderDate(rset.getString("ORDER_DATE"));
				o.setOrderStatus(rset.getInt("ORDER_STATUS"));
				o.setSellerNo(rset.getInt("SELLER_NO"));
				o.setMemberName(rset.getString("MEMBER_NAME"));
				list.add(o);
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

	public int selectTotalCount(Connection conn, int sellerNo, int orderStatus) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from order_info where seller_no=? and order_status=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			pstmt.setInt(2, orderStatus);
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

	public ArrayList<ProductQNA> selectInquiryList(Connection conn, int start, int end, int sellerNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductQNA> list = new ArrayList<ProductQNA>();
		String query = "select * from(select rownum as rnum, n.*from(select PQ_NO,PQ_WRITER, PQ_LEVEL,PQ_TITLE,PQ_CONTENT,PRODUCT_NO,PQ_REF,PQ_STATUS,PQ_DATE,PRODUCT_SELLER from PRODUCT_QNA left join PRODUCT using(PRODUCT_NO) where PRODUCT_SELLER=? and PQ_LEVEL=1 order by pq_no desc)n) where (rnum between ? and ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductQNA p = new ProductQNA();
				p.setPqNo(rset.getInt("PQ_NO"));
				p.setPqWriter(rset.getInt("PQ_WRITER"));
				p.setPqLevel(rset.getInt("PQ_LEVEL"));
				p.setPqTitle(rset.getString("PQ_TITLE"));
				p.setPqContent(rset.getString("PQ_CONTENT"));
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setPqRef(rset.getInt("PQ_REF"));
				p.setPqStatus(rset.getInt("PQ_STATUS"));
				p.setPqDate(rset.getString("PQ_DATE"));
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

	public int selectInquiryCount(Connection conn, int sellerNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from (select pq_no,PRODUCT_SELLER from PRODUCT_QNA left join PRODUCT using(PRODUCT_NO) where PRODUCT_SELLER=? and PQ_LEVEL=1)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sellerNo);
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

	public ProductQNA selectOneInquiry(Connection conn, int pqNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select PQ_NO,PQ_WRITER, PQ_LEVEL,PQ_TITLE,PQ_CONTENT,PRODUCT_NO,PQ_REF,PQ_STATUS,PQ_DATE,PRODUCT_NAME from PRODUCT_QNA left join PRODUCT using(PRODUCT_NO) where pq_no =?";
		ProductQNA pq = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pqNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				pq = new ProductQNA();
				pq.setPqNo(rset.getInt("PQ_NO"));
				pq.setPqWriter(rset.getInt("PQ_WRITER"));
				pq.setPqLevel(rset.getInt("PQ_LEVEL"));
				pq.setPqTitle(rset.getString("PQ_TITLE"));
				pq.setPqContent(rset.getString("PQ_CONTENT"));
				pq.setProductNo(rset.getInt("PRODUCT_NO"));
				pq.setPqRef(rset.getInt("PQ_REF"));
				pq.setPqStatus(rset.getInt("PQ_STATUS"));
				pq.setPqDate(rset.getString("PQ_DATE"));
				pq.setProductName(rset.getString("PRODUCT_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pq;
	}

	public ProductQNA selectOneAnswer(Connection conn, int pqNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select PQ_NO,PQ_WRITER, PQ_LEVEL,PQ_TITLE,PQ_CONTENT,PRODUCT_NO,PQ_REF,PQ_STATUS,PQ_DATE,PRODUCT_NAME from PRODUCT_QNA left join PRODUCT using(PRODUCT_NO) where PQ_REF=?";
		ProductQNA pa = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pqNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				pa = new ProductQNA();
				pa.setPqNo(rset.getInt("PQ_NO"));
				pa.setPqWriter(rset.getInt("PQ_WRITER"));
				pa.setPqLevel(rset.getInt("PQ_LEVEL"));
				pa.setPqTitle(rset.getString("PQ_TITLE"));
				pa.setPqContent(rset.getString("PQ_CONTENT"));
				pa.setProductNo(rset.getInt("PRODUCT_NO"));
				pa.setPqRef(rset.getInt("PQ_REF"));
				pa.setPqStatus(rset.getInt("PQ_STATUS"));
				pa.setPqDate(rset.getString("PQ_DATE"));
				pa.setProductName(rset.getString("PRODUCT_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pa;
	}

	public int insertAnswer(Connection conn, ProductQNA pq) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into PRODUCT_QNA values(PQ_SEQ.nextval,?,2,?,?,?,?,2,to_char(sysdate, 'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pq.getPqWriter());
			pstmt.setString(2, pq.getPqRef()+"answer");
			pstmt.setString(3, pq.getPqContent());
			pstmt.setInt(4, pq.getProductNo());
			pstmt.setInt(5, pq.getPqRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateInquriyAnswer(Connection conn, ProductQNA pq) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update PRODUCT_QNA set PQ_STATUS=2 where PQ_NO=? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pq.getPqRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateAnswer(Connection conn, ProductQNA pq) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update PRODUCT_QNA set PQ_CONTENT=? where PQ_REF=? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pq.getPqContent());
			pstmt.setInt(2, pq.getPqRef());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public OrderInfo selectOneOrder(Connection conn, int orderNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query="select ORDER_NO,MEMBER_NO,o.MEMBER_PHONE,o.MEMBER_EMAIL,POSTCODE,ADDRESS_ROAD,ADDRESS_DETAIL,PAYMENT_METHOD,ORDER_PRICE,DELIVERY_PRICE,ORDER_POINT,PAYMENT_PRICE,ORDER_DATE,ORDER_STATUS,SELLER_NO,DELIVERY_NO,DELIVERY_COMPANY,DELIVERY_REQUEST,MEMBER_NAME from order_info o left join member m using(MEMBER_NO) where ORDER_NO=?";
		OrderInfo oi = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				oi = new OrderInfo();
				oi.setOrderNo(rset.getInt("ORDER_NO"));
				oi.setMemberNo(rset.getInt("MEMBER_NO"));
				oi.setMemberPhone(rset.getString("MEMBER_PHONE"));
				oi.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				oi.setPostcode(rset.getString("POSTCODE"));
				oi.setAddressRoad(rset.getString("ADDRESS_ROAD"));
				oi.setAddressDetail(rset.getString("ADDRESS_DETAIL"));
				oi.setPaymentMethod(rset.getInt("PAYMENT_METHOD"));
				oi.setOrderPrice(rset.getInt("ORDER_PRICE"));
				oi.setDeliveryPrice(rset.getInt("DELIVERY_PRICE"));
				oi.setOrderPoint(rset.getInt("ORDER_POINT"));
				oi.setPaymentPrice(rset.getInt("PAYMENT_PRICE"));
				oi.setOrderDate(rset.getString("ORDER_DATE"));
				oi.setOrderStatus(rset.getInt("ORDER_STATUS"));
				oi.setSellerNo(rset.getInt("SELLER_NO"));
				oi.setDeliveryNo(rset.getInt("DELIVERY_NO"));
				oi.setDeliveryCompany(rset.getString("DELIVERY_COMPANY"));
				oi.setDeliveryRequest(rset.getString("DELIVERY_REQUEST"));
				oi.setMemberName(rset.getString("MEMBER_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return oi;
	}

	public ArrayList<OrderProduct> orderProductList(Connection conn, int orderNo, int start, int end) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderProduct> list = new ArrayList<OrderProduct>();
		String query = "select * from(select rownum as rnum, n.*from(select OP_NO,ORDER_NO,PRODUCT_NO,PRODUCT_COLOR,PRODUCT_SIZE,PRODUCT_PRICE,ORDER_COUNT,product_name from ORDER_PRODUCT left join product using(product_no) where order_no=? order by op_no desc)n) where (rnum between ? and ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderProduct op = new OrderProduct();
				op.setOpNo(rset.getInt("OP_NO"));
				op.setOrderNo(rset.getInt("ORDER_NO"));
				op.setProductNo(rset.getInt("PRODUCT_NO"));
				op.setProductColor(rset.getString("PRODUCT_COLOR"));
				op.setProductSize(rset.getString("PRODUCT_SIZE"));
				op.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				op.setOrderCount(rset.getInt("ORDER_COUNT"));
				op.setProductName(rset.getString("product_name"));
				list.add(op);
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

	public int selectOrderCount(Connection conn, int orderNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from (select * from ORDER_PRODUCT where ORDER_NO=?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
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

	public int updateOrder(Connection conn, int orderNo, String deliveryCompany, int deliveryNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update order_info set DELIVERY_COMPANY=?,DELIVERY_NO=?,ORDER_STATUS=2 where ORDER_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deliveryCompany);
			pstmt.setInt(2, deliveryNo);
			pstmt.setInt(3, orderNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectOneMembership(Connection conn, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int membership = 0;
		String query = "select membership from member where member_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				membership = rset.getInt("membership");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return membership;
	}
	
	public int deliveryComplete(Connection conn, int orderNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update order_info set ORDER_STATUS=3 where ORDER_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updatePoint(Connection conn, int memberNo, double upPoint) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into POINT values(POINT_SEQ.nextval,?,1,?,to_char(sysdate,'yy/mm/dd'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setDouble(2, upPoint);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateSeller(Connection conn, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;	
		String query = "update MEMBER set MEMBER_PASSWORD=?, MEMBER_PHONE=?, POSTCODE1=?, ADDRESS_ROAD1=?, ADDRESS_DETAIL1=?, POSTCODE2=?, ADDRESS_ROAD2=?, ADDRESS_DETAIL2=? where MEMBER_ID=?";
		                
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getPostcode1());
			pstmt.setString(4, member.getAddressRoad1());
			pstmt.setString(5, member.getAddressDetail1());
			pstmt.setString(6, member.getPostcode2());
			pstmt.setString(7, member.getAddressRoad2());
			pstmt.setString(8, member.getAddressDetail2());
			pstmt.setString(9, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteSeller(Connection conn, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;	
		String query = "update member set MEMBER_STATUS=2 where member_no=?";		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public SellerPageData deliveryCnt(Connection conn, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SellerPageData spd = null;
		String query = "select m.*, n.*, j.* from(select count(*) as cnt1 from  ORDER_INFO where ORDER_STATUS=1 and seller_no=?)m CROSS JOIN (select count(*) as cnt2 from  ORDER_INFO where ORDER_STATUS=2 and seller_no=?)n, (select count(*) as cnt3 from  ORDER_INFO where ORDER_STATUS=3 and seller_no=?)j";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				spd = new SellerPageData();
				spd.setPayCompleteCnt(rset.getInt("cnt1"));
				spd.setDeliveryingCnt(rset.getInt("cnt2"));
				spd.setDeliveredCnt(rset.getInt("cnt3"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return spd;
	}

	public SellerPageData orderToday(Connection conn, int memberNo, String today) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SellerPageData spd = null;
		String query="select m.*, n.* from (select count(*) as cnt from  ORDER_INFO where order_date=? and seller_no=?)m CROSS JOIN (select sum(order_price) as sum from ORDER_INFO where order_date=? and seller_no=?)n";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, today);
			pstmt.setInt(4, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				spd = new SellerPageData();
				spd.setOrderTodayCnt(rset.getInt("cnt"));
				spd.setOrderAmountSum(rset.getInt("sum"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return spd;
	}

	public int orderCount(Connection conn, String day, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from  ORDER_INFO where order_date=? and seller_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, day);
			pstmt.setInt(2, memberNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
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

	public int orderAmount(Connection conn, String day, int memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select sum(order_price) as sum from ORDER_INFO where order_date=? and seller_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, day);
			pstmt.setInt(2, memberNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("sum");
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
