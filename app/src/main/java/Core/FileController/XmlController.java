package Core.FileController;

import Core.DataStore.StorerData.*;
import Core.Settings.SaveFileType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class XmlController extends ConcreteFileController {
    public XmlController(){
    }

    @Override
    public void saveImage(StorerDataImageWithID images) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(getFile("images"), images);
    }

    @Override
    public void saveCustomers(StorerDataCustomer customer) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(getFile("customers"), customer);
    }

    @Override
    public void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(getFile("premiumCustomers"), premiumCustomer);
    }

    @Override
    public String getFileType() {
        return SaveFileType.XML.getName();
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem item) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(getFile("items"), item);
    }

    @Override
    public void saveBill(StorerDataBill bills) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(getFile("bills"), bills);
    }

    @Override
    public StorerDataBill loadBill() throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(getFilePath("bills"))));
        return new XmlMapper().readValue(xml, StorerDataBill.class);
    }

    @Override
    public StorerDataImageWithID loadImage() throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(getFilePath("images"))));
        return new XmlMapper().readValue(xml, StorerDataImageWithID.class);
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(getFilePath("items"))));
        return new XmlMapper().readValue(xml, StorerDataQuantifiableItem.class);
    }

    @Override
    public StorerDataCustomer loadCustomer() throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(getFilePath("customers"))));
        return new XmlMapper().readValue(xml, StorerDataCustomer.class);
    }

    @Override
    public StorerDataPremiumCustomer loadPremiumCustomer() throws IOException {
        String xml = new String(Files.readAllBytes(Paths.get(getFilePath("premiumCustomers"))));
        return new XmlMapper().readValue(xml, StorerDataPremiumCustomer.class);
    }

}
