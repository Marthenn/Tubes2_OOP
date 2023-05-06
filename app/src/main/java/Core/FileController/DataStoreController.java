package Core.FileController;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import Core.Settings;

import java.io.IOException;
import java.util.Map;

public class DataStoreController implements FileController{
    private FileController fileController;
    public DataStoreController(){
        Map<String, Boolean> fileType = DataStore.getInstance().getFileType();
        if (fileType.get("JSON")){
            fileController = new JsonController();
        }
    }

    @Override
    public void saveItem(StorerData<QuantifiableItem> items) throws IOException {
        fileController.saveItem(items);
    }


    @Override
    public void saveImage(StorerData<ImageWithID> images) throws IOException {
        fileController.saveImage(images);
    }

    @Override
    public void saveBill (StorerData<Bill> bills) throws IOException {
        fileController.saveBill(bills);
    }

//    @Override
//    public StorerData<ImageWithID> loadImage() throws IOException {
//
//    }
//
////    @Override
//    public StorerData<QuantifiableItem> loadItem() throws IOException {
//
//    }

}
