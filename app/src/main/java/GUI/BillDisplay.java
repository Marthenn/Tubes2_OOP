package GUI;

import Core.DataStore.DataStore;
import Core.Item.Bill.Bill;
import Core.Item.Bill.Exception.ItemInBillNotExist;
import Core.Item.QuantifiableItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BillDisplay extends JScrollPane {

    @Getter(AccessLevel.PUBLIC)
    private Bill displayedBill;

    private JTable displayedTable;

    private DefaultTableModel displayedTableModel;

    private  JLabel totalPriceLabel;
    public BillDisplay(JLabel totalPriceLabel) {
        this.displayedBill = DataStore.getInstance().createNewBill();

        this.displayedTableModel = new DefaultTableModel();
        this.displayedTableModel.addColumn("Nama");
        this.displayedTableModel.addColumn("Quantity");
        this.displayedTableModel.addColumn("Subtotal");

        this.displayedTable = new JTable(this.displayedTableModel);
        this.displayedTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        this.totalPriceLabel = totalPriceLabel;

        this.setViewportView(this.displayedTable);
    }

    @SneakyThrows
    public void updateTableModel() {
        this.displayedTableModel.setRowCount(0);

        for (QuantifiableItem qItem : this.displayedBill.getItemList()) {
            this.displayedTableModel.addRow(new String[]{qItem.getName(), Integer.toString(qItem.getQuantity()), Double.toString(qItem.getPrice())});
        }

        updateTotalPriceLabel();
    }

    @SneakyThrows
    public void updateTotalPriceLabel () {
        Double totalPrice = 0d;

        for (QuantifiableItem qItem : this.displayedBill.getItemList()) {
            totalPrice += qItem.getPrice();
        }

        this.totalPriceLabel.setText(Double.toString(totalPrice));
    }




}
