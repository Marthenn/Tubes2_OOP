package Core.Item;

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
    @Getter
    @Setter
    private ImageWithID image;

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
        this.setImage(item.image);
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
            clone.setImage(this.image);
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
}
