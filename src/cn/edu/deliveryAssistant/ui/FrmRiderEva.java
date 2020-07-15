package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.util.BaseException;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class FrmRiderEva extends JDialog{

	JScrollPane scrollPane = new JScrollPane();
	JList list = new JList();
	private Object eva[];
	private List<String> alleva=null;
	
	private void reloadEva() {
		try {
			alleva=ManagerUtil.riderManager.loadEva(BeanRider.currider);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(int i=0;i<alleva.size();i++)
			eva[i]=alleva.get(i);
		list.setListData(eva);
	}
	public FrmRiderEva(FrmRiderMain f, String s,boolean b) {
		super(f, s, b);
		setBounds(100, 100, 459, 547);

		this.setLocation(500,300);
		
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(list);
	}
}
