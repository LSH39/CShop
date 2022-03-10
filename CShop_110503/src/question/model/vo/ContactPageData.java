package question.model.vo;

import java.util.ArrayList;

public class ContactPageData {
	private ArrayList<Contact> list;
	private String pageNavi;
	private int start;
	public ContactPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactPageData(ArrayList<Contact> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Contact> getList() {
		return list;
	}
	public void setList(ArrayList<Contact> list) {
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
