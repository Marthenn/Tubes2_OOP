package Core.Item;

import Core.IDAble;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Profit.Profitable;

interface ItemLikeInterface extends IDAble, Profitable {
    String getName();

    String getCategory();

    ImageWithID getImage();

    void setName(String name);

    void setCategory(String category);

    void setImage(ImageWithID image);

}
