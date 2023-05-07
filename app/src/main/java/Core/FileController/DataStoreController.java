package Core.FileController;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.*;
import Core.Settings;
import lombok.Setter;

import java.io.IOException;

public class DataStoreController implements FileController{
    private FileController fileController;

    public DataStoreController(){
        if (Settings.getInstance().getFileType().get("JSON")){
            fileController = new JsonController();
        }
    }

    @Override
    public String getFileType() {
        return fileController.getFileType();
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem items) throws IOException {
        fileController.saveItem(items);
    }

    @Override
    public void saveImage(StorerDataImageWithID images) throws IOException {
        fileController.saveImage(images);
    }

    @Override
    public void saveBill (StorerDataBill bills) throws IOException {
        fileController.saveBill(bills);
    }

    @Override
    public void saveCustomers(StorerDataCustomer customer) throws IOException{
        fileController.saveCustomers(customer);
    }

    public void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException{
        fileController.savePremiumCustomers(premiumCustomer);
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        return fileController.loadItem();
    }

    @Override
    public StorerDataImageWithID loadImage() throws IOException {
        return fileController.loadImage();
    }

    @Override
    public StorerDataBill loadBill() throws  IOException {
        return fileController.loadBill();
    }

    @Override
    public StorerDataCustomer loadCustomer() throws IOException{
        return fileController.loadCustomer();
    }
    @Override
    public StorerDataPremiumCustomer loadPremiumCustomer() throws IOException{
        return fileController.loadPremiumCustomer();
    }


}
