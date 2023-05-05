package Core.Item.Bill.FixedBill;

import Core.IDAble;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Price.Priceable;
import Core.Item.QuantifiableItem;
import lombok.*;


import java.util.ArrayList;

@RequiredArgsConstructor
@AllArgsConstructor
public class FixedBill implements Priceable, IDAble {

    @NonNull
    private Integer id;

    @Getter(AccessLevel.PUBLIC)
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
    public Double getPrice() {
        double price = getUnmodifiedCost();
        for (FixedBillModifier modifier : modifiers){
            price = modifier.applyModifier(price);
        }
        return price;

    }


    /**
     * @return The supposedly unique ID of the item
     */
    @Override
    public Integer getID() {
        return id;
    }
}
