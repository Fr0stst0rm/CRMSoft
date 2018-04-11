package MyFirstPlugin;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import PluginInterface.client.PluginInterface;

public class Plugin implements PluginInterface {
	
	String name = "MyFirstPlugin";

	PluginXMLHandler xmlHandler = new PluginXMLHandler();
	
	PluginView view = new PluginView();

	String currentXML = "";
	HashMap<String, String> data = new HashMap<>();
	
	@Override
	public void loadXML(String xmlFileName) {
		currentXML = xmlFileName;
		try {
			data = (HashMap<String, String>) xmlHandler.loadXML(getXMLPath() + currentXML);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveToXML() {
		try {
			xmlHandler.createXML(data, getXMLPath() + currentXML);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getXMLPath() {
		return "/xml/" + getName() + "/";
	}

	@Override
	public void handleData() {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel getView() {
		return (JPanel) view;
	}

	@Override
	public String getName() {
		return name;
	}

}
