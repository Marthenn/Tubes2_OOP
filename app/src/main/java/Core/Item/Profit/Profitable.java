package Core.Item.Profit;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Cost.Costable;
import Core.Item.Price.Priceable;

public interface Profitable extends Priceable, Costable {

    /**
     *
     * @return price - cost
     */
    Double getProfit() throws ItemInBillNotExist;
}
