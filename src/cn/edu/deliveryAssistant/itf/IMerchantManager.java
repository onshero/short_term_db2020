package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IMerchantManager {
	//��ʾ�����̼�
	public List<BeanMerchant> loadAll() throws BaseException;
	
	//ģ����ѯ,��ѯ�̼�
	public BeanMerchant load(String merchant_name) throws BaseException;
	
	//����̼�
	public BeanMerchant addMerchant(String merchant_name) throws BaseException;
	
	//ɾ���̼�
	public void deleteMerchant(BeanMerchant merchant) throws BaseException;
	
}
