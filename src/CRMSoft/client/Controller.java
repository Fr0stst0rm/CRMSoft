package CRMSoft.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import PluginInterface.client.PluginInterface;

public class Controller implements ActionListener {

	View v;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		new Controller();
	}

	public Controller() {

		v = new View();

		for (PluginInterface plugin : PluginManager.loadPlugins()) {
			v.addPlugin(plugin);
		}

		v.nameTextField.addActionListener(this);
		v.loadBtn.addActionListener(this);
		v.saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}

}
