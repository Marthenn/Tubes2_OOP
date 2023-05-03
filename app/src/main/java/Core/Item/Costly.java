package Core.Item;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;

public interface Costly {

    /**
     *
     * @return The cost of the item(s)
     * @throws ItemIsNotInBillException Certain item does not exist in the Bill
     * @throws ItemInBillNotExist Certain item does not exist in the DataStore
     */
    double getCost() throws ItemIsNotInBillException, ItemInBillNotExist;
}
