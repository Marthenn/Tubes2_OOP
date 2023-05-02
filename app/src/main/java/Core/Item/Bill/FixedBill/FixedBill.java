package Core.Item.Bill.FixedBill;

import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Costly;
import Core.Item.QuantifiableItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;

@NoArgsConstructor
public class FixedBill implements Costly {
    private ArrayList<QuantifiableItem> items = new ArrayList<>();

    @Getter(AccessLevel.PUBLIC)
    private ArrayList<FixedBillModifier> modifiers;

    public void addHistory(QuantifiableItem transactionHistory) {
        items.add(transactionHistory);
    }

    public void addFixedBillModifier(FixedBillModifier fixedBillModifier){
        modifiers.add(fixedBillModifier);
    }

    private double getUnmodifiedCost() {
        double realCost = 0;
        for (QuantifiableItem transactionHistory : items){
            realCost += transactionHistory.getCost();
        }

        return realCost;
    }


    @Override
    public double getCost() {
        double cost = getUnmodifiedCost();
        for (FixedBillModifier modifier : modifiers){
            cost = modifier.applyModifier(cost);
        }
        return cost;
    }
}
