package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class BeanRider {
	private int rider_id;
	private String rider_name;
	private Date date_on_board;
	private String rider_status;
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
	

}
