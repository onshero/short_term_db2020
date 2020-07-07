package cn.edu.deliveryAssistant.itf;

import java.sql.Date;

import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IUserManager {
	
	//ע��
	public BeanUser reg(String name, boolean sex, String pwd1, String pwd2, String phone_num, String email, String city) throws BaseException;
	
	//��¼
	public BeanUser login(String phone_num,String pwd)throws BaseException;
	
	//�޸�����
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	//��ʾ������Ϣ;
	public BeanUser show(BeanUser user) throws BaseException;
	
	//����/���ѻ�Ա
	public BeanUser VIP(boolean isVIP,Date VIP_end_date,double money);
	
	//ɾ���û�
	public void deleteUser(BeanUser user) throws BaseException;

}
