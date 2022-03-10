package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import admin.model.service.AdminService;
import member.model.service.MemberService;
import member.model.vo.Member;
import oracle.sql.ARRAY;

/**
 * Servlet implementation class UpdateMemberDoServlet
 */
@WebServlet(name = "UpdateMemberDo", urlPatterns = { "/updateMemberDo" })
public class UpdateMemberDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choiceNoStr = request.getParameter("choiceNo");
		int choiceNo = Integer.parseInt(choiceNoStr);
		String selectStr = null;
		selectStr = request.getParameter("selectArr");
		
		int result = 0;
		if(selectStr != null) {
			StringTokenizer sT = new StringTokenizer(selectStr,",");
			String [] memberNoArr = new String [sT.countTokens()];
			int i=0;
			while(sT.hasMoreTokens()) {
				memberNoArr[i++] = sT.nextToken();//sT.nextToken();
			}
			
			result = new AdminService().selectUpdateMemberDo(choiceNo, memberNoArr);
			
		}else {
			result = 0;
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new Gson().toJson(result,out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
