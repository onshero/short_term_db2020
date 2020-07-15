package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmManagerMain extends JFrame implements ActionListener{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_manager = new JMenu("员工管理");
	private JMenuItem addmanager = new JMenuItem("添加员工");
	private JMenuItem delmanager = new JMenuItem("删除员工");
	private JMenuItem modifyPwd = new JMenuItem("更改密码");
	private JMenu menu_more = new JMenu("更多");
	DefaultTableModel tabManager=new DefaultTableModel();
	private JTable manager_table = new JTable(tabManager);
	private JMenuItem user_manager = new JMenuItem("用户管理");
	private JMenuItem merchant_manager = new JMenuItem("商家管理");
	private JMenuItem rider_manager = new JMenuItem("骑手管理");
	private JScrollPane scrollPane = new JScrollPane(manager_table);
	
	List<BeanManager> allManager=null;
	int manager_id;
	private Object managertitle[]=BeanManager.title;
	private Object ListManager[][];
	
	private void reloadManager() {
		try {
			allManager=ManagerUtil.managerManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		ListManager=new Object[allManager.size()][BeanManager.title.length];
		for(int i=0;i<allManager.size();i++)
			for(int j=0;j<BeanManager.title.length;j++)
				ListManager[i][j]=allManager.get(i).getCell(j);
		tabManager.setDataVector(ListManager, managertitle);
		this.manager_table.validate();
		this.manager_table.repaint();
	}
	/**
	 * Create the application.
	 */
	public FrmManagerMain() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 379, 378);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(500,300);
		this.setTitle("管理员："+BeanManager.currentLoginManager.getManager_name());
		
		setJMenuBar(menuBar);
		menuBar.add(menu_manager);
		menu_manager.add(addmanager);this.addmanager.addActionListener(this);
		
		menu_manager.add(delmanager);this.delmanager.addActionListener(this);
		menu_manager.add(modifyPwd);this.modifyPwd.addActionListener(this);

		menuBar.add(menu_more);
		
		menu_more.add(user_manager);this.user_manager.addActionListener(this);
		
		menu_more.add(merchant_manager);this.merchant_manager.addActionListener(this);
		
		menu_more.add(rider_manager);this.rider_manager.addActionListener(this);
		
		this.reloadManager();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(scrollPane);
		
		//scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addmanager) {
			new FrmaddManager(this,"添加员工",true).setVisible(true);
			this.reloadManager();
		}else if (e.getSource()==delmanager) {
			int i=FrmManagerMain.this.manager_table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择员工", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ManagerUtil.managerManager.deleteManager(allManager.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadManager();
		}else if (e.getSource()==modifyPwd) {
			new FrmModifyPwd(this, "更改密码", true).setVisible(true);
		}else if (e.getSource()==user_manager) {
			new FrmMintoU(this, "用户管理", true).setVisible(true);
		}else if (e.getSource()==merchant_manager) {
			new FrmMerchantMain(this, "商家管理", true).setVisible(true);
		}else if (e.getSource()==rider_manager) {
			new FrmRiderMain(this, "骑手管理", true).setVisible(true);
		}
	}
}
