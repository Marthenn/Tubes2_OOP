package Plugins;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.DiscountFixedBillModifier;
import Core.Item.Bill.FixedBill.FixedBillModifier.FractionFixedBillModifier;
import GUI.Cashier;
import GUI.Setting;
import Plugins.Plugin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Payment implements Plugin {
    ArrayList<FixedBill> bills = getBills();
    private Double tax = 0.0;
    Thread paymentThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
//                    System.out.println("Current tax: " + tax);

                    // read the currency chosen from the setting dropdown
                    Setting setting = Setting.getInstance();
                    Field pluginField = findField(Setting.class, "viewportPanel");
                    pluginField.setAccessible(true);

                    // get the JPanel inside the viewportPanel with the JLabel name "Currency"
                    for (Component componentOuter : ((JPanel) pluginField.get(setting)).getComponents()) {
//                        System.out.println(componentOuter.getClass().getName());
                        if (componentOuter instanceof JPanel) {
                            for (Component component : ((JPanel) componentOuter).getComponents()) {
//                                System.out.println(component.getClass().getName());
                                if (component instanceof JLabel) {
                                    if (((JLabel) component).getText().equals("Payment")) {
                                        // get the JComboBox inside the JPanel
                                        for (Component component1 : ((JPanel) component.getParent()).getComponents()) {
                                            if (component1 instanceof JComboBox) {
                                                // get the selected item from the JComboBox
                                                String newTax = (String) ((JComboBox) component1).getSelectedItem();
                                                tax = Double.parseDouble(newTax);
                                                writeTax(tax);
//                                                System.out.println("New Tax: " + tax);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
//                    System.out.println("Current tax: " + tax);
//                    System.out.println("Bills size: " + bills.size());
//                    for (FixedBill x : bills){
//                        System.out.println(x.getID()+" : "+ x.getPrice());
//                    }
                } catch (InterruptedException e) {
                    // do nothing
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });
    Thread discountThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    ArrayList<FixedBill> fb = getBills();
                    // if there's new bill then
                    for (FixedBill x : fb){
                        // if x not in bills
                        if (!bills.contains(x)){
                            Thread.sleep(500);
                            x.addFixedBillModifier(new FractionFixedBillModifier("Tax and Service: "+(tax*100)+"%", 1+tax));
                            // JPopup to insert discount rate
                            Double d = -1.0;
                            while (d < 0 || d > 100){
                                String s = JOptionPane.showInputDialog("Insert discount rate (0-100)");
                                d = Double.parseDouble(s);
                            }
                            x.addFixedBillModifier(new DiscountFixedBillModifier("Discount: "+ d + "%", d/100));
                            bills.add(x);
                        }
                    }
                    bills = fb; // safety measure
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

    private ArrayList<FixedBill> getBills(){
        ArrayList<FixedBill> fb = new ArrayList<>();
        for (Customer c : DataStore.getInstance().getCustomers()){
            for (FixedBill b : c.getHistory()){
                fb.add(b);
            }
        }
        for (PremiumCustomer c : DataStore.getInstance().getPremiumCustomers()){
            for (FixedBill b : c.getHistory()){
                fb.add(b);
            }
        }
        return fb;
    }

    @Override
    public void load() {
        System.out.println("Loading Payment");
        initialize();
        addToSetting("Payment", getItems());

        // set the dropdown
        Setting setting = Setting.getInstance();
        Field pluginField = findField(Setting.class, "viewportPanel");
        pluginField.setAccessible(true);
        try{
            for (Component componentOuter : ((JPanel) pluginField.get(setting)).getComponents()) {
                if (componentOuter instanceof JPanel) {
                    for (Component component : ((JPanel) componentOuter).getComponents()) {
                        if (component instanceof JLabel) {
                            if (((JLabel) component).getText().equals("Payment")) {
                                // get the JComboBox inside the JPanel
                                for (Component component1 : ((JPanel) component.getParent()).getComponents()) {
                                    if (component1 instanceof JComboBox) {
                                        // set the selected item from the JComboBox
                                        ((JComboBox) component1).setSelectedItem(tax.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        paymentThread.start();
        discountThread.start();
        // make pop up
        JOptionPane.showMessageDialog(null, "Change the tax and service charge rate in the setting menu (accumulative)");
    }

    @Override
    public void unload() {
        System.out.println("Unloading Payment");
        paymentThread.interrupt();
        discountThread.interrupt();
        removeFromSetting("Payment");
    }

    @Override
    public ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<>();
        for (Double d = 0.0; d <= 1.0; d += 0.05) {
            d = Math.round(d * 100.0) / 100.0;
            items.add(d.toString());
        }
        return items;
    }

    private void writeTax(Double tax){
        File file = new File("tax.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(tax.toString().getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initialize(){
        File file = new File("tax.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("0.0".getBytes());
                fos.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                byte[] bytes = new byte[100];
                FileInputStream fis = new FileInputStream(file);
                fis.read(bytes);
                fis.close();
                String s = new String(bytes);
                tax = Double.parseDouble(s);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
