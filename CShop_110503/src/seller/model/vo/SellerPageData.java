package seller.model.vo;

public class SellerPageData {
	private int orderTodayCnt;
	private int orderAmountSum;
	private int payCompleteCnt;
	private int deliveryingCnt;
	private int deliveredCnt;
	private int orderCount[];
	private String orderDay[];
	private int orderAmount[];
	public SellerPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SellerPageData(int orderTodayCnt, int orderAmountSum, int payCompleteCnt, int deliveryingCnt,
			int deliveredCnt, int[] orderCount, String[] orderDay, int[] orderAmount) {
		super();
		this.orderTodayCnt = orderTodayCnt;
		this.orderAmountSum = orderAmountSum;
		this.payCompleteCnt = payCompleteCnt;
		this.deliveryingCnt = deliveryingCnt;
		this.deliveredCnt = deliveredCnt;
		this.orderCount = orderCount;
		this.orderDay = orderDay;
		this.orderAmount = orderAmount;
	}
	public int getOrderTodayCnt() {
		return orderTodayCnt;
	}
	public void setOrderTodayCnt(int orderTodayCnt) {
		this.orderTodayCnt = orderTodayCnt;
	}
	public int getOrderAmountSum() {
		return orderAmountSum;
	}
	public void setOrderAmountSum(int orderAmountSum) {
		this.orderAmountSum = orderAmountSum;
	}
	public int getPayCompleteCnt() {
		return payCompleteCnt;
	}
	public void setPayCompleteCnt(int payCompleteCnt) {
		this.payCompleteCnt = payCompleteCnt;
	}
	public int getDeliveryingCnt() {
		return deliveryingCnt;
	}
	public void setDeliveryingCnt(int deliveryingCnt) {
		this.deliveryingCnt = deliveryingCnt;
	}
	public int getDeliveredCnt() {
		return deliveredCnt;
	}
	public void setDeliveredCnt(int deliveredCnt) {
		this.deliveredCnt = deliveredCnt;
	}
	public int[] getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int[] orderCount) {
		this.orderCount = orderCount;
	}
	public String[] getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(String[] orderDay) {
		this.orderDay = orderDay;
	}
	public int[] getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int[] orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	
}
