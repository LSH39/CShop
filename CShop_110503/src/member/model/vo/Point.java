package member.model.vo;

public class Point {
	private int pointNo;
	private int memberNo;
	private int pointLevel;
	private int pointValue;
	private String pointDate;
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Point(int pointNo, int memberNo, int pointLevel, int pointValue, String pointDate) {
		super();
		this.pointNo = pointNo;
		this.memberNo = memberNo;
		this.pointLevel = pointLevel;
		this.pointValue = pointValue;
		this.pointDate = pointDate;
	}
	public int getPointNo() {
		return pointNo;
	}
	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getPointLevel() {
		return pointLevel;
	}
	public void setPointLevel(int pointLevel) {
		this.pointLevel = pointLevel;
	}
	public int getPointValue() {
		return pointValue;
	}
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	public String getPointDate() {
		return pointDate;
	}
	public void setPointDate(String pointDate) {
		this.pointDate = pointDate;
	}
	
	
}
