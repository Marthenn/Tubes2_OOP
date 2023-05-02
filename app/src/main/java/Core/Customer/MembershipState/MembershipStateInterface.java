package Core.Customer.MembershipState;

import Core.Customer.CanPay;
import Core.Customer.CanPayWithPoint;

public interface MembershipStateInterface extends CanPay, CanPayWithPoint {
    public MembershipStateName getStatus();
}
