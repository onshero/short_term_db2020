package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class MerchantManager{

	//显示所以商家
	public List<BeanMerchant> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchant> result=new ArrayList<BeanMerchant>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchant";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanMerchant merchant=new BeanMerchant();
				merchant.setMerchant_id(rs.getInt(1));
				merchant.setMerchant_name(rs.getString(2));
				merchant.setMerchant_star(rs.getDouble(3));
				merchant.setConsumption_per_person(rs.getDouble(4));
				merchant.setTotal_sale(rs.getInt(5));
				result.add(merchant);
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

	
	public BeanMerchant load(String merchant_name) throws BaseException {
		// TODO Auto-generated method stub
		if(merchant_name==null||"".equals(merchant_name)) throw new BusinessException("商家名不能为空");
		BeanMerchant merchant=new BeanMerchant();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchant WHERE merchant_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, merchant_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				merchant.setMerchant_id(rs.getInt(1));
				merchant.setMerchant_name(rs.getString(2));
				merchant.setMerchant_star(rs.getDouble(3));
				merchant.setConsumption_per_person(rs.getDouble(4));
				merchant.setTotal_sale(rs.getInt(5));
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
		return merchant;
	}
	
	//添加商家
	public BeanMerchant addMerchant(String merchant_name) throws BaseException {
		// TODO Auto-generated method stub
		if(merchant_name==null||"".equals(merchant_name)) throw new BusinessException("商家名不能为空");
		BeanMerchant merchant=new BeanMerchant();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchant WHERE merchant_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, merchant_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商家名已存在");
			rs.close();
			pst.close();
			
			sql="select max(merchant_id) from merchant";
			int id=0;
			java.sql.Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) id=rs.getInt(1)+1;
			else id=1;
			rs.close();
			st.close();
			
			sql="INSERT INTO merchant VALUES(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, merchant_name);
			pst.setDouble(3, 5);
			pst.setDouble(4, 0);
			pst.setInt(5, 0);
			int f=pst.executeUpdate();
			if(f==1) System.out.println("添加成功");
			else throw new BusinessException("添加失败");
			merchant.setMerchant_id(id);
			merchant.setMerchant_name(merchant_name);
			merchant.setMerchant_star(5);
			merchant.setConsumption_per_person(0);
			merchant.setTotal_sale(0);
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
		return merchant;
	}

	//删除商家
	public void deleteMerchant(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("存在相关类别，无法删除");
			rs.close();
			pst.close();
			
			sql="DELETE FROM merchant WHERE merchant_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			int f=pst.executeUpdate();
			if(f==0) throw new BusinessException("删除失败");
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
	public static void main(String[] args) {
		try {
			new MerchantManager().addMerchant("外婆菜");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//根据订单显示商家
	public List<BeanMerchant> loadbyOrder() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchant> result=new ArrayList<BeanMerchant>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchant\r\n" + 
					"WHERE merchant_id in(\r\n" + 
					"	SELECT merchant_id FROM ord\r\n" + 
					"	WHERE user_id='1'\r\n" + 
					"	and order_id not in(\r\n" + 
					"		SELECT order_id FROM merchandise_order WHERE user_id='1')\r\n" + 
					")";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanMerchant merchant=new BeanMerchant();
				merchant.setMerchant_id(rs.getInt(1));
				merchant.setMerchant_name(rs.getString(2));
				merchant.setMerchant_star(rs.getDouble(3));
				merchant.setConsumption_per_person(rs.getDouble(4));
				merchant.setTotal_sale(rs.getInt(5));
				result.add(merchant);
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

}
