package CRMSoft.client;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame {

	public JTextField nameTextField = new JTextField(17);
			
	public View() {
		this.setTitle("CRMSoft");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel nameSearchPnl = new JPanel();
		nameSearchPnl.setLayout(new BorderLayout());
		
		nameSearchPnl.add(new Label("Name:"), BorderLayout.WEST);
		nameSearchPnl.add(nameTextField, BorderLayout.CENTER);
		
		this.add(nameSearchPnl, BorderLayout.NORTH);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
