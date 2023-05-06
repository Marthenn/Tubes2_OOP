/*
 * Created by JFormDesigner on Mon Apr 24 01:03:02 WIB 2023
 */

package GUI;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.QuantifiableItem;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Cashier extends JPanel {
    public Cashier() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        //// DEBUG DATA
        try {
            DataStore.getInstance().addNewItem("makan", 2.3, 2.4, "ayam", 3, "");
            DataStore.getInstance().addNewItem("tidak", 2.3, 2.4, "bebek", 4, "");
        } catch (ItemWithIDAlreadyExist e) {
            throw new RuntimeException(e);
        } catch (NegativeQuantityException e) {
            throw new RuntimeException(e);
        }
        //// DEBUG DATA


        ArrayList<QuantifiableItem> browseObjects = DataStore.getInstance().getItems();
        Object[][] browseObjectItemPool = new Object[browseObjects.size()][3];
        for(int i = 0; i < browseObjects.size(); i++) {
            browseObjectItemPool[i] = new Object[]{ browseObjects.get(i).getName(),
                                                    browseObjects.get(i).getCategory(),
                                                    Double.toString(browseObjects.get(i).getCost())
                                                    };
        }

//        Object[][] browseObjectDisplayPool = new Object[browseObjectItemPool.length][3];
//        for (int i = 0; i < browseObjectItemPool.length; i++) {
//            browseObjectDisplayPool[i] = new Object[]{  browseObjectItemPool[i][0],
//                                                        browseObjectItemPool[i][1],
//                                                        browseObjectItemPool[i][2]
//                                                      };
//        }

        DefaultTableModel browseListTableModel = new DefaultTableModel();
        browseListTableModel.addColumn("Nama");
        browseListTableModel.addColumn("Kategori");
        browseListTableModel.addColumn("Harga");
        for(int i = 0; i < browseObjectItemPool.length; i++) {
            browseListTableModel.addRow(browseObjectItemPool[i]);
        }

        DefaultTableModel billItemTableModel = new DefaultTableModel();
        billItemTableModel.addColumn("nama");
        billItemTableModel.addColumn("quantity");
        billItemTableModel.addColumn("subtotal");

        title = new JLabel();
        browsePane = new JScrollPane();
        browseTable = new JTable(browseListTableModel);
        searchText = new JTextField();
        searchButton = new JButton();
        billTabPane = new JTabbedPane();
        billDetailPane = new JScrollPane();
        billItemTable = new JTable(billItemTableModel);
        subtotalTitle = new JLabel();
        subtotalAmount = new JLabel();
        saveBill = new JButton();
        printBill = new JButton();
        addItem = new JButton();



        //---- title ----
        title.setText("Cashier");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 4f));

        //======== browsePane ========
        {

            //---- browseList ----
            browseTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            browsePane.setViewportView(browseTable);
        }

        //---- searchText ----
        searchText.setMargin(new Insets(0, 0, 0, 0));

        //---- searchButton ----
        searchButton.setText("search");

        //======== billTabPane ========
        {

            //======== billDetailPane ========
            {

                //---- billItemList ----
                billItemTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                billDetailPane.setViewportView(billItemTable);
            }
            billTabPane.addTab("text", billDetailPane);
        }

        //---- subtotalTitle ----
        subtotalTitle.setText("subtotal");

        //---- subtotalAmount ----
        subtotalAmount.setText("0");

        //---- saveBill ----
        saveBill.setText("save");

        //---- printBill ----
        printBill.setText("print");

        addItem.setText("add");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(searchText, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(browsePane, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                                                                        .addComponent(addItem, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(billTabPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(saveBill, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(printBill, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(subtotalTitle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(subtotalAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchButton))
                                                .addGap(18, 18, 18)
                                                .addComponent(browsePane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(86, 86, 86)
                                                .addComponent(billTabPane, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(printBill)
                                                        .addComponent(saveBill))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(subtotalTitle)
                                                        .addComponent(subtotalAmount))
                                                .addGap(0, 124, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addItem)
                                                .addContainerGap(110, Short.MAX_VALUE))))
        );



        // Selecting Browsable Object
        browseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel browseTableSM = browseTable.getSelectionModel();
        browseTableSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();
                if(!listSelectionModel.isSelectionEmpty()) {
                    setSelectedBrowseObject(browseObjects.get(listSelectionModel.getMinSelectionIndex()));
                }
            }
        });

        // Search Box
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Object[] _temp = Arrays.stream(browseObjectItemPool).filter(item ->
                        item[0].toString().contains(searchText.getText()) ||
                        item[1].toString().contains(searchText.getText()) ||
                        item[2].toString().contains(searchText.getText())
                ).toArray();
                Object[][] newValue = new Object[_temp.length][3];
                for(int i = 0; i < _temp.length; i++) {
                    newValue[i] = new Object[]{((Object[]) _temp[i])[0], ((Object[]) _temp[i])[1], ((Object[]) _temp[i])[2]};
                }

                setTableModelContent(browseListTableModel, newValue);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Object[] _temp = Arrays.stream(browseObjectItemPool).filter(item ->
                        item[0].toString().contains(searchText.getText()) ||
                                item[1].toString().contains(searchText.getText()) ||
                                item[2].toString().contains(searchText.getText())
                ).toArray();
                Object[][] newValue = new Object[_temp.length][3];
                for(int i = 0; i < _temp.length; i++) {
                    newValue[i] = new Object[]{((Object[]) _temp[i])[0], ((Object[]) _temp[i])[1], ((Object[]) _temp[i])[2]};
                }

                setTableModelContent(browseListTableModel, newValue);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //?
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (browseTableSM.isSelectionEmpty()) return;
                CashierItemAdd cashierItemAddDialog = new CashierItemAdd(selectedSoldItem, billItemTableModel);
            }
        });




        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    QuantifiableItem selectedSoldItem;
    void setSelectedBrowseObject(QuantifiableItem soldItem) {
        selectedSoldItem = soldItem;
    }

    void setTableModelContent(DefaultTableModel tableModel, Object[][] newValue){
//        for (int i = 0; i < tableModel.getRowCount(); i++){
//            tableModel.removeRow(0);
//        }
        tableModel.setRowCount(0);

        for (int i = 0; i < newValue.length; i++){
            tableModel.addRow(new Object[]{newValue[i][0], newValue[i][1], newValue[i][2]});
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel title;
    private JScrollPane browsePane;
    private JTable browseTable;
    private JTextField searchText;
    private JButton searchButton;
    private JTabbedPane billTabPane;
    private JScrollPane billDetailPane;
    private JTable billItemTable;
    private JLabel subtotalTitle;
    private JLabel subtotalAmount;
    private JButton saveBill;
    private JButton printBill;
    private JButton addItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
