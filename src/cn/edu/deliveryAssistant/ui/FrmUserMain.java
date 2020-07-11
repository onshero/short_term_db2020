package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.BeanUser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class FrmUserMain extends JFrame{

	List<BeanMerchant> allMerchant=null;
	List<BeanMerchandiseClassify> allClassify=null;
	List<BeanMerchandise> allMerchandise=null;

	/**
	 * Create the application.
	 */
	public FrmUserMain() {
		setTitle("用户："+BeanUser.currentLoginUser.getUser_name());
		setBounds(100, 100, 671, 473);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu take_out = new JMenu("外卖");
		menuBar.add(take_out);
		
		JMenuItem total = new JMenuItem("全部");
		total.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		take_out.add(total);
		
		JMenu cart = new JMenu("购物车");
		menuBar.add(cart);
		
		JMenu order = new JMenu("订单");
		menuBar.add(order);
		
		JMenu menu_user = new JMenu("我的");
		menuBar.add(menu_user);
		
		JMenuItem user_detail = new JMenuItem("个人信息");
		menu_user.add(user_detail);
		
		JMenuItem address = new JMenuItem("我的地址");
		menu_user.add(address);
		
		JMenuItem coupon = new JMenuItem("我的优惠券");
		menu_user.add(coupon);
		
		JMenuItem modify_password = new JMenuItem("修改密码");
		menu_user.add(modify_password);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane merchant = new JScrollPane();
		GridBagConstraints gbc_merchant = new GridBagConstraints();
		gbc_merchant.insets = new Insets(0, 0, 0, 5);
		gbc_merchant.fill = GridBagConstraints.BOTH;
		gbc_merchant.gridx = 0;
		gbc_merchant.gridy = 0;
		getContentPane().add(merchant, gbc_merchant);
		
		JList merchant_list = new JList();
		merchant.setViewportView(merchant_list);
		
		JLabel merchant_label = new JLabel("商家");
		merchant.setColumnHeaderView(merchant_label);
		
		JScrollPane classify = new JScrollPane();
		GridBagConstraints gbc_classify = new GridBagConstraints();
		gbc_classify.insets = new Insets(0, 0, 0, 5);
		gbc_classify.fill = GridBagConstraints.BOTH;
		gbc_classify.gridx = 1;
		gbc_classify.gridy = 0;
		getContentPane().add(classify, gbc_classify);
		
		JList classify_list = new JList();
		classify.setViewportView(classify_list);
		
		JLabel classify_label = new JLabel("类别");
		classify.setColumnHeaderView(classify_label);
		
		JScrollPane merchandise = new JScrollPane();
		GridBagConstraints gbc_merchandise = new GridBagConstraints();
		gbc_merchandise.fill = GridBagConstraints.BOTH;
		gbc_merchandise.gridx = 2;
		gbc_merchandise.gridy = 0;
		getContentPane().add(merchandise, gbc_merchandise);
		
		JList merchandise_list = new JList();
		merchandise.setViewportView(merchandise_list);
		
		JLabel merchandise_label = new JLabel("商品");
		merchandise.setColumnHeaderView(merchandise_label);
		
		this.setVisible(true);
	}
}
