package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmUserMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserMain window = new FrmUserMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmUserMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\uFF1A");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu_user = new JMenu("\u6211\u7684");
		menuBar.add(menu_user);
		
		JMenuItem user_detail = new JMenuItem("\u4E2A\u4EBA\u4FE1\u606F");
		menu_user.add(user_detail);
		
		JMenuItem address = new JMenuItem("\u6211\u7684\u5730\u5740");
		menu_user.add(address);
		
		JMenuItem coupon = new JMenuItem("\u6211\u7684\u4F18\u60E0\u5238");
		menu_user.add(coupon);
		
		JMenuItem modify_password = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu_user.add(modify_password);
		
		JMenu order = new JMenu("\u8BA2\u5355");
		menuBar.add(order);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
	}

}
