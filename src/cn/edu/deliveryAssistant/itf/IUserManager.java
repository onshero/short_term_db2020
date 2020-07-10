package cn.edu.deliveryAssistant.itf;

import java.sql.Date;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IUserManager {
	
	//ע��
	public BeanUser reg(String name, String sex, String pwd1, String pwd2, String phone_num, String email, String city) throws BaseException;
	
	//��¼
	public BeanUser login(String phone_num,String pwd)throws BaseException;
	
	//�޸�����
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	//��ʾ�����û�
	public List<BeanUser> loadAll() throws BaseException;
	
	/*
	 * 	����/���ѻ�Ա
	 * 	�趨10Ԫ/�£�һ����30�죩
	 * 	����ʮԪ���ܹ����Ա
	 */
	public BeanUser VIP(BeanUser user,double money) throws BaseException;
	
	//ɾ���û�
	public void deleteUser(BeanUser user) throws BaseException;
	
	

}
