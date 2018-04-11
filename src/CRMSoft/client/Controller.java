package CRMSoft.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import CRMSoft.host.BusinessLayer;
import CRMSoft.host.BusinessLayerInterface;
import CRMSoft.host.PluginSaveData;
import PluginInterface.client.PluginInterface;

public class Controller implements ActionListener {

	View v;
	BusinessLayerInterface businessLayer;

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

		businessLayer = BusinessLayer.getInstance();
		
		v = new View();

		for (PluginInterface plugin : PluginManager.loadPlugins().values()) {
			v.addPlugin(plugin);
		}
		
		v.loadBtn.addActionListener(this);
		v.saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(v.loadBtn.getActionCommand())) {
			System.out.println("Loading " + v.getActivePluginName() + " data for " + v.nameTextField.getText());
			businessLayer.getData(v.nameTextField.getText(), v.getActivePluginName());
		} else if (e.getActionCommand().equals(v.saveBtn.getActionCommand())) {
			System.out.println("Saving " + v.getActivePluginName() + " data for " + v.nameTextField.getText());
			businessLayer.saveData(v.nameTextField.getText(), v.getActivePluginName());
		}
	}

}
