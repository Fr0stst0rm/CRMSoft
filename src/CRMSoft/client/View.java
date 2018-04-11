package CRMSoft.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import PluginInterface.client.PluginInterface;

@SuppressWarnings("serial")
public class View extends JFrame implements DocumentListener {

	public JTextField nameTextField = new JTextField(17);
	public JButton loadBtn = new JButton("Load");
	public JButton saveBtn = new JButton("Save");

	public JLabel status = new JLabel("");

	private JTabbedPane tabbedPane = new JTabbedPane();

	public View() {
		this.setTitle("CRMSoft");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel nameSearchPnl = new JPanel();
		nameSearchPnl.setLayout(new BorderLayout());

		nameTextField.getDocument().addDocumentListener(this);

		nameSearchPnl.add(new Label(" Name:"), BorderLayout.WEST);
		nameSearchPnl.add(nameTextField, BorderLayout.CENTER);
		nameSearchPnl.add(loadBtn, BorderLayout.EAST);

		this.add(nameSearchPnl, BorderLayout.NORTH);

		this.add(tabbedPane, BorderLayout.CENTER);

		saveBtn.setEnabled(false);

		JPanel statusPnl = new JPanel();
		statusPnl.setLayout(new BorderLayout());
		statusPnl.add(status, BorderLayout.CENTER);
		statusPnl.add(saveBtn, BorderLayout.EAST);

		this.add(statusPnl, BorderLayout.SOUTH);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void addPlugin(PluginInterface plugin) {
		JScrollPane scrp = new JScrollPane(plugin.getView());
		tabbedPane.addTab(plugin.getName(), scrp);
		this.pack();
		this.setLocationRelativeTo(null);
		this.validate();
		this.repaint();
	}

	public String getActivePluginName() {
		return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
	}

	private void checkNameTextField() {
		if (nameTextField.getText().trim().isEmpty()) {
			saveBtn.setEnabled(false);
		} else if (!saveBtn.isEnabled()) {
			saveBtn.setEnabled(true);
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkNameTextField();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkNameTextField();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkNameTextField();
	}

}
