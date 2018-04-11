package PluginInterface.client;

import javax.swing.JPanel;

public interface PluginInterface {

	void loadXML(String path);
	void saveToXML();
	
	String getXMLPath();
	
	void handleData();
	
	JPanel getView();
	
	String getName();
	
	// Plugin Logik
}
