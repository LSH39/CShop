package seller.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.model.service.SellerService;
import seller.model.vo.SellerPageData;

/**
 * Servlet implementation class SellerPageServlet
 */
@WebServlet(name = "SellerPage", urlPatterns = { "/sellerPage" })
public class SellerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MM-dd");
		String today = formatter.format(new Date());
		
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		System.out.println("1"+date.getTime());
		int orderCount[] = new SellerService().orderCount(date, memberNo);
		date.setTime(new Date());
		System.out.println("2"+date.getTime());
		int orderAmount[] = new SellerService().orderAmount(date, memberNo);
		
		String orderDay[] = new String[7];
		Calendar date2 = Calendar.getInstance();
		date2.setTime(new Date());
		for(int i=0;i<7;i++) {
			orderDay[i] = formatter2.format(date2.getTime());
			date2.add(Calendar.DATE, -1);
		}
		
		SellerPageData spd = new SellerService().deliveryCnt(memberNo); 
		SellerPageData orderToday = new SellerService().orderToday(memberNo,today);
		spd.setOrderTodayCnt(orderToday.getOrderTodayCnt());
		spd.setOrderAmountSum(orderToday.getOrderAmountSum());
		spd.setOrderCount(orderCount);
		spd.setOrderDay(orderDay);
		spd.setOrderAmount(orderAmount);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/seller/sellerPage.jsp");
		request.setAttribute("orderTodayCnt", spd.getOrderTodayCnt());
		request.setAttribute("payCompleteCnt", spd.getPayCompleteCnt());
		request.setAttribute("deliveryingCnt", spd.getDeliveryingCnt());
		request.setAttribute("deliveredCnt", spd.getDeliveredCnt());
		request.setAttribute("orderAmountSum", spd.getOrderAmountSum());
		request.setAttribute("orderCount", spd.getOrderCount());
		request.setAttribute("orderDay", spd.getOrderDay());
		request.setAttribute("orderAmount", spd.getOrderAmount());
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
