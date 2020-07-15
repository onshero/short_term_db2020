package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.model.CouponGot;
import cn.edu.deliveryAssistant.util.BaseException;

public class FrmUserCoupon extends JDialog{
	private static final long serialVersionUID = 1L;
	
	List<CouponGot> allCoupon=null;
	private Object coupontitle[]=CouponGot.titles;
	private Object couponlist[][];
	DefaultTableModel tabcoupon=new DefaultTableModel();
	private JTable table = new JTable(tabcoupon);
	JScrollPane scrollPane = new JScrollPane(table);
	
	private void reloadCoupon() {
		try {
			allCoupon=UserUtil.couponManager.loadUserGot(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
			return;
		}
		couponlist=new Object[allCoupon.size()][CouponGot.titles.length];
		for(int i=0;i<allCoupon.size();i++)
			for(int j=0;j<CouponGot.titles.length;j++)
				couponlist[i][j]=allCoupon.get(i).getCell(j);
		tabcoupon.setDataVector(couponlist, CouponGot.titles);
		this.table.validate();
		this.table.repaint();
	}
	public FrmUserCoupon(FrmUserMain f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 543);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		this.reloadCoupon();
		scrollPane.setBounds(39, 0, 2, 2);
		getContentPane().add(scrollPane);
		
		
		table.setBounds(161, 181, 1, 1);
		getContentPane().add(table);
	}
}
