package seller.model.vo;

public class OrderInfo {
	private int orderNo;
	private int memberNo;
	private String memberPhone;
	private String memberEmail;
	private String postcode;
	private String addressRoad;
	private String addressDetail;
	private int paymentMethod;
	private int orderPrice;
	private int deliveryPrice;
	private int orderPoint;
	private int paymentPrice;
	private String orderDate;
	private int orderStatus;
	private int sellerNo;
	private int deliveryNo;
	private String deliveryCompany;
	private String deliveryRequest;
	private String impUid;
	private String memberName;
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderInfo(int orderNo, int memberNo, String memberPhone, String memberEmail, String postcode,
			String addressRoad, String addressDetail, int paymentMethod, int orderPrice, int deliveryPrice,
			int orderPoint, int paymentPrice, String orderDate, int orderStatus, int sellerNo, int deliveryNo,
			String deliveryCompany, String deliveryRequest, String impUid) {
		super();
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.postcode = postcode;
		this.addressRoad = addressRoad;
		this.addressDetail = addressDetail;
		this.paymentMethod = paymentMethod;
		this.orderPrice = orderPrice;
		this.deliveryPrice = deliveryPrice;
		this.orderPoint = orderPoint;
		this.paymentPrice = paymentPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.sellerNo = sellerNo;
		this.deliveryNo = deliveryNo;
		this.deliveryCompany = deliveryCompany;
		this.deliveryRequest = deliveryRequest;
		this.impUid = impUid;
	}
	
	public OrderInfo(int orderNo, int memberNo, String memberPhone, String memberEmail, String postcode,
			String addressRoad, String addressDetail, int paymentMethod, int orderPrice, int deliveryPrice,
			int orderPoint, int paymentPrice, String orderDate, int orderStatus, int sellerNo, int deliveryNo,
			String deliveryCompany, String deliveryRequest, String impUid, String memberName) {
		super();
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.postcode = postcode;
		this.addressRoad = addressRoad;
		this.addressDetail = addressDetail;
		this.paymentMethod = paymentMethod;
		this.orderPrice = orderPrice;
		this.deliveryPrice = deliveryPrice;
		this.orderPoint = orderPoint;
		this.paymentPrice = paymentPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.sellerNo = sellerNo;
		this.deliveryNo = deliveryNo;
		this.deliveryCompany = deliveryCompany;
		this.deliveryRequest = deliveryRequest;
		this.impUid = impUid;
		this.memberName = memberName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddressRoad() {
		return addressRoad;
	}
	public void setAddressRoad(String addressRoad) {
		this.addressRoad = addressRoad;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public int getOrderPoint() {
		return orderPoint;
	}
	public void setOrderPoint(int orderPoint) {
		this.orderPoint = orderPoint;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(int sellerNo) {
		this.sellerNo = sellerNo;
	}
	public int getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getDeliveryRequest() {
		return deliveryRequest;
	}
	public void setDeliveryRequest(String deliveryRequest) {
		this.deliveryRequest = deliveryRequest;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	

}	