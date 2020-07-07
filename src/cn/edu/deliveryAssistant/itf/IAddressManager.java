package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanAddress;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IAddressManager {
	//显示地址
	public List<BeanAddress> loadAll() throws BaseException;
	
	//更改默认地址
	public void modifyDefault(BeanAddress address) throws BaseException;
	
	//添加地址
	public BeanAddress addAddress(BeanUser user, String province, String city, String area, String address, String contact_person, String phone_num) throws BaseException;
	
	//删除地址
	public void deleteAddress(BeanAddress address) throws BaseException;

}
