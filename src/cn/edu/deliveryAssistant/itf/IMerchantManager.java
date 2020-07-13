package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IMerchantManager {
	//显示所以商家
	public List<BeanMerchant> loadAll() throws BaseException;
	
	//模糊查询,查询商家
	public BeanMerchant load(String merchant_name) throws BaseException;
	
	//添加商家
	public BeanMerchant addMerchant(String merchant_name) throws BaseException;
	
	//删除商家
	public void deleteMerchant(BeanMerchant merchant) throws BaseException;
	
}
