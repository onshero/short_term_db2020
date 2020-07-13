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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mysql.cj.util.Util;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandise;

public class FrmAddCart extends JDialog implements ActionListener{
	int get_num;
	private JPanel panel = new JPanel();
	private JButton confirm = new JButton("确认");
	private JButton cancel = new JButton("取消");
	private JPanel panel_1 = new JPanel();
	private JLabel num = new JLabel("数量");
	SpinnerModel spinnerModel=new SpinnerNumberModel(1, 1, 100, 1);
	private JSpinner addnum = new JSpinner(spinnerModel);
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
		
		
		num.setBounds(85, 81, 72, 18);
		panel_1.add(num);
		
		
		addnum.setBounds(187, 78, 152, 24);
		addnum.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				SpinnerModel source=(SpinnerModel)e.getSource();
				get_num=(int)source.getValue();
			}
		});
		panel_1.add(addnum);
		
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
			try {
				UserUtil.orderManager.addOrder(BeanMerchandise.curmerchandise, get_num);
				this.setVisible(false);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
