package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		label = new JLabel("π‹¿Ì‘±");
		label.setBounds(102, 62, 54, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("√‹¬Î");
		label_1.setBounds(102, 100, 54, 15);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(180, 59, 128, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 97, 128, 21);
		getContentPane().add(passwordField);
		
		button = new JButton("µ«¬º");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String manager_name=textField.getText();
				String pwd=new String(passwordField.getPassword());
				try {
					//BeanUser.currentLoginUser = UserUtil.userManager.login(phone_num, pwd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
					return;
				}
				dispose();
			}
		});
		button.setBounds(102, 162, 95, 25);
		getContentPane().add(button);
		
		button_1 = new JButton("∑µªÿ");
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
