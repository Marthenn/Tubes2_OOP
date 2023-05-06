/*
 * Created by JFormDesigner on Sat May 06 16:07:30 WIB 2023
 */

package Plugins;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import Core.DataStore.DataStore;
import Core.Item.Bill.FixedBill.*;
import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.Item.QuantifiableItem;

/**
 * @author Marthen
 */
public class PieInsightGUI extends JPanel {

    private DataStore ds = DataStore.getInstance();

    public PieInsightGUI() {
        initComponents();
        Thread updateData = new Thread (() -> {
        });
    }

    // return dari method ini adalah map id dengan jumlah item yang terjual
    private Map<Integer, Integer> getSoldItems(){
        Map<Integer, Integer> soldItems = new HashMap<>();
        for (Customer cs : ds.getCustomers()){
            for (FixedBill fb : cs.getHistory()){
                for (QuantifiableItem qi : fb.getItems()) {
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        label1 = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
        .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
        .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
        Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
        )))throw new RuntimeException();}});
        setLayout(null);

        //---- label1 ----
        label1.setText("INI PIE CHARTNYA NANTI");
        add(label1);
        label1.setBounds(new Rectangle(new Point(345, 260), label1.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
