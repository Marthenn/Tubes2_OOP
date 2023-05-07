package Core.Item.Bill.FixedBill.FixedBillModifier;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiscountFixedBillModifier.class, name = "DiscountFixedBillModifier"),
        @JsonSubTypes.Type(value = FractionFixedBillModifier.class, name = "FractionFixedBillModifier"),
        @JsonSubTypes.Type(value = FlatFixedBillModifier.class, name = "FlatFixedBillModifier")
})
public abstract class FixedBillModifier implements Serializable {
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
