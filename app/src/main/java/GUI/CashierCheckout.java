/*
 * Created by JFormDesigner on Sat May 06 14:53:39 ICT 2023
 */

package GUI;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import Core.DataStore.StorerData.StorerDataListener;
import Core.IDAble.IDAbleListener;
import Core.Printer.FixedBillPrinter;
import Core.Item.Bill.Bill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Fakih A
 */
public class CashierCheckout extends JPanel implements StorerDataListener, IDAbleListener<QuantifiableItem> {
    JTabbedPane parentTabbedPane;
    Cashier parentCashier;
    Bill billToBeCheckedOut; // add listeners
    DefaultTableModel tabelDetailModel = new DefaultTableModel();
    ArrayList<FixedBillModifier> fixedBillModifiers = new ArrayList<>();
    Boolean adaStokKosong = false;
    public CashierCheckout(JTabbedPane parentTabbedPane, Cashier parentCashier, Bill billToBeCheckedOut) {
        this.parentTabbedPane = parentTabbedPane;
        this.parentCashier = parentCashier;
        this.billToBeCheckedOut  = billToBeCheckedOut;

        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama

        // init observer
        DataStore.getInstance().listenToItemStore(this);
        DataStore.getInstance().listenToItem(this);
        DataStore.getInstance().listenToCustomerStore(this);

        CancelCheckout = new JButton();
        DaftarPembelian = new JScrollPane();
        TabelPembelian = new JTable();
        LabelCustomer = new JLabel();
        NamaCustomer = new CashierComboBox(DataStore.getInstance().getPremiumCustomers());
        DaftarDetail = new JScrollPane();
        TabelDetail = new JTable();
        LabelTotalPembelian = new JLabel();
        NilaiTotalPembelian = new JLabel();
        PayButton = new JButton();
        PayWithPointButton = new JButton();

        TabelDetail.setModel(tabelDetailModel);

        tabelDetailModel.addColumn("No");
        tabelDetailModel.addColumn("Name");
        tabelDetailModel.addColumn("Price");
        tabelDetailModel.addColumn("Purchase Qty");
        tabelDetailModel.addColumn("Subtotal");
        tabelDetailModel.addColumn("Keterangan");

        TabelDetail.getColumnModel().getColumn(5).setCellRenderer(new KeteranganDetailRenderer());

        updateTabelDetailModel();

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.6, 0.6, 2.0, 1.0, 0.2, 1.0E-4};

