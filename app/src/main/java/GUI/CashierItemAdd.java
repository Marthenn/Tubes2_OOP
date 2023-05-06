/*
 * Created by JFormDesigner on Fri May 05 05:56:14 ICT 2023
 */

package GUI;

import Core.Item.QuantifiableItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Fakih A
 */
public class CashierItemAdd extends JDialog {
    QuantifiableItem itemToBeAdd;
    int requestedItemQuantity;
    DefaultTableModel tableModel;
    public CashierItemAdd(QuantifiableItem itemToBeAdd, DefaultTableModel tableModel) {
        this.itemToBeAdd = itemToBeAdd;
        this.requestedItemQuantity = 1;

        this.tableModel = tableModel;


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("add new item");
        this.setLocationRelativeTo(null);

        initComponents();

        this.setVisible(true);
        this.setSize(new Dimension(800, 600));
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        itemDesc = new JLabel();
        saveItem = new JButton();
        quantityLabel = new JLabel();
        itemAmount = new JTextField();
        quantityAdd = new JButton();
        quantityReduce = new JButton();

        //======== this ========
        setTitle("Add Item");
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.2, 2.0, 0.0, 1.0, 1.0, 0.2};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0E-4};

        //---- itemDesc ----
        itemDesc.setText(this.itemToBeAdd.getName() + " - " + Double.toString(this.itemToBeAdd.getCost()));
        itemDesc.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(itemDesc, new GridBagConstraints(1, 0, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- saveItem ----
        saveItem.setText("save");
        contentPane.add(saveItem, new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- quantityLabel ----
        quantityLabel.setText("quantity");
        contentPane.add(quantityLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- itemAmount ----
        itemAmount.setText("1");
        contentPane.add(itemAmount, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- quantityAdd ----
        quantityAdd.setText("+");
        contentPane.add(quantityAdd, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- quantityReduce ----
        quantityReduce.setText("-");
        contentPane.add(quantityReduce, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[]{itemToBeAdd.getName(), requestedItemQuantity, requestedItemQuantity * itemToBeAdd.getCost()});

                // exit dialog
                dispose();
            }
        });

        quantityAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestedItemQuantity += 1;

                itemAmount.setText(Integer.toString(requestedItemQuantity));
            }
        });

        quantityReduce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestedItemQuantity -= 1;

                itemAmount.setText(Integer.toString(requestedItemQuantity));
            }
        });
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

//    void setTableModel(DefaultTableModel tableModel, )

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel itemDesc;
    private JButton saveItem;
    private JLabel quantityLabel;
    private JTextField itemAmount;
    private JButton quantityAdd;
    private JButton quantityReduce;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
