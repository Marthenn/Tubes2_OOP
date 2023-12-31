package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.Customer.Exception.PointInaccessibleIfNotMemberException;
import Core.Customer.Exception.ZeroPointException;
import Core.Customer.MembershipState.MembershipState;
import Core.Customer.MembershipState.MembershipStateFactory;
import Core.Customer.MembershipState.MembershipStateInterface;
import Core.Customer.MembershipState.MembershipStateName;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Deserializer.Customer.PremiumCustomerDeserializer;
import Core.Item.Bill.Exception.ItemOverOrderedException;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Serializer.Customer.PremiumCustomerSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


@JsonSerialize(using = PremiumCustomerSerializer.class)
@JsonDeserialize(using = PremiumCustomerDeserializer.class)
public class PremiumCustomer extends Customer implements MembershipStateInterface {
    private MembershipState state;

    private MembershipStateName stateName;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String name;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String email;

    @Getter(AccessLevel.PUBLIC)
    @NonNull
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
        if (this.state != null) {
            if (state == this.getStatus()) {
                return;
            }
        }

        this.state = MembershipStateFactory.getInstance().createState(state, this);
        this.stateName = this.state.getStatus();
        notifyListener();
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
    public FixedBill payWithPoint() throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException {
        return state.payWithPoint();
    }

    @Override
    public FixedBill payWithPoint(ArrayList<FixedBillModifier> externalModifier) throws ZeroPointException, PointInaccessibleIfNotMemberException, NoOngoingPurchaseException, SearchedItemNotExist, ItemOverOrderedException {
        return state.payWithPoint(externalModifier);
    }

    public void setName(String name) {
        this.name = name;
        notifyListener();
    }

    public void setEmail(String email) {
        this.email = email;
        notifyListener();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyListener();
    }
}
