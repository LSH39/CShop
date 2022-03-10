package member.model.vo;

public class ShoppingList {
	private int orderNo;
	private int memberNo;
	private int paymentPrice;
	private String orderDate;
	private int orderStatus;
	private int orderCount;
	private String productName;
	private String productImage;
	public ShoppingList(int orderNo, int memberNo, int paymentPrice, String orderDate, int orderStatus, int orderCount,
			String productName, String productImage) {
		super();
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.paymentPrice = paymentPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderCount = orderCount;
		this.productName = productName;
		this.productImage = productImage;
	}
	public ShoppingList() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
}
