package cn.edu.deliveryAssistant.control;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanAddress;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class AddressManager{

	//显示地址
	public List<BeanAddress> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM delivery_address WHERE user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanAddress address=new BeanAddress();
				address.setAddress_id(rs.getInt(1));
				address.setUser_id(rs.getInt(2));
				address.setProvince(rs.getString(3));
				address.setCity(rs.getString(4));
				address.setArea(rs.getString(5));
				address.setAddress(rs.getString(6));
				address.setContact_person(rs.getString(7));
				address.setPhone_num(rs.getString(8));
				address.setUsed(rs.getBoolean(9));
				result.add(address);
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
		return result;
	}

	//更改默认地址
	public void modifyDefault(BeanAddress address) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE delivery_address SET used=? WHERE user_id=? AND address_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(3, address.getAddress_id());
			int f=pst.executeUpdate();
			if(f==0) throw new BusinessException("修改失败");
			pst.close();
			sql="select address_id from delivery_address where user_id=? and used='1' ";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				sql="UPDATE delivery_address SET used='0' WHERE user_id='"+BeanUser.currentLoginUser.getUser_id()+"' AND address_id="+rs.getInt(1);
				java.sql.Statement st=conn.createStatement();
				st.executeUpdate(sql);
				st.close();
			}
			pst.close();
			rs.close();
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

	//添加地址
	public BeanAddress addAddress(BeanUser user, String province, String city, String area, String address,
			String contact_person, String phone_num) throws BaseException {
		// TODO Auto-generated method stub
		if(province==null||"".equals(province)) throw new BusinessException("省不能为空");
		if(city==null||"".equals(city)) throw new BusinessException("市不能为空");
		if(area==null||"".equals(area)) throw new BusinessException("区不能为空");
		if(address==null||"".equals(address)) throw new BusinessException("地址不能为空");
		if(contact_person==null||"".equals(contact_person)) contact_person=BeanUser.currentLoginUser.getUser_name();
		if(phone_num==null||"".equals(phone_num)) phone_num=BeanUser.currentLoginUser.getUser_phone_num();
		BeanAddress a=new BeanAddress();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT max(address_id) FROM delivery_address  WHERE user_id=?";
			int ad_id=0;
			int used=0;
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) ad_id=rs.getInt(1)+1;
			else {
				ad_id=1;
				used=1;
			}
			rs.close();
			pst.close();
			sql="INSERT INTO delivery_address VALUES(?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ad_id);
			pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
			pst.setString(3, province);
			pst.setString(4, city);
			pst.setString(5, area);
			pst.setString(6, address);
			pst.setString(7, contact_person);
			pst.setString(8, phone_num);
			pst.setInt(9, used);
			pst.executeUpdate();
			pst.close();
			a.setAddress_id(ad_id);
			a.setUser_id(BeanUser.currentLoginUser.getUser_id());
			a.setProvince(province);
			a.setCity(city);
			a.setArea(area);
			a.setAddress(address);
			a.setContact_person(contact_person);
			a.setPhone_num(phone_num);
			a.setUsed(used==1);
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
		
		return a;
	}

	//删除地址
	public void deleteAddress(BeanAddress address) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM delivery_address WHERE address_id=? AND user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, address.getAddress_id());
			pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
			pst.executeUpdate();
			pst.close();
			
			sql="UPDATE delivery_address SET address_id=0-address_id WHERE user_id=? AND address_id>?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(2, address.getAddress_id());
			pst.executeUpdate();
			pst.close();
			
			sql="UPDATE delivery_address SET address_id=-1-address_id WHERE user_id=? AND address_id<?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(2, 0);
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

	//用户注销删除地址
	public void deleteAddress() throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM delivery_address WHERE AND user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
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
