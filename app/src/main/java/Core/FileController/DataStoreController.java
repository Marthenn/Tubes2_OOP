package Core.FileController;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.StorerData;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import Core.Settings;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;
import java.util.Map;

public class DataStoreController implements FileController{
    private FileController fileController;

    @Setter
    private String fileType;
    public DataStoreController(){
//        Map<String, Boolean> fileType = DataStore.getInstance().getFileType();
////        System.out.println(fileType.keySet());
        fileController = new JsonController();
//        if (fileType.get("JSON")){
//        }
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
    public StorerDataImageWithID loadImage() throws IOException {
        return fileController.loadImage();
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        return fileController.loadItem();
    }

    @Override
    public void saveBill (StorerDataBill bills) throws IOException {
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
