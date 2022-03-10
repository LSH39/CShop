package admin.model.vo;

import java.util.ArrayList;

import member.model.vo.Member;
import product.model.vo.Product;

public class SalePageData {
	private ArrayList<Product> product;
	private ArrayList<Member> member;
	private Product p;
	private int memberNo;
	private int totalSale;
	private int topSale;
	private String pageNavi;
	private int start;
	public SalePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalePageData(ArrayList<Product> product, ArrayList<Member> member, Product p, int memberNo, int totalSale,
			int topSale, String pageNavi, int start) {
		super();
		this.product = product;
		this.member = member;
		this.p = p;
		this.memberNo = memberNo;
		this.totalSale = totalSale;
		this.topSale = topSale;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	public SalePageData(Product p, int memberNo, int totalSale) {
		super();
		this.p = p;
		this.memberNo = memberNo;
		this.totalSale = totalSale;
	}
	public ArrayList<Product> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	public ArrayList<Member> getMember() {
		return member;
	}
	public void setMember(ArrayList<Member> member) {
		this.member = member;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}
	public int getTopSale() {
		return topSale;
	}
	public void setTopSale(int topSale) {
		this.topSale = topSale;
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
