package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.MembershipState.*;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import lombok.*;

import java.util.ArrayList;


public class PremiumCustomer extends Customer implements MembershipStateInterface {
    private MembershipState state;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    @Setter
    private String name;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    @Setter
    private String email;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    @Setter
    private String phoneNumber;

    @Getter(AccessLevel.PUBLIC)
    private int point = 0;

    public PremiumCustomer(Customer customer, String name, String phoneNumber, String email){
        this(customer, name, phoneNumber, email, MembershipStateName.MEMBER);
    }

    public PremiumCustomer(Customer customer, String name, String phoneNumber, String email, MembershipStateName stateName){
        super(customer);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = MembershipStateFactory.getInstance().createState(stateName, this);
    }

    /**
     * Transition the PremiumCustomer to a another state
     * @param state The MembershipState to transition into
     */
    public void transitionToState(MembershipStateName state) {
        if (state == this.getStatus()){
            return;
        }

        this.state = MembershipStateFactory.getInstance().createState(state, this);
    }

    /**
     * @return The state (MembershipState) of the PremiumCustomer
     */
    @Override
    public MembershipStateName getStatus() {
        return state.getStatus();
    }

    /**
     *
     * @return Whether the PremiumCustomer has any point
     */
    public boolean isNoPoint() {
        return point == 0;
    }

    @Override
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist {
        return state.payWithPoint();
    }

    @Override
    public FixedBill payWithPoint(ArrayList<FixedBillModifier> externalModifier) throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist {
        return state.payWithPoint(externalModifier);
    }
}
