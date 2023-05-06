package Core.FileController;

import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import Core.Settings;

import java.io.IOException;
import java.util.Map;

public class DataStoreController implements FileController{
    private FileController fileController;
    public DataStoreController(){
        Map<String, Boolean> fileType = Settings.getInstance().getFileType();
        if (fileType.get("JSON")){
            fileController = new JsonController();
        }
    }

    @Override
    public void saveItem(StorerData<QuantifiableItem> items) throws IOException {
        fileController.saveItem(items);
    }
//
//    @Override
//    public void saveBill() {
//    }

    public void saveImage(StorerData<ImageWithID> images) throws IOException {
        fileController.saveImage(images);
    }


}
