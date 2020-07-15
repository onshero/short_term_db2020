package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JLabel;

public class Frmcom_situation extends JDialog{
	int ordnum;
	double spendsum;
	double save;
	public Frmcom_situation(FrmMintoU f, String s,  boolean b) {
		super(f, s, b);
		setBounds(100, 100, 444, 345);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		try {
			ordnum=ManagerUtil.userManager.ordercount();
			spendsum=ManagerUtil.userManager.spend();
			save=ManagerUtil.userManager.save();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("单数：");
		lblNewLabel.setBounds(89, 52, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("总消费：");
		lblNewLabel_1.setBounds(89, 121, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("总优惠：");
		lblNewLabel_2.setBounds(89, 200, 72, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(""+ordnum);
		lblNewLabel_3.setBounds(217, 52, 72, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(""+spendsum+"元");
		lblNewLabel_4.setBounds(217, 121, 72, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(""+save+"元");
		lblNewLabel_5.setBounds(217, 200, 72, 18);
		getContentPane().add(lblNewLabel_5);
	}

}
