package cn.edu.deliveryAssistant.model;

public class BeanMerchant {
	private int merchant_id;
	private String merchant_name;
	private double merchant_star;
	private double consumption_per_person;
	private int total_sale;
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
	
}
