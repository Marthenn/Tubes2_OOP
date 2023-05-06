/*
 * Created by JFormDesigner on Mon Apr 24 01:03:02 WIB 2023
 */

package GUI;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.IDAble.IDAbleListener;
import Core.Item.Exception.NegativeQuantityException;
import Core.Item.QuantifiableItem;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Cashier extends JPanel implements IDAbleListener<QuantifiableItem> {

    private ArrayList<BillDisplay> currentActiveBillDisplays = new ArrayList<>();
    ArrayList<QuantifiableItem> browseObjects = new ArrayList<>();
    DefaultTableModel browseListTableModel = new DefaultTableModel();
    public Cashier() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        // add this to listener list
        DataStore.getInstance().listenToItem(this);

        //// DEBUG DATA


        //TODO : DATA PERSISTENCE AND NON_STATIC DATA FETCHING
        // FIRST TIME FETCH
         browseObjects = DataStore.getInstance().getItems();

        title = new JLabel();
        browsePane = new JScrollPane();
        browseTable = new JTable();
        searchText = new JTextField();
        searchButton = new JButton();
        billTabPane = new JTabbedPane();
        createNewBillTab(); // Bill1
        createNewBillTab(); // +
        subtotalTitle = new JLabel();
        subtotalAmount = new JLabel();
        saveBill = new JButton();
        printBill = new JButton();
        addItem = new JButton();

        // browsed Items Table Model
        browseListTableModel.addColumn("Nama");
        browseListTableModel.addColumn("Kategori");
        browseListTableModel.addColumn("Harga");

        updateBrowseTableModel();

        browseTable.setModel(browseListTableModel);



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
//            {

                //---- billItemList ----
//                billItemTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//                billDetailPane.setViewportView(billItemTable);
//            }
            //Bill 1
            billTabPane.addTab("Bill 1", currentActiveBillDisplays.get(0));
            //+
            billTabPane.addTab("+", currentActiveBillDisplays.get(1));
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
                    setSelectedBrowseObject(browseObjects
                                            .stream()
                                            .filter(qItem -> qItem.getName().contains(searchText.getText()) ||
                                                    qItem.getCategory().contains(searchText.getText()) ||
                                                    Double.toString(qItem.getSingularPrice()).contains(searchText.getText()))
                                            .collect(Collectors.toCollection(ArrayList::new))
                                                .get(listSelectionModel.getMinSelectionIndex()));
                }
            }
        });

        // Search Box
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateBrowseTableModel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateBrowseTableModel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // this will only trigger when font, size, and other non-text attribute changed
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (browseTableSM.isSelectionEmpty()) return;
                CashierItemAdd cashierItemAddDialog = new CashierItemAdd(selectedSoldItem,
                                                        currentActiveBillDisplays
                                                        .get(billTabPane.getSelectedIndex())
                                                    );
            }
        });

        billTabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    if (pane.getTitleAt(pane.getSelectedIndex()).equals("+")) {
                        pane.setTitleAt(pane.getSelectedIndex(), "Bill");
                        pane.add("+", createNewBillTab());
                    }
                }
            }
        });

        saveBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseObjects.get(0).setName(browseObjects.get(0).getName().concat(browseObjects.get(0).getName().substring(1)));
            }
        });

        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    QuantifiableItem selectedSoldItem;
    void setSelectedBrowseObject(QuantifiableItem soldItem) {
        selectedSoldItem = soldItem;
    }

    void updateBrowseTableModel(){

        String filterText = searchText.getText() == null ? "" : searchText.getText();

        // Empty tableModel
        browseListTableModel.setRowCount(0);

        // Filter tableModel
        ArrayList<QuantifiableItem> filteredBrowseObjects =
                browseObjects
                .stream()
                .filter(qItem ->    qItem.getName().contains(filterText) ||
                                    qItem.getCategory().contains(filterText) ||
                                    Double.toString(qItem.getSingularPrice()).contains(filterText))
                .collect(Collectors.toCollection(ArrayList::new));

        // Update tableModel
        for (QuantifiableItem qItem : filteredBrowseObjects) {
            browseListTableModel.addRow(new String[]{qItem.getName(), qItem.getCategory(), Double.toString(qItem.getSingularPrice())});
        }
    }

    BillDisplay createNewBillTab() {
        currentActiveBillDisplays.add(new BillDisplay());

        // return latest created BillDisplay
        return currentActiveBillDisplays.get(currentActiveBillDisplays.size() - 1);
    }

    public void onItemWithIDChange(QuantifiableItem item) {
        // NOTE : MIGHT CHANGE INTO MORE OPTIMIZED SOLUTION
        browseObjects = DataStore.getInstance().getItems();
        updateBrowseTableModel();

        System.out.println("TRIGGERED OBSERVER");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel title;
    private JScrollPane browsePane;
    private JTable browseTable;
    private JTextField searchText;
    private JButton searchButton;
    private JTabbedPane billTabPane;
//    private JScrollPane billDetailPane;
//    private JTable billItemTable;
    private JLabel subtotalTitle;
    private JLabel subtotalAmount;
    private JButton saveBill;
    private JButton printBill;
    private JButton addItem;

    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
