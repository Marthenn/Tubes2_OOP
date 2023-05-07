package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemOverOrderedException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;

import java.util.ArrayList;

public class Deactivated extends MembershipState{
    public Deactivated(PremiumCustomer context) {
        super(context);
    }
    @Override
    public FixedBill pay() throws NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException {
        return pay(new ArrayList<FixedBillModifier>());

    }

    @Override
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException {
        return getContext().finalizeOngoingPurchase();
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        return payWithPoint(new ArrayList<FixedBillModifier>());
    }

    @Override
    public FixedBill payWithPoint(ArrayList<FixedBillModifier> externalModifier) throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        throw new PointInaccessibleIfNotMemberException();
    }


    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.DEACTIVATED;
    }


}
