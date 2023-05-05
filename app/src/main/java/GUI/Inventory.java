package GUI;

import Core.DataStore.DataStore;
import Core.Item.Item;
import Core.Item.QuantifiableItem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Image;


import java.util.*;
import java.util.List;

public class Inventory extends JPanel {
    private List<QuantifiableItem> items;
    private String imgPath;

    public Inventory() {
        this.items = DataStore.getInstance().getItems();
        initComponents();
    }

    private void initComponents() {
        DefaultListModel<String> items_list = new DefaultListModel();
        for (int i = 0; i<this.items.size(); i++){
            items_list.addElement(items.get(i).getItem().getName());
        }

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Althaaf Khasyi Atisomya
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        list1 = new JList(items_list);
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        dialog1 = new JDialog();
        label13 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label14 = new JLabel();
        label15 = new JLabel();
        textField3 = new JTextField();
        label16 = new JLabel();
        textField4 = new JTextField();
        label19 = new JLabel();
        button4 = new JButton();
        textField5 = new JTextField();
        label18 = new JLabel();
        label20 = new JLabel();
        button5 = new JButton();
        dialog2 = new JDialog();
        label17 = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
        border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER
        ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
        . BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
        new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r"
        .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //---- label1 ----
        label1.setText("Items");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            scrollPane1.setViewportView(list1);
        }

        //---- label2 ----
        label2.setText("text");
        label2.setOpaque(true);
        label2.setBackground(new Color(0xcccccc));

        //---- label3 ----
        label3.setText("Name              ");
        label3.setFont(label3.getFont().deriveFont(14f));

        //---- label4 ----
        label4.setText("Sell Price          ");
        label4.setFont(label4.getFont().deriveFont(14f));

        //---- label5 ----
        label5.setText("Buy Price          ");
        label5.setFont(label5.getFont().deriveFont(14f));

        //---- label6 ----
        label6.setText("Stock                ");
        label6.setFont(label6.getFont().deriveFont(14f));

        //---- label7 ----
        label7.setText("Category          ");
        label7.setFont(label7.getFont().deriveFont(14f));

        //---- button1 ----
        button1.setText("delete");

        //---- button2 ----
        button2.setText("edit");

        //---- button3 ----
        button3.setText("add");

        //---- label8 ----
        label8.setText("dummy1");
        label8.setFont(label8.getFont().deriveFont(14f));

        //---- label9 ----
        label9.setText("9999");
        label9.setFont(label9.getFont().deriveFont(14f));

        //---- label10 ----
        label10.setText("8888");
        label10.setFont(label10.getFont().deriveFont(14f));

        //---- label11 ----
        label11.setText("7777");
        label11.setFont(label11.getFont().deriveFont(14f));

