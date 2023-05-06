package Core.Item.Price;

import Core.Item.Bill.Exception.ItemInBillNotExist;

public interface Priceable {

    /**
     *
     * @return The cost of the item(s)
     * @throws ItemInBillNotExist Certain item does not exist in the DataStore
     */
    Double getPrice() throws ItemInBillNotExist;
}
