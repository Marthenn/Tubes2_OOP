package Plugins;

import Core.Settings.Settings;
import GUI.Setting;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public interface Plugin {
    public void load();
    public void unload();
    default public String getName(){
        return this.getClass().getSimpleName();
    }

    public ArrayList<String> getItems();

    default Field findField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                return findField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }
    default Method findMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null) {
                return findMethod(clazz.getSuperclass(), name, parameterTypes);
            }
            return null;
        }
    }
    default void setField(Object obj, String name, Object value) {
        try {
            Field field = findField(obj.getClass(), name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    default Object getField(Object obj, String name) {
        try {
            Field field = findField(obj.getClass(), name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public default void addToSetting(String name, ArrayList<String> items){
        System.out.print("Adding " + name + " to setting...");
        Setting setting = Setting.getInstance();
        setting.addPlugin(name, items);
    }

    public default void removeFromSetting(String name){
        Setting setting = Setting.getInstance();
        setting.removePlugin(name);
    }

    public static void initialLoad(){
        // find pluginsLoaded.txt and load all plugins in it
        File file = new File("pluginsLoaded.txt");
        try{
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
                String line;
                Settings settings = Settings.getInstance();
                while ((line = br.readLine()) != null) {
                    settings.addPlugin(line);
                }
            }
            else {
                file.createNewFile();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write(String directory){
        // write to pluginsLoaded.txt
        File file = new File("pluginsLoaded.txt");
        try{
            // if not exist the path in pluginsLoaded.txt, add it
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(directory)) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(directory.getBytes());
            fos.write("\n".getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void remove(Plugin plugin){
        // remove from pluginsLoaded.txt
        File file = new File("pluginsLoaded.txt");
        System.out.println("Removing " + plugin.getName() + " from pluginsLoaded.txt");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // check if the plugin is in pluginsLoaded.txt (..../pluginName.jar)
                String pluginName = plugin.getName()+".jar";
                System.out.println(pluginName);
                if (line.endsWith(pluginName)) {
                    continue;
                }
                sb.append(line).append("\n");
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
