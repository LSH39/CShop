package product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import member.model.dao.MemberDao;
import member.model.service.MemberService;
import member.model.vo.Cart;
import member.model.vo.Member;
import product.model.vo.Product;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet(name = "Payment", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cart no
		String numStr = request.getParameter("num");
		//총 합산가격
		int orderPrice = new MemberService().selectChkPrice(numStr);
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		
		StringTokenizer sT = new StringTokenizer(numStr,"/");
		String [] cartNoArr = new String [sT.countTokens()];
		int i=0;
		while(sT.hasMoreTokens()) {
			cartNoArr[i++] = sT.nextToken();
		}
		
		ArrayList<Cart> list = new MemberService().selectOneMemberOrder(memberNo, cartNoArr);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/Payment.jsp");
		request.setAttribute("list", list);
		request.setAttribute("orderPrice", orderPrice);
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
