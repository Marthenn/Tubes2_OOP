package Core.Customer.MembershipState;

import Core.Customer.PremiumCustomer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;


@AllArgsConstructor
public abstract class MembershipState implements MembershipStateInterface, Serializable {
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private PremiumCustomer context;

}
