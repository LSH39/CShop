package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.Point;

/**
 * Servlet implementation class PointListServlet
 */
@WebServlet(name = "PointList", urlPatterns = { "/pointList" })
public class PointListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int selectmenu = Integer.parseInt(request.getParameter("selectmenu"));
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		int memberNo = m.getMemberNo();
		//3.비즈니스 로직
		ArrayList<Point> list = new MemberService().PointList(memberNo);
		
		
		int totalPoint = 0;
		for(Point p : list) {
			if(p.getPointLevel() == 1) {
				totalPoint += p.getPointValue();
			}else if(p.getPointLevel() == 2) {
				totalPoint -= p.getPointValue();
			}
		}
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/PointList.jsp");
		request.setAttribute("list", list);
		request.setAttribute("totalPoint", totalPoint);
		request.setAttribute("selectmenu", selectmenu);
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
