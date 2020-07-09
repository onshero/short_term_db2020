package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanPlan;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IPanManager {
	//显示满减方案
	public List<BeanPlan> loadAll() throws BaseException;
	
	public List<BeanPlan> load(BeanMerchant merchant) throws BaseException;
	
	//添加方案
	public BeanPlan addPlan(BeanMerchant merchant, double reduce_amount, double discount_amount, boolean used_with_coupon) throws BaseException;
	
	//删除方案
	public void delete(BeanMerchant merchant, BeanPlan plan) throws BaseException;

}
