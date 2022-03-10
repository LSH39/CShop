package question.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import question.model.vo.Contact;
import question.model.vo.CtComment;

public class ContactDao {

	public ArrayList<Contact> selectContactList(Connection conn, int start, int end) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		ArrayList<Contact> list = new ArrayList<Contact>();
		String query = "select cc.*,(select count(*) from ct_comment where contact_ref=cc.contact_no) as \"ct_count\" from (select rownum as rnum, c.* from (select * from contact order by contact_no desc)c)cc where rnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Contact n = new Contact();
				n.setContactNo(rset.getInt("contact_no"));
				n.setContactTitle(rset.getString("contact_title"));
				n.setContactContent(rset.getString("contact_content"));
				n.setContactWriter(rset.getString("contact_writer"));
				n.setRegDate(rset.getString("reg_date"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setCtCount(rset.getInt("ct_count"));
				list.add(n);
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

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from contact";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");//별칭으로 가져온거
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

	public Contact selectOneContact(Connection conn, int contactNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Contact c = null;
		String query = "select * from contact where contact_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, contactNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				c = new Contact();
				c.setContactNo(rset.getInt("contact_no"));
				c.setContactTitle(rset.getString("contact_title"));
				c.setContactContent(rset.getString("contact_content"));
				c.setContactWriter(rset.getString("contact_writer"));
				c.setRegDate(rset.getString("reg_date"));
				c.setFilename(rset.getString("filename"));
				c.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return c;
	}

	public int insertContact(Connection conn, Contact c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into contact values (contact_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, c.getContactTitle());
			pstmt.setString(2, c.getContactContent());
			pstmt.setString(3, c.getContactWriter());
			pstmt.setString(4, c.getFilename());
			pstmt.setString(5, c.getFilepath());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateContact(Connection conn, Contact c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update contact set contact_title=?, contact_content=?, filename=?, filepath=? where contact_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, c.getContactTitle());
			pstmt.setString(2, c.getContactContent());
			pstmt.setString(3, c.getFilename());
			pstmt.setString(4, c.getFilepath());
			pstmt.setInt(5, c.getContactNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteContact(Connection conn, int contactNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from contact where contact_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, contactNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, CtComment ct) {
		PreparedStatement pstmt=null;
		int result = 0;
		String query = "insert into ct_comment values (ct_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, ct.getCtLevel());
			pstmt.setString(2, ct.getCtWriter());
			pstmt.setString(3, ct.getCtContent());
			pstmt.setInt(4, ct.getContactRef());
			//pstmt.setInt(5, nc.getNcRef()); 참조인데 0이없음 그래서 아래식으로 해줘야됌
			
			//(조건식) ? a : b ; - 삼항연산자 조건식이 맞으면 a 틀리면 b
			pstmt.setString(5, (ct.getCtRef()==0) ? null:String.valueOf(ct.getCtRef()));
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<CtComment> selectCommentList(Connection conn, int contactNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CtComment> list = new ArrayList<CtComment>();
		
		String query = "select * from ct_comment where contact_ref=? order by 1";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, contactNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				CtComment cc = new CtComment();
				cc.setCtNo(rset.getInt("ct_no"));
				cc.setCtLevel(rset.getInt("ct_level"));
				cc.setCtWriter(rset.getString("ct_writer"));
				cc.setCtContent(rset.getString("ct_content"));
				cc.setCtDate(rset.getString("ct_date"));
				cc.setContactRef(rset.getInt("contact_ref"));
				cc.setCtRef(rset.getInt("ct_ref"));//0이면 자동으로 null리턴
				list.add(cc);
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

	public int updateComment(Connection conn, int ctNo, String ctContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update ct_comment set ct_content=? where ct_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, ctContent);
			pstmt.setInt(2, ctNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteComment(Connection conn, int ctNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from ct_comment where ct_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, ctNo);
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
