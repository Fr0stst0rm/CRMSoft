package CRMSoft.host;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataAccessLayer implements DataAccessLayerInterface {

    private Map<String, Collection<PluginSaveData>> customerPlugins = new HashMap<>();

    @Override
    public Collection<String> getCustomers() {
        return customerPlugins.keySet();
    }

    @Override
    public void addNewCustomer(String customer) {
        if(customerPlugins.get(customer) == null){
            customerPlugins.put(customer, new ArrayList<>());
        }else{
            System.err.println("Customer already in the System");
        }
    }

    @Override
    public void saveAllCustomerData(String customer, Collection<PluginSaveData> data) {
        customerPlugins.put(customer, data);
    }

    @Override
    public void saveCustomerData(String customer, PluginSaveData data) {
        Collection<PluginSaveData> pluginSaveData = customerPlugins.get(customer);
        pluginSaveData.add(data);
        customerPlugins.put(customer, pluginSaveData);
    }

    @Override
    public Collection<PluginSaveData> getAllCustomerData(String customer) {
        Collection<PluginSaveData> plugins = customerPlugins.get(customer);
        if(plugins != null){
            return customerPlugins.get(customer);
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public PluginSaveData getCustomerData(String customer, String plugin) {
        ArrayList<PluginSaveData> plugins = (ArrayList<PluginSaveData>) customerPlugins.get(customer);
        for (PluginSaveData plugin1 : plugins) {
            if (plugin1.pluginName.equals(plugin)) {
                return plugin1;
            }
        }
        return null;
    }

    @Override
    public void openConnection() {
        //todo: whatever
    }

    @Override
    public void closeConnection() {
        //todo: whatever
    }

}
