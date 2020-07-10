package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.*;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FrmUserLogin extends JFrame{

	private JTextField textField;
	private JPasswordField passwordField;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel Label;
	private JLabel Label_1;
	/**
	 * Create the application.
	 */
	public FrmUserLogin() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setLocation(500,300);
		Label = new JLabel("ÊÖ»úºÅ");
		Label.setBounds(92, 55, 72, 18);
		this.getContentPane().add(Label);
		
		Label_1 = new JLabel("ÃÜÂë");
		Label_1.setBounds(92, 86, 72, 18);
		this.getContentPane().add(Label_1);
		
		textField = new JTextField();
		textField.setBounds(165, 52, 170, 24);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 86, 170, 24);
		this.getContentPane().add(passwordField);
		
		button = new JButton("×¢²á");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUserRegister register=new FrmUserRegister();
				register.setVisible(true);
			}
		});
		button.setBounds(47, 181, 113, 27);
		this.getContentPane().add(button);
		
		button_1 = new JButton("µÇÂ¼");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone_num=textField.getText();
				String pwd=new String(passwordField.getPassword());
				try {
					BeanUser.currentLoginUser = UserUtil.userManager.login(phone_num, pwd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
					return;
				}
				dispose();
			}
		});
		button_1.setBounds(178, 181, 113, 27);
		this.getContentPane().add(button_1);
		
		button_2 = new JButton("·µ»Ø");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrmSelect();
			}
		});
		button_2.setBounds(305, 181, 113, 27);
		this.getContentPane().add(button_2);
		
		this.setVisible(true);
	}
}
