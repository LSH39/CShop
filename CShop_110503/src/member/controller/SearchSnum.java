package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name = "SearchSnum", urlPatterns = { "/searchSnum" })
public class SearchSnum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSnum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String apiKey = "Cu5uxf7LUuDNTON7CPuh3J3qD%2BDA0FQ%2BM7V%2FKqJjXN%2B3KVHoZSKOHci349ZHvlEdKsd%2BRX7c5HBpd2QnWlyBvw%3D%3D";
		String no = request.getParameter("no");
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,
				"{\r\n" + 
				"  \"b_no\": [\r\n" + 
				"    \""+no+"\"\r\n" + 
				"  ]\r\n" + 
				"}");
		
		Request req = new Request.Builder()
				.url("https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="+apiKey)
				.method("POST", body)
				.addHeader("Content-Type", "application/json")
				.build();
		Response res = client.newCall(req).execute();		
		String result = res.body().string();
		System.out.println(result);
		JsonParser parser = new JsonParser();		
		JsonObject json = (JsonObject)parser.parse(result);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(json,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
