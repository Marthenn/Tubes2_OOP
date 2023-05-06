package Core.Item;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Image.ImageWithID;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class Item implements Cloneable, ItemInterface {

    @Nullable
    private Integer id;

    @Nullable
    @Getter
    @Setter
    private String name;

    @Nullable
    @Setter
    @Getter
    private Double price;

    @Nullable
    @Getter
    @Setter
    private Double cost;

    @Nullable
    @Getter
    @Setter
    private String category;

    @Nullable
    @Setter
    @Getter
    private Integer imageID;

    @Getter
    @Setter
    @Builder.Default
    private boolean deleted = false;

    @Override
    public Integer getID() {
        return id;
    }

    public Item(Item item){
        this.setName(item.name);
        this.setCategory(item.category);
        this.setDeleted(item.deleted);
        this.setImageID(item.getImageID());
        this.setPrice(item.price);
    }


    /**
     *
     * @return a clone of the item without the ID
     */
    @Override
    public Item clone() {
        try {
            Item clone = (Item) super.clone();
            clone.setName(this.name);
            clone.setPrice(this.price);
            clone.setImageID(this.imageID);
            clone.setDeleted(this.deleted);
            clone.setCategory(this.category);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Set the Item's deleted attribute to true
     */
    public void setAsDeleted() {
        this.deleted = true;
    }

    @Override
    public Double getProfit() throws ItemInBillNotExist {
        return getPrice() - getCost();
    }

    @Override
    public ImageWithID getImage() throws SearchedItemNotExist {
        return DataStore.getInstance().getImageWithID(this.imageID);
    }

    @Override
    public void setImage(String base64Image) {
        ImageWithID addedImage = DataStore.getInstance().createNewImageWithID(base64Image);
        this.imageID = addedImage.getID();
    }
}
