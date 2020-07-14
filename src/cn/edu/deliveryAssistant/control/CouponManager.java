package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanCoupon;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.CouponGot;
import cn.edu.deliveryAssistant.model.CouponReqOrder;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class CouponManager {

	//显示优惠券
	public List<BeanCoupon> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon GROUP BY merchant_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanCoupon coupon=new BeanCoupon();
				coupon.setCoupon_id(rs.getInt(1));
				coupon.setMerchant_id(rs.getInt(2));
				coupon.setDiscount_amount(rs.getDouble(3));
				coupon.setOrder_req_num(rs.getInt(4));
				coupon.setStart_date(rs.getDate(5));
				coupon.setEnd_date(rs.getDate(6));
				result.add(coupon);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public List<BeanCoupon> load(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon WHERE merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCoupon coupon=new BeanCoupon();
				coupon.setCoupon_id(rs.getInt(1));
				coupon.setMerchant_id(rs.getInt(2));
				coupon.setDiscount_amount(rs.getDouble(3));
				coupon.setOrder_req_num(rs.getInt(4));
				coupon.setStart_date(rs.getDate(5));
				coupon.setEnd_date(rs.getDate(6));
				result.add(coupon);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//显示用户得到的优惠券
	public List<CouponGot> loadUserGot(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<CouponGot> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon_got WHERE user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				CouponGot cGot=new CouponGot();
				cGot.setUser_id(rs.getInt(1));
				cGot.setCoupon_id(rs.getInt(2));
				cGot.setMerchant_id(rs.getInt(3));
				cGot.setDiscount_amount(rs.getDouble(4));
				cGot.setGot_num(rs.getInt(5));
				cGot.setEnd_date(rs.getDate(6));
				result.add(cGot);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//显示用户在该商家能用的优惠券
	public List<CouponGot> loadUserGot(BeanUser user, BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<CouponGot> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon_got WHERE user_id=? and merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(2, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				CouponGot cGot=new CouponGot();
				cGot.setUser_id(rs.getInt(1));
				cGot.setCoupon_id(rs.getInt(2));
				cGot.setMerchant_id(rs.getInt(3));
				cGot.setDiscount_amount(rs.getDouble(4));
				cGot.setGot_num(rs.getInt(5));
				cGot.setEnd_date(rs.getDate(6));
				result.add(cGot);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	//显示用户正在集单的优惠券
	public List<CouponReqOrder> loadUserGting(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<CouponReqOrder> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon_require_order WHERE user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				CouponReqOrder cReqOrder=new CouponReqOrder();
				cReqOrder.setUser_id(rs.getInt(1));
				cReqOrder.setCoupon_id(rs.getInt(2));
				cReqOrder.setCoupon_id(rs.getInt(3));
				cReqOrder.setOrder_req_num(rs.getInt(4));
				cReqOrder.setFinished_order_num(rs.getInt(5));
				result.add(cReqOrder);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//添加优惠券
	public BeanCoupon addCoupon(BeanMerchant merchant, double discount_amount, int order_req_num, Date start_date,
			Date end_date) throws BaseException {
		// TODO Auto-generated method stub
		if(discount_amount<=0) throw new BusinessException("优惠金额要大于0");
		if(order_req_num<0) throw new BusinessException("要求订单数不能小于0");
		if(start_date==null) throw new BusinessException("起始日期不能为空");
		if(end_date==null) throw new BusinessException("结束日期不能为空");
		if(!start_date.before(end_date)) throw new BusinessException("开始日期要在结束日期之前");
		BeanCoupon coupon=new BeanCoupon();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM coupon\r\n" + 
					" WHERE (merchant_id=? AND discount_amount=? AND  order_req_num=? AND start_date=? AND end_date=?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			pst.setDouble(2, discount_amount);
			pst.setInt(3, order_req_num);
			pst.setDate(4, start_date);
			pst.setDate(5, end_date);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("存在相同优惠券");
			rs.close();
			pst.close();
			
			int cou_id=0;
			sql="select max(coupon_id) from coupon where merchant_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			rs=pst.executeQuery();
			if(rs.next()) cou_id=rs.getInt(1)+1;
			else cou_id=1;
			rs.close();
			pst.close();
			
			sql="INSERT INTO coupon VALUES(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cou_id);
			pst.setInt(2, merchant.getMerchant_id());
			pst.setDouble(3, discount_amount);
			pst.setInt(4, order_req_num);
			pst.setDate(5, start_date);
			pst.setDate(6, end_date);
			pst.executeUpdate();
			rs.close();
			pst.close();
			coupon.setCoupon_id(cou_id);
			coupon.setMerchant_id(merchant.getMerchant_id());
			coupon.setDiscount_amount(discount_amount);
			coupon.setOrder_req_num(order_req_num);
			coupon.setStart_date(start_date);
			coupon.setEnd_date(end_date);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return coupon;
	}

	public CouponGot addUserGot(BeanUser user, BeanCoupon coupon) throws BaseException {
		// TODO Auto-generated method stub
		CouponGot cGot=new CouponGot();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * from coupon WHERE user_id=? AND coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, coupon.getCoupon_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				cGot.setUser_id(rs.getInt(1));
				cGot.setCoupon_id(rs.getInt(2));
				cGot.setMerchant_id(rs.getInt(3));
				cGot.setDiscount_amount(rs.getDouble(4));
				cGot.setGot_num(rs.getInt(5));
				cGot.setEnd_date(rs.getDate(6));
				rs.close();
				pst.close();
				sql="UPDATE coupon_got SET got_num=got_num+1 WHERE user_id=? AND coupon_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, user.getUser_id());
				pst.setInt(2, coupon.getCoupon_id());
				pst.executeUpdate();
				cGot.setGot_num(cGot.getGot_num()+1);
			}else {
				rs.close();
				pst.close();
				sql="INSERT INTO coupon_got(user_id,coupon_id,merchant_id,discount_amount,got_num,end_date)\r\n" + 
						" VALUES (?,?,?,?,'1',?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, user.getUser_id());
				pst.setInt(2, coupon.getCoupon_id());
				pst.setInt(3, coupon.getMerchant_id());
				pst.setDouble(4, coupon.getDiscount_amount());
				pst.setDate(5, coupon.getEnd_date());
				pst.executeUpdate();
				cGot.setUser_id(user.getUser_id());
				cGot.setCoupon_id(coupon.getCoupon_id());
				cGot.setMerchant_id(coupon.getMerchant_id());
				cGot.setDiscount_amount(coupon.getDiscount_amount());
				cGot.setGot_num(1);
				cGot.setEnd_date(coupon.getEnd_date());
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return cGot;
	}

	public CouponGot addUserGot(CouponReqOrder couponReqOrder) throws BaseException {
		// TODO Auto-generated method stub
		
		return null;
	}

	//添加/修改/删除集单送券
	public CouponReqOrder amdCouReqOrd(BeanMerchant merchant, BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	//删除优惠券
	public void deleteCoupon(BeanMerchant merchant, BeanCoupon coupon) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM coupon where coupon_id=? AND merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, coupon.getCoupon_id());
			pst.setInt(2, coupon.getMerchant_id());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}


	public void deleteCoupon(CouponGot cGot,int num) throws BaseException {
		// TODO Auto-generated method stub
		if(num<=0) throw new BusinessException("删除数要大于0");
		if(num>cGot.getGot_num()) throw new BusinessException("删除数不能大于拥有数");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="";
			if(num<cGot.getGot_num()) {
				sql="UPDATE coupon_got SET got_num=got_num-? WHERE user_id=? AND merchant_id=? AND coupon_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setInt(2, cGot.getUser_id());
				pst.setInt(3, cGot.getMerchant_id());
				pst.setInt(4, cGot.getCoupon_id());
				pst.executeUpdate();
				pst.close();
			}else if (num==cGot.getGot_num()) {
				sql="DELETE FROM coupon_got WHERE user_id=? AND merchant_id=? AND coupon_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, cGot.getUser_id());
				pst.setInt(2, cGot.getMerchant_id());
				pst.setInt(3, cGot.getCoupon_id());
				pst.executeUpdate();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	

}
