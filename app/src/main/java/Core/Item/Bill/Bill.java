package Core.Item.Bill;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;
import Core.Item.Costly;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
public class Bill implements Costly {
    @NonNull
    protected HashMap<Integer, Integer> itemsQuantity = new HashMap<Integer, Integer>();

    protected Bill(HashMap<Integer, Integer> itemsQuantity) {
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

    @Override
    public double getCost() throws ItemInBillNotExist {
        double cost = 0;
        for (int id : itemsQuantity.keySet()) {
            try {
                cost += DataStore.getInstance().getItemWithID(id).getCost();
            } catch (SearchedItemNotExist e) {
                throw new ItemInBillNotExist(id);
            }
        }
        return cost;
    }

    public ArrayList<QuantifiableItem> getItemList() throws ItemInBillNotExist {
        ArrayList<QuantifiableItem> list = new ArrayList<>();
        for (int id : itemsQuantity.keySet()) {
            try {
                // The below error should not be possible because the quantity is asserted to not have error
                list.add(new QuantifiableItem(DataStore.getInstance().getItemWithID(id), itemsQuantity.get(id)));
            } catch (NegativeQuantityException ignored) {
            }
            catch (SearchedItemNotExist e) {
                throw new ItemInBillNotExist(id);
            }
        }
        return list;
    }
}