        //---- Title ----
        CancelCheckout.setText("Batalkan");
        add(CancelCheckout, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== DaftarPembelian ========
        {
            DaftarPembelian.setViewportView(TabelPembelian);
        }
        add(DaftarPembelian, new GridBagConstraints(3, 0, 2, 2, 0.0, 0.0,
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
        add(LabelTotalPembelian, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- NilaiTotalPembelian ----
        updateNilaiTotalPembelian();
        add(NilaiTotalPembelian, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- Checkout ----
        PayButton.setText("Pay");
        add(PayButton, new GridBagConstraints(3, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- Checkout ----
        PayWithPointButton.setText("Pay with Points");
        add(PayWithPointButton, new GridBagConstraints(3, 4, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        PayButton.addActionListener(new ActionListener() {// MAY RESULT IN DEACTIVATED USER
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(NamaCustomer.getSelectedCustomerID());
                // function finish checkout
                if (adaStokKosong) {
                    JOptionPane.showMessageDialog(null, "Ada barang yang tidak tersedia", "Payment", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Customer billOwner;
                boolean isPremium =  NamaCustomer.getSelectedCustomerID() != -1;

                if (isPremium) {
                    billOwner = DataStore.getInstance().getPremiumCustomerWithID(NamaCustomer.getSelectedCustomerID())    ;  // premium customer
                } else {
                    billOwner = DataStore.getInstance().createNewCustomer()      ;                                   // soon-to-be-customer
                }

                billToBeCheckedOut.setOwner(billOwner);
                billOwner.assignBill(billToBeCheckedOut);

                // pay
                // TODO : add modifiers if exists
                billOwner.pay();

                // pop up print bill
                int custId = billOwner.getID();
                int fixedbillidx = billOwner.getHistory().size() - 1; // print latest
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Print Bill");
                int userSelection = JOptionPane.showConfirmDialog(null, "Do you want to print the bill?", "Print Bill?", JOptionPane.YES_NO_OPTION);
                if (userSelection == JOptionPane.YES_OPTION) {
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        FixedBillPrinter fixedBillPrinter = new FixedBillPrinter(fileToSave.getAbsolutePath()+ File.separator + "bill_"+custId+"_"+fixedbillidx+".pdf", custId,
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

        PayWithPointButton.addActionListener(new ActionListener() {// MAY RESULT IN DEACTIVATED USER
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adaStokKosong) {
                    JOptionPane.showMessageDialog(null, "Ada barang yang tidak tersedia", "Payment", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // function finish checkout
                Customer billOwner;
                boolean isPremium = NamaCustomer.getSelectedCustomerID() != -1;

                // pay
                // TODO : add modifiers if exists
                if (isPremium) {
                    billOwner = DataStore.getInstance().createNewCustomer();                                         // soon-to-be-customer
                    PremiumCustomer pCustomer = DataStore.getInstance().getPremiumCustomerWithID(billOwner.getID());

                    billToBeCheckedOut.setOwner(billOwner);
                    billOwner.assignBill(billToBeCheckedOut);

                    pCustomer.payWithPoint();
                } else {
                    JOptionPane.showMessageDialog(null, "Bukan Premium Customer", "Payment", JOptionPane.WARNING_MESSAGE);

                    return;
                }

                // pop up print bill
                int custId = billOwner.getID();
                int fixedbillidx = billOwner.getHistory().size() - 1; // print latest
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Print Bill");
                int userSelection = JOptionPane.showConfirmDialog(null, "Do you want to print the bill?", "Print Bill?", JOptionPane.YES_NO_OPTION);
                if (userSelection == JOptionPane.YES_OPTION) {
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        FixedBillPrinter fixedBillPrinter = new FixedBillPrinter(fileToSave.getAbsolutePath()+ File.separator + "bill_"+custId+"_"+fixedbillidx+".pdf", custId,
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

        CancelCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentTabbedPane.setComponentAt(parentTabbedPane.getSelectedIndex(), parentCashier);
                parentTabbedPane.repaint();

                removeAll();
            }
        });

        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    @SneakyThrows // TODO : check ketika item dihapus
    void updateTabelDetailModel() {
        adaStokKosong = false;
        tabelDetailModel.setRowCount(0);

        int i = 1;
        for (QuantifiableItem qItem : billToBeCheckedOut.getItemList()) {
            tabelDetailModel.addRow(new String[]{Integer.toString(i),
                    qItem.getName(),
                    Double.toString(qItem.getSingularPrice()),
                    Integer.toString(this.billToBeCheckedOut.getQuantityOfItemWithID(qItem.getID())),
                    Double.toString(qItem.getPrice()),
                    Integer.toString(DataStore.getInstance().getItemWithID(qItem.getID()).getQuantity() - qItem.getQuantity())
            });

            if (DataStore.getInstance().getItemWithID(qItem.getID()).getQuantity() - qItem.getQuantity() < 0) {
                adaStokKosong = true;
            }
        }
    }

    @SneakyThrows
    void updateNilaiTotalPembelian() {
        Double totalPurchase = 0d;
        for (QuantifiableItem qItem : billToBeCheckedOut.getItemList()) {
            totalPurchase += qItem.getPrice();
        }

        NilaiTotalPembelian.setText(Double.toString(totalPurchase));
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JButton CancelCheckout;
    private JScrollPane DaftarPembelian;
    private JTable TabelPembelian;
    private JLabel LabelCustomer;
    private CashierComboBox NamaCustomer;
    private JScrollPane DaftarDetail;
    private JTable TabelDetail;
    private JLabel LabelTotalPembelian;
    private JLabel NilaiTotalPembelian;
    private JButton PayButton;
    private JButton PayWithPointButton;

    @Override
    public void onStorerDataChange(String storerName) {
     if(storerName.equals("Premium Customer")) {

     }
    }

    @SneakyThrows
    @Override
    public void onItemWithIDChange(QuantifiableItem item) {
        this.billToBeCheckedOut = DataStore.getInstance().getBillWithID(billToBeCheckedOut);
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

class KeteranganDetailRenderer extends DefaultTableCellRenderer {
    JLabel label = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return  null;

        Boolean tersedia = Integer.parseInt(String.valueOf(value)) >= 0;

        Color bg =
                isSelected
                        ?
                        super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column).getBackground()
                        :
                         tersedia
                            ?
                            Color.GREEN
                                :
                            Color.RED
                ;

        JButton labelValue = new JButton(tersedia ? "Tersedia" : "Melebihi stok");
        labelValue.setBackground(bg);

        return labelValue;
    }


}