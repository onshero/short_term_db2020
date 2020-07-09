package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

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
		if(pwd1!=pwd2) throw new BusinessException("前后密码不一致");
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
		return null;
	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public BeanUser show(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanUser VIP(boolean isVIP, Date VIP_end_date, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
