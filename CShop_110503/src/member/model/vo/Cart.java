package member.model.vo;

public class Cart {
	
	private int cartNo;
	private int productNo;
	private int memberNo;
	private String productColor;
	private String productSize;
	private int productPrice;
	private int productSeller;
	private String productName;
	private String productImage;
	private int orderAmount;
	private String sellerName;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int cartNo, int productNo, int memberNo, String productColor, String productSize, int productPrice,
			int productSeller, String productName, String productImage, int orderAmount, String sellerName) {
		super();
		this.cartNo = cartNo;
		this.productNo = productNo;
		this.memberNo = memberNo;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.productSeller = productSeller;
		this.productName = productName;
		this.productImage = productImage;
		this.orderAmount = orderAmount;
		this.sellerName = sellerName;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
	public int getProductSeller() {
		return productSeller;
	}
	public void setProductSeller(int productSeller) {
		this.productSeller = productSeller;
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
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}
	