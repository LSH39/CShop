package main.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import main.model.dao.MainDao;
import product.model.vo.Product;
import question.model.dao.ContactDao;
import question.model.vo.Contact;
import question.model.vo.ContactPageData;

public class MainService {

	public ArrayList<Product> selectBestProduct() {
		Connection conn = JDBCTemplate.getConnection();
		MainDao dao = new MainDao();
		ArrayList<Product> list = dao.seleceBestProduct(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectNewListProduct() {
		Connection conn = JDBCTemplate.getConnection();
		MainDao dao = new MainDao();
		ArrayList<Product> list = dao.selectNewListProduct(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

	public MainSearchPageData searchSearchProduct(int reqPage, String search) {
		Connection conn = JDBCTemplate.getConnection();
		/*
		 * 페이징 처리를위해 지정해야 할 항목
		 * 1. 한페이지당 게시물을 몇개 보여줄지 - 10개
		 * 
		 */
		
		int numPerPage = 12; //한 페이지당 게시물 수
		//1페이지면 1/10, 2페이지는 11/20->reqPage=1 start=1/end=10, reqPage=2 start=11/end=20
		int end = reqPage*numPerPage;
		int start = end - numPerPage+1;
		MainDao dao = new MainDao();
		ArrayList<Product> list = dao.selectSearchProductList(conn,start,end,search);
		
		//페이지네비게이션을 제작
		//DB조회해야하는 값->전체 게시물의 수
		int totalCount = dao.selectTotalCount(conn,search);
		//전체페이지수
		int totalPage=0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage; // 10개단위
		}else {
			totalPage=totalCount/numPerPage+1; // 1개단위
		}
		//네비길이
				int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A' href='/mainSearchProduct?reqPage="+(pageNo-1)+"&search="+search+"'>";
			pageNavi += "&lt;</a></li>";		//&lit;는 < (html에서 태그 열때 쓰이는거라 문자로 쓸땐 이렇게 씀)
		}
		//페이지숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' style='Background-color:#5F755A' href='/mainSearchProduct?reqPage="+pageNo+"&search="+search+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' style='color:#5F755A'  href='/mainSearchProduct?reqPage="+pageNo+"&search="+search+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' style='color:#5F755A'  href='/mainSearchProduct?reqPage="+pageNo+"&search="+search+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi +="</ul>";
		
		//게시물목록은 arraylist, 페이지네비는 string, start는 int(번호표시용)
		MainSearchPageData mspd = new MainSearchPageData(list, pageNavi,start);
		JDBCTemplate.close(conn);
		return mspd;
	}

}
