package Core.Customer.MembershipState;
import Core.Customer.PremiumCustomer;

public class MembershipStateFactory {
    private static MembershipStateFactory instance = null;

    private MembershipStateFactory() {
    }

    static public MembershipStateFactory getInstance() {
        if (MembershipStateFactory.instance == null) {
            MembershipStateFactory.instance = new MembershipStateFactory();
        }
        return MembershipStateFactory.instance;
    }

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
