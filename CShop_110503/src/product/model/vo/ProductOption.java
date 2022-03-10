package product.model.vo;

public class ProductOption {
	private int optionNo;
	private String productId;
	private String productColor;
	private String productSize;
	private int productPrice;
	private int productCount;
	public ProductOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductOption(int optionNo, String productId, String productColor, String productSize, int productPrice,
			int productCount) {
		super();
		this.optionNo = optionNo;
		this.productId = productId;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productPrice = productPrice;
		this.productCount = productCount;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	
}
