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
	//��ʾ�Ż�ȯ
	public List<BeanCoupon> loadAll() throws BaseException;
	
	public List<BeanCoupon> load(BeanMerchant merchant) throws BaseException;
	
	//��ʾ�û��õ����Ż�ȯ
	public List<CouponGot> loadUserGot(BeanUser user) throws BaseException;
	
	//��ʾ�û����ڼ������Ż�ȯ
	public List<CouponReqOrder> loadUserGting(BeanUser user) throws BaseException;
	
	//����Ż�ȯ
	public BeanCoupon addCoupon(BeanMerchant merchant,double discount_amount,int order_req_num,Date start_date, Date end_date) throws BaseException;
	
	public CouponGot addUserGot(BeanUser user,BeanCoupon coupon) throws BaseException;
	
	public CouponGot addUserGot(CouponReqOrder couponReqOrder) throws BaseException;
	
	//���/�޸�/ɾ��������ȯ
	public CouponReqOrder amdCouReqOrd(BeanMerchant merchant,BeanUser user) throws BaseException;
	
	
	//ɾ���Ż�ȯ
	public void deleteCoupon(BeanMerchant merchant,BeanCoupon coupon) throws BaseException;
	
	public void deleteCoupon(CouponGot cGot) throws BaseException;

}
