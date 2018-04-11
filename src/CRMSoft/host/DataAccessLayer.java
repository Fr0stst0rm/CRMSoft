package CRMSoft.host;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataAccessLayer implements DataAccessLayerInterface {

	File databaseFile = new File("crmsoft.db");
	
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
        
        if(!databaseFile.exists()) {
        	try {
				databaseFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(databaseFile));
			oos.writeObject(customerPlugins);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    @Override
    public void saveCustomerData(String customer, PluginSaveData data) {
    	if(!customerPlugins.containsKey(customer)) {
    		addNewCustomer(customer);
    	}
        Collection<PluginSaveData> saveData = customerPlugins.get(customer);
        saveData.add(data);
        saveAllCustomerData(customer, saveData);
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

    @SuppressWarnings("unchecked")
	@Override
    public void openConnection() {
    	 if(databaseFile.exists()) {
    		 try {
    				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(databaseFile));
    				customerPlugins = (Map<String, Collection<PluginSaveData>>) ois.readObject();
    				ois.close();
    			} catch (FileNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException | ClassNotFoundException e) {
    				databaseFile.delete();
				}
    	 }
    }

    @Override
    public void closeConnection() {
        //todo: whatever
    }

}
