package Plugins.PieInsight;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.QuantifiableItem;
import GUI.MainMenu;
import Plugins.Plugin;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieInsight implements Plugin {
    private MainMenu mainMenu = MainMenu.getInstance();
    private static DataStore ds = DataStore.getInstance();
    private static JMenuItem pieChart = new JMenuItem("PieChart");

    public void load() {
        System.out.println("Loading PieChart based insight");

        // add PieChart menuItem
        Field menuBarField = findField(MainMenu.class, "menuBar");
        menuBarField.setAccessible(true);
        Field tabbedPaneField = findField(MainMenu.class, "tabbedPane");
        tabbedPaneField.setAccessible(true);
        JMenuBar menuBar = null;
        try {
            menuBar = (JMenuBar) menuBarField.get(mainMenu);
            pieChart.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    if(tabbedPaneField != null){
                        JTabbedPane tabbedPane = null;
                        try {
                            tabbedPane = (JTabbedPane) tabbedPaneField.get(mainMenu);
                            if(tabbedPane.indexOfTab("PieChart") == -1){
                                tabbedPane.addTab("PieChart", new PieInsightGUI());
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            // add PieChart menuItem at the second from last index
            menuBar.add(pieChart, menuBar.getMenuCount() - 1);
            addToSetting("PieChart", null);
            System.out.println("Successfully added PieChart menuItem");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void unload() {
        System.out.println("Unloading PieChart based insight");
        Field menuBarField = findField(MainMenu.class, "menuBar");
        menuBarField.setAccessible(true);
        JMenuBar menuBar = null;
        try {
            menuBar = (JMenuBar) menuBarField.get(mainMenu);
            menuBar.remove(pieChart);
            System.out.println("Successfully removed PieChart menuItem");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        removeFromSetting("PieChart");
    }

    public ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("PieChart");
        return items;
    }

    public static Map<Integer, Integer> getSoldItems(){
        Map<Integer, Integer> soldItems = new HashMap<>();
        for (Customer cs : ds.getCustomers()){
            for (FixedBill fb : cs.getHistory()){
                for (QuantifiableItem qi : fb.getItems()) {
                    System.out.println(qi.getID() + " " + qi.getQuantity());
                    if (soldItems.containsKey(qi.getID())) {
                        soldItems.put(qi.getID(), soldItems.get(qi.getID()) + qi.getQuantity());
                    } else {
                        soldItems.put(qi.getID(), qi.getQuantity());
                    }
                }
            }
        }
        for (PremiumCustomer pc : ds.getPremiumCustomers()){
            for (FixedBill fb : pc.getHistory()){
                for (QuantifiableItem qi : fb.getItems()) {
                    if (soldItems.containsKey(qi.getID())) {
                        soldItems.put(qi.getID(), soldItems.get(qi.getID()) + qi.getQuantity());
                    } else {
                        soldItems.put(qi.getID(), qi.getQuantity());
                    }
                }
            }
        }
        return soldItems;
    }
}
