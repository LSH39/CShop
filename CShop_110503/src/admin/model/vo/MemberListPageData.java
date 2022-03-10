package admin.model.vo;

import java.util.ArrayList;

import member.model.vo.Member;

public class MemberListPageData {
	private ArrayList<Member> member;
	private int purchaseSixMonth;
	private int newMembership;
	private String pageNavi;
	private int start;
	public MemberListPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberListPageData(ArrayList<Member> member,int purchaseSixMonth, int newMembership, String pageNavi, int start) {
		super();
		this.member = member;
		this.purchaseSixMonth = purchaseSixMonth;
		this.newMembership = newMembership;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	
	public MemberListPageData(ArrayList<Member> member, int purchaseSixMonth, int newMembership, String pageNavi) {
		super();
		this.member = member;
		this.purchaseSixMonth = purchaseSixMonth;
		this.newMembership = newMembership;
		this.pageNavi = pageNavi;
	}
	
	public MemberListPageData(ArrayList<Member> member, String pageNavi, int start) {
		super();
		this.member = member;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Member> getMember() {
		return member;
	}
	public void setMember(ArrayList<Member> member) {
		this.member = member;
	}
	
	public int getPurchaseSixMonth() {
		return purchaseSixMonth;
	}
	public void setPurchaseSixMonth(int purchaseSixMonth) {
		this.purchaseSixMonth = purchaseSixMonth;
	}
	
	public int getNewMembership() {
		return newMembership;
	}
	public void setNewMembership(int newMembership) {
		this.newMembership = newMembership;
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
