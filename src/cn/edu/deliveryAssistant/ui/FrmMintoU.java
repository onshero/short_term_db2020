package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMintoU extends JDialog implements ActionListener{
	DefaultTableModel tabuser=new DefaultTableModel();
	private JTable table = new JTable(tabuser);

	JMenuBar menuBar = new JMenuBar();
	JMenu more = new JMenu("更多");
	JMenuItem com_situ = new JMenuItem("消费情况");
	JScrollPane scrollPane = new JScrollPane(table);
	
	List<BeanUser> allUser=null;
	private Object usertitle[]=BeanUser.titles;
	private Object usertab[][];
	private void reloadUser() {
		try {
			allUser=ManagerUtil.userManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		usertab=new Object[allUser.size()][BeanUser.titles.length];
		for(int i=0;i<allUser.size();i++)
			for(int j=0;j<BeanUser.titles.length;j++)
				usertab[i][j]=allUser.get(i).getCell(j);
		this.tabuser.setDataVector(usertab, BeanUser.titles);
		this.table.validate();
		this.table.repaint();
	}
	/**
	 * Create the application.
	 */
	public FrmMintoU(FrmManagerMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 559, 557);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				BeanUser.currentLoginUser=allUser.get(i);
			}
		});
		
		
		this.reloadUser();
		setJMenuBar(menuBar);
		menuBar.add(more);
		more.add(com_situ);this.com_situ.addActionListener(this);
		
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		//scrollPane.setViewportView(table);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==com_situ) {
			if(BeanUser.currentLoginUser==null) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			new Frmcom_situation(this, "消费情况", true).setVisible(true);
		}
	}


}
