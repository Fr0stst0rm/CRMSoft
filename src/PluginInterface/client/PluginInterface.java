package PluginInterface.client;

import java.util.Map;

import javax.swing.JPanel;

public interface PluginInterface {

	void loadXML(String xmlFileName);
	void saveToXML(String xmlFileName);
	
	String getXMLPath();
	
	void handleData();
	
	Map<String, String> getData();
	
	void setData(Map<String, String> data);
	
	JPanel getView();
	
	String getName();
	
	// Plugin Logik
}
