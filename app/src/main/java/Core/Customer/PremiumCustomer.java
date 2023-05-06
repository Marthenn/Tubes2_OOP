package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.MembershipState.MembershipState;
import Core.Customer.MembershipState.MembershipStateFactory;
import Core.Customer.MembershipState.MembershipStateInterface;
import Core.Customer.MembershipState.MembershipStateName;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;


public class PremiumCustomer extends Customer implements MembershipStateInterface {
    private MembershipState state;

    private MembershipStateName stateName;

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
        this.transitionToState(stateName);
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
        this.stateName = this.state.getStatus();
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
