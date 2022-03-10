package product.model.vo;

public class ProductQNA {
	private int pqNo;
	private int pqWriter;
	private int pqLevel;
	private String pqTitle;
	private String pqContent;
	private int productNo;
	private int pqRef;
	private int pqStatus;
	private String pqDate;
	private String pqWriterId;
	private String productName;
	public ProductQNA() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductQNA(int pqNo, int pqWriter, int pqLevel, String pqTitle, String pqContent, int productNo, int pqRef,
			int pqStatus, String pqDate) {
		super();
		this.pqNo = pqNo;
		this.pqWriter = pqWriter;
		this.pqLevel = pqLevel;
		this.pqTitle = pqTitle;
		this.pqContent = pqContent;
		this.productNo = productNo;
		this.pqRef = pqRef;
		this.pqStatus = pqStatus;
		this.pqDate = pqDate;
	}
	
	public ProductQNA(int pqNo, int pqWriter, int pqLevel, String pqTitle, String pqContent, int productNo, int pqRef,
			int pqStatus, String pqDate, String pqWriterId) {
		super();
		this.pqNo = pqNo;
		this.pqWriter = pqWriter;
		this.pqLevel = pqLevel;
		this.pqTitle = pqTitle;
		this.pqContent = pqContent;
		this.productNo = productNo;
		this.pqRef = pqRef;
		this.pqStatus = pqStatus;
		this.pqDate = pqDate;
		this.pqWriterId = pqWriterId;
	}
	
	public ProductQNA(int pqNo, int pqWriter, int pqLevel, String pqTitle, String pqContent, int productNo, int pqRef,
			int pqStatus, String pqDate, String pqWriterId, String productName) {
		super();
		this.pqNo = pqNo;
		this.pqWriter = pqWriter;
		this.pqLevel = pqLevel;
		this.pqTitle = pqTitle;
		this.pqContent = pqContent;
		this.productNo = productNo;
		this.pqRef = pqRef;
		this.pqStatus = pqStatus;
		this.pqDate = pqDate;
		this.pqWriterId = pqWriterId;
		this.productName = productName;
	}
	public String getPqWriterId() {
		return pqWriterId;
	}
	public void setPqWriterId(String pqWriterId) {
		this.pqWriterId = pqWriterId;
	}
	public int getPqNo() {
		return pqNo;
	}
	public void setPqNo(int pqNo) {
		this.pqNo = pqNo;
	}
	public int getPqWriter() {
		return pqWriter;
	}
	public void setPqWriter(int pqWriter) {
		this.pqWriter = pqWriter;
	}
	public int getPqLevel() {
		return pqLevel;
	}
	public void setPqLevel(int pqLevel) {
		this.pqLevel = pqLevel;
	}
	public String getPqTitle() {
		return pqTitle;
	}
	public void setPqTitle(String pqTitle) {
		this.pqTitle = pqTitle;
	}
	public String getPqContent() {
		return pqContent;
	}
	public void setPqContent(String pqContent) {
		this.pqContent = pqContent;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getPqRef() {
		return pqRef;
	}
	public void setPqRef(int pqRef) {
		this.pqRef = pqRef;
	}
	public int getPqStatus() {
		return pqStatus;
	}
	public void setPqStatus(int pqStatus) {
		this.pqStatus = pqStatus;
	}
	public String getPqDate() {
		return pqDate;
	}
	public void setPqDate(String pqDate) {
		this.pqDate = pqDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
