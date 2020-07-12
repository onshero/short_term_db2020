package cn.edu.deliveryAssistant.model;

public class BeanAddress {
	public static final String[] titles= {"序号","省","市","区","地址","联系人","联系电话","默认"};
	
	private int address_id;
	private int user_id;
	private String province;
	private String city;
	private String area;
	private String address;
	private String contact_person;
	private String phone_num;
	private boolean used;
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	public String getCell(int col){
		if(col==0) return ""+this.address_id;
		else if(col==1) return this.province;
		else if(col==2) return this.city;
		else if(col==3) return this.area;
		else if(col==4) return this.address;
		else if(col==5) return this.contact_person;
		else if(col==6) return this.phone_num;
		else if(col==7) return used?"USING":"";
		else return "";
	}
}
