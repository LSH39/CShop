package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Order;
import member.model.vo.Shopping;
import member.model.vo.ShoppingList;

public class ShoppingListDao {

	public ArrayList<ShoppingList> selectShoppingList(Connection conn, int start, int end, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ShoppingList> list = new ArrayList<ShoppingList>();
		//글번호 역순 -> rownum으로 순번을 새로매겨줌 최신순 ( oi은 별칭이다!)
		String query = "select * from \r\n" + 
				"(select rownum as rnum, \r\n" + 
				"    oi.*,\r\n" + 
				"    (select count(*) from order_product op where op.order_no=oi.order_no)-1 as order_count,\r\n" + 
				"    (select product_name from product where product_no=(select product_no from order_product op where op.order_no=oi.order_no and product_price = (select max(product_price) from order_product))) as product_name,\r\n" + 
				"    (select product_image from product where product_no=(select product_no from order_product op where op.order_no=oi.order_no and product_price = (select max(product_price) from order_product))) as product_image\r\n" + 
				"from (select order_no,member_no,payment_price,order_date,order_status from order_info where member_no=? order by order_no desc)oi)";
		/*
		String query = "select * from \r\n" + 
				"(select rownum as rnum, \r\n" + 
				"    oi.*,\r\n" + 
				"    (select count(*) from order_product op where op.order_no=oi.order_no)-1 as order_count,\r\n" + 
				"    (select product_name from product where product_no=(select product_no from order_product op where op.order_no=oi.order_no and product_price = (select max(product_price) from order_product))) as product_name,\r\n" + 
				"    (select product_image from product where product_no=(select product_no from order_product op where op.order_no=oi.order_no and product_price = (select max(product_price) from order_product))) as product_image\r\n" + 
				"from (select order_no,member_no,payment_price,order_date,order_status from order_info where member_no=? order by order_no desc)oi) where rnum between ? and ?";
		*/
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			//pstmt.setInt(2,start);
			//pstmt.setInt(3,end);
			rset = pstmt.executeQuery();
			while(rset.next()){
				ShoppingList oi = new ShoppingList();
				oi.setMemberNo(rset.getInt("member_No"));
				oi.setOrderDate(rset.getString("order_Date"));
				oi.setProductName(rset.getString("product_Name"));
				oi.setProductImage(rset.getString("product_Image"));
				oi.setOrderNo(rset.getInt("order_No"));
				oi.setMemberNo(rset.getInt("member_No"));
				oi.setPaymentPrice(rset.getInt("payment_Price"));
				oi.setOrderStatus(rset.getInt("order_Status"));
				oi.setOrderCount(rset.getInt("order_Count"));
				list.add(oi);
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

	public ArrayList<Shopping> selectShoppingPro(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Shopping> slist = new ArrayList<Shopping>();
		String query = "select \r\n" + 
				"    op_no,\r\n" + 
				"    product_color,\r\n" + 
				"    product_size,\r\n" + 
				"    product_price,\r\n" + 
				"    order_count,\r\n" + 
				"    p.product_no,\r\n" + 
				"    product_name,\r\n" + 
				"    product_image,\r\n" + 
				"    (select member_name from member where member_no=p.product_seller) as member_name,\r\n" + 
				"    (select order_status from order_info where order_no=?)as order_status\r\n" + 
				"from order_product op \r\n" + 
				"left join product p on (op.product_no=p.product_no)\r\n" + 
				"where order_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, orderNo);			
			rset = pstmt.executeQuery();
			while(rset.next()){
				Shopping s = new Shopping();
				s.setOpNo(rset.getInt("op_no"));
				s.setProductColor(rset.getString("product_color"));
				s.setProductSize(rset.getString("product_size"));
				s.setProductPrice(rset.getInt("product_price"));
				s.setOrderCount(rset.getInt("order_count"));
				s.setProductNo(rset.getInt("product_no"));
				s.setProductName(rset.getString("product_name"));
				s.setProductImage(rset.getString("product_image"));
				s.setOrderStatus(rset.getInt("order_status"));
				//아래 필요없음!!!!!!!!!!!!!!!!!!!!!!!
				slist.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return slist;
	}

	public Order selectOrder(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Order o = null;
		String query = "select \r\n" + 
				"    order_no,\r\n" + 
				"    (select member_name from member where member_no=oi.member_no) as member_name,\r\n" + 
				"    member_phone,\r\n" + 
				"    postcode,\r\n" + 
				"    address_road,\r\n" + 
				"    address_detail,\r\n" + 
				"    order_price,\r\n" + 
				"    delivery_price,\r\n" + 
				"    order_point,\r\n" + 
				"    payment_price\r\n" + 
				"from order_info oi where order_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,orderNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new Order();
				o.setMemberName(rset.getString("member_Name"));
				o.setMemberPhone(rset.getString("member_Phone"));
				o.setPostcode(rset.getString("postcode"));
				o.setAddressRoad(rset.getString("address_road"));
				o.setAddressDetail(rset.getString("address_detail"));
				o.setOrderPrice(rset.getInt("order_price"));
				o.setPaymentPrice(rset.getInt("delivery_price"));
				o.setOrderPoint(rset.getInt("order_point"));
				o.setPaymentPrice(rset.getInt("payment_price"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return o;
	}

	public int getOrderCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int oc = 0;
		String query = "SELECT COUNT(*) as cnt FROM ORDER_INFO WHERE MEMBER_NO=? AND TO_DATE(ORDER_DATE,'YYYY-MM-DD') BETWEEN ADD_MONTHS(SYSDATE,-6) AND SYSDATE";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				oc = rset.getInt("cnt");
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return oc;
	}
}

