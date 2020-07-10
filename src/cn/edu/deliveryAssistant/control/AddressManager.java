package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.itf.IAddressManager;
import cn.edu.deliveryAssistant.model.BeanAddress;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class AddressManager implements IAddressManager {

	@Override
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

	@Override
	public void modifyDefault(BeanAddress address) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE delivery_address SET used=? WHERE user_id=? AND address_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, 1);
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

	@Override
	public BeanAddress addAddress(BeanUser user, String province, String city, String area, String address,
			String contact_person, String phone_num) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAddress(BeanAddress address) throws BaseException {
		// TODO Auto-generated method stub

	}

}
