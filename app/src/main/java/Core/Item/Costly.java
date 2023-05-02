package Core.Item;

import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.Exception.ItemIsNotInBillException;

public interface Costly {
    double getCost() throws ItemIsNotInBillException, ItemInBillNotExist;
}
