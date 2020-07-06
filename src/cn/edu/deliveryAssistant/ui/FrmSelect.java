package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmSelect extends JFrame implements ActionListener{

	private JFrame frame;
	private JButton manager;
	private JButton user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FrmSelect window = new FrmSelect();
	}

	/**
	 * Create the application.
	 */
	public FrmSelect() {
		setBounds(100, 100, 450, 312);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocation(500,300);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		manager = new JButton("管理员");
		manager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new FrmManagerLogin();
			}
		});
		getContentPane().add(manager);
		
		user = new JButton("用户");
		user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new FrmUserLogin();
			}
		});
		getContentPane().add(user);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
