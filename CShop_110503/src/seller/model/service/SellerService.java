package seller.model.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import common.JDBCTemplate;
import member.model.vo.Member;
import product.model.vo.ProductQNA;
import seller.model.dao.SellerDao;
import seller.model.vo.InquiryPageData;
import seller.model.vo.OrderInfo;
import seller.model.vo.OrderPageData;
import seller.model.vo.OrderProduct;
import seller.model.vo.OrderViewData;
import seller.model.vo.SellerPageData;

public class SellerService {

	public OrderPageData selectOrderList(int reqPage, int sellerNo, int orderStatus) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalPage = 0;
		int totalCount = 0;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		SellerDao dao = new SellerDao();
		ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
		
		if(orderStatus==4) { //전제
			list = dao.selectOrderList(conn,start,end,sellerNo);
			totalCount = dao.selectTotalCount(conn,sellerNo);
		}else {
			list = dao.selectOrderList(conn,start,end,sellerNo,orderStatus);
			totalCount = dao.selectTotalCount(conn,sellerNo,orderStatus);
		}
		
		
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-sm'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='previous'>";
			pageNavi += "<a href='/saleManage?reqPage="+(pageNo-1)+"&memberNo="+sellerNo+"&orderStatus="+orderStatus+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='active'>";
				pageNavi += "<a href='/saleManage?reqPage="+pageNo+"&memberNo="+sellerNo+"&orderStatus="+orderStatus+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a href='/saleManage?reqPage="+pageNo+"&memberNo="+sellerNo+"&orderStatus="+orderStatus+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='next'>";
			pageNavi += "<a href='/saleManage?reqPage="+pageNo+"&memberNo="+sellerNo+"&orderStatus="+orderStatus+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호표시용)
		OrderPageData opd = new OrderPageData(list, pageNavi,start);
		
		
		JDBCTemplate.close(conn);
		return opd;
	}

	public InquiryPageData selectInquiryList(int reqPage, int sellerNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalPage = 0;
		int totalCount = 0;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		SellerDao dao = new SellerDao();
		ArrayList<ProductQNA> list = dao.selectInquiryList(conn,start,end,sellerNo);
		totalCount = dao.selectInquiryCount(conn,sellerNo);
		
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-sm'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='previous'>";
			pageNavi += "<a href='/productInquiryList?reqPage="+(pageNo-1)+"&memberNo="+sellerNo+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='active'>";
				pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo="+sellerNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo="+sellerNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='next'>";
			pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo="+sellerNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호표시용)
		InquiryPageData ipd = new InquiryPageData(list, pageNavi,start);
		
		
		JDBCTemplate.close(conn);
		return ipd;
	}

	public ProductQNA selectOneInquiry(int pqNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ProductQNA pq = new SellerDao().selectOneInquiry(conn, pqNo);
		JDBCTemplate.close(conn);
		return pq;
	}

	public ProductQNA selectOneAnswer(int pqNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ProductQNA pa = new SellerDao().selectOneAnswer(conn, pqNo);
		JDBCTemplate.close(conn);
		return pa;
	}

	public int insertAnswer(ProductQNA pq) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result1 = new SellerDao().updateInquriyAnswer(conn,pq);
		int result2 = new SellerDao().insertAnswer(conn,pq);
		int result3 = result1+result2;
		if(result3>1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result3;
	}

	public int updateAnswer(ProductQNA pq) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new SellerDao().updateAnswer(conn,pq);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public OrderViewData orderManage(int orderNo, int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalPage = 0;
		int totalCount = 0;
		int reqPage = 1;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		SellerDao dao = new SellerDao();
		OrderInfo oi = dao.selectOneOrder(conn, orderNo);
		ArrayList<OrderProduct> list = dao.orderProductList(conn, orderNo, start, end);
		totalCount = dao.selectOrderCount(conn,orderNo);
		
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination pagination-sm'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='previous'>";
			pageNavi += "<a href='/productInquiryList?reqPage="+(pageNo-1)+"&memberNo="+memberNo+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='active'>";
				pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo="+memberNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo="+memberNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='next'>";
			pageNavi += "<a href='/productInquiryList?reqPage="+pageNo+"&memberNo=15'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호표시용)
		OrderViewData ovd = new OrderViewData(oi, list, pageNavi, start);
		
		JDBCTemplate.close(conn);
		return ovd;
	}

	public int updateOrder(int orderNo, String deliveryCompany, int deliveryNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new SellerDao().updateOrder(conn,orderNo,deliveryCompany,deliveryNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deliveryComplete(int orderNo, int memberNo, int orderPrice) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		SellerDao dao = new SellerDao();
		int membership = dao.selectOneMembership(conn,memberNo);
		double upPoint=0;
		if(membership ==1) {
			upPoint = orderPrice*0.05;
		}else if(membership ==2) {
			upPoint = orderPrice*0.1;
		}else if(membership ==3) {
			upPoint = orderPrice*0.15;
		}
		int result1 = dao.deliveryComplete(conn,orderNo);
		int result2 = dao.updatePoint(conn,memberNo,upPoint);
		
		if(result1>0 && result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result1+result2;
	}

	public int updateSeller(Member member) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new SellerDao().updateSeller(conn, member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteSeller(int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new SellerDao().deleteSeller(conn, memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public SellerPageData deliveryCnt(int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();		
		SellerPageData spd = new SellerDao().deliveryCnt(conn, memberNo);
		JDBCTemplate.close(conn);
		return spd;
	}

	public SellerPageData orderToday(int memberNo, String today) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		SellerPageData spd = new SellerDao().orderToday(conn,memberNo,today);
		JDBCTemplate.close(conn);
		return spd;	
	}

	public int[] orderCount(Calendar date, int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int orderCount[]= new int[7];
		String day;
		for(int i=0;i<7;i++) {
			day = formatter.format(date.getTime());
			orderCount[i] = new SellerDao().orderCount(conn,day,memberNo);
			date.add(Calendar.DATE, -1);
		}
		JDBCTemplate.close(conn);
		return orderCount;
	}

	public int[] orderAmount(Calendar date, int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int orderAmount[]= new int[7];
		String day;
		for(int i=0;i<7;i++) {
			day = formatter.format(date.getTime());
			orderAmount[i] = new SellerDao().orderAmount(conn,day,memberNo);
			date.add(Calendar.DATE, -1);
		}
		JDBCTemplate.close(conn);
		return orderAmount;
	}
	

}
