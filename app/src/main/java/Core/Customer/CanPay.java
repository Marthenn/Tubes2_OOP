package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Item.Bill.FixedBill.FixedBill;

public interface CanPay {
//    Should return fixed bill
    public FixedBill pay() throws NoOngoingPurchaseException;
}
