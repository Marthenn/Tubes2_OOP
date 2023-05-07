package Plugins;

import GUI.Setting;

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
}
