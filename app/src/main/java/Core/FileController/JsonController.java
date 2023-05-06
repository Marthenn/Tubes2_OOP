package Core.FileController;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import Core.Settings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.xml.crypto.Data;
import java.io.IOException;
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
    public void saveImage(StorerData<ImageWithID> images) throws IOException {
        String json = new ObjectMapper().writeValueAsString(images);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(StorerData<ImageWithID>)

        mapper.writeValue(Paths.get(DataStore.getInstance().getPath()+"/image.json").toFile(), json);
    }

    @Override
    public void saveItem(StorerData<QuantifiableItem> item) throws IOException {
        String json = new ObjectMapper().writeValueAsString(item);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(DataStore.getInstance().getPath()+"/item.json").toFile(), json);
    }

    @Override
    public void saveBill(StorerData<Bill> bills) throws IOException {
        String json = new ObjectMapper().writeValueAsString(bills);
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(Paths.get(DataStore.getInstance().getPath()+"/bill.json").toFile(), json);
    }
}
