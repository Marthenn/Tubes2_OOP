package Core.Item.Bill;

import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Deserializer.BillDeserializer;
import Core.IDAble.IDAble;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemOverOrderedException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Price.Priceable;
import Core.Item.QuantifiableItem;
import Core.Serializer.BillSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@RequiredArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = BillSerializer.class)
@JsonDeserialize(using = BillDeserializer.class)
public class Bill implements Priceable, IDAble, Serializable {

    @NonNull
    private Integer id;

    @Nullable
    @Getter
    private Integer ownerId;

    @NonNull
    @JsonProperty("map")
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
    public Double getPrice() throws ItemInBillNotExist {
        double price = 0;
        for (int id : itemsQuantity.keySet()) {
            try {
                price += DataStore.getInstance().getItemWithID(id).getPrice();
            } catch (SearchedItemNotExist e) {
                throw new ItemInBillNotExist(id);
            }
        }
        return price;
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
                list.add(new QuantifiableItem(DataStore.getInstance().getItemWithID(id).getItem(), itemsQuantity.get(id)));
            } catch (NegativeQuantityException | SearchedItemNotExist ignored) {
            }

        }
        return list;
    }

    /**
     * @param itemId
     * @return The quantity of an item with the given ID. Null if it doesn't exist.
     */
    public Integer getQuantityOfItemWithID(Integer itemId) {
        return this.itemsQuantity.get(itemId);
    }

    /**
     * Will ignore error of if Item in a bill does not exist in the datastore
     */
    public void assertBillValid() throws ItemOverOrderedException {
        for (Integer itemId : itemsQuantity.keySet()) {
            QuantifiableItem item = null;
            try {
                item = DataStore.getInstance().getItemWithID(itemId);
            } catch (SearchedItemNotExist ignored) {
            }

            assert(item != null);


            Integer itemQuantity = itemsQuantity.get(itemId);

            if (itemQuantity == null){
                continue;
            }

            if (itemQuantity > item.getQuantity()) {
                throw new ItemOverOrderedException(itemId);
            }

        }
    }

    /**
     * @return The supposedly unique ID of the item
     */
    @Override
    public Integer getID() {
        return id;
    }

    public FixedBill getFixedBill() {
        return new FixedBill(this.id, getItemList(), new ArrayList<>()
        );
    }

    public void setOwner(Customer customer) {
        this.ownerId = customer.getID();
    }

    /**
     *
     * @return Whether this bill already has an owner or not
     */
    public boolean isWithoutOwner() {
        return this.ownerId == null;
    }
}
