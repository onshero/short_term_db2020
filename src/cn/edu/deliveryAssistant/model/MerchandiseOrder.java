package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class MerchandiseOrder {
	public static final String[] titles= {"�������","�̼����","�������","�������","�Ż�ȯ���","��ַ���","ԭʼ���","������","�µ�ʱ��","�ʹ�ʱ��","����״̬"};
	
	private int order_id;
	private int merchant_id;
	private int user_id;
	private int rider_id;
	private int plan_id;
	private int coupon_id;
	private int address_id;
	private double origin_price;
	private double final_price;
	private Date order_date;
	private Date req_delivery_date;
	private String order_status;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(int merchant_id) {
		this.merchant_id = merchant_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public double getOrigin_price() {
		return origin_price;
	}
	public void setOrigin_price(double origin_price) {
		this.origin_price = origin_price;
	}
	public double getFinal_price() {
		return final_price;
	}
	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getReq_delivery_date() {
		return req_delivery_date;
	}
	public void setReq_delivery_date(Date req_delivery_date) {
		this.req_delivery_date = req_delivery_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	public String getCell(int col){
		if(col==0) return ""+this.order_id;
		else if(col==1) return ""+this.merchant_id;
		else if(col==2) return ""+this.rider_id;
		else if(col==3) return ""+this.plan_id;
		else if(col==4) return ""+this.coupon_id;
		else if(col==5) return ""+this.address_id;
		else if(col==6) return ""+this.origin_price;
		else if(col==7) return ""+this.final_price;
		else if(col==8) return this.order_date.toString();
		else if(col==9) return ""+this.req_delivery_date.toString();
		else if(col==10) return this.order_status;
		else return "";
	}

}
