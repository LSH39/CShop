package admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDao;
import admin.model.vo.MemberListPageData;
import admin.model.vo.ProductCheckPageData;
import admin.model.vo.SalePageData;
import common.JDBCTemplate;
import member.model.vo.Member;
import product.model.vo.Product;
import seller.model.vo.OrderProduct;

public class AdminService {

	public MemberListPageData selectJoinMemberList(int reqPage, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		AdminDao dao = new AdminDao();
		ArrayList<Member> list = null;
		if(memberLevel == 1) {
			list = dao.selectJoinMember1List(conn,memberLevel,start,end);
		}else if(memberLevel == 2){
			list = dao.selectJoinMember2List(conn,memberLevel,start,end);
			
		}
		
		int totalCount = dao.selectJoinTotalCount(conn,memberLevel);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination admin-navi-ul'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/joinMemberList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/joinMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/joinMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/joinMemberList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		MemberListPageData mlpd = new MemberListPageData(list, pageNavi, start);			
		
		
		JDBCTemplate.close(conn);
		
		return mlpd;
	}

	public MemberListPageData selectRetireMemberList(int reqPage, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		AdminDao dao = new AdminDao();
		ArrayList<Member> list = dao.selectRetireMemberList(conn,memberLevel,start,end);
		
		int totalCount = dao.selectRetireTotalCount(conn,memberLevel);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination admin-navi-ul'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/retireMemberList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/retireMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/retireMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/retireMemberList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		MemberListPageData mlpd = new MemberListPageData(list, pageNavi, start);			
		
		
		JDBCTemplate.close(conn);
		
		return mlpd;
	}

	public int selectDeleteRetireMember(int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new AdminDao().selectDeleteRetireMember(conn,memberLevel);
		if(list != null) {
			JDBCTemplate.close(conn);
			return 0;
		}else {
			JDBCTemplate.close(conn);
			return -1;
		}
	}
	
	public int deleteRetireMember(int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdminDao().deleteRetireMember(conn,memberLevel);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MemberListPageData selectUpdateMemberList(int reqPage, int newMembership) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		AdminDao dao = new AdminDao();
		ArrayList<Member> list = new ArrayList<Member>();
		int totalCount = 0;
		if(newMembership == 1) {
			list = dao.selectUpdateMember1List(conn,start,end);
			totalCount = dao.selectUpdateTotal1Count(conn);
		}else if(newMembership == 2){
			list = dao.selectUpdateMember2List(conn,start,end);
			totalCount = dao.selectUpdateTotal2Count(conn);
		}else if(newMembership == 3) {
			list = dao.selectUpdateMember3List(conn,start,end);
			totalCount = dao.selectUpdateTotal3Count(conn);
		}
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination admin-navi-ul'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/updateMemberList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/updateMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/updateMemberList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/updateMemberList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		MemberListPageData mlpd = new MemberListPageData(list, totalCount, newMembership, pageNavi);			
		
		
		JDBCTemplate.close(conn);
		
		return mlpd;
	}

	public ArrayList<SalePageData> selectSaleSellerList() {
		Connection conn = JDBCTemplate.getConnection();
		int end = 10;
		int start = 1;
		AdminDao dao = new AdminDao();
		ArrayList<Member> mlist = dao.selectSaleSellerList(conn,start,end);
		ArrayList<SalePageData> spdlist = new ArrayList<SalePageData>();
		for(int i=0; i<mlist.size(); i++) {
			int memberNo = mlist.get(i).getMemberNo();
			int totalSale = mlist.get(i).getSaleProduct();
			Product p = dao.selectSaleSellerInfo(conn,memberNo);
			SalePageData spd = new SalePageData(p, memberNo, totalSale);
			spdlist.add(spd);
		}
		
		JDBCTemplate.close(conn);
		
		return spdlist;
	}

