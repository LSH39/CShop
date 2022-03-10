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
 * Servlet implementation class JoinMemberListServlet
 */
@WebServlet(name = "JoinMemberList", urlPatterns = { "/joinMemberList" })
public class JoinMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int memberLevel1 = 1;
		int memberLevel2 = 2;
		// 3. 비즈니스로직
		MemberListPageData mlpd1 = new AdminService().selectJoinMemberList(reqPage,memberLevel1);
		MemberListPageData mlpd2 = new AdminService().selectJoinMemberList(reqPage,memberLevel2);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/JoinMemberList.jsp");
		request.setAttribute("list1", mlpd1.getMember());
		request.setAttribute("pageNavi1", mlpd1.getPageNavi());
		request.setAttribute("list2", mlpd2.getMember());
		request.setAttribute("pageNavi2", mlpd2.getPageNavi());
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
