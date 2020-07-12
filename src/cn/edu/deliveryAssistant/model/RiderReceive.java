package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class RiderReceive {
	public static final String[] titles= {"时间","用户评价","单笔收入"};
	
	private int order_id;
	private int rider_id;
	private Date order_date;
	private String user_evaluate;
	private double single_income;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getUser_evaluate() {
		return user_evaluate;
	}
	public void setUser_evaluate(String user_evaluate) {
		this.user_evaluate = user_evaluate;
	}
	public double getSingle_income() {
		return single_income;
	}
	public void setSingle_income(double single_income) {
		this.single_income = single_income;
	}
	public String getCell(int col){
		if(col==0) return this.order_date.toString();
		else if(col==1) return this.user_evaluate;
		else if(col==2) return ""+this.single_income;
		else return "";
	}

}
