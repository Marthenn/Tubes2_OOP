/*
 * Created by JFormDesigner on Sun May 07 09:36:37 ICT 2023
 */

package GUI;

import Core.Item.QuantifiableItem;
import com.itextpdf.text.Header;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Fakih A
 */
public class CashierItemDisplay extends JPanel {
    String base64Image = "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";
//    byte[] btDataFile = Base64.getDecoder().decode(base64Image);
//    BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(btDataFile));
//    ImageIcon image2 = new ImageIcon(image1);
    public CashierItemDisplay(QuantifiableItem qItem, Color bgColor) {
        initComponents(qItem, bgColor);
    }

    @SneakyThrows
    private void initComponents(QuantifiableItem qItem, Color bgColor) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
        header = new JPanel();
        Nama = new JLabel(qItem.getName());
        Kategori = new JLabel(qItem.getCategory());
        GambarItem = new JLabel();
        HargaItem = new JButton(Double.toString(qItem.getSingularPrice()));

        // set bgColor
        header.setBackground(bgColor);

        setLayout(new BorderLayout());

        //---- GambarItem ----
        GambarItem.setHorizontalAlignment(SwingConstants.CENTER);
        add(GambarItem, BorderLayout.CENTER);

        //---- HargaItem ----
        add(HargaItem, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            header.setLayout(new BorderLayout());

            //---- Nama ----
            Nama.setFont(Nama.getFont().deriveFont(Nama.getFont().getSize() + 3f));
            Nama.setHorizontalAlignment(SwingConstants.CENTER);
            header.add(Nama, BorderLayout.CENTER);

            //---- Kategori ----
            Kategori.setFont(Kategori.getFont().deriveFont(Kategori.getFont().getSize() - 1f));
            Kategori.setHorizontalAlignment(SwingConstants.CENTER);
            header.add(Kategori, BorderLayout.SOUTH);
        }
        add(header, BorderLayout.NORTH);

        GambarItem.setIcon(getImageIcon(qItem.getImage().getBase64Image(), 128));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    @SneakyThrows
    private ImageIcon getImageIcon(String img, int desiredWidth){
            byte[] btDataFile = Base64.getDecoder().decode(img);
            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(btDataFile));
            ImageIcon image2 = new ImageIcon(image1);

            return resizeImageIcon(image2, desiredWidth);
    }

    private ImageIcon resizeImageIcon(ImageIcon imgIcon, int desiredWidth) {
        return  new ImageIcon(imgIcon.getImage().getScaledInstance(desiredWidth, (int) ((double)imgIcon.getIconHeight() / imgIcon.getIconWidth() * desiredWidth), Image.SCALE_SMOOTH));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Fakih Anugerah Pratama
    private JPanel header;
    private JLabel Nama;
    private JLabel Kategori;
    private JLabel GambarItem;
    private JButton HargaItem;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
