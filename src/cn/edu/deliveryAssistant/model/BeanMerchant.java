package cn.edu.deliveryAssistant.model;

public class BeanMerchant {
	public static final String[] titles= {"商家","星级","人均消费","总销量"};
	public static BeanMerchant curmerchant=null;
	private int order_id;
	private int merchant_id;
	private String merchant_name;
	private double merchant_star;
	private double consumption_per_person;
	private int total_sale;
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
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public double getMerchant_star() {
		return merchant_star;
	}
	public void setMerchant_star(double merchant_star) {
		this.merchant_star = merchant_star;
	}
	public double getConsumption_per_person() {
		return consumption_per_person;
	}
	public void setConsumption_per_person(double consumption_per_person) {
		this.consumption_per_person = consumption_per_person;
	}
	public int getTotal_sale() {
		return total_sale;
	}
	public void setTotal_sale(int total_sale) {
		this.total_sale = total_sale;
	}
	public String getCell(int col){
		if(col==0) return this.merchant_name;
		else if(col==1) return ""+this.merchant_star;
		else if(col==2) return ""+this.consumption_per_person;
		else if(col==3) return ""+this.total_sale;
		else return "";
	}
}
