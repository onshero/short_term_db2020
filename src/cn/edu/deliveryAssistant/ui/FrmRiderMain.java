package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRiderMain extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar = new JMenuBar();
	JMenu menu_rider = new JMenu("骑手管理");
	JMenuItem addrider = new JMenuItem("添加骑手");
	JMenuItem delrider = new JMenuItem("删除骑手");
	JMenu more = new JMenu("更多");
	JMenuItem situation = new JMenuItem("骑手情况");
	DefaultTableModel tabrider=new DefaultTableModel();
	private JTable table = new JTable(tabrider);
	JScrollPane scrollPane = new JScrollPane(table);
	List<BeanRider> allrider=null;
	private Object listrider[][];
	private Object ridertitle[]=BeanRider.titles;
	private final JMenuItem rider_evaluate = new JMenuItem("骑手评价");
	
	private void reloadRidersituation() {
		try {
			allrider=ManagerUtil.riderManager.countOrdre();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		ridertitle=BeanRider.titles2;
		listrider=new Object[allrider.size()][BeanRider.titles2.length];
		for(int i=0;i<allrider.size();i++)
			for(int j=0;j<BeanRider.titles2.length;j++)
				listrider[i][j]=allrider.get(i).getCell2(j);
		tabrider.setDataVector(listrider, ridertitle);
		this.table.validate();
		this.table.repaint();
	}
	private void reloadRider() {
		try {
			allrider=ManagerUtil.riderManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		listrider=new Object[allrider.size()][BeanRider.titles.length];
		for(int i=0;i<allrider.size();i++)
			for(int j=0;j<BeanRider.titles.length;j++)
				listrider[i][j]=allrider.get(i).getCell(j);
		tabrider.setDataVector(listrider, ridertitle);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				BeanRider.currider=allrider.get(i);
			}
		});
		this.table.validate();
		this.table.repaint();
	}
	public FrmRiderMain(FrmManagerMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 900, 551);
		
		setJMenuBar(menuBar);
		menuBar.add(menu_rider);
		menu_rider.add(addrider);this.addrider.addActionListener(this);
		menu_rider.add(delrider);this.delrider.addActionListener(this);
		menuBar.add(more);
		more.add(situation);this.situation.addActionListener(this);
		
		more.add(rider_evaluate);this.rider_evaluate.addActionListener(this);
		
		this.reloadRider();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addrider) {
			new FrmaddRider(this, "添加骑手", true).setVisible(true);
			this.reloadRider();
		}else if (e.getSource()==delrider) {
			if(BeanRider.currider==null){
				JOptionPane.showMessageDialog(null, "请选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				ManagerUtil.riderManager.deleteRider(BeanRider.currider);
				reloadRider();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadRider();
		}else if (e.getSource()==situation) {
			this.reloadRidersituation();
		}else if (e.getSource()==rider_evaluate) {
			if(BeanRider.currider==null){
				JOptionPane.showMessageDialog(null, "请选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			new FrmRiderEva(this, "骑手评价", true).setVisible(true);
		}
	}

}
