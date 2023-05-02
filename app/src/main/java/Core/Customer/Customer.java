package Core.Customer;
import java.util.*;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.IDAble;
import Core.Customer.CanPay;
import Core.Item.Bill.Bill;
import Core.Item.Bill.FixedBill.FixedBill;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nullable;

@Data
class Customer implements IDAble, CanPay {
    @NonNull
    private Integer id;



    private ArrayList<FixedBill> history = new ArrayList<>();

    @Getter
    @Nullable
    private Bill ongoingPurchase;


    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public FixedBill pay()  throws NoOngoingPurchaseException {
        if (isNoOngoingPurchase()) {
            throw new NoOngoingPurchaseException();
        }
        assert(ongoingPurchase != null);
        FixedBill fixedBill = ongoingPurchase.getFixedBill();
        addFixedBill(fixedBill);
        ongoingPurchase = null;
        return fixedBill;
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