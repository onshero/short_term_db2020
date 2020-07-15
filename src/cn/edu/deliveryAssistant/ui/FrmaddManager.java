package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.BaseException;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class FrmaddManager extends JDialog implements ActionListener{

	private JLabel man_name = new JLabel("姓名：");
	private JTextField name = new JTextField();
	private JPasswordField pwd = new JPasswordField();
	private JPasswordField pwd1 = new JPasswordField();
	private JLabel man_pwd = new JLabel("密码：");
	private JLabel man_pwd1 = new JLabel("密码：");
	private JButton btnok = new JButton("确认");
	private JButton btncancel = new JButton("取消");
	/**
	 * Create the application.
	 */
	public FrmaddManager(FrmManagerMain f, String s, boolean b) {
		super(f, s, b);
		setBounds(100, 100, 450, 300);

		this.setLocation(500,300);
		getContentPane().setLayout(null);
		
		
		man_name.setBounds(69, 33, 72, 18);
		getContentPane().add(man_name);
		
		
		man_pwd.setBounds(69, 77, 72, 18);
		getContentPane().add(man_pwd);
		
		
		man_pwd1.setBounds(69, 122, 72, 18);
		getContentPane().add(man_pwd1);
		
		
		name.setBounds(163, 30, 152, 24);
		getContentPane().add(name);
		name.setColumns(10);
		
		
		pwd.setBounds(163, 74, 152, 24);
		getContentPane().add(pwd);
		
		
		pwd1.setBounds(163, 119, 152, 24);
		getContentPane().add(pwd1);
		
		
		btnok.setBounds(69, 196, 113, 27);
		getContentPane().add(btnok);this.btnok.addActionListener(this);
		
		
		btncancel.setBounds(240, 196, 113, 27);
		getContentPane().add(btncancel);this.btncancel.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btncancel) {
			this.setVisible(false);
		}else if (e.getSource()==btnok) {
			String n=this.name.getText();
			String p=new String(this.pwd.getPassword());
			String p1=new String(this.pwd1.getPassword());
			try {
				if(!p.equals(p1)) throw new BusinessException("密码不一致");
				BeanManager manager=ManagerUtil.managerManager.addManager(n, p1);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
}
