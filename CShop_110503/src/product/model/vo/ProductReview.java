package product.model.vo;

public class ProductReview {
	private int prNo;
	private int productNo;
	private String prWriter;
	private int prLevel;
	private String prContent;
	private String prFilepath;
	private int prStar;
	private int prRef;
	private String prDate;
	public ProductReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductReview(int prNo, int productNo, String prWriter, int prLevel, String prContent, String prFilepath,
			int prStar, int prRef, String prDate) {
		super();
		this.prNo = prNo;
		this.productNo = productNo;
		this.prWriter = prWriter;
		this.prLevel = prLevel;
		this.prContent = prContent;
		this.prFilepath = prFilepath;
		this.prStar = prStar;
		this.prRef = prRef;
		this.prDate = prDate;
	}
	public int getPrNo() {
		return prNo;
	}
	public void setPrNo(int prNo) {
		this.prNo = prNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getPrWriter() {
		return prWriter;
	}
	public void setPrWriter(String prWriter) {
		this.prWriter = prWriter;
	}
	public int getPrLevel() {
		return prLevel;
	}
	public void setPrLevel(int prLevel) {
		this.prLevel = prLevel;
	}
	public String getPrContent() {
		return prContent;
	}
	public void setPrContent(String prContent) {
		this.prContent = prContent;
	}
	public String getPrFilepath() {
		return prFilepath;
	}
	public void setPrFilepath(String prFilepath) {
		this.prFilepath = prFilepath;
	}
	public int getPrStar() {
		return prStar;
	}
	public void setPrStar(int prStar) {
		this.prStar = prStar;
	}
	public int getPrRef() {
		return prRef;
	}
	public void setPrRef(int prRef) {
		this.prRef = prRef;
	}
	public String getPrDate() {
		return prDate;
	}
	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}
	
	
}
