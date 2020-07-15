package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import cn.edu.deliveryAssistant.ManagerUtil;
import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanManager;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;

import java.awt.BorderLayout;
import java.awt.Button;

public class FrmModifyPwd extends JDialog implements ActionListener{

	int t=0;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPwdOld = new JLabel("原密码：");
	private JLabel labelPwd = new JLabel("新密码：");
	private JLabel labelPwd2 = new JLabel("新密码：");
	private JPasswordField edtPwdOld = new JPasswordField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	/**
	 * Create the application.
	 */
	public FrmModifyPwd(Frame f, String s, boolean b) {
		super(f, s, b);
		if(s.equals("更改密码")) t=1;
		else if(s.equals("修改密码")) t=2;
		setBounds(100, 100, 450, 300);

		this.setLocation(500,300);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelPwdOld.setBounds(25, 8, 60, 18);
		workPane.add(labelPwdOld);
		edtPwdOld.setBounds(90, 5, 166, 24);
		workPane.add(edtPwdOld);
		labelPwd.setBounds(25, 39, 60, 18);
		workPane.add(labelPwd);
		edtPwd.setBounds(90, 34, 166, 24);
		workPane.add(edtPwd);
		labelPwd2.setBounds(25, 66, 60, 18);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(90, 63, 166, 24);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			try {
				if(t==2)
					UserUtil.userManager.changePwd(BeanUser.currentLoginUser, new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()), new String(edtPwd2.getPassword()));
				else if(t==1)
					ManagerUtil.managerManager.changePwd(BeanManager.currentLoginManager,new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()), new String(edtPwd2.getPassword()));
				dispose();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}

}
