package MyFirstPlugin;

import CRMSoft.host.BusinessLayerInterface;
import CRMSoft.host.PluginSaveData;

import java.util.*;

public class BusinessLayer implements BusinessLayerInterface {

    private static final BusinessLayer BusinessLayerInstance = new BusinessLayer();
    private DataAccessLayer dataAccessLayer = new DataAccessLayer();

    private BusinessLayer(){}

    public static BusinessLayer  getBusinessLayerImplInstance(){
        return BusinessLayerInstance;
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
    public void saveData(String customerName, PluginSaveData data) {
        dataAccessLayer.saveCustomerData(customerName, data);
    }
}
