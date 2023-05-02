package Core.Item.Bill.FixedBill.FixedBillModifier;


public class FlatFixedBillModifier extends FixedBillModifier{
    public FlatFixedBillModifier(String modifierText, double modifierValue){
        super(FixedBillModifierType.FLAT, modifierText, modifierValue);
    };

    @Override
    public double applyModifier(double initialValue) {
        return initialValue - this.getModifierValue();
    }
}
