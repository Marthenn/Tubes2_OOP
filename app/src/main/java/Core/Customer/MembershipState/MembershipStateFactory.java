package Core.Customer.MembershipState;
import Core.Customer.PremiumCustomer;

public class MembershipStateFactory {
    private static MembershipStateFactory instance = null;

    private MembershipStateFactory() {
    }

    /**
     * Get singleton instance of the MembershipStateFactory
     * @return Singleton instance of the MembershipStateFactory
     */
    static public MembershipStateFactory getInstance() {
        if (MembershipStateFactory.instance == null) {
            MembershipStateFactory.instance = new MembershipStateFactory();
        }
        return MembershipStateFactory.instance;
    }

    /**
     * Create a MembershipState corresponding with the given status with the given context inserted
     * @param state
     * @param context
     * @return MembershipState
     */
    public MembershipState createState(MembershipStateName state, PremiumCustomer context){
        switch (state){
            case VIP -> {
                return new VIP(context);
            }
            case MEMBER -> {
                return new Member(context);

            }
            case DEACTIVATED -> {
                return new Deactivated(context);
            }
            default -> {
                return new Deactivated(context);
            }
        }
    }
}
