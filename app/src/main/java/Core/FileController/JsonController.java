package Core.FileController;

import Core.DataStore.StorerData.*;
import Core.Settings.SaveFileType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonController extends ConcreteFileController {
    public JsonController(){
    }

    @Override
    public void saveImage(StorerDataImageWithID images) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getFile("images"), images);
    }

    @Override
    public void saveCustomers(StorerDataCustomer customer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getFile("customers"), customer);
    }

    @Override
    public void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getFile("premiumCustomers"), premiumCustomer);
    }

    @Override
    public String getFileType() {
        return SaveFileType.JSON.getName();
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem item) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getFile("items"), item);
    }

    @Override
    public void saveBill(StorerDataBill bills) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(getFile("bills"), bills);
    }

    @Override
    public StorerDataBill loadBill() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(getFilePath("bills"))));
        return new ObjectMapper().readValue(json, StorerDataBill.class);
    }

    @Override
    public StorerDataImageWithID loadImage() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(getFilePath("images"))));
        return new ObjectMapper().readValue(json, StorerDataImageWithID.class);
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(getFilePath("items"))));
        return new ObjectMapper().readValue(json, StorerDataQuantifiableItem.class);
    }

    @Override
    public StorerDataCustomer loadCustomer() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(getFilePath("customers"))));
        return new ObjectMapper().readValue(json, StorerDataCustomer.class);
    }

    @Override
    public StorerDataPremiumCustomer loadPremiumCustomer() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(getFilePath("premiumCustomers"))));
        return new ObjectMapper().readValue(json, StorerDataPremiumCustomer.class);
    }

}
