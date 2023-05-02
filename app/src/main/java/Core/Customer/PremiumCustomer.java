package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.MembershipState.Deactivated;
import Core.Customer.MembershipState.MembershipState;
import Core.Customer.MembershipState.MembershipStateInterface;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Item.Bill.FixedBill.FixedBill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;


public class PremiumCustomer extends Customer implements MembershipStateInterface {
    private MembershipState state;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String name;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String phoneNumber;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private int point = 0;


    public PremiumCustomer(int id,String name, String phoneNumber){
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.state = new Deactivated(this);
    }

    @Override
    public MembershipStateName getStatus() {
        return state.getStatus();
    }

    public boolean isNoPoint() {
        return point == 0;
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException {
        return state.payWithPoint();
    }
}
