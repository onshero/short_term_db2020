package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanOrder;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.MerchandiseEvaluate;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IMerchandiseManager {
	//显示商品
	public List<BeanMerchandise> loadAll() throws BaseException;
	
	public List<BeanMerchandise> load(BeanMerchant merchant) throws BaseException;
	
	public List<BeanMerchandise> load(BeanMerchandiseClassify classify) throws BaseException;
	
	//查询商品
	public BeanMerchandise search(String merchandise_name) throws BaseException;
	
	public List<BeanMerchandise> searchbyclassify(String classify_name) throws BaseException;
	
	public List<BeanMerchandise> searchbyclassify(BeanMerchant merchant,String classify_name) throws BaseException;
	
	//添加商品
	public BeanMerchandise addMerchandise(BeanMerchant merchant, BeanMerchandiseClassify classify, String name,double unit_price, double discount_unit_price, int remain) throws BaseException;
	
	//删除商品
	public void deleteMerchandise(BeanMerchandise merchandise) throws BaseException;
	
	//显示评价
	public List<MerchandiseEvaluate> loadEvaluation(BeanMerchandise merchandise) throws BaseException;
	
	//用户评价
	public MerchandiseEvaluate userEvaluate(MerchandiseOrder merchandiseOrder) throws BaseException;

}
