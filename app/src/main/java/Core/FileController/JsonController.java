package Core.FileController;

import Core.DataStore.StorerData.*;
import Core.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonController implements FileController{
//    @Override
//    public void saveItem() {
//
//    }
//
//    @Override
//    public void saveBill() {
//
//    }
    public JsonController(){
    }

    @Override
    public void saveImage(StorerDataImageWithID images) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/image.json").toFile(), images);
    }

    @Override
    public void saveCustomers(StorerDataCustomer customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/customer.json").toFile(), customer);
    }

    @Override
    public void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/premium_customer.json").toFile(), premiumCustomer);
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem item) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/item.json").toFile(), item);
    }

    @Override
    public void saveBill(StorerDataBill bills) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/bill.json").toFile(), bills);
    }

    @Override
    public StorerDataBill loadBill() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(Settings.getInstance().getPath() + "/bill.json")));
        return new ObjectMapper().readValue(json, StorerDataBill.class);
    }

    @Override
    public StorerDataImageWithID loadImage() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(Settings.getInstance().getPath() + "/image.json")));
        return new ObjectMapper().readValue(json, StorerDataImageWithID.class);
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(Settings.getInstance().getPath() + "/item.json")));
        return new ObjectMapper().readValue(json, StorerDataQuantifiableItem.class);
    }

    @Override
    public StorerDataCustomer loadCustomer() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(Settings.getInstance().getPath() + "/customer.json")));
        return new ObjectMapper().readValue(json, StorerDataCustomer.class);
    }

    @Override
    public StorerDataPremiumCustomer loadPremiumCustomer() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(Settings.getInstance().getPath() + "/premium_customer.json")));
        return new ObjectMapper().readValue(json, StorerDataPremiumCustomer.class);
    }

}
