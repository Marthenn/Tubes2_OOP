package Core.FileController;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.*;

import java.io.IOException;

public interface FileController{

    void saveItem(StorerDataQuantifiableItem items) throws IOException;


    void saveBill(StorerDataBill bills) throws IOException;

    void saveImage(StorerDataImageWithID images) throws IOException;

    void saveCustomers(StorerDataCustomer customer) throws IOException;

    void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException;

    StorerDataImageWithID loadImage() throws IOException;
    StorerDataQuantifiableItem loadItem() throws IOException;
    StorerDataBill loadBill() throws IOException;
    StorerDataCustomer loadCustomer() throws IOException;
    StorerDataPremiumCustomer loadPremiumCustomer() throws IOException;



//
//    StorerData<ImageWithID> loadImage() throws IOException;
//
//    StorerData<QuantifiableItem> loadItem() throws IOException;
}
