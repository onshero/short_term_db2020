package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanOrder;
import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IOrderManager {
	//��ʾ����
	public List<BeanOrder> loadAll() throws BaseException;
	
	public List<MerchandiseOrder> load(BeanUser user) throws BaseException;
	
	public List<BeanOrder> load(MerchandiseOrder merchandiseOrder) throws BaseException;
	
	//���ݶ���״̬��ʾ����
	public List<MerchandiseOrder> load(BeanUser user, String status) throws BaseException;
	
	//��Ӷ���
	public BeanOrder addOrder(BeanMerchandise merchandise, int num) throws BaseException;
	
	public MerchandiseOrder addtotalOrder(BeanUser user,BeanMerchant merchant,BeanRider rider,BeanOrder order) throws  BaseException;
	
	//�޸Ķ���״̬
	public MerchandiseOrder modifyOrderStatus(MerchandiseOrder merchandiseOrder, String status) throws BaseException;
	
	//ɾ������
	public void deleteOrder(BeanOrder order) throws BaseException;

}
