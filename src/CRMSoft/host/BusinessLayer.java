package CRMSoft.host;

import java.util.Collection;

import CRMSoft.client.PluginManager;

public class BusinessLayer implements BusinessLayerInterface{
	
	DataAccessLayerInterface dataAccessLayer = new DataAccessLayer();
	
	public static BusinessLayer instance = new BusinessLayer();
	
	private BusinessLayer() {
		dataAccessLayer.openConnection();
	}
	
	public static BusinessLayer getInstance() {
		return instance;
	}

    @Override
    public Collection<PluginSaveData> getAvailablePluginsForCustomer(String customerName) {
        return dataAccessLayer.getAllCustomerData(customerName);
    }

    @Override
    public PluginSaveData getData(String customerName, String plugin) {
        return dataAccessLayer.getCustomerData(customerName, plugin);
    }


	@Override
	public void saveData(String customerName, String plugin) {
		String xmlName = (customerName+plugin).hashCode() + ".xml";
		PluginManager.loadedPlugins.get(customerName).saveToXML(xmlName);
		
		PluginSaveData save = new PluginSaveData(plugin,xmlName);
		dataAccessLayer.saveCustomerData(customerName, save);
	}

}
