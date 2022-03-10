package admin.model.vo;

import java.util.ArrayList;

import product.model.vo.Product;

public class ProductCheckPageData {
	private ArrayList<Product> product;
	private String pageNavi;
	private int start;
	public ProductCheckPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductCheckPageData(ArrayList<Product> product, String pageNavi, int start) {
		super();
		this.product = product;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	public ProductCheckPageData(ArrayList<Product> product) {
		super();
		this.product = product;
	}
	public ArrayList<Product> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
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
