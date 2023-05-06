package GUI;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.Item.QuantifiableItem;
import Core.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class Inventory extends JPanel {
    private List<QuantifiableItem> items;
    private String base64Image;

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
        title = new JLabel();
        scrollPane1 = new JScrollPane();
        item_list = new JList(items_list);
        item_image = new JLabel();
        prop_name = new JLabel();
        prop_sellprice = new JLabel();
        prop_buyprice = new JLabel();
        prop_stock = new JLabel();
        prop_category = new JLabel();
        delete_button = new JButton();
        edit_button = new JButton();
        add_button = new JButton();
        item_name = new JLabel();
        item_sellprice = new JLabel();
        item_buyprice = new JLabel();
        item_stock = new JLabel();
        item_category = new JLabel();
        delete_mark = new JLabel();
        dialog1 = new JDialog();
        prop_name_edit = new JLabel();
        input_name = new JTextField();
        input_sellprice = new JTextField();
        prop_sellprice_edit = new JLabel();
        prop_buyprice_edit = new JLabel();
        input_buyprice = new JTextField();
        prop_stock_edit = new JLabel();
        input_stock = new JTextField();
        image_editdisplay = new JLabel();
        save_button = new JButton();
        input_category = new JTextField();
        prop_image_edit = new JLabel();
        prop_category_edit = new JLabel();
        add_image_button = new JButton();
        error_popup = new JDialog();
        error_message = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
        12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //---- title ----
        title.setText("Items");
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 4f));

        //======== scrollPane1 ========
        {

            //---- item_list ----
            item_list.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            scrollPane1.setViewportView(item_list);
        }

        //---- item_image ----
        item_image.setText("text");
        item_image.setOpaque(true);
        item_image.setBackground(new Color(0xcccccc));
        item_image.setSize(210,210);
        setBase64ImageToDefault();
        displayImageInJLabel(base64Image,item_image);

        //---- prop_name ----
        prop_name.setText("Name              ");
        prop_name.setFont(prop_name.getFont().deriveFont(14f));

        //---- prop_sellprice ----
        prop_sellprice.setText("Sell Price          ");
        prop_sellprice.setFont(prop_sellprice.getFont().deriveFont(14f));

        //---- prop_buyprice ----
        prop_buyprice.setText("Buy Price          ");
        prop_buyprice.setFont(prop_buyprice.getFont().deriveFont(14f));

        //---- prop_stock ----
        prop_stock.setText("Stock                ");
        prop_stock.setFont(prop_stock.getFont().deriveFont(14f));

        //---- prop_category ----
        prop_category.setText("Category          ");
        prop_category.setFont(prop_category.getFont().deriveFont(14f));

        //---- delete_button ----
        delete_button.setText("delete");

        //---- edit_button ----
        edit_button.setText("edit");

        //---- add_button ----
        add_button.setText("add");

        //---- item_name ----
        item_name.setText(" ");
        item_name.setFont(item_name.getFont().deriveFont(14f));

        //---- item_sellprice ----
        item_sellprice.setText(" ");
        item_sellprice.setFont(item_sellprice.getFont().deriveFont(14f));

        //---- item_buyprice ----
        item_buyprice.setText(" ");
        item_buyprice.setFont(item_buyprice.getFont().deriveFont(14f));

        //---- item_stock ----
        item_stock.setText(" ");
        item_stock.setFont(item_stock.getFont().deriveFont(14f));

        //---- item_category ----
        item_category.setText(" ");
        item_category.setFont(item_category.getFont().deriveFont(14f));

        //---- delete_mark ----
        delete_mark.setText("");
        delete_mark.setForeground(new Color(0xff0033));
        delete_mark.setFont(new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 20));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(add_button))
                        .addComponent(scrollPane1))
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(prop_name)
                                .addComponent(prop_sellprice)
                                .addComponent(prop_buyprice)
                                .addComponent(prop_stock)
                                .addComponent(prop_category))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(item_name, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                .addComponent(item_sellprice, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                .addComponent(item_buyprice, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                .addComponent(item_stock, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                .addComponent(item_category, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(47, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(item_image, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(delete_button)
                                    .addGap(23, 23, 23)
                                    .addComponent(edit_button)
                                    .addGap(102, 102, 102))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(delete_mark)
                                    .addGap(142, 142, 142))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(add_button)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addComponent(delete_mark))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(item_image, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(prop_name)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prop_sellprice)
                                        .addComponent(item_sellprice))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prop_buyprice)
                                        .addComponent(item_buyprice))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prop_stock)
                                        .addComponent(item_stock))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prop_category)
                                        .addComponent(item_category)))
                                .addComponent(item_name))
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(edit_button)
                                .addComponent(delete_button))
                            .addGap(19, 19, 19))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(109, Short.MAX_VALUE))
        );

        //======== dialog1 ========
        {
            dialog1.setTitle("Item Property");
            var dialog1ContentPane = dialog1.getContentPane();

            //---- prop_name_edit ----
            prop_name_edit.setText("Name");
            prop_name_edit.setFont(prop_name_edit.getFont().deriveFont(prop_name_edit.getFont().getSize() + 2f));

            //---- prop_sellprice_edit ----
            prop_sellprice_edit.setText("Sell Price");
            prop_sellprice_edit.setFont(prop_sellprice_edit.getFont().deriveFont(prop_sellprice_edit.getFont().getSize() + 2f));

            //---- prop_buyprice_edit ----
            prop_buyprice_edit.setText("Buy Price");
            prop_buyprice_edit.setFont(prop_buyprice_edit.getFont().deriveFont(prop_buyprice_edit.getFont().getSize() + 2f));

            //---- prop_stock_edit ----
            prop_stock_edit.setText("Stock");
            prop_stock_edit.setFont(prop_stock_edit.getFont().deriveFont(prop_stock_edit.getFont().getSize() + 2f));

            //---- image_editdisplay ----
            image_editdisplay.setText("text");
            image_editdisplay.setOpaque(true);
            image_editdisplay.setBackground(new Color(0xcccccc));

            //---- save_button ----
            save_button.setText("save");

            //---- prop_image_edit ----
            prop_image_edit.setText("Image");
            prop_image_edit.setFont(prop_image_edit.getFont().deriveFont(prop_image_edit.getFont().getSize() + 2f));

            //---- prop_category_edit ----
            prop_category_edit.setText("Category");
            prop_category_edit.setFont(prop_category_edit.getFont().deriveFont(prop_category_edit.getFont().getSize() + 2f));

            //---- add_image_button ----
            add_image_button.setText("add image");

            GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
            dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
            dialog1ContentPaneLayout.setHorizontalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(add_image_button))
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                    .addComponent(prop_image_edit)
                                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                            .addComponent(prop_name_edit)
                                            .addComponent(prop_sellprice_edit)
                                            .addComponent(prop_buyprice_edit)
                                            .addComponent(prop_stock_edit)
                                            .addComponent(prop_category_edit))
                                        .addGap(32, 32, 32)
                                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                                            .addComponent(input_stock, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(input_buyprice, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(input_sellprice, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(input_name, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(input_category, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                                .addComponent(image_editdisplay, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                                .addComponent(save_button, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))))
            );
            dialog1ContentPaneLayout.setVerticalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_name_edit)
                                    .addComponent(input_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_sellprice_edit)
                                    .addComponent(input_sellprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_buyprice_edit)
                                    .addComponent(input_buyprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_stock_edit)
                                    .addComponent(input_stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_category_edit)
                                    .addComponent(input_category, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(prop_image_edit)
                                    .addComponent(add_image_button)))
                            .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(image_editdisplay, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(save_button)))
                        .addContainerGap(18, Short.MAX_VALUE))
            );
            dialog1.pack();
            dialog1.setLocationRelativeTo(dialog1.getOwner());
        }

        //======== error_popup ========
        {
            var error_popupContentPane = error_popup.getContentPane();
            error_popupContentPane.setLayout(new FlowLayout());

            //---- error_message ----
            error_message.setText("Error: Item Already Deleted aaaaaaaaaaaaaaaaaa");
            error_message.setForeground(Color.red);
            error_popupContentPane.add(error_message);
            error_popup.pack();
            error_popup.setLocationRelativeTo(error_popup.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        item_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    int idx = item_list.getSelectedIndex();
                    try {
                        if (idx!=-1) {
                            QuantifiableItem x = items.get(idx);
                            setItemProperty(x.getName(),x.getItem().getPrice(), x.getItem().getCost(),x.getQuantity(),x.getCategory(),x.getItem().isDeleted());
                            displayImageInJLabel(x.getImage().getBase64Image(),item_image);
                        }
                    } catch (SearchedItemNotExist ignored) {

                    }
                }
            }
        });

        delete_button.addActionListener(new ActionListener() { //delete
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = item_list.getSelectedIndex();
                if (index != -1){
                    if (items.get(item_list.getSelectedIndex()).getItem().isDeleted()){
                        error_message.setText("Error: Item Already Deleted");
                        error_popup.setVisible(true);
                    } else {
                        items.get(index).getItem().setAsDeleted();
                        QuantifiableItem x = items.get(index);
                        setItemProperty(x.getName(), x.getItem().getPrice(), x.getItem().getCost(), x.getQuantity(), x.getCategory(), x.getItem().isDeleted());
                        items_list.setElementAt(x.getName()+" (deleted)",index);
                    }
                }
            }
        });

        edit_button.addActionListener(new ActionListener() { //edit
            @Override
            public void actionPerformed(ActionEvent e) {
                if (item_list.getSelectedIndex()!=-1) {
                    if (items.get(item_list.getSelectedIndex()).getItem().isDeleted()){
                        error_message.setText("Error: Item Already Deleted");
                        error_popup.setVisible(true);
                    } else {
                        try {
                            dialog1.setTitle("Edit Item");
                            QuantifiableItem x = items.get(item_list.getSelectedIndex());
                            setTextField(x.getName(), String.valueOf(x.getItem().getPrice()), String.valueOf(x.getItem().getCost()), String.valueOf(x.getQuantity()), x.getCategory());
                            displayImageInJLabel(x.getImage().getBase64Image(),image_editdisplay);
                            base64Image= x.getImage().getBase64Image();
                            dialog1.setVisible(true);
                        } catch (SearchedItemNotExist ignored) {

                        }
                    }
                }
            }
        });

        add_button.addActionListener(new ActionListener() { //add
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog1.setTitle("Add Item");
                clearTextField();
                dialog1.setVisible(true);
                displayImageInJLabel(base64Image,image_editdisplay);
            }
        });

        save_button.addActionListener(new ActionListener() { //save
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = input_name.getText();
                String newPrice = input_sellprice.getText();
                String newOriginalPrice = input_buyprice.getText();
                String newQuantity = input_stock.getText();
                String newCategory = input_category.getText();
                if (newName.equals("") || newPrice.equals("") || newOriginalPrice.equals("") || newQuantity.equals("") || newCategory.equals("")) {
                    error_message.setText("Error: Value Cannot be Null");
                    error_popup.setVisible(true);
                } else if (Objects.equals(dialog1.getTitle(), "Add Item")){
                    try {
                        Double newPriceD = Double.valueOf(input_sellprice.getText());
                        Double newOriginalPriceD = Double.valueOf(input_buyprice.getText());
                        Integer newQuantityI = Integer.parseInt(input_stock.getText());

                        if(newPriceD<0 || newOriginalPriceD<0 || newQuantityI<0){
                            error_message.setText("Error: Price & Stock Cannot Be Negative");
                            error_popup.setVisible(true);
                        } else {

                            QuantifiableItem newItem = null;
                            boolean addingSuccess = false;
                            try {
                                newItem = DataStore.getInstance().addNewItem(newName, newPriceD, newOriginalPriceD, newCategory, newQuantityI, base64Image);
                                addingSuccess = true;
                            } catch (Exception error) {
                                System.out.println(error.getMessage());
                            }

                            if (addingSuccess) {
                                assert (newItem != null);
                                items.add(newItem);
                                items_list.addElement(newItem.getName());
                                dialog1.setVisible(false);
                                setBase64ImageToDefault();
                            }
                        }
                    } catch (NumberFormatException nfe){
                        error_message.setText("Error: Invalid Input Types");
                        error_popup.setVisible(true);
                    }
                } else { //edit
                    int idx = item_list.getSelectedIndex();
                    try {
                        Double newPriceD = Double.valueOf(input_sellprice.getText());
                        Double newOriginalPriceD = Double.valueOf(input_buyprice.getText());
                        Integer newQuantityI = Integer.parseInt(input_stock.getText());

                        if (newPriceD<0 || newOriginalPriceD<0 || newQuantityI<0){
                            error_message.setText("Error:  Price & Stock Cannot Be Negative");
                            error_popup.setVisible(true);
                        } else {
                            QuantifiableItem editedItemDisplay = items.get(idx);
                            editedItemDisplay.setName(newName);
                            editedItemDisplay.setSingularPrice(newPriceD);
                            editedItemDisplay.setSingularCost(newOriginalPriceD);
                            editedItemDisplay.setQuantity(newQuantityI);
                            editedItemDisplay.setCategory(newCategory);
                            editedItemDisplay.setImage(base64Image);
                            items_list.setElementAt(input_name.getText(), idx);

                            String itemImage = null;
                            try {
                                itemImage = editedItemDisplay.getImage().getBase64Image();
                            } catch (SearchedItemNotExist error) {

                            }

                            assert (itemImage != null);

                            setItemProperty(input_name.getText(), Double.parseDouble(input_sellprice.getText()), Double.parseDouble(input_buyprice.getText()), Integer.parseInt(input_stock.getText()), input_category.getText(), false);
                            displayImageInJLabel(itemImage, item_image);
                            setBase64ImageToDefault();
                            dialog1.setVisible(false);
                        }
                    } catch (NumberFormatException nfe){
                        error_message.setText("Error: Invalid Input Types");
                        error_popup.setVisible(true);
                    }
                }
            }
        });

        add_image_button.addActionListener(new ActionListener() { //add image
            public void actionPerformed(ActionEvent e) {

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();

                    Path imagePath = Paths.get(path);
                    try {
                        byte[] imageBytes = Files.readAllBytes(imagePath);
                        base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    }  catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    displayImageInJLabel(base64Image,image_editdisplay);

                }
                else if(result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No File Select");
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Althaaf Khasyi Atisomya
    private JLabel title;
    private JScrollPane scrollPane1;
    private JList item_list;
    private JLabel item_image;
    private JLabel prop_name;
    private JLabel prop_sellprice;
    private JLabel prop_buyprice;
    private JLabel prop_stock;
    private JLabel prop_category;
    private JButton delete_button;
    private JButton edit_button;
    private JButton add_button;
    private JLabel item_name;
    private JLabel item_sellprice;
    private JLabel item_buyprice;
    private JLabel item_stock;
    private JLabel item_category;
    private JLabel delete_mark;
    private JDialog dialog1;
    private JLabel prop_name_edit;
    private JTextField input_name;
    private JTextField input_sellprice;
    private JLabel prop_sellprice_edit;
    private JLabel prop_buyprice_edit;
    private JTextField input_buyprice;
    private JLabel prop_stock_edit;
    private JTextField input_stock;
    private JLabel image_editdisplay;
    private JButton save_button;
    private JTextField input_category;
    private JLabel prop_image_edit;
    private JLabel prop_category_edit;
    private JButton add_image_button;
    private JDialog error_popup;
    private JLabel error_message;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:of
    private void setItemProperty(String name, Double sell_price, Double buy_price, Integer stock,String category,Boolean isDeleted){
        item_name.setText(name);
        item_sellprice.setText(String.valueOf(sell_price));
        item_buyprice.setText(String.valueOf(buy_price));
        item_stock.setText(stock.toString());
        item_category.setText(category);
        if (isDeleted) delete_mark.setText("(deleted)");
        else delete_mark.setText("");
    }

    private void clearItemProperty(){
        item_name.setText(" ");
        item_sellprice.setText(" ");
        item_buyprice.setText(" ");
        item_stock.setText(" ");
        item_category.setText(" ");
    }

    private void setTextField(String name, String sell_price, String buy_price, String stock, String category){
        input_name.setText(name);
        input_sellprice.setText(sell_price);
        input_buyprice.setText(buy_price);
        input_stock.setText(stock);
        input_category.setText(category);
    }

    public ImageIcon ResizeImage(ImageIcon MyImage, JLabel label)
    {
        Image img = MyImage.getImage();
        Image newImg;
        newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void displayImageInJLabel(String img, JLabel label){
        try {
            byte[] btDataFile = Base64.getDecoder().decode(img);
            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(btDataFile));
            ImageIcon image2 = new ImageIcon(image1);
            label.setIcon(ResizeImage(image2,label));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//    public ImageIcon ResizeImage(String ImagePath,Boolean isitem_image)
//    {
//        ImageIcon MyImage = new ImageIcon(ImagePath);
//        Image img = MyImage.getImage();
//        Image newImg;
//        if (isitem_image){
//            newImg = img.getScaledInstance(item_image.getWidth(), item_image.getHeight(), Image.SCALE_SMOOTH);
//        } else {
//            newImg = img.getScaledInstance(image_editdisplay.getWidth(), image_editdisplay.getHeight(), Image.SCALE_SMOOTH);
//        }
//        ImageIcon image = new ImageIcon(newImg);
//        return image;
//    }

    private void clearTextField(){
        setTextField("","","","","");
    }

    private void setBase64ImageToDefault(){
        base64Image = "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";
    }

}

