package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanAddress;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmUseraddress extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	List<BeanAddress> alladdress=null;
	private Object addresstitle[]=MerchandiseOrder.titles;
	private Object addresslist[][];
	DefaultTableModel taborder=new DefaultTableModel();
	private JTable table = new JTable(taborder);
	JScrollPane scrollPane = new JScrollPane(table);
	JMenuBar menuBar = new JMenuBar();
	JMenu menu_address = new JMenu("地址管理");
	JMenuItem addAddress = new JMenuItem("添加地址");
	JMenuItem delAddress = new JMenuItem("删除地址");
	private JMenuItem defAddress = new JMenuItem("设为默认地址");
	BeanAddress curaddress=null;
	private void reloadAddress() {
		try {
			alladdress=UserUtil.addressManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		addresslist=new Object[alladdress.size()][BeanAddress.titles.length];
		for(int i=0;i<alladdress.size();i++)
			for(int j=0;j<BeanAddress.titles.length;j++)
				addresslist[i][j]=alladdress.get(i).getCell(j);
		taborder.setDataVector(addresslist, BeanAddress.titles);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				curaddress=alladdress.get(i);
			}
		});
		this.table.validate();
		this.table.repaint();
	}
	/**
	 * Create the application.
	 */
	public FrmUseraddress(FrmUserMain f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 543);

		this.setLocation(500,300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		reloadAddress();
		setJMenuBar(menuBar);
		
		menuBar.add(menu_address);
		
		
		menu_address.add(addAddress);this.addAddress.addActionListener(this);
		
		menu_address.add(defAddress);this.defAddress.addActionListener(this);
		
		menu_address.add(delAddress);this.delAddress.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addAddress) {
			new FrmaddAddress(this, "添加地址", true).setVisible(true);
		}else if (e.getSource()==defAddress) {
			if(curaddress==null) {
				JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				UserUtil.addressManager.modifyDefault(curaddress);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource()==delAddress) {
			if(curaddress==null) {
				JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				UserUtil.addressManager.deleteAddress(curaddress);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
