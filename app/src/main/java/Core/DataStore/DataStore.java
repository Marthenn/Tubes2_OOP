package Core.DataStore;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.DataStore.StorerData.Exception.RemovedItemNotExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class DataStore {
    private static DataStore instance = null;
    private StorerData<QuantifiableItem> items = new StorerData<>("Item");
    private StorerData<Customer> customers = new StorerData<>("Customer");
    private StorerData<PremiumCustomer> premiumCustomers = new StorerData<>("Premium Customer");
    private StorerData<ImageWithID> images = new StorerData<>("ImageWithID");
    private StorerData<Bill> bills = new StorerData<>("Bill");

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

    /**
     * Get a Bill with the given ID
     * @param id
     * @return Bill
     * @throws SearchedItemNotExist The Bill with the given ID does not exist
     */
    public Bill getBillWithID(int id) throws SearchedItemNotExist {
        return bills.getItem(id);
    }

    /**
     * Get a Bill with the given ID
     * @param bill
     * @return Bill
     * @throws SearchedItemNotExist The Bill with the given ID does not exist
     */
    public Bill getBillWithID(Bill bill) throws SearchedItemNotExist {
        return bills.getItem(bill);
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
     * Remove bill from the DataStore. Even if the bill is different, it will only be compared by its ID.
     * @param bill
     * @throws RemovedItemNotExist
     */
    public void removeBillWithID(Bill bill) throws RemovedItemNotExist {
        bills.removeItem(bill);
    }

    /**
     *
     * @return New Bill with the latest ID
     */
    public Bill createNewBill() {
        Bill newBill = new Bill(bills.getNewID());
        try {
            bills.addItem(newBill, true);
        } catch (Exception ignored) {

        }
        return newBill;
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

    public QuantifiableItem addNewItem(String name, Double price, Double originalPrice, String category, Integer quantity, String img) throws ItemWithIDAlreadyExist, NegativeQuantityException {
        QuantifiableItem newQItem = new QuantifiableItem(new Item(items.getNewID(), name, price, originalPrice, category, img, false), quantity);
        items.addItem(newQItem);
        return newQItem;
    }





}
