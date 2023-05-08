package Core.Item.Bill.FixedBill;

import Core.Deserializer.FixedBillDeserializer;
import Core.IDAble.IDAble;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Profit.Profitable;
import Core.Item.QuantifiableItem;
import Core.Serializer.FixedBillSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@RequiredArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = FixedBillSerializer.class)
@JsonDeserialize(using = FixedBillDeserializer.class)
public class FixedBill implements Profitable, IDAble, Serializable {

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

    private double getUnmodifiedPrice() {
        double realCost = 0;
        for (QuantifiableItem transactionHistory : items){
            realCost += transactionHistory.getCost();
        }

        return realCost;
    }


    @Override
    public Double getPrice() {
        double price = getUnmodifiedPrice();
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

    @Override
    public Double getCost() throws ItemInBillNotExist {
        double cost = 0;
        for (QuantifiableItem transactionHistory : items) {
            cost += transactionHistory.getCost();
        }
        return cost;
    }

    @Override
    public Double getProfit() throws ItemInBillNotExist {
        return getPrice() - getCost();
    }
}
