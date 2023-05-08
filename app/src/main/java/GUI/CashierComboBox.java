package GUI;

import Core.Customer.PremiumCustomer;
import lombok.SneakyThrows;

import javax.swing.*;
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
                if (e.getSource() instanceof JTextField){
                    updateComboModel();
                    hidePopup();
                    showPopup();
                }
            }


        });

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(getSelectedIndex());


            }
        });
    }

    private void updateComboModel(){
        // TODO: MAKE SURE OF THE MAX LENGTH
        // KNOWN PROBLEM : AUTO WIDTH INCREASE
//        String lastInput = textField.getText().substring(0, textField.getText().length() >= 16 ?
//                                                                16
//                                                                :
//                                                                textField.getText().length()
//                                                        );
        String lastInput = textField.getText();

        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(lastInput);

        displayedList = customerList
                            .stream()
                            .filter(customer -> (customer.getName().toLowerCase() + " (" + customer.getID() + ")")
                                    .contains(lastInput.toLowerCase()))
                            .collect(Collectors
                            .toCollection(ArrayList::new));

        for (PremiumCustomer pCustomer : displayedList) {
            comboBoxModel.addElement(pCustomer.getName() + " (" + pCustomer.getID() + ")");
        }
    }

    @SneakyThrows
    public int getSelectedCustomerID() {
        // wrong query
        if (displayedList.size() == 0) {
            return -1;
        }

        // incomplete query
        if (getSelectedIndex() == 0 && ! (displayedList.get(0).getName() + " (" + displayedList.get(0).getID() + ")").equals(textField.getText())) {
            System.out.println("buat");
            return -1;
        }

        for (PremiumCustomer pCustomer : displayedList) {
            if ((pCustomer.getName() + " (" + pCustomer.getID() + ")").equals(textField.getText())) {
                System.out.println("make premium" + pCustomer.getID());
                return pCustomer.getID();
            }
        }

        return -1; // ERROR
    }




}
