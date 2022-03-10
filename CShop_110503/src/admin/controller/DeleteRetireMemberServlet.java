package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.model.service.AdminService;

/**
 * Servlet implementation class DeleteRetireMemberServlet
 */
@WebServlet(name = "DeleteRetireMember", urlPatterns = { "/deleteRetireMember" })
public class DeleteRetireMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRetireMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		int result = 0;
		int selectResult = new AdminService().selectDeleteRetireMember(memberLevel);
		if(selectResult == -1) {
			result = -1;
		}else {
			result = new AdminService().deleteRetireMember(memberLevel);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(result,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
