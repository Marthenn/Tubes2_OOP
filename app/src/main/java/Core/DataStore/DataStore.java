package Core.DataStore;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
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
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import lombok.SneakyThrows;

import java.util.ArrayList;

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
        int newID = Math.max(customers.getHighestID(), premiumCustomers.getHighestID()) + 1;
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
    public PremiumCustomer promoteCustomer(int id, String name, String phoneNumber, String email, MembershipStateName stateName) throws CustomerNotExistException, PromotedCustomerAlreadyExist {
        Customer promotedCustomer;
        try {
            promotedCustomer = customers.removeItem(id);
        } catch (RemovedItemNotExist e) {
            throw new CustomerNotExistException();
        }

        assert(promotedCustomer != null);

        PremiumCustomer newPremiumCustomer = new PremiumCustomer(promotedCustomer, name, phoneNumber, email, stateName);

        try {
            premiumCustomers.addItem(newPremiumCustomer);
        } catch (ItemWithIDAlreadyExist e) {
            throw new PromotedCustomerAlreadyExist();
        }
        return newPremiumCustomer;

    }

    public PremiumCustomer promoteCustomer(int id, String name, String phoneNumber, String email) throws CustomerNotExistException, PromotedCustomerAlreadyExist {
        return promoteCustomer(id, name, phoneNumber, email, MembershipStateName.MEMBER);
    }



    public Item getItemWithID(int id) throws SearchedItemNotExist {
        return items.getItem(id).getItem();
    }

    /**
     * Get a Customer with the given ID
     * @param id
     * @return Customer
     * @throws SearchedItemNotExist The Customer with the given ID does not exist
     */
    public Customer getCustomerWithID(int id) throws SearchedItemNotExist {
        return customers.getItem(id);
    }

    /**
     * Get a PremiumCustomer with the given ID
     * @param id
     * @return PremiumCustomer
     * @throws SearchedItemNotExist The Customer with the given ID does not exist
     */
    public PremiumCustomer getPremiumCustomerWithID(int id) throws SearchedItemNotExist {
        return premiumCustomers.getItem(id);
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

    public ArrayList<QuantifiableItem> getItems() {
        return items.getItemList();
    }

    public ArrayList<Customer> getCustomers() {
        return customers.getItemList();
    }

    public ArrayList<PremiumCustomer> getPremiumCustomers() {
        return premiumCustomers.getItemList();
    }

    public QuantifiableItem addNewItem(String name, Double price, Double originalPrice, String category, Integer quantity) throws ItemWithIDAlreadyExist, NegativeQuantityException {
        QuantifiableItem newQItem = new QuantifiableItem(new Item(items.getNewID(), name, price, originalPrice, category, new ImageWithID(), false), quantity);
        items.addItem(newQItem);
        return newQItem;
    }





}
