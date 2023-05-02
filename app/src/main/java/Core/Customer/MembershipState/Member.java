package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.DiscountFixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FlatFixedBillModifier;

public class Member extends MembershipState{
    public Member(PremiumCustomer context) {
        super(context);
    }

    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.MEMBER;
    }

    @Override
    public FixedBill pay() throws NoOngoingPurchaseException {
        return getContext().finalizeOngoingPurchase();
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        FixedBill finalBill = getContext().finalizeOngoingPurchase();

        if (this.getContext().isNoPoint()){
            throw new ZeroPointException();
        }

        finalBill.addFixedBillModifier(new FlatFixedBillModifier("Pembayaran dengan poin sebagai member", getContext().getPoint()));
        return finalBill;
    }
}
