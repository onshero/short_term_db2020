package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.*;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FrmUserLogin window = new FrmUserLogin();		
	}

	/**
	 * Create the application.
	 */
	public FrmUserLogin() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setLocation(500,300);
		Label = new JLabel("\u624B\u673A\u53F7");
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
		button.setBounds(47, 181, 113, 27);
		this.getContentPane().add(button);
		
		button_1 = new JButton("µÇÂ¼");
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
