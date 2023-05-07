/*
 * Created by JFormDesigner on Sat May 06 14:53:39 ICT 2023
 */

package GUI;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.DataStore.DataStore;
import Core.DataStore.Exception.CustomerNotExistException;
import Core.DataStore.Exception.PromotedCustomerAlreadyExist;
import Core.DataStore.StorerData.StorerDataListener;
import Core.FixedBillPrinter;
import Core.Item.Bill.Bill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Fakih A
 */
public class CashierCheckout extends JPanel implements StorerDataListener {
    JTabbedPane parentTabbedPane;
    Cashier parentCashier;
    Bill billToBeCheckedOut; // add listeners
    DefaultTableModel tabelDetailModel = new DefaultTableModel();
    ArrayList<FixedBillModifier> fixedBillModifiers = new ArrayList<>();
    public CashierCheckout(JTabbedPane parentTabbedPane, Cashier parentCashier, Bill billToBeCheckedOut) {
        this.parentTabbedPane = parentTabbedPane;
        this.parentCashier = parentCashier;
        this.billToBeCheckedOut  = billToBeCheckedOut;

        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        // listener init
        DataStore.getInstance().listenToCustomerStore(this);

        // DEBUG DATA //
        try {
            DataStore.getInstance().promoteCustomer(DataStore.getInstance().createNewCustomer().getID(), "lmao", "123456789012", "asd@gmail.com", MembershipStateName.VIP );
            DataStore.getInstance().promoteCustomer(DataStore.getInstance().createNewCustomer().getID(), "likalat", "123456789012", "asd@gmail.com", MembershipStateName.MEMBER );
        } catch (CustomerNotExistException e) {
            throw new RuntimeException(e);
        } catch (PromotedCustomerAlreadyExist e) {
            throw new RuntimeException(e);
        }
        // DEBUG DATA //

        Title = new JLabel();
        DaftarPembelian = new JScrollPane();
        TabelPembelian = new JTable();
        LabelCustomer = new JLabel();
        NamaCustomer = new CashierComboBox(DataStore.getInstance().getPremiumCustomers());
        DaftarDetail = new JScrollPane();
        TabelDetail = new JTable();
        LabelTotalPembelian = new JLabel();
        NilaiTotalPembelian = new JLabel();
        Checkout = new JButton();

        TabelDetail.setModel(tabelDetailModel);

        tabelDetailModel.addColumn("No");
        tabelDetailModel.addColumn("Name");
        tabelDetailModel.addColumn("Detail");
        tabelDetailModel.addColumn("Price");
        tabelDetailModel.addColumn("Qty");
        tabelDetailModel.addColumn("Subtotal");

        updateTabelDetailModel();

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.6, 0.6, 2.0, 1.0, 0.2, 1.0E-4};

        //---- Title ----
        Title.setText("Checkout");
        add(Title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== DaftarPembelian ========
        {
            DaftarPembelian.setViewportView(TabelPembelian);
        }
        add(DaftarPembelian, new GridBagConstraints(3, 0, 2, 3, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- LabelCustomer ----
        LabelCustomer.setText("Nama");
        add(LabelCustomer, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(NamaCustomer, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== DaftarDetail ========
        {
            DaftarDetail.setViewportView(TabelDetail);
        }
        add(DaftarDetail, new GridBagConstraints(0, 2, 3, 3, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- LabelTotalPembelian ----
        LabelTotalPembelian.setText("Total");
        add(LabelTotalPembelian, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- NilaiTotalPembelian ----
        NilaiTotalPembelian.setText("1000");
        add(NilaiTotalPembelian, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- Checkout ----
        Checkout.setText("Checkout");
        add(Checkout, new GridBagConstraints(3, 4, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        Checkout.addActionListener(new ActionListener() {
            @SneakyThrows // MAY RESULT IN DEACTIVATED USER
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(NamaCustomer.getSelectedCustomerID());
                // function finish checkout
                Customer billOwner = NamaCustomer.getSelectedCustomerID() == -1
                        ?
                        DataStore.getInstance().createNewCustomer()                                         // soon-to-be-customer
                        :
                        DataStore.getInstance().getCustomerWithID(NamaCustomer.getSelectedCustomerID()      // premium customer
                        );

                billToBeCheckedOut.setOwner(billOwner);
                billOwner.assignBill(billToBeCheckedOut);

//                System.out.println("Bill Id = " + billToBeCheckedOut.getID());
//                System.out.println(DataStore.getInstance().getBillWithID(billToBeCheckedOut).getID());

                // pay
                // TODO : add modifiers if exists
                billOwner.pay();

                // pop up print bill
                int custId = billOwner.getID();
                int fixedbillidx = DataStore.getInstance().getCustomerWithID(custId).getHistory().size() - 1; // print latest
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Print Bill");
                int userSelection = JOptionPane.showConfirmDialog(null, "Do you want to print the bill?", "Print Bill?", JOptionPane.YES_NO_OPTION);
                if (userSelection == JOptionPane.YES_OPTION) {
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        FixedBillPrinter fixedBillPrinter = new FixedBillPrinter(fileToSave.getAbsolutePath()+"/bill_"+custId+"_"+fixedbillidx+".pdf", custId,
                                fixedbillidx);
                        Thread printThread = new Thread(fixedBillPrinter::printFixedBill);
                        printThread.start();
                    }
                } else {
                    // Don't save file
                }
                // hide this page (TODO: destroy or recycle instead)
                parentTabbedPane.setComponentAt(parentTabbedPane.getSelectedIndex(), parentCashier);
                parentTabbedPane.repaint();

                // close bill tab
                parentCashier.removeCurrentBillTab();
                removeAll();
            }
        });

        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    @SneakyThrows // TODO : check ketika item dihapus
    void updateTabelDetailModel() {
        tabelDetailModel.setRowCount(0);

        int i = 1;
        for (QuantifiableItem qItem : billToBeCheckedOut.getItemList()) {
            tabelDetailModel.addRow(new String[]{Integer.toString(i),
                    qItem.getName(),
                    "TODO",
                    Double.toString(qItem.getSingularPrice()),
                    Integer.toString(qItem.getQuantity()),
                    Double.toString(qItem.getPrice())
            });
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JLabel Title;
    private JScrollPane DaftarPembelian;
    private JTable TabelPembelian;
    private JLabel LabelCustomer;
    private CashierComboBox NamaCustomer;
    private JScrollPane DaftarDetail;
    private JTable TabelDetail;
    private JLabel LabelTotalPembelian;
    private JLabel NilaiTotalPembelian;
    private JButton Checkout;

    @Override
    public void onStorerDataChange(String storerName) {
     if(storerName.equals("Premium Customer")) {

     }
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
