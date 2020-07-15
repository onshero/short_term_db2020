package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FrmaddRider extends JDialog{
	private JTextField textField;
	String string[]= {"新人","正式员工","单王"};
	JComboBox comboBox = new JComboBox();
	/**
	 * Create the application.
	 */
	public FrmaddRider(FrmRiderMain f, String s,boolean b) {
		super(f, s, b);
		setBounds(100, 100, 507, 377);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setBounds(96, 84, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("身份");
		lblNewLabel_1.setBounds(96, 151, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(193, 81, 128, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String status=comboBox.getSelectedItem().toString();
				System.out.println(comboBox.getSelectedItem().toString());
				try {
					ManagerUtil.riderManager.addRider(name, status);
					dispose();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(95, 227, 113, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(251, 227, 113, 27);
		getContentPane().add(btnNewButton_1);
		
		
		comboBox.addItem("新人");
		comboBox.addItem("正式员工");
		comboBox.addItem("单王");
		comboBox.setBounds(193, 148, 128, 24);
		getContentPane().add(comboBox);
		
	}
}
