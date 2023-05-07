/*
 * Created by JFormDesigner on Sat May 06 16:07:30 WIB 2023
 */

package Plugins.PieInsight;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.QuantifiableItem;
import Plugins.PieInsight.PieChart;

/**
 * @author Marthen
 */
public class PieInsightGUI extends JPanel {

    private DataStore ds = DataStore.getInstance();
    private Map<Integer, Integer> items = new HashMap<>();

    public PieInsightGUI() {
        initComponents();
        Thread updateData = new Thread (() -> {
//            System.out.println("PieInsightGUI: updateData thread started");
            while (true) {
                try {
                    Map<Integer,Integer> soldItems = PieInsight.getSoldItems();
                    if (soldItems.equals(items)) {
//                        System.out.println("PieInsightGUI: chart data is equal to soldItems");
                        continue;
                    } else {
//                        System.out.println("Looping soldItems:");
                        chart.clear();
                        for (Map.Entry<Integer, Integer> entry : soldItems.entrySet()) {
                            Integer key = entry.getKey();
                            Integer value = entry.getValue();
//                            System.out.println("Key: " + key + " Value: " + value);
//                            System.out.println(ds.getItems().size());
//                            for (QuantifiableItem item : ds.getItems()) {
//                                System.out.println("Item: " + item.getName() + " ID: " + item.getID());
//                            }
                            chart.addSlice(value, ds.getItemWithID(key).getName());
                        }
                        items = soldItems;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SearchedItemNotExist searchedItemNotExist) {
                    searchedItemNotExist.printStackTrace();
                }
                repaint();
            }
        });
        updateData.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        chart = new PieChart();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new BorderLayout());
        add(chart, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private PieChart chart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
