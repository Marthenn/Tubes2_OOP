package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Item.Bill.FixedBill.FixedBill;

public interface CanPayWithPoint {
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException;
}
