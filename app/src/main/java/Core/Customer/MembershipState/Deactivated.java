package Core.Customer.MembershipState;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.PremiumCustomer;
import Core.Item.Bill.FixedBill.FixedBill;

public class Deactivated extends MembershipState{
    public Deactivated(PremiumCustomer context) {
        super(context);
    }
    @Override
    public FixedBill pay() throws NoOngoingPurchaseException {
        return getContext().finalizeOngoingPurchase();
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        throw new PointInaccessibleIfNotMemberException();
    }



    @Override
    public MembershipStateName getStatus() {
        return MembershipStateName.DEACTIVATED;
    }


}
