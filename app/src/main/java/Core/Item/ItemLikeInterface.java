package Core.Item;

import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.IDAble.IDAble;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Profit.Profitable;

interface ItemLikeInterface extends IDAble, Profitable {
    String getName();

    String getCategory();

    ImageWithID getImage() throws SearchedItemNotExist;

    void setName(String name);

    void setCategory(String category);

    void setImage(String base64Image);

    boolean isDeleted();

    void setDeleted(boolean deleted);
}
