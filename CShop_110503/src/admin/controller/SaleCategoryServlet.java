package admin.controller;

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

/**
 * Servlet implementation class SaleCategoryServlet
 */
@WebServlet(name = "SaleCategory", urlPatterns = { "/saleCategory" })
public class SaleCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mDate = new SimpleDateFormat("yyyy-MM");
		String todayDate = sDate.format(new Date());
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        String prevMonth = mDate.format(cal.getTime());
		
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/SaleCategory.jsp");
		request.setAttribute("selectDate", todayDate);
		request.setAttribute("selectMonth", prevMonth);
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("prevMonth", prevMonth);
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
