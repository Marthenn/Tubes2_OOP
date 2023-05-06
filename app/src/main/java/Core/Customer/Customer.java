package Core.Customer;

import Core.Customer.Exception.NoOngoingPurchaseException;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.IDAble.IDAbleEmitter;
import Core.IDAble.IDAbleListener;
import Core.Item.Bill.Bill;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

@Data
public class Customer implements IDAbleEmitter<IDAbleListener<Customer>>, CanPay {
    @NonNull
    private Integer id;

    @Getter
    private ArrayList<FixedBill> history = new ArrayList<>();

    @Nullable
    private Integer billID;

    private transient ArrayList<IDAbleListener<Customer>> customerListeners = new ArrayList<>();

    public Customer(int id) {
        this.id = id;
    }

    public Customer(Customer customer) {
        this.id = customer.getID();
        this.history = customer.getHistory();
        try {
            this.billID = customer.getOngoingPurchase().getID();
        } catch (NoOngoingPurchaseException | SearchedItemNotExist ignored) {

        }
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public FixedBill pay() throws NoOngoingPurchaseException, SearchedItemNotExist {
        return this.pay(new ArrayList<FixedBillModifier>());
    }

    @Override
    public FixedBill pay(ArrayList<FixedBillModifier> externalModifier) throws NoOngoingPurchaseException, SearchedItemNotExist {
        FixedBill finalBill = finalizeOngoingPurchase();
        for (FixedBillModifier modifier : externalModifier) {
            finalBill.addFixedBillModifier(modifier);
        }

        return finalBill;
    }

    public boolean isNoOngoingPurchase() {
        return billID == null;
    }

    /**
     * Convert the Bill in ongoingPurchase to FixedBill, insert it to history and return it
     * @return The Bill from ongoingPurchase converted to FixedBill
     * @throws NoOngoingPurchaseException The Customer has no current bill
     */
    public FixedBill finalizeOngoingPurchase() throws NoOngoingPurchaseException, SearchedItemNotExist {
        if (isNoOngoingPurchase()) {
            throw new NoOngoingPurchaseException();
        }

        assert(getOngoingPurchase() != null);
        FixedBill finalBill = getOngoingPurchase().getFixedBill();
        addFixedBill(finalBill);
        return finalBill;

    }

    /**
     * Add fixedBill to the Customer's history
     * @param fixedBill
     */
    public void addFixedBill(FixedBill fixedBill){
        history.add(fixedBill);
    };

    /**
     * Assign bill to the Customer's ongoingPurchase
     * @param bill
     */
    public void assignBill(Bill bill){
        billID = bill.getID();
    }

    public Bill getOngoingPurchase() throws NoOngoingPurchaseException, SearchedItemNotExist {
        if (billID == null) {
            throw new NoOngoingPurchaseException();
        }
        return DataStore.getInstance().getBillWithID(this.id);
    }

    @Override
    public void notifyListener() {
        for (IDAbleListener<Customer> itemListener : customerListeners) {
            itemListener.onItemWithIDChange(this);
        }
    }

    @Override
    public void setListenerList(ArrayList<IDAbleListener<Customer>> listeners) {
        this.customerListeners = listeners;
    }
}