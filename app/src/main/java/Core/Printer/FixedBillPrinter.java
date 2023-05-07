package Core.Printer;
import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;

import java.util.ArrayList;

public class FixedBillPrinter {
    private Customer customer;
    private FixedBill fixedBill;
    private final int fixedBillIdx, customerId;
    private String filename;
    public FixedBillPrinter(String filename, int customerId, int fixedBillIdx) throws SearchedItemNotExist {
        this.customerId = customerId;
        this.fixedBillIdx = fixedBillIdx;
        DataStore dataStore = DataStore.getInstance();
        try{
            this.customer = dataStore.getCustomerWithID(customerId);
        } catch (SearchedItemNotExist e){
            this.customer = dataStore.getPremiumCustomerWithID(customerId);
        }
        ArrayList<FixedBill> history = customer.getHistory();
        this.fixedBill = history.get(fixedBillIdx);
        this.filename = filename;
    }
    public void printFixedBill() {
        PDFPrinter pdfPrinter = new PDFPrinter(this.filename, 250, 1000);
        Thread pdfThread = new Thread(pdfPrinter);
        pdfThread.start();
        pdfPrinter.addText("-------------------------------");
        String text = String.format("Fixed Bill %d\nCustomer Id: %s ",
                this.fixedBillIdx, this.customerId);
        pdfPrinter.addText(text);
        if(this.customer instanceof PremiumCustomer){
            PremiumCustomer customer1 = (PremiumCustomer) customer;
            String text1 = String.format("Customer Name: %s\nEmail: %s\nPhone Number: %s\nMembership Status: %s\nPoint(s): %d",
                    customer1.getName(), customer1.getEmail(), customer1.getPhoneNumber(),
                    customer1.getStatus().getName(), customer1.getPoint());
            pdfPrinter.addText(text1);
        }
        for(int i = 0; i < this.fixedBill.getItems().size(); i++) {
            QuantifiableItem item = this.fixedBill.getItems().get(i);
            String text2 = null;
            try {
                text2 = String.format("%d. Item ID: %d\n    Name: %s\n    Category: %s\n    Price: $%.2f\n    Quantity: %d\n    Sub-Total: $%.2f\n",
                        i + 1, item.getItem().getID(), item.getItem().getName(), item.getItem().getCategory(),
                        item.getItem().getPrice(), item.getQuantity(), item.getPrice());
            } catch (ItemInBillNotExist e) {
                throw new RuntimeException(e);
            }
            pdfPrinter.addText(text2);
        }
        double price = this.fixedBill.getPrice();
        pdfPrinter.addText(String.format("Total: $%.2f\n", price));
        pdfPrinter.addText("-------------------------------");
        // wait for the PDF thread to finish
        try {
            pdfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
