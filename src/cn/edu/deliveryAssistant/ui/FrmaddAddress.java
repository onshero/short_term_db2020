package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmaddAddress extends JDialog{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	

	/**
	 * Create the application.
	 */
	public FrmaddAddress(FrmUseraddress f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 419, 467);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省");
		lblNewLabel.setBounds(67, 55, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("市");
		lblNewLabel_1.setBounds(67, 101, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("区");
		lblNewLabel_2.setBounds(67, 146, 72, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("地址");
		lblNewLabel_3.setBounds(67, 189, 72, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("联系人");
		lblNewLabel_4.setBounds(67, 231, 72, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("电话");
		lblNewLabel_5.setBounds(67, 272, 72, 18);
		getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(175, 52, 121, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 98, 121, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(175, 143, 121, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(175, 186, 121, 24);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(175, 228, 121, 24);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(175, 269, 121, 24);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String province=textField.getText();
				String city=textField_1.getText();
				String area=textField_2.getText();
				String address=textField_3.getText();
				String contact_person=textField_4.getText();
				String phone_num=textField_5.getText();
				try {
					UserUtil.addressManager.addAddress(BeanUser.currentLoginUser, province, city, area, address, contact_person, phone_num);
					setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(56, 330, 113, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(208, 330, 113, 27);
		getContentPane().add(btnNewButton_1);
	}

	

}
