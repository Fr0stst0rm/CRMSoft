package CRMSoft.host;

import java.util.Collection;

import CRMSoft.client.PluginManager;

public class BusinessLayer implements BusinessLayerInterface {

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
		PluginSaveData data = dataAccessLayer.getCustomerData(customerName, plugin);
		if (data != null)
			PluginManager.loadedPlugins.get(plugin).loadXML(data.xmlPath);
		return data;
	}

	@Override
	public void saveData(String customerName, String plugin) {
		long hash = customerName.hashCode();
		String xmlName = plugin + "_" + hash + ".xml";

		PluginManager.loadedPlugins.get(plugin).saveToXML(xmlName);

		PluginSaveData save = new PluginSaveData(plugin, xmlName);
		dataAccessLayer.saveCustomerData(customerName, save);
	}

}
