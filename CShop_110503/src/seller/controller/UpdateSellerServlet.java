package seller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import seller.model.service.SellerService;

/**
 * Servlet implementation class UpdateSellerServlet
 */
@WebServlet(name = "UpdateSeller", urlPatterns = { "/updateSeller" })
public class UpdateSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member member = new Member();
		member.setMemberPassword(request.getParameter("memberPw"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberPhone(request.getParameter("phone"));
		member.setPostcode1(request.getParameter("postcode1"));
		member.setPostcode2(request.getParameter("postcode2"));
		member.setAddressDetail1(request.getParameter("addressDetail1"));
		member.setAddressDetail2(request.getParameter("addressDetail2"));
		member.setAddressRoad1(request.getParameter("addressRoad1"));
		member.setAddressRoad2(request.getParameter("addressRoad2"));
		member.setMemberId(request.getParameter("memberId"));
		int result = new SellerService().updateSeller(member);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/");
		if(result>0) {
			request.setAttribute("msg", "정보변경 성공");
			Member m = new MemberService().selectOneMember(member.getMemberId());
			HttpSession session = request.getSession(false);
			session.setAttribute("m", m);
		}else {
			request.setAttribute("msg", "정보변경 실패");
		}
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
