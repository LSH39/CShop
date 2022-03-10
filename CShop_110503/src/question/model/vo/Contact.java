package question.model.vo;

public class Contact {
	private int contactNo;
	private String contactTitle;
	private String contactContent;
	private String contactWriter;
	private String regDate;
	private String filename;
	private String filepath;
	private int ctCount;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(int contactNo, String contactTitle, String contactContent, String contactWriter, String regDate,
			String filename, String filepath, int ctCount) {
		super();
		this.contactNo = contactNo;
		this.contactTitle = contactTitle;
		this.contactContent = contactContent;
		this.contactWriter = contactWriter;
		this.regDate = regDate;
		this.filename = filename;
		this.filepath = filepath;
		this.ctCount = ctCount;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getContactContent() {
		return contactContent;
	}
	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}
	public String getContactWriter() {
		return contactWriter;
	}
	public void setContactWriter(String contactWriter) {
		this.contactWriter = contactWriter;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getCtCount() {
		return ctCount;
	}
	public void setCtCount(int ctCount) {
		this.ctCount = ctCount;
	}


	
	
}
