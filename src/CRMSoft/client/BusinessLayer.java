package CRMSoft.client;

import java.util.Collection;

import CRMSoft.host.BusinessLayerInterface;
import CRMSoft.host.DataAccessLayerInterface;
import CRMSoft.host.PluginSaveData;

public class BusinessLayer implements BusinessLayerInterface{
	
	DataAccessLayerInterface dal;

	@Override
	public Collection<PluginSaveData> getAvailablePluginsForCustomer(String customerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginSaveData getData(String customerName, String plugin) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void saveData(String customerName, String plugin) {
		String xmlName = (customerName+plugin).hashCode() + ".xml";
		PluginManager.loadedPlugins.get(customerName).saveToXML(xmlName);
		
		PluginSaveData save = new PluginSaveData(plugin,xmlName);
		dal.saveCustomerData(customerName, save);
	}

}
