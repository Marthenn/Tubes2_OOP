package Core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
public class Settings {
    // pada class ini terdapat minim pengecekan terhadap inputan dari user
    // hal ini dilatarbelakangi oleh datanya yang berasal dari GUI
    private static Settings instance = null;

    @Getter(AccessLevel.PUBLIC)
    private Map<String, Boolean> fileType = new HashMap<>(){{
        put("OBJ", false);
        put("XML", false);
        put("JSON", false);
    }};

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String directoryPath = null;
    // should it be able to distinguish between file and directory?
    // Jika data file disimpan lebih dari 1 file maka basis penyimpanan adalah memilih folder

    private Settings(){}

    // Singleton pattern for Settings
    public static Settings getInstance(){
        if (Settings.instance == null){
            Settings.instance = new Settings();
        }
        return Settings.instance;
    }

    // TODO: Add settings for plugins
}
