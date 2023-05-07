package Plugins;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.lang.reflect.*;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;
import GUI.Setting;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.swing.*;

public class Currency implements Plugin {
    private DataStore ds = DataStore.getInstance();
    private Map<String, Double> currencyRates = getCurrencyRates();
    @Getter @Setter @NonNull
    private String currentCurrency = "IDR";
    Thread currencyThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    currencyRates = getCurrencyRates();
                    System.out.println("Current Currency: " + currentCurrency);

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
                                    if (((JLabel) component).getText().equals("Currency")) {
                                        // get the JComboBox inside the JPanel
                                        for (Component component1 : ((JPanel) component.getParent()).getComponents()) {
                                            if (component1 instanceof JComboBox) {
                                                // get the selected item from the JComboBox
                                                String newCurrency = (String) ((JComboBox) component1).getSelectedItem();
                                                modifyCurrency(newCurrency);
                                                currentCurrency = newCurrency;
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

    public void modifyCurrency(String newCurrency) {
        if (newCurrency.equals(currentCurrency)) {
            return;
        }
        double rate = currencyRates.get(newCurrency) / currencyRates.get(currentCurrency);
        DataStore ds = DataStore.getInstance();
        for (QuantifiableItem item : ds.getItems()) {
//            System.out.println("Old: " + item.getSingularPrice());
            item.setSingularPrice(item.getSingularPrice() * rate);
//            System.out.println("New: " + item.getSingularPrice());
            item.setSingularCost(item.getSingularCost() * rate);
        }
        for (PremiumCustomer pc : ds.getPremiumCustomers()) {
            for (FixedBill fb : pc.getHistory()) {
                for (QuantifiableItem item : fb.getItems()) {
                    item.setSingularPrice(item.getSingularPrice() * rate);
                    item.setSingularCost(item.getSingularCost() * rate);
                }
            }
        }
        for (Customer c : ds.getCustomers()) {
            for (FixedBill fb : c.getHistory()) {
                for (QuantifiableItem item : fb.getItems()) {
                    item.setSingularPrice(item.getSingularPrice() * rate);
                    item.setSingularCost(item.getSingularCost() * rate);
                }
            }
        }
    }

    public void revertCurrency() {
        modifyCurrency("IDR");
    }

    public void load() {
        System.out.println("Loading Currency based insight.....");
        addToSetting("Currency", getItems());
        currencyThread.start();

        // make pop up
        JOptionPane.showMessageDialog(null, "Make sure to set the currency in the setting menu as the first on before exiting!");
    }

    public void unload() {
        System.out.println("Unloading Currency based insight.....");
        revertCurrency();
        currencyThread.interrupt();
        removeFromSetting("Currency");
    }

    public ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<String>();
        for (String currency : currencyRates.keySet()) {
            items.add(currency);
        }
        return items;
    }

    private Map<String, Double> getCurrencyRates(){
        // find the currency rates file
        Map<String, Double> currencyRates = new HashMap<String, Double>();
        currencyRates.put("IDR", 1.0);
        File currencyRatesFile = new File("currencyRates.txt");
        if(currencyRatesFile.exists()){
            // read each line, split with " " for key and value
            // put into the currencyRates map
            try {
                BufferedReader br = new BufferedReader (new FileReader(currencyRatesFile));
                String line = br.readLine();
                while (line != null) {
                    String[] currencyRate = line.split(" ");
                    currencyRates.put(currencyRate[0], Double.parseDouble(currencyRate[1]));
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try{
                currencyRatesFile.createNewFile();
                System.out.println("Currency rates file created");
                currencyRates.put("USD", 1/15000.0);
                currencyRates.put("EUR", 1/17000.0);
                currencyRates.put("SGD", 1/11000.0);

                // write all the currency rates to the file (FILE IO)
                FileOutputStream fos = new FileOutputStream(currencyRatesFile);
                for (Map.Entry<String, Double> entry : currencyRates.entrySet()) {
                    if (entry.getKey().equals("IDR")) {
                        continue;
                    }
                    String key = entry.getKey();
                    Double value = entry.getValue();
                    fos.write((key + " " + value + "\n").getBytes());
                }
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return currencyRates;
    }
}
