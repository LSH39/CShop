package product.model.vo;

public class Product {
	private int productNo;
	private String productId;
	private int productSeller;
	private int productCategory1;
	private int productCategory2;
	private String productName;
	private String productContent;
	private String productBrand;
	private int productImports;
	private int productStatus;
	private String productImage;
	private String productFile;
	private int productPrice;
	private int productSellCount;
	private String sellerName;
	private String businessNo;
	private int rnum;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int productNo, String productId, int productSeller, int productCategory1, int productCategory2,
			String productName, String productContent, String productBrand, int productImports, int productStatus,
			String productImage, String productFile) {
		super();
		this.productNo = productNo;
		this.productId = productId;
		this.productSeller = productSeller;
		this.productCategory1 = productCategory1;
		this.productCategory2 = productCategory2;
		this.productName = productName;
		this.productContent = productContent;
		this.productBrand = productBrand;
		this.productImports = productImports;
		this.productStatus = productStatus;
		this.productImage = productImage;
		this.productFile = productFile;
	}
	
	
	public Product(int productNo, String productId, int productSeller, int productCategory1, int productCategory2,
			String productName, String productContent, String productBrand, int productImports, int productStatus,
			String productImage, String productFile, int productPrice) {
		super();
		this.productNo = productNo;
		this.productId = productId;
		this.productSeller = productSeller;
		this.productCategory1 = productCategory1;
		this.productCategory2 = productCategory2;
		this.productName = productName;
		this.productContent = productContent;
		this.productBrand = productBrand;
		this.productImports = productImports;
		this.productStatus = productStatus;
		this.productImage = productImage;
		this.productFile = productFile;
		this.productPrice = productPrice;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getProductSeller() {
		return productSeller;
	}
	public void setProductSeller(int productSeller) {
		this.productSeller = productSeller;
	}
	public int getProductCategory1() {
		return productCategory1;
	}
	public void setProductCategory1(int productCategory1) {
		this.productCategory1 = productCategory1;
	}
	public int getProductCategory2() {
		return productCategory2;
	}
	public void setProductCategory2(int productCategory2) {
		this.productCategory2 = productCategory2;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public int getProductImports() {
		return productImports;
	}
	public void setProductImports(int productImports) {
		this.productImports = productImports;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductFile() {
		return productFile;
	}
	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}
	
	public int getProductSellCount() {
		return productSellCount;
	}
	public void setProductSellCount(int productSellCount) {
		this.productSellCount = productSellCount;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public int getrnum() {
		return rnum;
	}
	public void setrNum(int rnum) {
		this.rnum = rnum;
	}
	public Product(int rnum, int productNo, String productName, int productStatus, String productFile, int productSellCount,
			String sellerName, String businessNo) {
		super();
		this.rnum = rnum;
		this.productNo = productNo;
		this.productName = productName;
		this.productStatus = productStatus;
		this.productFile = productFile;
		this.productSellCount = productSellCount;
		this.sellerName = sellerName;
		this.businessNo = businessNo;
	}
	
	
}
