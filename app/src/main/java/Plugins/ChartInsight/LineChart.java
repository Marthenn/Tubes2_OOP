package Plugins.ChartInsight;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LineChart extends JPanel {
    private Map<String, Double> datas;
    public LineChart(){
        datas = new LinkedHashMap<>();
    }

    public void addData(String label, double value){
        datas.put(label, value);
        repaint();
    }

    public void clear(){
        datas.clear();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 25;
        int labelPadding = 25;
        int numXPoints = datas.size();
        int numYPoints = 10;
        int pointWidth = 4;
        int chartWidth = width - (2 * padding) - labelPadding;
        int chartHeight = height - (2 * padding) - labelPadding;
        int xUnit = chartWidth / (numXPoints - 1); // Updated xUnit calculation
        int yUnit = chartHeight / numYPoints;

        // Draw x and y axis lines
        g2d.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
        g2d.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

        // Draw x axis labels and tick marks
        int x = padding + labelPadding;
        for (String label : datas.keySet()) {
            g2d.drawString(label, x, height - padding + labelPadding);
            g2d.drawLine(x, height - padding - labelPadding, x, height - padding - labelPadding + pointWidth);
            x += xUnit;
        }

        // Draw y axis labels and tick marks
        int y = height - padding - labelPadding - yUnit;
        for (int i = 0; i <= numYPoints; i++) {
            g2d.drawString(String.valueOf(i), padding, y + labelPadding / 2);
            g2d.drawLine(padding + labelPadding - pointWidth, y, padding + labelPadding, y);
            y -= yUnit;
        }

        // Draw line chart
        g2d.setColor(Color.BLUE);
        int lastX = 0, lastY = 0;
        int index = 0;
        for (String label : datas.keySet()) {
            int xCoord = padding + labelPadding + xUnit * index;
            int yCoord = height - padding - labelPadding - (int) (yUnit * datas.get(label));
            g2d.fillOval(xCoord - pointWidth / 2, yCoord - pointWidth / 2, pointWidth, pointWidth);
            if (index > 0) {
                g2d.drawLine(lastX, lastY, xCoord, yCoord);
            }
            lastX = xCoord;
            lastY = yCoord;
            index++;
        }

        g2d.dispose();
    }
}