        //---- label12 ----
        label12.setText("dummy2");
        label12.setFont(label12.getFont().deriveFont(14f));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button3))
                        .addComponent(scrollPane1))
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label3)
                                .addComponent(label4)
                                .addComponent(label5)
                                .addComponent(label6)
                                .addComponent(label7))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label9, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addComponent(label10, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addComponent(label11, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addComponent(label12, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addComponent(label8, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                            .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(button1)
                                    .addGap(23, 23, 23)
                                    .addComponent(button2)
                                    .addGap(102, 102, 102))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button3)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label6)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label7))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(label8)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label9)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label10)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label11)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label12)))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button2)
                                .addComponent(button1))
                            .addGap(19, 19, 19))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(115, Short.MAX_VALUE))
        );

        //======== dialog1 ========
        {
            dialog1.setTitle("Item Property");
            var dialog1ContentPane = dialog1.getContentPane();

            //---- label13 ----
            label13.setText("Name");
            label13.setFont(label13.getFont().deriveFont(label13.getFont().getSize() + 2f));

            //---- label14 ----
            label14.setText("Sell Price");
            label14.setFont(label14.getFont().deriveFont(label14.getFont().getSize() + 2f));

            //---- label15 ----
            label15.setText("Buy Price");
            label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 2f));

            //---- label16 ----
            label16.setText("Stock");
            label16.setFont(label16.getFont().deriveFont(label16.getFont().getSize() + 2f));

            //---- label19 ----
            label19.setText("text");
            label19.setOpaque(true);
            label19.setBackground(new Color(0xcccccc));

            //---- button4 ----
            button4.setText("save");

            //---- label18 ----
            label18.setText("Image");
            label18.setFont(label18.getFont().deriveFont(label18.getFont().getSize() + 2f));

            //---- label20 ----
            label20.setText("Category");
            label20.setFont(label20.getFont().deriveFont(label20.getFont().getSize() + 2f));

            //---- button5 ----
            button5.setText("add image");

            GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
            dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
            dialog1ContentPaneLayout.setHorizontalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                    .addComponent(label18)
                                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                            .addComponent(label13)
                                            .addComponent(label14)
                                            .addComponent(label15)
                                            .addComponent(label16)
                                            .addComponent(label20))
                                        .addGap(32, 32, 32)
                                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                            .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                    .addComponent(button5)
                                    .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                                .addComponent(label19, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                                .addComponent(button4)
                                .addGap(70, 70, 70))))
            );
            dialog1ContentPaneLayout.setVerticalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label13)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label14)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label15)
                                    .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label16)
                                    .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label20))
                                .addGap(18, 18, 18)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label18)
                                    .addComponent(button5)))
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(label19, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button4)))
                        .addContainerGap(12, Short.MAX_VALUE))
            );
            dialog1.pack();
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== dialog2 ========
        {
            var dialog2ContentPane = dialog2.getContentPane();
            dialog2ContentPane.setLayout(new FlowLayout());

            //---- label17 ----
            label17.setText("Error: Item Already Deleted");
            label17.setForeground(Color.red);
            dialog2ContentPane.add(label17);
            dialog2.pack();
            dialog2.setLocationRelativeTo(dialog2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    int idx = list1.getSelectedIndex();
                    if (idx!=-1) {
                        QuantifiableItem x = items.get(idx);
                        setItemProperty(x.getName(),x.getSingularCost(), x.getCost(),x.getQuantity(),x.getCategory());
                        label2.setIcon(ResizeImage(imgPath,true));
                    }
                }
            }
        });

        button1.addActionListener(new ActionListener() { //delete
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if (index != -1){
                    if (items.get(list1.getSelectedIndex()).getItem().isDeleted()){
                        dialog2.setVisible(true);
                    } else {
                        clearItemProperty();
                        items.get(index).getItem().setAsDeleted();
                    }
                }
            }
        });

        button2.addActionListener(new ActionListener() { //edit
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex()!=-1) {
                    if (items.get(list1.getSelectedIndex()).getItem().isDeleted()){
                        dialog2.setVisible(true);
                    } else {
                        dialog1.setTitle("Edit Item");
                        QuantifiableItem x = items.get(list1.getSelectedIndex());
                        setTextField(x.getName(), String.valueOf(x.getSingularCost()), String.valueOf(x.getCost()), String.valueOf(x.getQuantity()), x.getCategory());
                        dialog1.setVisible(true);
                    }
                }
            }
        });

        button3.addActionListener(new ActionListener() { //add
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog1.setTitle("Add Item");
                clearTextField();
                dialog1.setVisible(true);
            }
        });

        button4.addActionListener(new ActionListener() { //save
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(dialog1.getTitle(), "Add Item")){
                    String newName = textField1.getText();
                    Double newPrice = Double.valueOf(textField2.getText());
                    Double newOriginalPrice = Double.valueOf(textField3.getText());
                    Integer newQuantity = Integer.parseInt(textField4.getText());
                    String newCategory = textField5.getText();
                    QuantifiableItem newItem = null;
                    boolean addingSuccess = false;
                    try {
                        newItem = DataStore.getInstance().addNewItem(newName, newPrice, newOriginalPrice, newCategory, newQuantity);
                        addingSuccess = true;
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }

                    if (addingSuccess){
                        assert (newItem != null);
                        items.add(newItem);
                        items_list.addElement(newItem.getName());
                        dialog1.setVisible(false);
                    }
                } else { //edit
                    int idx = list1.getSelectedIndex();
                    QuantifiableItem editedItemDisplay = items.get(idx);
                    editedItemDisplay.setName(textField1.getText());
                    editedItemDisplay.setSingularPrice(Double.valueOf((textField2.getText())));
                    editedItemDisplay.setSingularCost(Double.valueOf(textField3.getText()));
                    editedItemDisplay.setQuantity(Integer.parseInt(textField4.getText()));
                    editedItemDisplay.setCategory(textField5.getText());
                    items_list.setElementAt(textField1.getText(),idx);
                    setItemProperty(textField1.getText(),Double.parseDouble(textField2.getText()),Double.parseDouble(textField3.getText()),Integer.parseInt(textField4.getText()),textField5.getText());
                    dialog1.setVisible(false);
                }
            }
        });

        button5.addActionListener(new ActionListener() { //add image
            public void actionPerformed(ActionEvent e) {

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                //if the user click on save in Jfilechooser
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    label19.setIcon(ResizeImage(path,false));
                    imgPath = path;
                }
                //if the user click on save in Jfilechooser
                else if(result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No File Select");
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Althaaf Khasyi Atisomya
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JList list1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JDialog dialog1;
    private JLabel label13;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label14;
    private JLabel label15;
    private JTextField textField3;
    private JLabel label16;
    private JTextField textField4;
    private JLabel label19;
    private JButton button4;
    private JTextField textField5;
    private JLabel label18;
    private JLabel label20;
    private JButton button5;
    private JDialog dialog2;
    private JLabel label17;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:of
    private void setItemProperty(String name, Double sell_price, Double buy_price, Integer stock,String category){
        label8.setText(name);
        label9.setText(String.valueOf(sell_price));
        label10.setText(String.valueOf(buy_price));
        label11.setText(stock.toString());
        label12.setText(category);
    }

    private void clearItemProperty(){
        label8.setText(" ");
        label9.setText(" ");
        label10.setText(" ");
        label11.setText(" ");
        label12.setText(" ");
    }

    private void setTextField(String name, String sell_price, String buy_price, String stock, String category){
        textField1.setText(name);
        textField2.setText(sell_price);
        textField3.setText(buy_price);
        textField4.setText(stock);
        textField5.setText(category);
    }

    public ImageIcon ResizeImage(String ImagePath,Boolean isLabel2)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg;
        if (isLabel2){
            newImg = img.getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_SMOOTH);
        } else {
            newImg = img.getScaledInstance(label19.getWidth(), label19.getHeight(), Image.SCALE_SMOOTH);
        }
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void clearTextField(){
        setTextField("","","","","");
    }




}

