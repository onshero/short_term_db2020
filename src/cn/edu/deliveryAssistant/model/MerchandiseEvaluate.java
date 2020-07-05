package cn.edu.deliveryAssistant.model;

import java.awt.image.BufferedImage;

import javax.imageio.*;

public class MerchandiseEvaluate {
	private int merchandise_id;
	private int merchant_id;
	private int user_id;
	private String evaluate_content;
	private int order_stars;
	private BufferedImage image;
	public int getMerchandise_id() {
		return merchandise_id;
	}
	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
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
	public String getEvaluate_content() {
		return evaluate_content;
	}
	public void setEvaluate_content(String evaluate_content) {
		this.evaluate_content = evaluate_content;
	}
	public int getOrder_stars() {
		return order_stars;
	}
	public void setOrder_stars(int order_stars) {
		this.order_stars = order_stars;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	

}
