package Core.DataStore;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.DataStore.StorerData.Exception.RemovedItemNotExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.DataStore.StorerData.*;
import Core.FileController.DataStoreController;
import Core.FileController.FileController;
import Core.IDAble.IDAbleListener;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class DataStore {
    private static DataStore instance = null;
    private StorerDataQuantifiableItem items = new StorerDataQuantifiableItem();
    private StorerDataCustomer customers = new StorerDataCustomer();
    private StorerDataPremiumCustomer premiumCustomers = new StorerDataPremiumCustomer();
    private StorerDataImageWithID images = new StorerDataImageWithID();
    private StorerDataBill bills = new StorerDataBill();

    private transient ArrayList<IDAbleListener<QuantifiableItem>> itemListeners = new ArrayList<>();

    private transient ArrayList<IDAbleListener<Customer>> customerListeners = new ArrayList<>();

    private transient ArrayList<StorerDataListener> itemStoreListeners = new ArrayList<>();

    private transient ArrayList<StorerDataListener> customerStoreListeners = new ArrayList<>();



    private DataStore() {

        items.setListenerList(itemStoreListeners);
        customers.setListenerList(customerStoreListeners);
        premiumCustomers.setListenerList(customerStoreListeners);
    }

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            DataStore.instance = new DataStore();
        }
        return DataStore.instance;
    }

    public int load() {
        FileController controller = new DataStoreController();

        try {
            this.images = controller.loadImage();
        } catch (IOException ignored) {
            return 1;
        }

        try {
            this.items = controller.loadItem();
        } catch (IOException ignored) {
            return 2;
        }

        try {
            this.customers = controller.loadCustomer();
        } catch (IOException ignored) {
            System.out.println("c error");
            return 3;
        }

        try {
            this.premiumCustomers = controller.loadPremiumCustomer();
        } catch (IOException ignored) {
            System.out.println("pc error");
            return 4;
        }
        return 0;
    }

    /**
     * Create new Customer with new ID that is higher than the ID of all PremiumCustomer and Customer
     * @return Customer
     */
    @SneakyThrows
    public Customer createNewCustomer() {
        int newID = Math.max(customers.getHighestID(), premiumCustomers.getHighestID()) + 1;
        Customer newCustomer = new Customer(newID);
        newCustomer.setListenerList(customerListeners);
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

        try {
            saveBill();
        } catch (IOException e) {
            System.out.println(e);
        }
        return newBill;
    }

    public ImageWithID createNewImageWithID(String base64Image) {
        ImageWithID image = new ImageWithID(images.getNewID(), base64Image);
        try {
            images.addItem(image, true);
        } catch (ItemWithIDAlreadyExist ignored){

        }
        return image;
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
        ImageWithID image = createNewImageWithID(img);
        QuantifiableItem newQItem = new QuantifiableItem(new Item(items.getNewID(), name, price, originalPrice, category, image.getID(), false), quantity);
        items.addItem(newQItem);
        newQItem.setListenerList(itemListeners);
        return newQItem;
    }

    /**
     * Add listener list of listener of changes to the individual Customer and PremiumCustomer stored in here
     * @param listener
     */
    public void listenToCustomer(IDAbleListener<Customer> listener) {
        customerListeners.add(listener);
    }

    /**
     * Add listener list of listener of changes to the individual QuantifiableItem (i.e. item sold in the store) stored in here
     * @param listener
     */
    public void listenToItem(IDAbleListener<QuantifiableItem> listener) {
        itemListeners.add(listener);
    }

    /**
     * Add listener list of listener of changes to the addition/removal of Customer / PremiumCustomer stored in here
     * @param listener
     */
    public void listenToCustomerStore(StorerDataListener listener) {
        customerStoreListeners.add(listener);
    }


    /**
     * Add listener list of listener of changes to the addition/removal of QuantifiableItem (i.e. item sold in the store) stored in here
     * @param listener
     */
    public void listenToItemStore(StorerDataListener listener){
        itemStoreListeners.add(listener);
    }

    public void saveImage() throws IOException {
        FileController controller = new DataStoreController();
        controller.saveImage(this.images);
    }
    public void saveItem() throws IOException {
        FileController controller = new DataStoreController();
        for (QuantifiableItem qItem : items.getItemList()) {
            System.out.println(qItem);
        }
        controller.saveItem(this.items);
    }

    public void saveBill() throws IOException {
        FileController controller = new DataStoreController();
        controller.saveBill(this.bills);
    }

    public void saveCustomers() throws IOException {
        FileController controller = new DataStoreController();
        controller.saveCustomers(this.customers);
    }

    public void savePremiumCustomers() throws IOException {
        FileController controller = new DataStoreController();
        controller.savePremiumCustomers(this.premiumCustomers);
    }
}
