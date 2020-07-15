package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmaddMerchandise extends JDialog{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	
	/**
	 * Create the application.
	 */
	public FrmaddMerchandise(FrmMerchantMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 317, 362);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品");
		lblNewLabel.setBounds(46, 53, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("单价");
		lblNewLabel_1.setBounds(46, 84, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("单品优惠价格");
		lblNewLabel_2.setBounds(46, 115, 72, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("余量");
		lblNewLabel_3.setBounds(46, 146, 72, 18);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(142, 50, 86, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 81, 86, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(142, 112, 86, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 143, 86, 24);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				int unit_price=Integer.parseInt(textField_1.getText());
				int discount_unit_price=Integer.parseInt(textField_2.getText());
				int remain=Integer.parseInt(textField_3.getText());
				try {
					ManagerUtil.merchandiseManager.addMerchandise(BeanMerchandiseClassify.curclassify, name, unit_price, discount_unit_price, remain);
					setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(37, 210, 113, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(153, 210, 113, 27);
		getContentPane().add(btnNewButton_1);
		
	}


//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==)
//		
//	}

}
