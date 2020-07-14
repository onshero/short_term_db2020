package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mysql.cj.util.Util;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;

import javax.swing.JTextField;

public class FrmAddCart extends JDialog implements ActionListener{
	
	private JPanel panel = new JPanel();
	private JButton confirm = new JButton("确认");
	private JButton cancel = new JButton("取消");
	private JPanel panel_1 = new JPanel();
	private JLabel num = new JLabel("数量");
	private JTextField addnum;
	/**
	 * Create the application.
	 */
	public FrmAddCart(JFrame f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 450, 300);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		panel.setBounds(0, 196, 432, 57);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		confirm.setBounds(183, 13, 113, 27);
		panel.add(confirm);
		
		
		cancel.setBounds(310, 13, 113, 27);
		panel.add(cancel);
		
		
		panel_1.setBounds(0, 0, 432, 195);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		num.setBounds(96, 81, 72, 18);
		panel_1.add(num);
		
		addnum = new JTextField();
		addnum.setBounds(172, 78, 137, 24);
		panel_1.add(addnum);
		addnum.setColumns(10);
		
		this.cancel.addActionListener(this);
		this.confirm.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cancel) {
			this.setVisible(false);
			return;
		}else if (e.getSource()==confirm) {
			String num=addnum.getText();
			//if(num==null||"".equals(num)) throw new BusinessException("数量不能为空");
			int get_num=Integer.parseInt(num);
			try {
				if(get_num<=0) throw new BusinessException("数量至少为1");
				UserUtil.orderManager.addOrder(BeanMerchandise.curmerchandise, get_num);
				dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
