package Core.Printer;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import com.itextpdf.text.PageSize;

import java.util.ArrayList;
import java.util.Date;

public class FullReportPrinter {
    private ArrayList<Customer> customers;
    private ArrayList<PremiumCustomer> premiumCustomers;
    private String filename;
    private Double totalProfit = 0.0;

    public FullReportPrinter(String filename) throws SearchedItemNotExist {
        DataStore dataStore = DataStore.getInstance();
        this.customers = dataStore.getCustomers();
        this.premiumCustomers = dataStore.getPremiumCustomers();
        this.filename = filename;
    }
    private void printFormat(PDFPrinter pdfPrinter, ArrayList<? extends Customer> arrCust) throws ItemInBillNotExist {
        for(int i = 0; i < arrCust.size(); i++) {
            pdfPrinter.addText((i + 1) + ". Customer ID:" + arrCust.get(i).getID());
            if(arrCust.get(i) instanceof PremiumCustomer customer1){
                String text1 = String.format("   Customer Name: %s\n   Email: %s\n   Phone Number: %s\n   Membership Status: %s\n   Point(s): %d",
                        customer1.getName(), customer1.getEmail(), customer1.getPhoneNumber(),
                        customer1.getStatus().getName(), customer1.getPoint());
                pdfPrinter.addText(text1);
            }
            for(int j = 0; j < arrCust.get(i).getHistory().size(); j++){
                pdfPrinter.addText("   FixedBill " + (j + 1));
                pdfPrinter.addText("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                for(int k = 0; k < arrCust.get(i).getHistory().get(j).getItems().size(); k++) {
                    QuantifiableItem item = arrCust.get(i).getHistory().get(j).getItems().get(k);
                    String text2 = String.format("   %d. Item ID: %d\n       Name: %s\n       Category: %s\n       Price: $%.2f\n       Quantity: %d\n       Sub-Total: $%.2f\n",
                            k + 1, item.getItem().getID(), item.getItem().getName(), item.getItem().getCategory(),
                            item.getItem().getPrice(), item.getQuantity(), item.getPrice());
                    pdfPrinter.addText(text2);
                }
                ArrayList<FixedBillModifier> modifiers = arrCust.get(i).getHistory().get(j).getModifiers();
                for(int k = 0; k < arrCust.get(i).getHistory().get(j).getModifiers().size(); k++){
                    String textModifier = "   " + arrCust.get(i).getHistory().get(j).getModifiers().get(k).getModifierText();
                    pdfPrinter.addText(textModifier);
                }
                double price = arrCust.get(i).getHistory().get(j).getPrice();
                double profit = arrCust.get(i).getHistory().get(j).getProfit();
                totalProfit += profit;
                String text3 = String.format("   Total: $%.2f", price);
                String text4 = String.format("   Profit: $%.2f", profit);
                pdfPrinter.addText(text3);
                pdfPrinter.addText(text4);
                pdfPrinter.addText("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n");
            }
        }
    }
    public void printFullReport() {
        PDFPrinter pdfPrinter = new PDFPrinter(this.filename, PageSize.A4.getWidth(), PageSize.A4.getHeight());
        Thread pdfThread = new Thread(pdfPrinter);
        pdfThread.start();
        pdfPrinter.addText("Full Sales Report");
        pdfPrinter.addText("---------------------------------------------------\n");
        pdfPrinter.addText("Printed On: " + new Date());
        pdfPrinter.addText("---------------------------------------------------\n\n");
        try {
            pdfPrinter.addText("Regular Customer");
            pdfPrinter.addText("-----------------\n");
            printFormat(pdfPrinter, this.customers);
            pdfPrinter.addText("Member & VIP");
            pdfPrinter.addText("-----------------\n");
            printFormat(pdfPrinter, this.premiumCustomers);
        } catch (ItemInBillNotExist e) {
            throw new RuntimeException(e);
        }
        pdfPrinter.addText("\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
        pdfPrinter.addText(String.format("Total Profit: $%.2f", totalProfit));
        pdfPrinter.addText("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        // wait for the PDF thread to finish
        try {
            pdfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

