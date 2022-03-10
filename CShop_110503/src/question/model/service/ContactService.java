package question.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import question.model.dao.ContactDao;
import question.model.vo.Contact;
import question.model.vo.ContactPageData;
import question.model.vo.ContactViewData;
import question.model.vo.CtComment;

public class ContactService {

	public ContactPageData selectContactList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		/*
		 * 페이징 처리를위해 지정해야 할 항목
		 * 1. 한페이지당 게시물을 몇개 보여줄지 - 10개
		 * 
		 */
		
		int numPerPage = 10; //한 페이지당 게시물 수
		//1페이지면 1/10, 2페이지는 11/20->reqPage=1 start=1/end=10, reqPage=2 start=11/end=20
		int end = reqPage*numPerPage;
		int start = end - numPerPage+1;
		ContactDao dao = new ContactDao();
		ArrayList<Contact> list = dao.selectContactList(conn,start,end);
		
		//페이지네비게이션을 제작
		//DB조회해야하는 값->전체 게시물의 수
		int totalCount = dao.selectTotalCount(conn);
		//전체페이지수
		int totalPage=0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage; // 10개단위
		}else {
			totalPage=totalCount/numPerPage+1; // 1개단위
		}
		
		//네비길이
		int pageNaviSize = 5;
		
	//	4번부터 가운데로 오면서 <2 3 4 5 6> 이 되야되는데 그러면 이전버튼 조건이 4번부터 나와야되고
	//	전체적으로 이동하는것도 기존조건과 달라야하네
		//4페이지부터 < 이 나와야되고 4페이지부터 4번이 가운데로 와야됌
				
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;

		//페이지네비 태그 제작 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
			if(reqPage/2 >= 1) { //현재페이지가 2page일때부터
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/question?reqPage="+(reqPage-1)+"'>";//하나씩은 넘어감
				pageNavi += "&lt;</a></li>";// 꺽새< 가 &lt
			}
		//페이지숫자
		int startPage=reqPage-2; // 4page부터 나오게 하는 변수
		int endPage=totalPage-4; // 마지막페이지에서 pagenavi가 5개 나오게 하는 변수
		
		for(int i=0;i<pageNaviSize;i++) {
			if(startPage>1 && reqPage<=(totalPage-2)) {//4번부터 현재페이지가 13일때까지 돔
				if(startPage==reqPage) {//활성화버튼
					pageNavi += "<li class='page-item active'>"; //활성화
					pageNavi += "<a class='page-link' href='/question?reqPage="+startPage+"'>";
					pageNavi += startPage+"</a></li>"; // 들어갈 내용(페이지숫자)
					
				}else {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/question?reqPage="+startPage+"'>";
					pageNavi += startPage+"</a></li>";
				}
				startPage++;//현재페이지가 13이면 13이활성 14,15가 비활성으로 나오고 16으로 늘어서 탈출
				if(startPage>totalPage) {
					break;
				}
			}else if(reqPage<=3){//1,2,3만 돔
				if((i+1)==reqPage) {//i=0이니깐 +1로 시작
					pageNavi += "<li class='page-item active'>"; //활성화
					pageNavi += "<a class='page-link' href='/question?reqPage="+(i+1)+"'>";
					pageNavi += (i+1)+"</a></li>"; // 들어갈 내용(페이지숫자)
				}else {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/question?reqPage="+(i+1)+"'>";
					pageNavi += (i+1)+"</a></li>";
				}
			}else{//현재페이지가 total보다 1작을때 돌아가는 함수
				//total-4부터 total까지 5번 돔
				if(endPage==reqPage) {
					pageNavi += "<li class='page-item active'>"; //활성화
					pageNavi += "<a class='page-link' href='/question?reqPage="+endPage+"'>";//reqPage의 번호
					pageNavi += endPage+"</a></li>"; // 들어갈 내용(페이지숫자)
					
				}else {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/question?reqPage="+endPage+"'>";
					pageNavi += endPage+"</a></li>";
				}
				endPage++;
				if(endPage>totalPage) {
					break;
				}
			}
		}
		//다음버튼
		if(reqPage<totalPage) {//total-1까지 다음버튼이 나오게함
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/question?reqPage="+(reqPage+1)+"'>";//하나씩은 넘어감
			pageNavi += "&gt;</a></li>";//꺽새 >가 &gt
		}
		pageNavi += "</ul>";
		
		//게시물목록은 arraylist, 페이지네비는 string, start는 int(번호표시용)
		ContactPageData npd = new ContactPageData(list, pageNavi,start);
		JDBCTemplate.close(conn);
		return npd;
	}

	public ContactViewData selectOneContact(int contactNo) {
		Connection conn = JDBCTemplate.getConnection();
		ContactDao dao = new ContactDao();
		Contact c = dao.selectOneContact(conn,contactNo);
		ArrayList<CtComment> list = dao.selectCommentList(conn,contactNo);
		ContactViewData cvd = new ContactViewData(list, c);
		JDBCTemplate.close(conn);
		return cvd;
	}

	public int insertContact(Contact c) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().insertContact(conn,c);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Contact getContact(int contactNo) {
		Connection conn = JDBCTemplate.getConnection();
		Contact c = new ContactDao().selectOneContact(conn, contactNo);
		JDBCTemplate.close(conn);
		return c;
	}

	public int updateContact(Contact c) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().updateContact(conn,c);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteContact(int contactNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().deleteContact(conn,contactNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(CtComment ct) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().insertComment(conn,ct);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateComment(int ctNo, String ctContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().updateComment(conn,ctNo,ctContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteComment(int ctNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ContactDao().deleteComment(conn,ctNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
