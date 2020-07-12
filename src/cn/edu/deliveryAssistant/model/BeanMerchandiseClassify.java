package cn.edu.deliveryAssistant.model;

public class BeanMerchandiseClassify {
	public static final String[] titles= {"类名","商品数"};
	
	private int classify_id;
	private int merchant_id;
	private String classify_name;
	private int merchandise_number;
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
	public String getClassify_name() {
		return classify_name;
	}
	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}
	public int getMerchandise_number() {
		return merchandise_number;
	}
	public void setMerchandise_number(int merchandise_number) {
		this.merchandise_number = merchandise_number;
	}
	public String getCell(int col){
		if(col==0) return this.classify_name;
		else if(col==1) return ""+this.merchandise_number;
		else return "";
	}

}
