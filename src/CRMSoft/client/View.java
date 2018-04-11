package CRMSoft.client;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import PluginInterface.client.PuginInterface;

@SuppressWarnings("serial")
public class View extends JFrame {

	public JTextField nameTextField = new JTextField(17);
	public JButton saveBtn = new JButton("Save");
	
	public JLabel status = new JLabel(""); 
	
	private JTabbedPane tabbedPane = new JTabbedPane();
			
	public View() {
		this.setTitle("CRMSoft");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel nameSearchPnl = new JPanel();
		nameSearchPnl.setLayout(new BorderLayout());
		
		nameSearchPnl.add(new Label("Name:"), BorderLayout.WEST);
		nameSearchPnl.add(nameTextField, BorderLayout.CENTER);
		
		this.add(nameSearchPnl, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel statusPnl = new JPanel();
		statusPnl.setLayout(new BorderLayout());
		statusPnl.add(status, BorderLayout.CENTER);
		statusPnl.add(saveBtn, BorderLayout.EAST);
		
		this.add(statusPnl);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void addPlugin(PuginInterface plugin) {
		JScrollPane scrp = new JScrollPane(plugin.getView());
		tabbedPane.addTab(plugin.getName(), scrp);
		this.pack();
		this.setLocationRelativeTo(null);
		this.validate();
		this.repaint();
	}

}
