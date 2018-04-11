package CRMSoft.host;

import java.util.Collection;

public interface DataAccessLayerInterface {

	void openConnection();

	void closeConnection();

	Collection<String> getCustomers();

	void addNewCustomer(String customer);

	void saveAllCustomerData(String customer, Collection<PluginSaveData> data);

	void saveCustomerData(String customer, PluginSaveData data);

	Collection<PluginSaveData> getAllCustomerData(String customer);

	PluginSaveData getCustomerData(String customer, String plugin);

}
