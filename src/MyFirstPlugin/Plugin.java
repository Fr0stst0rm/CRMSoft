package MyFirstPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import PluginInterface.client.PluginInterface;
import PluginInterface.client.PluginXMLHandler;

public class Plugin implements PluginInterface {
	
	private String name = "MyFirstPlugin";

	private PluginXMLHandler xmlHandler = new PluginXMLHandler();

	private PluginView view = new PluginView();

	private File currentXML = new File("");

	@Override
	public void loadXML(String xmlFileName) {
		currentXML = new File(getXMLPath() + xmlFileName);
		
		if(currentXML.exists()) {
		
		System.out.println("Loading data from "+ currentXML.getPath() );
		
		try {
			
			setData(xmlHandler.loadXML(currentXML.getPath()));
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
		} else {
			System.err.println(currentXML.getName()+ " does not exist!");
		}
	}

	@Override
	public void saveToXML(String xmlFileName) {
		currentXML = new File(getXMLPath() + xmlFileName);
		
		if(currentXML.exists()) {
			currentXML.delete();
		}
		
		try {
			currentXML.getParentFile().mkdirs();
			currentXML.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Saving data to "+ currentXML.getPath());
				
		try {
			xmlHandler.createXML(getData(), currentXML.getPath());
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
		return "xml/" + getName() + "/";
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

	@Override
	public Map<String, String> getData() {
		return view.getPluginData();
	}

	@Override
	public void setData(Map<String, String> data) {
		view.setPluginData(data);		
	}

}
