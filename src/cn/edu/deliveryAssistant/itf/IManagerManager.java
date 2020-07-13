package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IManagerManager {
	//登录
	public BeanManager login(String name, String pwd) throws BaseException;
	
	//显示管理员
	public List<BeanManager> loadAll() throws BaseException;
	
	//添加管理员
	public BeanManager addManager(String name, String password) throws BaseException;
	
	//删除管理员
	public void deleteManager(BeanManager manager) throws BaseException;

}
