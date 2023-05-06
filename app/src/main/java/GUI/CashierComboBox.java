package GUI;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.DataStore;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CashierComboBox extends JComboBox {
    private ArrayList<PremiumCustomer>  customerList;
    private ArrayList<PremiumCustomer> displayedList = new ArrayList<>();
    private JTextField textField = new JTextField();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private BasicComboBoxEditor comboBoxEditor = new BasicComboBoxEditor();
    public CashierComboBox(ArrayList<PremiumCustomer> customerList){
        this.customerList = customerList;

        this.setEditable(true);

        initComponents();

        updateComboModel();
    }

    private void initComponents(){
        this.setEditor(comboBoxEditor);
        this.setModel(comboBoxModel);


        textField = (JTextField) comboBoxEditor.getEditorComponent();

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                showPopup();
                if (e.getSource() instanceof JTextField){
                    updateComboModel();
                    hidePopup();
                    showPopup();
                }
            }


        });
    }

    private void updateComboModel(){
        // TODO: MAKE SURE OF THE MAX LENGTH
        // KNOWN PROBLEM : AUTO WIDTH INCREASE
        String lastInput = textField.getText().substring(0, textField.getText().length() >= 16 ?
                                                                16
                                                                :
                                                                textField.getText().length()
                                                        );
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(lastInput);

        displayedList = customerList
                            .stream()
                            .filter(customer -> customer.getName().contains(lastInput))
                            .collect(Collectors
                            .toCollection(ArrayList::new));

        for (PremiumCustomer pCustomer : displayedList) {
            comboBoxModel.addElement(pCustomer.getName());
        }
    }

    @SneakyThrows
    public int getSelectedCustomerID() {
        // wrong query
        if (displayedList.size() == 0) {
            return -1;
        }

        // incomplete query
        if (! displayedList.get(0).getName().equals(textField.getText())) {
            return -1;
        }
        
        return displayedList.get(0).getID();
    }




}
