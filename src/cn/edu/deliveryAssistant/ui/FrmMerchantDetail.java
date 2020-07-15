package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cn.edu.deliveryAssistant.model.BeanMerchant;

public class FrmMerchantDetail extends JDialog{

	
	/**
	 * Create the application.
	 */
	public FrmMerchantDetail(FrmMerchantMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 381, 456);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商家");
		lblNewLabel.setBounds(62, 58, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("星级");
		lblNewLabel_1.setBounds(62, 110, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("人均消费");
		lblNewLabel_2.setBounds(62, 167, 72, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("总销量");
		lblNewLabel_3.setBounds(62, 227, 72, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(BeanMerchant.curmerchant.getCell(0));
		lblNewLabel_4.setBounds(202, 58, 72, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(BeanMerchant.curmerchant.getCell(1));
		lblNewLabel_5.setBounds(202, 110, 72, 18);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(BeanMerchant.curmerchant.getCell(2));
		lblNewLabel_6.setBounds(202, 167, 72, 18);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(BeanMerchant.curmerchant.getCell(3));
		lblNewLabel_7.setBounds(202, 227, 72, 18);
		getContentPane().add(lblNewLabel_7);
		
		
	}
}
