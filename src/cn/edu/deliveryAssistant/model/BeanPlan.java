package cn.edu.deliveryAssistant.model;

public class BeanPlan {
	private int plan_id;
	private double reduce_amount;
	private double plan_discount_amount;
	private boolean used_with_coupon;
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public double getReduce_amount() {
		return reduce_amount;
	}
	public void setReduce_amount(double reduce_amount) {
		this.reduce_amount = reduce_amount;
	}
	public double getPlan_discount_amount() {
		return plan_discount_amount;
	}
	public void setPlan_discount_amount(double plan_discount_amount) {
		this.plan_discount_amount = plan_discount_amount;
	}
	public boolean isUsed_with_coupon() {
		return used_with_coupon;
	}
	public void setUsed_with_coupon(boolean used_with_coupon) {
		this.used_with_coupon = used_with_coupon;
	}
	
}
