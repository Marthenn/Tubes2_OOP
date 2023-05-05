package Core.Item.Cost;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;

public interface Costable {

    /**
     *
     * @return The cost of the item(s)
     * @throws ItemIsNotInBillException Certain item does not exist in the Bill
     * @throws ItemInBillNotExist Certain item does not exist in the DataStore
     */
    Double getCost() throws ItemIsNotInBillException, ItemInBillNotExist;
}
