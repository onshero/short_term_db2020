package cn.edu.deliveryAssistant.model;

public class BeanMerchandise {
	public static final String[] titles= {"商品名称","单价","优惠价格","余量"};
	public static BeanMerchandise curmerchandise=null;
	
	private int merchandise_id;
	private int classify_id;
	private int merchant_id;
	private String mercandise_name;
	private double unit_price;
	private double discount_unit_price;
	private int remain;
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
	public String getMercandise_name() {
		return mercandise_name;
	}
	public void setMercandise_name(String mercandise_name) {
		this.mercandise_name = mercandise_name;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getDiscount_unit_price() {
		return discount_unit_price;
	}
	public void setDiscount_unit_price(double discount_unit_price) {
		this.discount_unit_price = discount_unit_price;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	
	public String getCell(int col){
		if(col==0) return this.mercandise_name;
		else if(col==1) return ""+this.unit_price;
		else if(col==2) return ""+this.discount_unit_price;
		else if(col==3) return ""+this.remain;
		else return "";
	}
}
