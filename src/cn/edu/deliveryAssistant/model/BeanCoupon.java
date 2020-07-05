package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class BeanCoupon {
	private int coupon_id;
	private int merchant_id;
	private double discount_amount;
	private int order_req_num;
	private Date start_date;
	private Date end_date;
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	public double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public int getOrder_req_num() {
		return order_req_num;
	}
	public void setOrder_req_num(int order_req_num) {
		this.order_req_num = order_req_num;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
}
