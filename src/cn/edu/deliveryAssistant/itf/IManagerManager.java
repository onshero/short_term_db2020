package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IManagerManager {
	//��¼
	public BeanManager login(String name, String pwd) throws BaseException;
	
	//��ʾ����Ա
	public List<BeanManager> loadAll() throws BaseException;
	
	//��ӹ���Ա
	public BeanManager addManager(String name, String password) throws BaseException;
	
	//ɾ������Ա
	public void deleteManager(BeanManager manager) throws BaseException;

}
