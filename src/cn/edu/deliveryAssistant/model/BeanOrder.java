package cn.edu.deliveryAssistant.model;

public class BeanOrder {
	private int order_id;
	private int merchandise_id;
	private int count;
	private double total_price;
	private double discount_unit_price;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getMerchandise_id() {
		return merchandise_id;
	}
	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public double getDiscount_unit_price() {
		return discount_unit_price;
	}
	public void setDiscount_unit_price(double discount_unit_price) {
		this.discount_unit_price = discount_unit_price;
	}
	
}