package Core.Item;

import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Deserializer.QuantifiableItemDeserializer;
import Core.IDAble.IDAbleEmitter;
import Core.IDAble.IDAbleListener;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Exception.NegativeQuantityModifierException;
import Core.Serializer.QuantifiableItemSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@JsonSerialize(using = QuantifiableItemSerializer.class)
@JsonDeserialize(using = QuantifiableItemDeserializer.class)
public class QuantifiableItem implements ItemLikeInterface, IDAbleEmitter<IDAbleListener<QuantifiableItem>>, Serializable {
    @Setter
    private int quantity = 0;

    @Setter
    private Item item;

    @JsonIgnore
    private transient ArrayList<IDAbleListener<QuantifiableItem>> itemListeners = new ArrayList<>();

    public QuantifiableItem(Item item){
        this.item = item;
    }


    /**
     * Create a new quantifiable item
     * @param item
     * @param quantity
     * @throws NegativeQuantityException The given quantity is negative
     */
    public QuantifiableItem(Item item, int quantity) throws NegativeQuantityException {
        this.item = item;
        if (quantity < 0) {
            throw  new NegativeQuantityException();
        }

        this.quantity = quantity;
    }

    /**
     * Decrease the quantity of the item
     * @param number The amount to decrease by
     * @throws NegativeQuantityException The resulting quantity is 0
     * @throws NegativeQuantityModifierException number is negative
     */
    public void decreaseQuantity(int number) throws NegativeQuantityException, NegativeQuantityModifierException {
        if (number < 0){
            throw new NegativeQuantityModifierException();
        }

        if (this.quantity - number < 0) {
            throw new NegativeQuantityException();
        }
        modifyQuantity(-1*number);
    }

    /**
     * Decrease the quantity of the item by 1
     * @throws NegativeQuantityException The resulting quantity is 0
     * @throws NegativeQuantityModifierException number is negative
     */
    public void decreaseQuantity() throws NegativeQuantityException, NegativeQuantityModifierException {
        decreaseQuantity(1);
    }

    /**
     * Increase the quantity of the item
     * @param number The amount to increase by
     * @throws NegativeQuantityException The resulting quantity is 0
     * @throws NegativeQuantityModifierException number is negative
     */
    public void increaseQuantity(int number) throws NegativeQuantityException, NegativeQuantityModifierException {
        if (number < 0){
            throw new NegativeQuantityModifierException();
        }

        if (this.quantity + number < 0) {
            throw new NegativeQuantityException();
        }
        modifyQuantity(number);
    }

    /**
     * Increase quantity by one
     * @throws NegativeQuantityException The resulting quantity is 0
     * @throws NegativeQuantityModifierException number is negative
     */
    public void increaseQuantity() throws NegativeQuantityException, NegativeQuantityModifierException {
        increaseQuantity(1);
    }

    private void modifyQuantity(int modifier) {
        this.quantity += modifier;
        notifyListener();
    }

    /**
     *
     * @return Whether the quantity of the item is zero
     */
    @JsonIgnore
    public boolean isQuantityZero() {
        return this.quantity == 0;
    }

    @JsonIgnore
    public Double getCost() {
        return quantity * item.getCost();
    }

    @Override
    public Integer getID() {
        return item.getID();
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public String getCategory() {
        return item.getCategory();
    }

    @Override
    @JsonIgnore
    public ImageWithID getImage() throws SearchedItemNotExist {
        return item.getImage();
    }

    @Override
    public void setName(String name) {
        this.item.setName(name);
        notifyListener();
    }

    @Override
    public void setCategory(String category) {
        this.item.setCategory(category);
        notifyListener();
    }

    @Override
    @JsonIgnore
    public void setImage(String image) {
        this.item.setImage(image);
        notifyListener();
    }

    @Override
    public boolean isDeleted() {
        return item.isDeleted();
    }

    @Override
    public void setDeleted(boolean deleted) {
        item.setDeleted(deleted);
        notifyListener();
    }

    public void setSingularCost(Double cost) {
        this.item.setCost(cost);
        notifyListener();
    }

    /**
     *
     * @return The cost of one item
     */
    @JsonIgnore
    public Double getSingularCost() {
        return this.item.getCost();
    }

    /**
     *
     * @return The price of one item
     */
    @JsonIgnore
    public Double getSingularPrice() {
        return this.item.getPrice();
    }


    public void setSingularPrice(Double price) {
        this.item.setPrice(price);
    }

    public Double setSingularPrice() {
        return this.item.getPrice();
    }


    /**
     * @return The cost of the item(s)
     * @throws ItemInBillNotExist       Certain item does not exist in the DataStore
     */
    @Override
    @JsonIgnore
    public Double getPrice() throws ItemInBillNotExist {
        return quantity * item.getPrice();
    }

    @Override
    @JsonIgnore
    public Double getProfit() throws ItemInBillNotExist {
        return getPrice() - getCost();
    }

    @Override
    public void notifyListener() {
        for (IDAbleListener<QuantifiableItem> itemListener : itemListeners) {
            itemListener.onItemWithIDChange(this);
        }
    }

    @Override
    @JsonIgnore
    public void setListenerList(ArrayList<IDAbleListener<QuantifiableItem>> listeners) {
        this.itemListeners = listeners;
    }

    public Integer getImageID() {
        return item.getImageID();
    }



}
