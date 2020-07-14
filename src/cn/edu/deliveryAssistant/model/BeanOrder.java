package cn.edu.deliveryAssistant.model;

public class BeanOrder {
	public static final String[] titles= {"商品","数量","总价","单品优惠价格"};
	public static BeanOrder curorder=null;
	
	private int order_id;
	private int merchandise_id;
	private int classify_id;
	private int merchant_id;
	private int user_id;
	private String merchandise_name;
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
	
	public int getClassify_id() {
		return classify_id;
	}
	public void setClassify_id(int classify_id) {
		this.classify_id = classify_id;
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
	public String getMerchandise_name() {
		return merchandise_name;
	}
	public void setMerchandise_name(String merchandise_name) {
		this.merchandise_name = merchandise_name;
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
	
	public String getCell(int col){
		if(col==0) return this.merchandise_name;
		else if(col==1) return ""+this.count;
		else if(col==2) return ""+this.total_price;
		else if(col==3) return ""+this.discount_unit_price;
		else return "";
	}
}
