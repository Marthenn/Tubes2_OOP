package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemOverOrderedException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;

import java.util.ArrayList;

public interface CanPayWithPoint {
    /**
     * Finalize the current bill held by the Customer
     * @return The final FixedBill after all applicable modifier
     * @throws ZeroPointException The PremiumCustomer has no point
     * @throws PointInaccessibleIfNotMemberException The PremiumCustomer cannot pay with point due to their membership being deactivated
     * @throws NoOngoingPurchaseException The PremiumCustomer has no current Bill
     */
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException;

    /**
     * Finalize the current bill held by the PremiumCustomer. External modifier will be applied first before the PremiumCustomer's own modifier
     * @param externalModifier Modifiers to be first applied to the FixedBill
     * @return The final FixedBill after all applicable modifier
     * @throws ZeroPointException The PremiumCustomer has no point
     * @throws PointInaccessibleIfNotMemberException The PremiumCustomer cannot pay with point due to their membership being deactivated
     * @throws NoOngoingPurchaseException The PremiumCustomer has no current Bill
     */
    public FixedBill payWithPoint(ArrayList<FixedBillModifier> externalModifier) throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException;

}
