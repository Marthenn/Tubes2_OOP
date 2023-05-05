package Core.Item;

import Core.IDAble;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Cost.Costable;
import Core.Item.Price.Priceable;

interface ItemLikeInterface extends IDAble, Priceable, Costable {
    String getName();

    String getCategory();

    ImageWithID getImage();

    void setName(String name);

    void setCategory(String category);

    void setImage(ImageWithID image);

}
