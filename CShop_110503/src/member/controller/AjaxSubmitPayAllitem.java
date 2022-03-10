package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Cart;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxIdCheckServlet
 */
@WebServlet(name = "AjaxSubmitPayAllItem", urlPatterns = { "/ajaxSubmitPayAllItem" })
public class AjaxSubmitPayAllitem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxSubmitPayAllitem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		//view에서 보내온 값 추출 /세션에서 멤버no가져옴
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		String memberPhone = m.getMemberPhone();
		String memberEmail = m.getMemberEmail();
		String postCode = m.getPostcode1();
		String address_Road = m.getAddressRoad1();
		String address_Detail = m.getAddressDetail1();
		System.out.println(memberNo);
		int oderPrice = new MemberService().selectAllPrice(memberNo);
		System.out.println("servlet price :" + oderPrice);//CART MEMBER_NO체크된 가격
		
		//결과처리 No로 조회한 Cart
		ArrayList<Cart> list = new MemberService().selectOneOrder(memberNo);
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
