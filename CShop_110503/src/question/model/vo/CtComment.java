package question.model.vo;

public class CtComment {
	private int ctNo;
	private int ctLevel;
	private String ctWriter;
	private String ctContent;
	private String ctDate;
	private int contactRef;
	private int ctRef;
	public CtComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CtComment(int ctNo, int ctLevel, String ctWriter, String ctContent, String ctDate, int contactRef,
			int ctRef) {
		super();
		this.ctNo = ctNo;
		this.ctLevel = ctLevel;
		this.ctWriter = ctWriter;
		this.ctContent = ctContent;
		this.ctDate = ctDate;
		this.contactRef = contactRef;
		this.ctRef = ctRef;
	}
	public int getCtNo() {
		return ctNo;
	}
	public void setCtNo(int ctNo) {
		this.ctNo = ctNo;
	}
	public int getCtLevel() {
		return ctLevel;
	}
	public void setCtLevel(int ctLevel) {
		this.ctLevel = ctLevel;
	}
	public String getCtWriter() {
		return ctWriter;
	}
	public void setCtWriter(String ctWriter) {
		this.ctWriter = ctWriter;
	}
	public String getCtContentBr() {
		return ctContent.replaceAll("\r\n", "<br>");
	}
	public String getCtContent() {
		return ctContent;
	}
	public void setCtContent(String ctContent) {
		this.ctContent = ctContent;
	}
	public String getCtDate() {
		return ctDate;
	}
	public void setCtDate(String ctDate) {
		this.ctDate = ctDate;
	}
	public int getContactRef() {
		return contactRef;
	}
	public void setContactRef(int contactRef) {
		this.contactRef = contactRef;
	}
	public int getCtRef() {
		return ctRef;
	}
	public void setCtRef(int ctRef) {
		this.ctRef = ctRef;
	}
	
	
}