	public ProductCheckPageData selectProductCheck(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		AdminDao dao = new AdminDao();
		ArrayList<Product> list = dao.selectProductCheckList(conn,start,end);
		
		int totalCount = dao.selectProductCheckCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination admin-navi-ul'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/productCheck?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/productCheck?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/productCheck?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/productCheck?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		ProductCheckPageData pcpd = new ProductCheckPageData(list, pageNavi, start);
		
		JDBCTemplate.close(conn);
		
		return pcpd;
	}

	public ProductCheckPageData selectProductCheckFail(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end-numPerPage+1;
		AdminDao dao = new AdminDao();
		ArrayList<Product> list = dao.selectProductCheckFailList(conn,start,end);
		
		int totalCount = dao.selectProductCheckFailCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination admin-navi-ul'>";
		
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/productCheckFail?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/productCheckFail?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/productCheckFail?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/productCheckFail?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		ProductCheckPageData pcpd = new ProductCheckPageData(list, pageNavi, start);
		
		JDBCTemplate.close(conn);
		
		return pcpd;
	}

	public int productCheckUpdate(int productNo, int productStatus) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdminDao().productCheckUpdate(conn,productNo,productStatus);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ProductCheckPageData selectSaleProductDaily(String selectDaily) {
		Connection conn = JDBCTemplate.getConnection();
		int end = 10;
		int start = 1;
		AdminDao dao = new AdminDao();
		ArrayList<Product> list = dao.selectSaleProductDaily(conn,selectDaily,start,end);
		
		ProductCheckPageData pcpd = new ProductCheckPageData(list);		
		
		
		JDBCTemplate.close(conn);
		
		return pcpd;
	}

	public ProductCheckPageData selectSaleProductMonth(String selectMonth) {
		Connection conn = JDBCTemplate.getConnection();
		int end = 10;
		int start = 1;
		AdminDao dao = new AdminDao();
		ArrayList<Product> list = dao.selectSaleProductMonth(conn,selectMonth,start,end);
		
		ProductCheckPageData pcpd = new ProductCheckPageData(list);		
		JDBCTemplate.close(conn);
		return pcpd;
	}

	public ProductCheckPageData selectSaleProductPeriod(String selectPeriodStart, String selectPeriodEnd) {
		Connection conn = JDBCTemplate.getConnection();
		int end = 10;
		int start = 1;
		AdminDao dao = new AdminDao();
		ArrayList<Product> list = dao.selectSaleProductPeriod(conn,selectPeriodStart, selectPeriodEnd,start,end);
		
		ProductCheckPageData pcpd = new ProductCheckPageData(list);		
		JDBCTemplate.close(conn);
		return pcpd;
	}

	public Product selectProduct(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		Product p = new AdminDao().selectProduct(conn,productNo);
		return p;
	}

