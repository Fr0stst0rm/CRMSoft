package CRMSoft.client;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import PluginInterface.client.PluginXMLHandlerInterface;
import org.xml.sax.SAXException;

public class PluginXMLHandler implements PluginXMLHandlerInterface{

	@Override
	public Map<String, String> loadXML(String path) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(System.getProperty("user.dir")+"/XML/"+path);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile.getAbsolutePath());

        Element parentNode = (Element) doc.getElementsByTagName("data").item(0);
        Map<String, String> Values = new HashMap<>();

        NodeList childNodes = parentNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            Values.put(node.getNodeName(), node.getChildNodes().item(0).getNodeValue());
        }

        return Values;
	}

	@Override
	public void createXML(Map<String, String> Values, String fileName) throws TransformerException, ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("data");
		doc.appendChild(rootElement);

        Iterator it = Values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            Element entry = doc.createElement((String) pair.getKey());
            entry.appendChild(doc.createTextNode((String)pair.getValue()));
            rootElement.appendChild(entry);
            it.remove(); // avoids a ConcurrentModificationException
        }

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(System.getProperty("user.dir")+"\\XML\\"+fileName+".xml"));

		// Output to console for testing
        //StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
		
	}

}
