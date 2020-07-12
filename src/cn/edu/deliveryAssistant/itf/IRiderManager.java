package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.model.MerchandiseEvaluate;
import cn.edu.deliveryAssistant.model.RiderReceive;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IRiderManager {
	//��ʾ��������
	public List<BeanRider> loadAll() throws BaseException;
	
	//��׼����ĳ����
	//public BeanRider search(BeanRider rider) throws BaseException;
	
	//ģ������ĳ����
	public List<BeanRider> search(String rider_name) throws BaseException;
	
	//�����ֵ�����
	public List<String> load(BeanRider rider) throws BaseException;
	
	//�û�����
	public RiderReceive userEvaluate(MerchandiseEvaluate merchandiseEvaluate) throws BaseException;
	
	//���ֽӵ���
	public int countOrdre(BeanRider rider) throws BaseException;
	
	//�����ܵ�
	public double sumMoney(BeanRider rider) throws BaseException;
	
	//�������
	public BeanRider addRider(String rider_name, String status) throws BaseException;
	
	//ɾ������
	public void deleteRider(BeanRider rider) throws BaseException;
	

}
