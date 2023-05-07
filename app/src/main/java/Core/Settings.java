package Core;

import Plugins.Plugin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Plugins.Helper.loadClasses;

public class Settings {
    // pada class ini terdapat minim pengecekan terhadap inputan dari user
    // hal ini dilatarbelakangi oleh datanya yang berasal dari GUI
    private static Settings instance = null;
    @Setter @Getter(AccessLevel.PUBLIC)
    private String path;

    @Getter(AccessLevel.PUBLIC)
    private Map<String, Boolean> fileType = new HashMap<>(){{
        put("OBJ", false);
        put("JSON", true);
        put("XML", false);
    }};

    @Getter(AccessLevel.PUBLIC)
    private ArrayList<Plugin> plugins = new ArrayList<>();

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
                    System.out.println(plugin);
                    System.out.println(plugin.getName());
                    this.plugins.add(plugin);
                    plugin.load();
                }
            } catch (Exception e){
                System.out.println("Failed to load plugin: " + cls.getName() + "\n " + e.getMessage());
            }
        }
    }
    public void removePlugin(Plugin plugin){
        // unload the plugin
        for (Plugin pl : this.plugins){
            if (pl.getName().equals(plugin.getName())){
                pl.unload();
                this.plugins.remove(pl);
                break;
            }
        }
    }
    public void savePath() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("jsonConfig/path_config.json").toFile(), this.path);
    }
    public void saveFileType() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(Paths.get("jsonConfig/filetype_config.json").toFile(), this.fileType);
    }
    public void loadPath() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("jsonConfig/path_config.json")));
        this.path =  new ObjectMapper().readValue(json, String.class);
    }
    public void loadFileType() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("jsonConfig/fileType_config.json")));
        this.fileType = new ObjectMapper().readValue(json, Map.class);
    }
}

