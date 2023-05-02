package Core.DataStore;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.DataStore.StorerData.Exception.RemovedItemNotExist;
import Core.DataStore.StorerData.StorerCustomer;
import Core.DataStore.StorerData.StorerPremiumCustomer;
import lombok.SneakyThrows;

public class DataStore {
    private static DataStore instance = null;
    private StorerCustomer customers = new StorerCustomer();
    private StorerPremiumCustomer premiumCustomers = new StorerPremiumCustomer();

    private DataStore() {

    }

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            DataStore.instance = new DataStore();
        }
        return DataStore.instance;
    }

    @SneakyThrows
    public Customer createNewCustomer() {
        int newID = Math.max(customers.getHighestID(), premiumCustomers.getHighestID());
        Customer newCustomer = new Customer(newID);
        customers.addItem(newCustomer);
        return newCustomer;
    }

    public PremiumCustomer promoteCustomer(int id, String name, String phoneNumber) throws CustomerNotExistException, PromotedCustomerAlreadyExist {
        Customer promotedCustomer;
        try {
            promotedCustomer = customers.removeItem(id);
        } catch (RemovedItemNotExist e) {
            throw new CustomerNotExistException();
        }

        assert(promotedCustomer != null);

        PremiumCustomer newPremiumCustomer = new PremiumCustomer(promotedCustomer, name, phoneNumber);

        try {
            premiumCustomers.addItem(newPremiumCustomer);
        } catch (ItemWithIDAlreadyExist e) {
            throw new PromotedCustomerAlreadyExist();
        }
        return newPremiumCustomer;

    }



}
