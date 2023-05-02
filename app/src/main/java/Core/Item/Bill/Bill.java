package Core.Item.Bill;

import Core.Item.Bill.Exception.ItemIsNotInBillException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Costly;
import Core.Item.SoldItem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Bill extends BarebonesBill implements Costly {
//    private items;

    public SoldItem getItem(int id) throws ItemIsNotInBillException {
        if (!itemsQuantity.containsKey(id)){
            throw new ItemIsNotInBillException();
        }
//       To be changed later
        return null;
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
