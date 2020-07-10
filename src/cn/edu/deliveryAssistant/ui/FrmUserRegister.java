package cn.edu.deliveryAssistant.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import cn.edu.deliveryAssistant.UserUtil;
import cn.edu.deliveryAssistant.model.BeanUser;
import cn.edu.deliveryAssistant.util.BaseException;
import javax.swing.JRadioButton;

public class FrmUserRegister extends JFrame implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户名：");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelPwd2 = new JLabel("密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private ButtonGroup sex=new ButtonGroup();
	private final JLabel label = new JLabel("性别：");
	private final JRadioButton radioMan = new JRadioButton("男");
	private final JRadioButton radioWoman = new JRadioButton("女");
	private JTextField edtPhone= new JTextField(20);
	private JTextField edtEmail= new JTextField(20);
	private JTextField edtCity= new JTextField(20);
	JLabel labelPhone = new JLabel("手机号：");
	JLabel labelEmail = new JLabel("邮箱：");
	JLabel labelCity = new JLabel("所在城市：");
	public FrmUserRegister() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		sex.add(radioMan);
		sex.add(radioWoman);
		workPane.setLayout(null);
		labelUser.setBounds(25, 8, 60, 18);
		workPane.add(labelUser);
		edtUserId.setBounds(90, 5, 166, 24);
		workPane.add(edtUserId);
		label.setBounds(40, 39, 45, 18);
		
		workPane.add(label);
		radioMan.setBounds(95, 34, 43, 27);
		
		workPane.add(radioMan);
		radioWoman.setBounds(143, 34, 43, 27);
		
		workPane.add(radioWoman);
		labelPwd.setBounds(40, 70, 45, 18);
		workPane.add(labelPwd);
		edtPwd.setBounds(90, 61, 166, 24);
		workPane.add(edtPwd);
		labelPwd2.setBounds(40, 101, 45, 18);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(90, 98, 166, 24);
		workPane.add(edtPwd2);
		labelPhone.setBounds(14, 137, 72, 18);
		workPane.add(labelPhone);

		edtPhone.setBounds(90, 135, 166, 24);
		workPane.add(edtPhone);
		
		labelEmail.setBounds(14, 181, 72, 18);
		workPane.add(labelEmail);
		
		edtEmail.setBounds(90, 178, 166, 24);
		workPane.add(edtEmail);
		
		labelCity.setBounds(14, 229, 72, 18);
		workPane.add(labelCity);
		
		edtCity.setBounds(90, 226, 166, 24);
		workPane.add(edtCity);
		
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		

		this.setSize(300, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String user_name=this.edtUserId.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			String s="男";
			Enumeration<AbstractButton> radioBtns=sex.getElements();
			while (radioBtns.hasMoreElements()) {  
			    AbstractButton btn = radioBtns.nextElement();  
			    if(btn.isSelected()){  
			        s=btn.getText();  
			        break;  
			    }  
			}
			String phone=this.edtPhone.getText();
			String email=this.edtEmail.getText();
			String city=this.edtCity.getText();
			try {
				BeanUser user=UserUtil.userManager.reg(user_name, s, pwd1, pwd2, phone, email, city);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}