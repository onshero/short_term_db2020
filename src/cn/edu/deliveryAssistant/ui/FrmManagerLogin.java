package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmManagerLogin extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label;
	private JLabel label_1;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FrmManagerLogin window = new FrmManagerLogin();
	}

	/**
	 * Create the application.
	 */
	public FrmManagerLogin() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		label = new JLabel("管理员");
		label.setBounds(102, 62, 54, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("密码");
		label_1.setBounds(102, 100, 54, 15);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(180, 59, 128, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 97, 128, 21);
		getContentPane().add(passwordField);
		
		button = new JButton("登录");
		button.setBounds(102, 162, 95, 25);
		getContentPane().add(button);
		
		button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrmSelect();
			}
		});
		button_1.setBounds(239, 162, 95, 25);
		getContentPane().add(button_1);
		
		
		this.setVisible(true);
	}
}
