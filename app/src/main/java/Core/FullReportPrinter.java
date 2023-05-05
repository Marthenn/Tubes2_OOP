package Core;

import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.QuantifiableItem;

import java.util.ArrayList;

public class FullReportPrinter {
    private ArrayList<Customer> customers;
    private String filename;

    public FullReportPrinter(String filename) throws SearchedItemNotExist {
        DataStore dataStore = DataStore.getInstance();
        this.customers = dataStore.getCustomers();
        this.filename = filename;
    }
    public void printFullReport() {
        PDFPrinter pdfPrinter = new PDFPrinter(this.filename);
        Thread pdfThread = new Thread(pdfPrinter);
        pdfThread.start();
        pdfPrinter.addText("Full Report");
        for(int i = 0; i < this.customers.size(); i++) {
            pdfPrinter.addText((i + 1) + "Customer ID:" + this.customers.get(i).getID());
            for(int j = 0; j < this.customers.get(i).getHistory().size(); j++){
                QuantifiableItem item = this.customers.get(j).getHistory().get(j).getItems().get(i);
                String text2 = String.format("%d. Item ID: %d, Name: %s, Category: %s, Cost: $%.2f, OriginalPrice: $%.2f",
                        j + 1, item.getItem().getID(), item.getItem().getName(), item.getItem().getCategory(),
                        item.getItem().getCost(), item.getItem().getCost());
                pdfPrinter.addText(text2);
            }
        }
        // wait for the PDF thread to finish
        try {
            pdfThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

