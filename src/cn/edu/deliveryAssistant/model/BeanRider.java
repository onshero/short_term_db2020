package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class BeanRider {
	public static final String[] titles= {"序号","骑手姓名","注册时间","身份"};
	public static final String[] titles2= {"序号","骑手姓名","单数","收入"};
	public static BeanRider currider=null;
	private int rider_id;
	private String rider_name;
	private Date date_on_board;
	private String rider_status;
	private int order_num;
	private double sum_income;
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public String getRider_name() {
		return rider_name;
	}
	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
	}
	public Date getDate_on_board() {
		return date_on_board;
	}
	public void setDate_on_board(Date date_on_board) {
		this.date_on_board = date_on_board;
	}
	public String getRider_status() {
		return rider_status;
	}
	public void setRider_status(String rider_status) {
		this.rider_status = rider_status;
	}
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public double getSum_income() {
		return sum_income;
	}
	public void setSum_income(double sum_income) {
		this.sum_income = sum_income;
	}
	public String getCell(int col){
		if(col==0) return ""+this.rider_id;
		else if(col==1) return this.rider_name;
		else if(col==2) return this.date_on_board.toString();
		else if(col==3) return this.rider_status;
		else return "";
	}
	public String getCell2(int col){
		if(col==0) return ""+this.rider_id;
		else if(col==1) return this.rider_name;
		else if (col==2) return ""+this.order_num;
		else if(col==3) return ""+this.sum_income;
		else return "";
	}

}
