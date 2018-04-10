package CRMSoft.host;

import java.util.Collection;

public interface BusinessLayerInterface {

	Collection<PluginSaveData> getAvailablePluginsForCustomer(String customerName);

	PluginSaveData getData(String customerName, String plugin);

	void saveData(String customerName, PluginSaveData data);

}
