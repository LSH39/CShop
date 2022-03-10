package member.model.vo;

public class Order {
	private int orderNo;
	private String memberName;
	private String memberPhone;
	private String postcode;
	private String addressRoad;
	private String addressDetail;
	private int orderPrice;
	private int deliveryPrice;
	private int orderPoint;
	private int paymentPrice;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(int orderNo, String memberName, String memberPhone, String postcode, String addressRoad,
			String addressDetail, int orderPrice, int deliveryPrice, int orderPoint, int paymentPrice) {
		super();
		this.orderNo = orderNo;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.postcode = postcode;
		this.addressRoad = addressRoad;
		this.addressDetail = addressDetail;
		this.orderPrice = orderPrice;
		this.deliveryPrice = deliveryPrice;
		this.orderPoint = orderPoint;
		this.paymentPrice = paymentPrice;
	}


	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
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
	
}
