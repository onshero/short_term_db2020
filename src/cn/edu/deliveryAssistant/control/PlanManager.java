package cn.edu.deliveryAssistant.control;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import cn.edu.deliveryAssistant.itf.IPlanManager;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanPlan;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class PlanManager implements IPlanManager {

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM full_reduction_plan";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanPlan plan=new BeanPlan();
				plan.setMerchant_id(rs.getInt(1));
				plan.setPlan_id(rs.getInt(2));
				plan.setReduce_amount(rs.getDouble(3));
				plan.setPlan_discount_amount(rs.getDouble(4));
				plan.setUsed_with_coupon(rs.getBoolean(5));
				result.add(plan);
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

	@Override
	public List<BeanPlan> load(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM full_reduction_plan where merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanPlan plan=new BeanPlan();
				plan.setMerchant_id(rs.getInt(1));
				plan.setPlan_id(rs.getInt(2));
				plan.setReduce_amount(rs.getDouble(3));
				plan.setPlan_discount_amount(rs.getDouble(4));
				plan.setUsed_with_coupon(rs.getBoolean(5));
				result.add(plan);
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

	@Override
	public BeanPlan addPlan(BeanMerchant merchant, double reduce_amount, double discount_amount,
			int used_with_coupon) throws BaseException {
		// TODO Auto-generated method stub
		if(reduce_amount<0) throw new BusinessException("满减金额不能小于0");
		if(discount_amount<0) throw new BusinessException("优惠金额不能少于0");
		if(used_with_coupon<=0) used_with_coupon=0;
		else {
			used_with_coupon=1;
		}
		BeanPlan plan=new BeanPlan();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM full_reduction_plan\r\n" + 
					"	WHERE merchant_id=? AND reduce_amount=? AND plan_discount_amount=? AND used_with_coupon=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			pst.setDouble(2, reduce_amount);
			pst.setDouble(3, discount_amount);
			pst.setInt(4, used_with_coupon);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在相同方案");
			rs.close();
			pst.close();
			
			sql="select max(plan_id) from full_reduction_plan where merchant_id=?";
			int pid=0;
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			rs=pst.executeQuery();
			if(rs.next()) pid=rs.getInt(1)+1;
			else pid=1;
			rs.close();
			pst.close();
			
			sql="INSERT INTO full_reduction_plan VALUES(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			pst.setInt(2, pid);
			pst.setDouble(3, reduce_amount);
			pst.setDouble(4, discount_amount);
			pst.setInt(5, used_with_coupon);
			pst.executeUpdate();
			plan.setMerchant_id(merchant.getMerchant_id());
			plan.setPlan_id(pid);
			plan.setReduce_amount(reduce_amount);
			plan.setPlan_discount_amount(discount_amount);
			plan.setUsed_with_coupon(used_with_coupon==1);
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
		return null;
	}

	@Override
	public void delete(BeanMerchant merchant, BeanPlan plan) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM full_reduction_plan WHERE merchant_id=? AND plan_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan.getMerchant_id());
			pst.setInt(2, plan.getPlan_id());
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

}
