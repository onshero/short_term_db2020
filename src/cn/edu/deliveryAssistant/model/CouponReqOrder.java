package cn.edu.deliveryAssistant.model;

public class CouponReqOrder {
	private int user_id;
	private int merchant_id;
	private int coupon_id;
	private int order_req_num;
	private int finished_order_num;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getOrder_req_num() {
		return order_req_num;
	}
	public void setOrder_req_num(int order_req_num) {
		this.order_req_num = order_req_num;
	}
	public int getFinished_order_num() {
		return finished_order_num;
	}
	public void setFinished_order_num(int finished_order_num) {
		this.finished_order_num = finished_order_num;
	}
	

}
