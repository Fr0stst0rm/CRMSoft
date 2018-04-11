package CRMSoft.host;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PluginSaveData implements Serializable{
	
	public String pluginName;
	public String xmlPath;
	
	public PluginSaveData(String plugin, String xmlName) {
		pluginName = plugin;
		xmlPath = xmlName;
	}

}
