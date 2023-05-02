package Core.Item;

import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Exception.NegativeQuantityModifierException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TransactionHistory extends Item{
    @Setter
    private int quantity = 0;

    public TransactionHistory(Item item){
        super(item);
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





}
