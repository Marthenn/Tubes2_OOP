package Core.FileController;

import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
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
        String json = new ObjectMapper().writeValueAsString(images);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/image.json").toFile(), json);
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem item) throws IOException {
        String json = new ObjectMapper().writeValueAsString(item);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/item.json").toFile(), json);
    }

    @Override
    public void saveBill(StorerDataBill bills) throws IOException {
        String json = new ObjectMapper().writeValueAsString(bills);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(Settings.getInstance().getPath());
        mapper.writeValue(Paths.get(Settings.getInstance().getPath()+"/bill.json").toFile(), json);
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
}
