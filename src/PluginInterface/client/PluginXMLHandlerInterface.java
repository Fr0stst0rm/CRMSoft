package PluginInterface.client;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Map;

public interface PluginXMLHandlerInterface {
	
	Map<String,String> loadXML(String xmlFileName) throws ParserConfigurationException, IOException, SAXException;
	
	void createXML(Map<String,String> Values, String xmlFileName) throws TransformerException, ParserConfigurationException;

}
