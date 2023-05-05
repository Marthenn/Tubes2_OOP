package Core.Item.Bill;

import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.IDAble;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Costly;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import lombok.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

@RequiredArgsConstructor
public class Bill implements Costly, IDAble {

    @NonNull
    private Integer id;

    @Nullable
    @Getter
    private Integer ownerId;

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

    /**
     * If certain Item in the Bill does not exist in DataStore, it will not be included here
     * @return
     * @throws ItemInBillNotExist
     */
    public ArrayList<QuantifiableItem> getItemList() {
        ArrayList<QuantifiableItem> list = new ArrayList<>();
        for (int id : itemsQuantity.keySet()) {
            try {
                // The below error should not be possible because the quantity is asserted to not have error
                list.add(new QuantifiableItem(DataStore.getInstance().getItemWithID(id), itemsQuantity.get(id)));
            } catch (NegativeQuantityException | SearchedItemNotExist ignored) {
            }

        }
        return list;
    }

    /**
     * @return The supposedly unique ID of the item
     */
    @Override
    public Integer getID() {
        return id;
    }

    public FixedBill getFixedBill() {
        return new FixedBill(this.id, getItemList(), new ArrayList<>());
    }

    public void setOwner(Customer customer) {
        this.ownerId = customer.getID();
    }
}
