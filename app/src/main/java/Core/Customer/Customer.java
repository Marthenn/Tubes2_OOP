package Core.Customer;
import java.util.*;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.IDAble;
import Core.Customer.CanPay;
import Core.Item.Bill.Bill;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nullable;

@Data
public class Customer implements IDAble, CanPay {
    @NonNull
    private Integer id;

    private ArrayList<FixedBill> history = new ArrayList<>();

    @Getter
    @Nullable
    private Bill ongoingPurchase;

    public Customer(int id) {
        this.id = id;
    }

    public  Customer(Customer customer) {
        this.id = customer.getID();
        this.history = customer.getHistory();
        this.ongoingPurchase = customer.getOngoingPurchase();
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public FixedBill pay()  throws NoOngoingPurchaseException {
        return this.pay(new ArrayList<FixedBillModifier>());
    }

    @Override
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException {
        FixedBill finalBill = finalizeOngoingPurchase();
        for (FixedBillModifier modifier : externalModifier) {
            finalBill.addFixedBillModifier(modifier);
        }

        return finalBill;
    }

    public boolean isNoOngoingPurchase() {
        return ongoingPurchase == null;
    }

    public FixedBill finalizeOngoingPurchase() throws NoOngoingPurchaseException {
        if (isNoOngoingPurchase()) {
            throw new NoOngoingPurchaseException();
        }

        assert(getOngoingPurchase() != null);
        FixedBill finalBill = getOngoingPurchase().getFixedBill();
        addFixedBill(finalBill);
        return finalBill;

    }

    private void addFixedBill(FixedBill fixedBill){
        history.add(fixedBill);
    };
}