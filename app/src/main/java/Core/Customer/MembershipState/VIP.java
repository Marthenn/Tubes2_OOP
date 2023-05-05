package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.DiscountFixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FlatFixedBillModifier;

import java.util.ArrayList;

public class VIP extends MembershipState{
    public VIP(PremiumCustomer context) {
        super(context);
    }

    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.VIP;
    }


    @Override
    public FixedBill pay() throws NoOngoingPurchaseException, SearchedItemNotExist {
        return this.pay(new ArrayList<FixedBillModifier>());
    }

    @Override
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException, SearchedItemNotExist {
        FixedBill finalBill = getContext().finalizeOngoingPurchase();
        for (FixedBillModifier modifier : externalModifier) {
            finalBill.addFixedBillModifier(modifier);
        }
        finalBill.addFixedBillModifier(new DiscountFixedBillModifier("Diskon 10 persen dari menjadi VIP", 0.1));
        return finalBill;
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist {
        return this.payWithPoint(new ArrayList<FixedBillModifier>());
    }

    @Override
    public FixedBill payWithPoint(ArrayList<FixedBillModifier> externalModifier) throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist {

        if (getContext().isNoPoint()) {
            throw new ZeroPointException();
        }

        externalModifier.add(new DiscountFixedBillModifier("Diskon 10 persen dari menjadi VIP", 0.1));
        return pay(externalModifier);
    }
}
