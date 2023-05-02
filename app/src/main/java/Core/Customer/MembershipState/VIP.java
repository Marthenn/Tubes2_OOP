package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.DiscountFixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FlatFixedBillModifier;

public class VIP extends MembershipState{
    public VIP(PremiumCustomer context) {
        super(context);
    }
    @Override
    public FixedBill pay() throws NoOngoingPurchaseException {
        FixedBill finalBill = getContext().finalizeOngoingPurchase();
        finalBill.addFixedBillModifier(new DiscountFixedBillModifier("Diskon 10 persen dari menjadi VIP", 0.1));
        return finalBill;
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        FixedBill finalBill = getContext().finalizeOngoingPurchase();

        if (getContext().isNoPoint()) {
            throw new ZeroPointException();
        }

        finalBill.addFixedBillModifier(new DiscountFixedBillModifier("Diskon 10 persen dari menjadi VIP", 0.1));
        finalBill.addFixedBillModifier(new FlatFixedBillModifier("Pembayaran dengan poin sebagai member", getContext().getPoint()));
        return finalBill;
    }

    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.VIP;
    }

}
