package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FrmaddMerchant extends JDialog{
	private JTextField textField;
	
	public FrmaddMerchant(FrmMerchantMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 371, 212);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("名称");
		lblNewLabel.setBounds(53, 63, 72, 18);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(123, 60, 145, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=textField.getText();
				try {
					if(s.equals("添加商家")) ManagerUtil.merchantManager.addMerchant(name);
					else if(s.equals("添加类别")) ManagerUtil.classifyManager.addClassify(BeanMerchant.curmerchant, name);
					dispose();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(53, 108, 113, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(189, 108, 113, 27);
		getContentPane().add(btnNewButton_1);
	}
}
