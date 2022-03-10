package seller.model.vo;

import java.util.ArrayList;

public class OrderViewData {
	private OrderInfo oi;
	private ArrayList<OrderProduct> list;
	private String pageNavi;
	private int start;
	public OrderViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderViewData(OrderInfo oi, ArrayList<OrderProduct> list, String pageNavi, int start) {
		super();
		this.oi = oi;
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public OrderInfo getOi() {
		return oi;
	}
	public void setOi(OrderInfo oi) {
		this.oi = oi;
	}
	public ArrayList<OrderProduct> getList() {
		return list;
	}
	public void setList(ArrayList<OrderProduct> list) {
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
