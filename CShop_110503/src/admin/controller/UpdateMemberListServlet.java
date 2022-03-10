package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.MemberListPageData;

/**
 * Servlet implementation class UpdateMemberListServlet
 */
@WebServlet(name = "UpdateMemberList", urlPatterns = { "/updateMemberList" })
public class UpdateMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int newMembership1 = 1;
		int newMembership2 = 2;
		int newMembership3 = 3;
		// 3. 비즈니스로직
		MemberListPageData mlpd1 = new AdminService().selectUpdateMemberList(reqPage,newMembership1);
		MemberListPageData mlpd2 = new AdminService().selectUpdateMemberList(reqPage,newMembership2);
		MemberListPageData mlpd3 = new AdminService().selectUpdateMemberList(reqPage,newMembership3);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/UpdateMemberList.jsp");
		request.setAttribute("list1", mlpd1.getMember());
		request.setAttribute("purchaseSixMonth1", mlpd1.getPurchaseSixMonth());
		request.setAttribute("newMembership1", mlpd1.getNewMembership());
		request.setAttribute("pageNavi1", mlpd1.getPageNavi());
		request.setAttribute("list2", mlpd2.getMember());
		request.setAttribute("purchaseSixMonth2", mlpd2.getPurchaseSixMonth());
		request.setAttribute("newMembership2", mlpd2.getNewMembership());
		request.setAttribute("pageNavi2", mlpd2.getPageNavi());
		request.setAttribute("list3", mlpd3.getMember());
		request.setAttribute("purchaseSixMonth3", mlpd3.getPurchaseSixMonth());
		request.setAttribute("newMembership3", mlpd3.getNewMembership());
		request.setAttribute("pageNavi3", mlpd3.getPageNavi());
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
