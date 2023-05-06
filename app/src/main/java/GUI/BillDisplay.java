package GUI;

import Core.Item.Bill.Bill;
import Core.DataStore.DataStore;
import Core.Item.QuantifiableItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BillDisplay extends JScrollPane {

    @Getter
    private Bill displayedBill;

    @Getter(AccessLevel.PRIVATE)
    private JTable displayedTable;

    private DefaultTableModel displayedTableModel;
    public BillDisplay() {
        this.displayedBill = DataStore.getInstance().createNewBill();

        this.displayedTableModel = new DefaultTableModel();
        this.displayedTableModel.addColumn("Nama");
        this.displayedTableModel.addColumn("Quantity");
        this.displayedTableModel.addColumn("Subtotal");

        this.displayedTable = new JTable(this.displayedTableModel);
        this.displayedTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        this.setViewportView(this.displayedTable);
    }

    public void updateTableModel() {
        this.displayedTableModel.setRowCount(0);

        for (QuantifiableItem qItem : this.displayedBill.getItemList()) {
            this.displayedTableModel.addRow(new String[]{qItem.getName(), qItem.getCategory(), Double.toString(qItem.getCost())});
        }
    }




}
