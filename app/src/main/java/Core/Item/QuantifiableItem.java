package Core.Item;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Exception.NegativeQuantityModifierException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class QuantifiableItem implements ItemLikeInterface {
    @Setter
    private int quantity = 0;

    @Getter
    private Item item;

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
        this.quantity -= number;
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
        this.quantity += number;
    }

    /**
     * Increase quantity by one
     * @throws NegativeQuantityException The resulting quantity is 0
     * @throws NegativeQuantityModifierException number is negative
     */
    public void increaseQuantity() throws NegativeQuantityException, NegativeQuantityModifierException {
        increaseQuantity(1);
    }

    /**
     *
     * @return Whether the quantity of the item is zero
     */
    public boolean isQuantityZero() {
        return this.quantity == 0;
    }

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
    public ImageWithID getImage() {
        return item.getImage();
    }

    @Override
    public void setName(String name) {
        this.item.setName(name);
    }

    @Override
    public void setCategory(String category) {
        this.item.setCategory(category);
    }

    @Override
    public void setImage(ImageWithID image) {
        this.item.setImage(image);
    }

    public void setSingularCost(Double cost) {
        this.item.setCost(cost);
    }

    public Double getSingularCost() {
        return this.item.getCost();
    }

    public void setSingularPrice(Double price) {
        this.item.setPrice(price);
    }

    public Double setSingularPrice() {
        return this.item.getPrice();
    }


    /**
     * @return The cost of the item(s)
     * @throws ItemIsNotInBillException Certain item does not exist in the Bill
     * @throws ItemInBillNotExist       Certain item does not exist in the DataStore
     */
    @Override
    public Double getPrice() throws ItemIsNotInBillException, ItemInBillNotExist {
        return quantity * item.getPrice();
    }

    @Override
    public Double getProfit() throws ItemIsNotInBillException, ItemInBillNotExist {
        return getPrice() - getCost();
    }
}
