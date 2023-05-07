package Plugins;

import GUI.Cashier;
import GUI.MainMenu;
import GUI.Setting;
import Plugins.Plugin;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Payment implements Plugin {

    final Double tax = 0.1;
    final Double serviceCharge = 0.05;
    private Double discount = 0.0;
    Thread paymentThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Current Discount: " + discount);

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
                                                String newDiscount = (String) ((JComboBox) component1).getSelectedItem();
                                                discount = Double.parseDouble(newDiscount);
                                                System.out.println("New Discount: " + discount);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

    @Override
    public void load() {
        System.out.println("Loading Payment");
        addToSetting("Payment", getItems());
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
}
