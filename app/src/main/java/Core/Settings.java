package Core;

import Core.DataStore.DataStore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.*;
import java.util.jar.JarInputStream;

import static Plugins.Helper.loadClasses;
import Plugins.Plugin;

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
    @SneakyThrows
    public void addPlugin(String pluginDirectory) {
        List<Class<?>> classes = loadClasses(pluginDirectory);
        for (Class<?> cls : classes) {
            try{
                Object obj = cls.newInstance();
                if (obj instanceof Plugin){
                    Plugin plugin = (Plugin) obj;
                    System.out.println(plugin.getName());
                    this.plugins.add(plugin.getName());
                }
            } catch (Exception e){
                System.out.println("Failed to load plugin");
            }
        }
    }

    public void removePlugin(String pluginName){
        // unload the plugin
        this.plugins.remove(pluginName);
    }
}

