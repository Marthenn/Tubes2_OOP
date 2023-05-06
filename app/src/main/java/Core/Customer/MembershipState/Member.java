package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FlatFixedBillModifier;

import java.util.ArrayList;

public class Member extends MembershipState{
    public Member(PremiumCustomer context) {
        super(context);
    }

    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.MEMBER;
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

        externalModifier.add(new FlatFixedBillModifier("Pembayaran dengan poin sebagai member", getContext().getPoint()));
        return pay(externalModifier);
    }
}
