package seller.model.vo;

public class OrderProduct {
	private int opNo;
	private int orderNo;
	private int productNo;
	private String productColor;
	private String productSize;
	private int productPrice;
	private int orderCount;
	private int sellerNo;
	private String productName;
	public OrderProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderProduct(int opNo, int orderNo, int productNo, String productColor, String productSize, int productPrice,
			int orderCount) {
		super();
		this.opNo = opNo;
		this.orderNo = orderNo;
		this.productNo = productNo;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.orderCount = orderCount;
	}
	
	public OrderProduct(int opNo, int orderNo, int productNo, String productColor, String productSize, int productPrice,
			int orderCount, int sellerNo, String productName) {
		super();
		this.opNo = opNo;
		this.orderNo = orderNo;
		this.productNo = productNo;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.orderCount = orderCount;
		this.sellerNo = sellerNo;
		this.productName = productName;
	}
	public int getOpNo() {
		return opNo;
	}
	public void setOpNo(int opNo) {
		this.opNo = opNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(int sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
