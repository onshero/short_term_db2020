package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanAddress;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IAddressManager {
	//��ʾ��ַ
	public List<BeanAddress> loadAll() throws BaseException;
	
	//����Ĭ�ϵ�ַ
	public void modifyDefault(BeanAddress address) throws BaseException;
	
	//��ӵ�ַ
	public BeanAddress addAddress(BeanUser user, String province, String city, String area, String address, String contact_person, String phone_num) throws BaseException;
	
	//ɾ����ַ
	public void deleteAddress(BeanAddress address) throws BaseException;

}
