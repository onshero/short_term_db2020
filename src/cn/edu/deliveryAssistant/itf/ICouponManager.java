package cn.edu.deliveryAssistant.itf;

import java.sql.Date;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanCoupon;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.CouponGot;
import cn.edu.deliveryAssistant.model.CouponReqOrder;
import cn.edu.deliveryAssistant.util.BaseException;

public interface ICouponManager {
	//显示优惠券
	public List<BeanCoupon> loadAll() throws BaseException;
	
	public List<BeanCoupon> load(BeanMerchant merchant) throws BaseException;
	
	//显示用户得到的优惠券
	public List<CouponGot> loadUserGot(BeanUser user) throws BaseException;
	
	//显示用户正在集单的优惠券
	public List<CouponReqOrder> loadUserGting(BeanUser user) throws BaseException;
	
	//添加优惠券
	public BeanCoupon addCoupon(BeanMerchant merchant,double discount_amount,int order_req_num,Date start_date, Date end_date) throws BaseException;
	
	public CouponGot addUserGot(BeanUser user,BeanCoupon coupon) throws BaseException;
	
	public CouponGot addUserGot(CouponReqOrder couponReqOrder) throws BaseException;
	
	//添加/修改/删除集单送券
	public CouponReqOrder amdCouReqOrd(BeanMerchant merchant,BeanUser user) throws BaseException;
	
	
	//删除优惠券
	public void deleteCoupon(BeanMerchant merchant,BeanCoupon coupon) throws BaseException;
	
	public void deleteCoupon(CouponGot cGot) throws BaseException;

}
