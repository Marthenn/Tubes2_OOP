package Plugins.PieInsight;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Map;

public class PieChart extends JPanel {
    private ArrayList<Double> values;
    private ArrayList<Color> colors;
    private ArrayList<String> labels;

    public PieChart(){
        values = new ArrayList<Double>();
        colors = new ArrayList<Color>();
        labels = new ArrayList<String>();
    }

    Color randomColor(){
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b= (int)(Math.random()*256);
        return new Color(r, g, b);
    }

    public void addSlice(double value, String label){
        values.add(new Double(value));
        colors.add(randomColor());
        labels.add(label);
        repaint();
    }

    public void clear(){
        values.clear();
        colors.clear();
        labels.clear();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        double arcAngle = 0.0;

        // Set anti-aliasing to improve the rendering quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double total = 0.0;
        for (Double value : values) {
            total += value;
        }

        double startAngle = 0.0;
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = Math.min(x, y) - 5;

        for (int i = 0; i < values.size(); i++) {
            arcAngle = 360 * (values.get(i) / total);
            g2d.setColor(colors.get(i));
            g2d.fill(new Arc2D.Double(x - radius, y - radius, 2 * radius, 2 * radius, startAngle, arcAngle, Arc2D.PIE));
            startAngle += arcAngle;
        }

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x - radius / 2, y - radius / 2, radius, radius);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(x - radius / 2, y - radius / 2, radius, radius);

        double angle = 0.0;
        for (int i = 0; i < values.size(); i++) {
            angle += 360 * (values.get(i) / total);
            double labelX = x + Math.cos(Math.toRadians(angle - arcAngle / 2)) * radius / 2;
            double labelY = y + Math.sin(Math.toRadians(angle - arcAngle / 2)) * radius / 2;
            g2d.setColor(Color.BLACK);
            g2d.drawString(labels.get(i), (int) labelX, (int) labelY);
        }
        g2d.dispose();
    }
}
