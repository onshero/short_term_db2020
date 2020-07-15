package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class ManagerManager{

	//显示管理员
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

	//添加管理员
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

	//删除管理员
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

	//登录
	public BeanManager login(String name, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name)) throw new BusinessException("员工姓名不能为空");
		if(pwd==null||"".equals(pwd)) throw new BusinessException("密码不能为空");
		BeanManager manager=new BeanManager();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM manager WHERE manager_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				if(!pwd.equals(rs.getString(3))) throw new BusinessException("密码错误");
				manager.setManager_id(rs.getInt(1));
				manager.setManager_name(name);
				manager.setManager_password(pwd);
			}else {
				throw new BusinessException("员工不存在");
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
		}
		return manager;
	}
	
	//更改密码
	public void changePwd(BeanManager manager, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(!manager.getManager_password().equals(oldPwd)) {
			throw new BusinessException("原密码错误");
		}
		if(!newPwd.equals(newPwd2)) {
			throw new BusinessException("新密码不一致");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE manager SET manager_password=? WHERE manager_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, manager.getManager_id());
			int f=pst.executeUpdate();
			if(f==0) throw new BusinessException("修改失败");
			manager.setManager_password(newPwd);
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
