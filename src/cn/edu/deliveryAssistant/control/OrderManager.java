package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanOrder;
import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class OrderManager{

	//显示订单
	public List<BeanOrder> loadAll() throws BaseException {
		List<BeanOrder> result=new ArrayList<BeanOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id,merchandise_id,classify_id,merchant_id,user_id,count,total_price,discount_unit_price \r\n" + 
					"	FROM ord";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanOrder order=new BeanOrder();
				order.setOrder_id(rs.getInt(1));
				order.setMerchandise_id(rs.getInt(2));
				order.setClassify_id(rs.getInt(3));
				order.setMerchant_id(rs.getInt(4));
				order.setUser_id(rs.getInt(5));
				order.setCount(rs.getInt(6));
				order.setTotal_price(rs.getDouble(7));
				order.setDiscount_unit_price(rs.getDouble(8));
				result.add(order);
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

	public List<MerchandiseOrder> load(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<MerchandiseOrder> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_order WHERE user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				MerchandiseOrder mOrder=new MerchandiseOrder();
				mOrder.setOrder_id(rs.getInt(1));
				mOrder.setMerchant_id(rs.getInt(2));
				mOrder.setUser_id(rs.getInt(3));
				mOrder.setRider_id(rs.getInt(4));
				mOrder.setPlan_id(rs.getInt(5));
				mOrder.setCoupon_id(rs.getInt(6));
				mOrder.setAddress_id(rs.getInt(7));
				mOrder.setOrigin_price(rs.getDouble(8));
				mOrder.setFinal_price(rs.getDouble(9));
				mOrder.setOrder_date(rs.getDate(10));
				mOrder.setReq_delivery_date(rs.getDate(11));
				mOrder.setOrder_status(rs.getString(12));
				result.add(mOrder);
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

	public List<BeanOrder> load(MerchandiseOrder merchandiseOrder) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanOrder> result=new ArrayList<BeanOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id,merchandise_id,classify_id,merchant_id,user_id,count,total_price,discount_unit_price \r\n" + 
					"	FROM ord WHERE order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchandiseOrder.getOrder_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrder order=new BeanOrder();
				order.setOrder_id(rs.getInt(1));
				order.setMerchandise_id(rs.getInt(2));
				order.setClassify_id(rs.getInt(3));
				order.setMerchant_id(rs.getInt(4));
				order.setUser_id(rs.getInt(5));
				order.setCount(rs.getInt(6));
				order.setTotal_price(rs.getDouble(7));
				order.setDiscount_unit_price(rs.getDouble(8));
				result.add(order);
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

	//根据订单状态显示订单
	public List<MerchandiseOrder> load(BeanUser user, String status) throws BaseException {
		// TODO Auto-generated method stub
		List<MerchandiseOrder> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_order WHERE user_id=? AND order_status=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setString(2, status);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				MerchandiseOrder mOrder=new MerchandiseOrder();
				mOrder.setOrder_id(rs.getInt(1));
				mOrder.setMerchant_id(rs.getInt(2));
				mOrder.setUser_id(rs.getInt(3));
				mOrder.setRider_id(rs.getInt(4));
				mOrder.setPlan_id(rs.getInt(5));
				mOrder.setCoupon_id(rs.getInt(6));
				mOrder.setAddress_id(rs.getInt(7));
				mOrder.setOrigin_price(rs.getDouble(8));
				mOrder.setFinal_price(rs.getDouble(9));
				mOrder.setOrder_date(rs.getDate(10));
				mOrder.setReq_delivery_date(rs.getDate(11));
				mOrder.setOrder_status(rs.getString(12));
				result.add(mOrder);
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

	//添加订单
	public BeanOrder addOrder(BeanMerchandise merchandise, int num)
			throws BaseException {
		// TODO Auto-generated method stub
		if(num<=0) throw new BusinessException("商品数应大于0");
		BeanOrder order=new BeanOrder();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id FROM ord \r\n" + 
					"	WHERE user_id=? \r\n" + 
					"	AND order_id not in(\r\n" + 
					"	SELECT order_id FROM merchandise_order\r\n" + 
					"	WHERE user_id=?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			int ord_id=0;
			if(rs.next()) {
				ord_id=rs.getInt(1);
				rs.close();
				pst.close();
			}
			else {
				rs.close();
				pst.close();
				sql="select max(order_id) from ord";
				java.sql.Statement st=conn.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next()) ord_id=rs.getInt(1)+1;
				else ord_id=1;
				rs.close();
				pst.close();
			}
			sql="INSERT INTO ord(order_id,merchandise_id,classify_id,merchant_id,user_id,count,total_price,discount_unit_price)\r\n" + 
					"VALUES(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ord_id);
			pst.setInt(2, merchandise.getMerchandise_id());
			pst.setInt(3, merchandise.getClassify_id());
			pst.setInt(4, merchandise.getMerchant_id());
			pst.setInt(5, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(6, num);
			pst.setDouble(7, num*(merchandise.getUnit_price()-merchandise.getDiscount_unit_price()));
			pst.setDouble(8, merchandise.getDiscount_unit_price());
			pst.executeUpdate();
			order.setOrder_id(ord_id);
			order.setMerchandise_id(merchandise.getMerchandise_id());
			order.setClassify_id(merchandise.getClassify_id());
			order.setMerchant_id(merchandise.getMerchant_id());
			order.setUser_id(BeanUser.currentLoginUser.getUser_id());
			order.setCount(num);
			order.setTotal_price(num*(merchandise.getUnit_price()-merchandise.getDiscount_unit_price()));
			order.setDiscount_unit_price(merchandise.getDiscount_unit_price());
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
		return order;
	}

	//根据商家显示未出订单号
	public BeanOrder loadbymerchant(BeanMerchant merchant) throws BaseException{
		BeanOrder order=new BeanOrder();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT DISTINCT order_id FROM ord\r\n" + 
					"WHERE merchant_id=?\r\n" + 
					"AND order_id not in (\r\n" + 
					"SELECT order_id FROM merchandise_order)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				order.setOrder_id(rs.getInt(1));
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
		return order;
	}
	
	public MerchandiseOrder addtotalOrder(BeanUser user, BeanMerchant merchant, BeanRider rider, BeanOrder order)
			throws BaseException {
		MerchandiseOrder merchandiseOrder=new MerchandiseOrder();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			merchandiseOrder.setOrder_id(order.getOrder_id());
			merchandiseOrder.setMerchant_id(merchant.getMerchant_id());
			merchandiseOrder.setUser_id(user.getUser_id());
			merchandiseOrder.setRider_id(rider.getRider_id());
			merchandiseOrder.setOrder_date(new Date(System.currentTimeMillis()));
			merchandiseOrder.setReq_delivery_date(new Date(System.currentTimeMillis()+30*60*1000));
			merchandiseOrder.setOrder_status("配送中");
			String sql="SELECT sum(total_price),count(*) FROM ord\r\n" + 
					"	WHERE order_id=? AND merchant_id=? AND user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.setInt(2, merchant.getMerchant_id());
			pst.setInt(3, user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			double origin_price=rs.getDouble(1);
			double final_price=origin_price;
			int ord_num=rs.getInt(2);
			if(user.getIsVIP()==true) final_price=0.9*origin_price;
			merchandiseOrder.setOrigin_price(origin_price);
			rs.close();
			pst.close();
			
			int usePlan=0;
			int usewithcoupon=0;
			sql="SELECT plan_id,max(reduce_amount),plan_discount_amount,used_with_coupon FROM full_reduction_plan \r\n" + 
					"WHERE merchant_id=?\r\n" + 
					"AND plan_id not in(\r\n" + 
					"SELECT plan_id FROM full_reduction_plan\r\n" + 
					"WHERE merchant_id=?\r\n" + 
					"AND reduce_amount>=?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			pst.setInt(2, merchant.getMerchant_id());
			pst.setDouble(3, final_price);
			rs=pst.executeQuery();
			if(rs.next()) {
				usePlan=1;
				merchandiseOrder.setPlan_id(rs.getInt(1));
				final_price=final_price-rs.getDouble(3);
				usewithcoupon=rs.getInt(4);
			}
			rs.close();
			pst.close();
			
			if(usePlan==0||usewithcoupon==1) {
				sql="SELECT coupon_got.coupon_id,max(coupon_got.discount_amount)\r\n" + 
						"FROM (coupon_got LEFT JOIN coupon ON (coupon_got.coupon_id=coupon.coupon_id AND coupon_got.merchant_id=coupon.merchant_id))\r\n" + 
						"WHERE user_id=? AND coupon.merchant_id=? and coupon.order_req_num<=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, user.getUser_id());
				pst.setInt(2, merchant.getMerchant_id());
				pst.setInt(3, ord_num);
				rs=pst.executeQuery();
				if(rs.next()) {
					merchandiseOrder.setCoupon_id(rs.getInt(1));
					final_price-=rs.getDouble(2);
					rs.close();
					pst.close();
					
					sql="UPDATE  coupon_got SET got_num=got_num-1 WHERE coupon_id=? AND user_id=? AND merchant_id=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, merchandiseOrder.getCoupon_id());
					pst.setInt(2, user.getUser_id());
					pst.setInt(3, merchant.getMerchant_id());
					pst.executeUpdate();
				}
				if(rs!=null) rs.close();
				pst.close();
			}
			merchandiseOrder.setFinal_price(final_price);
			
			sql="SELECT address_id FROM delivery_address WHERE user_id=? AND used='1'";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			rs=pst.executeQuery();
			if(rs.next()) merchandiseOrder.setAddress_id(rs.getInt(1));
			rs.close();
			pst.close();
			
			sql="INSERT INTO merchandise_order VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchandiseOrder.getOrder_id());
			pst.setInt(2, merchandiseOrder.getMerchant_id());
			pst.setInt(3, merchandiseOrder.getUser_id());
			pst.setInt(4, merchandiseOrder.getRider_id());
			pst.setInt(5, merchandiseOrder.getPlan_id());
			pst.setInt(6, merchandiseOrder.getCoupon_id());
			pst.setInt(7, merchandiseOrder.getAddress_id());
			pst.setDouble(8, merchandiseOrder.getOrigin_price());
			pst.setDouble(9, merchandiseOrder.getFinal_price());
			pst.setDate(10, merchandiseOrder.getOrder_date());
			pst.setDate(11, merchandiseOrder.getReq_delivery_date());
			pst.setString(12, merchandiseOrder.getOrder_status());
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
		return merchandiseOrder;
	}

	//修改订单状态
	public MerchandiseOrder modifyOrderStatus(MerchandiseOrder merchandiseOrder, String status) throws BaseException {
		// TODO Auto-generated method stub
		if(status==null||"".equals(status)) throw new BusinessException("状态不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE merchandise_order SET order_status=? WHERE order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setInt(2, merchandiseOrder.getOrder_id());
			pst.executeUpdate();
			merchandiseOrder.setOrder_status(status);
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
		return merchandiseOrder;
	}

	//删除订单
	public void deleteOrder(BeanOrder order) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM ord WHERE order_id=? AND merchandise_id=? AND classify_id=? AND merchant_id=? AND user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.setInt(2, order.getMerchandise_id());
			pst.setInt(3, order.getClassify_id());
			pst.setInt(4, order.getMerchant_id());
			pst.setInt(5, order.getUser_id());
			pst.executeUpdate();
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

	//清空购物车
		public void deleteOrder(BeanUser user) throws BaseException {
			// TODO Auto-generated method stub
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="DELETE FROM ord WHERE user_id=? and order_id not in(select order_id from merchandise_order WHERE user_id=?)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, user.getUser_id());
				pst.setInt(2, user.getUser_id());
				pst.executeUpdate();
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
	//根据商家和用户查购物车订单
	public List<BeanOrder> loadbyMerchant(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanOrder> result=new ArrayList<BeanOrder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT order_id,ord.merchandise_id,ord.classify_id,ord.merchant_id,ord.user_id,merchandise.merchandise_name,count,total_price,ord.discount_unit_price\r\n" + 
					"	FROM ord, merchandise\r\n" + 
					"	WHERE ord.merchandise_id=merchandise.merchandise_id\r\n" + 
					"	AND ord.merchant_id=?\r\n" + 
					"	AND user_id=?\r\n" + 
					"	AND order_id not in(\r\n" + 
					"		SELECT order_id FROM merchandise_order WHERE user_id=?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(3, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrder order=new BeanOrder();
				order.setOrder_id(rs.getInt(1));
				order.setMerchandise_id(rs.getInt(2));
				order.setClassify_id(rs.getInt(3));
				order.setMerchant_id(rs.getInt(4));
				order.setUser_id(rs.getInt(5));
				order.setMerchandise_name(rs.getString(6));
				order.setCount(rs.getInt(7));
				order.setTotal_price(rs.getDouble(8));
				order.setDiscount_unit_price(rs.getDouble(9));
				result.add(order);
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
	
	//根据订单状态查订单
	public List<MerchandiseOrder> searchbystatus() throws BaseException{
		List<MerchandiseOrder> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_order WHERE user_id=? ORDER BY order_date ASC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				MerchandiseOrder mOrder=new MerchandiseOrder();
				mOrder.setOrder_id(rs.getInt(1));
				mOrder.setMerchant_id(rs.getInt(2));
				mOrder.setUser_id(rs.getInt(3));
				mOrder.setRider_id(rs.getInt(4));
				mOrder.setPlan_id(rs.getInt(5));
				mOrder.setCoupon_id(rs.getInt(6));
				mOrder.setAddress_id(rs.getInt(7));
				mOrder.setOrigin_price(rs.getDouble(8));
				mOrder.setFinal_price(rs.getDouble(9));
				mOrder.setOrder_date(rs.getDate(10));
				mOrder.setReq_delivery_date(rs.getDate(11));
				mOrder.setOrder_status(rs.getString(12));
				result.add(mOrder);
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
	
	public List<MerchandiseOrder> searchbystatus(String status) throws BaseException{
		List<MerchandiseOrder> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_order WHERE user_id=? AND order_status=? ORDER BY order_date ASC";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setString(2, status);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				MerchandiseOrder mOrder=new MerchandiseOrder();
				mOrder.setOrder_id(rs.getInt(1));
				mOrder.setMerchant_id(rs.getInt(2));
				mOrder.setUser_id(rs.getInt(3));
				mOrder.setRider_id(rs.getInt(4));
				mOrder.setPlan_id(rs.getInt(5));
				mOrder.setCoupon_id(rs.getInt(6));
				mOrder.setAddress_id(rs.getInt(7));
				mOrder.setOrigin_price(rs.getDouble(8));
				mOrder.setFinal_price(rs.getDouble(9));
				mOrder.setOrder_date(rs.getDate(10));
				mOrder.setReq_delivery_date(rs.getDate(11));
				mOrder.setOrder_status(rs.getString(12));
				result.add(mOrder);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			//new OrderManager().loadbyMerchant(merchant);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
