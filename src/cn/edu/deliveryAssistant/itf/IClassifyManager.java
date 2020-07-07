package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IClassifyManager {
	//显示类别
	public List<BeanMerchandiseClassify> loadAll() throws BaseException;
	
	public List<BeanMerchandiseClassify> loadAll(BeanMerchant merchant) throws BaseException;
	
	//添加类别
	public BeanMerchandiseClassify addClassify(BeanMerchant merchant,String classify_name) throws BaseException;
	
	//删除类别
	public void deleteClassify(BeanMerchant merchant,BeanMerchandiseClassify classify) throws BaseException;
	

}
