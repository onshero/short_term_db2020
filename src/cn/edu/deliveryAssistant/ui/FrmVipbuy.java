package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmVipbuy extends JDialog{
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public FrmVipbuy(FrmUserMain f, String s, boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 312);
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("³äÖµ½ð¶î£º");
		lblNewLabel.setBounds(75, 102, 88, 18);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(177, 99, 140, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("³äÖµ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money=Integer.parseInt(textField.getText());
				try {
					UserUtil.userManager.VIP(BeanUser.currentLoginUser, money);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(136, 185, 113, 27);
		getContentPane().add(btnNewButton);
	}

}
