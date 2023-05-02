package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;

import java.util.ArrayList;

public interface CanPay {
//    Should return fixed bill

    public FixedBill pay() throws NoOngoingPurchaseException;
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException;

}
