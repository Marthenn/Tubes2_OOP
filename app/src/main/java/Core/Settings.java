package Core;

import Core.DataStore.DataStore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Settings {
    // pada class ini terdapat minim pengecekan terhadap inputan dari user
    // hal ini dilatarbelakangi oleh datanya yang berasal dari GUI
    private static Settings instance = null;

    @Getter(AccessLevel.PUBLIC)
    private ArrayList<String> plugins = new ArrayList<>();

    private Settings(){}

    // Singleton pattern for Settings
    public static Settings getInstance(){
        if (Settings.instance == null){
            Settings.instance = new Settings();
        }
        return Settings.instance;
    }

    // TODO: Add settings for plugins
    public void addPlugin(JarInputStream pluginDirectory) throws Exception {
        ArrayList<String> classNames = null;
        try{
            classNames = getClassNames(pluginDirectory);
        } catch (Exception e){
            throw new Exception("Failed to load plugin");
        }
        for (String className : classNames) {
            System.out.println(className);
        }
    }

    public void removePlugin(String pluginName){
        // unload the plugin
        this.plugins.remove(pluginName);
    }

    private ArrayList<String> getClassNames(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try{
            JarEntry jar;
            while(true){
                jar = jarFile.getNextJarEntry();
                if(jar == null){
                    break;
                }
                if(jar.getName().endsWith(".class")){
                    classNames.add(jar.getName().replaceAll("/", "\\.").substring(0, jar.getName().length() - 6));
                }
            }
        } catch (Exception e){
            throw new Exception("Failed to get class names from jar file");
        }
        return classNames;
    }
}
