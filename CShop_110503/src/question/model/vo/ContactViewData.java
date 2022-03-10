package question.model.vo;

import java.util.ArrayList;

public class ContactViewData {
	private ArrayList<CtComment> list;
	private Contact c;
	public ContactViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactViewData(ArrayList<CtComment> list, Contact c) {
		super();
		this.list = list;
		this.c = c;
	}
	public ArrayList<CtComment> getList() {
		return list;
	}
	public void setList(ArrayList<CtComment> list) {
		this.list = list;
	}
	public Contact getC() {
		return c;
	}
	public void setC(Contact c) {
		this.c = c;
	}
	
}
