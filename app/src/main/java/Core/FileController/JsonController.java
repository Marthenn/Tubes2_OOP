package Core.FileController;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import Core.Settings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        mapper.writeValue(Paths.get(DataStore.getInstance().getPath()+"/image.json").toFile(), json);
    }

    public void saveItem(StorerData<QuantifiableItem> item) throws IOException {
        String json = new ObjectMapper().writeValueAsString(item);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get(DataStore.getInstance().getPath()+"/item.json").toFile(), json);
    }
}
