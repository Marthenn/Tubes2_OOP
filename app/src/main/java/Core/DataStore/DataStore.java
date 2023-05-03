package Core.DataStore;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.DataStore.StorerData.Exception.RemovedItemNotExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.DataStore.StorerData.StorerCustomer;
import Core.DataStore.StorerData.StorerImage;
import Core.DataStore.StorerData.StorerItem;
import Core.DataStore.StorerData.StorerPremiumCustomer;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Item;
import lombok.SneakyThrows;

public class DataStore {
    private static DataStore instance = null;
    private StorerItem items = new StorerItem();
    private StorerCustomer customers = new StorerCustomer();
    private StorerPremiumCustomer premiumCustomers = new StorerPremiumCustomer();
    private StorerImage images = new StorerImage();

    private DataStore() {

    }

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            DataStore.instance = new DataStore();
        }
        return DataStore.instance;
    }

    /**
     * Create new Customer with new ID that is higher than the ID of all PremiumCustomer and Customer
     * @return Customer
     */
    @SneakyThrows
    public Customer createNewCustomer() {
        int newID = Math.max(customers.getHighestID(), premiumCustomers.getHighestID());
        Customer newCustomer = new Customer(newID);
        customers.addItem(newCustomer);
        return newCustomer;
    }

    /**
     * Promote an existing Customer in the DataStore with the given ID to PremiumCustomer with the given name and phoneBumber
     * @param id
     * @param name
     * @param phoneNumber
     * @return The PremiumCustomer that resulted in Customer's promotion
     * @throws CustomerNotExistException No Customer with the given ID exists in the DataStore
     * @throws PromotedCustomerAlreadyExist PremiumCustomer with the given ID already exists
     */
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

    public Item getItemWithID(int id) throws SearchedItemNotExist {
        return items.getItem(id);
    }

    /**
     * Get a Customer with the given ID
     * @param id
     * @return Customer
     * @throws SearchedItemNotExist The Customer with the given ID does not exist
     */
    public Customer getCustomerWithID(int id) throws SearchedItemNotExist {
        try {
            return premiumCustomers.getItem(id);
        } catch (SearchedItemNotExist e) {
            return customers.getItem(id);
        }
    }

    /**
     * Get an ImageWithID with the given ID
     * @param id
     * @return ImageWithID
     * @throws SearchedItemNotExist The ImageWithID with the given ID does not exist
     */
    public ImageWithID getImageWithID(int id) throws SearchedItemNotExist {
        return images.getItem(id);
    }



}
