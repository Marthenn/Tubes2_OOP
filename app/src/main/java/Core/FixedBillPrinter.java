package Core;
import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;

import java.util.ArrayList;

public class FixedBillPrinter {
    private FixedBill fixedBill;
    private final int fixedBillIdx, customerId;
    private String filename;
    public FixedBillPrinter(String filename, int customerId, int fixedBillIdx) throws SearchedItemNotExist {
        this.customerId = customerId;
        this.fixedBillIdx = fixedBillIdx;
        DataStore dataStore = DataStore.getInstance();
        Customer customer = dataStore.getCustomerWithID(customerId);
        ArrayList<FixedBill> history = customer.getHistory();
        this.fixedBill = history.get(fixedBillIdx);
        this.filename = filename;
    }
    public void printFixedBill() {
        PDFPrinter pdfPrinter = new PDFPrinter(this.filename);
        Thread pdfThread = new Thread(pdfPrinter);
        pdfThread.start();
        String text1 = String.format("Fixed Bill %d\nCustomer Id: %s ",
                this.fixedBillIdx, this.customerId);
        pdfPrinter.addText(text1);
        for(int i = 0; i < this.fixedBill.getItems().size(); i++) {
            QuantifiableItem item = this.fixedBill.getItems().get(i);
            String text2 = String.format("%d. Item ID: %d, Name: %s, Category: %s, Cost: $%.2f, OriginalPrice: $%.2f",
                    i + 1, item.getItem().getID(), item.getItem().getName(), item.getItem().getCategory(),
                    item.getItem().getCost(), item.getItem().getCost() );
            pdfPrinter.addText(text2);
        }
        // wait for the PDF thread to finish
        try {
            pdfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
