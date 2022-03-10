package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Cart;
import member.model.vo.Member;
import member.model.vo.Point;


public class MemberDao {

	public Member selectOneMember(Connection conn, String memberId, String memberPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=? and member_password=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				System.out.println(memberId);
				System.out.println(memberPw);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int insertbMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,null,null,null,'1','0',?,?,'null','1','1',to_char(sysdate,'yyyy-mm-dd'),null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberEmail());
			pstmt.setString(3, m.getMemberPassword());
			pstmt.setString(4, m.getMemberName());
			pstmt.setString(5, m.getMemberNickname());
			pstmt.setString(6, m.getMemberPhone());
			pstmt.setString(7, m.getMemberBirthday());
			pstmt.setInt(8, m.getMemberGender());
			pstmt.setString(9, m.getPostcode1());
			pstmt.setString(10, m.getAddressRoad1());
			pstmt.setString(11, m.getAddressDetail1());
			pstmt.setInt(12, m.getMemberReceiveEmail());
			pstmt.setInt(13, m.getMemberReceiveSms());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int insertsMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,null,null,null,'1','0',?,?,?,'2','1',to_char(sysdate,'yyyy-mm-dd'),null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberEmail());
			pstmt.setString(3, m.getMemberPassword());
			pstmt.setString(4, m.getMemberName());
			pstmt.setString(5, m.getMemberNickname());
			pstmt.setString(6, m.getMemberPhone());
			pstmt.setString(7, m.getMemberBirthday());
			pstmt.setInt(8, m.getMemberGender());
			pstmt.setString(9, m.getPostcode1());
			pstmt.setString(10, m.getAddressRoad1());
			pstmt.setString(11, m.getAddressDetail1());
			pstmt.setInt(12, m.getMemberReceiveEmail());
			pstmt.setInt(13, m.getMemberReceiveSms());
			pstmt.setString(14, m.getBusinessNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Member selectOneMemberNickname(Connection conn, String memberNickname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_nickname=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNickname);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Member selectOneMemberEmail(Connection conn, String memberEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_email=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberEmail);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Cart selectOneMemberOrder(Connection conn, int memberNo, String cart) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart c = null;
		String query = "select c.*,m.member_nickname as nick from cart c join member m on m.member_no=c.product_seller where c.member_no=? and cart_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, cart);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				c = new Cart();
				c.setProductNo(rset.getInt("product_No"));
				c.setMemberNo(rset.getInt("member_no"));
				c.setProductColor(rset.getString("product_color"));
				c.setProductSize(rset.getString("product_size"));
				c.setProductPrice(rset.getInt("product_price"));
				c.setProductSeller(rset.getInt("product_seller"));
				c.setProductName(rset.getString("product_name"));
				c.setProductImage(rset.getString("product_image"));
				c.setOrderAmount(rset.getInt("order_amount"));
				c.setCartNo(rset.getInt("cart_no"));
				// 아래내용 추가 + 쿼리문 수정
				c.setSellerName(rset.getString("nick"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("카트출력 끝");
		return c;
	}
	
	
	public ArrayList<Cart> selectOneMemberOrder(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		String query = "select c.*,m.member_nickname as nick from cart c join member m on m.member_no=c.product_seller where c.member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			System.out.println("카트 출력");
			while(rset.next()) {
				System.out.println("카트 출력시작");
				Cart c = new Cart();
				c.setProductNo(rset.getInt("product_No"));
				c.setMemberNo(rset.getInt("member_no"));
				c.setProductColor(rset.getString("product_color"));
				c.setProductSize(rset.getString("product_size"));
				c.setProductPrice(rset.getInt("product_price"));
				c.setProductSeller(rset.getInt("product_seller"));
				c.setProductName(rset.getString("product_name"));
				c.setProductImage(rset.getString("product_image"));
				c.setOrderAmount(rset.getInt("order_amount"));
				c.setCartNo(rset.getInt("cart_no"));
				// 아래내용 추가 + 쿼리문 수정
				c.setSellerName(rset.getString("nick"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("카트출력 끝");
		return list;
	}

	public Member selectOneMemberBno(Connection conn, String business) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_no=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, business);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public Member selectOneMemberPw(Connection conn, String memberEmail, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_email=? and member_id=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPassword(rset.getString("member_password"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNickname(rset.getString("member_nickname"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberBirthday(rset.getString("member_birthday"));
				member.setMemberGender(rset.getInt("member_gender"));
				
				member.setPostcode1(rset.getString("postcode1"));
				member.setAddressRoad1(rset.getString("address_road1"));
				member.setAddressDetail1(rset.getString("address_detail1"));
				
				member.setPostcode2(rset.getString("postcode2"));
				member.setAddressRoad2(rset.getString("address_road2"));
				member.setAddressDetail2(rset.getString("address_detail2"));
				
				member.setMembership(rset.getInt("membership"));
				member.setMemberPoint(rset.getInt("member_point"));
				member.setMemberReceiveEmail(rset.getInt("member_receive_email"));
				member.setMemberReceiveSms(rset.getInt("member_receive_sms"));
				
				member.setBusinessNo(rset.getString("business_no"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setMemberStatus(rset.getInt("member_status"));
				member.setEnrollDate(rset.getString("enroll_date"));
				member.setWithdrawDate(rset.getString("withdraw_date"));
				
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public int deleteOneCart(Connection conn, int checked) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart where cart_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, checked);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(checked);
		return result;
	}

	public Cart selectOneCart(Connection conn, String productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart cart = null;
		String query = "select * from cart where product_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productNo);
			rset = pstmt.executeQuery();
			System.out.println("결제 출력");
			while(rset.next()) {
				System.out.println("결제 출력시작");
				Cart c = new Cart();
				c.setProductNo(rset.getInt("product_No"));
				c.setMemberNo(rset.getInt("member_no"));
				c.setProductColor(rset.getString("product_color"));
				c.setProductSize(rset.getString("product_size"));
				c.setProductPrice(rset.getInt("product_price"));
				c.setProductSeller(rset.getInt("product_seller"));
				c.setProductName(rset.getString("product_name"));
				c.setProductImage(rset.getString("product_image"));
				c.setOrderAmount(rset.getInt("order_amount"));
				c.setCartNo(rset.getInt("cart_no"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("결제 전송 끝");
		return cart;
	
	}

	public int deleteAllCart(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(memberNo);
		return result;
	}

	public int selectAllPrice(Connection conn, int num) {
		PreparedStatement pstmt = null;
		int cart = 0;
		ResultSet rset = null;
		String query = "select sum(product_price) from cart where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				cart =(rset.getInt("sum(product_price)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(num);
		return cart;
	}

	public int selectChkPrice(Connection conn, int num) {
		PreparedStatement pstmt = null;
		int cart = 0;
		ResultSet rset = null;
		String query = "select product_price from cart where cart_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				cart =(rset.getInt("product_price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return cart;
		
	}

	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no=?";
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

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_password=?,member_name=?,member_birthday=?,member_phone=?,member_email=?,postcode1=?,address_road1=?,address_detail1=?,postcode2=?,address_road2=?,address_detail2=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberBirthday());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getPostcode1());
			pstmt.setString(7, member.getAddressRoad1());
			pstmt.setString(8, member.getAddressDetail1());
			pstmt.setString(9, member.getPostcode2());
			pstmt.setString(10, member.getAddressRoad2());
			pstmt.setString(11, member.getAddressDetail2());
			pstmt.setString(12, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Point> PointList(Connection conn,int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Point> list = new ArrayList<Point>();
		String query = "select * from point where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Point p = new Point();
				p.setMemberNo(rset.getInt("member_no"));
				p.setPointLevel(rset.getInt("point_level"));
				p.setPointValue(rset.getInt("point_value"));
				p.setPointDate(rset.getString("point_date"));
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
	
	}

