package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanPlan;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IPanManager {
	//��ʾ��������
	public List<BeanPlan> loadAll() throws BaseException;
	
	public List<BeanPlan> load(BeanMerchant merchant) throws BaseException;
	
	//��ӷ���
	public BeanPlan addPlan(BeanMerchant merchant, double reduce_amount, double discount_amount, boolean used_with_coupon) throws BaseException;
	
	//ɾ������
	public void delete(BeanMerchant merchant, BeanPlan plan) throws BaseException;

}
