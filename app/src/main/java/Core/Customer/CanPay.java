package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;

import java.util.ArrayList;

public interface CanPay {
//    Should return fixed bill

    /**
     * Finalize the current bill held by the Customer
     * @return The final FixedBill from the Customer purchase after applicable modifier
     * @throws NoOngoingPurchaseException If the Customer has no current Bill
     */
    public FixedBill pay() throws NoOngoingPurchaseException, SearchedItemNotExist;

    /**
     * Finalize the current bill held by the Customer. External modifier will be applied first before the Customer's own modifier
     * @param externalModifier Modifiers to be first applied to the FixedBill
     * @return The final FixedBill from the Customer purchase after applicable modifier
     * @throws NoOngoingPurchaseException The Customer has no current bill
     */
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException, SearchedItemNotExist;

}
