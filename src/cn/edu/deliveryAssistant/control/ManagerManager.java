package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.itf.IManagerManager;
import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class ManagerManager implements IManagerManager {

	@Override
	public List<BeanManager> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanManager> result=new ArrayList<BeanManager>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM manager";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanManager manager=new BeanManager();
				manager.setManager_id(rs.getInt(1));
				manager.setManager_name(rs.getString(2));
				manager.setManager_password(rs.getString(3));
				result.add(manager);
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
	public BeanManager addManager(String name, String password) throws BaseException {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name)) throw new BusinessException("管理员姓名不能为空");
		if(password==null||"".equals(password)) throw new BusinessException("密码姓名不能为空");
		BeanManager manager=new BeanManager();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM manager WHERE manager_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("员工名字已存在");
			rs.close();
			pst.close();
			
			sql="SELECT MAX(manager_id) FROM manager";
			java.sql.Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			int id=0;
			if(rs.next()) id=rs.getInt(1)+1;
			else id=1;
			rs.close();
			st.close();
			
			sql="INSERT INTO manager VALUES(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, password);
			pst.executeUpdate();
			manager.setManager_id(id);
			manager.setManager_name(name);
			manager.setManager_password(password);
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
		return manager;
	}

	@Override
	public void deleteManager(BeanManager manager) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from manager where manager_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, manager.getManager_id());
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
