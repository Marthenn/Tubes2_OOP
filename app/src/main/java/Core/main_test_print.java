package Core;

import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;

import java.util.ArrayList;

public class main_test_print {
    public static void main(String[] args) throws SearchedItemNotExist {
        DataStore dataStore = DataStore.getInstance();
        ArrayList<Customer> customers = dataStore.getCustomers();
        FullReportPrinter printer = new FullReportPrinter("full_report.pdf");
        printer.printFullReport();
    }
}
