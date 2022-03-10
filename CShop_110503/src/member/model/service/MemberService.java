package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Cart;
import member.model.vo.Member;
import member.model.vo.Point;

public class MemberService {

	public Member selectOneMember(String memberId, String memberPw) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn,memberId,memberPw); 
		JDBCTemplate.close(conn);
		
		return member;
		
	}

	public int insertbMember(Member m) {
Connection conn = JDBCTemplate.getConnection();
int result = new MemberDao().insertbMember(conn,m);
if(result>0) {
	JDBCTemplate.commit(conn);
}else {
	JDBCTemplate.rollback(conn);
}
JDBCTemplate.close(conn);
		return result;
	}
	

	public int insertsMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertsMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
				return result;
			}
	

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMember(conn,memberId); 
		JDBCTemplate.close(conn);
		
		return member;
	}

	public Member selectOneMemberNickname(String memberNickname) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberNickname(conn,memberNickname); 
		JDBCTemplate.close(conn);
		
		return member;
	}

	public Member selectOneMemberEmail(String memberEmail) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberEmail(conn,memberEmail); 
		JDBCTemplate.close(conn);
		return member;
	}



	public Member selectOneMemberBno(String business) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberBno(conn,business); 
		JDBCTemplate.close(conn);
		return member;
	}

	public Member selectOneMemberPw(String memberEmail, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = new MemberDao().selectOneMemberPw(conn,memberEmail,memberId);
		JDBCTemplate.close(conn);
		return member;		

}
	public ArrayList<Cart> selectOneOrder(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Cart> list = new MemberDao().selectOneMemberOrder(conn,memberNo);		
		JDBCTemplate.close(conn);
		return list;
	}

	/*
	 * public int deleteOneCart(String memberNo) { Connection conn =
	 * JDBCTemplate.getConnection(); int result = new
	 * MemberDao().deleteOneCart(conn,memberNo); if(result>0) {
	 * JDBCTemplate.commit(conn); }else { JDBCTemplate.rollback(conn); } return
	 * result; }
	 */
	public int deleteOneCart(int cartNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteOneCart(conn,cartNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
}	

	public Cart selectOneCart(String productNo) {
		Connection conn = JDBCTemplate.getConnection();
		Cart cart = new MemberDao().selectOneCart(conn,productNo); 
		JDBCTemplate.close(conn);
		return cart;
	}

	public int deleteAllCart(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteAllCart(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	public int selectChkPrice(String num) {
		
		Connection conn = JDBCTemplate.getConnection();
		// num 1/2/3
		// /를 기준으로 구분
		StringTokenizer sT = new StringTokenizer(num,"/");
		MemberDao dao = new MemberDao();
		int priceresult = 0;
		while(sT.hasMoreTokens()) {
			int checked = Integer.parseInt(sT.nextToken());
			priceresult += dao.selectChkPrice(conn, checked);
			
			
		}
		System.out.println(priceresult +"가져온 가격값");
		JDBCTemplate.close(conn);
		return priceresult;



	
}
		/*
		 * Connection conn = JDBCTemplate.getConnection(); String price = new
		 * MemberDao().selectAllPrice(conn,num); JDBCTemplate.close(conn); return price;
		 */
	

	public boolean chkDeleteCart(String num) {
		Connection conn = JDBCTemplate.getConnection();
		// num 1/2/3
		// /를 기준으로 구분
		StringTokenizer sT = new StringTokenizer(num,"/");
		MemberDao dao = new MemberDao();
		boolean result = true;
		while(sT.hasMoreTokens()) {
			int checked = Integer.parseInt(sT.nextToken());
			int result1 = dao.deleteOneCart(conn, checked);
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

	public int selectAllPrice(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int member = new MemberDao().selectAllPrice(conn,memberNo); 
		JDBCTemplate.close(conn);
		System.out.println(member);
		return member;
	}

	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Point> PointList(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Point> list = new MemberDao().PointList(conn,memberNo);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public ArrayList<Cart> selectOneMemberOrder(int memberNo, String[] cartNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		String cart = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		for(int i=0; i<cartNoArr.length; i++) {
			cart = cartNoArr[i];
			Cart c = new MemberDao().selectOneMemberOrder(conn,memberNo, cart);
			list.add(c);
		}
		
		//ArrayList<Cart> list = new MemberDao().selectOneMemberOrder(conn,cartNoArr);		
		JDBCTemplate.close(conn);
		return list;
	}
}