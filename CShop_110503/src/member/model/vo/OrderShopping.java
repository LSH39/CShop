package member.model.vo;

import java.util.ArrayList;

public class OrderShopping {
	private ArrayList<Shopping> slist;
	private Order o;
	public OrderShopping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderShopping(ArrayList<Shopping> slist, Order o) {
		super();
		this.slist = slist;
		this.o = o;
	}
	public ArrayList<Shopping> getSlist() {
		return slist;
	}
	public void setSlist(ArrayList<Shopping> slist) {
		this.slist = slist;
	}
	public Order getO() {
		return o;
	}
	public void setO(Order o) {
		this.o = o;
	}
	
}
