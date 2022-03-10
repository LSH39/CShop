package product.model.vo;

import java.util.ArrayList;

public class ProductPageData {
	private ArrayList<Product> pList;
	private ArrayList<ProductOption> poList;
	private ArrayList<ProductReview> prList;
	private ArrayList<ProductQNA> pqList;
	private String pageNavi;
	private int start;
	private int num;
	public ProductPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductPageData(ArrayList<ProductQNA> pqList, String pageNavi, int start, int num) {
		super();
		this.pqList = pqList;
		this.pageNavi = pageNavi;
		this.start = start;
		this.num = num;
	}
	public ProductPageData(ArrayList<Product> pList, ArrayList<ProductOption> poList, String pageNavi, int start) {
		super();
		this.pList = pList;
		this.poList = poList;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	public ProductPageData(ArrayList<ProductReview> prList, String pageNavi, int start) {
		super();
		this.prList = prList;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<ProductReview> getPrList() {
		return prList;
	}
	public void setPrList(ArrayList<ProductReview> prList) {
		this.prList = prList;
	}
	public ArrayList<Product> getpList() {
		return pList;
	}
	public void setpList(ArrayList<Product> pList) {
		this.pList = pList;
	}
	public ArrayList<ProductOption> getPoList() {
		return poList;
	}
	public void setPoList(ArrayList<ProductOption> poList) {
		this.poList = poList;
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
	public ArrayList<ProductQNA> getPqList() {
		return pqList;
	}
	public void setPqList(ArrayList<ProductQNA> pqList) {
		this.pqList = pqList;
	}
	
	
}
