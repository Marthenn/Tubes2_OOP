package Core.Item.Bill.Original;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Costly;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import Core.Item.SoldItem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Bill extends BarebonesBill implements Costly {
//    private items;

    public Item getItem(int id) throws ItemIsNotInBillException {
        if (!itemsQuantity.containsKey(id)){
            throw new ItemIsNotInBillException();
        }

//        try {
//
//        } catch (e) {
//
//        }
//       To be changed later
        try {

            return DataStore.getInstance().getItemWithID(id);
        } catch (SearchedItemNotExist ignored) {
            return null;
        }
    }

    @Override
    public double getCost() {
        return 0;
    }

    public BarebonesBill getBarebonesBill() {
        return new BarebonesBill(this.itemsQuantity);
    }

    public FixedBill getFixedBill() {
        FixedBill producedFixedBill = new FixedBill();
        for (int itemsID : itemsQuantity.keySet()){
            int quantity = itemsQuantity.get(itemsID);
//            To be changed
            producedFixedBill.addHistory(null);

        }
        return producedFixedBill;

    }
}
