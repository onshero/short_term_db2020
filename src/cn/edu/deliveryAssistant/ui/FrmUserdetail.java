package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import cn.edu.deliveryAssistant.model.BeanUser;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmUserdetail extends JDialog implements ActionListener{

	JLabel user_name = new JLabel("用户名：");
	JLabel user_sex = new JLabel("性别：");
	JLabel user_phone_num = new JLabel("手机号：");
	JLabel user_email = new JLabel("邮箱：");
	JLabel user_city = new JLabel("所在城市：");
	JLabel user_reg_date = new JLabel("注册时间");
	JLabel user_IsVip = new JLabel("是否会员：");
	JLabel user_Vip_end_date = new JLabel("会员到期时间：");
	private final JLabel name = new JLabel(BeanUser.currentLoginUser.getUser_name());
	private final JLabel sex = new JLabel(BeanUser.currentLoginUser.getUser_sex());
	private final JLabel phone_num = new JLabel(BeanUser.currentLoginUser.getUser_phone_num());
	private final JLabel email = new JLabel(BeanUser.currentLoginUser.getUser_email());
	private final JLabel city = new JLabel(BeanUser.currentLoginUser.getUser_city());
	private final JLabel reg_date = new JLabel(BeanUser.currentLoginUser.getRegistration_date().toString());
	private final JLabel is_vip;
	private final JLabel end_date = new JLabel(BeanUser.currentLoginUser.getVIP_end_date().toString());
	private final JButton btnoff = new JButton("注销");
	
	/**
	 * Create the application.
	 */
	public FrmUserdetail(JFrame f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 453, 433);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		user_name.setBounds(67, 42, 110, 18);
		getContentPane().add(user_name);
		
		user_sex.setBounds(67, 73, 110, 18);
		getContentPane().add(user_sex);
		
		user_phone_num.setBounds(67, 104, 110, 18);
		getContentPane().add(user_phone_num);
		
		user_email.setBounds(67, 135, 110, 18);
		getContentPane().add(user_email);
		
		user_city.setBounds(67, 166, 110, 18);
		getContentPane().add(user_city);
		
		user_reg_date.setBounds(67, 197, 110, 18);
		getContentPane().add(user_reg_date);
		
		user_IsVip.setBounds(67, 228, 110, 18);
		getContentPane().add(user_IsVip);
		
		user_Vip_end_date.setBounds(67, 259, 110, 18);
		getContentPane().add(user_Vip_end_date);
		
		name.setBounds(218, 42, 141, 18);
		
		getContentPane().add(name);
		sex.setBounds(218, 73, 141, 18);
		
		getContentPane().add(sex);
		phone_num.setBounds(218, 104, 141, 18);
		
		getContentPane().add(phone_num);
		email.setBounds(218, 135, 141, 18);
		
		getContentPane().add(email);
		city.setBounds(218, 166, 141, 18);
		
		getContentPane().add(city);
		reg_date.setBounds(218, 197, 141, 18);
		
		if(BeanUser.currentLoginUser.getIsVIP()==true) is_vip=new JLabel("会员");
		else is_vip=new JLabel("已过期");
		getContentPane().add(reg_date);
		is_vip.setBounds(218, 228, 72, 18);
		
		getContentPane().add(is_vip);
		end_date.setBounds(218, 259, 141, 18);
		
		getContentPane().add(end_date);
		btnoff.setBounds(131, 314, 113, 27);
		
		getContentPane().add(btnoff);this.btnoff.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnoff) {
			new FrmUserLogoff(this, "注销用户", true).setVisible(true);
		}
		
	}
}
