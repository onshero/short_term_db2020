package cn.edu.deliveryAssistant.itf;

import java.sql.Date;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanCoupon;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;

public interface ICouponManager {
	//œ‘ æ”≈ª›»Ø
	public List<BeanCoupon> loadAll() throws BaseException;
	
	public List<BeanCoupon> load(BeanMerchant merchant) throws BaseException;
	
	//ÃÌº””≈ª›»Ø
	public BeanCoupon addCoupon(BeanMerchant merchant,double discount_amount,int order_req_num,Date start_date, Date end_date) throws BaseException;
	
	//…æ≥˝”≈ª›»Ø
	public void deleteCoupon(BeanCoupon coupon) throws BaseException;
	
	

}
