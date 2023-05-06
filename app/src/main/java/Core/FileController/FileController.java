package Core.FileController;

import Core.DataStore.StorerData.StorerData;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface FileController{

    void saveItem(StorerDataQuantifiableItem items) throws IOException;


    void saveBill(StorerDataBill bills) throws IOException;

    void saveImage(StorerDataImageWithID images) throws IOException;

     StorerDataImageWithID loadImage() throws IOException;

//     StorerDataBill loadBill() throws IOException;

     StorerDataQuantifiableItem loadItem() throws IOException;



//
//    StorerData<ImageWithID> loadImage() throws IOException;
//
//    StorerData<QuantifiableItem> loadItem() throws IOException;
}
