package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmUserLogoff extends JDialog{
	/**
	 * Create the application.
	 */
	public FrmUserLogoff(FrmUserdetail f,String s, boolean b) {
		super(f,s,b);
		setBounds(100, 100, 392, 253);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("确认");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UserUtil.addressManager.deleteAddress();
					UserUtil.userManager.deleteUser(BeanUser.currentLoginUser);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnOk.setBounds(55, 73, 113, 27);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(201, 73, 113, 27);
		getContentPane().add(btnCancel);
		
	}

}
