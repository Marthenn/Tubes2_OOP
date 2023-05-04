/*
 * Created by JFormDesigner on Mon Apr 24 01:03:02 WIB 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;

public class Cashier extends JPanel {
    public Cashier() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        title = new JLabel();
        browsePane = new JScrollPane();
        browseList = new JList();
        searchText = new JTextField();
        searchButton = new JButton();
        billTabPane = new JTabbedPane();
        billDetailPane = new JScrollPane();
        billItemList = new JList();
        subtotalTitle = new JLabel();
        subtotalAmount = new JLabel();
        saveBill = new JButton();
        printBill = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //---- title ----
        title.setText("Cashier");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 4f));

        //======== browsePane ========
        {

            //---- browseList ----
            browseList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            browsePane.setViewportView(browseList);
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
                                    .addComponent(browsePane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(searchText, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addContainerGap()
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton))
                    .addGap(18, 18, 18)
                    .addComponent(browsePane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addContainerGap(136, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addComponent(billTabPane, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(printBill)
                        .addComponent(saveBill))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(subtotalTitle)
                        .addComponent(subtotalAmount))
                    .addGap(0, 124, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel title;
    private JScrollPane browsePane;
    private JList browseList;
    private JTextField searchText;
    private JButton searchButton;
    private JTabbedPane billTabPane;
    private JScrollPane billDetailPane;
    private JList billItemList;
    private JLabel subtotalTitle;
    private JLabel subtotalAmount;
    private JButton saveBill;
    private JButton printBill;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
