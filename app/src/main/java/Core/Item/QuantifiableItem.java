package Core.Item;

import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Exception.NegativeQuantityModifierException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class QuantifiableItem extends Item{
    @Setter
    private int quantity = 0;

    public QuantifiableItem(Item item){
        super(item);
    }

    public QuantifiableItem(Item item, int quantity) throws NegativeQuantityException {
        super(item);
        if (quantity < 0) {
            throw  new NegativeQuantityException();
        }

        this.quantity = quantity;

    }

    public void decreaseQuantity(int number) throws NegativeQuantityException, NegativeQuantityModifierException {
        if (number < 0){
            throw new NegativeQuantityModifierException();
        }

        if (this.quantity - number < 0) {
            throw new NegativeQuantityException();
        }
        this.quantity -= number;
    }

    public void decreaseQuantity() throws NegativeQuantityException, NegativeQuantityModifierException {
        decreaseQuantity(1);
    }

    public void increaseQuantity(int number) throws NegativeQuantityException, NegativeQuantityModifierException {
        if (number < 0){
            throw new NegativeQuantityModifierException();
        }

        if (this.quantity + number < 0) {
            throw new NegativeQuantityException();
        }
        this.quantity += number;
    }

    public void increaseQuantity() throws NegativeQuantityException, NegativeQuantityModifierException {
        increaseQuantity(1);
    }

    public double getCost() {
        return quantity * super.getCost();
    }





}
