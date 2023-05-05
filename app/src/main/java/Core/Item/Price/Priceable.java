package Core.Item.Price;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;

public interface Priceable {

    /**
     *
     * @return The cost of the item(s)
     * @throws ItemIsNotInBillException Certain item does not exist in the Bill
     * @throws ItemInBillNotExist Certain item does not exist in the DataStore
     */
    Double getPrice() throws ItemIsNotInBillException, ItemInBillNotExist;
}
