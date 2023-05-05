package Core.Item.Cost;

import Core.Item.Bill.Exception.ItemInBillNotExist;

public interface Costable {

    /**
     *
     * @return The cost of the item(s)
     * @throws ItemInBillNotExist Certain item does not exist in the DataStore
     */
    Double getCost() throws ItemInBillNotExist;
}
