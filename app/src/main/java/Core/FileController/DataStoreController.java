package Core.FileController;

import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Settings;
import lombok.Setter;

import java.io.IOException;

public class DataStoreController implements FileController{
    private FileController fileController;

    @Setter
    private String fileType;
    public DataStoreController(){
        if (Settings.getInstance().getFileType().get("JSON")){
            fileController = new JsonController();
        }
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
