package member.model.vo;

public class Shopping {
	private int orderNo;
	private int opNo;
	private String productColor;
	private String productSize;
	private int productPrice;
	private int orderCount;
	private int productNo;
	private String productName;
	private String productImage;
	private int orderStatus;
	
	public Shopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Shopping(int orderNo, int opNo, String productColor, String productSize, int productPrice, int orderCount,
			int productNo, String productName, String productImage, int orderStatus) {
		super();
		this.orderNo = orderNo;
		this.opNo = opNo;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.orderCount = orderCount;
		this.productNo = productNo;
		this.productName = productName;
		this.productImage = productImage;
		this.orderStatus = orderStatus;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOpNo() {
		return opNo;
	}

	public void setOpNo(int opNo) {
		this.opNo = opNo;
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

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
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

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}