	public int paymentOrderInfo(int i, int memberNo, String memberPhone, String memberEmail, String postcode, String addressRoad, String addressDetail, int paymentMethod, int orderPrice, int deliveryPrice, int orderPoint, int paymentPrice, String deliveryRequest, String impUid) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdminDao().paymentOrderInfo(conn,memberNo,memberPhone,memberEmail,postcode,addressRoad,addressDetail,paymentMethod,orderPrice,deliveryPrice,orderPoint,paymentPrice,deliveryRequest,impUid);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		int orderNo = new AdminDao().selectPaymentOrderNo(conn,i, impUid);
		
		
		JDBCTemplate.close(conn);
		return orderNo;
	}


	public int paymentOrderProduct(int sellerNo, int orderNo, int productNo, String productColor, String productSize,
			int productPrice, int productCount) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		
		AdminDao dao = new AdminDao();
		int result1 = dao.paymentOrderInfoUpdate(conn, sellerNo, orderNo);
		int result2 = dao.paymentOrderProduct(conn, orderNo, productNo, productColor, productSize, productPrice, productCount);
		
		result = result1*result2;
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int[][] selectSaleCategoryDaily(String selectDaily) {
		Connection conn = JDBCTemplate.getConnection();
		int arr[][] = new int[4][7];
		
		AdminDao dao = new AdminDao();
		
		int resultAll = dao.selectSaleCategoryDaily(conn, selectDaily);
		arr[0][0] = resultAll;
		for(int i=1;i<=3;i++) {
			int resultOne = dao.selectSaleCategoryDaily(conn, selectDaily, i);
			arr[i][0] = resultOne;
			
			for(int j=1;j<=6;j++) {
				int resultDetail = dao.selectSaleCategoryDailyDetail(conn, selectDaily, i, j);
				arr[i][j] = resultDetail;
			}
		}
		
		JDBCTemplate.close(conn);
		
		return arr;
	}

	public int[][] selectSaleCategoryMonth(String selectMonth) {
		Connection conn = JDBCTemplate.getConnection();
		int arr[][] = new int[4][7];
		
		AdminDao dao = new AdminDao();
		
		int resultAll = dao.selectSaleCategoryMonth(conn, selectMonth);
		arr[0][0] = resultAll;
		for(int i=1;i<=3;i++) {
			int resultOne = dao.selectSaleCategoryMonth(conn, selectMonth, i);
			arr[i][0] = resultOne;
			
			for(int j=1;j<=6;j++) {
				int resultDetail = dao.selectSaleCategoryMonthDetail(conn, selectMonth, i, j);
				arr[i][j] = resultDetail;
			}
		}
		
		JDBCTemplate.close(conn);
		
		return arr;
	}

	public int[][] selectSaleCategoryPeriod(String selectPeriodStart, String selectPeriodEnd) {
		Connection conn = JDBCTemplate.getConnection();
		int arr[][] = new int[4][7];
		
		AdminDao dao = new AdminDao();
		
		int resultAll = dao.selectSaleCategoryPeriod(conn, selectPeriodStart,selectPeriodEnd);
		arr[0][0] = resultAll;
		for(int i=1;i<=3;i++) {
			int resultOne = dao.selectSaleCategoryPeriod(conn, selectPeriodStart,selectPeriodEnd, i);
			arr[i][0] = resultOne;
			
			for(int j=1;j<=6;j++) {
				int resultDetail = dao.selectSaleCategoryPeriodDetail(conn, selectPeriodStart,selectPeriodEnd, i, j);
				arr[i][j] = resultDetail;
			}
		}
		
		JDBCTemplate.close(conn);
		
		return arr;
	}

	public int[][] selectMemberCount(String todayMonth, String prev1Month, String prev2Month) {
		Connection conn = JDBCTemplate.getConnection();
		int arr[][] = new int[3][3];
		
		AdminDao dao = new AdminDao();
		
		for(int i=1;i<=3;i++) {
			String Month = "";
			if(i==1) {
				Month = todayMonth;
			}else if(i==2) {
				Month = prev1Month;
			}else if(i==3) {
				Month = prev2Month;
			}
			for(int j=1;j<=3;j++) {
				if(j == 1) {  // i==1 : todayMonth, j==1 : 4건미만
					int result = dao.selectMemberCount1(conn, Month);
					arr[i][j] = result;
				}else if(j == 2) {  // j == 2 : 4 and 9 
					//int result = dao.selectMemberCount2(conn, Month);
					//arr[i][j] = result;
				}else if(j == 3) { // j == 3 : 10건이상
					//int result = dao.selectMemberCount3(conn, Month);
					//arr[i][j] = result;
				}
			}
		}
		
		JDBCTemplate.close(conn);
		
		return arr;
	}

	public int selectUpdateMemberDo(int choiceNo, String[] memberNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		int resultCal = 1;
		for(int j=0; j<memberNoArr.length; j++) {
			String memberNo = memberNoArr[j];
			result = new AdminDao().selectUpdateMemberDo(conn, choiceNo, memberNo);
			resultCal = resultCal*result;
		}
		
		if(resultCal > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return resultCal;
	}

	


}
