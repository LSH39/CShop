package seller.model.vo;

import java.util.ArrayList;

public class OrderPageData {
	private ArrayList<OrderInfo> list;
	private String pageNavi;
	private int start;
	public OrderPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderPageData(ArrayList<OrderInfo> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<OrderInfo> getList() {
		return list;
	}
	public void setList(ArrayList<OrderInfo> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

}
