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
	//显示订单
	public List<BeanOrder> loadAll() throws BaseException;
	
	public List<MerchandiseOrder> load(BeanUser user) throws BaseException;
	
	public List<BeanOrder> load(MerchandiseOrder merchandiseOrder) throws BaseException;
	
	//根据订单状态显示订单
	public List<MerchandiseOrder> load(BeanUser user, String status) throws BaseException;
	
	//添加订单
	public BeanOrder addOrder(BeanMerchandise merchandise, int num) throws BaseException;
	
	public MerchandiseOrder addtotalOrder(BeanUser user,BeanMerchant merchant,BeanRider rider,BeanOrder order) throws  BaseException;
	
	//修改订单状态
	public MerchandiseOrder modifyOrderStatus(MerchandiseOrder merchandiseOrder, String status) throws BaseException;
	
	//删除订单
	public void deleteOrder(BeanOrder order) throws BaseException;

}
