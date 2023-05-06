import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import GUI.MainMenu;
import Core.Customer.Customer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.FixedBillPrinter;
import Core.FullReportPrinter;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.DiscountFixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FractionFixedBillModifier;
import Core.Item.Item;
import Core.Item.QuantifiableItem;

import java.util.ArrayList;
import javax.swing.*;

import GUI.*;

public class Main {
    public static void main(String[] args) throws ItemInBillNotExist, SearchedItemNotExist, CustomerNotExistException, PromotedCustomerAlreadyExist {
        ArrayList<QuantifiableItem> items = new ArrayList<QuantifiableItem>();
        items.add(new QuantifiableItem(new Item(1, "bakso", (double)10000, (double)5000, "makanan", null, false)));
        items.add(new QuantifiableItem(new Item(2, "ayam", (double)1000, (double)7000, "hewan", null, false)));
        ArrayList<FixedBillModifier> mofifiers = new ArrayList<FixedBillModifier>();
        mofifiers.add(new DiscountFixedBillModifier("discount!", 1));
        mofifiers.add(new FractionFixedBillModifier("fraction!", 2));
        for (int i = 0; i < 3; i++){
            FixedBill fb = new FixedBill(0, items, mofifiers);
            Customer cust = DataStore.getInstance().createNewCustomer();
            PremiumCustomer cust1 = DataStore.getInstance().promoteCustomer(cust.getID(), "aku premium","08123425", "premium@gmail.com", MembershipStateName.VIP);
            cust1.addFixedBill(fb);
            FixedBillPrinter fbp = new FixedBillPrinter("fixedbill" + i + ".pdf", cust1.getID(), 0);
            fbp.printFixedBill();
        }
        FullReportPrinter frp = new FullReportPrinter("report.pdf");
        frp.printFullReport();
        System.out.println("Hello World! GUI STARTED!");
        MainMenu mainMenu = MainMenu.getInstance();
        JFrame frame = new JFrame("ObjectEnjoyer");
        frame.setContentPane(mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
