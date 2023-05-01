package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;
import java.util.List;

//to-do:
// blm bisa per-image an, validasi edit & add item
// di spek inventory yg di delete ga sepenuhnya hilang

//class item buat ngetest aja
class Item{
    private String name;
    private int sell_price;
    private int buy_price;
    private int stock;
    private String category;
    public Item(String name,int sell_price,int buy_price,int stock,String category){
        this.name = name; this.sell_price=sell_price;
        this.buy_price = buy_price; this.stock=stock; this.category=category;
    }
    public Item(String name){
        this.name = name; sell_price=999;
        buy_price = 888; stock=10; category="ayam";
    }
    public String getName(){return name;}
    public int getSellPrice(){return sell_price;}
    public int getBuyPrice() {return buy_price;}
    public int getStock(){return stock;}
    public String getCategory(){return category;}
    public void setName(String name){this.name=name;}
    public void setSellPrice(int sell_price){this.sell_price=sell_price;}
    public void setBuyPrice(int buy_price){this.buy_price=buy_price;}
    public void setStock(int stock){this.stock=stock;}
    public void setCategory(String category){this.category=category;}
}
public class Inventory extends JPanel {
    private List<Item> items;

    private String[] getItemsName(){
        String[] item_name = new String[items.size()];
        for (int i=0;i<items.size();i++){
            item_name[i]=items.get(i).getName();
        }
        return item_name;
    }

    public Inventory() {
        this.items = new ArrayList<Item>();
        initComponents();
    }

    private void initComponents() {
        String items_name[] = {"dummy1","dummy2","dummy3","dummy4","dummy5"};
        for (int i=0;i<items_name.length;i++){
            items.add(new Item(items_name[i]));
        }
        DefaultListModel<String> items_list = new DefaultListModel();
        for (int i=0;i<this.items.size();i++){
            items_list.addElement(items.get(i).getName());
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
        textField6 = new JTextField();
        label18 = new JLabel();
        label20 = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
        , 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
         getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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
        label8.setText(" ");
        label8.setFont(label8.getFont().deriveFont(14f));

        //---- label9 ----
        label9.setText(" ");
        label9.setFont(label9.getFont().deriveFont(14f));

        //---- label10 ----
        label10.setText(" ");
        label10.setFont(label10.getFont().deriveFont(14f));

        //---- label11 ----
        label11.setText(" ");
        label11.setFont(label11.getFont().deriveFont(14f));

        //---- label12 ----
        label12.setText(" ");
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

            GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
            dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
            dialog1ContentPaneLayout.setHorizontalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                    .addComponent(label13)
                                    .addComponent(label14)
                                    .addComponent(label15)
                                    .addComponent(label16)
                                    .addComponent(label20)
                                    .addComponent(label18))
                                .addGap(32, 32, 32)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                    .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                    .addGap(96, 96, 96)
                                    .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
                                .addComponent(textField6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
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
                                .addGap(6, 6, 6)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label18)))
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    int idx = list1.getSelectedIndex();
                    if (idx!=-1) {
                        Item x = items.get(idx);
                        setItemProperty(x.getName(),x.getSellPrice(),x.getBuyPrice(),x.getStock(),x.getCategory());
                    }
                }
            }
        });

        button1.addActionListener(new ActionListener() { //delete
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if (index != -1){
                    clearItemProperty();
                    items_list.remove(index);
                    items.remove(index);
                }
            }
        });

        button2.addActionListener(new ActionListener() { //edit
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list1.getSelectedIndex()!=-1) {
                    dialog1.setTitle("Edit Item");
                    Item x = items.get(list1.getSelectedIndex());
                    setTextField(x.getName(),String.valueOf(x.getSellPrice()),String.valueOf(x.getBuyPrice()),String.valueOf(x.getStock()),x.getCategory()," ");
                    dialog1.setVisible(true);
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
                if (dialog1.getTitle()=="Add Item"){
                    Item newItem = new Item(textField1.getText(),Integer.parseInt(textField2.getText()),Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()),textField5.getText());
                    items.add(newItem);
                    items_list.addElement(newItem.getName());
                    dialog1.setVisible(false);
                } else { //edit
                    int idx = list1.getSelectedIndex();
                    Item editedItem = items.get(idx);
                    editedItem.setName(textField1.getText());
                    editedItem.setSellPrice(Integer.parseInt(textField2.getText()));
                    editedItem.setBuyPrice(Integer.parseInt(textField3.getText()));
                    editedItem.setStock(Integer.parseInt(textField4.getText()));
                    editedItem.setCategory(textField5.getText());
                    items_list.setElementAt(textField1.getText(),idx);
                    setItemProperty(textField1.getText(),Integer.parseInt(textField2.getText()),Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()),textField5.getText());
                    dialog1.setVisible(false);
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
    private JTextField textField6;
    private JLabel label18;
    private JLabel label20;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:of
    private void setItemProperty(String name, int sell_price, int buy_price, int stock,String category){
        label8.setText(name);
        label9.setText(Integer.valueOf(sell_price).toString());
        label10.setText(Integer.valueOf(buy_price).toString());
        label11.setText(Integer.valueOf(stock).toString());
        label12.setText(category);
    }

    private void clearItemProperty(){
        label8.setText(" ");
        label9.setText(" ");
        label10.setText(" ");
        label11.setText(" ");
        label12.setText(" ");
    }


    private void setTextField(String name, String sell_price, String buy_price, String stock, String category, String image){
        textField1.setText(name);
        textField2.setText(sell_price);
        textField3.setText(buy_price);
        textField4.setText(stock);
        textField5.setText(category);
        textField6.setText(image);
    }

    private void clearTextField(){
        setTextField("","","","","","");
    }
}