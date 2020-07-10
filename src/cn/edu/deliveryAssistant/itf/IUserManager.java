package cn.edu.deliveryAssistant.itf;

import java.sql.Date;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IUserManager {
	
	//注册
	public BeanUser reg(String name, String sex, String pwd1, String pwd2, String phone_num, String email, String city) throws BaseException;
	
	//登录
	public BeanUser login(String phone_num,String pwd)throws BaseException;
	
	//修改密码
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	//显示所有用户
	public List<BeanUser> loadAll() throws BaseException;
	
	/*
	 * 	购买/续费会员
	 * 	设定10元/月（一个月30天）
	 * 	不满十元不能购买会员
	 */
	public BeanUser VIP(BeanUser user,double money) throws BaseException;
	
	//删除用户
	public void deleteUser(BeanUser user) throws BaseException;
	
	

}
