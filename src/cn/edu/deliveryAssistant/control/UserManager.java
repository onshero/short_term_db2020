package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.itf.IUserManager;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class UserManager implements IUserManager {

	@Override
	public BeanUser reg(String name, String sex, String pwd1, String pwd2, String phone_num, String email, String city)
			throws BaseException {
		if(name==null||"".equals(name)) throw new BusinessException("用户名不能为空");
		if(pwd1==null||"".equals(pwd1)||pwd2==null||"".equals(pwd2)) throw new BusinessException("密码不能为空");
		if(!pwd1.equals(pwd2)) throw new BusinessException("前后密码不一致");
		if(phone_num==null||"".equals(phone_num)) throw new BusinessException("手机号不能为空");
		if(email==null||"".equals(email)) throw new BusinessException("邮箱不能为空");
		if(city==null||"".equals(city)) throw new BusinessException("城市名不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM `user` WHERE user_phone_num=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, phone_num);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("手机号已被注册");
			rs.close();
			pst.close();
			
			int id=0;
			sql="SELECT MAX(user_id) FROM `user`";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) id=rs.getInt(1)+1;
			else id=1;
			rs.close();
			pst.close();
			
			Date date=new Date(System.currentTimeMillis());
			
			sql="INSERT INTO `user`( user_id,user_name,user_sex,user_password,user_phone_num,"+
					"user_email,user_city,registration_date,isVIP,VIP_end_date)\r\n" + 
					"VALUES(?,?,?,?,?,?,?,?,FALSE,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, sex);
			pst.setString(4, pwd1);
			pst.setString(5, phone_num);
			pst.setString(6, email);
			pst.setString(7, city);
			pst.setDate(8, date);
			pst.setDate(9, date);
			int f=pst.executeUpdate();
			if(f==0) throw new BusinessException("注册失败");
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
		return null;
	}

	@Override
	public BeanUser login(String phone_num, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if(phone_num==null||"".equals(phone_num)) throw new BusinessException("手机号不能为空");
		if(pwd==null||"".equals(pwd)) throw new BusinessException("密码不能为空");
		BeanUser  user=new BeanUser();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from `user` where user_phone_num=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, phone_num);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该手机号未被注册");
			rs.close();
			pst.close();
			sql="select * from `user` where user_phone_num=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, phone_num);
			rs=pst.executeQuery();
			rs.next();
			if(!pwd.equals(rs.getString(4))) throw new BusinessException("密码错误");
			user.setUser_id(rs.getInt(1));
			user.setUser_name(rs.getString(2));
			user.setUser_sex(rs.getString(3));
			user.setUser_password(rs.getString(4));
			user.setUser_phone_num(rs.getString(5));
			user.setUser_email(rs.getString(6));
			user.setUser_city(rs.getString(7));
			user.setRegistration_date(rs.getDate(8));
			user.setIsVIP(rs.getBoolean(9));
			user.setVIP_end_date(rs.getDate(10));
			rs.close();
			pst.close();
			if(user.getVIP_end_date().before(new java.util.Date(System.currentTimeMillis()))&&user.getIsVIP()==true){
				user.setIsVIP(false);
				sql="UPDATE `user` SET isVIP='0' WHERE user_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, user.getUser_id());
				pst.executeUpdate();
				pst.close();
			}
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
		return user;
	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(!user.getUser_password().equals(oldPwd)) {
			throw new BusinessException("原密码错误");
		}
		if(!newPwd.equals(newPwd2)) {
			throw new BusinessException("新密码不一致");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update `user` set user_password=? where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, user.getUser_id());
			int f=pst.executeUpdate();
			if(f==0) throw new BusinessException("修改失败");
			user.setUser_password(newPwd);
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

	@Override
	public List<BeanUser> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanUser> result=new ArrayList<BeanUser>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from `user` order by user_id asc";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanUser user=new BeanUser();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_sex(rs.getString(3));
				user.setUser_password(rs.getString(4));
				user.setUser_phone_num(rs.getString(5));
				user.setUser_email(rs.getString(6));
				user.setUser_city(rs.getString(7));
				user.setRegistration_date(rs.getDate(8));
				user.setIsVIP(rs.getBoolean(9));
				user.setVIP_end_date(rs.getDate(10));
				result.add(user);
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
	public BeanUser VIP(BeanUser user, double money) throws BaseException {
		// TODO Auto-generated method stub
		if(money<10) throw new BusinessException("低于最小购买值");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int n=(int)money/10;
			if(user.getVIP_end_date().before(new java.util.Date(System.currentTimeMillis())))
				user.setVIP_end_date(new Date(System.currentTimeMillis()+n*30*24*60*60*1000L));
			else
				user.setVIP_end_date(new Date(user.getVIP_end_date().getTime()+n*30*24*60*60*1000L));
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
		return user;
	}

	@Override
	public void deleteUser(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from `user` where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);;
			pst.setInt(1, user.getUser_id());
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


}
