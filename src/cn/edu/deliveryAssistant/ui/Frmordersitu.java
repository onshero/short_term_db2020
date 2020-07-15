package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanOrder;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;

public class Frmordersitu extends JDialog{
	private static final long serialVersionUID = 1L;
	List<MerchandiseOrder> allorder=null;
	private Object ordertitle[]=MerchandiseOrder.titles;
	private Object orderlist[][];
	DefaultTableModel taborder=new DefaultTableModel();
	private JTable table = new JTable(taborder);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	JComboBox comboBox = new JComboBox();
	
	private void reloadtab() {
		try {
			allorder=UserUtil.orderManager.searchbystatus();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		orderlist=new Object[allorder.size()][MerchandiseOrder.titles.length];
		for(int i=0;i<allorder.size();i++)
			for(int j=0;j<MerchandiseOrder.titles.length;j++)
				orderlist[i][j]=allorder.get(i).getCell(j);
		taborder.setDataVector(orderlist, MerchandiseOrder.titles);
		this.table.validate();
		this.table.repaint();
	}
	private void reloadtab(String status) {
		try {
			allorder=UserUtil.orderManager.searchbystatus(status);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		orderlist=new Object[allorder.size()][MerchandiseOrder.titles.length];
		for(int i=0;i<allorder.size();i++)
			for(int j=0;j<MerchandiseOrder.titles.length;j++)
				orderlist[i][j]=allorder.get(i).getCell(j);
		taborder.setDataVector(orderlist, MerchandiseOrder.titles);
		this.table.validate();
		this.table.repaint();
	}
	/**
	 * Create the application.
	 */
	public Frmordersitu(FrmUserMain f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 543);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		
		panel.setBounds(0, 0, 437, 72);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox.addItem("全部");
		comboBox.addItem("配送中");
		comboBox.addItem("超时");
		comboBox.addItem("送达");
		comboBox.addItem("取消");
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(comboBox.getSelectedItem().toString().equals("全部")) {
					reloadtab();
				}else {
					reloadtab(comboBox.getSelectedItem().toString());
				}
				
			}
		});
		comboBox.setBounds(131, 23, 151, 24);
		panel.add(comboBox);
		
		
		scrollPane.setBounds(0, 72, 437, 426);
		getContentPane().add(scrollPane);
		
		
		//scrollPane.setViewportView(table);
		
	}
}
