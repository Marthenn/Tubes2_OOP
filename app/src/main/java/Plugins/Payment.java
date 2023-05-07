package Plugins;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.Item.Bill.FixedBill.FixedBill;
import GUI.Setting;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Payment implements Plugin{
    ArrayList<FixedBill> bills = new ArrayList<>();
    private Double tax = 0.0;
    Thread paymentThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Current tax: " + tax);

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
                                                System.out.println("New Discount: " + tax);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ArrayList<FixedBill> fb = getBills();
                    // if fb is
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
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
        // make pop up
        JOptionPane.showMessageDialog(null, "Change the discount rate in the setting menu");
    }

    @Override
    public void unload() {
        System.out.println("Unloading Payment");
        paymentThread.interrupt();
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
