package Core.Item;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Image.ImageWithID;
import Core.Serializer.QuantifiableItemSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = QuantifiableItemSerializer.class)
public class Item implements Cloneable, ItemInterface {

    @Nullable
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private Double price;

    @Getter
    @Setter
    private Double cost;

    @Getter
    @Setter
    private String category;

    @Setter
    @Getter
    private Integer imageID;

    @Setter
    @Getter
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
