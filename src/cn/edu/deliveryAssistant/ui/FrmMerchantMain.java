package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;
import javax.swing.JMenuItem;

public class FrmMerchantMain extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	List<BeanMerchant> allMerchant=null;
	List<BeanMerchandiseClassify> allClassify=null;
	List<BeanMerchandise> allMerchandise=null;
	int merchant_id;
	int classify_id;
	int merchandise_id;
	private Object listMerchant[];
	private Object listClassify[];
	private Object tblMerchandiseTitle[]=BeanMerchandise.titles;
	private Object listMerchandise[][];
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_merchant = new JMenu("商家管理");
	private JMenu menu_classify = new JMenu("类别管理");
	private JMenu menu_merchandise = new JMenu("商品管理");
	private JList merchant_list = new JList();
	private JLabel lab_merchant = new JLabel("商家");
	private JLabel lab_classify = new JLabel("类别");
	private JList classify_list = new JList();
	DefaultTableModel tabMerchandise=new DefaultTableModel();
	private JTable Merchandise_table = new JTable(tabMerchandise);
	private JScrollPane work_merchant = new JScrollPane();
	private JScrollPane work_classify = new JScrollPane();
	private JScrollPane work_merchandise = new JScrollPane(Merchandise_table);
	private JMenuItem merchant_detail = new JMenuItem("商家信息");
	private JMenuItem addMerchant = new JMenuItem("添加商家");
	private JMenuItem delMerchant = new JMenuItem("删除商家");
	private JMenuItem addClassify = new JMenuItem("添加类别");
	private JMenuItem delClassify = new JMenuItem("删除类别");
	private JMenuItem addMerchandise = new JMenuItem("添加商品");
	private JMenuItem delMerchandise = new JMenuItem("删除商品");
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
		this.Merchandise_table.validate();
		this.Merchandise_table.repaint();
	}
	/**
	 * Create the application.
	 */
	public FrmMerchantMain(FrmManagerMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 900, 551);
		
		setJMenuBar(menuBar);
		
		menuBar.add(menu_merchant);
		
		menu_merchant.add(merchant_detail);this.merchant_detail.addActionListener(this);
		
		menu_merchant.add(addMerchant);this.addMerchant.addActionListener(this);
		
		menu_merchant.add(delMerchant);this.delMerchant.addActionListener(this);
		
		menuBar.add(menu_classify);
		
		menu_classify.add(addClassify);this.addClassify.addActionListener(this);
		
		menu_classify.add(delClassify);this.delClassify.addActionListener(this);
		
		menuBar.add(menu_merchandise);
		
		menu_merchandise.add(addMerchandise);this.addMerchandise.addActionListener(this);
		
		menu_merchandise.add(delMerchandise);this.delMerchandise.addActionListener(this);
		
		getContentPane().setLayout(null);
		
		work_merchant.setBounds(0, 0, 241, 478);
		getContentPane().add(work_merchant);
		
		this.reloadMerhcant();
		merchant_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				tabMerchandise.setRowCount(0);
				Merchandise_table.repaint();
				BeanMerchandise.curmerchandise=null;
				merchant_id=merchant_list.getSelectedIndex();
				BeanMerchant.curmerchant=allMerchant.get(merchant_id);
				FrmMerchantMain.this.reloadClassify(allMerchant.get(merchant_id));
				
			}
		});
		work_merchant.setViewportView(merchant_list);
		
		work_merchant.setColumnHeaderView(lab_merchant);
		
		work_classify.setBounds(243, 0, 241, 478);
		getContentPane().add(work_classify);
		classify_list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				BeanMerchandise.curmerchandise=null;
				classify_id=classify_list.getSelectedIndex();
				BeanMerchandiseClassify.curclassify=allClassify.get(classify_id);
				FrmMerchantMain.this.reloadMerchandise(allClassify.get(classify_id));
			}
		});
		work_classify.setColumnHeaderView(lab_classify);
		
		work_classify.setViewportView(classify_list);
		
		work_merchandise.setBounds(486, 0, 396, 478);
		Merchandise_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				merchandise_id=Merchandise_table.getSelectedRow();
				BeanMerchandise.curmerchandise=allMerchandise.get(merchandise_id);
			}
		});
		getContentPane().add(work_merchandise);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.merchant_detail) {
			if(BeanMerchant.curmerchant==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			new FrmMerchantDetail(this, "商家信息", true).setVisible(true);
		}else if (e.getSource()==this.addMerchant) {
			new FrmaddMerchant(this, "添加商家", true).setVisible(true);
		}else if (e.getSource()==this.delMerchant) {
			if(BeanMerchant.curmerchant==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ManagerUtil.merchantManager.deleteMerchant(BeanMerchant.curmerchant);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadMerhcant();
		}else if (e.getSource()==this.addClassify) {
			if(BeanMerchant.curmerchant==null) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			new FrmaddMerchant(this, "添加类别", true).setVisible(true);
		}else if (e.getSource()==this.delClassify) {
			if(BeanMerchandiseClassify.curclassify==null) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ManagerUtil.classifyManager.deleteClassify(BeanMerchandiseClassify.curclassify);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource()==this.addMerchandise) {
			if(BeanMerchandiseClassify.curclassify==null) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			new FrmaddMerchandise(this, "添加商品", true).setVisible(true);
			this.reloadMerchandise(BeanMerchandiseClassify.curclassify);
		}else if (e.getSource()==this.delMerchandise) {
			if(BeanMerchandise.curmerchandise==null) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ManagerUtil.merchandiseManager.deleteMerchandise(BeanMerchandise.curmerchandise);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
