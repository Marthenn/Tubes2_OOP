package Core.Customer.MembershipState;

import Core.Customer.CanPay;
import Core.Customer.CanPayWithPoint;

public interface MembershipStateInterface extends CanPay, CanPayWithPoint {

    /**
     *
     * @return The state of the corresponding MembershipState or PremiumCustomer
     */
    public MembershipStateName getStatus();
}
