package cn.edu.deliveryAssistant.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
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
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;


public class FrmSelect {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSelect window = new FrmSelect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmSelect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("\u7BA1\u7406\u5458");
		panel_2.add(button, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, "cell 1 0,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton button_1 = new JButton("\u7528\u6237");
		panel_3.add(button_1, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton button_2 = new JButton("\u5546\u5BB6");
		panel_1.add(button_2, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 1 1,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton button_3 = new JButton("\u9A91\u624B");
		panel.add(button_3, BorderLayout.CENTER);
	}
}
