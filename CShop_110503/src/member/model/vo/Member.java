package member.model.vo;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private String memberNickname;
	private String memberPhone;
	private String memberBirthday;
	private int memberGender;
	private String postcode1;
	private String addressRoad1;
	private String addressDetail1;
	private String postcode2;
	private String addressRoad2;
	private String addressDetail2;
	private int membership;
	private int memberPoint;
	private int memberReceiveEmail;
	private int memberReceiveSms;
	private String businessNo;
	private int memberLevel;
	private int memberStatus;
	private String enrollDate;
	private String withdrawDate;
	private int saleProduct;
	private int orderCount;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberNo, String memberId, String memberEmail, String memberPassword, String memberName,
			String memberNickname, String memberPhone, String memberBirthday, int memberGender, String postcode1,
			String addressRoad1, String addressDetail1, String postcode2, String addressRoad2, String addressDetail2,
			int membership, int memberPoint, int memberReceiveEmail, int memberReceiveSms, String businessNo,
			int memberLevel, int memberStatus, String enrollDate, String withdrawDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberPhone = memberPhone;
		this.memberBirthday = memberBirthday;
		this.memberGender = memberGender;
		this.postcode1 = postcode1;
		this.addressRoad1 = addressRoad1;
		this.addressDetail1 = addressDetail1;
		this.postcode2 = postcode2;
		this.addressRoad2 = addressRoad2;
		this.addressDetail2 = addressDetail2;
		this.membership = membership;
		this.memberPoint = memberPoint;
		this.memberReceiveEmail = memberReceiveEmail;
		this.memberReceiveSms = memberReceiveSms;
		this.businessNo = businessNo;
		this.memberLevel = memberLevel;
		this.memberStatus = memberStatus;
		this.enrollDate = enrollDate;
		this.withdrawDate = withdrawDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public int getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(int memberGender) {
		this.memberGender = memberGender;
	}
	public String getPostcode1() {
		return postcode1;
	}
	public void setPostcode1(String postcode1) {
		this.postcode1 = postcode1;
	}
	public String getAddressRoad1() {
		return addressRoad1;
	}
	public void setAddressRoad1(String addressRoad1) {
		this.addressRoad1 = addressRoad1;
	}
	public String getAddressDetail1() {
		return addressDetail1;
	}
	public void setAddressDetail1(String addressDetail1) {
		this.addressDetail1 = addressDetail1;
	}
	public String getPostcode2() {
		return postcode2;
	}
	public void setPostcode2(String postcode2) {
		this.postcode2 = postcode2;
	}
	public String getAddressRoad2() {
		return addressRoad2;
	}
	public void setAddressRoad2(String addressRoad2) {
		this.addressRoad2 = addressRoad2;
	}
	public String getAddressDetail2() {
		return addressDetail2;
	}
	public void setAddressDetail2(String addressDetail2) {
		this.addressDetail2 = addressDetail2;
	}
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
		this.membership = membership;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public int getMemberReceiveEmail() {
		return memberReceiveEmail;
	}
	public void setMemberReceiveEmail(int memberReceiveEmail) {
		this.memberReceiveEmail = memberReceiveEmail;
	}
	public int getMemberReceiveSms() {
		return memberReceiveSms;
	}
	public void setMemberReceiveSms(int memberReceiveSms) {
		this.memberReceiveSms = memberReceiveSms;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public int getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getWithdrawDate() {
		return withdrawDate;
	}
	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
	// 異붽�
	public int getSaleProduct() {
		return saleProduct;
	}
	public void setSaleProduct(int saleProduct) {
		this.saleProduct = saleProduct;
	}
	
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public Member(int memberNo, String memberId, String memberName, String memberNickname, String memberPhone,
			String enrollDate, int saleProduct) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberPhone = memberPhone;
		this.enrollDate = enrollDate;
		this.saleProduct = saleProduct;
	}
	public Member(int memberNo, String memberId, String memberName, String memberNickname, String memberPhone,
			String enrollDate, String withdrawDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberPhone = memberPhone;
		this.enrollDate = enrollDate;
		this.withdrawDate = withdrawDate;
	}
	public Member(int memberNo, String memberId, int membership, int orderCount) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.membership = membership;
		this.orderCount = orderCount;
	}
	
	
	
}
