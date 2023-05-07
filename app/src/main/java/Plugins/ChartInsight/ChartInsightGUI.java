/*
 * Created by JFormDesigner on Sun May 07 08:10:11 WIB 2023
 */

package Plugins.ChartInsight;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.Line;
import javax.swing.*;

import Core.DataStore.DataStore;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;

/**
 * @author Marthen
 */
public class ChartInsightGUI extends JPanel {
    private DataStore ds = DataStore.getInstance();
    private Map<Integer, Integer> items = new HashMap<>();
    public ChartInsightGUI() {
        initComponents();
        Thread updateData = new Thread (() -> {
            while(true){
                try{
                    Map<Integer,Integer> soldItems = ChartInsight.getSoldItems();
                    if(soldItems.equals(items)){
                        continue;
                    }else{
                        barPane.clear();
                        linePane.clear();
                        for(Map.Entry<Integer,Integer> entry : soldItems.entrySet()){
                            Integer key = entry.getKey();
                            Integer value = entry.getValue();
                            barPane.addValue(value, ds.getItemWithID(key).getName());
                            linePane.addData(ds.getItemWithID(key).getName(), value);
                        }
                        items = soldItems;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (SearchedItemNotExist e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateData.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
        splitPane1 = new JSplitPane();
        barPane = new BarChart();
        linePane = new LineChart();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new BorderLayout());

        //======== splitPane1 ========
        {
            splitPane1.setResizeWeight(0.5);

            //======== barPane ========
            {
                barPane.setLayout(new BorderLayout());
            }
            splitPane1.setLeftComponent(barPane);

            //======== linePane ========
            {
                linePane.setLayout(new BorderLayout());
            }
            splitPane1.setRightComponent(linePane);
        }
        add(splitPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Bintang Dwi Marthen
    private JSplitPane splitPane1;
    private BarChart barPane;
    private LineChart linePane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
