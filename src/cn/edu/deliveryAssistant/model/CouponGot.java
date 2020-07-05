package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class CouponGot {
	private int user_id;
	private int coupon_id;
	private int merchant_id;
	private double discount_amount;
	private int got_num;
	private Date end_date;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
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
	public int getGot_num() {
		return got_num;
	}
	public void setGot_num(int got_num) {
		this.got_num = got_num;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	

}
