package Core.Item;

import Core.IDAble;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Setter
public class Item implements IDAble, Cloneable, Costly {

    @Nullable
    private Integer id;
    @Nullable
    @Getter
    private String name;
    @Nullable
    private Double price;

    @Nullable
    @Getter
    private String category;
    @Nullable
    @Getter
    private String image;

    @NonNull
    @Getter
    @Builder.Default
    private boolean deleted = true;

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

    @Override
    public double getCost() {
        return price;
    }
}
