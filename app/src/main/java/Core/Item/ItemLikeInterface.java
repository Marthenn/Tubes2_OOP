package Core.Item;

import Core.IDAble.IDAble;
import Core.Item.Profit.Profitable;

interface ItemLikeInterface extends IDAble, Profitable {
    String getName();

    String getCategory();

    String getImage();

    void setName(String name);

    void setCategory(String category);

    void setImage(String image);

}
