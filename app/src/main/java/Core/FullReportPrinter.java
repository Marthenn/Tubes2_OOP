package Core;

import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.QuantifiableItem;
import com.itextpdf.text.PageSize;

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
        PDFPrinter pdfPrinter = new PDFPrinter(this.filename, PageSize.A4.getWidth(), PageSize.A4.getHeight());
        Thread pdfThread = new Thread(pdfPrinter);
        pdfThread.start();
        pdfPrinter.addText("Full Report");
        pdfPrinter.addText("-----------\n\n");
        for(int i = 0; i < this.customers.size(); i++) {
            pdfPrinter.addText((i + 1) + ". Customer ID:" + this.customers.get(i).getID());
            for(int j = 0; j < this.customers.get(i).getHistory().size(); j++){
                pdfPrinter.addText("   FixedBill " + (j + 1));
                for(int k = 0; k < this.customers.get(i).getHistory().get(j).getItems().size(); k++) {
                    QuantifiableItem item = this.customers.get(i).getHistory().get(j).getItems().get(k);
                    String text2 = String.format("   %d. Item ID: %d\n    Name: %s\n    Category: %s\n    Cost: $%.2f\n",
                            k + 1, item.getItem().getID(), item.getItem().getName(), item.getItem().getCategory(),
                            item.getItem().getCost());
                    pdfPrinter.addText(text2);
                }
                double cost = 0;
                try {
                    cost = this.customers.get(i).getHistory().get(j).getCost();
                } catch (ItemInBillNotExist e) {
                    throw new RuntimeException(e);
                }
                String text3 = Double.toString(cost);
                pdfPrinter.addText("   Total: " + text3 + "\n\n");
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

