package PluginInterface.client;

public interface PuginInterface {

	void loadXML(String path);
	void saveToXML();
	
	String getXMLPath();
	
	void handleData();
	
	// Plugin Logik
}
