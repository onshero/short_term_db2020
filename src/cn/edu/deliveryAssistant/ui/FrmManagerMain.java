package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.model.BeanUser;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

public class FrmManagerMain extends JFrame implements ActionListener{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_manager = new JMenu("管理员操作");
	private JMenuItem addmanager = new JMenuItem("添加管理员");
	
	
	
	
	
	/**
	 * Create the application.
	 */
	public FrmManagerMain() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("管理员："+BeanManager.currentLoginManager.getManager_name());
		
		setJMenuBar(menuBar);
		menuBar.add(menu_manager);
		menu_manager.add(addmanager);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
