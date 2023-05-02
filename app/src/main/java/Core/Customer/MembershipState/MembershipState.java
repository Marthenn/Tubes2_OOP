package Core.Customer.MembershipState;

import Core.Customer.PremiumCustomer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;


@AllArgsConstructor
public abstract class MembershipState implements MembershipStateInterface{
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private PremiumCustomer context;

}
