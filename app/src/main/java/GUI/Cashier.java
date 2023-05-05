/*
 * Created by JFormDesigner on Mon Apr 24 01:03:02 WIB 2023
 */

package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class BrowseObject {
    String name;
    String category;
    int price;
    public BrowseObject(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public BrowseObject(String[] data) {
        this.name = data[0];
        this.category = data[1];
        this.price = Integer.parseInt(data[2]);
    }

    public String[] getText() {
        return new String[] {this.name, this.category, Integer.toString(this.price)};
    }
}

public class Cashier extends JPanel {
    public Cashier() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        String[][] dummyData = new String[][] {
                {"fafakjdlkjfalkj", "makanaan", "100000"},
                {"fairvielad", "makanan", "9911"},
                {"aqua", "minuman", "10000"},
                {"doesitsowell", "sayyou", "90"}
        };

        ArrayList<BrowseObject> browseObjects = new ArrayList();

        for(int i = 0; i < dummyData.length; i++) {
            browseObjects.add(new BrowseObject(dummyData[i]));
        }

        DefaultListModel<String> billItemListModel = new DefaultListModel();


        title = new JLabel();
        browsePane = new JScrollPane();
        browseTable = new JTable(dummyData, new String[]{"Nama", "Kategori", "Harga"});
        searchText = new JTextField();
        searchButton = new JButton();
        billTabPane = new JTabbedPane();
        billDetailPane = new JScrollPane();
        billItemList = new JList(billItemListModel);
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
                billItemList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                billDetailPane.setViewportView(billItemList);
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


        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (browseTableSM.isSelectionEmpty()) return;

                CashierItemAdd cashierItemAddDialog = new CashierItemAdd(selectedBrowseObject.name, selectedBrowseObject.price, billItemListModel);


            }
        });




        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    BrowseObject selectedBrowseObject;
    void setSelectedBrowseObject(BrowseObject obj) {
        selectedBrowseObject = obj;
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
    private JList billItemList;
    private JLabel subtotalTitle;
    private JLabel subtotalAmount;
    private JButton saveBill;
    private JButton printBill;
    private JButton addItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
