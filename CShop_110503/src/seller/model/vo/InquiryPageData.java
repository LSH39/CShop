package seller.model.vo;

import java.util.ArrayList;

import product.model.vo.ProductQNA;

public class InquiryPageData {
	private ArrayList<ProductQNA> list;
	private String pageNavi;
	private int start;
	public InquiryPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InquiryPageData(ArrayList<ProductQNA> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<ProductQNA> getList() {
		return list;
	}
	public void setList(ArrayList<ProductQNA> list) {
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
