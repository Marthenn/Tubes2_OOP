package Core.Item.Bill.FixedBill.FixedBillModifier;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public abstract class FixedBillModifier {
    @NonNull
    @Getter
    private FixedBillModifierType type;

    @NonNull
    @Getter
    private String modifierText;

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private double modifierValue;

    public abstract double applyModifier(double initialValue);

}
