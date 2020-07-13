package cn.edu.deliveryAssistant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.ui.FrmModifyPwd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class FrmUserMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private BeanMerchandise curmerchandise=null;
	List<BeanMerchant> allMerchant=null;
	List<BeanMerchandiseClassify> allClassify=null;
	List<BeanMerchandise> allMerchandise=null;
	int merchant_id;
	int classify_id;
	int merchandise_id;
	int addCart_num;
	private Object listMerchant[];
	private Object listClassify[];
	private Object tblMerchandiseTitle[]=BeanMerchandise.titles;
	private Object listMerchandise[][];
	private JMenuBar menuBar = new JMenuBar();
	private JMenu take_out = new JMenu("外卖");
	private JMenuItem addCart = new JMenuItem("加入购物车");
	private JMenuItem deleteCart = new JMenuItem("移除购物车");
	private JMenu cart = new JMenu("购物车");
	private JMenu order = new JMenu("订单");
	private JMenu menu_user = new JMenu("我的");
	private JMenuItem user_detail = new JMenuItem("个人信息");
	private JMenuItem address = new JMenuItem("我的地址");
	private JMenuItem coupon = new JMenuItem("我的优惠券");
	private JMenuItem modify_password = new JMenuItem("修改密码");
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private JScrollPane merchant = new JScrollPane();
	private GridBagConstraints gbc_merchant = new GridBagConstraints();
	private JList merchant_list = new JList();
	private JLabel merchant_label = new JLabel("商家");
	private JScrollPane classify = new JScrollPane();
	private GridBagConstraints gbc_classify = new GridBagConstraints();
	private JList classify_list = new JList();
	private JLabel classify_label = new JLabel("类别");
	DefaultTableModel tabMerchandise=new DefaultTableModel();
	private JTable merchandise_table = new JTable(tabMerchandise);
	private GridBagConstraints gbc_merchandise = new GridBagConstraints();
	
	private FrmUserLogin dlgLogin=null;
	
	
	private void reloadMerhcant() {
		try {
			allMerchant=UserUtil.merchantManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listMerchant= new Object[allMerchant.size()];
		for(int i=0;i<allMerchant.size();i++)
			listMerchant[i]=allMerchant.get(i).getCell(0);
		merchant_list.setListData(listMerchant);
		tabMerchandise.getDataVector().clear();

	}
	private void reloadClassify(BeanMerchant merchant) {
		try {
			allClassify=UserUtil.classifyManager.loadAll(merchant);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listClassify=new Object[allClassify.size()];
		for(int i=0;i<allClassify.size();i++)
			listClassify[i]=allClassify.get(i).getCell(0);
		classify_list.setListData(listClassify);
	}
	private void reloadMerchandise(BeanMerchandiseClassify classify) {
		try {
			allMerchandise=UserUtil.merchandiseManager.load(classify);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listMerchandise=new Object[allMerchandise.size()][BeanMerchandise.titles.length];
		for(int i=0;i<allMerchandise.size();i++)
			for(int j=0;j<BeanMerchandise.titles.length;j++)
				listMerchandise[i][j]=allMerchandise.get(i).getCell(j);
		tabMerchandise.setDataVector(listMerchandise, tblMerchandiseTitle);
		this.merchandise_table.validate();
		this.merchandise_table.repaint();
	}
	
	
	/**
	 * Create the application.
	 */
	public FrmUserMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("用户："+BeanUser.currentLoginUser.getUser_name());
		
		this.take_out.add(addCart);	this.addCart.addActionListener(this);
		this.menu_user.add(user_detail);this.user_detail.addActionListener(this);
		this.menu_user.add(address);this.address.addActionListener(this);
		this.menu_user.add(coupon);this.coupon.addActionListener(this);
		this.menu_user.add(modify_password);this.modify_password.addActionListener(this);
		
		menuBar.add(take_out);
		
		take_out.add(deleteCart);
		menuBar.add(cart);
		menuBar.add(order);	
		menuBar.add(menu_user);
		
		this.setJMenuBar(menuBar);
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);

		gbc_merchant.gridwidth = 3;
		gbc_merchant.insets = new Insets(0, 0, 0, 5);
		gbc_merchant.fill = GridBagConstraints.BOTH;
		gbc_merchant.gridx = 0;
		gbc_merchant.gridy = 0;
		this.getContentPane().add(merchant, gbc_merchant);
		
		this.reloadMerhcant();
		
		merchant_list.setFont(new Font("宋体", Font.PLAIN, 24));
		
		merchant_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				tabMerchandise.setRowCount(0);
				merchandise_table.repaint();
				merchant_id=merchant_list.getSelectedIndex();
				FrmUserMain.this.reloadClassify(allMerchant.get(merchant_id));
				
			}
		});
		merchant.setViewportView(merchant_list);

		merchant.setColumnHeaderView(merchant_label);

		gbc_classify.gridwidth = 3;
		gbc_classify.insets = new Insets(0, 0, 0, 5);
		gbc_classify.fill = GridBagConstraints.BOTH;
		gbc_classify.gridx = 3;
		gbc_classify.gridy = 0;
		this.getContentPane().add(classify, gbc_classify);
		classify_list.setFont(new Font("宋体", Font.PLAIN, 24));

		classify_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				classify_id=classify_list.getSelectedIndex();
				FrmUserMain.this.reloadMerchandise(allClassify.get(classify_id));
			}
		});
		classify.setViewportView(classify_list);
		classify.setColumnHeaderView(classify_label);

		gbc_merchandise.gridwidth = 5;
		gbc_merchandise.fill = GridBagConstraints.BOTH;
		gbc_merchandise.gridx = 6;
		gbc_merchandise.gridy = 0;
		merchandise_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				merchandise_id=merchandise_table.getSelectedRow();
				BeanMerchandise.curmerchandise=allMerchandise.get(merchandise_id);
			}
		});
		merchandise_table.setFont(new Font("宋体", Font.PLAIN, 20));
		merchandise_table.setRowHeight(24);
		this.getContentPane().add(new JScrollPane(merchandise_table), gbc_merchandise);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addCart) {
			
			new FrmAddCart(this, "加入购物车", true).setVisible(true);
		}else if (e.getSource()==this.modify_password) {
			new FrmModifyPwd(this, "修改密码", true).setVisible(true);
		}
	}
}
