package Plugins.ChartInsight;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BarChart extends JPanel {
    private ArrayList<Double> values;
    private ArrayList<Color> colors;
    private ArrayList<String> labels;
    private double maxValue;

    public BarChart(){
        values = new ArrayList<>();
        colors = new ArrayList<>();
        labels = new ArrayList<>();
        maxValue = 0;
    }

    public void addValue(double value, String label){
        values.add(value);
        labels.add(label);
        maxValue = Math.max(maxValue, value);
        colors.add(Color.getHSBColor((float) Math.random(), 1.0f, 1.0f));
        repaint();
    }

    public void clear(){
        values.clear();
        labels.clear();
        maxValue = 0;
        colors.clear();
        repaint();
    }

    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        if(values.size() == 0){
            return;
        }
        double barWidth = (getWidth() - 10.0) / values.size() - 10.0;
        double scale = (getHeight() - 5.0) / maxValue;
        int i = 0;
        for(Double value : values){
            double barHeight = value * scale;
            g.setColor(colors.get(i));
            g.fillRect((int) (i * (barWidth + 10) + 5), (int) (getHeight() - barHeight), (int) barWidth, (int) barHeight);
            g.setColor(Color.black);
            g.drawRect((int) (i * (barWidth + 10) + 5), (int) (getHeight() - barHeight), (int) barWidth, (int) barHeight);
            int labelWidth = g.getFontMetrics().stringWidth(labels.get(i));
            int labelHeight = g.getFontMetrics().getHeight();
            if(labelWidth < barWidth){
                g.drawString(labels.get(i), (int) (i * (barWidth + 10) + 5 + (barWidth - labelWidth) / 2), getHeight() - labelHeight / 2);
            }
            i++;
        }
    }
}
