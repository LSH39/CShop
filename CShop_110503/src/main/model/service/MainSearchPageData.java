package main.model.service;

import java.util.ArrayList;

import product.model.vo.Product;

public class MainSearchPageData {
	private ArrayList<Product> list;
	private String pageNavi;
	private int start;
	public MainSearchPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainSearchPageData(ArrayList<Product> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Product> getList() {
		return list;
	}
	public void setList(ArrayList<Product> list) {
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
