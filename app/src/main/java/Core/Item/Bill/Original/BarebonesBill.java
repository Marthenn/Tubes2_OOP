package Core.Item.Bill.Original;

import Core.Item.Exception.NegativeQuantityException;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
public class BarebonesBill{
    @NonNull
    protected HashMap<Integer, Integer> itemsQuantity = new HashMap<Integer, Integer>();

    protected BarebonesBill(HashMap<Integer, Integer> itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }


    public boolean setItemIDQuantity(int id, int quantity) throws NegativeQuantityException {
        if (quantity < 0) {
            throw new NegativeQuantityException();
        }

        if (itemsQuantity.containsKey(id)){
            itemsQuantity.put(id, quantity);
            return true;
        }

        return false;

    }

    public boolean addItemID(int id) {
        if (itemsQuantity.containsKey(id)) {
            itemsQuantity.put(id, itemsQuantity.get(id) + 1);
            return true;
        }
        itemsQuantity.put(id, 1);
        return false;
    }

    public boolean removeItemID(int id) {
        if (itemsQuantity.containsKey((id))){
            itemsQuantity.remove(id);
            return true;
        }
        return  false;
    }

}
