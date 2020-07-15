package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanOrder;
import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class FrmCart extends JDialog implements ActionListener {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_merchant = new JMenu("商家操作");
	private JMenu menu_merchandise = new JMenu("商品操作");
	private JMenuItem del_merchant = new JMenuItem("删除商家");
	private JMenuItem del_merchandise = new JMenuItem("删除商品");
	private JMenuItem des_merchandise = new JMenuItem("减少商品");
	List<BeanMerchant> allMerchant=null;
	List<BeanOrder> allMerchandise=null;
	int merchant_id;
	int merchandise_id;
	private Object listMerchant[];
	private Object tblMerchandiseTitle[]=BeanOrder.titles;
	private Object listMerchandise[][];
	DefaultTableModel tabMerchandise=new DefaultTableModel();
	private JTable merchandise_table = new JTable(tabMerchandise);
	private JScrollPane merchandise = new JScrollPane(merchandise_table);
	private JScrollPane merchant = new JScrollPane();
	private JList merchant_list = new JList();
	private JLabel merchant_name = new JLabel("商家");
	
	BeanOrder curorder=null;
	BeanMerchant curmerchant=null;
	private final JMenuItem btnOK = new JMenuItem("下单");
	
	private void reloadMerhcant() {
		try {
			allMerchant=UserUtil.merchantManager.loadbyOrder();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listMerchant= new Object[allMerchant.size()];
		for(int i=0;i<allMerchant.size();i++)
			listMerchant[i]=allMerchant.get(i).getCell(0);
		merchant_list.setListData(listMerchant);
	}
	private void reloadMerchandise(BeanMerchant merchant) {
		try {
			allMerchandise=UserUtil.orderManager.loadbyMerchant(merchant);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listMerchandise=new Object[allMerchandise.size()][BeanOrder.titles.length];
		for(int i=0;i<allMerchandise.size();i++)
			for(int j=0;j<BeanOrder.titles.length;j++)
				listMerchandise[i][j]=allMerchandise.get(i).getCell(j);
		tabMerchandise.setDataVector(listMerchandise, tblMerchandiseTitle);
		this.merchandise_table.validate();
		this.merchandise_table.repaint();
	}
	/**
	 * Create the application.
	 */
	public FrmCart(JFrame f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 664, 436);

		this.setLocation(500,300);
		setJMenuBar(menuBar);
		
		menuBar.add(menu_merchant);
		
		menu_merchant.add(del_merchant);this.del_merchant.addActionListener(this);
		
		menu_merchant.add(btnOK);this.btnOK.addActionListener(this);
		
		menuBar.add(menu_merchandise);
		
		menu_merchandise.add(del_merchandise);this.del_merchandise.addActionListener(this);
		
		menu_merchandise.add(des_merchandise);this.des_merchandise.addActionListener(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		GridBagConstraints gbc_merchant = new GridBagConstraints();
		gbc_merchant.gridwidth = 2;
		gbc_merchant.insets = new Insets(0, 0, 0, 5);
		gbc_merchant.fill = GridBagConstraints.BOTH;
		gbc_merchant.gridx = 0;
		gbc_merchant.gridy = 0;
		getContentPane().add(merchant, gbc_merchant);
		this.reloadMerhcant();
		merchant_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				BeanOrder.curorder=null;
				merchant_id=merchant_list.getSelectedIndex();
				curmerchant=allMerchant.get(merchant_id);
				FrmCart.this.reloadMerchandise(allMerchant.get(merchant_id));
				
			}
		});
		merchant.setViewportView(merchant_list);
		
		merchant.setColumnHeaderView(merchant_name);
		
		GridBagConstraints gbc_merchandise = new GridBagConstraints();
		gbc_merchandise.gridwidth = 4;
		gbc_merchandise.fill = GridBagConstraints.BOTH;
		gbc_merchandise.gridx = 2;
		gbc_merchandise.gridy = 0;
		getContentPane().add(merchandise, gbc_merchandise);
		
		
		
		merchandise_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				merchandise_id=merchandise_table.getSelectedRow();
				BeanOrder.curorder=allMerchandise.get(merchandise_id);
			}
		});
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==del_merchant) {
			
		}else if (e.getSource()==btnOK) {
			int i=merchant_list.getSelectedIndex();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				BeanMerchant merchant =allMerchant.get(i);
				BeanOrder order=UserUtil.orderManager.loadbymerchant(merchant);
				BeanRider rider=UserUtil.riderManager.loadRandom();
				UserUtil.orderManager.addtotalOrder(BeanUser.currentLoginUser, merchant, rider, order);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadMerhcant();
		}else if (e.getSource()==del_merchandise) {
			try {
				if(BeanOrder.curorder==null) throw new BusinessException("未选中商品");
				UserUtil.orderManager.deleteOrder(BeanOrder.curorder);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource()==des_merchandise) {
			
		}
		
	}
}
