package Core.Item.Bill.FixedBill.FixedBillModifier;


public class DiscountFixedBillModifier extends FixedBillModifier{
    public DiscountFixedBillModifier(String modifierText, double modifierValue){
        super(FixedBillModifierType.DISCOUNT, modifierText, modifierValue);
    };

    @Override
    public double applyModifier(double initialValue) {
        return initialValue * (1 - this.getModifierValue());
    }
}
