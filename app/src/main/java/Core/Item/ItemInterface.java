package Core.Item;

import Core.IDAble;
import Core.Item.Bill.Image.ImageWithID;

public interface ItemInterface extends IDAble, Costly {
    String getName();

    Double getOriginalPrice();

    String getCategory();

    ImageWithID getImage();

    void setName(String name);

    void setCategory(String category);

    void setImage(ImageWithID image);

    void setOriginalPrice(Double originalPrice);
}
