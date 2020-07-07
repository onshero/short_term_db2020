package cn.edu.deliveryAssistant.itf;

import java.sql.Date;

import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IUserManager {
	
	//注册
	public BeanUser reg(String name, boolean sex, String pwd1, String pwd2, String phone_num, String email, String city) throws BaseException;
	
	//登录
	public BeanUser login(String phone_num,String pwd)throws BaseException;
	
	//修改密码
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	//显示个人信息;
	public BeanUser show(BeanUser user) throws BaseException;
	
	//购买/续费会员
	public BeanUser VIP(boolean isVIP,Date VIP_end_date,double money);
	
	//删除用户
	public void deleteUser(BeanUser user) throws BaseException;

}
