package Plugins;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface Plugin {
    public void load();
    public void unload();
    public String getName();
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
}
