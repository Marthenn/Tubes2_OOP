package Core.FileController;

import Core.DataStore.StorerData.StorerData;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface FileController{

    void saveItem(StorerData<QuantifiableItem> items) throws IOException;
//    void saveBill();

    void saveImage(StorerData<ImageWithID> images) throws IOException;
}
