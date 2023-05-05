package Core.Customer.MembershipState;

/**
 * The names of all possible MembershipState
 */
public enum MembershipStateName {
    DEACTIVATED("Deactivated Member"),
    MEMBER ("Member"),
    VIP ("VIP");

    String name;

    MembershipStateName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
