package Core.Item.Bill.FixedBill.FixedBillModifier;


public class FractionFixedBillModifier extends FixedBillModifier{
    public FractionFixedBillModifier(String modifierText, double modifierValue){
        super(FixedBillModifierType.FRACTION, modifierText, modifierValue);
    };

    @Override
    public double applyModifier(double initialValue) {
        return initialValue * this.getModifierValue();
    }
}
