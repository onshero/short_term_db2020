package cn.edu.deliveryAssistant.model;

import java.sql.Date;

public class BeanUser {
	public static BeanUser currentLoginUser=null;
	private int user_id;
	private String user_name;
	private boolean user_sex;
	private String user_password;
	private String user_phone_num;
	private String user_email;
	private String user_city;
	private Date registration_date;
	private boolean isVIP;
	private Date VIP_end_date;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Boolean getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(Boolean user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone_num() {
		return user_phone_num;
	}
	public void setUser_phone_num(String user_phone_num) {
		this.user_phone_num = user_phone_num;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public Boolean getIsVIP() {
		return isVIP;
	}
	public void setIsVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}
	public Date getVIP_end_date() {
		return VIP_end_date;
	}
	public void setVIP_end_date(Date vIP_end_date) {
		VIP_end_date = vIP_end_date;
	}
	
}
